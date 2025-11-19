package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Asignatura {
    private Departamento departamento;
    private long codigo;
    private String nombre;
    private int creditos;
    private boolean requisitoingles;


    private List<Asignatura> prerequisitos = new ArrayList<>();

    public void addPrerequisitos(Asignatura asignatura){
        this.prerequisitos.add(asignatura);
    }

    private List<Asignatura> corequisitos = new ArrayList<>();

    public void addCorequisitos(Asignatura asignatura){
        this.corequisitos.add(asignatura);
    }

    private List<Clase> clases = new ArrayList<>();

    public void addClases(Clase clase){
        this.clases.add(clase);
    }



}
