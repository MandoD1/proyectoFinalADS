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


public class CalcularNominaControllerFX implements Initializable {

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Campos de texto (AutoCompleteTextField de Gluon)
    @FXML private AutoCompleteTextField idprofenomina;
    @FXML private AutoCompleteTextField idhorasdictadasnomina;
    @FXML private AutoCompleteTextField idminhoras;
    @FXML private AutoCompleteTextField idmaxhoras;

    // Botones
    @FXML private Button btnVolverMenuDPTO;
    @FXML private Button btnCalcularnomina;


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
     * Ejecuta la lógica para calcular la nómina de un profesor.
     * Corresponde a onAction="#OnActionCalcularNomina"
     */
    @FXML
    public void OnActionCalcularNomina(ActionEvent event) {
        // 1. Obtener valores y limpiar espacios en blanco
        String idProfesor = idprofenomina.getText().trim();
        String horasDictadasStr = idhorasdictadasnomina.getText().trim();
        String minHorasStr = idminhoras.getText().trim();
        String maxHorasStr = idmaxhoras.getText().trim();

        // 2. Validación Básica
        if (idProfesor.isEmpty() || horasDictadasStr.isEmpty() || minHorasStr.isEmpty() || maxHorasStr.isEmpty()) {
            mostrarAlerta("Error de Validación", "Todos los campos son obligatorios para el cálculo de nómina.", AlertType.ERROR);
            return;
        }

        // 3. Validación de Formato Numérico
        int horasDictadas, minHoras, maxHoras;
        try {
            horasDictadas = Integer.parseInt(horasDictadasStr);
            minHoras = Integer.parseInt(minHorasStr);
            maxHoras = Integer.parseInt(maxHorasStr);

            if (horasDictadas < 0 || minHoras < 0 || maxHoras <= 0 || minHoras >= maxHoras) {
                mostrarAlerta("Error de Validación", "Las horas deben ser valores válidos. Maximo debe ser mayor que Minimo.", AlertType.ERROR);
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Horas dictadas, Mínimo y Máximo de horas deben ser números enteros válidos.", AlertType.ERROR);
            return;
        }

        // --- 4. Lógica de Cálculo de Nómina (Simulación) ---

        double salarioBaseHora = 50.0; // Ejemplo de tarifa por hora
        double salarioTotal = calcularNomina(horasDictadas, minHoras, maxHoras, salarioBaseHora);

        // 5. Mostrar Resultado
        mostrarAlerta("Cálculo de Nómina Exitoso",
                String.format("Nómina calculada para el Profesor %s:\n" +
                                "Horas pagables: %d\n" +
                                "Salario Total Estimado: $%.2f",
                        idProfesor, horasDictadas, salarioTotal),
                AlertType.INFORMATION);

        limpiarCampos();
    }

    /**
     * Método auxiliar para simular la lógica de cálculo de nómina.
     * (Ajusta esta lógica según las reglas de tu negocio)
     */
    private double calcularNomina(int horasDictadas, int minHoras, int maxHoras, double salarioBaseHora) {
        int horasAPagar = horasDictadas;

        if (horasDictadas < minHoras) {
            // Ejemplo: Pagar solo el mínimo si no alcanza
            horasAPagar = minHoras;
        } else if (horasDictadas > maxHoras) {
            // Ejemplo: Solo pagar hasta el máximo permitido
            horasAPagar = maxHoras;
        }

        return horasAPagar * salarioBaseHora;
    }

    /**
     * Método auxiliar para limpiar los campos de texto (Usando setText("") para Gluon AutoCompleteTextField).
     */
    private void limpiarCampos() {
        idprofenomina.setText("");
        idhorasdictadasnomina.setText("");
        idminhoras.setText("");
        idmaxhoras.setText("");
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
        System.out.println("Controlador CalcularNomina inicializado.");
        // Opcional: Aquí podrías inicializar la lista de sugerencias para idprofenomina
    }
}