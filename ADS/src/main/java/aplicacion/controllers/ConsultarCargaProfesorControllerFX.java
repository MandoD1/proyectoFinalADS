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
import javafx.scene.control.TextField;
import com.gluonhq.charm.glisten.control.AutoCompleteTextField;
import javafx.stage.Stage;

public class ConsultarCargaProfesorControllerFX implements Initializable {

    @FXML private Button btnVolverAdminMenu;
    @FXML private Button btnConsultarCarga;

    @FXML private TextField idprofesorConsultar;
    @FXML private AutoCompleteTextField idHorassemalaesprof;
    @FXML private AutoCompleteTextField idTipoContrato;
    @FXML private AutoCompleteTextField idCalculoNomina;

    private BackendClientDirectorDepartamento backend = new BackendClientDirectorDepartamento();

    @FXML
    public void onActionVolverAdminMenu(ActionEvent event) throws IOException {
        cargarEscenaSegura(event, "/menudirectordept.fxml");
    }

    @FXML
    public void onActionConsultarCarga(ActionEvent event) {
        String idProfesor = idprofesorConsultar.getText().trim();

        if (idProfesor.isEmpty()) {
            mostrarAlertaError("Entrada Incompleta", "Digite el ID del profesor a consultar.");
            limpiarCamposSalida();
            return;
        }

        try {
            Long id = Long.parseLong(idProfesor);
            backend.generarInformeProfesor(id); // Uso del cliente backend
            mostrarAlertaInformacion("Informe generado", "Se generó el informe para el profesor " + id);
        } catch (NumberFormatException e) {
            mostrarAlertaError("Formato inválido", "El ID debe ser numérico.");
        }
        limpiarCamposSalida();
    }

    private void limpiarCamposSalida() {
        idHorassemalaesprof.setText("");
        idTipoContrato.setText("");
        idCalculoNomina.setText("");
    }

    private void mostrarAlertaError(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarAlertaInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cargarEscenaSegura(ActionEvent event, String fxmlPath) throws IOException {
        URL location = getClass().getResource(fxmlPath);
        if (location == null) {
            mostrarAlertaError("Error de Navegación", "No se pudo encontrar el archivo FXML: " + fxmlPath);
            return;
        }
        Parent root = FXMLLoader.load(location);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador ConsultarCargaProfesorController inicializado.");
        limpiarCamposSalida();
    }
}
