package aplicacion.services;

import aplicacion.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aplicacion.repository.*;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private EstudianteRepository estudianteRepository;
    private DirectorDepartamentoRepository directorDepartamentoRepository;
    private ProfesorCatedraRepository profesorCatedraRepository;
    private DirectorCarreraRepository directorCarreraRepository;

    public boolean existeCorreo(String Correo) {
        for (Usuario usuario : usuarioRepository.findAll()) {
            if (usuario.getCorreo().equals(Correo)) {
                return true;
            }
        }
        return false;
    }

    public Usuario registrar(Usuario usuario) {
        if(existeCorreo(usuario.getCorreo())){
            throw new RuntimeException("El correo ya está registrado");
        }
        switch (usuario.getTipoUsuario()) {
            case "Estudiante" -> {
                Estudiante estudiante = new Estudiante();
                Long id = usuario.getId();
                String correo = usuario.getCorreo();
                estudiante.setId(id);
                estudiante.setEmail(correo);
                estudianteRepository.save(estudiante);
            }
            case "DirectorCarrera" -> {
                DirectorCarrera directorCarrera = new DirectorCarrera();
                Long id = usuario.getId();
                String correo = usuario.getCorreo();
                directorCarrera.setId(id);
                directorCarrera.setEmail(correo);
                directorCarreraRepository.save(directorCarrera);
            }
            case "ProfesorCatedra" -> {
                ProfesorCatedra profesorCatedra = new ProfesorCatedra();
                Long id = usuario.getId();
                String correo = usuario.getCorreo();
                profesorCatedra.setId(id);
                profesorCatedra.setEmail(correo);
                profesorCatedraRepository.save(profesorCatedra);
            }
            case "DirectorDepartamento" -> {
                DirectorDepartamento directorDepartamento = new DirectorDepartamento();
                Long id = usuario.getId();
                String correo = usuario.getCorreo();
                directorDepartamento.setId(id);
                directorDepartamento.setEmail(correo);
                directorDepartamentoRepository.save(directorDepartamento);
            }
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario login(String correo, String contraseña) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);

        if (usuario == null)
            throw new IllegalArgumentException("usuario no encontrado");

        if (!usuario.getContraseña().equals(contraseña)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        usuarioRepository.saveUsuario(usuario);
        return usuario;
    }

    public void logout(){

    }
}
