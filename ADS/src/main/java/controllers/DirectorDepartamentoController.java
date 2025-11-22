package controllers;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ClaseService;
import services.DirectorCarreraService;
import services.DirectorDepartamentoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/directorcarrera")
public class DirectorDepartamentoController extends ProfesorController<DirectorDepartamento> {

    private final DirectorDepartamentoService directorDepartamentoService;

    @Autowired
    public DirectorDepartamentoController(DirectorDepartamentoService directorDepartamentoService, DirectorDepartamentoService directorDepartamentoService1) {
        super(directorDepartamentoService);
        this.directorDepartamentoService = directorDepartamentoService;
    }


    @GetMapping("/{pId}/verprofesor")
    public Profesor getProfesor(@PathVariable Long pId){
        return directorDepartamentoService.consultarProfesor(pId);
    }

    @GetMapping("/crearasignatura/{dId}/")
    public Departamento crearAsignatura(@PathVariable Long dId, @RequestBody String nombre, @RequestBody List<Clase> clases, @RequestBody List<Asignatura> Corequisitos, @RequestBody boolean requisitoIngles, @RequestBody List<Asignatura> Prerequisitos, @RequestBody int creditos){
        return directorDepartamentoService.crearAsignatura( nombre,  dId,  clases,  Corequisitos,  requisitoIngles,  Prerequisitos, creditos);
    }

    @GetMapping("/modificarclase/{cId}")
    public void modificarClase(Long cId, Long nuevoId, Long profesorId, List<Date> horario, String salon, int cupoMaximo, int cupoActual, String semestre, Long AsignaturaId, List<Estudiante> estudiantes ){
        directorDepartamentoService.modificarClase(cId, nuevoId, profesorId, horario, salon, cupoMaximo, cupoActual, semestre, AsignaturaId, estudiantes);
    }

    @GetMapping("/verProfesores")
    public List<Profesor> getProfesores(){
        List<ProfesorCatedra> profesoresCatedra = directorDepartamentoService.mostrarProfesoresCatedra();
        List<ProfesorPlanta> profesoresPlanta = directorDepartamentoService.mostrarProfesoresPlanta();
        List<Profesor> profesores = new ArrayList<>();

        profesores.addAll(profesoresCatedra);
        profesores.addAll(profesoresPlanta);

        return profesores;
    }
}
