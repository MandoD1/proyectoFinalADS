package aplicacion.repository;

import aplicacion.model.Semestre;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import aplicacion.model.Departamento;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SemestreRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("ADS/data/semestre.json");

    private List<Semestre> semestres = new ArrayList<>();

    public SemestreRepository() {
        loadData();
    }

    private void loadData() {
        try {
            if (file.exists()) {
                semestres = mapper.readValue(file, new TypeReference<List<Semestre>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error cargando semestre.json", e);
        }
    }

    private void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, semestres);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando semestre.json", e);
        }
    }

    public List<Semestre> findAll() {
        return semestres;
    }

    public Semestre findById(Long id) {
        return semestres.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Semestre save(Semestre semestre) {
        if (semestre.getId() == null) {
            semestre.setId(generateId());
        }

        semestres.removeIf(c -> c.getId().equals(semestre.getId()));
        semestres.add(semestre);
        saveData();
        return semestre;
    }

    public void delete(Long id) {
        semestres.removeIf(c -> c.getId().equals(id));
        saveData();
    }

    private Long generateId() {
        return semestres.stream()
                .mapToLong(Semestre::getId)
                .max()
                .orElse(0) + 1;
    }
}
