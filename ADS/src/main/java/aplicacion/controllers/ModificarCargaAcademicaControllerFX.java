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
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ModificarCargaAcademicaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campos de texto
    @FXML private TextField idprofesormodificarCarga; // ID del profesor
    @FXML private TextField idclasemodificarprofe; // ID de la clase

    // Items del MenuButton
    @FXML private MenuItem idañadirmodificarcargaprofe; // Item Añadir
    @FXML private MenuItem idretirarmodificarcargaprofe; // Item Retirar (Action 2 en FXML)

    // Botones
    @FXML private Button btnVolverMenuDCarrera;
    @FXML private Button btnModificarCargaprofe;

    // Variable de estado para rastrear la acción seleccionada
    private String accionSeleccionada = null; // Puede ser "AÑADIR" o "RETIRAR"


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú del Director de Carrera/Departamento.
     * Corresponde a onAction="#onActionVolverMenuDCarrera"
     */
    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorcarrera.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Define la acción seleccionada como "AÑADIR".
     * Corresponde a onAction="#OnActionañadirmodificarcargaprofe"
     */
    @FXML
    public void OnActionañadirmodificarcargaprofe(ActionEvent event) {
        accionSeleccionada = "AÑADIR";
        System.out.println("Acción seleccionada: AÑADIR");
        mostrarAlerta("Acción Seleccionada", "Se ha seleccionado la acción: AÑADIR clase.", AlertType.INFORMATION);
    }

    /**
     * Define la acción seleccionada como "RETIRAR".
     * Corresponde a onAction="#OnActionretirarmodificarcargaprofe"
     */
    @FXML
    public void OnActionretirarmodificarcargaprofe(ActionEvent event) {
        accionSeleccionada = "RETIRAR"; // Asumiendo que "Action 2" significa Retirar
        System.out.println("Acción seleccionada: RETIRAR");
        mostrarAlerta("Acción Seleccionada", "Se ha seleccionado la acción: RETIRAR clase.", AlertType.INFORMATION);
    }

    /**
     * Ejecuta la lógica para modificar la carga académica de un profesor.
     * Corresponde a onAction="#OnActionModificarCargaprofe"
     */
    @FXML
    public void OnActionModificarCargaprofe(ActionEvent event) {
        String idProfesor = idprofesormodificarCarga.getText().trim();
        String idClase = idclasemodificarprofe.getText().trim();

        // 1. Validación
        if (idProfesor.isEmpty() || idClase.isEmpty()) {
            mostrarAlerta("Error de Validación", "Debe digitar el ID del profesor y el ID de la clase.", AlertType.ERROR);
            return;
        }

        if (accionSeleccionada == null) {
            mostrarAlerta("Error de Validación", "Debe seleccionar si desea Añadir o Retirar la clase.", AlertType.ERROR);
            return;
        }

        // --- 2. Lógica de Negocio (Simulación) ---
        String resultado = realizarOperacion(idProfesor, idClase, accionSeleccionada);

        // 3. Mostrar Resultado
        if (resultado.startsWith("Éxito")) {
            mostrarAlerta("Éxito", resultado, AlertType.INFORMATION);
            limpiarCampos();
            accionSeleccionada = null; // Resetear la acción
        } else {
            mostrarAlerta("Error", resultado, AlertType.ERROR);
        }
    }

    /**
     * Método auxiliar para ejecutar la lógica de negocio simulada.
     */
    private String realizarOperacion(String idProfesor, String idClase, String accion) {
        System.out.printf("Procesando: %s, Profesor ID %s, Clase ID %s%n", accion, idProfesor, idClase);

        // Simulación de lógica:
        if (accion.equals("AÑADIR")) {
            // Falla simulada si el profesor es P999
            if (idProfesor.equalsIgnoreCase("P999")) {
                return "Error: El profesor P999 no existe o ya tiene una carga máxima.";
            }
            return String.format("Éxito: Clase %s añadida al Profesor %s.", idClase, idProfesor);
        }

        if (accion.equals("RETIRAR")) {
            // Falla simulada si la clase es C000
            if (idClase.equalsIgnoreCase("C000")) {
                return "Error: La clase C000 no está asignada a este profesor.";
            }
            return String.format("Éxito: Clase %s retirada del Profesor %s.", idClase, idProfesor);
        }

        return "Error: Acción no reconocida.";
    }

    /**
     * Método auxiliar para limpiar los campos de texto.
     */
    private void limpiarCampos() {
        idprofesormodificarCarga.clear();
        idclasemodificarprofe.clear();
    }

    // Métodos onAction vacíos para los TextField
    @FXML public void onActionidprofemodificarcarga(ActionEvent event) {}
    @FXML public void OnActionidclasemodificarprofe(ActionEvent event) {}


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
        System.out.println("Controlador ModificarCargaAcademica inicializado.");
    }
}