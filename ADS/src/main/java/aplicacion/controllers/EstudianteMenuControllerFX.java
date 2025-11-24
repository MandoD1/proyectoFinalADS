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

public class EstudianteMenuControllerFX implements Initializable {

    @FXML private Button btnVolvertipoLogin;
    @FXML private Button btnCrearHorario;
    @FXML private Button btnRetirarMaterias;
    @FXML private Button btnConsultarAsignatura;
    @FXML private Button btnDobleCarrera;
    @FXML private Button btnConsultarHorario;

    @FXML
    public void onActionVolvertipoLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/nTipoLogin.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionCrearHorario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/CREARHORARIO.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionRetirarMaterias(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/RetirarMateria.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionConsultarAsignatura(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ConsultarAsignatura.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionDobleCarrera(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/DobleCarrera.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionConsultarHorario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ConsultarHorario.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onActionretirarcarrera(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/RETIRARCARRERA.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador EstudianteMenu inicializado.");
    }
}
