package model;

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
    private Long codigo;

    private List<Clase> clases = new ArrayList<>();

    public void addClases(Clase clase){
        this.clases.add(clase);
    }

}
