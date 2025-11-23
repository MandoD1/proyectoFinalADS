// Archivo: ConsultarProfesorController.java
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
import javafx.scene.control.TextField;
// Otras importaciones de API que puedas necesitar:


public class ConsultarProfesorControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverAdminMenu;
    @FXML private TextField idprofesorConsultar;
    @FXML private Button btnConsultarProfesor;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del Administrador (AdminMenu.fxml).
     */
    @FXML
    public void onActionVolverAdminMenu(ActionEvent event) throws IOException {

        // 1. Carga la vista de destino
        Parent root = FXMLLoader.load(getClass().getResource("/menudirectordept.fxml"));

        // 2. Obtiene la Stage (ventana) actual y cambia la escena
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Inicia la lógica para consultar la información del profesor basado en el ID.
     */
    @FXML
    public void onActionConsultarProfesor(ActionEvent event) {
        String profesorId = idprofesorConsultar.getText();

        if (profesorId == null || profesorId.trim().isEmpty()) {
            System.out.println("ADVERTENCIA: Debe ingresar un ID de profesor.");
            // Lógica para mostrar una alerta al usuario
            return;
        }

        System.out.println("Consultando información para el ID de profesor: " + profesorId);
        // Lógica: Realizar llamada a API/Base de Datos para obtener la información
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador ConsultarProfesor inicializado.");
    }
}