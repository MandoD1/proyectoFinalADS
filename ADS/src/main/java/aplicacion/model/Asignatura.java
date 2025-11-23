package aplicacion.model;

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
    private String estado;    //#enum aprobada, retirada, perdida, no vista
    private int calificacion;

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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
