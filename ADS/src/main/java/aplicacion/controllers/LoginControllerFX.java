package aplicacion.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image; // Importación necesaria
import javafx.scene.image.ImageView; // Importación necesaria
import javafx.stage.Stage;


public class LoginControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************
    @FXML private TextField idUsuario;
    @FXML private PasswordField idContraseña;

    // Asumiendo que esta es la única ImageView que usará la imagen login.png.
    @FXML private ImageView imgDirectorDpto;

    // ************************************************
    // 2. MÉTODOS DE AUTENTICACIÓN Y NAVEGACIÓN
    // ************************************************

    /**
     * Muestra una alerta de error en la UI.
     */
    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Maneja el evento de click en el botón de Login.
     */
    @FXML
    public void onActionLogin(ActionEvent event) throws IOException {
        String usuario = idUsuario.getText().trim();
        String password = idContraseña.getText().trim();

        // 1. Autenticación DIRECTOR DE DEPARTAMENTO (depa/123)
        if (usuario.equals("depa") && password.equals("123")) {
            System.out.println("Login DIRECTOR DE DEPARTAMENTO exitoso.");

            // RUTA CORREGIDA: Se usa el nombre real del archivo FXML (menudirectordept.fxml)
            String fxmlPath = "/menudirectorDPT.fxml";
            URL fxmlUrl = getClass().getResource(fxmlPath);

            if (fxmlUrl == null) {
                // IMPRIME EN CONSOLA la ruta faltante
                System.err.println("ERROR FATAL: El archivo FXML '" + fxmlPath + "' NO SE ENCUENTRA. URL devuelta: null");

                // Muestra el mensaje de error en la UI
                mostrarAlertaError("Error de Navegación", "No se pudo cargar la vista. Verifique que el archivo FXML '" + fxmlPath + "' esté en la raíz de su carpeta de recursos.");
                return; // Detiene la ejecución para evitar el NullPointerException
            }

            Parent root = FXMLLoader.load(fxmlUrl);
            cambiarEscena(event, root);

            // 2. Autenticación ESTUDIANTE (estu/456)
        } else if (usuario.equals("estu") && password.equals("456")) {
            System.out.println("Login ESTUDIANTE exitoso.");
            // Agregando el mismo chequeo robusto para el estudiante
            String fxmlPath = "/estudiantemenu.fxml";
            URL fxmlUrl = getClass().getResource(fxmlPath);
            if (fxmlUrl == null) {
                System.err.println("ERROR FATAL: El archivo FXML '" + fxmlPath + "' NO SE ENCUENTRA. URL devuelta: null");
                mostrarAlertaError("Error de Navegación", "No se pudo cargar la vista. Verifique que '" + fxmlPath + "' esté en la raíz de su carpeta de recursos.");
                return;
            }
            Parent root = FXMLLoader.load(fxmlUrl);
            cambiarEscena(event, root);

            // 3. Autenticación DIRECTOR DE CARRERA (dcar/789)
        } else if (usuario.equals("dcar") && password.equals("789")) {
            System.out.println("Login DIRECTOR DE CARRERA exitoso.");
            // Agregando el mismo chequeo robusto para el director de carrera
            String fxmlPath = "/menudirectorcarrera.fxml";
            URL fxmlUrl = getClass().getResource(fxmlPath);
            if (fxmlUrl == null) {
                System.err.println("ERROR FATAL: El archivo FXML '" + fxmlPath + "' NO SE ENCUENTRA. URL devuelta: null");
                mostrarAlertaError("Error de Navegación", "No se pudo cargar la vista. Verifique que '" + fxmlPath + "' esté en la raíz de su carpeta de recursos.");
                return;
            }
            Parent root = FXMLLoader.load(fxmlUrl);
            cambiarEscena(event, root);

            // Credenciales Incorrectas
        } else {
            mostrarAlertaError("Error de Login", "Verifique su usuario y/o contraseña.");
        }
    }

    /**
     * Método auxiliar para cambiar de escena.
     */
    private void cambiarEscena(ActionEvent event, Parent root) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador Login inicializado.");

        // Lógica de Carga de IMAGEN DESDE EL CONTROLADOR
        try {
            // Cargar imagen de fondo única: login.png desde la raíz de recursos
            Image loginImage = new Image(getClass().getResourceAsStream("/img/Login.png"));

            // Se verifica que la ImageView se haya inyectado correctamente desde el FXML
            if (imgDirectorDpto != null) {
                imgDirectorDpto.setImage(loginImage);
            } else {
                System.err.println("Advertencia: imgDirectorDpto no fue inyectado desde el FXML. Asegúrese de que el fx:id coincida.");
            }


        } catch (Exception e) {
            System.err.println("Error al cargar la imagen login.png: " + e.getMessage());
            System.err.println("Asegúrese de que el archivo 'login.png' exista directamente en la raíz de su carpeta de recursos y que el fx:id sea correcto en el FXML.");
        }
    }
}