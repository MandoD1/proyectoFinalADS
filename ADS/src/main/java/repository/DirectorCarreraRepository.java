package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.DirectorCarrera;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectorCarreraRepository extends ProfesorRepository<DirectorCarrera> {
    public DirectorCarreraRepository() {
        super("src/main/resources/data/directorcarrera.json",
                new TypeReference<List<DirectorCarrera>>() {});
    }
}

