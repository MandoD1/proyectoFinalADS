package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Profesor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfesorRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("src/main/resources/data/profesor.json");

    private List<Profesor> profesores = new ArrayList<>();

    public ProfesorRepository() {
        loadData();
    }

    private void loadData() {
        try {
            if (file.exists()) {
                profesores = mapper.readValue(file, new TypeReference<List<Profesor>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo archivo JSON", e);
        }
    }

    private void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, profesores);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando archivo JSON", e);
        }
    }

    public List<Profesor> findAll() {
        return profesores;
    }

    public Profesor findById(long id) {
        return profesores.stream()
                .filter(e -> e.getCodigo() == id)
                .findFirst()
                .orElse(null);
    }

    public Profesor save(Profesor e) {
        if (e.getCodigo() == 0) {
            e.setCodigo(generateId());
        }
        profesores.removeIf(x -> x.getCodigo() == e.getCodigo());
        profesores.add(e);
        saveData();
        return e;
    }

    public void delete(long id) {
        profesores.removeIf(e -> e.getCodigo() == id);
        saveData();
    }

    private long generateId() {
        return profesores.stream()
                .mapToLong(Profesor::getCodigo)
                .max()
                .orElse(0) + 1;
    }
}
