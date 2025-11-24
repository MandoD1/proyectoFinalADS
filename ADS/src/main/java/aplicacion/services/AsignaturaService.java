package aplicacion.services;

import aplicacion.model.Clase;
import aplicacion.model.Asignatura;
import aplicacion.model.Estudiante;
import aplicacion.repository.ClaseRepository;
import aplicacion.repository.AsignaturaRepository;
import aplicacion.repository.EstudianteRepository;
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
        original.setId(asignatura.getId());
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

    public Asignatura assingAsignaturaNotaYEstado(Long asignaturaid, double calificacion){
        Asignatura asignatura = findAsignaturaById(asignaturaid);
        int estado=0;
        asignatura.setCalificacion(calificacion);

        if (asignatura == null)
            throw new IllegalArgumentException("asignatura no encontrada");

        if(asignatura.getCalificacion()<3.0){
                estado=1;
            }

        if(asignatura.getCalificacion()>3.0){
                estado=2;
            }

        if(!asignatura.getEstado().equals("CURSANDO") && asignatura.getCalificacion()==0.0){
                estado=3;
            }

        switch (estado){
                case 1: asignatura.setEstado(estadoClaseEnum.REPROBADA);
                case 2: asignatura.setEstado(estadoClaseEnum.APROBADA);
                case 3: asignatura.setEstado(estadoClaseEnum.NO_VISTA);
            }

        return  asignaturaRepository.save(asignatura);
    }

    public Asignatura eliminarPrerequisito(Long asignaturaId, Long prerequisitoId) {
        Asignatura asignatura = findAsignaturaById(asignaturaId);
        Asignatura prerequisito = findAsignaturaById(prerequisitoId);

        try {
            asignatura.removePrerequisitos(prerequisito);
        } catch (Exception e) {
            // No hacer nada
        }

        return asignaturaRepository.save(asignatura);
    }



    public Asignatura eliminarCorequisito(Long asignaturaId, Long corequisitoId) {
        Asignatura asignatura = findAsignaturaById(asignaturaId);
        Asignatura corequisito = findAsignaturaById(corequisitoId);

        try {
            asignatura.removeCorequisitos(corequisito);
        } catch (Exception e) {
            // No hacer nada
        }

        return asignaturaRepository.save(asignatura);
    }


    public Asignatura eliminarClase(Long asignaturaId, Long claseId) {
        Asignatura asignatura = findAsignaturaById(asignaturaId);
        Clase clase = claseRepository.findById(claseId);

        try {
            asignatura.removeClases(clase);
        } catch (Exception e) {
            // No hacer nada si falla
        }

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
