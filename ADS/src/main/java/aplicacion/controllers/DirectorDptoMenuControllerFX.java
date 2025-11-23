// Archivo: DirectorDptoMenuController.java
package aplicacion.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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


public class DirectorDptoMenuControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolvertipoLogin;
    @FXML private Button btnConsultarCarga;
    @FXML private Button btnModificarclasesprofe;
    @FXML private Button btnConsultarprofes;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta a la pantalla de selección de tipo de Login (TipoLogin.fxml).
     */
    @FXML
    public void onActionVolvertipoLogin(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/nTipoLogin.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla de Consulta de Carga Académica de Profesor.
     * (Asumido: ConsultarCargaAcademica.fxml)
     */
    @FXML
    public void onActionConsultarCarga(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/nuevoconsultarcarga.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla para Consultar Profesores (información general).
     * (Asumido: ConsultarProfesor.fxml)
     */
    @FXML
    public void onActionConsultarprofes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/nuevoconsultarprofe.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla para Modificar las clases asignadas a un profesor.
     * (Asumido: ModificarClasesProfesor.fxml)
     */
    @FXML
    public void onActionModificarclasesprofe(ActionEvent event) throws IOException {
        // Debes crear este FXML si no existe, o usar el que corresponda
        Parent root = FXMLLoader.load(getClass().getResource("/modificarclasesaprofe.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador DirectorDptoMenu inicializado.");
    }
}