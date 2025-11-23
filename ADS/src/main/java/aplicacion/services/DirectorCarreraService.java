package aplicacion.services;

import aplicacion.model.*;
import aplicacion.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DirectorCarreraService extends ProfesorService<DirectorCarrera> {

    private final DepartamentoRepository departamentoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final ClaseService claseService;
    private final AsignaturaService asignaturaService;
    private final UsuarioRepository usuarioRepository;
    private final ProfesorPlantaRepository profesorPlantaRepository;
    private final ProfesorCatedraRepository profesorCatedraRepository;
    private final SemestreRepository semestreRepository;
    private final EstudianteService estudianteService;


    @Autowired
    public DirectorCarreraService(DirectorCarreraRepository directorCarreraRepository, DepartamentoRepository departamentoRepository, AsignaturaRepository asignaturaRepository, ClaseService claseService, AsignaturaService asignaturaService, UsuarioRepository usuarioRepository, ProfesorPlantaRepository profesorPlantaRepository, ProfesorCatedraRepository profesorCatedraRepository, SemestreRepository semestreRepository, EstudianteService estudianteService) {
        super(directorCarreraRepository);
        this.departamentoRepository = departamentoRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.claseService = claseService;
        this.asignaturaService = asignaturaService;
        this.usuarioRepository = usuarioRepository;
        this.profesorPlantaRepository = profesorPlantaRepository;
        this.profesorCatedraRepository = profesorCatedraRepository;
        this.semestreRepository = semestreRepository;
        this.estudianteService = estudianteService;
    }

    @Override
    protected void applyUpdates(DirectorCarrera original, DirectorCarrera updated) {
        original.setNombre(updated.getNombre());
        original.setEmail(updated.getEmail());
        original.setDepartamento(updated.getDepartamento());
        original.setPago(updated.getPago());
        original.setId(updated.getId());
        original.setClases(updated.getClases());
        original.setCarrera(updated.getCarrera());
        original.setHorasDeClase();
    }

    public Profesor consultarProfesor(Long id) {
        return findById(id);
    }

    public boolean esMayorA3MesesDate(Date fechaDada, Date fechaActual) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaDada);
        cal.add(Calendar.MONTH, 3);

        Date fechaMas3Meses = cal.getTime();

        return fechaMas3Meses.before(fechaActual);
    }

    public Departamento crearAsignatura(Long SemestreId, String nombre, Long departamentoId, List<Clase> clases, List<Asignatura> Corequisitos, boolean requisitoIngles, List<Asignatura> Prerequisitos, int creditos) {
        Asignatura asignatura = new Asignatura();
        Semestre semestre = semestreRepository.findById(SemestreId);
        Departamento departamento = departamentoRepository.findById(departamentoId);
        Usuario usuario = usuarioRepository.loadUsuario();

        if (departamento == null) {
            throw  new RuntimeException("Departamento no existe");
        }

        if(usuario.getTipoUsuario().equals("DirectorCarrera")){
            Long codigo1 = usuario.getId();
            DirectorCarrera directorCarrera = findById(codigo1);
            if(directorCarrera != null){
                Departamento departamento1 = departamentoRepository.findById(directorCarrera.getDepartamento().getId());
                Date fechaUsuario = usuario.getFechaActual();
                Date fechaSemestre = semestre.getFechaInicio();
                if(esMayorA3MesesDate(fechaSemestre, fechaUsuario)){
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
                    } else {throw new RuntimeException("no eres directror de carrera que sea del departamento al que quieres agregar una asignatura");}
                } else {throw new RuntimeException("ya paso el limite de los tres semestres para agregar asignaturas");}
            } else { throw new RuntimeException("director de carrera no encontrado"); }
        } else { throw new RuntimeException("No eres director de carrera"); }

        return departamentoRepository.save(departamento);
    }

    public void crearClase(Long id, Long idProfesor, List<Date> horario, int horas, String salon, int cupoMaximo, String semestre, Long idAsignatura) {
        Profesor profesor = findById(idProfesor);
        Asignatura asgnatura = asignaturaRepository.findById(idAsignatura);
        Clase clase = new Clase();
        clase.setId(id);
        clase.setProfesor(profesor);
        clase.setHorario(horario);
        clase.setHoras(horas);
        clase.setSalon(salon);
        clase.setCupoMaximo(cupoMaximo);
        clase.setSemestre(semestre);
        clase.setCupoActual(0);
        clase.setAsignatura(asgnatura);
        claseService.createClase(clase);

    }
    public void modificarClase(int horas, Long cid, Long nuevoId, Long profesorId, List<Date> horario, String salon, int cupoMaximo, int cupoActual, String semestre, Long AsignaturaId, List<Estudiante> estudiantes ) {
        Clase clase = new Clase();
        Clase claseOriginal = claseService.findClaseById(cid);
        boolean laClaseExiste = false;

        Profesor profesor = findById(profesorId);
        Asignatura asignatura = asignaturaRepository.findById(AsignaturaId);

        Usuario usuario = usuarioRepository.loadUsuario();

        if(usuario.getTipoUsuario().equals("DirectorCarrera")){
            Long codigo1 = usuario.getId();
            DirectorCarrera directorCarrera = findById(codigo1);
            if(directorCarrera != null){
                List<Asignatura> asignaturasDeDepartamentoDeUsuario = directorCarrera.getDepartamento().getAsignaturas();
                for (Asignatura asignatura1 : asignaturasDeDepartamentoDeUsuario){
                    List<Clase> clasesAsignaturaDeDepartamentoDeUsuario = asignatura1.getClases();
                    for (Clase clase1 : clasesAsignaturaDeDepartamentoDeUsuario){
                        Long idClase1 = clase1.getId();
                        if(idClase1 == cid){
                            laClaseExiste = true;
                        }
                    }
                }
            } else {throw new RuntimeException("director de carrera no encontrado");}
        } else { throw new RuntimeException("No eres director de carrera"); }

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
            clase.setHoras(horas);
            claseService.updateClase(cid, clase);
        }
    }

    public void eliminarAsignatura(Long asignaturaId) {
        Asignatura asignatura = asignaturaRepository.findById(asignaturaId);
        Departamento departamento =  asignatura.getDepartamento();
        Usuario usuario = usuarioRepository.loadUsuario();

        if (departamento == null) {
            throw  new RuntimeException("Departamento no existe");
        }

        if(usuario.getTipoUsuario().equals("DirectorCarrera")){
            Long codigo1 = usuario.getId();
            DirectorCarrera directorCarrera = findById(codigo1);
            if(directorCarrera != null){
                Departamento departamento1 = departamentoRepository.findById(directorCarrera.getDepartamento().getId());
                if(departamento1 == departamento){
                    List<Asignatura> asignaturasDeDepartamento = departamento1.getAsignaturas();
                    for(Asignatura asignatura1 : asignaturasDeDepartamento){
                        asignatura1.removeCorequisitos(asignatura);
                        asignatura1.removePrerequisitos(asignatura);
                    }
                    departamento.removeAsignaturas(asignatura);
                } else {throw new RuntimeException("no eres directror de carrera del departamento al que quieres agregar una asignatura");}
            } else { throw new RuntimeException("director de carrera no encontrado"); }
        } else { throw new RuntimeException("No eres director de carrera"); }


        departamentoRepository.save(departamento);
    }

    public void eliminarClase(Long cid ) {
        boolean laClaseExiste = false;
        Usuario usuario = usuarioRepository.loadUsuario();

        if(usuario.getTipoUsuario().equals("DirectorCarrera")){
            Long codigo1 = usuario.getId();
            DirectorCarrera directorCarrera = findById(codigo1);
            if(directorCarrera != null){
                List<Asignatura> asignaturasDeDepartamentoDeUsuario = directorCarrera.getDepartamento().getAsignaturas();
                for (Asignatura asignatura1 : asignaturasDeDepartamentoDeUsuario){
                    List<Clase> clasesAsignaturaDeDepartamentoDeUsuario = asignatura1.getClases();
                    for (Clase clase1 : clasesAsignaturaDeDepartamentoDeUsuario){
                        Long idClase1 = clase1.getId();
                        if(idClase1 == cid){
                            laClaseExiste = true;
                        }
                    }
                }
            } else {throw new RuntimeException("director de carrera no encontrado");}
        } else { throw new RuntimeException("No eres director de carrera"); }

        if(laClaseExiste){
            List<Estudiante> estudiantesRetirados = new ArrayList<>();
            for(Estudiante estudiante : estudianteService.findAllEstudiante()){
                List<Clase> clasesEstudiante = estudiante.getClases();
                for (Clase clase1 : clasesEstudiante){
                    Long idClase1 = clase1.getId();
                    if(idClase1 == cid){
                        estudiantesRetirados.add(estudiante);
                        estudiante.deleteClase(clase1);
                    }
                }
            }
            Clase clase = claseService.findClaseById(cid);

            if(clase.conProfesor()){ throw new RuntimeException("Clase con profesor no se puede eliminar");}
            if(!clase.cupomenoral40()){ throw new RuntimeException("Clase con cupo mayor al 50% no se puede eliminar");}
            generarReporteEstudiantesRetirados(estudiantesRetirados, cid);
            claseService.deleteClass(cid);
        }
    }

    private void generarReporteEstudiantesRetirados(List<Estudiante> lista, Long claseId) {
        StringBuilder sb = new StringBuilder();
        sb.append("Estudiantes retirados de la clase ID: ").append(claseId).append("\n\n");

        for (Estudiante e : lista) {
            sb.append("ID: ").append(e.getId()).append("\n");
            sb.append("Nombre: ").append(e.getNombre()).append("\n");
            sb.append("Email: ").append(e.getEmail()).append("\n");
            sb.append("-----------------------------------------\n");
        }

        javafx.stage.FileChooser fileChooser = new javafx.stage.FileChooser();
        fileChooser.setTitle("Guardar reporte de estudiantes retirados");
        fileChooser.getExtensionFilters().add(new javafx.stage.FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));

        fileChooser.setInitialFileName("estudiantes_retirados_clase_" + claseId + ".txt");

        java.io.File archivo = fileChooser.showSaveDialog(null);

        if (archivo != null) {
            try (java.io.FileWriter writer = new java.io.FileWriter(archivo)) {
                writer.write(sb.toString());
            } catch (Exception ex) {
                throw new RuntimeException("Error al guardar el archivo", ex);
            }
        }
    }




}
