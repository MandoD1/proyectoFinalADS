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


public class EliminarAsignaturaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campo de texto para el ID de la asignatura a eliminar
    @FXML private TextField idasignaturaeliminar; // fx:id="idasignaturaeliminar"

    // Botones
    @FXML private Button btnVolverMenuDCarrera; // fx:id="btnVolverMenuDCarrera"
    @FXML private Button btnEliminarrAasigntura; // fx:id="btnEliminarrAasigntura"

    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {
        // Se asume que el FXML del menú es "DirectorDptoMenu.fxml" o "MenuGestion.fxml"
        // Usaremos "DirectorDptoMenu.fxml" como ejemplo de tu navegación anterior.
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorcarrera.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Ejecuta la lógica para eliminar una asignatura del sistema.
     * Corresponde a onAction="#OnActionEliminarAsignatura"
     */
    @FXML
    public void OnActionEliminarAsignatura(ActionEvent event) {
        String idAsignatura = idasignaturaeliminar.getText().trim();

        // 1. Validación
        if (idAsignatura.isEmpty()) {
            mostrarAlerta("Error de Validación", "Por favor, digite el ID de la asignatura a eliminar.", AlertType.ERROR);
            return;
        }

        // --- 2. Lógica de Negocio (Simulación) ---
        System.out.println("Intentando eliminar asignatura con ID: " + idAsignatura);

        // Aquí iría tu lógica real para interactuar con la BBDD (DAO/Service)
        boolean eliminacionExitosa = simularEliminacion(idAsignatura);

        // 3. Mostrar Resultado
        if (eliminacionExitosa) {
            mostrarAlerta("Éxito", "La asignatura con ID " + idAsignatura + " ha sido eliminada correctamente.", AlertType.INFORMATION);
            limpiarCampos();
        } else {
            // Ejemplo de error si el ID no existe, tiene clases activas, o falló la BBDD.
            mostrarAlerta("Error de Eliminación", "No se pudo eliminar la asignatura. Verifique que el ID " + idAsignatura + " exista y no tenga dependencias activas.", AlertType.ERROR);
        }
    }

    /**
     * Método auxiliar para simular la eliminación (REEMPLAZAR POR LÓGICA REAL).
     */
    private boolean simularEliminacion(String id) {
        // Simulamos un error si el ID es "A000" (la asignatura no se encuentra).
        return !id.equalsIgnoreCase("A000");
    }

    /**
     * Método auxiliar para limpiar los campos de texto (Usando setText("") para Gluon TextField).
     */
    private void limpiarCampos() {
        idasignaturaeliminar.setText("");
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
        System.out.println("Controlador EliminarAsignatura inicializado.");
    }
}