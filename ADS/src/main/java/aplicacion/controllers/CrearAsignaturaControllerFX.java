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

// IMPORTACIÓN CORREGIDA: Usamos el TextField de Gluon, como en el FXML.
import com.gluonhq.charm.glisten.control.TextField;


public class CrearAsignaturaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Columna Izquierda:
    @FXML private TextField idasignaturacrear;
    @FXML private TextField idcupomaxcrearasignatura;
    @FXML private TextField idsemestrecreasasignatura;
    @FXML private TextField iddeptcrearasignatura;

    // Columna Derecha:
    @FXML private TextField idcrearasignaturacreditos;
    @FXML private TextField idasignaturacrearnombre;

    // Botones:
    @FXML private Button btnVolverMenuDCarrera;
    @FXML private Button btnCrearAasigntura;

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
     * Ejecuta la lógica para crear una nueva asignatura.
     * Corresponde a onAction="#OnActionCrearAsignatura"
     */
    @FXML
    public void OnActionCrearAsignatura(ActionEvent event) {

        // 1. Obtener valores y limpiar espacios en blanco
        String idAsignatura = idasignaturacrear.getText().trim();
        String cupoMaxStr = idcupomaxcrearasignatura.getText().trim();
        String idSemestre = idsemestrecreasasignatura.getText().trim();
        String idDepartamento = iddeptcrearasignatura.getText().trim();
        String creditosStr = idcrearasignaturacreditos.getText().trim();
        String nombre = idasignaturacrearnombre.getText().trim();

        // 2. Validación Básica
        if (idAsignatura.isEmpty() || nombre.isEmpty() || creditosStr.isEmpty() || cupoMaxStr.isEmpty() || idSemestre.isEmpty() || idDepartamento.isEmpty()) {
            mostrarAlerta("Error de Validación", "Todos los campos son obligatorios. Por favor, complete la información.", AlertType.ERROR);
            return;
        }

        // 3. Validación de Formato Numérico
        int cupoMax;
        int creditos;
        try {
            cupoMax = Integer.parseInt(cupoMaxStr);
            creditos = Integer.parseInt(creditosStr);

            if (cupoMax <= 0 || creditos < 1) {
                mostrarAlerta("Error de Validación", "Cupo Máximo debe ser positivo y Créditos debe ser al menos 1.", AlertType.ERROR);
                return;
            }

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Cupo Máximo y Créditos deben ser números enteros válidos.", AlertType.ERROR);
            return;
        }

        // --- 4. Lógica de Negocio (Aquí llamarías a tu servicio o DAO para guardar) ---

        System.out.println("Intentando crear asignatura con ID: " + idAsignatura);

        // Simulación: Reemplazar con la llamada a tu lógica de persistencia real
        boolean creacionExitosa = simularCreacion(idAsignatura);

        // 5. Mostrar Resultado
        if (creacionExitosa) {
            mostrarAlerta("Éxito", "La asignatura '" + nombre + "' ha sido creada e ID: " + idAsignatura + ".", AlertType.INFORMATION);
            limpiarCampos();
        } else {
            // Error simulado, por ejemplo, si la asignatura ya existe
            mostrarAlerta("Error de Creación", "No se pudo crear la asignatura. Verifique si el ID ya existe o hay un problema de conexión.", AlertType.ERROR);
        }
    }

    /**
     * Método auxiliar para simular la creación (REEMPLAZAR POR LÓGICA REAL).
     */
    private boolean simularCreacion(String id) {
        return !id.equalsIgnoreCase("A999");
    }

    /**
     * Método auxiliar para limpiar los campos de texto.
     * CORREGIDO: Usa setText("") en lugar de clear() para los TextField de Gluon.
     */
    private void limpiarCampos() {
        idasignaturacrear.setText("");
        idcupomaxcrearasignatura.setText("");
        idsemestrecreasasignatura.setText("");
        iddeptcrearasignatura.setText("");
        idcrearasignaturacreditos.setText("");
        idasignaturacrearnombre.setText("");
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
        System.out.println("Controlador CrearAsignatura inicializado.");
    }
}