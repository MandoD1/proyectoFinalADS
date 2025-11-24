package aplicacion.front;// Archivo: AgregarClaseController.java

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//API


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
import javafx.scene.control.MenuButton; // Nuevo
import javafx.scene.control.MenuItem; // Nuevo
import javafx.scene.control.Label; // Nuevo (usado para idClase)

// Importaciones de Gluon Charm Glisten (Componentes avanzados)
import com.gluonhq.charm.glisten.control.AutoCompleteTextField; // ¡Esta es la más importante!


public class AgregarClaseControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Botones y Menús
    @FXML private Button btnVolverEstudMenu;
    @FXML private MenuButton idMenuProfes;
    @FXML private MenuItem idTeologia;
    @FXML private MenuItem idCalculo;
    @FXML private Button btnAgregarClase;

    // Campos de Texto (AutoCompleteTextField de Gluon)
    @FXML private AutoCompleteTextField idProfesorClase;
    @FXML private AutoCompleteTextField idCupoMax;
    @FXML private AutoCompleteTextField idCalculoNomina;

    // Labels
    @FXML private Label idClase; // El fx:id del Label "ID:"


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del estudiante (estudiantemenu.fxml).
     * Nota: Asume que el archivo estudiantemenu.fxml existe en la raíz de resources.
     */
    @FXML
    public void onActionVolverEstudMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/estudiantemenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Este evento está en el MenuButton, aunque la lógica suele ir en los MenuItems.
    @FXML
    public void onActionSeleccionarprofe(ActionEvent event) {
        System.out.println("MenuButton de Profesores abierto.");
    }

    @FXML
    public void OnActionInfoTeologia(ActionEvent event) {
        System.out.println("Opción Teología seleccionada.");
        // Lógica: Mostrar info de Teología
    }

    @FXML
    public void OnActionInfoCalculo(ActionEvent event) {
        System.out.println("Opción Cálculo seleccionada.");
        // Lógica: Mostrar info de Cálculo
    }

    @FXML
    public void onActionAgregarClase(ActionEvent event) {
        System.out.println("Botón Agregar Clase presionado.");
        // Lógica para procesar y agregar la clase
    }

    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de inicialización (ej. llenar listas de AutoCompleteTextField)
        System.out.println("Controlador AgregarClase inicializado.");
    }
}