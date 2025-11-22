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

    @PostMapping("/{dId}/profesores/{pId}")
    public Departamento assignProfesor(
            @PathVariable Long dId,
            @PathVariable Long pId){
        return departamentoService.assignProfesorToDepartamento(pId, dId);
    }

    @PostMapping("/{dId}/asignaturas/{aId}")
    public Departamento assignAsignaturas(
            @PathVariable Long dId,
            @PathVariable Long aId){
        return departamentoService.assignAsignaturaToDepartamento(aId, dId);
    }

    @PostMapping("/{dId}/removeprofesores/{pId}")
    public Departamento removeProfesor(
            @PathVariable Long dId,
            @PathVariable Long pId){
        return departamentoService.retirarProfesorToDepartamento(pId, dId);
    }

    @PostMapping("/{dId}/removeasignaturas/{aId}")
    public Departamento removeAsignaturas(
            @PathVariable Long dId,
            @PathVariable Long aId){
        return departamentoService.retirarAsignaturaToDepartamento(aId, dId);
    }

    @GetMapping("/{did}/profesores")
    public List<Profesor> verProfesores(@PathVariable Long did){
        return departamentoService.findProfesores(did);
    }

    @GetMapping("/{did}/asignaturas")
    public List<Asignatura> verAsignaturas(@PathVariable Long did){
        return departamentoService.findAsignaturas(did);
    }

}
