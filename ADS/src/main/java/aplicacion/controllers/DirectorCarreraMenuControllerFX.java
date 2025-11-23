package aplicacion.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Importaciones estándar de JavaFX
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


public class DirectorCarreraMenuControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // Se sincronizan con los ID del FXML
    // ************************************************
    @FXML private Button btnVolverLogin;
    @FXML private Button btncrearclases;        // Coincide con fx:id="btncrearclases"
    @FXML private Button btncrearasignaturas;   // Coincide con fx:id="btncrearasignaturas"
    @FXML private Button btnGenerarReporte;     // Coincide con fx:id="btnGenerarReporte"
    // NOTA: Se eliminaron btnModificarAsignaturas y btnModificarClases porque no estaban en el FXML.


    // ************************************************
    // 2. MÉTODOS DE NAVEGACIÓN (onAction)
    // Se sincronizan con los onAction del FXML
    // ************************************************

    /**
     * Vuelve a la pantalla principal de Login (nTipoLogin.fxml).
     */
    @FXML
    public void onActionVolverLogin(ActionEvent event) throws IOException {

        // Vuelve a la pantalla de Login (nTipoLogin.fxml)
        Parent root = FXMLLoader.load(getClass().getResource("/nTipoLogin.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla de Creación de Asignaturas.
     * Corregido para coincidir con onAction="#onActioncrearasignaturas".
     */
    @FXML
    public void onActioncrearasignaturas(ActionEvent event) throws IOException {
        // NOTA: Asumo que el FXML para esta acción es /CrearAsignaturas.fxml
        Parent root = FXMLLoader.load(getClass().getResource("/CrearasignaturaDdpt.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla de Creación de Clases.
     * Corregido para coincidir con onAction="#onActioncrearclases".
     */
    @FXML
    public void onActioncrearclases(ActionEvent event) throws IOException {
        // NOTA: Asumo que el FXML para esta acción es /CrearClases.fxml
        Parent root = FXMLLoader.load(getClass().getResource("/CrearclaseDpt.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla de Generación de Reportes.
     */
    @FXML
    public void onActionGenerarReporte(ActionEvent event) throws IOException {
        // NOTA: Asumo que el FXML para esta acción es /GenerarReporte.fxml
        Parent root = FXMLLoader.load(getClass().getResource("/generarreportedeestudiantes.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // NOTA: Se eliminaron los métodos onActionmodificarasignaturas y onActionmodificarclases
    // porque no estaban referenciados en el FXML proporcionado.


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador DirectorCarreraMenu inicializado.");
    }
}