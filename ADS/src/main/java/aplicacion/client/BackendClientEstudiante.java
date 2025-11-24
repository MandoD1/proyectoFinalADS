package aplicacion.client;

import aplicacion.controllers.UsuarioController;
import aplicacion.model.Clase;
import aplicacion.model.Estudiante;
import aplicacion.repository.UsuarioRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class BackendClientEstudiante {

    private final RestTemplate rest = new RestTemplate();
    private final String BASE = "http://localhost:8080/api/estudiante";
    private final UsuarioRepository usuarioRepository;

    public BackendClientEstudiante(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Estudiante asignarClase(Long cId) {
        Long eId = usuarioRepository.loadUsuario().getId();
        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/asignarclases").queryParam("eId", eId).queryParam("cId", cId).toUriString();

        return rest.postForObject(url, null, Estudiante.class);
    }

    public Estudiante retirarClase(Long cId) {
        Long eId = usuarioRepository.loadUsuario().getId();
        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/retirarclases").queryParam("eId", eId).queryParam("cId", cId).toUriString();

        return rest.postForObject(url, null, Estudiante.class);
    }

    public List<Clase> verClases(Long eId) {

        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/verclases").queryParam("eId", eId).toUriString();

        Clase[] clasesArray = rest.postForObject(url, null, Clase[].class);

        return List.of(clasesArray);
    }

    public Estudiante retirarCarrera(Long eId, Long cId) {

        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/retirarcarrera").queryParam("eId", eId).queryParam("cId", cId).toUriString();

        return rest.postForObject(url, null, Estudiante.class);
    }

    public Estudiante asignarCarrera(Long aId, Long carreraId) {

        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/asingcarrera").queryParam("aId", aId).queryParam("carreraId", carreraId).toUriString();

        return rest.postForObject(url, null, Estudiante.class);
    }


}
