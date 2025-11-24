package aplicacion.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aplicacion.client.BackendClientDirectorDepartamento;

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

import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.stage.Stage;

public class CalcularNominaControllerFX implements Initializable {

    @FXML private AutoCompleteTextField idprofenomina;
    @FXML private AutoCompleteTextField idhorasdictadasnomina;
    @FXML private AutoCompleteTextField idminhoras;
    @FXML private AutoCompleteTextField idmaxhoras;
    @FXML private Button btnVolverMenuDPTO;
    @FXML private Button btnCalcularnomina;

    private BackendClientDirectorDepartamento backend = new BackendClientDirectorDepartamento();

    @FXML
    public void onActionVolverMenuDPTO(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorDPT.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void OnActionCalcularNomina(ActionEvent event) {
        String idProfesor = idprofenomina.getText().trim();
        String horasDictadasStr = idhorasdictadasnomina.getText().trim();
        String minHorasStr = idminhoras.getText().trim();
        String maxHorasStr = idmaxhoras.getText().trim();

        if (idProfesor.isEmpty() || horasDictadasStr.isEmpty() || minHorasStr.isEmpty() || maxHorasStr.isEmpty()) {
            mostrarAlerta("Error de Validación", "Todos los campos son obligatorios.", AlertType.ERROR);
            return;
        }

        int horasDictadas, minHoras, maxHoras;
        try {
            horasDictadas = Integer.parseInt(horasDictadasStr);
            minHoras = Integer.parseInt(minHorasStr);
            maxHoras = Integer.parseInt(maxHorasStr);

            if (horasDictadas < 0 || minHoras < 0 || maxHoras <= 0 || minHoras >= maxHoras) {
                mostrarAlerta("Error de Validación", "Valores inválidos. Máximo debe ser mayor que Mínimo.", AlertType.ERROR);
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Formato", "Horas deben ser números enteros.", AlertType.ERROR);
            return;
        }

        double salarioTotal = backend.calcularPago(Long.parseLong(idProfesor), horasDictadas, minHoras, maxHoras);

        mostrarAlerta("Cálculo de Nómina",
                String.format("Profesor %s:\nHoras pagables: %d\nSalario estimado: $%.2f",
                        idProfesor, horasDictadas, salarioTotal),
                AlertType.INFORMATION);

        limpiarCampos();
    }

    private void limpiarCampos() {
        idprofenomina.setText("");
        idhorasdictadasnomina.setText("");
        idminhoras.setText("");
        idmaxhoras.setText("");
    }

    private void mostrarAlerta(String titulo, String mensaje, AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador CalcularNomina inicializado.");
    }
}
