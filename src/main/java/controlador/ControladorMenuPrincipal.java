package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorMenuPrincipal {

    @FXML
    private Button btnComprasYVentas;

    @FXML
    private Button btnInventarios;

    @FXML
    private Button btnProveedores;

    @FXML
    private ImageView imgSalir;

    @FXML
    void clickComprasYVentas(ActionEvent event) {

    }

    @FXML
    void clickInventarios(ActionEvent event) {


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaGestionInventarios.fxml"));

            Parent root = loader.load();

            ControladorGestionInventarios controlador = loader.getController();

            Scene scene = new Scene(root);

            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(e -> controlador.cerrarVentana());

            Stage myStage = (Stage) this.btnInventarios.getScene().getWindow();

            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }

    @FXML
    void clickProveedores(ActionEvent event) {

    }

    @FXML
    void clickSalir(MouseEvent event) {

    }

}
