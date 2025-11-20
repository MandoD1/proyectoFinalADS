package services;

import model.Clase;
import model.Estudiante;
import model.Profesor;
import repository.EstudianteRepository;
import repository.ClaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProfesorRepository;

import java.util.List;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final ClaseRepository claseRepository;

    @Autowired
    public ProfesorService(ProfesorRepository profesorRepository, ClaseRepository claseRepository) {
        this.profesorRepository = profesorRepository;
        this.claseRepository = claseRepository;
    }

    public Profesor createProfesor(Profesor profesor){
        return profesorRepository.save(profesor);
    }

    public List<Profesor> findAllProfesores(){
        return profesorRepository.findAll();
    }

    public Profesor findProfesorById(Long id){
        Profesor profesor = profesorRepository.findById(id);
        if (profesor == null)
            throw new IllegalArgumentException("Profesor no encontrado");
        return profesor;
    }

    public Profesor updateProfesor(Long id, Profesor profesor){
        Profesor original = findProfesorById(id);

        original.setNombre(profesor.getNombre());
        original.setEmail(profesor.getEmail());
        original.setDepartamento(profesor.getDepartamento());
        original.setPago(profesor.getPago());
        original.setCodigo(profesor.getCodigo());
        original.setClases(profesor.getClases());

        return profesorRepository.save(original);
    }

    public void deleteProfesor(Long id){
        findProfesorById(id);
        profesorRepository.delete(id);
    }

    public Profesor assignClassToProfesor(Long profesorId, Long claseId){
        Profesor profesor = findProfesorById(profesorId);
        Clase clase = claseRepository.findById(claseId);

        if (clase == null)
            throw new IllegalArgumentException("Clase no encontrada");

        profesor.addClases(clase);
        return profesorRepository.save(profesor);
    }

    public Profesor retirarClaseOfProfesor(Long profesorId, Long claseId){
        Profesor profesor = findProfesorById(profesorId);
        Clase clase = claseRepository.findById(claseId);
        if (clase == null)
            throw new IllegalArgumentException("Clase no encontrada");

        profesor.removeClases(clase);
        return profesorRepository.save(profesor);
    }


}
