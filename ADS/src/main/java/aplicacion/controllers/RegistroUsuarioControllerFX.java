package aplicacion.controllers;

import aplicacion.client.BackendClientUsuario;
import aplicacion.model.Usuario;
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroUsuarioControllerFX implements Initializable {

    @FXML private AutoCompleteTextField idcorreoregistro;
    @FXML private AutoCompleteTextField idcontraseñaregistro;
    @FXML private MenuItem idresgistroestudiante;
    @FXML private MenuItem idresgistroDcarrera;
    @FXML private MenuItem idresgistroDdepartamento;
    @FXML private Button btntipoLogin;
    @FXML private Button btncrearusuario;

    private String rolSeleccionado = null;
    private final BackendClientUsuario backend = new BackendClientUsuario();

    @FXML
    public void onActiontipoLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("nTipoLogin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void OnActionresgistroestudiante(ActionEvent event) { rolSeleccionado = "Estudiante"; mostrarInfo("Estudiante"); }
    @FXML
    public void OnActionregistroDcarrera(ActionEvent event) { rolSeleccionado = "DirectorCarrera"; mostrarInfo("Director de Carrera"); }
    @FXML
    public void OnActionresgistroDdepartamento(ActionEvent event) { rolSeleccionado = "DirectorDepartamento"; mostrarInfo("Director de Departamento"); }

    private void mostrarInfo(String rol) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rol Seleccionado");
        alert.setHeaderText(null);
        alert.setContentText("Tipo de usuario seleccionado: " + rol);
        alert.showAndWait();
    }

    @FXML
    public void OnActioncrearusuario(ActionEvent event) {
        String correo = idcorreoregistro.getText().trim();
        String contrasena = idcontraseñaregistro.getText().trim();

        if(correo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar correo y contraseña.", Alert.AlertType.ERROR);
            return;
        }

        if(rolSeleccionado == null) {
            mostrarAlerta("Error", "Debe seleccionar un rol.", Alert.AlertType.ERROR);
            return;
        }

        if(!correo.contains("@") || !correo.contains(".")) {
            mostrarAlerta("Error", "Correo inválido.", Alert.AlertType.ERROR);
            return;
        }

        try {
            Usuario usuario = backend.registrarUsuario(correo, contrasena, rolSeleccionado);
            if(usuario != null) {
                mostrarAlerta("Éxito", "Usuario registrado correctamente como " + rolSeleccionado, Alert.AlertType.INFORMATION);
                idcorreoregistro.setText("");
                idcontraseñaregistro.setText("");
                rolSeleccionado = null;
            } else {
                mostrarAlerta("Error", "No se pudo registrar el usuario.", Alert.AlertType.ERROR);
            }
        } catch(Exception e) {
            mostrarAlerta("Error de Conexión", "No se pudo conectar al backend.\n" + e.getMessage(), Alert.AlertType.ERROR);
        }
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
        System.out.println("Controlador RegistroUsuario inicializado.");
    }
}
