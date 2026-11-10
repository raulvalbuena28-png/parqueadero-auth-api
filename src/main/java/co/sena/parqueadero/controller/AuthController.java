package co.sena.parqueadero.controller;

import co.sena.parqueadero.dto.AuthRequest;
import co.sena.parqueadero.dto.AuthResponse;
import co.sena.parqueadero.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Endpoints REST del servicio de autenticación.
 *
 *   POST /api/auth/registro  -> crea un usuario
 *   POST /api/auth/login     -> valida usuario y contraseña
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000") // permite llamadas desde React en desarrollo
public class AuthController {

    private final AuthService servicio;

    public AuthController(AuthService servicio) {
        this.servicio = servicio;
    }

    // Registro: responde 201 si se creó el usuario
    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registro(@Valid @RequestBody AuthRequest datos) {
        servicio.registrar(datos.getUsuario(), datos.getContrasena());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse("Usuario registrado correctamente"));
    }

    // Login: 200 con "Autenticación satisfactoria" o 401 con "Error en la autenticación"
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest datos) {
        servicio.iniciarSesion(datos.getUsuario(), datos.getContrasena());
        return ResponseEntity.ok(new AuthResponse("Autenticación satisfactoria"));
    }
}
