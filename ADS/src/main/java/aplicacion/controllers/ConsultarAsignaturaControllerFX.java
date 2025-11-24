package aplicacion.controllers;

import aplicacion.client.BackendClientEstudiante;
import aplicacion.model.Asignatura;
import aplicacion.model.Clase;
import aplicacion.repository.UsuarioRepository;
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ConsultarAsignaturaControllerFX implements Initializable {

    @FXML private AutoCompleteTextField idclaseaconsultar; // ID de la asignatura
    @FXML private Button btnVolverEstudMenu;
    @FXML private Button btnconsultarclase;

    private BackendClientEstudiante backend;
    private UsuarioRepository usuarioRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador ConsultarAsignatura inicializado.");
        usuarioRepository = new UsuarioRepository();
        backend = new BackendClientEstudiante(usuarioRepository);
    }

    @FXML
    public void onActionconsultarclase(ActionEvent event) {
        String idAsignaturaStr = idclaseaconsultar.getText().trim();

        if (idAsignaturaStr.isEmpty()) {
            mostrarAlerta("Error de Validación", "Ingrese el ID de la asignatura.", Alert.AlertType.ERROR);
            return;
        }

        try {
            Long idAsignatura = Long.valueOf(idAsignaturaStr);

            // Traemos todas las clases del estudiante desde el backend
            List<Clase> clases = backend.verClases(usuarioRepository.loadUsuario().getId());

            Asignatura asignaturaEncontrada = null;

            // Buscamos la asignatura por ID en todas las clases
            for (Clase c : clases) {
                if (c.getAsignatura() != null && c.getAsignatura().getId().equals(idAsignatura)) {
                    asignaturaEncontrada = c.getAsignatura();
                    break;
                }
            }

            if (asignaturaEncontrada == null) {
                mostrarAlerta("Asignatura no encontrada",
                        "No se encontró la asignatura con ID " + idAsignatura,
                        Alert.AlertType.ERROR);
                return;
            }

            // Construimos la información detallada
            StringBuilder info = new StringBuilder();
            info.append("ID: ").append(asignaturaEncontrada.getId()).append("\n");
            info.append("Nombre: ").append(asignaturaEncontrada.getNombre()).append("\n");
            info.append("Departamento: ").append(asignaturaEncontrada.getDepartamento() != null ?
                    asignaturaEncontrada.getDepartamento().getNombre() : "N/A").append("\n");
            info.append("Créditos: ").append(asignaturaEncontrada.getCreditos()).append("\n");
            info.append("Requisito inglés: ").append(asignaturaEncontrada.isRequisitoingles() ? "Sí" : "No").append("\n");
            info.append("Estado: ").append(asignaturaEncontrada.getEstado()).append("\n");
            info.append("Calificación: ").append(asignaturaEncontrada.getCalificacion()).append("\n");

            // Prerequisitos
            if (!asignaturaEncontrada.getPrerequisitos().isEmpty()) {
                info.append("Prerrequisitos: ");
                for (Asignatura pre : asignaturaEncontrada.getPrerequisitos()) {
                    info.append(pre.getNombre()).append(" ");
                }
                info.append("\n");
            }

            // Corequisitos
            if (!asignaturaEncontrada.getCorequisitos().isEmpty()) {
                info.append("Corequisitos: ");
                for (Asignatura co : asignaturaEncontrada.getCorequisitos()) {
                    info.append(co.getNombre()).append(" ");
                }
                info.append("\n");
            }

            // Clases asociadas
            if (!asignaturaEncontrada.getClases().isEmpty()) {
                info.append("Clases asociadas:\n");
                for (Clase clase : asignaturaEncontrada.getClases()) {
                    info.append("  - ID Clase: ").append(clase.getId())
                            .append(", Salón: ").append(clase.getSalon())
                            .append(", Horario: ").append(clase.getHorario())
                            .append(", Cupo: ").append(clase.getCupoActual()).append("/").append(clase.getCupoMaximo())
                            .append("\n");
                }
            }

            mostrarAlerta("Información de la Asignatura", info.toString(), Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID de la asignatura debe ser un número.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo consultar la asignatura: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void onActionVolverEstudMenu(ActionEvent actionEvent) throws IOException {
        // Ruta del FXML al que quieres volver (ej. selección de login)
        String fxmlPath = "/nTipoLogin.fxml";

        // Cargar la vista
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));

        // Obtener la ventana actual desde el evento
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        // Cambiar la escena
        stage.setScene(new Scene(root));
        stage.show();
    }

}
