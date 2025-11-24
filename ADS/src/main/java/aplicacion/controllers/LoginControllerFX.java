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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginControllerFX implements Initializable {

    // ************************************************
    // Variables inyectadas (fx:id)
    // ************************************************
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContraseña;
    @FXML private Button btnLogin;
    @FXML private Button btnVolver;

    private final BackendClientUsuario backendClientUsuario = new BackendClientUsuario();

    // ************************************************
    // Método de inicialización
    // ************************************************
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador Login inicializado.");
    }

    // ************************************************
    // Acción para el login
    // ************************************************
    @FXML
    public void onActionLogin(ActionEvent event) {
        String correo = txtCorreo.getText().trim();
        String contraseña = txtContraseña.getText().trim();

        if (correo.isEmpty() || contraseña.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese correo y contraseña.", AlertType.ERROR);
            return;
        }

        // Llamada al cliente Backend para autenticar usuario
        Usuario usuario = backendClientUsuario.login(correo, contraseña);

        if (usuario != null) {
            // Si el login es exitoso, redirigimos a la siguiente pantalla (por ejemplo, menú principal)
            mostrarAlerta("Éxito", "Login exitoso", AlertType.INFORMATION);

            // Navegamos a la siguiente escena, por ejemplo, el menú principal del estudiante o administrador
            try {
                // Cargamos la siguiente pantalla (ejemplo: menú del estudiante)
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MenuEstudiante.fxml"));
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo cargar la siguiente escena: " + e.getMessage(), AlertType.ERROR);
            }

        } else {
            // Si no se pudo autenticar el usuario
            mostrarAlerta("Error", "Usuario o contraseña incorrectos.", AlertType.ERROR);
        }
    }

    // ************************************************
    // Acción para volver (si es necesario)
    // ************************************************
    @FXML
    public void onActionVolver(ActionEvent event) throws IOException {
        // Volver a la pantalla anterior (por ejemplo, al inicio o pantalla principal)
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Inicio.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // ************************************************
    // Método para mostrar alertas
    // ************************************************
    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
