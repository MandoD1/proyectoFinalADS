package controllers;

import model.Asignatura;
import model.Clase;
import services.AsignaturaService;
import services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignatura")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    @Autowired
    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @PostMapping
    public Asignatura create(@RequestBody Asignatura asignatura){
        return asignaturaService.createAsignatura(asignatura);
    }

    @GetMapping
    public List<Asignatura> getAll(){
        return asignaturaService.findAllAsignatura();
    }

    @GetMapping("/{id}")
    public Asignatura getById(@PathVariable Long id){
        return asignaturaService.findAsignaturaById(id);
    }

    @PutMapping("/{id}")
    public Asignatura update(@PathVariable Long id, @RequestBody Asignatura asignatura){
        return asignaturaService.updateAsignatura(id, asignatura);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        asignaturaService.deleteAsignatura(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{aId}/prerequisito/{pId}")
    public Asignatura assingPrerequisito(
            @PathVariable Long aId,
            @PathVariable Long pId){
        return asignaturaService.assingPrerequisito(aId, pId);
    }

    @PostMapping("/{aId}/corequisito/{cId}")
    public Asignatura assingCorequisito(
            @PathVariable Long aId,
            @PathVariable Long cId){
        return asignaturaService.assingCorequisito(aId, cId);
    }

    @PostMapping("/{aId}/clase/{claseId}")
    public Asignatura assingClase(
            @PathVariable Long aId,
            @PathVariable Long claseId){
        return asignaturaService.assingClase(aId, claseId);
    }

    @PostMapping("/{aId}/removeprerequisito/{pId}")
    public Asignatura removePrerequisito(
            @PathVariable Long aId,
            @PathVariable Long pId){
        return asignaturaService.eliminarPrerequisito(aId, pId);
    }

    @PostMapping("/{aId}/removecorequisito/{cId}")
    public Asignatura removeCorequisito(
            @PathVariable Long aId,
            @PathVariable Long cId){
        return asignaturaService.eliminarCorequisito(aId, cId);
    }

    @PostMapping("/{aId}/removeclase/{claseId}")
    public Asignatura removeClase(
            @PathVariable Long aId,
            @PathVariable Long claseId){
        return asignaturaService.eliminarPrerequisito(aId, claseId);
    }

    @PostMapping("/prerequisito/{pId}")
    public List<Asignatura> getPrerequisitos(@PathVariable Long pId){
        return asignaturaService.seePrerequisitos(pId);
    }

    @PostMapping("/corequisito/{cId}")
    public List<Asignatura> getCorequisitos(@PathVariable Long cId){
        return asignaturaService.seeCorequisitos(cId);
    }

}

