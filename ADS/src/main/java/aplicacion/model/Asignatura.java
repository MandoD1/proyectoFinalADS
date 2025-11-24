package aplicacion.model;

import aplicacion.services.AsignaturaService;
import aplicacion.services.estadoClaseEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Asignatura {
    private Departamento departamento;
    private Long id;
    private String nombre;
    private int creditos;
    private boolean requisitoingles;
    private estadoClaseEnum estado;    //#enum aprobada, retirada, perdida, no vista
    private double calificacion;

    private List<Asignatura> prerequisitos = new ArrayList<>();

    public void addPrerequisitos(Asignatura asignatura){
        this.prerequisitos.add(asignatura);
    }

    public void removePrerequisitos(Asignatura asignatura) {
        try {
            if (asignatura != null) {
                this.prerequisitos.remove(asignatura);
            }
        } catch (Exception e) {
            // No hacer nada si falla
        }
    }

    private List<Asignatura> corequisitos = new ArrayList<>();

    public void addCorequisitos(Asignatura asignatura) {
        this.corequisitos.add(asignatura);
    }

    public void removeCorequisitos(Asignatura asignatura) {
        try {
            if (asignatura != null) {
                this.corequisitos.remove(asignatura);
            }
        } catch (Exception e) {
            // No hacer nada si falla
        }
    }
    private List<Clase> clases = new ArrayList<>();

    public void addClases(Clase clase){
        this.clases.add(clase);
    }

    public void removeClases(Clase clase){ this.clases.remove(clase); }

    public List<Asignatura> getPrerequisitos(){ return prerequisitos; }

    public List<Asignatura> getCorequisitos(){ return corequisitos; }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public boolean isRequisitoingles() {
        return requisitoingles;
    }

    public void setRequisitoingles(boolean requisitoingles) {
        this.requisitoingles = requisitoingles;
    }

    public void setPrerequisitos(List<Asignatura> prerequisitos) {
        this.prerequisitos = prerequisitos;
    }

    public void setCorequisitos(List<Asignatura> corequisitos) {
        this.corequisitos = corequisitos;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    public estadoClaseEnum getEstado() {
        return estado;
    }

    public void setEstado(estadoClaseEnum estado) {
        this.estado = estado;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
