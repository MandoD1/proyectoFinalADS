package aplicacion.repository;

import aplicacion.model.Profesor;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultiFileProfesorRepository<T extends Profesor> extends ProfesorRepository<T> {

    private final List<T> data2 = new ArrayList<>();
    private final File file2;

    public MultiFileProfesorRepository(String filePath1, String filePath2, TypeReference<List<T>> type) {
        super(filePath1, type);
        this.file2 = new File(filePath2);
        loadSecondFile(type);
    }

    private void loadSecondFile(TypeReference<List<T>> type) {
        try {
            if (file2.exists()) {
                List<T> temp = mapper.readValue(file2, type);
                data2.addAll(temp);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo JSON: " + file2.getPath(), e);
        }
    }

    @Override
    public T findById(Long id) {
        T result = super.findById(id);
        if (result != null) return result;
        return data2.stream()
                .filter(e -> ((HasId) e).getCodigo() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> findAll() {
        List<T> all = new ArrayList<>(super.findAll());
        all.addAll(data2);
        return all;
    }

    @Override
    public void delete(Long id) {
        boolean removed = super.findAll().removeIf(e -> ((HasId)e).getCodigo() == id);
        removed = data2.removeIf(e -> ((HasId)e).getCodigo() == id) || removed;
        if (removed) {
            saveData();
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file2, data2);
            } catch (Exception e) {
                throw new RuntimeException("Error guardando JSON: " + file2.getPath(), e);
            }
        }
    }

    @Override
    public T save(T e) {
        HasId obj = (HasId)e;
        if (obj.getCodigo() == 0) obj.setCodigo(generateId());
        boolean updated = false;

        if (super.findAll().stream().anyMatch(x -> ((HasId)x).getCodigo() == obj.getCodigo())) {
            super.findAll().removeIf(x -> ((HasId)x).getCodigo() == obj.getCodigo());
            super.findAll().add(e);
            saveData();
            updated = true;
        }

        if (!updated && data2.stream().anyMatch(x -> ((HasId)x).getCodigo() == obj.getCodigo())) {
            data2.removeIf(x -> ((HasId)x).getCodigo() == obj.getCodigo());
            data2.add(e);
            try {
                mapper.writerWithDefaultPrettyPrinter().writeValue(file2, data2);
            } catch (Exception ex) {
                throw new RuntimeException("Error guardando JSON: " + file2.getPath(), ex);
            }
            updated = true;
        }

        if (!updated) {
            super.save(e);
        }

        return e;
    }
}
