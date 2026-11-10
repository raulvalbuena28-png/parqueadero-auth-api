package co.sena.parqueadero.dto;

/**
 * Respuesta del servicio: un mensaje para el cliente (por ejemplo React).
 */
public class AuthResponse {

    private final String mensaje;

    public AuthResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() { return mensaje; }
}
