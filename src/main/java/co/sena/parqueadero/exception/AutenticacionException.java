package co.sena.parqueadero.exception;

/** Se lanza cuando el usuario o la contraseña son incorrectos (HTTP 401). */
public class AutenticacionException extends RuntimeException {
    public AutenticacionException(String mensaje) {
        super(mensaje);
    }
}
