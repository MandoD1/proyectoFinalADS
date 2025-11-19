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
    private int cupoActual;
    private String semestre;
    private Asigatura asigatura;

    private List<Estudiante> estudiantes = new ArrayList<>();

    public void addEstudiante(Estudiante estudiante){
        this.estudiantes.add(estudiante);
    }

    public void retirarEstudiante(Estudiante estudiante){
        this.estudiantes.remove(estudiante);
    }

    public boolean cupo(){
        if(cupoMaximo > cupoActual){
            return true;
        }
        return false;
    }

    public void aumentarCupo(){
        this.cupoActual++;
    }

}
