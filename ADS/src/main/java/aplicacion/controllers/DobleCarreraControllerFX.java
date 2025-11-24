package aplicacion.controllers;

import aplicacion.client.BackendClientEstudiante;
import aplicacion.repository.UsuarioRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DobleCarreraControllerFX implements Initializable {

    @FXML private Button btnVolverEstudMenu;
    @FXML private MenuButton idMenuProfes;
    @FXML private MenuItem idCarrera1;
    @FXML private MenuItem idCarrera2;
    @FXML private RadioButton idIngSistemas;
    @FXML private RadioButton idArtes;
    @FXML private Button btnInscribir;

    private BackendClientEstudiante backend;
    private UsuarioRepository usuarioRepository;

    // Almacena las carreras seleccionadas
    private final List<Long> carrerasSeleccionadas = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controlador DobleCarrera inicializado.");
        usuarioRepository = new UsuarioRepository();
        backend = new BackendClientEstudiante(usuarioRepository);
    }

    @FXML
    public void onActionVolverEstudMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/EstudianteMenu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    // =================================================
    // SELECCIÓN DE CARRERAS
    // =================================================
    @FXML
    public void OnActionCarrera1(ActionEvent event) {
        carrerasSeleccionadas.clear();
        carrerasSeleccionadas.add(1L); // ID de la primera carrera
        System.out.println("Carrera 1 seleccionada");
    }

    @FXML
    public void OnActionCarrera2(ActionEvent event) {
        carrerasSeleccionadas.clear();
        carrerasSeleccionadas.add(2L); // ID de la segunda carrera
        System.out.println("Carrera 2 seleccionada");
    }

    @FXML
    public void onActionCarreraing(ActionEvent event) {
        if (!carrerasSeleccionadas.contains(10L)) { // ejemplo: ID de Ingeniería de Sistemas
            carrerasSeleccionadas.add(10L);
            System.out.println("Carrera Ingeniería de Sistemas seleccionada");
        }
    }

    @FXML
    public void onActionCarreraArtes(ActionEvent event) {
        if (!carrerasSeleccionadas.contains(20L)) { // ejemplo: ID de Artes
            carrerasSeleccionadas.add(20L);
            System.out.println("Carrera Artes seleccionada");
        }
    }

    @FXML public void onActionSeleccionarprofe(ActionEvent event) {}

    // =================================================
    // INSCRIPCIÓN
    // =================================================
    @FXML
    public void onActionInscribirCarrera(ActionEvent event) {
        if (carrerasSeleccionadas.isEmpty()) {
            mostrarAlerta("Error", "No se ha seleccionado ninguna carrera.", Alert.AlertType.ERROR);
            return;
        }

        try {
            Long estudianteId = usuarioRepository.loadUsuario().getId();

            // Inscribir cada carrera
            for (Long carreraId : carrerasSeleccionadas) {
                backend.asignarCarrera(estudianteId, carreraId);
            }

            mostrarAlerta("Éxito", "Carrera(s) inscrita(s) correctamente.", Alert.AlertType.INFORMATION);
            carrerasSeleccionadas.clear();

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo inscribir la(s) carrera(s): " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
