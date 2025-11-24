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

public class ConsultarCargaAcademicadptControllerFX implements Initializable {

    @FXML private AutoCompleteTextField idprofeconsultacargaacademica;
    @FXML private Button btnVolverMenuDPTO;
    @FXML private Button btnconsultarcargaprofes;

    private BackendClientDirectorDepartamento backend = new BackendClientDirectorDepartamento();

    @FXML
    public void onActionVolverMenuDPTO(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorDPT.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionconsultarcargaprofes(ActionEvent event) {
        String idProfesor = idprofeconsultacargaacademica.getText().trim();

        if (idProfesor.isEmpty()) {
            mostrarAlerta("Error de Validación", "Debe digitar el ID del profesor.", AlertType.ERROR);
            return;
        }

        try {
            Long id = Long.parseLong(idProfesor);

            backend.generarInformeProfesor(id);

            mostrarAlerta("Carga Académica", "Se generó el informe para el profesor " + id, AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser numérico.", AlertType.ERROR);
        }

        idprofeconsultacargaacademica.setText("");
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
        System.out.println("Controlador ConsultarCargaAcademicadpt inicializado.");
    }
}
