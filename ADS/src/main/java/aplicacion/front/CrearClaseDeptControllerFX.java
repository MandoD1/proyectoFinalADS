package aplicacion.front;// Archivo: CrearClaseController.java

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


public class CrearClaseDeptControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverMenuDCarrera;
    @FXML private TextField idCrearClase;
    @FXML private TextField idcupomaxcrearClase;
    @FXML private TextField idClaseCrearProfe; // Corregido el nombre en Java
    @FXML private TextField idclasecrearnombre;
    @FXML private Button btnCrearClasedpt;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del Director de Carrera (DirectorCarreraMenu.fxml).
     */
    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/menudirectorcarrera.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Procesa la creación de la nueva clase.
     */
    @FXML
    public void OnActionCrearClasedpt(ActionEvent event) {
        String idClase = idCrearClase.getText();
        String cupoMaximo = idcupomaxcrearClase.getText();
        String profesorId = idClaseCrearProfe.getText();
        String nombreAsignatura = idclasecrearnombre.getText();

        // Validación básica
        if (idClase.isEmpty() || cupoMaximo.isEmpty() || profesorId.isEmpty() || nombreAsignatura.isEmpty()) {
            System.out.println("ERROR: Todos los campos deben ser completados.");
            // Aquí se debería mostrar una alerta al usuario.
            return;
        }

        System.out.println("Intentando crear clase:");
        System.out.println("  ID: " + idClase);
        System.out.println("  Asignatura: " + nombreAsignatura);
        System.out.println("  Profesor ID: " + profesorId);
        System.out.println("  Cupo Máximo: " + cupoMaximo);

        // Lógica de API/DB para crear la clase
        System.out.println("Clase creada exitosamente.");
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador CrearClase inicializado.");
    }
}