// Archivo: ModificarClaseDptoController.java
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


public class ModificarClaseDptoControllerFX implements Initializable {


    @FXML private Button btnVolverAdminMenu;

    // Campo de ID de la clase
    @FXML private TextField idClaseAModificar; // Corregido el nombre para mayor claridad

    // Campos de modificación
    @FXML private TextField idmodificarclasehorario;
    @FXML private TextField modificarclasecupo;

    // Botones de acción
    @FXML private Button btnBuscarClase;
    @FXML private Button btnAñadirclasedpt;
    @FXML private Button btnEliminarclasedpt;
    @FXML private Button btnModificarClasedpt;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del Administrador (AdminMenu.fxml).
     */
    @FXML
    public void onActionVolverAdminMenu(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/menudirectordept.fxml"));

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Busca la información de la clase por ID y la carga en los campos de modificación.
     * Se llama al presionar Enter en el TextField o al presionar "Buscar Clase".
     */
    @FXML
    public void onActionBuscarClase(ActionEvent event) {
        String claseId = idClaseAModificar.getText();

        if (claseId == null || claseId.trim().isEmpty()) {
            System.out.println("ADVERTENCIA: Debe ingresar el ID de la clase a buscar.");
            return;
        }

        System.out.println("Buscando información para la clase ID: " + claseId);

        // --- Lógica de Simulación de Carga de Datos ---
        if (claseId.equals("CS101")) {
            idmodificarclasehorario.setText("Lun/Mie 14:00 - 16:00, Aula 301");
            modificarclasecupo.setText("35");
        } else {
            idmodificarclasehorario.setText("");
            modificarclasecupo.setText("");
            System.out.println("Clase no encontrada.");
        }
    }

    /**
     * Añade una nueva clase utilizando los datos de los campos.
     */
    @FXML
    public void OnActionAñadirclasedpt(ActionEvent event) {
        String claseId = idClaseAModificar.getText();
        String horario = idmodificarclasehorario.getText();
        String cupo = modificarclasecupo.getText();

        System.out.println("Añadiendo nueva clase: ID=" + claseId + ", Horario=" + horario + ", Cupo=" + cupo);
        // Lógica: Validación y llamada a la API/DB para crear.
    }

    /**
     * Elimina la clase identificada por el ID.
     */
    @FXML
    public void OnActionEliminarclasedpt(ActionEvent event) {
        String claseId = idClaseAModificar.getText();

        System.out.println("Eliminando clase con ID: " + claseId);
        // Lógica: Confirmación y llamada a la API/DB para eliminar.
    }

    /**
     * Modifica el horario y/o cupo de la clase ya cargada.
     */
    @FXML
    public void OnActionModificarClasedpt(ActionEvent event) {
        String claseId = idClaseAModificar.getText();
        String nuevoHorario = idmodificarclasehorario.getText();
        String nuevoCupo = modificarclasecupo.getText();

        System.out.println("Modificando clase ID: " + claseId +
                " -> Horario: " + nuevoHorario +
                ", Cupo: " + nuevoCupo);
        // Lógica: Validación y llamada a la API/DB para actualizar.
    }

    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador ModificarClaseDpto inicializado.");
    }
}