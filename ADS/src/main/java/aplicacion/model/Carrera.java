package aplicacion.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Carrera {
    private Long id;
    private String nombre;

    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Profesor> profesores = new ArrayList<>();

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

    public List<Profesor> getProfesores() {
        return profesores;
    }

    public void setProfesores(List<Profesor> profesores) {
        this.profesores = profesores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCodigo() {
        return id;
    }

    public void setCodigo(Long codigo) {
        this.id = codigo;
    }
}
