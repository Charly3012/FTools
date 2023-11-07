package Modelo;

import controlador.ControladorGestionDeProveedores;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AplicacionGestionDeProveedor extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(ControladorGestionDeProveedores.class.getResource("VistaGestionDeProvedores.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }
}
