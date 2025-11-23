package aplicacion.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class ProfesorRepository<T> {

    protected final ObjectMapper mapper = new ObjectMapper();
    protected final File file;
    protected List<T> data = new ArrayList<>();

    public ProfesorRepository(String filePath, TypeReference<List<T>> type) {
        this.file = new File(filePath);
        loadData(type);
    }

    private void loadData(TypeReference<List<T>> type) {
        try {
            if (file.exists()) {
                data = mapper.readValue(file, type);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo JSON", e);
        }
    }

    protected void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando JSON", e);
        }
    }

    public List<T> findAll() { return data; }

    public T findById(Long id) {
        return data.stream()
                .filter(e -> ((HasId) e).getCodigo() == id)
                .findFirst()
                .orElse(null);
    }

    public T save(T e) {
        HasId obj = (HasId)e;
        if (obj.getCodigo() == 0) obj.setCodigo(generateId());
        data.removeIf(x -> ((HasId)x).getCodigo() == obj.getCodigo());
        data.add(e);
        saveData();
        return e;
    }

    public void delete(Long id) {
        data.removeIf(e -> ((HasId)e).getCodigo() == id);
        saveData();
    }

    public long generateId() {
        return data.stream()
                .mapToLong(e -> ((HasId)e).getCodigo())
                .max()
                .orElse(0) + 1;
    }
}
