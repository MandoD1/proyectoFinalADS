// Archivo: ConsultarHorarioController.java
package aplicacion.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//API


// Importaciones esenciales de JavaFX
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Importaciones de Controles
import javafx.scene.control.Button;
// import javafx.scene.control.TextField; // <-- ESTA SE ELIMINA

// Importación CORREGIDA a la versión de Gluon
import com.gluonhq.charm.glisten.control.TextField;

public class ConsultarHorarioControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverEstudMenu;

    // CAMPOS DE HORARIO (DECLARADOS AHORA COMO GLUON TextField)
    @FXML private TextField idclase7a9;
    @FXML private TextField idclase9a11;
    @FXML private TextField idclase11a1;
    @FXML private TextField idclase1a2;
    @FXML private TextField idclase2a4;
    @FXML private TextField idclase4a6;
    @FXML private TextField idclase6a8;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica: Cargar los datos del horario del estudiante aquí.
    }

    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    @FXML
    public void onActionVolverEstudMenu(ActionEvent event) throws IOException {
        System.out.println("Navegando a: estudiantemenu.fxml");
        Parent root = FXMLLoader.load(getClass().getResource("/estudiantemenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}