package services;

import model.Clase;
import model.Asignatura;
import repository.ClaseRepository;
import repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaService {

    private final ClaseRepository claseRepository;
    private final AsignaturaRepository asignaturaRepository;

    @Autowired
    public AsignaturaService( ClaseRepository claseRepository, AsignaturaRepository asignaturaRepository) {
        this.claseRepository = claseRepository;
        this.asignaturaRepository = asignaturaRepository;
    }

    public Asignatura createAsignatura(Asignatura asignatura){
        return asignaturaRepository.save(asignatura);
    }

    public List<Asignatura> findAllAsignatura(){
        return asignaturaRepository.findAll();
    }

    public Asignatura findAsignaturaById(Long id){
        Asignatura asignatura = asignaturaRepository.findById(id);
        if (asignatura == null)
            throw new IllegalArgumentException("Asignatura no encontrada");
        return asignatura;
    }

    public Asignatura updateAsignatura(Long id, Asignatura asignatura){
        Asignatura original = findAsignaturaById(id);
        original.setDepartamento(asignatura.getDepartamento());
        original.setCodigo(asignatura.getCodigo());
        original.setNombre(asignatura.getNombre());
        original.setCreditos(asignatura.getCreditos());
        original.setRequisitoingles(asignatura.isRequisitoingles());
        original.setPrerequisitos(asignatura.getPrerequisitos());
        original.setCorequisitos(asignatura.getCorequisitos());
        original.setClases(asignatura.getClases());

        return asignaturaRepository.save(original);
    }

    public void deleteAsignatura(Long id){
        findAsignaturaById(id);
        asignaturaRepository.delete(id);
    }

    public Asignatura assingPrerequisito(Long asignaturaid, Long prerequisitoid){
        Asignatura asignatura = findAsignaturaById(asignaturaid);
        Asignatura prerequisito = findAsignaturaById(prerequisitoid);

        if (asignatura == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        if (prerequisito == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        asignatura.addPrerequisitos(prerequisito);
        return asignaturaRepository.save(asignatura);
    }

    public Asignatura assingCorequisito(Long asignaturaid, Long corequisitoid){
        Asignatura asignatura = findAsignaturaById(asignaturaid);
        Asignatura corequisito = findAsignaturaById(corequisitoid);

        if (asignatura == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        if (corequisito == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        asignatura.addCorequisitos(corequisito);
        return asignaturaRepository.save(asignatura);
    }

    public Asignatura assingClase(Long asignaturaid, Long classeid){
        Asignatura asignatura = findAsignaturaById(asignaturaid);
        Clase clase = claseRepository.findById(classeid);

        if (asignatura == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        if (clase == null)
            throw new IllegalArgumentException("clase no encontrada");

        asignatura.addClases(clase);
        return asignaturaRepository.save(asignatura);
    }

    public Asignatura eliminarPrerequisito(Long asignaturaid, Long prerequisitoid){
        Asignatura asignatura = findAsignaturaById(asignaturaid);
        Asignatura prerequisito = findAsignaturaById(prerequisitoid);

        if (asignatura == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        if (prerequisito == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        asignatura.removePrerequisitos(prerequisito);
        return asignaturaRepository.save(asignatura);
    }


    public Asignatura eliminarCorequisito(Long asignaturaid, Long corequisitoid){
        Asignatura asignatura = findAsignaturaById(asignaturaid);
        Asignatura corequisito = findAsignaturaById(corequisitoid);

        if (asignatura == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        if (corequisito == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        asignatura.removeCorequisitos(corequisito);
        return asignaturaRepository.save(asignatura);
    }

    public Asignatura eliminarClase(Long asignaturaid, Long classeid){
        Asignatura asignatura = findAsignaturaById(asignaturaid);
        Clase clase = claseRepository.findById(classeid);

        if (asignatura == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        if (clase == null)
            throw new IllegalArgumentException("clase no encontrada");

        asignatura.removeClases(clase);
        return asignaturaRepository.save(asignatura);
    }

    public List<Asignatura> seePrerequisitos(Long asignaturaid){
        Asignatura asignatura = findAsignaturaById(asignaturaid);
        return asignatura.getPrerequisitos();
    }

    public List<Asignatura> seeCorequisitos(Long asignaturaid){
        Asignatura asignatura = findAsignaturaById(asignaturaid);
        return asignatura.getCorequisitos();
    }

}
