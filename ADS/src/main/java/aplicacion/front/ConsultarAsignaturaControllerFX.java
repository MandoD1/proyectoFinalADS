package aplicacion.front;// Archivo: ConsultarAsignaturaController.java

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//API


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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

// Importación de Gluon Charm Glisten para AutoCompleteTextField
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;


public class ConsultarAsignaturaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    //    NOTA: Los campos de la derecha son AutoCompleteTextField
    // ************************************************

    // Botones y Menús
    @FXML private Button btnVolverAdminMenu;
    @FXML private MenuButton idMenuProfes;
    @FXML private MenuItem idJuanCamilo;
    @FXML private MenuItem idEdgar;
    @FXML private MenuItem idLinares;

    // CAMPOS DE TEXTO (AutoCompleteTextField)
    // ¡Declarados correctamente como AutoCompleteTextField para coincidir con el FXML!
    @FXML private AutoCompleteTextField idHorassemalaesprof;
    @FXML private AutoCompleteTextField idTipoContrato;
    @FXML private AutoCompleteTextField idCalculoNomina;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del Administrador (adminmenu.fxml).
     * @param event El evento de acción del botón.
     */
    @FXML
    public void onActionVolverEstudMenu(ActionEvent event) throws IOException {

        // 1. Carga la vista de destino
        Parent root = FXMLLoader.load(getClass().getResource("/estudiantemenu.fxml"));

        // 2. Obtiene la Stage (ventana) actual y cambia la escena
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Este evento está en el MenuButton, suele ser solo para desplegar el menú
    @FXML
    public void onActionSeleccionarprofe(ActionEvent event) {
        System.out.println("MenuButton de Profesores desplegado.");
    }

    // Lógica para mostrar la información de Juan Camilo Vargas
    @FXML
    public void OnActionInfoTeologia(ActionEvent event) {
        System.out.println("Información de Teologia cargada.");
        // Lógica: Actualizar los campos idHorassemalaesprof, idTipoContrato, etc.
    }

    // Lógica para mostrar la información de Edgar Ruiz
    @FXML
    public void OnActionInfoCalculo(ActionEvent event) {
        System.out.println("Información de Calculo cargada.");
        // Lógica: Actualizar los campos idHorassemalaesprof, idTipoContrato, etc.
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador ConsultarCarga inicializado.");
    }
}