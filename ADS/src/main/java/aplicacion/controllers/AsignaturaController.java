package aplicacion.controllers;

import aplicacion.model.Asignatura;
import aplicacion.services.AsignaturaService;
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

    @PostMapping("/prerequisito")
    public Asignatura assingPrerequisito(
            @RequestParam Long aId,
            @RequestParam Long pId){
        return asignaturaService.assingPrerequisito(aId, pId);
    }

    @PostMapping("/corequisito")
    public Asignatura assingCorequisito(
            @RequestParam Long aId,
            @RequestParam Long cId){
        return asignaturaService.assingCorequisito(aId, cId);
    }

    @PostMapping("/asingclase")
    public Asignatura assingClase(
            @RequestParam Long aId,
            @RequestParam Long claseId){
        return asignaturaService.assingClase(aId, claseId);
    }

    @PostMapping("/Asignaturanota")
    public Asignatura assingAsignaturaNotaYEstado( //esta la metio marlon
            @RequestParam Long aId,
            @RequestParam double calificacion){
        return asignaturaService.assingAsignaturaNotaYEstado(aId, calificacion);
    }

    @PostMapping("/removeprerequisito")
    public Asignatura removePrerequisito(
            @RequestParam Long aId,
            @RequestParam Long pId){
        return asignaturaService.eliminarPrerequisito(aId, pId);
    }

    @PostMapping("/removecorequisito")
    public Asignatura removeCorequisito(
            @RequestParam Long aId,
            @RequestParam Long cId){
        return asignaturaService.eliminarCorequisito(aId, cId);
    }

    @PostMapping("/removeclase")
    public Asignatura removeClase(
            @RequestParam Long aId,
            @RequestParam Long claseId){
        return asignaturaService.eliminarPrerequisito(aId, claseId);
    }

    @PostMapping("/prerequisitoget")
    public List<Asignatura> getPrerequisitos( @RequestParam Long pId){
        return asignaturaService.seePrerequisitos(pId);
    }

    @PostMapping("/corequisitoget")
    public List<Asignatura> getCorequisitos( @RequestParam Long cId){
        return asignaturaService.seeCorequisitos(cId);
    }


}

