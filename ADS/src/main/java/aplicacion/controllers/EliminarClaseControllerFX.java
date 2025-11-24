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
import java.util.ResourceBundle;

public class EliminarClaseControllerFX implements Initializable {

    @FXML private TextField ideliminarClase;
    @FXML private Button btnVolverMenuDCarrera;
    @FXML private Button btneliminarClasedpt;

    private final BackendClientDirectorCarrera backend = new BackendClientDirectorCarrera();

    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorcarrera.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void OnActioneliminarClasedpt(ActionEvent event) {
        try {
            if (ideliminarClase.getText().isEmpty()) {
                mostrarAlerta("Error", "Digite el ID de la clase a eliminar.", AlertType.ERROR);
                return;
            }
            Long cId = Long.parseLong(ideliminarClase.getText().trim());
            backend.eliminarClase(cId);
            mostrarAlerta("Éxito", "Clase eliminada correctamente.", AlertType.INFORMATION);
            limpiarCampos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "ID de clase no válido.", AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo eliminar la clase: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        ideliminarClase.setText("");
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
        System.out.println("Controlador EliminarClase inicializado.");
    }
}
