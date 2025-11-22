package controllers;

import model.Asignatura;
import model.Clase;
import model.Departamento;
import model.Profesor;
import services.ClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.DepartamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/clase")
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
    public Departamento assingProfesor(
            @PathVariable Long dId,
            @PathVariable Long pId){
        return departamentoService.assignProfesorToDepartamento(pId, dId);
    }

    @PostMapping("/{dId}/asignaturas/{aId}")
    public Departamento assingAsignaturas(
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

    @PostMapping("/profesores/{did}")
    public List<Profesor> verProfesores(@PathVariable Long did){
        return departamentoService.findProfesores(did);
    }

    @PostMapping("/asignaturas/{did}")
    public List<Asignatura> verAsignaturas(@PathVariable Long did){
        return departamentoService.findAsignaturas(did);
    }



}
