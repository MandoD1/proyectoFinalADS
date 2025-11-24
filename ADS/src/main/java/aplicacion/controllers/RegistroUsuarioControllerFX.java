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
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

// Importación NECESARIA: Usamos AutoCompleteTextField de Gluon
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;


public class RegistroUsuarioControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campos de texto (AutoCompleteTextField de Gluon)
    @FXML private AutoCompleteTextField idcorreoregistro;
    @FXML private AutoCompleteTextField idcontraseñaregistro;

    // Items del MenuButton (para capturar la selección)
    @FXML private MenuItem idresgistroestudiante;
    @FXML private MenuItem idresgistroDcarrera;
    @FXML private MenuItem idresgistroDdepartamento;

    // Botones
    @FXML private Button btntipoLogin; // Botón Volver
    @FXML private Button btncrearusuario; // Botón Crear Usuario

    // Variable de estado para el rol seleccionado
    private String rolSeleccionado = null;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta a la pantalla de Login (o selección de tipo de Login).
     * Corresponde a onAction="#onActiontipoLogin"
     */
    @FXML
    public void onActiontipoLogin(ActionEvent event) throws IOException {
        // Se asume que el FXML de Login se llama "Login.fxml" o similar
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("nTipoLogin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // --- Manejo de la selección del rol en el MenuButton ---

    @FXML
    public void OnActionresgistroestudiante(ActionEvent event) {
        rolSeleccionado = "Estudiante";
        mostrarAlerta("Rol Seleccionado", "Tipo de usuario seleccionado: Estudiante.", AlertType.INFORMATION);
    }

    @FXML
    public void OnActionregistroDcarrera(ActionEvent event) {
        rolSeleccionado = "Director de Carrera";
        mostrarAlerta("Rol Seleccionado", "Tipo de usuario seleccionado: Director de Carrera.", AlertType.INFORMATION);
    }

    @FXML
    public void OnActionresgistroDdepartamento(ActionEvent event) {
        rolSeleccionado = "Director de Departamento";
        mostrarAlerta("Rol Seleccionado", "Tipo de usuario seleccionado: Director de Departamento.", AlertType.INFORMATION);
    }

    /**
     * Ejecuta la lógica para crear el nuevo usuario con el rol seleccionado.
     * Corresponde a onAction="#OnActioncrearusuario"
     */
    @FXML
    public void OnActioncrearusuario(ActionEvent event) {

        String correo = idcorreoregistro.getText().trim();
        String contrasena = idcontraseñaregistro.getText().trim();

        // 1. Validación de campos básicos
        if (correo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Error de Validación", "Debe ingresar un correo y una contraseña.", AlertType.ERROR);
            return;
        }

        // 2. Validación de rol seleccionado
        if (rolSeleccionado == null) {
            mostrarAlerta("Error de Validación", "Debe seleccionar un tipo de usuario (rol).", AlertType.ERROR);
            return;
        }

        // 3. Validación de formato de correo (Ejemplo simple)
        if (!correo.contains("@") || !correo.contains(".")) {
            mostrarAlerta("Error de Validación", "Por favor, ingrese un formato de correo electrónico válido.", AlertType.ERROR);
            return;
        }

        // --- 4. Lógica de Negocio (Simulación de Registro) ---

        System.out.printf("Intentando registrar usuario: %s con rol: %s%n", correo, rolSeleccionado);

        // Aquí iría tu lógica real (DAO/Service) para registrar el usuario en la BBDD
        boolean registroExitoso = simularRegistro(correo, contrasena, rolSeleccionado);

        // 5. Mostrar Resultado
        if (registroExitoso) {
            mostrarAlerta("Registro Exitoso",
                    String.format("El usuario '%s' ha sido registrado como %s.", correo, rolSeleccionado),
                    AlertType.INFORMATION);
            limpiarCampos();
            rolSeleccionado = null; // Resetear rol
        } else {
            // Error simulado (ej. usuario ya existe)
            mostrarAlerta("Error de Registro", "No se pudo crear el usuario. El correo puede ya estar registrado.", AlertType.ERROR);
        }
    }

    /**
     * Método auxiliar para simular la lógica de registro.
     */
    private boolean simularRegistro(String correo, String contrasena, String rol) {
        // Simulación: Falla si el correo es "test@exist.com"
        return !correo.equalsIgnoreCase("test@exist.com");
    }

    /**
     * Método auxiliar para limpiar los campos de texto (Usando setText("") para Gluon).
     */
    private void limpiarCampos() {
        idcorreoregistro.setText("");
        idcontraseñaregistro.setText("");
    }

    /**
     * Método auxiliar para mostrar alertas.
     */
    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador RegistroUsuario inicializado.");
    }
}