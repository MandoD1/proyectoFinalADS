package aplicacion.controllers;

import aplicacion.model.Asignatura;
import aplicacion.model.Clase;
import aplicacion.model.Estudiante;
import aplicacion.services.AsignaturaService;
import aplicacion.services.ClaseService;
import aplicacion.services.EstudianteService;
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

    @PostMapping("/asignarclases")
    public Estudiante assignClass(
            @RequestParam Long eId,
            @RequestParam Long cId){
        return estudianteService.assignClassToEstudiante(eId, cId);
    }

    @PostMapping("/retirarclases")
    public Estudiante retirarClase(
            @RequestParam Long eId,
            @RequestParam Long cId){
        return estudianteService.retirarClaseOfEstudiante(eId, cId);
    }

    @PostMapping("/verclases")
    public List<Clase> verClases(
            @RequestParam Long eId){
        return estudianteService.SeeClasesOfEstudiante(eId);
    }
    @PostMapping("/retirarcarrera")
    public Estudiante retirarCarrera(
            @RequestParam Long eId,
            @RequestParam Long cId){
        return estudianteService.retirarCarrearOfEstudiante(eId, cId);
    }

    @PostMapping("/asingcarrera")
    public Estudiante assingCarrera(
            @RequestParam Long aId,
            @RequestParam Long carreraId){
        return estudianteService.assignCarreraToEstudiante(aId, carreraId);
    }

}
