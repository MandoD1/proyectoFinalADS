package aplicacion.services;

import aplicacion.model.Carrera;
import aplicacion.model.Clase;
import aplicacion.model.Estudiante;
import aplicacion.repository.CarreraRepository;
import aplicacion.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {

    private final EstudianteRepository estudianteRepository;
    private final CarreraRepository carreraRepository;

    @Autowired
    public CarreraService(EstudianteRepository estudianteRepository, CarreraRepository carreraRepository) {
        this.estudianteRepository = estudianteRepository;
        this.carreraRepository = carreraRepository;
    }

    public Carrera createCarrera(Carrera carrera){
        return carreraRepository.save(carrera);
    }

    public List<Carrera> findAllCarreras(){
        return carreraRepository.findAll();
    }

    public Carrera findCarreraById(Long id){
        Carrera carrera = carreraRepository.findById(id);
        if (carrera == null)
            throw new IllegalArgumentException("Carrera no encontrada");
        return carrera;
    }

    public Carrera updateCarrera(Long id, Carrera carrera){
        Carrera original = findCarreraById(id);

        original.setId(carrera.getId());
        original.setNombre(carrera.getNombre());
        original.setDepartamento(carrera.getDepartamento());
        original.setEstudiantes(carrera.getEstudiantes());
        original.setProfesores(carrera.getProfesores());

        return carreraRepository.save(original);
    }

    public void deleteCarrera(Long id){
        findCarreraById(id);
        carreraRepository.delete(id);
    }

    public Carrera assignEstudianteToCarrera(Long estudianteId, Long claseId){
        Estudiante estudiante = estudianteRepository.findById(estudianteId);
        Carrera carrera = findCarreraById(claseId);

        if (estudiante == null)
            throw new IllegalArgumentException("Estudiante no encontrado");

        carrera.addEstudiante(estudiante);
        return carreraRepository.save(carrera);
    }

    public Carrera retirarEstudianteToCarrera(Long estudianteId, Long carreraId){
        Estudiante estudiante = estudianteRepository.findById(estudianteId);
        Carrera carrera = findCarreraById(carreraId);

        if (estudiante == null)
            throw new IllegalArgumentException("Estudiante no encontrado");

        carrera.retirarEstudiante(estudiante);
        return carreraRepository.save(carrera);
    }

}