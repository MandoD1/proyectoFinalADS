package aplicacion.controllers;

import aplicacion.model.ProfesorPlanta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import aplicacion.services.ProfesorPlantaService;

@RestController
@RequestMapping("/api/profesorplanta")
public class ProfesorPlantaController extends ProfesorController<ProfesorPlanta> {

    private final ProfesorPlantaService profesorPlantaService;

    @Autowired
    public ProfesorPlantaController(ProfesorPlantaService profesorPlantaService, ProfesorPlantaService profesorPlantaService1) {
        super(profesorPlantaService);
        this.profesorPlantaService = profesorPlantaService1;
    }

}