// Archivo: CrearAsignaturaController.java
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


public class CrearAsignaturaDeptControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverMenuDCarrera;
    @FXML private TextField idasignaturacrear;
    @FXML private TextField idcupomaxcrearasignatura; // Posiblemente se usa si se crea la primera clase de la asignatura
    @FXML private TextField idasignaturacrearprofe;
    @FXML private TextField idasignaturacrearnombre;
    @FXML private Button btnCrearAasigntura;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del Director de Carrera (DirectorCarreraMenu.fxml).
     */
    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {

        // Asumo que vuelve al menú del Director de Carrera
        Parent root = FXMLLoader.load(getClass().getResource("/menudirectorcarrera.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Procesa la creación de la nueva asignatura.
     */
    @FXML
    public void OnActionCrearAsignatura(ActionEvent event) {
        String idAsignatura = idasignaturacrear.getText();
        String cupoMaximo = idcupomaxcrearasignatura.getText();
        String profesor = idasignaturacrearprofe.getText();
        String nombreAsignatura = idasignaturacrearnombre.getText();

        // Validación básica
        if (idAsignatura.isEmpty() || nombreAsignatura.isEmpty()) {
            System.out.println("ERROR: El ID y el Nombre de la asignatura son obligatorios.");
            // Aquí se debería mostrar una alerta al usuario.
            return;
        }

        System.out.println("Intentando crear Asignatura:");
        System.out.println("  ID: " + idAsignatura);
        System.out.println("  Nombre: " + nombreAsignatura);

        // Los otros campos podrían ser opcionales o para crear una CLASE inicial junto a la Asignatura
        if (!profesor.isEmpty()) {
            System.out.println("  Profesor Inicial: " + profesor);
        }
        if (!cupoMaximo.isEmpty()) {
            System.out.println("  Cupo Máximo Inicial: " + cupoMaximo);
        }

        // Lógica de API/DB para crear la asignatura
        System.out.println("Asignatura " + idAsignatura + " creada exitosamente.");
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador CrearAsignatura inicializado.");
    }
}