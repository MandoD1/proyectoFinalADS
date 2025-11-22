package aplicacion.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import aplicacion.model.Usuario;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("ADS/data/usuarios.json");
    private final File file1 = new File("ADS/data/usuario.json");

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepository() {
        loadData();
    }

    private void loadData() {
        try {
            if (file.exists()) {
                usuarios = mapper.readValue(file, new TypeReference<List<Usuario>>() {});
            }
        } catch (Exception e) {
            throw new RuntimeException("Error cargando usuarios.json", e);
        }
    }

    private void saveData() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, usuarios);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando usuarios.json", e);
        }
    }

    public Usuario save(Usuario e) {
        if (e.getCodigo() == 0) {
            e.setCodigo(generateId());
        }
        usuarios.removeIf(x -> x.getCodigo() == e.getCodigo());
        usuarios.add(e);
        saveData();
        return e;
    }

    public Usuario findByCorreo(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo)) {
                return u;
            }
        }
        return null;
    }

    public void saveUsuario(Usuario usuario) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file1, usuario);
        } catch (Exception e) {
            throw new RuntimeException("Error guardando usuario.json", e);
        }
    }
    public void deleteUsuario() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file1, null);
        } catch (Exception e) {
            throw new RuntimeException("Error borrando usuario.json", e);
        }
    }

    public Usuario loadUsuario() {
        try {
            if (file1.exists()) {
                return mapper.readValue(file1, Usuario.class);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo usuario.json", e);
        }
        return null;
    }

    private Long generateId() {
        return usuarios.stream()
                .mapToLong(Usuario::getCodigo)
                .max()
                .orElse(0) + 1;
    }

    public List<Usuario> findAll() {
        return usuarios;
    }
}
