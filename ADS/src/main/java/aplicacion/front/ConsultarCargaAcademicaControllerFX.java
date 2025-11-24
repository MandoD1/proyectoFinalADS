// Archivo: ConsultarCargaAcademicaController.java

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


public class ConsultarCargaAcademicaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverAdminMenu;
    @FXML private TextField idprofesorCargaacademica;
    @FXML private Button btnConsultarCargaAcademica;

    // Asumo que tienes Labels en el centro para mostrar los resultados (ver FXML corregido)
    @FXML private Label lblMateria1;
    @FXML private Label lblMateria2;


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
     * Captura el evento cuando se presiona Enter en el TextField.
     * Aunque es menos común, mantenemos la función definida en el FXML original.
     */
    @FXML
    public void onActionidcargaprofe(ActionEvent event) {
        // Llama a la lógica principal de consulta
        onActionConsultarCargaAcademica(event);
    }

    /**
     * Inicia la lógica para consultar la carga académica del profesor.
     */
    @FXML
    public void onActionConsultarCargaAcademica(ActionEvent event) {
        String profesorId = idprofesorCargaacademica.getText();

        if (profesorId == null || profesorId.trim().isEmpty()) {
            System.out.println("ADVERTENCIA: Debe ingresar un ID de profesor.");
            return;
        }

        System.out.println("Consultando carga académica para el ID: " + profesorId);

        // --- Lógica de Simulación de Carga de Datos ---
        if (profesorId.equals("1002")) {
            lblMateria1.setText("Materia 1: Cálculo Diferencial (Lunes, 8:00)");
            lblMateria2.setText("Materia 2: Álgebra Lineal (Miércoles, 10:00)");
        } else {
            lblMateria1.setText("Profesor no encontrado o sin carga académica asignada.");
            lblMateria2.setText("");
        }
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador ConsultarCargaAcademica inicializado.");
        lblMateria1.setText("");
        lblMateria2.setText("");
    }
}