package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

//importación de la librería


public class ControladorReportes implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnReporteProductos;

    @FXML
    private Label labNombrePestana;

    @FXML
    void clickReporteProductos(ActionEvent event) {

    }

    /**
     * Métodos que se ejecutan al iniciar la pestaña
     */
    @FXML
    void initialize() {
        assert btnReporteProductos != null : "fx:id=\"btnReporteProductos\" was not injected: check your FXML file 'VistaReportes.fxml'.";
        assert labNombrePestana != null : "fx:id=\"labNombrePestana\" was not injected: check your FXML file 'VistaReportes.fxml'.";

    }
    /**
     * Métodos que se ejecutan al iniciar la pestaña
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
