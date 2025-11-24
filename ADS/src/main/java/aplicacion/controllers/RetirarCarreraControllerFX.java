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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RetirarCarreraControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campos de texto
    @FXML private TextField idestudenntretitarcarrera; // ID del estudiante
    @FXML private TextField idcarreraretirarcarrera;  // ID de la carrera a retirar

    // Botones
    @FXML private Button btnVolverEstudMenu; // Botón Volver
    @FXML private Button idretirodecarrera;    // Botón Retirar Carrera


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú principal del estudiante.
     * Corresponde a onAction="#onActionVolverEstudMenu"
     */
    @FXML
    public void onActionVolverEstudMenu(ActionEvent event) throws IOException {
        // Se asume que el menú principal del estudiante es "EstudianteMenu.fxml"
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("EstudianteMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * [Necesario para resolver el 'Cannot resolve symbol' del FXML]
     * Maneja el evento ENTER en el campo ID Estudiante.
     * Corresponde a onAction="#OnActionstudentretirarcarrera"
     */
    @FXML
    public void OnActionstudentretirarcarrera(ActionEvent event) {
        System.out.println("ENTER presionado en ID Estudiante. Enfocando ID Carrera.");
        // Opcional: enfocar el siguiente campo
        idcarreraretirarcarrera.requestFocus();
    }

    /**
     * [Necesario para resolver el 'Cannot resolve symbol' del FXML]
     * Maneja el evento ENTER en el campo ID Carrera.
     * Corresponde a onAction="#OnActionsidcarreraetirarcarrera"
     */
    @FXML
    public void OnActionsidcarreraetirarcarrera(ActionEvent event) {
        System.out.println("ENTER presionado en ID Carrera. Ejecutando Retiro.");
        // Opcional: ejecutar la acción principal
        OnActionretiroCarrera(event);
    }

    /**
     * Ejecuta la lógica para retirar al estudiante de la carrera especificada.
     * Corresponde a onAction="#OnActionretiroCarrera"
     */
    @FXML
    public void OnActionretiroCarrera(ActionEvent event) {

        String idEstudiante = idestudenntretitarcarrera.getText().trim();
        String idCarrera = idcarreraretirarcarrera.getText().trim();

        // 1. Validación Básica
        if (idEstudiante.isEmpty() || idCarrera.isEmpty()) {
            mostrarAlerta("Error de Validación", "Debe ingresar el ID del estudiante y el ID de la carrera.", AlertType.ERROR);
            return;
        }

        // --- 2. Lógica de Retiro (Simulación) ---

        System.out.printf("Intentando retirar al estudiante %s de la carrera %s%n", idEstudiante, idCarrera);

        // Aquí iría tu lógica real: verificar, eliminar relación BBDD, etc.
        boolean retiroExitoso = simularRetiro(idEstudiante, idCarrera);

        // 3. Mostrar Resultado
        if (retiroExitoso) {
            mostrarAlerta("Retiro Exitoso",
                    String.format("El estudiante %s ha sido retirado exitosamente de la carrera %s.", idEstudiante, idCarrera),
                    AlertType.INFORMATION);
            limpiarCampos();
        } else {
            // Error simulado (ej. estudiante no estaba inscrito, ID no existe, etc.)
            mostrarAlerta("Fallo de Retiro",
                    "No se pudo completar el retiro. Verifique los IDs o si el estudiante ya no pertenece a esa carrera.",
                    AlertType.ERROR);
        }
    }

    /**
     * Método auxiliar para simular la lógica de negocio.
     */
    private boolean simularRetiro(String idEstudiante, String idCarrera) {
        // Simulación: Falla si la carrera es "CERO"
        return !idCarrera.equalsIgnoreCase("CERO");
    }

    /**
     * Método auxiliar para limpiar los campos de texto.
     */
    private void limpiarCampos() {
        idestudenntretitarcarrera.setText("");
        idcarreraretirarcarrera.setText("");
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
        System.out.println("Controlador RetirarCarrera inicializado.");
    }
}