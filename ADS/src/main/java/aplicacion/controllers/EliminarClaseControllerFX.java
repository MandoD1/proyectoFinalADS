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
import javafx.stage.Stage;

// Importación NECESARIA: Usamos el TextField de Gluon
import com.gluonhq.charm.glisten.control.TextField;


public class EliminarClaseControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campo de texto para el ID de la clase a eliminar
    @FXML private TextField ideliminarClase; // fx:id="ideliminarClase"

    // Botones
    @FXML private Button btnVolverMenuDCarrera; // fx:id="btnVolverMenuDCarrera"
    @FXML private Button btneliminarClasedpt; // fx:id="btneliminarClasedpt"

    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú del Director de Carrera/Departamento.
     * Corresponde a onAction="#onActionVolverMenuDCarrera"
     */
    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {
        // Se asume que el FXML del menú es "DirectorDptoMenu.fxml"
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorcarrera.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Ejecuta la lógica para eliminar una clase del sistema.
     * Corresponde a onAction="#OnActioneliminarClasedpt"
     */
    @FXML
    public void OnActioneliminarClasedpt(ActionEvent event) {
        String idClase = ideliminarClase.getText().trim();

        // 1. Validación
        if (idClase.isEmpty()) {
            mostrarAlerta("Error de Validación", "Por favor, digite el ID de la clase a eliminar.", AlertType.ERROR);
            return;
        }

        // --- 2. Lógica de Negocio (Simulación) ---
        System.out.println("Intentando eliminar clase con ID: " + idClase);

        // Aquí iría tu lógica real para interactuar con la BBDD (DAO/Service)
        boolean eliminacionExitosa = simularEliminacion(idClase);

        // 3. Mostrar Resultado
        if (eliminacionExitosa) {
            mostrarAlerta("Éxito", "La clase con ID " + idClase + " ha sido eliminada correctamente.", AlertType.INFORMATION);
            limpiarCampos();
        } else {
            // Ejemplo de error si el ID no existe o falló la BBDD
            mostrarAlerta("Error de Eliminación", "No se pudo eliminar la clase. Verifique que el ID " + idClase + " exista.", AlertType.ERROR);
        }
    }

    /**
     * Método auxiliar para simular la eliminación (REEMPLAZAR POR LÓGICA REAL).
     */
    private boolean simularEliminacion(String id) {
        // Simulamos un error si el ID es "C100" (la clase no se encuentra).
        return !id.equalsIgnoreCase("C100");
    }

    /**
     * Método auxiliar para limpiar los campos de texto (Usando setText("") para Gluon TextField).
     */
    private void limpiarCampos() {
        ideliminarClase.setText("");
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
        System.out.println("Controlador EliminarClase inicializado.");
    }
}