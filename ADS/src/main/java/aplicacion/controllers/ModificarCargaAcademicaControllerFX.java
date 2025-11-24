package aplicacion.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aplicacion.client.BackendClientDirectorCarrera;
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
    @FXML private MenuItem idretirarmodificarcargaprofe; // Item Retirar

    // Botones
    @FXML private Button btnVolverMenuDCarrera;
    @FXML private Button btnModificarCargaprofe;

    // Variable de estado para rastrear la acción seleccionada
    private String accionSeleccionada = null; // Puede ser "AÑADIR" o "RETIRAR"

    // Cliente al backend
    private final BackendClientDirectorCarrera backend = new BackendClientDirectorCarrera();


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú del Director de Carrera.
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
     */
    @FXML
    public void OnActionañadirmodificarcargaprofe(ActionEvent event) {
        accionSeleccionada = "AÑADIR";
        System.out.println("Acción seleccionada: AÑADIR");
        mostrarAlerta("Acción Seleccionada", "Se ha seleccionado la acción: AÑADIR clase.", AlertType.INFORMATION);
    }

    /**
     * Define la acción seleccionada como "RETIRAR".
     */
    @FXML
    public void OnActionretirarmodificarcargaprofe(ActionEvent event) {
        accionSeleccionada = "RETIRAR";
        System.out.println("Acción seleccionada: RETIRAR");
        mostrarAlerta("Acción Seleccionada", "Se ha seleccionado la acción: RETIRAR clase.", AlertType.INFORMATION);
    }

    /**
     * Ejecuta la lógica para modificar la carga académica de un profesor usando BackendClientDirectorCarrera.
     */
    @FXML
    public void OnActionModificarCargaprofe(ActionEvent event) {
        String idProfesorStr = idprofesormodificarCarga.getText().trim();
        String idClaseStr = idclasemodificarprofe.getText().trim();

        // 1. Validación básica
        if (idProfesorStr.isEmpty() || idClaseStr.isEmpty()) {
            mostrarAlerta("Error de Validación", "Debe digitar el ID del profesor y el ID de la clase.", AlertType.ERROR);
            return;
        }

        if (accionSeleccionada == null) {
            mostrarAlerta("Error de Validación", "Debe seleccionar si desea Añadir o Retirar la clase.", AlertType.ERROR);
            return;
        }

        try {
            Long profesorId = Long.parseLong(idProfesorStr);
            Long claseId = Long.parseLong(idClaseStr);
            boolean accion = accionSeleccionada.equals("AÑADIR"); // true = Añadir, false = Retirar

            // Llamada al backend
            backend.modificarCarga(profesorId, claseId, accion);

            mostrarAlerta("Éxito", "Operación realizada correctamente: " + accionSeleccionada + " clase.", AlertType.INFORMATION);
            limpiarCampos();
            accionSeleccionada = null; // Resetear acción

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Los IDs deben ser números enteros válidos.", AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo completar la operación. Verifique la conexión con el backend.", AlertType.ERROR);
            e.printStackTrace();
        }
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
