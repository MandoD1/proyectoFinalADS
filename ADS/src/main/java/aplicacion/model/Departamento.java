package aplicacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Departamento {
    private String nombre;
    private Long id;

    private List<Asignatura> asignaturas = new ArrayList<>();

    public void addAsignaturas(Asignatura asignatura){
        this.asignaturas.add(asignatura);
    }


    private List<Profesor> profesores = new ArrayList<>();

    public void addProfesores(Profesor profesor){
        this.profesores.add(profesor);
    }

    public void removeAsignaturas(Asignatura asignatura) {
        try {
            if (asignatura != null) {
                this.asignaturas.remove(asignatura);
            }
        } catch (Exception e) {
            // No hacer nada
        }
    }

    public void removeProfesores(Profesor profesor) {
        try {
            if (profesor != null) {
                this.profesores.remove(profesor);
            }
        } catch (Exception e) {
            // No hacer nada
        }
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }
}
