package aplicacion.controllers;

import aplicacion.client.BackendClientDirectorCarrera;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import com.gluonhq.charm.glisten.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CrearClaseControllerFX implements Initializable {

    @FXML private TextField idcrearClase;
    @FXML private TextField idProfesor;
    @FXML private TextField idAsignatura;
    @FXML private TextField idHorario; // separar por comas
    @FXML private TextField idCupoMaximo;

    @FXML private Button btnVolverMenuDCarrera;
    @FXML private Button btnCrearClasedpt;

    private final BackendClientDirectorCarrera backend = new BackendClientDirectorCarrera();

    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorcarrera.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void OnActionCrearClasedpt(ActionEvent event) {
        try {
            if (idcrearClase.getText().isEmpty() || idProfesor.getText().isEmpty() || idAsignatura.getText().isEmpty()) {
                mostrarAlerta("Error de Validación", "Los campos ID Clase, Profesor y Asignatura son obligatorios.", AlertType.ERROR);
                return;
            }

            int horas = 2; // ejemplo por defecto
            Long cId = Long.parseLong(idcrearClase.getText().trim());
            Long profesorId = Long.parseLong(idProfesor.getText().trim());
            Long asignaturaId = Long.parseLong(idAsignatura.getText().trim());
            int cupoMaximo = Integer.parseInt(idCupoMaximo.getText().trim());
            List<String> horario = List.of(idHorario.getText().trim().split(","));
            String salon = "S1";
            String semestre = "2025-1";

            backend.crearClase(horas, cId, profesorId, horario, salon, cupoMaximo, semestre, asignaturaId);

            mostrarAlerta("Éxito", "Clase creada correctamente.", AlertType.INFORMATION);
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Campos numéricos no válidos.", AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo crear la clase: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        idcrearClase.setText("");
        idProfesor.setText("");
        idAsignatura.setText("");
        idHorario.setText("");
        idCupoMaximo.setText("");
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
        System.out.println("Controlador CrearClase inicializado.");
    }
}
