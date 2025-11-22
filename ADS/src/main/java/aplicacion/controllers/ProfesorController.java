package aplicacion.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import aplicacion.services.ProfesorService;

import java.util.List;

public abstract class ProfesorController<T> {

    protected final ProfesorService<T> service;

    protected ProfesorController(ProfesorService<T> service) {
        this.service = service;
    }

    @PostMapping
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }

    @GetMapping
    public List<T> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public T getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public T update(@PathVariable Long id, @RequestBody T entity) {
        return service.update(id, entity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
