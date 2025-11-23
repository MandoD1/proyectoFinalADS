package aplicacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringJavafxIntegrationalApplication extends Application {

    private static String[] savedArgs;
    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = SpringApplication.run(SpringJavafxIntegrationalApplication.class, savedArgs);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tipologin.fxml"));
        loader.setControllerFactory(context::getBean);

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Sistema de Gestión Académica");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        context.close();
    }

    public static void main(String[] args) {
        savedArgs = args;
        launch(args); // <-- JavaFX lanza la app
    }
}
