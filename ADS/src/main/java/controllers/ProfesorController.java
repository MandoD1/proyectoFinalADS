package controllers;

import model.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.ProfesorService;

import java.util.List;

@RestController
@RequestMapping("/api/profesor")
public class ProfesorController {

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping
    public Profesor create(@RequestBody Profesor profesor) {
            return profesorService.createProfesor(profesor);
    }

    @GetMapping
    public List<Profesor> getAll(){
        return profesorService.findAllProfesores();
    }

    @GetMapping("/{id}")
    public Profesor getById(@PathVariable Long id){
        return profesorService.findProfesorById(id);
    }

    @PutMapping("/{id}")
    public Profesor update(@PathVariable Long id, @RequestBody Profesor profesor){
        return profesorService.updateProfesor(id, profesor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{pId}/clases/{cId}")
    public Profesor assignClass(
            @PathVariable Long pId,
            @PathVariable Long cId){
        return profesorService.assignClassToProfesor(pId, cId);
    }

    @PostMapping("/{pId}/retirarclases/{cId}")
    public Profesor retirarClase(
            @PathVariable Long pId,
            @PathVariable Long cId){
        return profesorService.retirarClaseOfProfesor(pId, cId);
    }

}
