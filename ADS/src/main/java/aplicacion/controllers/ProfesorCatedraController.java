package aplicacion.controllers;

import aplicacion.model.ProfesorCatedra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import aplicacion.services.ProfesorCatedraService;

@RestController
@RequestMapping("/api/profesorcatedra")
public class ProfesorCatedraController extends ProfesorController<ProfesorCatedra> {

    private final ProfesorCatedraService profesorCatedraService;

    @Autowired
    public ProfesorCatedraController(ProfesorCatedraService profesorCatedraService, ProfesorCatedraService profesorCatedraService1) {
        super(profesorCatedraService);
        this.profesorCatedraService = profesorCatedraService1;
    }

    @GetMapping("/calcularpago")
    public double calcularPago(@RequestParam int horas, @RequestParam int horasmax, @RequestParam int horasmin, @RequestParam Long pId){
        ProfesorCatedra profesorCatedra = profesorCatedraService.findById(pId);
        return profesorCatedraService.calcularPago(horas, horasmin, horasmax, profesorCatedra);
    }


}