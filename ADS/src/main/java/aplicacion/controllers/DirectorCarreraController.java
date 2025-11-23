package aplicacion.controllers;

import aplicacion.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import aplicacion.services.ClaseService;
import aplicacion.services.DirectorCarreraService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/directorcarrera")
public class DirectorCarreraController extends ProfesorController<DirectorCarrera> {

    private final DirectorCarreraService directorCarreraService;
    private final ClaseService claseService;

    @Autowired
    public DirectorCarreraController(DirectorCarreraService directorCarreraService, DirectorCarreraService directorCarreraService1, ClaseService claseService) {
        super(directorCarreraService);
        this.directorCarreraService = directorCarreraService1;
        this.claseService = claseService;
    }

    @GetMapping("/verprofesor")
    public Profesor getProfesor(@RequestParam Long pId){
        return directorCarreraService.consultarProfesor(pId);
    }

    @GetMapping("/crearClase")
    public void crearClase(@RequestParam int horas, @RequestParam Long cId, @RequestParam Long profesorId, @RequestParam List<Date> horario,@RequestParam String salon,@RequestParam int cupoMaximo, @RequestParam String semestre,@RequestParam Long AsignaturaId){
        directorCarreraService.crearClase( cId, profesorId,  horario,  horas,  salon,  cupoMaximo,  semestre,  AsignaturaId);
    }
    @GetMapping("/crearasignatura")
    public Departamento crearAsignatura(@RequestParam Long semestreId, @RequestParam Long dId, @RequestParam String nombre, @RequestParam List<Clase> clases, @RequestParam List<Asignatura> Corequisitos, @RequestParam boolean requisitoIngles, @RequestParam List<Asignatura> Prerequisitos, @RequestParam int creditos){
        return directorCarreraService.crearAsignatura(semestreId, nombre,  dId,  clases,  Corequisitos,  requisitoIngles,  Prerequisitos, creditos);
    }

    @GetMapping("/modificarclase")
    public void modificarClase(@RequestParam int horas, @RequestParam Long cId,@RequestParam Long nuevoId,@RequestParam Long profesorId, @RequestParam List<Date> horario,@RequestParam String salon,@RequestParam int cupoMaximo, @RequestParam int cupoActual, @RequestParam String semestre,@RequestParam Long AsignaturaId, @RequestParam List<Estudiante> estudiantes ){
        directorCarreraService.modificarClase(horas, cId, nuevoId, profesorId, horario, salon, cupoMaximo, cupoActual, semestre, AsignaturaId, estudiantes);
    }

    @GetMapping("/eliminarclase")
    public void eliminarClase(@RequestParam Long cId){
        directorCarreraService.eliminarClase(cId);
    }

    @GetMapping("/eliminarasignatura") //did es de departamento id si algo
    public void eliminarAsignatura(@RequestParam Long aId){
        directorCarreraService.eliminarAsignatura(aId);
    }

}
