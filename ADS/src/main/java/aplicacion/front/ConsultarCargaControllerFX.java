package aplicacion.front;// Archivo: ConsultarCargaProfesorController.java

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
// Importación de Gluon Charm Glisten para AutoCompleteTextField
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;


public class ConsultarCargaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverAdminMenu;
    @FXML private TextField idprofesorConsultar;
    @FXML private Button btnConsultarCarga;

    // AutoCompleteTextFields para mostrar resultados
    @FXML private AutoCompleteTextField idHorassemalaesprof;
    @FXML private AutoCompleteTextField idTipoContrato;
    @FXML private AutoCompleteTextField idCalculoNomina;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del Administrador (AdminMenu.fxml).
     */
    @FXML
    public void onActionVolverAdminMenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/AdminMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Inicia la lógica para consultar la carga y nómina del profesor.
     */
    @FXML
    public void onActionConsultarCarga(ActionEvent event) {
        String profesorId = idprofesorConsultar.getText();

        if (profesorId == null || profesorId.trim().isEmpty()) {
            System.out.println("ADVERTENCIA: Debe ingresar un ID de profesor.");
            return;
        }

        System.out.println("Consultando carga y nómina para el ID: " + profesorId);

        // --- Lógica de Simulación de Carga de Datos ---
        // Aquí iría la llamada a la API o base de datos.

        if (profesorId.equals("1001")) {
            idHorassemalaesprof.setText("40");
            idTipoContrato.setText("Término indefinido");
            idCalculoNomina.setText("$5.500.000 COP");
        } else {
            idHorassemalaesprof.setText("Profesor no encontrado");
            idTipoContrato.setText("N/A");
            idCalculoNomina.setText("N/A");
        }
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador ConsultarCargaProfesor inicializado.");

        //Usamos setDisable(true) ya que AutoCompleteTextField no tiene setEditable().
        idHorassemalaesprof.setDisable(true);
        idTipoContrato.setDisable(true);
        idCalculoNomina.setDisable(true);
    }
}