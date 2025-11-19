package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Estudiante {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    private String nombre;
    private String email;
    private String carrera;
    private boolean examenIngles;

    List<Clase> clases = new ArrayList<>();

    public void addClase(Clase clase){
        this.clases.add(clase);
    }
}