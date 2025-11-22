package aplicacion.services;

import aplicacion.model.Clase;
import aplicacion.model.DirectorCarrera;
import aplicacion.model.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aplicacion.repository.DirectorCarreraRepository;

@Service
public class DirectorCarreraService extends ProfesorService<DirectorCarrera> {

    @Autowired
    public DirectorCarreraService(DirectorCarreraRepository directorCarreraRepository) {
        super(directorCarreraRepository);
    }

    @Override
    protected void applyUpdates(DirectorCarrera original, DirectorCarrera updated) {
        original.setNombre(updated.getNombre());
        original.setEmail(updated.getEmail());
        original.setDepartamento(updated.getDepartamento());
        original.setPago(updated.getPago());
        original.setCodigo(updated.getCodigo());
        original.setClases(updated.getClases());
        original.setCarrera(updated.getCarrera());
    }

    public Profesor consultarProfesor(Long id) {
        return findById(id);
    }

    public void modificarCargaProfesor(Long id, Clase clase, Boolean bandera) {
        Profesor profesor = consultarProfesor(id);
        if (bandera) {
            profesor.addClases(clase);
        } else if (!bandera) {
            profesor.removeClases(clase);
        } else {
            throw new RuntimeException("No se puede modificar el profesor");
        }
    }



}
