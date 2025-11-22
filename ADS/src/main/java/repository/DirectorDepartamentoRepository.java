package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.DirectorDepartamento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectorDepartamentoRepository extends ProfesorRepository<DirectorDepartamento> {
    public DirectorDepartamentoRepository() {
        super("src/main/resources/data/directordepartamento.json",
                new TypeReference<List<DirectorDepartamento>>() {});
    }
}

