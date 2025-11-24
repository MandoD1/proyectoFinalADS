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

// Importaciones de Controles de JavaFX y multimedia
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TipoLoginControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverLogin;
    @FXML private Button btnLoginDdpto;
    @FXML private Button btnLoginEstu;
    @FXML private Button btnLoginDCarrera;
    @FXML private Button btnregistro; // <-- Botón de registro en el CENTER

    // Declaraciones para las ImageView (Requieren fx:id="imgDirectorDpto" y fx:id="imgEstudiante" en el FXML)
    @FXML private ImageView imgDirectorDpto;
    @FXML private ImageView imgEstudiante;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega a la pantalla de Login Principal (o la pantalla anterior a esta selección de rol).
     * Corresponde a onAction="#onActionVolverLogin"
     */
    @FXML
    public void onActionVolverLogin(ActionEvent event) throws IOException {
        // Asumiendo que esta pantalla regresa al Login principal (Login.fxml)
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla de Login para Director de Departamento.
     * Corresponde a onAction="#onActionLoginDdpto"
     */
    @FXML
    public void onActionLoginDdpto(ActionEvent event) throws IOException {
        // Asume que el login de credenciales es "Login.fxml"
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla de Login para Estudiante.
     * Corresponde a onAction="#onActionLoginEstu"
     */
    @FXML
    public void onActionLoginEstu(ActionEvent event) throws IOException {
        // Asume que el login de credenciales es "Login.fxml"
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla de Login para Director de Carrera.
     * Corresponde a onAction="#onActionLoginDCarrera"
     */
    @FXML
    public void onActionLoginDCarrera(ActionEvent event) throws IOException {
        // Asume que el login de credenciales es "Login.fxml"
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Navega a la pantalla de Registro.
     * Corresponde a onAction="#onActionregistro"
     */
    @FXML
    public void onActionregistro(ActionEvent event) throws IOException {
        // Asume que la pantalla de registro se llama "Registro.fxml"
        Parent root = FXMLLoader.load(getClass().getResource("/Registro.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // NOTA: Se eliminó el método onActionmenudirectordept ya que no tiene un botón directo en el FXML.


    // ************************************************
    // 3. INICIALIZACIÓN (Carga de imágenes)
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador TipoLogin inicializado.");

        // Lógica de Carga de IMAGEN DESDE EL CONTROLADOR
        // ESTO SOLO FUNCIONARÁ SI HAS CORREGIDO EL FXML AÑADIENDO fx:id="imgDirectorDpto" y fx:id="imgEstudiante"
        try {
            // Cargar imagen de Director de Departamento
            Image imgDpto = new Image(getClass().getResourceAsStream("/img/LoginAdmin.png"));
            if (imgDirectorDpto != null && imgDpto.getWidth() > 0) {
                imgDirectorDpto.setImage(imgDpto);
            }

            // Cargar imagen de Estudiante
            Image imgEst = new Image(getClass().getResourceAsStream("/img/LoginEstud.png"));
            if (imgEstudiante != null && imgEst.getWidth() > 0) {
                imgEstudiante.setImage(imgEst);
            }

        } catch (Exception e) {
            System.err.println("Error al cargar las imágenes. Verifique la ruta src/main/resources/img/: " + e.getMessage());
        }
    }
}