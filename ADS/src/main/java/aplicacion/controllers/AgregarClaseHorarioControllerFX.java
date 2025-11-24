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


public class AgregarClaseHorarioControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campo de texto (AutoCompleteTextField de Gluon)
    @FXML private AutoCompleteTextField idclaseagregarahorario;

    // Botones
    @FXML private Button btnVolverEstudMenu;
    @FXML private Button btnAgregarClase;

    // NOTA: En una aplicación real, necesitarías el ID del estudiante logueado.
    private String idEstudianteActual = "E12345";


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
     * Ejecuta la lógica para agregar la clase al horario del estudiante.
     * Corresponde a onAction="#onActionAgregarClase"
     */
    @FXML
    public void onActionAgregarClase(ActionEvent event) {

        String idClase = idclaseagregarahorario.getText().trim();

        // 1. Validación Básica
        if (idClase.isEmpty()) {
            mostrarAlerta("Error de Validación", "Por favor, ingrese el ID de la clase que desea agregar.", AlertType.ERROR);
            return;
        }

        // --- 2. Lógica de Inscripción (Simulación) ---

        System.out.printf("Estudiante %s intentando inscribirse a la clase %s%n", idEstudianteActual, idClase);

        boolean inscripcionExitosa = simularInscripcion(idEstudianteActual, idClase);

        // 3. Mostrar Resultado
        if (inscripcionExitosa) {
            mostrarAlerta("Éxito de Inscripción",
                    String.format("¡Felicidades! Se ha inscrito exitosamente a la clase con ID: %s.", idClase),
                    AlertType.INFORMATION);
            limpiarCampos();
        } else {
            // Error simulado (ej. cupo lleno, prerequisitos, clase no existe)
            mostrarAlerta("Fallo de Inscripción",
                    "No se pudo completar la inscripción. Verifique que la clase exista, tenga cupo disponible y cumpla con los prerequisitos.",
                    AlertType.ERROR);
        }
    }

    /**
     * Método auxiliar para simular la lógica de negocio.
     */
    private boolean simularInscripcion(String idEstudiante, String idClase) {
        // Simulación: Falla si la clase es "C999" (Clase inexistente o llena)
        return !idClase.equalsIgnoreCase("C999");
    }

    /**
     * Método auxiliar para limpiar los campos de texto (Usando setText("") para Gluon).
     */
    private void limpiarCampos() {
        idclaseagregarahorario.setText("");
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
        System.out.println("Controlador AgregarClaseHorario inicializado.");
        // Opcional: Aquí podrías inicializar la lista de sugerencias para el AutoCompleteTextField
    }
}