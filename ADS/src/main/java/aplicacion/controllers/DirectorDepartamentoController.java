package aplicacion.controllers;

import aplicacion.model.*;
import aplicacion.services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import aplicacion.services.DirectorDepartamentoService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/directordepartamento")
public class DirectorDepartamentoController extends ProfesorController<DirectorDepartamento> {

    private final DirectorDepartamentoService directorDepartamentoService;
    private final ClaseService claseService;

    @Autowired
    public DirectorDepartamentoController(DirectorDepartamentoService directorDepartamentoService, DirectorDepartamentoService directorDepartamentoService1, ClaseService claseService) {
        super(directorDepartamentoService);
        this.directorDepartamentoService = directorDepartamentoService;
        this.claseService = claseService;
    }

    @GetMapping("/erprofesor")
    public void getProfesor( @RequestParam Long pId){directorDepartamentoService.generarInformeProfesor(pId);}

    @GetMapping("/verProfesores")
    public void generarInformeProfesores(){directorDepartamentoService.generarInformeProfesores();}

    @GetMapping("/calcularpago")
    public double calcularpago(@RequestParam Long pId, @RequestParam int horasDictadas, @RequestParam int minHoras, @RequestParam int maxHoras) {
        return directorDepartamentoService.calcularpago(pId, horasDictadas, minHoras, maxHoras);
    }

    @GetMapping("/modificarcarga")
    public void modificarCarga(@RequestParam Long pId, @RequestParam Long cId,  @RequestParam boolean accion ){
        Clase clase = claseService.findClaseById(cId);
        directorDepartamentoService.modificarCargaProfesor(pId, clase, accion);
    }

}
