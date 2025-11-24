package aplicacion.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuDirectorCarreraControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Botones de Asignatura
    @FXML private Button btncrearasignaturas;
    @FXML private Button btnireliminarasignaturas;

    // Botones de Clases
    @FXML private Button btncrearclases;
    @FXML private Button btnireliminarclases;

    // Botón de Carga
    @FXML private Button btnmodificarcargaprofe;

    // Botón de Salida
    @FXML private Button btnVolverLogin;


    // ************************************************
    // 2. MÉTODOS DE NAVEGACIÓN
    // ************************************************

    /**
     * Método auxiliar para cargar una nueva escena (FXML)
     */
    private void cargarEscena(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlPath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // --- Login ---
    @FXML
    public void onActionVolverLogin(ActionEvent event) throws IOException {
        cargarEscena(event, "nTipoLogin.fxml");
    }

    // --- Asignaturas ---
    @FXML
    public void onActioncrearasignaturas(ActionEvent event) throws IOException {
        cargarEscena(event, "crearasignatura.fxml");
    }

    @FXML
    public void onActionireliminarasignaturas(ActionEvent event) throws IOException {
        cargarEscena(event, "eliminarasignatura.fxml");
    }

    // --- Clases ---
    @FXML
    public void onActioncrearclases(ActionEvent event) throws IOException {
        cargarEscena(event, "CREARCLASE.fxml");
    }

    @FXML
    public void onActionireliminarclases(ActionEvent event) throws IOException {
        cargarEscena(event, "ELIMINARCLASE.fxml");
    }

    // --- Carga de Profesor ---
    @FXML
    public void onActionmodificarcargaprofe(ActionEvent event) throws IOException {
        cargarEscena(event, "modificarcargaprofesor.fxml");
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador MenuDirectorCarrera inicializado. Navegación lista.");
    }

}
