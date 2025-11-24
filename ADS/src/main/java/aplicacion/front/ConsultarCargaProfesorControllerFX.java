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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField; // TextField estándar de JavaFX

// Importaciones de la librería Gluon Glisten
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.stage.Stage;


public class ConsultarCargaProfesorControllerFX implements Initializable {

    /*
     ************************************************
     1. VARIABLES INYECTADAS (fx:id)
     ************************************************
    */

    // Botones de navegación y acción
    @FXML private Button btnVolverAdminMenu; // Botón para volver al menú de administrador
    @FXML private Button btnConsultarCarga;   // Botón para ejecutar la consulta

    // Campos de entrada y salida
    @FXML private TextField idprofesorConsultar; // Entrada del ID del profesor (JavaFX TextField)
    @FXML private AutoCompleteTextField idHorassemalaesprof; // Salida de Horas Semanales (Gluon AutoCompleteTextField)
    @FXML private AutoCompleteTextField idTipoContrato;      // Salida del Tipo de Contrato (Gluon AutoCompleteTextField)
    @FXML private AutoCompleteTextField idCalculoNomina;     // Salida del Cálculo de Nómina (Gluon AutoCompleteTextField)


    // ************************************************
    // 2. MÉTODOS DE NAVEGACIÓN Y ACCIÓN
    // ************************************************

    /**
     * Vuelve al menú principal de Administrador.
     */
    @FXML
    public void onActionVolverAdminMenu(ActionEvent event) throws IOException {
        cargarEscenaSegura(event, "/menudirectordept.fxml");
    }

    /**
     * Consulta la carga horaria y detalles del profesor basado en el ID.
     */
    @FXML
    public void onActionConsultarCarga(ActionEvent event) {
        String idProfesor = idprofesorConsultar.getText().trim();

        if (idProfesor.isEmpty()) {
            mostrarAlertaError("Entrada Incompleta", "Por favor, digite el ID del profesor a consultar.");
            // Limpia los campos de salida si el ID está vacío
            limpiarCamposSalida();
            return;
        }

        System.out.println("Consultando carga para profesor ID: " + idProfesor);

        // --- LÓGICA DE NEGOCIO (simulada) ---

        // 1. Aquí iría la llamada a la base de datos o al servicio para obtener la información
        // Por ahora, simularemos la respuesta:
        if (idProfesor.equals("1001")) {
            // Ejemplo de profesor a tiempo completo
            idHorassemalaesprof.setText("40 Horas");
            idTipoContrato.setText("Tiempo Completo");
            idCalculoNomina.setText("$5,000,000 COP");
            mostrarAlertaInformacion("Consulta Exitosa", "Se cargaron los detalles del profesor " + idProfesor + ".");
        } else if (idProfesor.equals("2002")) {
            // Ejemplo de profesor por horas
            idHorassemalaesprof.setText("15 Horas");
            idTipoContrato.setText("Cátedra (Por Horas)");
            idCalculoNomina.setText("Cálculo por tarifa horaria");
            mostrarAlertaInformacion("Consulta Exitosa", "Se cargaron los detalles del profesor " + idProfesor + ".");
        } else {
            // Profesor no encontrado
            mostrarAlertaError("Profesor No Encontrado", "El ID del profesor '" + idProfesor + "' no existe en el sistema.");
            limpiarCamposSalida();
        }

        // El AutoCompleteTextField no es un campo de entrada en este contexto,
        // sino que se usa como campo de salida no editable (solo lectura).
    }

    /**
     * Limpia los campos de texto de salida.
     */
    private void limpiarCamposSalida() {
        idHorassemalaesprof.setText("");
        idTipoContrato.setText("");
        idCalculoNomina.setText("");
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
        System.out.println("Controlador ConsultarCargaProfesorController inicializado.");

        // Inicialmente limpia los campos de salida
        // Esto es útil si los campos se inicializan con texto
        if (idHorassemalaesprof != null) {
            limpiarCamposSalida();
        }
    }
}