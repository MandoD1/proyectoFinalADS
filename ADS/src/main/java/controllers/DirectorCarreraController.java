package controllers;

import model.Clase;
import model.DirectorCarrera;
import model.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.ClaseService;
import services.DirectorCarreraService;

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

}
