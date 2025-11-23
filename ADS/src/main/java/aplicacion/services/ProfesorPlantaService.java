package aplicacion.services;

import aplicacion.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aplicacion.repository.ProfesorPlantaRepository;

@Service
public class ProfesorPlantaService extends ProfesorService<ProfesorPlanta> {

    @Autowired
    public ProfesorPlantaService(ProfesorPlantaRepository profesorPlantaRepository) {
        super(profesorPlantaRepository);
    }

    @Override
    protected void applyUpdates(ProfesorPlanta original, ProfesorPlanta updated) {
        original.setNombre(updated.getNombre());
        original.setEmail(updated.getEmail());
        original.setDepartamento(updated.getDepartamento());
        original.setPago(updated.getPago());
        original.setId(updated.getId());
        original.setClases(updated.getClases());
        original.setHorasDeClase();
    }
}