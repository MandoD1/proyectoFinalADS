package controllers;

import model.Estudiante;
import services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping
    public Estudiante create(@RequestBody Estudiante estudiante){
        return estudianteService.createEstudiante(estudiante);
    }

    @GetMapping
    public List<Estudiante> getAll(){
        return estudianteService.findAllEstudiante();
    }

    @GetMapping("/{id}")
    public Estudiante getById(@PathVariable Long id){
        return estudianteService.findEstudianteById(id);
    }

    @PutMapping("/{id}")
    public Estudiante update(@PathVariable Long id, @RequestBody Estudiante estudiante){
        return estudianteService.updateEstudiante(id, estudiante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        estudianteService.deleteEstudiante(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eId}/clases/{cId}")
    public Estudiante assignClass(
            @PathVariable Long eId,
            @PathVariable Long cId){
        return estudianteService.assignClassToEstudiante(eId, cId);
    }
}
