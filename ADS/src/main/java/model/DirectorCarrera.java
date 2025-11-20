package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DirectorCarrera extends Profesor {
    private String carrera;
}
