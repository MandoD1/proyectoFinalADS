package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Departamento;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DepartamentoRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("src/main/resources/data/departamento.json");

    private List<Departamento> departamentos = new ArrayList<>();

    public DepartamentoRepository() {
        loadData();
    }

    private void loadData() {
        try {
            if (file.exists()) {
                departamentos = mapper.readValue(file, new TypeReference<List<Departamento>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error cargando departamento.json", e);
        }
    }

    private void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, departamentos);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando departamento.json", e);
        }
    }

    public List<Departamento> findAll() {
        return departamentos;
    }

    public Departamento findById(Long id) {
        return departamentos.stream()
                .filter(c -> c.getCodigo().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Departamento save(Departamento departamento) {
        if (departamento.getCodigo() == null) {
            departamento.setCodigo(generateId());
        }

        departamentos.removeIf(c -> c.getCodigo().equals(departamento.getCodigo()));
        departamentos.add(departamento);
        saveData();
        return departamento;
    }

    public void delete(Long id) {
        departamentos.removeIf(c -> c.getCodigo().equals(id));
        saveData();
    }

    private Long generateId() {
        return departamentos.stream()
                .mapToLong(Departamento::getCodigo)
                .max()
                .orElse(0) + 1;
    }
}
