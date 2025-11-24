package aplicacion.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aplicacion.client.BackendClientDirectorDepartamento;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class GenerarInformeProfesoresControllerFX implements Initializable {

    @FXML private Button btnVolverMenuDPTO;
    @FXML private Button btnGenerarinformedeprofesores;

    private BackendClientDirectorDepartamento backend = new BackendClientDirectorDepartamento();

    @FXML
    public void onActionVolverMenuDPTO(ActionEvent event) throws IOException {
        // Navegación se mantiene como en tu proyecto
    }

    @FXML
    public void OnActionGenerarInformdeprofesores(ActionEvent event) {
        backend.generarInformeProfesores(); // Se usa cliente tal como lo tienes
        mostrarAlerta("Informe Generado", "El informe de profesores se generó con éxito.", AlertType.INFORMATION);
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador GenerarInformeProfesores inicializado.");
    }
}
