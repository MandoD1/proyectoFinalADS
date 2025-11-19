package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Clase;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClaseRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("src/main/resources/data/clases.json");

    private List<Clase> clases = new ArrayList<>();

    public ClaseRepository() {
        loadData();
    }

    private void loadData() {
        try {
            if (file.exists()) {
                clases = mapper.readValue(file, new TypeReference<List<Clase>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error cargando clases.json", e);
        }
    }

    private void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, clases);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando clases.json", e);
        }
    }

    public List<Clase> findAll() {
        return clases;
    }

    public Clase findById(Long id) {
        return clases.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Clase save(Clase clase) {
        if (clase.getId() == null) {
            clase.setId(generateId());
        }

        clases.removeIf(c -> c.getId().equals(clase.getId()));
        clases.add(clase);
        saveData();
        return clase;
    }

    public void delete(Long id) {
        clases.removeIf(c -> c.getId().equals(id));
        saveData();
    }

    private Long generateId() {
        return clases.stream()
                .mapToLong(Clase::getId)
                .max()
                .orElse(0) + 1;
    }
}
