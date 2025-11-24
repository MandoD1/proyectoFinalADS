package aplicacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Estudiante {

    private Long id;
    private String nombre;
    private String email;
    private boolean examenIngles;

    private List<Carrera> carreras= new ArrayList<>();
    private List<Clase> clases = new ArrayList<>();



    public void addClase(Clase clase){
        this.clases.add(clase);
    }

    public void deleteClase(Clase clase){
        this.clases.remove(clase);
    }

    public void addCarrera(Carrera carrera){this.carreras.add(carrera);}

    public void deleteCarrera(Carrera carrera) {
        try {
            if (carrera != null) {
                this.carreras.remove(carrera);
            }
        } catch (Exception e) {
            // No hacer nada
        }
    }


    public List<Clase> getClases() {
        return clases;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isExamenIngles() {
        return examenIngles;
    }

    public void setExamenIngles(boolean examenIngles) {
        this.examenIngles = examenIngles;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public boolean tieneChoqueHorario(Clase nuevaClase) {
        List<Date> nuevoHorario = nuevaClase.getHorario();

        for (Clase claseExistente : clases) {
            for (Date fechaExistente : claseExistente.getHorario()) {
                for (Date fechaNueva : nuevoHorario) {
                    if (fechaExistente.equals(fechaNueva)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}


