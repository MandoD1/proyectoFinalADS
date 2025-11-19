package services;

import model.Clase;
import model.Estudiante;
import repository.EstudianteRepository;
import repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {
    private final EstudianteRepository estudianteRepository;
    private final ClaseRepository claseRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository, ClaseRepository claseRepository) {
        this.estudianteRepository = estudianteRepository;
        this.claseRepository = claseRepository;
    }   

    public Estudiante createEstudiante(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiante> findAllEstudiante(){
        return estudianteRepository.findAll();
    }

    public Estudiante findEstudianteById(Long id){
        try {
            return estudianteRepository.findById(id).orElseThrow(() -> new Exception("Estudiante no encontrado"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public Estudiante updateEstudiante(Long id, Estudiante estudiante){
        Estudiante estudiante1 = findEstudianteById(id);
        estudiante1.setNombre(estudiante.getNombre());
        estudiante1.setCarrera(estudiante.getCarrera());
        estudiante1.setEmail(estudiante.getEmail());
        estudiante1.setExamenIngles(estudiante.isExamenIngles());
        estudiante1.setClases(estudiante.getClases());
        return estudianteRepository.save(estudiante1);
    }

    public void deleteEstudiante(Long id){
        Estudiante estudiante1 = findEstudianteById(id);
        if(estudiante1 != null){
            estudianteRepository.delete(estudiante1);
        } else {
            throw new IllegalArgumentException("Estudiante no encontrado");
        }
    }

    public Estudiante assignClassToEstudiante(Long id, Long claseId){
        Optional<Estudiante> estudiante1 = estudianteRepository.findById(id);
        if(!estudiante1.isPresent()){
            throw  new IllegalArgumentException("Estudiante no encontrado");
        }
        Estudiante estudiante = estudiante1.get();

        Optional<Clase> clases = claseRepository.findById(claseId);
        if(!clases.isPresent()){
            throw  new IllegalArgumentException("Clases no encontrado");
        }

        Clase clase = clases.get();
        estudiante.addClase(clase);
        return estudianteRepository.save(estudiante);
    }
}
