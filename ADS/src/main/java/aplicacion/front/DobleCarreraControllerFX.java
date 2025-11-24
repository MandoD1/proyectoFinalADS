package aplicacion.front;// Archivo: DobleCarreraController.java
// Colócalo en la carpeta raíz del código fuente (ej. src/main/java)

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//API

// Importaciones estándar de JavaFX para navegación y eventos
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Importaciones de Controles de JavaFX
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;


public class DobleCarreraControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverEstudMenu;
    @FXML private MenuButton idMenuProfes;
    @FXML private MenuItem idCarrera1;
    @FXML private MenuItem idCarrera2;
    @FXML private RadioButton idIngSistemas;
    @FXML private RadioButton idArtes;
    @FXML private Button btnInscribir;

    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    @FXML
    public void onActionVolverEstudMenu(ActionEvent event) throws IOException {

        // 1. Carga la vista de destino
        Parent root = FXMLLoader.load(getClass().getResource("/EstudianteMenu.fxml"));

        // 2. Obtiene la Stage (ventana) actual y cambia la escena
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML public void onActionSeleccionarprofe(ActionEvent event) {}
    @FXML public void OnActionCarrera1(ActionEvent event) { /* Lógica de UI */ }
    @FXML public void OnActionCarrera2(ActionEvent event) { /* Lógica de UI */ }
    @FXML public void onActionCarreraing(ActionEvent event) { /* Lógica de UI */ }
    @FXML public void onActionCarreraArtes(ActionEvent event) { /* Lógica de UI */ }

    @FXML
    public void onActionInscribirCarrera(ActionEvent event) {
        System.out.println("Inscripción de carrera(s) procesada.");
    }

    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador DobleCarrera inicializado.");
    }
}