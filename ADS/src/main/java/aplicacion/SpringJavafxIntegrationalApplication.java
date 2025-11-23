//package aplicacion;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//import java.io.IOException;
//
//@SpringBootApplication
//public class SpringJavafxIntegrationalApplication extends Application {
//
//    public static ConfigurableApplicationContext applicationContext;
//    public static Parent rootNode;
//    public static Stage stage;
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/tipologin.fxml"));
//        Parent root = fxmlLoader.load();
//
//        Scene scene = new Scene(root);
//
//        stage.setTitle("Sistema de Gestión Académica");
//        stage.setScene(scene);
//        stage.setResizable(false);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//
//}
package aplicacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringJavafxIntegrationalApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringJavafxIntegrationalApplication.class, args);
    }
}