package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Asignatura {
    private Departamento departamento;
    private Long codigo;
    private String nombre;
    private int creditos;
    private boolean requisitoingles;


    private List<Asignatura> prerequisitos = new ArrayList<>();

    public void addPrerequisitos(Asignatura asignatura){
        this.prerequisitos.add(asignatura);
    }

    public void removePrerequisitos(Asignatura asignatura){ this.prerequisitos.remove(asignatura); }

    private List<Asignatura> corequisitos = new ArrayList<>();

    public void addCorequisitos(Asignatura asignatura){
        this.corequisitos.add(asignatura);
    }

    public void removeCorequisitos(Asignatura asignatura){ this.corequisitos.remove(asignatura); }

    private List<Clase> clases = new ArrayList<>();

    public void addClases(Clase clase){
        this.clases.add(clase);
    }

    public void removeClases(Clase clase){ this.clases.remove(clase); }

    public List<Asignatura> getPrerequisitos(){ return prerequisitos; }

    public List<Asignatura> getCorequisitos(){ return corequisitos; }



}
