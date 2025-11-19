package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Departamento {
    private String nombre;
    private long codigo;

    private List<Asignatura> asignaturas = new ArrayList<>();

    public void addAsignaturas(Asignatura asignatura){
        this.asignaturas.add(asignatura);
    }

    private List<Profesor> profesores = new ArrayList<>();

    public void addProfesores(Profesor profesor){
        this.profesores.add(profesor);
    }
}
