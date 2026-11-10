package co.sena.parqueadero.exception;

/** Se lanza cuando se intenta registrar un usuario que ya existe (HTTP 409). */
public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException(String mensaje) {
        super(mensaje);
    }
}
