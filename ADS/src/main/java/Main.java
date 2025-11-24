// Archivo: Main.java

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application { // ¡El nombre de la clase es Main!

    // Este método se llama cuando se inicia la aplicación JavaFX.
    @Override
    public void start(Stage stage) throws IOException {

        // Cargar la vista inicial (ntipologin.fxml)
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/nTipologin.fxml"));
        Parent root = fxmlLoader.load();

        // Configurar la escena
        Scene scene = new Scene(root);

        // Configurar y mostrar la ventana (Stage)
        stage.setTitle("Sistema de Gestión Académica");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    // El método main es el punto de entrada estándar de Java.
    public static void main(String[] args) {
        launch();
    }
}