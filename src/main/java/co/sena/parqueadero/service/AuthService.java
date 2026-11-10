package co.sena.parqueadero.service;

import co.sena.parqueadero.exception.AutenticacionException;
import co.sena.parqueadero.exception.UsuarioExistenteException;
import co.sena.parqueadero.model.Usuario;
import co.sena.parqueadero.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Lógica de negocio del registro y del inicio de sesión.
 */
@Service
public class AuthService {

    private final UsuarioRepository repositorio;
    private final PasswordEncoder codificador;

    public AuthService(UsuarioRepository repositorio, PasswordEncoder codificador) {
        this.repositorio = repositorio;
        this.codificador = codificador;
    }

    /**
     * Registra un usuario nuevo. La contraseña se guarda cifrada con BCrypt.
     */
    public void registrar(String usuario, String contrasena) {
        // Si el nombre de usuario ya existe, no se permite registrarlo otra vez
        if (repositorio.existsByUsuario(usuario)) {
            throw new UsuarioExistenteException("El usuario ya está registrado");
        }
        String hash = codificador.encode(contrasena);
        repositorio.save(new Usuario(usuario, hash));
    }

    /**
     * Valida usuario y contraseña. Si algo falla lanza AutenticacionException.
     * Se usa el mismo mensaje en ambos casos para no revelar cuál dato falló.
     */
    public void iniciarSesion(String usuario, String contrasena) {
        Usuario encontrado = repositorio.findByUsuario(usuario)
                .orElseThrow(() -> new AutenticacionException("Error en la autenticación"));

        // matches() compara la contraseña recibida contra el hash guardado
        if (!codificador.matches(contrasena, encontrado.getContrasena())) {
            throw new AutenticacionException("Error en la autenticación");
        }
    }
}
