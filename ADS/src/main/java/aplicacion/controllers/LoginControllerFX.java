package aplicacion.controllers;

import aplicacion.client.BackendClientUsuario;
import aplicacion.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginControllerFX implements Initializable {

    @FXML private TextField idUsuario;
    @FXML private PasswordField idContraseña;
    @FXML private ImageView imgDirectorDpto;

    private final BackendClientUsuario backend = new BackendClientUsuario();

    @FXML
    public void onActionLogin(ActionEvent event) throws IOException {
        String correo = idUsuario.getText().trim();
        String contrasena = idContraseña.getText().trim();

        if(correo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error de Login", "Debe ingresar usuario y contraseña.", Alert.AlertType.ERROR);
            return;
        }

        try {
            Usuario usuario = backend.login(correo, contrasena);

            if(usuario == null) {
                mostrarAlerta("Error de Login", "Usuario o contraseña incorrectos.", Alert.AlertType.ERROR);
                return;
            }

            // Redirigir según el tipo de usuario
            String fxmlPath;
            switch (usuario.getTipoUsuario()) {
                case "DirectorDepartamento":
                    fxmlPath = "/menudirectorDPT.fxml";
                    break;
                case "Estudiante":
                    fxmlPath = "/estudiantemenu.fxml";
                    break;
                case "DirectorCarrera":
                    fxmlPath = "/menudirectorcarrera.fxml";
                    break;
                default:
                    mostrarAlerta("Error de Login", "Tipo de usuario desconocido.", Alert.AlertType.ERROR);
                    return;
            }

            cambiarEscena(event, fxmlPath);

        } catch (Exception e) {
            mostrarAlerta("Error de Conexión", "No se pudo conectar con el servidor backend.\n" + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cambiarEscena(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador Login inicializado.");
        try {
            Image loginImage = new Image(getClass().getResourceAsStream("/img/Login.png"));
            if(imgDirectorDpto != null) imgDirectorDpto.setImage(loginImage);
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen login.png: " + e.getMessage());
        }
    }
}
