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
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuDepartamentoControllerFX implements Initializable {

    @FXML private Button btnVolverMenuDPTO;
    @FXML private Button btnConsultarCarga;
    @FXML private Button btnConsultarprofes;
    @FXML private Button btnCalcularnomina;

    private void cargarEscena(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlPath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionVolverMenuDPTO(ActionEvent event) throws IOException {
        cargarEscena(event, "nTipoLogin.fxml");
    }

    @FXML
    public void onActionConsultarCarga(ActionEvent event) throws IOException {
        cargarEscena(event, "consultarcargaprofesdpt.fxml");
    }

    @FXML
    public void onActionConsultaprofes(ActionEvent event) throws IOException {
        cargarEscena(event, "Consultarprofesdpt.fxml");
    }

    @FXML
    public void onActionCalcularnomina(ActionEvent event) throws IOException {
        cargarEscena(event, "calcularnomina.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador MenuDepartamento inicializado. Navegación lista.");
    }
}
