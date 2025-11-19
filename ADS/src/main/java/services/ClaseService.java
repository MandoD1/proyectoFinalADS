package services;

import model.Clase;
import model.Estudiante;
import repository.EstudianteRepository;
import repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaseService {

    private final EstudianteRepository estudianteRepository;
    private final ClaseRepository claseRepository;

    @Autowired
    public ClaseService(EstudianteRepository estudianteRepository, ClaseRepository claseRepository) {
        this.estudianteRepository = estudianteRepository;
        this.claseRepository = claseRepository;
    }

    public Clase createClase(Clase clase){
        return claseRepository.save(clase);
    }

    public List<Clase> findAllClases(){
        return claseRepository.findAll();
    }

    public Clase findClaseById(Long id){
        Clase clase = claseRepository.findById(id);
        if (clase == null)
            throw new IllegalArgumentException("Clase no encontrada");
        return clase;
    }

    public Clase updateClase(Long id, Clase clase){
        Clase original = findClaseById(id);

        original.setId(clase.getId());
        original.setProfesor(clase.getProfesor());
        original.setHorario(clase.getHorario());
        original.setSalon(clase.getSalon());
        original.setCupoMaximo(clase.getCupoMaximo());
        original.setSemestre(clase.getSemestre());
        original.setEstudiantes(clase.getEstudiantes());
        original.setAsignatura(clase.getAsignatura());

        return claseRepository.save(original);
    }

    public void deleteClass(Long id){
        findClaseById(id);
        estudianteRepository.delete(id);
    }

    public Clase assignEstudianteToClass(Long estudianteId, Long claseId){
        Estudiante estudiante = estudianteRepository.findById(estudianteId);
        Clase clase = findClaseById(claseId);

        if (estudiante == null)
            throw new IllegalArgumentException("Estudiante no encontrado");

        clase.addEstudiante(estudiante);
        return claseRepository.save(clase);
    }

    public Clase retirarEstudianteToClass(Long estudianteId, Long claseId){
        Estudiante estudiante = estudianteRepository.findById(estudianteId);
        Clase clase = findClaseById(claseId);

        if (estudiante == null)
            throw new IllegalArgumentException("Estudiante no encontrado");

        clase.retirarEstudiante(estudiante);
        return claseRepository.save(clase);
    }

}