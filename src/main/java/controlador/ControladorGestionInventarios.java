package controlador;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Clase encargada en controlar la vista de la pestaña "Gestión de intentarios",
 * así como es la encargada de realizar las operaciones necesarias para mostrar la información correspondiente
 * de la vista antes mencionada
 * @author Charly
 * @version 1.0
 */
public class ControladorGestionInventarios implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private TableView<Producto> tblProductosGestionInventarios;

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

    private ObservableList<Producto> productos;

    public void initialize(URL url, ResourceBundle rb) {
        assert btnAgregarProducto != null : "fx:id=\"btnAgregarProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert btnEliminarProducto != null : "fx:id=\"btnEliminarProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert btnGuardarProducto != null : "fx:id=\"btnGuardarProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert colCategoriasProducto != null : "fx:id=\"colCategoriasProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert colInventarioProducto != null : "fx:id=\"colInventarioProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert colNombreProducto != null : "fx:id=\"colNombreProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert colSkuProducto != null : "fx:id=\"colSkuProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert comFiltrarPor != null : "fx:id=\"comFiltrarPor\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert comFiltro != null : "fx:id=\"comFiltro\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert comMenuDesplegable != null : "fx:id=\"comMenuDesplegable\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert labNombrePestaña != null : "fx:id=\"labNombrePestaña\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert tblProductosGestionInventarios != null : "fx:id=\"tblProductosGestionInventarios\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtBuscarProducto != null : "fx:id=\"txtBuscarProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtCategoriaProducto != null : "fx:id=\"txtCategoriaProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtCodigoBarrasProducto != null : "fx:id=\"txtCodigoBarrasProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtDescripcionProducto != null : "fx:id=\"txtDescripcionProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtExistenciaProducto != null : "fx:id=\"txtExistenciaProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtMarcaProducto != null : "fx:id=\"txtMarcaProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtNombreProducto != null : "fx:id=\"txtNombreProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtPrecioProducto != null : "fx:id=\"txtPrecioProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtSkuProducto != null : "fx:id=\"txtSkuProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";

        productos = FXCollections.observableArrayList();

        this.colNombreProducto.setCellValueFactory((new PropertyValueFactory("nombre")));
        this.colCategoriasProducto.setCellValueFactory((new PropertyValueFactory("categoria")));
        this.colInventarioProducto.setCellValueFactory((new PropertyValueFactory("cantExistencia")));
        this.colSkuProducto.setCellValueFactory((new PropertyValueFactory("sku")));


    }

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
        String nombre = this.txtNombreProducto.getText();
        String categoria = this.txtCategoriaProducto.getText();
        int cantExistencia = Integer.parseInt(this.txtExistenciaProducto.getText());
        String sku = this.txtSkuProducto.getText();

        Producto p = new Producto(nombre, categoria, cantExistencia, sku);


        try{
            if(!this.productos.contains(p)){
                this.productos.add(p);
                this.tblProductosGestionInventarios.setItems(productos);
            }
            else{
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setTitle("Error");
                alerta.setContentText("Producto ya existe");
                alerta.showAndWait();
            }
        }catch(NumberFormatException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Error");
            alerta.setContentText("Error");
            alerta.showAndWait();
        }


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
