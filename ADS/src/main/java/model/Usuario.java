package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {
    public Long codigo;
    public String correo;
    public String tipoUsuario;
    public String contraseña;

}
