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
    private String carrera;
    private boolean examenIngles;

    private List<Clase> clases = new ArrayList<>();

    public void addClase(Clase clase){
        this.clases.add(clase);
    }
}
