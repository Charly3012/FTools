package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Clase encargada de ejecutar la pestaña principal de la aplicación
 * @author Charly
 * @version 1.0
 */
public class
Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creación de la primera "Stage" utilizando la vista del menu principal
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/vista/VistaMenuPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 500);
        stage.setTitle("FTools");
        stage.setScene(scene);
        stage.show();
    }
}
