package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DirectorDepartamento extends Profesor {
    private String carrera;
}
