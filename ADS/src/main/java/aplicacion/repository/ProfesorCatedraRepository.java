package aplicacion.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import aplicacion.model.ProfesorCatedra;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfesorCatedraRepository extends ProfesorRepository<ProfesorCatedra> {

    private static final TypeReference<List<ProfesorCatedra>> tipo = new TypeReference<List<ProfesorCatedra>>() {};

    public ProfesorCatedraRepository() {
        super("ADS/data/profesorcatedra.json", tipo);
    }

    public List<ProfesorCatedra> loadAllCatedra() {
        try {
            if (file.exists()) {
                return mapper.readValue(file, tipo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo profesorcatedra.json", e);
        }
        return null;
    }
}
