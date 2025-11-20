package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.ProfesorCatedra;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfesorCatedraRepository extends ProfesorRepository<ProfesorCatedra> {
    public ProfesorCatedraRepository() {
        super("src/main/resources/data/profesorcatedra.json",
                new TypeReference<List<ProfesorCatedra>>() {});
    }
}
