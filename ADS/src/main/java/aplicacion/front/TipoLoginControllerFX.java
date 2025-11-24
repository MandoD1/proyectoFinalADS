package aplicacion.front;// Archivo: TipoLoginController.java
// Colócalo en la carpeta raíz del código fuente (ej. src/main/java)

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

// API (Otras importaciones que usas, aunque no se usan directamente aquí)


public class TipoLoginControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    @FXML private Button btnVolverLogin;
    @FXML private Button btnLoginDdpto;
    @FXML private Button btnLoginEstu;
    @FXML private Button btnLoginDCarrera;

    // Declaraciones para las ImageView
    @FXML private ImageView imgDirectorDpto;
    @FXML private ImageView imgEstudiante;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    @FXML
    public void onActionVolverLogin(ActionEvent event) throws IOException {
        // Asumiendo que esta es la pantalla inicial o previa al login de rol
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionLoginDdpto(ActionEvent event) throws IOException {
        // Director de Departamento DEBE ir al login principal
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionLoginEstu(ActionEvent event) throws IOException {
        // CORREGIDO: Estudiante va al login principal para ingresar credenciales
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionmenudirectordept(ActionEvent event) throws IOException {
        // Este método parece ser un remanente, mantenemos la navegación
        Parent root = FXMLLoader.load(getClass().getResource("/DirectorCarreraMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionLoginDCarrera(ActionEvent event) throws IOException {
        // CORREGIDO: Director de Carrera va al login principal para ingresar credenciales
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    // ************************************************
    // 3. INICIALIZACIÓN (Carga de imágenes)
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador TipoLogin inicializado.");

        // Lógica de Carga de IMAGEN DESDE EL CONTROLADOR
        try {
            // Cargar imagen de Director de Departamento
            Image imgDpto = new Image(getClass().getResourceAsStream("/img/LoginAdmin.png"));
            imgDirectorDpto.setImage(imgDpto);

            // Cargar imagen de Estudiante
            Image imgEst = new Image(getClass().getResourceAsStream("/img/LoginEstud.png"));
            imgEstudiante.setImage(imgEst);

        } catch (Exception e) {
            System.err.println("Error al cargar las imágenes: " + e.getMessage());
            System.err.println("Asegúrese de que los archivos de imagen existan en src/main/resources/img/");
        }
    }
}