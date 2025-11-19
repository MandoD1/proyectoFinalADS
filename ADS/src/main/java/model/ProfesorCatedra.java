package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfesorCatedra extends Profesor {
    private String empresa;
    private char categoria;

}
