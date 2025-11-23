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

    @GetMapping("/{pId}/{cId}/{accion}")
    public void modificarCarga(@PathVariable Long pId, @PathVariable Long cId,  @PathVariable boolean accion ){
        Clase clase = claseService.findClaseById(cId);
        directorCarreraService.modificarCargaProfesor(pId, clase, accion);
    }

    @GetMapping("/{pId}/verprofesor")
    public Profesor getProfesor(@PathVariable Long pId){
        return directorCarreraService.consultarProfesor(pId);
    }

    @GetMapping("/crearasignatura/{dId}/")
    public Departamento crearAsignatura(@RequestBody Long semestreId, @PathVariable Long dId, @RequestBody String nombre, @RequestBody List<Clase> clases, @RequestBody List<Asignatura> Corequisitos, @RequestBody boolean requisitoIngles, @RequestBody List<Asignatura> Prerequisitos, @RequestBody int creditos){
        return directorCarreraService.crearAsignatura(semestreId, nombre,  dId,  clases,  Corequisitos,  requisitoIngles,  Prerequisitos, creditos);
    }

    @GetMapping("/modificarclase/{cId}")
    public void modificarClase(int horas, Long cId, Long nuevoId, Long profesorId, List<Date> horario, String salon, int cupoMaximo, int cupoActual, String semestre, Long AsignaturaId, List<Estudiante> estudiantes ){
        directorCarreraService.modificarClase(horas, cId, nuevoId, profesorId, horario, salon, cupoMaximo, cupoActual, semestre, AsignaturaId, estudiantes);
    }

    @GetMapping("/eliminarclase/{cId}/")
    public void eliminarClase(@PathVariable Long cId){
        directorCarreraService.eliminarClase(cId);
    }

    @GetMapping("/eliminarasignatura/{aId}") //did es de departamento id
    public void eliminarAsignatura(@PathVariable Long aId){
        directorCarreraService.eliminarAsignatura(aId);
    }

}
