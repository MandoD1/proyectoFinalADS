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

    // Botones de Asignatura (Izquierda)
    @FXML private Button btncrearasignaturas;
    @FXML private Button btnireliminarasignaturas;

    // Botones de Clases (Centro)
    @FXML private Button btncrearclases;
    @FXML private Button btnireliminarclases;

    // Botón de Carga (Derecha)
    @FXML private Button btnmodificarcargaprofe;

    // Botón de Salida (Top)
    @FXML private Button btnVolverLogin;


    // ************************************************
    // 2. MÉTODOS DE NAVEGACIÓN (onAction)
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

    /**
     * Regresa a la pantalla de Login o la principal de la aplicación.
     * Corresponde a onAction="#onActionVolverLogin"
     */
    @FXML
    public void onActionVolverLogin(ActionEvent event) throws IOException {
        // Asumiendo que el FXML de Login se llama "Login.fxml"
        cargarEscena(event, "nTipoLogin.fxml");
    }

    // --- Métodos de Asignaturas ---

    /**
     * Navega a la pantalla de Crear Asignaturas.
     * Corresponde a onAction="#onActioncrearasignaturas"
     */
    @FXML
    public void onActioncrearasignaturas(ActionEvent event) throws IOException {
        // Asumiendo el FXML "CrearAsignatura.fxml"
        cargarEscena(event, "crearasignatura.fxml");
    }

    /**
     * Navega a la pantalla de Eliminar Asignaturas.
     * Corresponde a onAction="#onActionireliminarasignaturas"
     */
    @FXML
    public void onActionireliminarasignaturas(ActionEvent event) throws IOException {
        // Asumiendo el FXML "EliminarAsignatura.fxml"
        cargarEscena(event, "eliminarasignatura.fxml");
    }

    // --- Métodos de Clases ---

    /**
     * Navega a la pantalla de Crear Clases.
     * Corresponde a onAction="#onActioncrearclases"
     */
    @FXML
    public void onActioncrearclases(ActionEvent event) throws IOException {
        // Asumiendo el FXML "CrearClase.fxml"
        cargarEscena(event, "CREARCLASE.fxml");
    }

    /**
     * Navega a la pantalla de Eliminar Clases.
     * Corresponde a onAction="#onActionireliminarclases"
     */
    @FXML
    public void onActionireliminarclases(ActionEvent event) throws IOException {
        // Asumiendo el FXML "EliminarClase.fxml"
        cargarEscena(event, "ELIMINARCLASE.fxml");
    }

    // --- Métodos de Carga de Profesor ---

    /**
     * Navega a la pantalla de Modificar Carga de Profesor.
     * Corresponde a onAction="#onActionmodificarcargaprofes"
     */
    @FXML
    public void onActionmodificarcargaprofes(ActionEvent event) throws IOException {
        // Asumiendo el FXML "ModificarCargaAcademica.fxml"
        cargarEscena(event, "modificarcargaprofesor.fxml");
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador MenuGestion inicializado. Navegación lista.");
    }
}