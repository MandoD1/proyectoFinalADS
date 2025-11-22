package aplicacion.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import aplicacion.model.Estudiante;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EstudianteRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("ADS/data/estudiantes.json");

    private List<Estudiante> estudiantes = new ArrayList<>();

    public EstudianteRepository() {
        loadData();
    }

    private void loadData() {
        try {
            if (file.exists()) {
                estudiantes = mapper.readValue(file, new TypeReference<List<Estudiante>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo archivo JSON", e);
        }
    }

    private void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, estudiantes);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando archivo JSON", e);
        }
    }

    public List<Estudiante> findAll() {
        return estudiantes;
    }

    public Estudiante findById(long id) {
        return estudiantes.stream()
                .filter(e -> e.getCodigo() == id)
                .findFirst()
                .orElse(null);
    }

    public Estudiante save(Estudiante e) {
        if (e.getCodigo() == 0) {
            e.setCodigo(generateId());
        }
        estudiantes.removeIf(x -> x.getCodigo() == e.getCodigo());
        estudiantes.add(e);
        saveData();
        return e;
    }

    public void delete(long id) {
        estudiantes.removeIf(e -> e.getCodigo() == id);
        saveData();
    }

    private long generateId() {
        return estudiantes.stream()
                .mapToLong(Estudiante::getCodigo)
                .max()
                .orElse(0) + 1;
    }
}
