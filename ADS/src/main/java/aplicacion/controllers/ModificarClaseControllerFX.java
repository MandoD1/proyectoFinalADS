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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ModificarClaseControllerFX implements Initializable {

    @FXML private TextField idclasemodificar;
    @FXML private TextField idprofesorCargaacademica1;
    @FXML private TextField idprofesormodificarclase;
    @FXML private TextField idmodificarclasehorario;
    @FXML private TextField modificarclasecupo;
    @FXML private TextField modificarclasecupomax;
    @FXML private TextField idmodificarclaseidasignatura;

    @FXML private Button btnVolverMenuDCarrera;
    @FXML private Button btnModificarClasedpt;

    private final BackendClientDirectorCarrera backend = new BackendClientDirectorCarrera();

    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorcarrera.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void OnActionModificarClasedpt(ActionEvent event) {
        try {
            if (idclasemodificar.getText().isEmpty() || idprofesormodificarclase.getText().isEmpty()) {
                mostrarAlerta("Error", "ID Clase y ID Profesor son obligatorios.", AlertType.ERROR);
                return;
            }

            Long cId = Long.parseLong(idclasemodificar.getText().trim());
            Long nuevoId = Long.parseLong(idprofesorCargaacademica1.getText().trim());
            Long profesorId = Long.parseLong(idprofesormodificarclase.getText().trim());
            int cupoActual = Integer.parseInt(modificarclasecupo.getText().trim());
            int cupoMax = Integer.parseInt(modificarclasecupomax.getText().trim());
            Long asignaturaId = Long.parseLong(idmodificarclaseidasignatura.getText().trim());
            List<String> horario = List.of(idmodificarclasehorario.getText().trim().split(","));
            int horas = horario.size();
            String salon = "S1";
            String semestre = "2025-1";
            List<Long> estudiantes = List.of();

            backend.modificarClase(horas, cId, nuevoId, profesorId, horario, salon, cupoMax, cupoActual, semestre, asignaturaId, estudiantes);

            mostrarAlerta("Éxito", "Clase modificada correctamente.", AlertType.INFORMATION);
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Campos numéricos no válidos.", AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo modificar la clase: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        idclasemodificar.setText("");
        idprofesorCargaacademica1.setText("");
        idprofesormodificarclase.setText("");
        idmodificarclasehorario.setText("");
        modificarclasecupo.setText("");
        modificarclasecupomax.setText("");
        idmodificarclaseidasignatura.setText("");
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
        System.out.println("Controlador ModificarClase inicializado.");
    }


}
