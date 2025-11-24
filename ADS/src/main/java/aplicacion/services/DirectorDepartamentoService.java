package aplicacion.services;

import aplicacion.model.*;
import javafx.stage.FileChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aplicacion.repository.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
    private final SemestreRepository semestreRepository;
    private final ProfesorCatedraService profesorCatedraService;
    private final MultiFileProfesorRepository<Profesor>  multiFileProfesorRepository;

    @Autowired
    public DirectorDepartamentoService(DirectorDepartamentoRepository directorDepartamentoRepository, DepartamentoRepository departamentoRepository, AsignaturaRepository asignaturaRepository, ClaseService claseService, AsignaturaService asignaturaService, UsuarioRepository usuarioRepository, ProfesorPlantaRepository profesorPlantaRepository, ProfesorCatedraRepository profesorCatedraRepository, SemestreRepository semestreRepository, ProfesorCatedraService profesorCatedraService, MultiFileProfesorRepository<Profesor> multiFileProfesorRepository) {
        super(directorDepartamentoRepository);
        this.departamentoRepository = departamentoRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.claseService = claseService;
        this.asignaturaService = asignaturaService;
        this.usuarioRepository = usuarioRepository;
        this.profesorPlantaRepository = profesorPlantaRepository;
        this.profesorCatedraRepository = profesorCatedraRepository;
        this.semestreRepository = semestreRepository;
        this.profesorCatedraService = profesorCatedraService;
        this.multiFileProfesorRepository = multiFileProfesorRepository;
    }

    @Override
    protected void applyUpdates(DirectorDepartamento original, DirectorDepartamento updated) {
        original.setNombre(updated.getNombre());
        original.setEmail(updated.getEmail());
        original.setDepartamento(updated.getDepartamento());
        original.setPago(updated.getPago());
        original.setId(updated.getId());
        original.setClases(updated.getClases());
        original.setDepartamento(updated.getDepartamento());
    }

    public Profesor consultarProfesor(Long id) {
        return findById(id);
    }

    public int verHorasProfesor(Long pId){
        Profesor profesor = multiFileProfesorRepository.findById(pId);
        return profesor.getTotalHoras();
    }

    public List<ProfesorPlanta> mostrarProfesoresPlanta(){
        List<ProfesorPlanta> profesoresPlanta = profesorPlantaRepository.loadAllPlanta();
        List<ProfesorPlanta> profesoresPlantaDepartamento = new ArrayList<>();
        Usuario user = usuarioRepository.loadUsuario();
        String tipoUsuario = user.getTipoUsuario();
        Departamento departamentoActual = new Departamento();
        DirectorDepartamento directorDepartamentoActual = new DirectorDepartamento();
        Long idUsuario = usuarioRepository.loadUsuario().getId();

        if(tipoUsuario.equals("DirectorDepartamento")){
            directorDepartamentoActual = (DirectorDepartamento) findById(idUsuario);
            departamentoActual = directorDepartamentoActual.getDepartamento();
            for(ProfesorPlanta profesorPlanta : profesoresPlanta){
                Departamento departamentoProfesor = profesorPlanta.getDepartamento();
                if(departamentoProfesor == departamentoActual){
                    profesoresPlantaDepartamento.add(profesorPlanta);
                }
            }
        }

        return profesoresPlantaDepartamento;
    }

    public List<ProfesorCatedra> mostrarProfesoresCatedra(){
        List<ProfesorCatedra> profesoresCatedra = profesorCatedraRepository.loadAllCatedra();
        List<ProfesorCatedra> profesoresCatedraDepartamento = new ArrayList<>();
        Usuario user = usuarioRepository.loadUsuario();
        String tipoUsuario = user.getTipoUsuario();
        Departamento departamentoActual = new Departamento();
        DirectorDepartamento directorDepartamentoActual = new DirectorDepartamento();
        Long idUsuario = usuarioRepository.loadUsuario().getId();

        if(tipoUsuario.equals("DirectorDepartamento")){
            directorDepartamentoActual = (DirectorDepartamento) findById(idUsuario);
            departamentoActual = directorDepartamentoActual.getDepartamento();
            for(ProfesorCatedra profesorCatedra : profesoresCatedra){
                Departamento departamentoProfesor = profesorCatedra.getDepartamento();
                if(departamentoProfesor == departamentoActual){
                    profesoresCatedraDepartamento.add(profesorCatedra);
                }
            }
        }

        return profesoresCatedraDepartamento;
    }

    public void generarInformeProfesores() {

        List<ProfesorPlanta> planta = mostrarProfesoresPlanta();
        List<ProfesorCatedra> catedra = mostrarProfesoresCatedra();

        StringBuilder sb = new StringBuilder();

        sb.append("INFORME DE PROFESORES\n");
        sb.append("----------------------\n\n");

        sb.append("PROFESORES DE CÁTEDRA\n");
        sb.append("----------------------\n");
        if (catedra.isEmpty()) {
            sb.append("  * No hay profesores de cátedra registrados\n");
        } else {
            for (ProfesorCatedra p : catedra) {
                sb.append("  - ID: ").append(p.getId()).append("\n");
            }
        }

        sb.append("\nPROFESORES DE PLANTA\n");
        sb.append("----------------------\n");
        if (planta.isEmpty()) {
            sb.append("  * No hay profesores de planta registrados\n");
        } else {
            for (ProfesorPlanta p : planta) {
                sb.append("  - ID: ").append(p.getId()).append("\n");
            }
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar informe de profesores");
        fileChooser.setInitialFileName("informe_profesores.txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));

        File archivo = fileChooser.showSaveDialog(null);

        if (archivo != null) {
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(sb.toString());
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar el archivo", e);
            }
        }
    }

    public void generarInformeProfesor(Long pId){
        Profesor profesor = multiFileProfesorRepository.findById(pId);;
        if(profesor == null)
            throw new RuntimeException("Profesor no encontrado");

        List<Asignatura> asignaturas = asignaturaRepository.findAll();
        List<Asignatura> asignaturasProfesor = new ArrayList<>();

        for(Asignatura asignatura : asignaturas){
            boolean profesorEnAsignatura = false;

            for(Clase clase : asignatura.getClases()){
                Profesor profesorAux = clase.getProfesor();
                if(profesorAux != null && profesorAux.getId().equals(pId)){
                    profesorEnAsignatura = true;
                    break;
                }
            }

            if(profesorEnAsignatura){
                asignaturasProfesor.add(asignatura);
            }
        }

        int horasTotales = profesor.getTotalHoras();

        StringBuilder informe = new StringBuilder();
        informe.append("INFORME DEL PROFESOR\n");
        informe.append("--------------------\n");
        informe.append("Nombre: ").append(profesor.getNombre()).append("\n");
        informe.append("ID: ").append(profesor.getId()).append("\n\n");

        informe.append("Asignaturas asignadas:\n");
        if(asignaturasProfesor.isEmpty()){
            informe.append(" - Ninguna asignatura asignada\n");
        } else {
            for(Asignatura a : asignaturasProfesor){
                informe.append(" - ").append(a.getNombre()).append("\n");
            }
        }

        informe.append("\nTotal horas: ").append(horasTotales).append("\n");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar informe de profesor");
        fileChooser.setInitialFileName("informe_profesor_" + pId + ".txt");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo de texto", "*.txt"));

        File archivo = fileChooser.showSaveDialog(null);

        if(archivo != null){
            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(informe.toString());
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar el informe", e);
            }
        }
    }

    public double calcularpago(Long pid, int horasDictadas, int minHoras, int maxHoras){
        ProfesorCatedra profesorCatedra = profesorCatedraRepository.findById(pid);
        return profesorCatedraService.calcularPago(horasDictadas, minHoras, maxHoras, profesorCatedra);
    }




}
