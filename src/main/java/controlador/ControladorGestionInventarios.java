package controlador;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Alerta;
import modelo.Producto;

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
    public Button btnEditarProducto;

    @FXML
    public TableColumn colMarcaProducto;

    @FXML
    public TableColumn colPrecioUnitario;

    @FXML
    public TableColumn colCodigoBarrasProducto;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnEliminarProducto;


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
    private MenuButton comMenuDesplegable;

    @FXML
    private Label labNombrePestana;

    @FXML
    private TableView<Producto> tblProductosGestionInventarios;

    @FXML
    private TextField txtBuscarProducto;


    private ObservableList<Producto> productos;

    public void initialize(URL url, ResourceBundle rb) {
        assert btnAgregarProducto != null : "fx:id=\"btnAgregarProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert btnEliminarProducto != null : "fx:id=\"btnEliminarProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert colCategoriasProducto != null : "fx:id=\"colCategoriasProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert colInventarioProducto != null : "fx:id=\"colInventarioProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert colNombreProducto != null : "fx:id=\"colNombreProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert colSkuProducto != null : "fx:id=\"colSkuProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert comFiltrarPor != null : "fx:id=\"comFiltrarPor\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert comMenuDesplegable != null : "fx:id=\"comMenuDesplegable\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert labNombrePestana != null : "fx:id=\"labNombrePestaña\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert tblProductosGestionInventarios != null : "fx:id=\"tblProductosGestionInventarios\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";
        assert txtBuscarProducto != null : "fx:id=\"txtBuscarProducto\" was not injected: check your FXML file 'VistaGestionInventarios.fxml'.";



        productos = FXCollections.observableArrayList(); //Se tiene que crear otro arraylist que va a ser el que se va a visualizar en la vista


        //mapeo de las columnas de la tabla con los atributos de los objetos persona
        this.colNombreProducto.setCellValueFactory((new PropertyValueFactory("nombre")));
        this.colMarcaProducto.setCellValueFactory((new PropertyValueFactory("marca")));
        this.colSkuProducto.setCellValueFactory((new PropertyValueFactory("sku")));
        this.colCodigoBarrasProducto.setCellValueFactory((new PropertyValueFactory("codigoBarras")));
        this.colInventarioProducto.setCellValueFactory((new PropertyValueFactory("cantExistencia")));
        this.colPrecioUnitario.setCellValueFactory((new PropertyValueFactory("precioUnitario")));
        this.colCategoriasProducto.setCellValueFactory((new PropertyValueFactory("categoria")));
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
        Producto productoSeleccionado = this.tblProductosGestionInventarios.getSelectionModel().getSelectedItem();

        if(productoSeleccionado == null){
            Alerta alertaSeleccionarProducto = new Alerta("Error", "Debes seleccionar un producto");
            alertaSeleccionarProducto.mostrarAlertaError();
        }else{
            this.productos.remove(productoSeleccionado);
            this.tblProductosGestionInventarios.refresh();

            Alerta alertaProductoEliminado = new Alerta("Producto eliminado", "El producto seleccionado se ha eliminado con exito");
            alertaProductoEliminado.mostrarAlertaInformation();
        }
    }

    /**
     * Lanza una pestaña con la posibilidad de editar el producto seleccionado
     * @param actionEvent Recibe la ejecución de un click en el botón correspondiente
     */
    @FXML
    public void ClickEditarProducto(ActionEvent actionEvent) {
    }

    /**
     * Se encarga de desplegar una ventana emergente donde se puede ingresar la información de nuevo producto,
     * dicho producto se agrega a la base de datos
     * @param event Recibe la ejecución de un click en el botón correspondiente
     */
    @FXML
    void ClickAgregarProducto(ActionEvent event) {

        //Abrir una ventana modal, y si, cada que quieras abrir una tienes que escribir esto
        try {
            FXMLLoader vistaEmergente = new FXMLLoader(getClass().getResource("/vista/VistaEmAgregarProducto.fxml"));
            Parent root = vistaEmergente.load();

            ControladorEmAgregarProducto controlador = vistaEmergente.getController();
            controlador.inicializarAtributos(productos);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            //Abre para agregar a la persona y si es correcto sigue con lo que sigue de código

            Producto nuevoProducto = controlador.getNuevoProducto();
            if (nuevoProducto != null){
                this.productos.add(nuevoProducto);
                this.tblProductosGestionInventarios.setItems(productos);

            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }





        /*
        Producto productoSeleccionado = this.tblProductosGestionInventarios.getSelectionModel().getSelectedItem();

        if(productoSeleccionado == null){
            Alerta alertaSeleccionarProducto = new Alerta("Error", "Debes seleccionar un producto");
            alertaSeleccionarProducto.mostrarAlertaError();
        }else {

        }*/

    }

    /* Codigo anterior para agregar un elemento a la tabla por medio de una observable list
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
     */

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
