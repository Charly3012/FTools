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

/**
 *Clase encargada de controlar la vista del menú principal,
 *desde esta clase se puede acceder a los diferentes tipos de pestañas del programa.
 * @author Charly
 * @version 1.0
 */
public class ControladorMenuPrincipal {


    @FXML
    private Button btnComprasYVentas;

    @FXML
    private Button btnInventarios;

    @FXML
    private Button btnProveedores;

    @FXML
    private ImageView imgSalir;


    /**
     * Método encargado de desplegar a la pestaña "Gestión de compras y ventas"
     * @param event Click que se regitra en el javaFX
     */
    @FXML
    void clickComprasYVentas(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/RegistroventasVista.fxml"));
            Parent root = loader.load();
            ControladorRegistroVentas controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Gestion de compras y ventas");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.cerrarVentana());
            Stage myStage = (Stage) this.btnInventarios.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método encargado de desplegar a la pestaña "Gestión de inventarios"
     * @param event Click que se regitra en el javaFX
     */
    @FXML
    void clickInventarios(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaGestionInventarios.fxml"));
            Parent root = loader.load();
            ControladorGestionInventarios controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Gestión de inventarios");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.cerrarVentana());
            Stage myStage = (Stage) this.btnInventarios.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método encargado de desplegar a la pestaña "Gestión de proveedores"
     * @param event Click que se regitra en el javaFX
     */
    @FXML
    void clickProveedores(ActionEvent event) {

    }

    /**
     * Encargado de cerrar las pestañas y salir de la ejecución del programa
     * @param event Click que se registra en javaFX
     */
    @FXML
    void clickSalir(MouseEvent event) {
        Stage stage = (Stage) imgSalir.getScene().getWindow();
        stage.close();
    }

}
