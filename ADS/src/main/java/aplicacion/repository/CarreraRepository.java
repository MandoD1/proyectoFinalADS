package aplicacion.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import aplicacion.model.Carrera;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarreraRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("ADS/data/carrera.json");

    private List<Carrera> carreras = new ArrayList<>();

    public CarreraRepository() {
        loadData();
    }

    private void loadData() {
        try {
            if (file.exists()) {
                carreras = mapper.readValue(file, new TypeReference<List<Carrera>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error cargando clases.json", e);
        }
    }

    private void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, carreras);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando clases.json", e);
        }
    }

    public List<Carrera> findAll() {
        return carreras;
    }

    public Carrera findById(Long id) {
        return carreras.stream()
                .filter(c -> c.getCodigo().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Carrera save(Carrera carrera) {
        if (carrera.getCodigo() == null) {
            carrera.setCodigo(generateId());
        }

        carreras.removeIf(c -> c.getCodigo().equals(carrera.getCodigo()));
        carreras.add(carrera);
        saveData();
        return carrera;
    }

    public void delete(Long id) {
        carreras.removeIf(c -> c.getCodigo().equals(id));
        saveData();
    }

    private Long generateId() {
        return carreras.stream()
                .mapToLong(Carrera::getCodigo)
                .max()
                .orElse(0) + 1;
    }
}
