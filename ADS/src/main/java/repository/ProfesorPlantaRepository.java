package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.ProfesorPlanta;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfesorPlantaRepository extends ProfesorRepository<ProfesorPlanta> {

    private static final TypeReference<List<ProfesorPlanta>> tipo = new TypeReference<List<ProfesorPlanta>>() {};

    public ProfesorPlantaRepository() {
        super("src/main/resources/data/profesorplanta.json", tipo);
    }

    public List<ProfesorPlanta> loadAllPlanta() {
        try {
            if (file.exists()) {
                return mapper.readValue(file, tipo);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo profesorplanta.json", e);
        }
        return null;
    }
}
