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

    public List<Asignatura> getAsignaturasPensum() {
        return AsignaturasPensum;
    }

    public void setAsignaturasPensum(List<Asignatura> asignaturasPensum) {
        AsignaturasPensum = asignaturasPensum;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
