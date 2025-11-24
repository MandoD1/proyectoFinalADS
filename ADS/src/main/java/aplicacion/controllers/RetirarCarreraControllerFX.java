package aplicacion.controllers;

import aplicacion.client.BackendClientEstudiante;
import aplicacion.repository.UsuarioRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class RetirarCarreraControllerFX implements Initializable {

    @FXML private TextField idEstudianteRetirarCarrera;
    @FXML private TextField idCarreraRetirar;
    @FXML private Button btnVolverEstudMenu;
    @FXML private Button btnRetirarCarrera;

    private BackendClientEstudiante backend;
    private UsuarioRepository usuarioRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador RetirarCarrera inicializado.");
        usuarioRepository = new UsuarioRepository();
        backend = new BackendClientEstudiante(usuarioRepository);
    }

    @FXML
    public void OnActionretiroCarrera(ActionEvent event) {
        String idEstudiante = idEstudianteRetirarCarrera.getText().trim();
        String idCarrera = idCarreraRetirar.getText().trim();

        if (idEstudiante.isEmpty() || idCarrera.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar ID de estudiante y carrera.", Alert.AlertType.ERROR);
            return;
        }

        try {
            backend.retirarCarrera(Long.valueOf(idEstudiante), Long.valueOf(idCarrera));
            mostrarAlerta("Éxito", "Carrera retirada correctamente.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo retirar la carrera: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        idEstudianteRetirarCarrera.setText("");
        idCarreraRetirar.setText("");
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
