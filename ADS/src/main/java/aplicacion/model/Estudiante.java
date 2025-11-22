package aplicacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Estudiante {

    private Long codigo;
    private String nombre;
    private String email;
    private String carrera;
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

    public void deleteCarrera(Carrera carrera){
        this.carreras.remove(carrera);
    }

    public List<Clase> getClases() {
        return clases;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
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
}


