package aplicacion.controllers;

import aplicacion.model.Clase;
import aplicacion.services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clase")
public class ClaseController {

    private final ClaseService claseService;

    @Autowired
    public ClaseController(ClaseService claseService) {
        this.claseService = claseService;
    }

    @PostMapping
    public Clase create(@RequestBody Clase clase){
        return claseService.createClase(clase);
    }

    @GetMapping
    public List<Clase> getAll(){
        return claseService.findAllClases();
    }

    @GetMapping("/{id}")
    public Clase getById(@PathVariable Long id){
        return claseService.findClaseById(id);
    }

    @PutMapping("/{id}")
    public Clase update(@PathVariable Long id, @RequestBody Clase clase){
        return claseService.updateClase(id, clase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ //marlon
        claseService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/asignarestudiantes")
    public Clase assignEstudiante(
            @RequestParam Long eId,
            @RequestParam Long cId){
        return claseService.assignEstudianteToClass(eId, cId);
    }

    @PostMapping("/retirarestudiantes")
    public Clase eliminarEstudiante(
            @RequestParam Long eId,
            @RequestParam Long cId){
        return claseService.retirarEstudianteToClass(eId, cId);
    }
}
