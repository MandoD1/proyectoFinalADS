package aplicacion.client;

import org.springframework.web.client.RestTemplate;

public class BackendClient {

    private final RestTemplate rest = new RestTemplate();
    private final String BASE = "http://localhost:8080/api/directordepartamento";

    public void generarInformeProfesor(Long idProfesor) {
        String url = BASE + "/erprofesor?pId=" + idProfesor;
        rest.getForObject(url, Void.class);
    }

    public void generarInformeProfesores() {
        rest.getForObject(BASE + "/verProfesores", Void.class);
    }

    public double calcularPago(long id, int horas, int min, int max) {
        String url = BASE + "/calcularpago?pId=" + id + "&horasDictadas=" + horas + "&minHoras=" + min + "&maxHoras=" + max;
        return rest.getForObject(url, Double.class);
    }
}
