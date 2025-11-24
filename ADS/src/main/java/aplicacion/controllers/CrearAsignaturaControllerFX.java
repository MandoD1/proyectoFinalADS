package aplicacion.controllers;

import aplicacion.client.BackendClientDirectorCarrera;
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

import com.gluonhq.charm.glisten.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CrearAsignaturaControllerFX implements Initializable {

    @FXML private TextField idasignaturacrear;
    @FXML private TextField idcupomaxcrearasignatura; // si aplica
    @FXML private TextField idsemestrecreasasignatura;
    @FXML private TextField iddeptcrearasignatura;
    @FXML private TextField idcrearasignaturacreditos;
    @FXML private TextField idasignaturacrearnombre;

    @FXML private Button btnVolverMenuDCarrera;
    @FXML private Button btnCrearAasigntura;

    private final BackendClientDirectorCarrera backend = new BackendClientDirectorCarrera();

    @FXML
    public void onActionVolverMenuDCarrera(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("menudirectorcarrera.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void OnActionCrearAsignatura(ActionEvent event) {
        try {
            String nombre = idasignaturacrearnombre.getText().trim();
            String idStr = idasignaturacrear.getText().trim();
            String semestreStr = idsemestrecreasasignatura.getText().trim();
            String deptStr = iddeptcrearasignatura.getText().trim();
            String creditosStr = idcrearasignaturacreditos.getText().trim();

            if (idStr.isEmpty() || nombre.isEmpty() || semestreStr.isEmpty() || deptStr.isEmpty() || creditosStr.isEmpty()) {
                mostrarAlerta("Error", "Todos los campos son obligatorios.", AlertType.ERROR);
                return;
            }

            Long semestreId = Long.parseLong(semestreStr);
            Long deptId = Long.parseLong(deptStr);
            int creditos = Integer.parseInt(creditosStr);
            Long asignaturaId = Long.parseLong(idStr);

            // Por ahora listas vacías de prerequisitos y corequisitos
            List<Long> prerequisitos = List.of();
            List<Long> corequisitos = List.of();
            boolean requisitoIngles = false;

            backend.crearAsignatura(semestreId, deptId, nombre, corequisitos, requisitoIngles, prerequisitos, creditos);

            mostrarAlerta("Éxito", "Asignatura creada correctamente.", AlertType.INFORMATION);
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Campos numéricos no válidos.", AlertType.ERROR);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo crear la asignatura: " + e.getMessage(), AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        idasignaturacrear.setText("");
        idcupomaxcrearasignatura.setText("");
        idsemestrecreasasignatura.setText("");
        iddeptcrearasignatura.setText("");
        idcrearasignaturacreditos.setText("");
        idasignaturacrearnombre.setText("");
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
        System.out.println("Controlador CrearAsignatura inicializado.");
    }
}
