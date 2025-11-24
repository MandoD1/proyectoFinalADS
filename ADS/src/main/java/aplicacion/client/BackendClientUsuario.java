package aplicacion.client;

import aplicacion.model.Usuario;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class BackendClientUsuario {

    private final RestTemplate rest = new RestTemplate();
    private final String BASE = "http://localhost:8080/api/usuario";

    public Usuario registrarUsuario(String correo, String contraseña, String tipoUsuario) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/register")
                .queryParam("correo", correo)
                .queryParam("contraseña", contraseña)
                .queryParam("tipoUsuario", tipoUsuario)
                .toUriString();

        return rest.postForObject(url, null, Usuario.class);
    }

    public Usuario login(String correo, String contraseña) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/login")
                .queryParam("correo", correo)
                .queryParam("contraseña", contraseña)
                .toUriString();

        return rest.postForObject(url, null, Usuario.class);
    }
}
