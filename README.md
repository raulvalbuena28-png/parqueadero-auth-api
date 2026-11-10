# Parqueadero - Servicio web de registro e inicio de sesión
Evidencia GA7-220501096-AA5-EV01 (SENA)

## Tecnologías
Java 17 · Spring Boot 3 · Spring Data JPA · MySQL · BCrypt

## Ejecución
1. Tener MySQL en marcha y ajustar usuario/contraseña en `src/main/resources/application.properties`.
2. Ejecutar: `mvn spring-boot:run`
3. El servicio queda en `http://localhost:8080`

## Endpoints
| Método | Ruta | Descripción |
|---|---|---|
| POST | /api/auth/registro | Registra un usuario |
| POST | /api/auth/login | Inicia sesión |

Cuerpo JSON (ambos): `{ "usuario": "admin", "contrasena": "Clave123" }`

| Caso | Código | Respuesta |
|---|---|---|
| Registro correcto | 201 | `{"mensaje":"Usuario registrado correctamente"}` |
| Usuario repetido | 409 | `{"mensaje":"El usuario ya está registrado"}` |
| Login correcto | 200 | `{"mensaje":"Autenticación satisfactoria"}` |
| Login incorrecto | 401 | `{"mensaje":"Error en la autenticación"}` |
| Datos vacíos/cortos | 400 | `{"mensaje":"<detalle>"}` |

## Pruebas con curl
```
curl -X POST http://localhost:8080/api/auth/registro -H "Content-Type: application/json" -d '{"usuario":"admin","contrasena":"Clave123"}'
curl -X POST http://localhost:8080/api/auth/login    -H "Content-Type: application/json" -d '{"usuario":"admin","contrasena":"Clave123"}'
curl -X POST http://localhost:8080/api/auth/login    -H "Content-Type: application/json" -d '{"usuario":"admin","contrasena":"mala"}'
```
