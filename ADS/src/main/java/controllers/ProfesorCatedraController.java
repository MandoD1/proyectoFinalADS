package controllers;

import model.ProfesorCatedra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ProfesorCatedraService;

@RestController
@RequestMapping("/api/profesorcatedra")
public class ProfesorCatedraController extends ProfesorController<ProfesorCatedra> {

    private final ProfesorCatedraService profesorCatedraService;

    @Autowired
    public ProfesorCatedraController(ProfesorCatedraService profesorCatedraService, ProfesorCatedraService profesorCatedraService1) {
        super(profesorCatedraService);
        this.profesorCatedraService = profesorCatedraService1;
    }

    @GetMapping("/{horas}/{horasmax}/{horasmin}/{pId}")
    public double calcularPago(@PathVariable int horas, @PathVariable int horasmax, @PathVariable int horasmin, @PathVariable Long pId){
        ProfesorCatedra profesorCatedra = profesorCatedraService.findById(pId);
        return profesorCatedraService.calcularPago(horas, horasmin, horasmax, profesorCatedra);
    }


}