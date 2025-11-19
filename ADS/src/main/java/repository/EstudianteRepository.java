package repository;
import model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EstudianteRepository extends JpaRepository<Estudiante, Long>{
}

