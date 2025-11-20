package services;

import model.Clase;
import model.Estudiante;
import repository.EstudianteRepository;
import repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Estudiante estudiante = estudianteRepository.findById(id);
        if (estudiante == null)
            throw new IllegalArgumentException("Estudiante no encontrado");
        return estudiante;
    }

    public Estudiante updateEstudiante(Long id, Estudiante estudiante){
        Estudiante original = findEstudianteById(id);

        original.setNombre(estudiante.getNombre());
        original.setEmail(estudiante.getEmail());
        original.setCarrera(estudiante.getCarrera());
        original.setExamenIngles(estudiante.isExamenIngles());
        original.setClases(estudiante.getClases());

        return estudianteRepository.save(original);
    }

    public void deleteEstudiante(Long id){
        findEstudianteById(id);
        estudianteRepository.delete(id);
    }

    public Estudiante assignClassToEstudiante(Long estudianteId, Long claseId){
        Estudiante estudiante = findEstudianteById(estudianteId);
        Clase clase = claseRepository.findById(claseId);

        if (clase == null)
            throw new IllegalArgumentException("Clase no encontrada");

        if(!verificarCupo(claseId)){
           throw new IllegalArgumentException("Clase llena");
        }

        clase.aumentarCupo();
        estudiante.addClase(clase);
        return estudianteRepository.save(estudiante);
    }

    public Estudiante retirarClaseOfEstudiante(Long estudianteId, Long claseId){
        Estudiante estudiante = findEstudianteById(estudianteId);
        Clase clase = claseRepository.findById(claseId);
        if (clase == null)
            throw new IllegalArgumentException("Clase no encontrada");

        estudiante.deleteClase(clase);
        return estudianteRepository.save(estudiante);
    }

    public List<Clase> SeeClasesOfEstudiante(Long estudianteId){
        Estudiante estudiante = findEstudianteById(estudianteId);
        return estudiante.getClases();
    }

    public boolean verificarCupo(Long claseId){
        Clase clase = claseRepository.findById(claseId);
        return clase.cupo();
    }

}
