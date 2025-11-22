package aplicacion.controllers;

import aplicacion.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import aplicacion.services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public Usuario register(@RequestBody String correo, @RequestBody String contraseña, @RequestBody String tipoUsuario) {
        Usuario usuario = new Usuario();
        usuario.setCorreo(correo);
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setContraseña(contraseña);
        return usuarioService.registrar(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody String correo, @RequestBody String contraseña) {
        return usuarioService.login(correo, contraseña);
    }
}
