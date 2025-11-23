package aplicacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class Clase {
    private Long id;
    private Profesor profesor;
    private List<Date> horario;
    private int horas;
    private String salon;
    private int cupoMaximo;
    private int cupoActual;
    private String semestre;
    private Asignatura asignatura;

    private List<Estudiante> estudiantes = new ArrayList<>();

    public void addEstudiante(Estudiante estudiante){
        this.estudiantes.add(estudiante);
    }

    public void retirarEstudiante(Estudiante estudiante) {
        try {
            if (estudiante != null) {
                this.estudiantes.remove(estudiante);
            }
        } catch (Exception e) {
            // No hacer nada si ocurre un error
        }
    }

    public boolean cupo(){
        if(cupoMaximo > cupoActual){
            return true;
        }
        return false;
    }

    public boolean cupomenoral40(){
        return cupoActual < (0.4 * cupoMaximo);
    }

    public boolean conProfesor(){
        return profesor != null;
    }

    public void aumentarCupo(){
        this.cupoActual++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Date> getHorario() {
        return horario;
    }

    public void setHorario(List<Date> horario) {
        this.horario = horario;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getCupoActual() {
        return cupoActual;
    }

    public void setCupoActual(int cupoActual) {
        this.cupoActual = cupoActual;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
}

