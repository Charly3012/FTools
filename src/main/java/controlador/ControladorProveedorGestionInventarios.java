/*import javafx.scene.control.cell.PropertyValueFactory;

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
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Alerta;
import modelo.ProductoProveedor;
import modelo.Proveedor;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
*/

/**
 * Clase encargada en controlar la vista de la pestaña "Gestión de intentarios",
 * así como es la encargada de realizar las operaciones necesarias para mostrar la información correspondiente
 * de la vista antes mencionada
 * @author Charly
 * @version 1.0
 */

/*
public class ControladorProveedorGestionInventarios implements Initializable{


    @FXML
    public Button btnEditarProducto;


    @FXML
    public TableColumn<?, ?> colMarcaProducto;

    @FXML
    public TableColumn<?, ?> colPrecioUnitario;

    @FXML
    public TableColumn<?, ?> colCodigoBarrasProducto;

    @FXML
    private Button btnAgregarProducto;

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
    private TableView<ProductoProveedor> tblProductosGestionInventarios;

    @FXML
    private TextField txtBuscarProducto;

    @FXML //Lista que se muestra de productos
    private ObservableList<ProductoProveedor> productosproveedores;

    @FXML //Lista que se muestra cuando se está buscando entre los productos
    private ObservableList<ProductoProveedor> busquedaProductos;

    private Proveedor proveedorActual;

    private ObservableList<ProductoProveedor> productosProveedorActual;

    private static final long serialId = 1L;



    public void initialize(Proveedor proveedor, ObservableList<ProductoProveedor> productosproveedores) {
        this.proveedorActual = proveedor;
        this.productosProveedorActual = productosproveedores.filtered(p -> p.getProveedor().equals(proveedor.getNombre()));
        this.tblProductosGestionInventarios.setItems(this.productosProveedorActual);

        iniciarDatosObservables();
        persistenciaLeer();


    }



    /**
     * Elimina el producto seleccionado de la base de datos
     * @param event Recibe la ejecución de un click en el botón correspondiente
     */
/*
    @FXML
    void ClickEliminarProducto(ActionEvent event) {

        //Para seleccionar un objeto
        ProductoProveedor productoSeleccionado = this.tblProductosGestionInventarios.getSelectionModel().getSelectedItem();

        if(productoSeleccionado == null){
            Alerta alertaSeleccionarProducto = new Alerta("Error", "Debes seleccionar un producto");
            alertaSeleccionarProducto.mostrarAlertaError();
        }else{
            this.productosproveedores.remove(productoSeleccionado);
            this.busquedaProductos.remove(productoSeleccionado);
            this.tblProductosGestionInventarios.refresh();

            Alerta alertaProductoEliminado = new Alerta("Producto eliminado", "El producto seleccionado se ha eliminado con exito");
            alertaProductoEliminado.mostrarAlertaInformation();
        }
    }

    /**
     * Lanza una pestaña con la posibilidad de editar el producto seleccionado
     * @param actionEvent Recibe la ejecución de un click en el botón correspondiente
     */

/*
    @FXML
    public void ClickEditarProducto(ActionEvent actionEvent) {

        //Seleccionar al producto a editar
        ProductoProveedor productoSeleccionado = this.tblProductosGestionInventarios.getSelectionModel().getSelectedItem();

        if(productoSeleccionado == null){
            Alerta alertaSeleccionarProducto = new Alerta("Error", "Debes seleccionar un producto");
            alertaSeleccionarProducto.mostrarAlertaError();
        }else{
            //Abrir una ventana modal, y si, cada que quieras abrir una tienes que escribir esto
            try {
                FXMLLoader vistaEmergente = new FXMLLoader(getClass().getResource("/vista/VistaProveedorEmAgregarProducto.fxml"));
                Parent root = vistaEmergente.load();

                ControladorProveedorEmAgregarProducto controlador = vistaEmergente.getController();
                controlador.inicializarAtributos(productosproveedores, productoSeleccionado);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                //Abre para agregar a la persona y si es correcto sigue con lo que sigue de código

                ProductoProveedor productoEditado = controlador.getProductoAux();

                if (productoEditado != null){

                    //Para evitar conflictos cuando se está buscando
                    if (!(productoEditado.getNombre().toLowerCase().contains(this.txtBuscarProducto.getText().toLowerCase()))){
                        this.busquedaProductos.remove(productoEditado);
                    }
                    this.tblProductosGestionInventarios.refresh();

                }

            } catch (IOException e) {
                Alerta alerta = new Alerta("Error", e.getMessage());
                alerta.mostrarAlertaError();
            }

        }

    }

    /**
     * Se encarga de desplegar una ventana emergente donde se puede ingresar la información de nuevo producto,
     * dicho producto se agrega a la base de datos
     * @param event Recibe la ejecución de un click en el botón correspondiente
     */

/*
    @FXML
    void ClickAgregarProducto(ActionEvent event) {

        //Abrir una ventana modal, y si, cada que quieras abrir una tienes que escribir esto
        try {
            FXMLLoader vistaEmergente = new FXMLLoader(getClass().getResource("/vista/VistaProveedorEmAgregarProducto.fxml"));
            Parent root = vistaEmergente.load();

            ControladorProveedorEmAgregarProducto controlador = vistaEmergente.getController();
            controlador.inicializarAtributos(productosproveedores);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            //Abre para agregar a la persona y si es correcto sigue con lo que sigue de código

            ProductoProveedor nuevoProducto = controlador.getProductoAux();
            if (nuevoProducto != null){
                this.productosproveedores.add(nuevoProducto);

                //Para evitar conflictos cuando se está buscando
                if (nuevoProducto.getNombre().toLowerCase().contains(this.txtBuscarProducto.getText().toLowerCase())){
                    this.busquedaProductos.add(nuevoProducto);
                }
                this.tblProductosGestionInventarios.refresh();

            }

        } catch (IOException e) {
            Alerta alerta = new Alerta("Error", e.getMessage());
            alerta.mostrarAlertaError();
        }
    }

    /**
     * Código util que se ejecuta cuando se cierra la ventana
     * Su principal funcion es retornar a la pestaña previa así como llamar que permite la persistencia
     */

/*
    public void cerrarVentana(){

        persistenciaEscribir();

        //Retorna a la ventana anterior cuando se cierra
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaMenuPrincipal.fxml"));
            Parent root = loader.load();
            ControladorMenuPrincipal controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("FTools");
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnAgregarProducto.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Permite realizar busquedas por nombre en la tabla principal
     * @param keyEvent
     */

/*
    @FXML
    public void escribirEnBuscar(KeyEvent keyEvent) {

        String busqueda = this.txtBuscarProducto.getText();

        if(busqueda.isEmpty()){
            this.tblProductosGestionInventarios.setItems(productosproveedores);
        }
        else{
            this.busquedaProductos.clear();
            for (ProductoProveedor producto : this.productosproveedores){
                if(producto.getNombre().toLowerCase().contains(busqueda.toLowerCase())){
                    this.busquedaProductos.add(producto);
                }
            }
            this.tblProductosGestionInventarios.setItems(busquedaProductos);
        }
    }

    /**
     * Lee los registros de sesiones pasadas permitiendo la persistencia de datos
     */

/*
    public void persistenciaLeer(){
        File comprobracionExist = new File("src/main/resources/persistencia/gestionProveedorInventarios.cja");
        if (comprobracionExist.exists()){

            //Persistencia - Leer el archivo de datos
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/persistencia/gestionProveedorInventarios.cja"));
                ArrayList<ProductoProveedor> productosGuardar = (ArrayList<ProductoProveedor>) ois.readObject();
                productosproveedores.addAll(productosGuardar);

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Guarda y actualiza los cambios realizados en las tablas permitiendo la persistencia
     * En caso de que sea primera vez que se guarda algún dato, permite la creación del archivo.
     */

/*
    public void persistenciaEscribir(){
        //Persistencia - Guardar los datos en un archivo
        ArrayList<ProductoProveedor> productosGuardar= new ArrayList<>(productosproveedores);
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/persistencia/gestionInventarios.cja"));
            oos.writeObject(productosGuardar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inicia y setea los datos para que sean visibles para el usuario en una tabla en la GUI
     */

/*
    public void iniciarDatosObservables(){
        //Iniciar la visualización de objetos en la tabla
        productosproveedores = FXCollections.observableArrayList(); //Se tiene que crear otro arraylist que va a ser el que se va a visualizar en la vista
        busquedaProductos = FXCollections.observableArrayList();
        this.tblProductosGestionInventarios.setItems(productosproveedores); //Para setear los elementos de nuestro array original al que se muestra en pantalla


        //Mapeo de las columnas de la tabla con los atributos de los objetos persona
        this.colNombreProducto.setCellValueFactory((new PropertyValueFactory<>("nombre")));
        this.colMarcaProducto.setCellValueFactory((new PropertyValueFactory<>("marca")));
        this.colSkuProducto.setCellValueFactory((new PropertyValueFactory<>("sku")));
        this.colCodigoBarrasProducto.setCellValueFactory((new PropertyValueFactory<>("codigoBarras")));
        this.colInventarioProducto.setCellValueFactory((new PropertyValueFactory<>("cantExistencia")));
        this.colPrecioUnitario.setCellValueFactory((new PropertyValueFactory<>("precioUnitario")));
        this.colCategoriasProducto.setCellValueFactory((new PropertyValueFactory<>("categoria")));

    }
}*/
