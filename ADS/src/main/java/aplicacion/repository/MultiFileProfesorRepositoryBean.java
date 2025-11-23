package aplicacion.repository;

import aplicacion.model.Profesor;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiFileProfesorRepositoryBean extends MultiFileProfesorRepository<Profesor> {

    public MultiFileProfesorRepositoryBean() {
        super(
                "ADS/data/profesorplanta.json",
                "ADS/data/profesorcatedra.json",
                new TypeReference<List<Profesor>>() {}
        );
    }
}
