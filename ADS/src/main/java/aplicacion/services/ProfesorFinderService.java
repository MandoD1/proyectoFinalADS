package aplicacion.services;

import aplicacion.model.Profesor;
import org.springframework.stereotype.Service;

@Service
public class ProfesorFinderService {

    private final ProfesorPlantaService profesorPlantaService;
    private final ProfesorCatedraService profesorCatedraService;
    private final DirectorCarreraService directorCarreraService;
    private final DirectorDepartamentoService directorDepartamentoService;

    public ProfesorFinderService(
            ProfesorPlantaService profesorPlantaService,
            ProfesorCatedraService profesorCatedraService,
            DirectorCarreraService directorCarreraService,
            DirectorDepartamentoService directorDepartamentoService
    ) {
        this.profesorPlantaService = profesorPlantaService;
        this.profesorCatedraService = profesorCatedraService;
        this.directorCarreraService = directorCarreraService;
        this.directorDepartamentoService = directorDepartamentoService;
    }

    public Profesor findProfesor(Long id) {
        Profesor p;

        if ((p = profesorPlantaService.repository.findById(id)) != null) return p;
        if ((p = profesorCatedraService.repository.findById(id)) != null) return p;
        if ((p = directorCarreraService.repository.findById(id)) != null) return p;
        if ((p = directorDepartamentoService.repository.findById(id)) != null) return p;

        throw new IllegalArgumentException("Profesor no encontrado");
    }
}
