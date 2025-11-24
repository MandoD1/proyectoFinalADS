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


public class ModificarClaseControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campos de la columna izquierda
    @FXML private TextField idclasemodificar; // ID de la clase a modificar (clave de búsqueda)
    @FXML private TextField idprofesorCargaacademica1; // Nuevo ID
    @FXML private TextField idprofesormodificarclase; // Id del profesor
    @FXML private TextField idmodificarclasehorario; // Horario de la clase

    // Campos de la columna central
    @FXML private TextField modificarclasecupo; // Cupo Actual
    @FXML private TextField modificarclasecupomax; // Cupo Máximo
    @FXML private TextField idmodificarclaseidasignatura; // Id asignatura

    // Botones
    @FXML private Button btnVolverMenuDCarrera;
    @FXML private Button btnModificarClasedpt;

    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú del Director de Carrera (o al menú principal del módulo, ajusta según la necesidad).
     * NOTA: Se asume que debe volver al menú del Director de Departamento, si es así, cambia el FXML a 'DirectorDptoMenu.fxml'
     */
    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorcarrera.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Ejecuta la lógica para modificar una clase en el sistema.
     */
    @FXML
    public void OnActionModificarClasedpt(ActionEvent event) {
        String idClase = idclasemodificar.getText();
        String nuevoId = idprofesorCargaacademica1.getText();
        String idProfesor = idprofesormodificarclase.getText();
        String horario = idmodificarclasehorario.getText();
        String cupo = modificarclasecupo.getText();
        String cupoMax = modificarclasecupomax.getText();
        String idAsignatura = idmodificarclaseidasignatura.getText();

        // --- Aquí iría la lógica de negocio para validar y actualizar la BBDD ---
        System.out.println("Intentando modificar clase: " + idClase);

        if (idClase.isEmpty() || idProfesor.isEmpty()) {
            mostrarAlerta("Error", "Los campos ID Clase y ID Profesor son obligatorios.", AlertType.ERROR);
            return;
        }

        // Simulación de éxito
        mostrarAlerta("Éxito", "La clase con ID " + idClase + " ha sido modificada correctamente.", AlertType.INFORMATION);

        // Limpiar campos después de la modificación
        limpiarCampos();
    }

    // Métodos onAction para los TextField (simplemente para que el FXML no dé errores)
    @FXML public void onActionclasemodificar(ActionEvent event) {}
    @FXML public void onActionidcargaprofe(ActionEvent event) {}
    @FXML public void onActionidprofemodificarclase(ActionEvent event) {}
    @FXML public void OnActionmodificarclasehorario(ActionEvent event) {}
    @FXML public void OnActionmodificarclasecupo(ActionEvent event) {}
    @FXML public void OnActionmodificarclasecupomax(ActionEvent event) {}
    @FXML public void OnActionmodificarclaseidasignatura(ActionEvent event) {}

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

    /**
     * Método auxiliar para limpiar los campos de texto.
     */
    private void limpiarCampos() {
        idclasemodificar.clear();
        idprofesorCargaacademica1.clear();
        idprofesormodificarclase.clear();
        idmodificarclasehorario.clear();
        modificarclasecupo.clear();
        modificarclasecupomax.clear();
        idmodificarclaseidasignatura.clear();
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador ModificarClase inicializado.");
    }
}