package aplicacion.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import aplicacion.model.DirectorDepartamento;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectorDepartamentoRepository extends ProfesorRepository<DirectorDepartamento> {
    public DirectorDepartamentoRepository() {
        super("ADS/data/directordepartamento.json",
                new TypeReference<List<DirectorDepartamento>>() {});
    }
}

