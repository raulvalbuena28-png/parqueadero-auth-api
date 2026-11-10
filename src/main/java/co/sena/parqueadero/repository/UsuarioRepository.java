package co.sena.parqueadero.repository;

import co.sena.parqueadero.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Acceso a datos de la tabla "usuarios". Spring genera la implementación.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca un usuario por su nombre de usuario
    Optional<Usuario> findByUsuario(String usuario);

    // Indica si ya existe un usuario con ese nombre
    boolean existsByUsuario(String usuario);
}
