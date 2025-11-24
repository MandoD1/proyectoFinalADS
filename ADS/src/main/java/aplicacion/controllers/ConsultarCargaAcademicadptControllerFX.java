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

// Importación NECESARIA: Usamos el AutoCompleteTextField de Gluon
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;


public class ConsultarCargaAcademicadptControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campo de texto (AutoCompleteTextField de Gluon)
    @FXML private AutoCompleteTextField idprofeconsultacargaacademica; // ID del profesor a consultar

    // Botones
    @FXML private Button btnVolverMenuDPTO;
    @FXML private Button btnconsultarcargaprofes;


    // ************************************************
    // 2. MÉTODOS DE EVENTO (onAction)
    // ************************************************

    /**
     * Navega de vuelta al menú del Departamento (Nómina).
     * Corresponde a onAction="#onActionVolverMenuDPTO"
     */
    @FXML
    public void onActionVolverMenuDPTO(ActionEvent event) throws IOException {
        // Se asume que el menú principal del departamento es "MenuDepartamento.fxml"
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorDPT.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Ejecuta la lógica para consultar la carga académica de un profesor.
     * Corresponde a onAction="#onActionconsultarcargaprofes"
     */
    @FXML
    public void onActionconsultarcargaprofes(ActionEvent event) {
        // 1. Obtener valor
        String idProfesor = idprofeconsultacargaacademica.getText().trim();

        // 2. Validación Básica
        if (idProfesor.isEmpty()) {
            mostrarAlerta("Error de Validación", "Debe digitar el ID del profesor para consultar su carga académica.", AlertType.ERROR);
            return;
        }

        // --- 3. Lógica de Consulta (Simulación) ---
        System.out.println("Intentando consultar carga académica del Profesor ID: " + idProfesor);

        // Aquí iría tu llamada al servicio para obtener la lista de clases del profesor
        String carga = simularConsultaCarga(idProfesor);

        // 4. Mostrar Resultado
        if (carga.startsWith("Error")) {
            mostrarAlerta("Profesor no encontrado", carga, AlertType.ERROR);
        } else {
            mostrarAlerta("Carga Académica Consultada",
                    String.format("Carga académica del Profesor %s:\n%s", idProfesor, carga),
                    AlertType.INFORMATION);
            limpiarCampos();
        }
    }

    /**
     * Método auxiliar para simular la consulta de la carga académica.
     */
    private String simularConsultaCarga(String idProfesor) {
        if (idProfesor.equalsIgnoreCase("P101")) {
            return "Clase: Matemáticas Avanzadas (C201)\n" +
                    "Clase: Algoritmos II (C305)";
        } else if (idProfesor.equalsIgnoreCase("P999")) {
            return "Error: El ID de profesor P999 no fue encontrado en el sistema.";
        } else {
            return "Clase: Introducción a la Programación (C101)\n" +
                    "Total de Clases: 1";
        }
    }

    /**
     * Método auxiliar para limpiar los campos de texto (Usando setText("") para Gluon AutoCompleteTextField).
     */
    private void limpiarCampos() {
        idprofeconsultacargaacademica.setText("");
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
        System.out.println("Controlador ConsultarCargaAcademica inicializado.");
        // Opcional: Aquí podrías inicializar la lista de sugerencias para el AutoCompleteTextField
    }
}