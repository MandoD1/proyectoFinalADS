// Archivo: ModificarAsignaturasController.java
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class GenerarReporteAsignaturaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverMenuDCarrera;
    @FXML private TextField idAsignaturaAModificar;
    @FXML private Button btnBuscarAsignatura;

    @FXML private Label lblNombreActual;
    @FXML private TextField txtNuevoNombre;
    @FXML private Button btnModificar;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del Director de Carrera (DirectorCarreraMenu.fxml).
     */
    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/menudirectordept.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Busca la asignatura por ID y carga la información en los campos de modificación.
     */
    @FXML
    public void onActionBuscarAsignatura(ActionEvent event) {
        String asignaturaId = idAsignaturaAModificar.getText();

        if (asignaturaId == null || asignaturaId.trim().isEmpty()) {
            lblNombreActual.setText("ERROR: Ingrese un ID.");
            return;
        }

        System.out.println("Buscando asignatura ID: " + asignaturaId);

        // --- Lógica de Simulación de Carga de Datos ---
        if (asignaturaId.equals("CS101")) {
            lblNombreActual.setText("Nombre Actual: Programación I");
            txtNuevoNombre.setText("Programación I");
        } else {
            lblNombreActual.setText("Asignatura no encontrada.");
            txtNuevoNombre.setText("");
        }
    }

    /**
     * Aplica la modificación del nombre de la asignatura.
     */
    @FXML
    public void onActionModificarAsignatura(ActionEvent event) {
        String asignaturaId = idAsignaturaAModificar.getText();
        String nuevoNombre = txtNuevoNombre.getText();

        if (asignaturaId == null || asignaturaId.trim().isEmpty() || nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            lblNombreActual.setText("ERROR: Falta ID o nuevo nombre.");
            return;
        }

        System.out.println("Modificando Asignatura " + asignaturaId + " a " + nuevoNombre);
        // Lógica de API/DB para la modificación
        lblNombreActual.setText("¡Modificación exitosa de " + asignaturaId + "!");
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador ModificarAsignaturas inicializado.");
        lblNombreActual.setText("Esperando ID de asignatura...");
        txtNuevoNombre.setPromptText("Nuevo Nombre de la asignatura");
    }
}