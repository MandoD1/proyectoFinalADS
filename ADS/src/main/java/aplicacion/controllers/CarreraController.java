package aplicacion.controllers;

import aplicacion.model.Carrera;
import aplicacion.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrera")
public class CarreraController {

    private final CarreraService carreraService;

    @Autowired
    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @PostMapping
    public Carrera create(@RequestBody Carrera carrera) {
        return carreraService.createCarrera(carrera);
    }

    @GetMapping
    public List<Carrera> getAll(){
        return carreraService.findAllCarreras();
    }

    @GetMapping("/{id}")
    public Carrera getById(@PathVariable Long id){
        return carreraService.findCarreraById(id);
    }

    @PutMapping("/{id}")
    public Carrera update(@PathVariable Long id, @RequestBody Carrera clase){
        return carreraService.updateCarrera(id, clase);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        carreraService.deleteCarrera(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{eId}/estudiantes/{cId}")
    public Carrera assignEstudiante(
            @PathVariable Long eId,
            @PathVariable Long cId){
        return carreraService.assignEstudianteToCarrera(eId, cId);
    }

    @PostMapping("/{eId}/retirarestudiantes/{cId}")
    public Carrera eliminarEstudiante(
            @PathVariable Long eId,
            @PathVariable Long cId){
        return carreraService.retirarEstudianteToCarrera(eId, cId);
    }
}
