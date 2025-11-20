package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfesorCatedra extends Profesor {
    private String empresa;
    private char categoria;

}
