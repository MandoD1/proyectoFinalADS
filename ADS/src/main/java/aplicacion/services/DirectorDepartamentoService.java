package aplicacion.services;

import aplicacion.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aplicacion.repository.*;

import java.util.Date;
import java.util.List;

@Service
public class DirectorDepartamentoService extends ProfesorService<DirectorDepartamento> {

    private final DepartamentoRepository departamentoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final ClaseService claseService;
    private final AsignaturaService asignaturaService;
    private final UsuarioRepository usuarioRepository;
    private final ProfesorPlantaRepository profesorPlantaRepository;
    private final ProfesorCatedraRepository profesorCatedraRepository;

    @Autowired
    public DirectorDepartamentoService(DirectorDepartamentoRepository directorDepartamentoRepository, DepartamentoRepository departamentoRepository, AsignaturaRepository asignaturaRepository, ClaseService claseService, AsignaturaService asignaturaService, UsuarioRepository usuarioRepository, ProfesorPlantaRepository profesorPlantaRepository, ProfesorCatedraRepository profesorCatedraRepository) {
        super(directorDepartamentoRepository);
        this.departamentoRepository = departamentoRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.claseService = claseService;
        this.asignaturaService = asignaturaService;
        this.usuarioRepository = usuarioRepository;
        this.profesorPlantaRepository = profesorPlantaRepository;
        this.profesorCatedraRepository = profesorCatedraRepository;
    }

    @Override
    protected void applyUpdates(DirectorDepartamento original, DirectorDepartamento updated) {
        original.setNombre(updated.getNombre());
        original.setEmail(updated.getEmail());
        original.setDepartamento(updated.getDepartamento());
        original.setPago(updated.getPago());
        original.setCodigo(updated.getCodigo());
        original.setClases(updated.getClases());
        original.setDepartamento(updated.getDepartamento());
    }

    public Profesor consultarProfesor(Long id) {
        return findById(id);
    }

    public Departamento crearAsignatura(String nombre, Long departamentoId, List<Clase> clases, List<Asignatura> Corequisitos, boolean requisitoIngles, List<Asignatura> Prerequisitos, int creditos) {
        Asignatura asignatura = new Asignatura();
        Departamento departamento = departamentoRepository.findById(departamentoId);
        Usuario usuario = usuarioRepository.loadUsuario();
        
        if (departamento == null) {
            throw  new RuntimeException("Departamento no existe");
        }
        
        if(usuario.getTipoUsuario().equals("DirectorDepartamento")){
            Long codigo1 = usuario.getCodigo();
            DirectorDepartamento directorDepartamento = findById(codigo1);
            if(directorDepartamento != null){
                Departamento departamento1 = departamentoRepository.findById(directorDepartamento.getDepartamento().getCodigo());
                if(departamento1 == departamento){
                    asignatura.setNombre(nombre);
                    asignatura.setDepartamento(departamento);
                    asignatura.setCorequisitos(Corequisitos);
                    asignatura.setRequisitoingles(requisitoIngles);
                    asignatura.setPrerequisitos(Prerequisitos);
                    asignatura.setClases(clases);
                    asignatura.setCreditos(creditos);
                    departamento.addAsignaturas(asignatura);
                    asignaturaService.createAsignatura(asignatura);
                } else {throw new RuntimeException("no eres directror de departamento del departamento al que quieres agregar una asignatura");}
            } else { throw new RuntimeException("director de departamento no encontrado"); }
        } else { throw new RuntimeException("No eres director de departamento"); }

        return departamentoRepository.save(departamento);
    }

    public void modificarClase(Long cid, Long nuevoId, Long profesorId, List<Date> horario, String salon, int cupoMaximo, int cupoActual, String semestre, Long AsignaturaId, List<Estudiante> estudiantes ) {
        Clase clase = new Clase();
        Clase claseOriginal = claseService.findClaseById(cid);
        boolean laClaseExiste = false;

        Profesor profesor = findById(profesorId);
        Asignatura asignatura = asignaturaRepository.findById(AsignaturaId);

        Usuario usuario = usuarioRepository.loadUsuario();

        if(usuario.getTipoUsuario().equals("DirectorDepartamento")){
            Long codigo1 = usuario.getCodigo();
            DirectorDepartamento directorDepartamento = findById(codigo1);
            if(directorDepartamento != null){
                List<Asignatura> asignaturasDeDepartamentoDeUsuario = directorDepartamento.getDepartamento().getAsignaturas();
                for (Asignatura asignatura1 : asignaturasDeDepartamentoDeUsuario){
                    List<Clase> clasesAsignaturaDeDepartamentoDeUsuario = asignatura1.getClases();
                    for (Clase clase1 : clasesAsignaturaDeDepartamentoDeUsuario){
                        Long idClase1 = clase1.getId();
                        if(idClase1 == cid){
                            laClaseExiste = true;
                        }
                    }
                }
            } else {throw new RuntimeException("director de departamento no encontrado");}
        } else { throw new RuntimeException("No eres director de departamento"); }

        if(laClaseExiste){
            clase.setId(nuevoId);
            clase.setAsignatura(asignatura);
            clase.setProfesor(profesor);
            clase.setSalon(salon);
            clase.setCupoMaximo(cupoMaximo);
            clase.setCupoActual(cupoActual);
            clase.setSemestre(semestre);
            clase.setHorario(horario);
            clase.setEstudiantes(estudiantes);
            claseService.updateClase(cid, clase);
        }
    }

    public void eliminarAsignatura(Long did, Long asignaturaId) {
        Departamento departamento = departamentoRepository.findById(did);
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId);

        Usuario usuario = usuarioRepository.loadUsuario();

        if (departamento == null) {
            throw  new RuntimeException("Departamento no existe");
        }

        if(usuario.getTipoUsuario().equals("DirectorDepartamento")){
            Long codigo1 = usuario.getCodigo();
            DirectorDepartamento directorDepartamento = findById(codigo1);
            if(directorDepartamento != null){
                Departamento departamento1 = departamentoRepository.findById(directorDepartamento.getDepartamento().getCodigo());
                if(departamento1 == departamento){
                    departamento.removeAsignaturas(asignatura);
                } else {throw new RuntimeException("no eres directror de departamento del departamento al que quieres agregar una asignatura");}
            } else { throw new RuntimeException("director de departamento no encontrado"); }
        } else { throw new RuntimeException("No eres director de departamento"); }


        departamentoRepository.save(departamento);
    }

    public void eliminarClase(Long cid ) {
        boolean laClaseExiste = false;

        Usuario usuario = usuarioRepository.loadUsuario();

        if(usuario.getTipoUsuario().equals("DirectorDepartamento")){
            Long codigo1 = usuario.getCodigo();
            DirectorDepartamento directorDepartamento = findById(codigo1);
            if(directorDepartamento != null){
                List<Asignatura> asignaturasDeDepartamentoDeUsuario = directorDepartamento.getDepartamento().getAsignaturas();
                for (Asignatura asignatura1 : asignaturasDeDepartamentoDeUsuario){
                    List<Clase> clasesAsignaturaDeDepartamentoDeUsuario = asignatura1.getClases();
                    for (Clase clase1 : clasesAsignaturaDeDepartamentoDeUsuario){
                        Long idClase1 = clase1.getId();
                        if(idClase1 == cid){
                            laClaseExiste = true;
                        }
                    }
                }
            } else {throw new RuntimeException("director de departamento no encontrado");}
        } else { throw new RuntimeException("No eres director de departamento"); }

        if(laClaseExiste){
            claseService.deleteClass(cid);
        }
    }

    public List<ProfesorPlanta> mostrarProfesoresPlanta(){
        return profesorPlantaRepository.loadAllPlanta();
    }

    public List<ProfesorCatedra> mostrarProfesoresCatedra(){
        return profesorCatedraRepository.loadAllCatedra();
    }



}
