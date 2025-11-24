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

    // ************************************************
    // 1. VARIABLES INYECTADAS (fx:id)
    // ************************************************

    // Botones
    @FXML private Button btnVolverMenuDPTO;
    @FXML private Button btnConsultarCarga;
    @FXML private Button btnConsultarprofes;
    @FXML private Button btnCalcularnomina;


    // ************************************************
    // 2. MÉTODOS DE NAVEGACIÓN (onAction)
    // ************************************************

    /**
     * Método auxiliar para cargar una nueva escena (FXML)
     */
    private void cargarEscena(ActionEvent event, String fxmlPath) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxmlPath));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Regresa al menú anterior (ej. Login o Menú Principal).
     * Corresponde a onAction="#onActionVolverMenuDPTO"
     */
    @FXML
    public void onActionVolverMenuDPTO(ActionEvent event) throws IOException {
        // Asumiendo que el menú principal anterior es "Login.fxml" o un menú de selección de rol.
        cargarEscena(event, "nTipoLogin.fxml");
    }

    /**
     * Navega a la pantalla para consultar la carga académica de un profesor.
     * Corresponde a onAction="#onActionConsultarCarga"
     */
    @FXML
    public void onActionConsultarCarga(ActionEvent event) throws IOException {
        // Asumiendo el FXML "ConsultarCargaAcademica.fxml"
        cargarEscena(event, "consultarcargaprofesdpt.fxml");
    }

    /**
     * Navega a la pantalla para generar el informe de profesores.
     * Corresponde a onAction="#onActionConsultaprofes"
     */
    @FXML
    public void onActionConsultaprofes(ActionEvent event) throws IOException {
        // Asumiendo el FXML "InformeProfesores.fxml"
        cargarEscena(event, "Consultarprofesdpt.fxml");
    }

    /**
     * Navega a la pantalla para calcular la nómina.
     * Corresponde a onAction="#onActionCalcularnomina"
     */
    @FXML
    public void onActionCalcularnomina(ActionEvent event) throws IOException {
        // Asumiendo el FXML "CalcularNomina.fxml"
        cargarEscena(event, "calcularnomina.fxml");
    }


    // ************************************************
    // 3. INICIALIZACIÓN
    // ************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador MenuDepartamento inicializado. Navegación lista.");
    }
}