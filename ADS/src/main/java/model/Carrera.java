package model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Carrera {
    private String codigo;
    private String nombre;

    private List<Estudiante> estudiantes = new ArrayList<>();

    public void addEstudiante(Estudiante estudiante){
        this.estudiantes.add(estudiante);
    }

    private List<Asignatura> AsignaturasPensum = new ArrayList<>();

    public void addAsignaturaPensum(Asignatura asignatura){
        this.AsignaturasPensum.add(asignatura);
    }
}
