package aplicacion.controllers;

import aplicacion.client.BackendClientEstudiante;
import aplicacion.repository.UsuarioRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import java.net.URL;
import java.util.ResourceBundle;

public class AgregarClaseHorarioControllerFX implements Initializable {

    @FXML private AutoCompleteTextField idclaseagregarahorario;
    @FXML private Button btnVolverEstudMenu;
    @FXML private Button btnAgregarClase;

    private BackendClientEstudiante backend;
    private UsuarioRepository usuarioRepository;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador AgregarClaseHorario inicializado.");
        usuarioRepository = new UsuarioRepository();
        backend = new BackendClientEstudiante(usuarioRepository);
    }

    @FXML
    public void onActionAgregarClase(ActionEvent event) {
        String idClase = idclaseagregarahorario.getText().trim();
        if (idClase.isEmpty()) {
            mostrarAlerta("Error de Validación", "Ingrese el ID de la clase.", Alert.AlertType.ERROR);
            return;
        }

        try {
            // Llamada real al backend
            backend.asignarClase(Long.valueOf(idClase));
            mostrarAlerta("Éxito", "Clase añadida exitosamente al horario.", Alert.AlertType.INFORMATION);
            limpiarCampos();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo agregar la clase: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        idclaseagregarahorario.setText("");
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
