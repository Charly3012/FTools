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
import modelo.Alerta;

import java.io.IOException;

/**
 *Clase encargada de controlar la vista del menú principal,
 *desde esta clase se puede acceder a los diferentes tipos de pestañas del programa.
 * @author Charly
 * @version 1.0
 */
public class ControladorMenuPrincipal {

    @FXML
    public Button btnCategorias;

    @FXML
    public Button btnClientes;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaRegistroComprasVentas.fxml"));
            Parent root = loader.load();
            ControladorRegistroVentas controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Compras y ventas");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.cerrarVentana());
            Stage myStage = (Stage) this.btnComprasYVentas.getScene().getWindow();
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaGestionDeProvedores.fxml"));
            Parent root = loader.load();
            ControladorGestionDeProveedores controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Gestión de proveedores");
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
     * Encargado de cerrar las pestañas y salir de la ejecución del programa
     * @param event Click que se registra en javaFX
     */
    @FXML
    void clickSalir(MouseEvent event) {
        Alerta alertaSalir = new Alerta("Salir de la aplicación", "Desea salir de la aplicación?\nSus datos se guardaran");
        alertaSalir.mostrarAlertaConfirmation();

        Stage stage = (Stage) imgSalir.getScene().getWindow();
        stage.close();
    }

    /**
     * Abré la pestaña de categorías
     * @param actionEvent
     */
    @FXML
    public void clickCategorias(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaCategorias.fxml"));
            Parent root = loader.load();
            ControladorCategorias controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Categorias");
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
     * Abré la pestaña de clientes
     * @param actionEvent
     */
    @FXML
    public void clickClientes(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaGestionClientes.fxml"));
            Parent root = loader.load();
            ControladorGestionClientes controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Clientes");
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(e -> controlador.cerrarVentana());
            Stage myStage = (Stage) this.btnInventarios.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
