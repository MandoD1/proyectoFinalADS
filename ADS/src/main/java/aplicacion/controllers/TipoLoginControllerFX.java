package aplicacion.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TipoLoginControllerFX implements Initializable {

    @FXML private Button btnVolverLogin, btnLoginDdpto, btnLoginEstu, btnLoginDCarrera, btnregistro;
    @FXML private ImageView imgDirectorDpto, imgEstudiante;

    @FXML
    public void onActionVolverLogin(ActionEvent event) throws IOException { cambiarEscena(event, "/Login.fxml"); }
    @FXML
    public void onActionLoginDdpto(ActionEvent event) throws IOException { cambiarEscena(event, "/Login.fxml"); }
    @FXML
    public void onActionLoginEstu(ActionEvent event) throws IOException { cambiarEscena(event, "/Login.fxml"); }
    @FXML
    public void onActionLoginDCarrera(ActionEvent event) throws IOException { cambiarEscena(event, "/Login.fxml"); }
    @FXML
    public void onActionregistro(ActionEvent event) throws IOException { cambiarEscena(event, "/Registro.fxml"); }

    private void cambiarEscena(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador TipoLogin inicializado.");
        try {
            if(imgDirectorDpto != null)
                imgDirectorDpto.setImage(new Image(getClass().getResourceAsStream("/img/LoginAdmin.png")));
            if(imgEstudiante != null)
                imgEstudiante.setImage(new Image(getClass().getResourceAsStream("/img/LoginEstud.png")));
        } catch(Exception e) {
            System.err.println("Error cargando imágenes: " + e.getMessage());
        }
    }
}
