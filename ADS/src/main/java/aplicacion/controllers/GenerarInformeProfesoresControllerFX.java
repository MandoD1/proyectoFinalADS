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


public class GenerarInformeProfesoresControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Botones
    @FXML private Button btnVolverMenuDPTO;
    @FXML private Button btnGenerarinformedeprofesores;

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
     * Ejecuta la lógica para generar el informe de profesores.
     * Corresponde a onAction="#OnActionGenerarInformdeprofesores"
     */
    @FXML
    public void OnActionGenerarInformdeprofesores(ActionEvent event) {

        // --- 1. Lógica de Negocio (Generar Informe) ---

        System.out.println("Iniciando generación del informe de profesores...");


        // 1. Llamar a tu DAO/Servicio para obtener la lista de profesores.
        // 2. Formatear los datos (ej. a PDF, Excel o CSV).
        // 3. Guardar el archivo en el sistema de archivos (ej. usar FileChooser o ruta predeterminada).

        boolean informeGenerado = simularGeneracionInforme();

        // 2. Mostrar Resultado
        if (informeGenerado) {
            mostrarAlerta("Informe Generado",
                    "El informe de profesores ha sido generado con éxito.\n" +
                            "Verifique la carpeta de reportes.",
                    AlertType.INFORMATION);
        } else {
            mostrarAlerta("Error al Generar",
                    "Hubo un error al intentar generar el informe.",
                    AlertType.ERROR);
        }
    }

    /**
     * Método auxiliar para simular la generación del informe.
     */
    private boolean simularGeneracionInforme() {
        // En una aplicación real, esta función interactuaría con la capa de datos.
        // Simplemente devolvemos true aquí para demostrar el flujo exitoso.
        return true;
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
        System.out.println("Controlador GenerarInformeProfesores inicializado.");
    }
}