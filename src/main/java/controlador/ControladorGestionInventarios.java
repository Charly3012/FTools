package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase encargada en controlar la vista de la pestaña "Gestión de intentarios",
 * así como es la encargada de realizar las operaciones necesarias para mostrar la información correspondiente
 * de la vista antes mencionada
 * @author Charly
 * @version 1.0
 */
public class ControladorGestionInventarios {

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private Button btnGuardarProducto;

    @FXML
    private TableColumn<?, ?> colCategoriasProducto;

    @FXML
    private TableColumn<?, ?> colInventarioProducto;

    @FXML
    private TableColumn<?, ?> colNombreProducto;

    @FXML
    private TableColumn<?, ?> colSkuProducto;

    @FXML
    private ComboBox<?> comFiltrarPor;

    @FXML
    private ComboBox<?> comFiltro;

    @FXML
    private MenuButton comMenuDesplegable;

    @FXML
    private Label labNombrePestaña;

    @FXML
    private TableView<?> tblProductosGestionInventarios;

    @FXML
    private TextField txtBuscarProducto;

    @FXML
    private TextField txtCategoriaProducto;

    @FXML
    private TextField txtCodigoBarrasProducto;

    @FXML
    private TextArea txtDescripcionProducto;

    @FXML
    private TextField txtExistenciaProducto;

    @FXML
    private TextField txtMarcaProducto;

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtPrecioProducto;

    @FXML
    private TextField txtSkuProducto;

    /**
     * Se encarga de desplegar una ventana emergente donde se puede ingresar la información de nuevo producto,
     * dicho producto se agrega a la base de datos
     * @param event Recibe la ejecución de un click en el botón correspondiente
     */
    @FXML
    void ClickAgregarProducto(ActionEvent event) {

    }

    /**
     * Manda la información correspondiente a un parametro de busqueda y despliega los resultados correspondientes
     *
     * @param event Recibe la ejecución de un click en el botón correspondiente
     */
    @FXML
    void ClickBuscar(ActionEvent event) {

    }

    /**
     * Elimina el producto seleccionado de la base de datos
     * @param event Recibe la ejecución de un click en el botón correspondiente
     */
    @FXML
    void ClickEliminarProducto(ActionEvent event) {

    }

    /**
     * Guarda los cambios correspondientes en la base de datos, actualiza la información que haya sido alterada
     * @param event Recibe la ejecución de un click en el botón correspondiente
     */
    @FXML
    void ClickGuardarProducto(ActionEvent event) {

    }

    /**
     * Cierra la ventana y regresa al menú principal
     */
    public void cerrarVentana(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaMenuPrincipal.fxml"));
            Parent root = loader.load();
            ControladorMenuPrincipal controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("FTools");
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnBuscar.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
