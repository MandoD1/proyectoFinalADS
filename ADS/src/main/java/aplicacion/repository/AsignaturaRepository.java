package aplicacion.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import aplicacion.model.Asignatura;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Repository
public class AsignaturaRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("ADS/data/asignaturas.json");

    private List<Asignatura> asignaturas = new ArrayList<>();

    public AsignaturaRepository() {
        loadData();
    }

    private void loadData() {
        try {
            if (file.exists()) {
                asignaturas = mapper.readValue(file, new TypeReference<List<Asignatura>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error cargando asignaturas.json", e);
        }
    }

    private void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, asignaturas);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando asignaturas.json", e);
        }
    }

    public List<Asignatura> findAll() {
        return asignaturas;
    }

    public Asignatura findById(Long id) {
        return asignaturas.stream()
                .filter(c -> c.getCodigo().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Asignatura save(Asignatura asignatura) {
        if (asignatura.getCodigo() == null) {
            asignatura.setCodigo(generateId());
        }

        asignaturas.removeIf(c -> c.getCodigo().equals(asignatura.getCodigo()));
        asignaturas.add(asignatura);
        saveData();
        return asignatura;
    }

    public void delete(Long id) {
        asignaturas.removeIf(c -> c.getCodigo().equals(id));
        saveData();
    }

    private Long generateId() {
        return asignaturas.stream()
                    .mapToLong(Asignatura::getCodigo)
                .max()
                .orElse(0) + 1;
    }
}
