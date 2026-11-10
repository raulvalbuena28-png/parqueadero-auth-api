package co.sena.parqueadero.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un usuario del sistema del parqueadero.
 * Se guarda en la tabla "usuarios" de MySQL.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // El nombre de usuario es único: no puede haber dos iguales
    @Column(nullable = false, unique = true, length = 50)
    private String usuario;

    // Aquí se almacena el hash BCrypt de la contraseña
    @Column(nullable = false)
    private String contrasena;

    public Usuario() { }

    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Long getId() { return id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}
