package aplicacion.services;

import aplicacion.model.*;
import aplicacion.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {


    private final DepartamentoRepository departamentoRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final ProfesorFinderService profesorFinderService;

    public DepartamentoService(
            DepartamentoRepository departamentoRepository,
            AsignaturaRepository asignaturaRepository,
            ProfesorFinderService profesorFinderService
    ) {
        this.departamentoRepository = departamentoRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.profesorFinderService = profesorFinderService;
    }

    public Departamento createDepartamento(Departamento departamento){
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> findAllDepartamentos(){
        return departamentoRepository.findAll();
    }

    public Departamento findDepartamentoById(Long id){
        Departamento departamento = departamentoRepository.findById(id);
        if (departamento == null)
            throw new IllegalArgumentException("Departamento no encontrado");
        return departamento;
    }

    public Departamento updateDepartamento(Long id, Departamento departamento){
        Departamento original = findDepartamentoById(id);

        original.setCodigo(departamento.getCodigo());
        original.setAsignaturas(departamento.getAsignaturas());
        original.setNombre(departamento.getNombre());
        original.setProfesores(departamento.getProfesores());

        return departamentoRepository.save(original);
    }

    public void deleteDepartamento(Long id){
        findDepartamentoById(id);
        departamentoRepository.delete(id);
    }

    public Departamento assignAsignaturaToDepartamento(Long AsignaturaId, Long DepartamentoId){
        Asignatura asignatura = asignaturaRepository.findById(AsignaturaId);
        Departamento departamento = findDepartamentoById(DepartamentoId);

        if (asignatura == null)
            throw new IllegalArgumentException("Asignatura no encontrada");

        departamento.addAsignaturas(asignatura);
        return departamentoRepository.save(departamento);
    }

    public Departamento retirarAsignaturaToDepartamento(Long AsignaturaId, Long DepartamentoId){
        Asignatura asignatura = asignaturaRepository.findById(AsignaturaId);
        Departamento departamento = findDepartamentoById(DepartamentoId);

        if (asignatura == null)
            throw new IllegalArgumentException("Asignatura no encontrada");

        departamento.removeAsignaturas(asignatura);
        return departamentoRepository.save(departamento);
    }

    public Departamento assignProfesorToDepartamento(Long profesorId, Long departamentoId) {
        Profesor profesor = profesorFinderService.findProfesor(profesorId);
        Departamento departamento = findDepartamentoById(departamentoId);

        if (profesor == null)
            throw new IllegalArgumentException("Profesor no encontrado");

        departamento.addProfesores(profesor);
        return departamentoRepository.save(departamento);
    }


    public Departamento retirarProfesorToDepartamento(Long ProfesorId, Long DepartamentoId){
        Profesor profesor = profesorFinderService.findProfesor(ProfesorId);
        Departamento departamento = findDepartamentoById(DepartamentoId);

        if (profesor == null)
            throw new IllegalArgumentException("Profesor no encontrado");

        departamento.removeProfesores(profesor);
        return departamentoRepository.save(departamento);
    }

    public List<Asignatura> findAsignaturas(Long Departamentoid){
        Departamento departamento = findDepartamentoById(Departamentoid);
        return departamento.getAsignaturas();
    }

    public List<Profesor> findProfesores(Long Departamentoid){
        Departamento departamento = findDepartamentoById(Departamentoid);
        return departamento.getProfesores();
    }

}