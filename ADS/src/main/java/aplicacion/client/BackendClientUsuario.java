package aplicacion.client;

import aplicacion.model.Usuario;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class BackendClientUsuario {

    private final RestTemplate rest = new RestTemplate();
    private final String BASE = "http://localhost:8080/api/usuario"; // URL base de tu API

    /**
     * Registra un nuevo usuario en el sistema.
     */
    public Usuario registrarUsuario(String correo, String contraseña, String tipoUsuario) {
        // Crea la URL para el registro
        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/register")
                .queryParam("correo", correo)
                .queryParam("contraseña", contraseña)
                .queryParam("tipoUsuario", tipoUsuario)
                .toUriString();

        // Realiza la solicitud POST para registrar al usuario
        return rest.postForObject(url, null, Usuario.class);
    }

    /**
     * Realiza login de un usuario.
     */
    public Usuario login(String correo, String contraseña) {
        // Crea la URL para el login
        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/login")
                .queryParam("correo", correo)
                .queryParam("contraseña", contraseña)
                .toUriString();

        // Realiza la solicitud POST para intentar loguearse
        return rest.postForObject(url, null, Usuario.class);
    }
}
