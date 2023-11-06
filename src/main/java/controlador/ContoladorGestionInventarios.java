package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ContoladorGestionInventarios {

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

    @FXML
    void ClickAgregarProducto(ActionEvent event) {

    }

    @FXML
    void ClickBuscar(ActionEvent event) {

    }

    @FXML
    void ClickEliminarProducto(ActionEvent event) {

    }

    @FXML
    void ClickGuardarProducto(ActionEvent event) {

    }

}
