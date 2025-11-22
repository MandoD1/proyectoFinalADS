package model;

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

    public List<Carrera> getCarreras(){ return carreras;}

}

