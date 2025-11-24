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

// Importación NECESARIA: Usamos AutoCompleteTextField de Gluon
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;


public class ConsultarAsignaturaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campo de texto (AutoCompleteTextField de Gluon)
    @FXML private AutoCompleteTextField idclaseaconsultar; // ID de la clase a buscar

    // Botones
    @FXML private Button btnVolverEstudMenu;
    @FXML private Button btnconsultarclase;


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
        // Ajusta el nombre del FXML si es diferente.
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("EstudianteMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Ejecuta la lógica para buscar y mostrar la información de la clase.
     * Corresponde a onAction="#onActionconsultarclase"
     */
    @FXML
    public void onActionconsultarclase(ActionEvent event) {

        String idClase = idclaseaconsultar.getText().trim();

        // 1. Validación Básica
        if (idClase.isEmpty()) {
            mostrarAlerta("Error de Validación", "Por favor, ingrese el ID de la clase que desea consultar.", AlertType.ERROR);
            return;
        }

        // --- 2. Lógica de Consulta (Simulación) ---

        System.out.printf("Intentando consultar información de la clase ID: %s%n", idClase);

        String infoClase = simularConsultaClase(idClase);

        // 3. Mostrar Resultado
        if (infoClase.startsWith("Error")) {
            mostrarAlerta("Clase no Encontrada", infoClase, AlertType.ERROR);
        } else {
            mostrarAlerta("Información de la Clase",
                    String.format("Detalles de la Clase %s:\n%s", idClase, infoClase),
                    AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    /**
     * Método auxiliar para simular la consulta de datos de la clase.
     */
    private String simularConsultaClase(String idClase) {
        if (idClase.equalsIgnoreCase("MAT101")) {
            return "Nombre: Matemáticas I\n" +
                    "Profesor: Dr. Pérez\n" +
                    "Horario: Lunes y Miércoles 8:00 AM\n" +
                    "Cupo Disponible: 15";
        } else if (idClase.equalsIgnoreCase("PRO202")) {
            return "Nombre: Programación Orientada a Objetos\n" +
                    "Profesor: Ing. González\n" +
                    "Horario: Martes y Jueves 10:00 AM\n" +
                    "Cupo Disponible: 5";
        } else {
            return "Error: La clase con ID '" + idClase + "' no fue encontrada en el catálogo.";
        }
    }

    /**
     * Método auxiliar para limpiar los campos de texto (Usando setText("") para Gluon).
     */
    private void limpiarCampos() {
        idclaseaconsultar.setText("");
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
        System.out.println("Controlador ConsultarAsignatura inicializado.");
        // Opcional: Aquí podrías cargar sugerencias de clases en idclaseaconsultar
    }
}