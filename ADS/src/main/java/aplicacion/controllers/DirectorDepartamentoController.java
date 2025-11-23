package aplicacion.controllers;

import aplicacion.model.*;
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

    @Autowired
    public DirectorDepartamentoController(DirectorDepartamentoService directorDepartamentoService, DirectorDepartamentoService directorDepartamentoService1) {
        super(directorDepartamentoService);
        this.directorDepartamentoService = directorDepartamentoService;
    }

    @GetMapping("/erprofesor")
    public void getProfesor( @RequestParam Long pId){directorDepartamentoService.generarInformeProfesor(pId);}

    @GetMapping("/verProfesores")
    public void generarInformeProfesores(){directorDepartamentoService.generarInformeProfesores();}

    @GetMapping("/calcularpago")
    public double calcularpago(@RequestParam Long pId, @RequestParam int horasDictadas, @RequestParam int minHoras, @RequestParam int maxHoras) {
        return directorDepartamentoService.calcularpago(pId, horasDictadas, minHoras, maxHoras);
    }

}
