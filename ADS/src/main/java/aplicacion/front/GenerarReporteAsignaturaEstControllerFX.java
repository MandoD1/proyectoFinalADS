package aplicacion.front;

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
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

// IMPORTACIÓN CORREGIDA: Usamos el TextField de Gluon Glisten (como en tu FXML)
import com.gluonhq.charm.glisten.control.TextField;


public class GenerarReporteAsignaturaEstControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Botón para regresar al menú anterior
    @FXML
    private Button btnVolverMenuDCarrera; // Sincronizado con fx:id="btnVolverMenuDCarrera"

    // Campo de texto para el ID de la asignatura
    // TIPO CORREGIDO: Ahora es com.gluonhq.charm.glisten.control.TextField
    @FXML
    private TextField idgenerarreporte; // Sincronizado con fx:id="idgenerarreporte"


    // ************************************************
    // 2. MÉTODOS DE NAVEGACIÓN Y ACCIÓN
    // ************************************************

    /**
     * Vuelve al menú principal del Director de Carrera.
     */
    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {
        // Asumiendo que el menú del Director de Carrera se llama menudirectorcarrera.fxml
        cargarEscenaSegura(event, "/menudirectorcarrera.fxml");
    }

    /**
     * Método placeholder para la acción de generar el reporte.
     * NOTA: Debes agregar un botón al FXML con onAction="#onActionGenerarReporte".
     */
    public void onActionGenerarReporte(ActionEvent event) {
        // NOTA: El método .getText() funciona igual en el TextField de Gluon.
        String idAsignatura = idgenerarreporte.getText();

        if (idAsignatura.trim().isEmpty()) {
            mostrarAlertaError("Entrada Incompleta", "Por favor, digite el ID de la asignatura para generar el reporte.");
            return;
        }

        // Aquí iría la lógica para buscar la asignatura,
        // obtener la lista de estudiantes y generar/mostrar el reporte.
        System.out.println("Solicitando reporte para la asignatura ID: " + idAsignatura);
        // ... (Lógica de base de datos o lógica de negocio)

        mostrarAlertaInformacion("Reporte Solicitado", "Se ha iniciado la generación del reporte para la asignatura: " + idAsignatura);
    }

    // ************************************************
    // 3. MÉTODOS AUXILIARES Y DE SEGURIDAD
    // ************************************************

    /**
     * Muestra una alerta de error en la UI.
     */
    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de información en la UI.
     */
    private void mostrarAlertaInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Intenta cargar un FXML de manera segura y cambia la escena.
     * Incluye chequeo para evitar NullPointerException.
     */
    private void cargarEscenaSegura(ActionEvent event, String fxmlPath) throws IOException {
        URL location = getClass().getResource(fxmlPath);

        if (location == null) {
            System.err.println("--- ERROR CRÍTICO DE FXML ---");
            System.err.println("Archivo FXML no encontrado. Se buscó: " + fxmlPath);
            mostrarAlertaError(
                    "Error de Navegación",
                    "No se pudo encontrar la pantalla de destino. Verifique que exista el archivo: " + fxmlPath
            );
            return;
        }

        Parent root = FXMLLoader.load(location);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    // ************************************************
    // 4. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador GenerarReporteAsignatura inicializado.");
    }
}