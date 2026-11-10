package co.sena.parqueadero.exception;

import co.sena.parqueadero.dto.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Convierte las excepciones en respuestas HTTP con un mensaje claro.
 */
@RestControllerAdvice
public class ManejadorErrores {

    // 401: credenciales incorrectas -> "error en la autenticación"
    @ExceptionHandler(AutenticacionException.class)
    public ResponseEntity<AuthResponse> autenticacion(AutenticacionException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse(e.getMessage()));
    }

    // 409: el usuario ya está registrado
    @ExceptionHandler(UsuarioExistenteException.class)
    public ResponseEntity<AuthResponse> existente(UsuarioExistenteException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new AuthResponse(e.getMessage()));
    }

    // 400: datos vacíos o con formato inválido
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AuthResponse> validacion(MethodArgumentNotValidException e) {
        String detalle = e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(new AuthResponse(detalle));
    }
}
