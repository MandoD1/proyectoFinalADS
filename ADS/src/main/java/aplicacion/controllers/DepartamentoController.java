package aplicacion.controllers;

import aplicacion.model.Asignatura;
import aplicacion.model.Departamento;
import aplicacion.model.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import aplicacion.services.DepartamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    @Autowired
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping
    public Departamento create(@RequestBody Departamento departamento){
        return departamentoService.createDepartamento(departamento);
    }

    @GetMapping
    public List<Departamento> getAll(){
        return departamentoService.findAllDepartamentos();
    }

    @GetMapping("/{id}")
    public Departamento getById(@PathVariable Long id){
        return departamentoService.findDepartamentoById(id);
    }

    @PutMapping("/{id}")
    public Departamento update(@PathVariable Long id, @RequestBody Departamento desdepartamento){
        return departamentoService.updateDepartamento(id, desdepartamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        departamentoService.deleteDepartamento(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/asignarprofesores")
    public Departamento assignProfesor(
            @RequestParam Long dId,
            @RequestParam Long pId){
        return departamentoService.assignProfesorToDepartamento(pId, dId);
    }

    @PostMapping("/asignarasignaturas")
    public Departamento assignAsignaturas(
            @RequestParam Long dId,
            @RequestParam Long aId){
        return departamentoService.assignAsignaturaToDepartamento(aId, dId);
    }

    @PostMapping("/removeprofesores")
    public Departamento removeProfesor(
            @RequestParam Long dId,
            @RequestParam Long pId){
        return departamentoService.retirarProfesorToDepartamento(pId, dId);
    }

    @PostMapping("/removeasignaturas")
    public Departamento removeAsignaturas(
            @RequestParam Long dId,
            @RequestParam Long aId){
        return departamentoService.retirarAsignaturaToDepartamento(aId, dId);
    }

    @GetMapping("/verprofesore")
    public List<Profesor> verProfesores(@RequestParam Long did){
        return departamentoService.findProfesores(did);
    }

    @GetMapping("/verasignaturas")
    public List<Asignatura> verAsignaturas(@RequestParam Long did){
        return departamentoService.findAsignaturas(did);
    }

}
