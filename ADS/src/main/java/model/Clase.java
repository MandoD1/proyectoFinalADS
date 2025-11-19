package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Clase {
    private Long id;
    private String profesor;
    private Date horario;
    private String salon;
    private int cupoMaximo;
    private String semestre;

    private List<Estudiante> estudiantes = new ArrayList<>();

    public void addEstudiante(Estudiante estudiante){
        this.estudiantes.add(estudiante);
    }
}
