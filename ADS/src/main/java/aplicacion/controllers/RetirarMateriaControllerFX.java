// Archivo: RetirarMateriaController.java
// Colócalo en la carpeta raíz del código fuente (ej. src/main/java)
package aplicacion.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// API y Networking (Mantenemos por si se usa lógica de HTTP más adelante)

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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

// Importación de Gluon Charm Glisten para AutoCompleteTextField
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;


public class RetirarMateriaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverEstudMenu;
    @FXML private MenuButton idClaseRetirar;
    @FXML private MenuItem idSemillero;
    @FXML private MenuItem idItaliano;

    @FXML private Label CodigoClaseRetirar;

    // CAMPOS DE TEXTO
    @FXML private AutoCompleteTextField idProfesorClaseRetirar;
    @FXML private AutoCompleteTextField iddeClaseRetirar;

    @FXML private Button btnRetirarClase;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del Estudiante (EstudianteMenu.fxml).
     */
    @FXML
    public void onActionVolverEstudMenu(ActionEvent event) throws IOException {

        // 1. Carga la vista de destino
        Parent root = FXMLLoader.load(getClass().getResource("/EstudianteMenu.fxml"));

        // 2. Obtiene la Stage (ventana) actual y cambia la escena
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Maneja el despliegue del MenuButton de clases (puede dejarse vacío si solo se usa para desplegar)
    @FXML
    public void onActionClaseRetirar(ActionEvent event) {
        System.out.println("MenuButton de clases desplegado.");
    }

    // Lógica al seleccionar Semillero
    @FXML
    public void OnActionRetiroSemillero(ActionEvent event) {
        System.out.println("Seleccionada clase: Semillero. Llenar campos.");
        // Ejemplo: iddeClaseRetirar.setText("SEM101");
    }

    // Lógica al seleccionar Italiano
    @FXML
    public void OnActionRetiroItaliano(ActionEvent event) {
        System.out.println("Seleccionada clase: Italiano. Llenar campos.");
        // Ejemplo: iddeClaseRetirar.setText("ITA305");
    }

    /**
     * Lógica para procesar el retiro de la clase.
     */
    @FXML
    public void onActionRetirarClase(ActionEvent event) {
        String codigoClase = iddeClaseRetirar.getText();
        System.out.println("Procesando retiro de clase con código: " + codigoClase);
        // Lógica principal: Validar, enviar petición HTTP a la API.
    }

    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador RetirarMateria inicializado.");
    }
}