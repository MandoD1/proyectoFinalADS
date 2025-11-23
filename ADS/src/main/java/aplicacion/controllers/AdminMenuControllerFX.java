// Archivo: AdminMenuController.java
package aplicacion.controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//API


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class   AdminMenuControllerFX implements Initializable {

    // 1. VARIABLES INYECTADAS (fx:id) - ¡CORREGIDAS PARA COINCIDIR CON EL FXML!
    @FXML private Button btnVolvertipoLogin;
    @FXML private Button btnConsultarCarga;
    @FXML private Button btnGenerarReporte;

    // **CORRECCIÓN DE IMÁGENES:**
    @FXML private ImageView ConsultaCarga; // Coincide con fx:id="ConsultaCarga"
    @FXML private ImageView GenerarReporte; // Coincide con fx:id="GenerarReporte"

    // 3. INICIALIZACIÓN (Carga de Imágenes)
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Lógica de CARGA DE IMÁGENES
        try {
            // Asumiendo que los archivos están en /img/
            Image cargaImg = new Image(getClass().getResourceAsStream("/img/ConsultarCarga.png"));
            ConsultaCarga.setImage(cargaImg); // Usamos la variable corregida

            Image reporteImg = new Image(getClass().getResourceAsStream("/img/GenerarReporte.png"));
            GenerarReporte.setImage(reporteImg); // Usamos la variable corregida
        } catch (Exception e) {
            System.err.println("Error al cargar imágenes del Menú Administrador: " + e.getMessage());
        }
    }

    // 2. MÉTODOS DE EVENTO (onAction)

    @FXML
    public void onActionVolvertipoLogin(ActionEvent event) throws IOException {
        System.out.println("Navegando a: tipologin.fxml");
        Parent root = FXMLLoader.load(getClass().getResource("/tipologin.fxml"));
        cambiarEscena(event, root);
    }

    @FXML
    public void onActionConsultarCarga(ActionEvent event) throws IOException {
        System.out.println("Navegando a: ConsultarCarga.fxml");
        Parent root = FXMLLoader.load(getClass().getResource("/ConsultarCarga.fxml"));
        cambiarEscena(event, root);
    }

    @FXML
    public void onActionGenerarReporte(ActionEvent event) throws IOException {
        System.out.println("Navegando a: GenerarReporte.fxml");
        Parent root = FXMLLoader.load(getClass().getResource("/GenerarReporte.fxml"));
        cambiarEscena(event, root);
    }

    private void cambiarEscena(ActionEvent event, Parent root) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}