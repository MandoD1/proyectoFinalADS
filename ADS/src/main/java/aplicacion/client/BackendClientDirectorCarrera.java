package aplicacion.client;

import aplicacion.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BackendClientDirectorCarrera{

    private final RestTemplate rest = new RestTemplate();
    private final String BASE = "http://localhost:8080/api/directorcarrera";


    public void crearClase(int horas, Long cId, Long profesorId, List<String> horario, String salon, int cupoMaximo, String semestre, Long asignaturaId) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/crearClase").queryParam("horas", horas).queryParam("cId", cId).queryParam("profesorId", profesorId).queryParam("horario", horario).queryParam("salon", salon).queryParam("cupoMaximo", cupoMaximo).queryParam("semestre", semestre).queryParam("AsignaturaId", asignaturaId).toUriString();
        rest.getForObject(url, Void.class);
    }

    public Departamento crearAsignatura(Long semestreId, Long dId, String nombre, List<Long> corequisitos, boolean requisitoIngles, List<Long> prerequisitos, int creditos) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/crearasignatura").queryParam("semestreId", semestreId).queryParam("dId", dId).queryParam("nombre", nombre).queryParam("Corequisitos", corequisitos).queryParam("requisitoIngles", requisitoIngles).queryParam("Prerequisitos", prerequisitos).queryParam("creditos", creditos).toUriString();
        return rest.getForObject(url, Departamento.class);
    }

    public void modificarClase(int horas, Long cId, Long nuevoId, Long profesorId, List<String> horario, String salon, int cupoMaximo, int cupoActual, String semestre, Long AsignaturaId, List<Long> estudiantes) {

        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/modificarclase").queryParam("horas", horas).queryParam("cId", cId).queryParam("nuevoId", nuevoId).queryParam("profesorId", profesorId).queryParam("horario", horario).queryParam("salon", salon).queryParam("cupoMaximo", cupoMaximo).queryParam("cupoActual", cupoActual).queryParam("semestre", semestre).queryParam("AsignaturaId", AsignaturaId).queryParam("estudiantes", estudiantes).toUriString();

        rest.getForObject(url, Void.class);
    }


    public void eliminarClase(Long idClase) {
        String url = BASE + "/eliminarclase?cId=" + idClase;
        rest.getForObject(url, Void.class);
    }

    public void eliminarAsignatura(Long idAsignatura) {
        String url = BASE + "/eliminarasignatura?aId=" + idAsignatura;
        rest.getForObject(url, Void.class);
    }

    public void modificarCarga(Long pId, Long cId, boolean accion){
        String url = UriComponentsBuilder.fromHttpUrl(BASE + "/modificarclase").queryParam("pId", pId).queryParam("cId", cId).queryParam("accion", accion).toUriString();
        rest.getForObject(url, Void.class);
    }

}

