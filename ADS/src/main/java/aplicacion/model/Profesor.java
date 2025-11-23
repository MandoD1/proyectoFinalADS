package aplicacion.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Profesor {
    private String nombre;
    private String email;
    private Departamento departamento;
    private double pago;
    private Long id;
    private int horasDeClase;

    private List<Clase> clases = new ArrayList<>();

    public void addClases(Clase clase){
        this.clases.add(clase);
    }

    public void removeClases(Clase clase) {
        try {
            if (clase != null) {
                this.clases.remove(clase);
            }
        } catch (Exception e) {
            // No hacer nada si ocurre un error
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }

    public float getHorasDeClase() { return horasDeClase; }

    public int getTotalHoras() {
        int total = 0;

        for (Clase clase : clases) {
            total += clase.getHoras();
        }

        return total;
    }

    public void setHorasDeClase() {this.horasDeClase = getTotalHoras(); }
}
