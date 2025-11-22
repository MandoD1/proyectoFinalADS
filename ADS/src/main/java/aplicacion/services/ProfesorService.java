package aplicacion.services;

import aplicacion.repository.ProfesorRepository;

import java.util.List;

public abstract class ProfesorService<T> {

    protected final ProfesorRepository<T> repository;

    protected ProfesorService(ProfesorRepository<T> repository) {
        this.repository = repository;
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findById(Long id) {
        T entity = repository.findById(id);
        if (entity == null)
            throw new IllegalArgumentException("Profesor no encontrado");
        return entity;
    }

    public T update(Long id, T newData) {
        T original = findById(id);
        applyUpdates(original, newData);
        return repository.save(original);
    }

    public void delete(Long id) {
        findById(id);
        repository.delete(id);
    }

    protected abstract void applyUpdates(T original, T updated);
}
