package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Producto;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * clase encargada de ser el controlador del Registro de ventas
 * @author Jhonatan Solis
 * @version 1.0
 */
public class ControladorRegistroVentas implements Initializable {

    @FXML
    private AnchorPane anchorDetalleVenta;

    @FXML
    private Button btnBuscar;

    @FXML
    private MenuButton btnMenu;

    @FXML
    private Button btnPagar;

    @FXML
    private TableColumn<?, ?> colCantidadDv;

    @FXML
    private TableColumn<?, ?> colCategoriaDisp;

    @FXML
    private TableColumn<?, ?> colInventarioDisp;

    @FXML
    private TableColumn<?, ?> colPrecioUnitarioDisp;

    @FXML
    private TableColumn<?, ?> colProductoDv;

    @FXML
    private TableColumn<?, ?> colProuctoDisp;

    @FXML
    private TableColumn<?, ?> colTotalDv;

    @FXML
    private TableView<?> tblDetalleVenta;

    @FXML
    private TableView<Producto> tblProductosDisponibles;

    @FXML
    private TextField txtBuscarProducto;

    @FXML
    private TextField txtTotal;

    @FXML //Lista que se muestra de productos
    private ObservableList<Producto> productosVista;

    /**
     * Maneja el evento de clic en el botón de búsqueda.
     *
     * @param event El evento de acción generado por el clic.
     */
    @FXML
    void clickBuscar(ActionEvent event) {

    }

    /**
     * Maneja el evento de clic en el botón de pagar.
     *
     * @param event El evento de acción generado por el clic.
     */
    @FXML
    void clickPagar(ActionEvent event) {

    }
    public void initialize(URL url, ResourceBundle rb) {

        assert colPrecioUnitarioDisp != null : "fx:id=\"colPrecioUnitarioDisp \" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert colInventarioDisp != null : "fx:id=\"colInventarioDisp\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert colCategoriaDisp != null : "fx:id=\"colCategoriaDisp\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert colProuctoDisp!= null : "fx:id=\"colProuctoDisp\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert tblProductosDisponibles != null : "fx:id=\"tblProductosDisponibles\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert txtBuscarProducto != null : "fx:id=\"txtBuscarProducto\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";

        iniciarDatosObservables();
        persistenciaLeer();






    }


    /**
     * Cierra la pestaña de la vista y retorna a la pestaña principal
     * El evento de acción generado por el clic.
     */
    public void cerrarVentana() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaMenuPrincipal.fxml"));
            Parent root = loader.load();
            ControladorMenuPrincipal controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("FTools");
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnPagar.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void persistenciaLeer(){
        //Persistencia - Leer el archivo de datos
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/persistencia/gestionInventarios.cja"));
            ArrayList<Producto> productosGuardar = (ArrayList<Producto>) ois.readObject();
            if (productosVista == null) {
                productosVista = FXCollections.observableArrayList();
            }
            productosVista.addAll(productosGuardar);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

    public void iniciarDatosObservables(){
        //Iniciar la visualización de objetos en la tabla
        productosVista = FXCollections.observableArrayList(); //Se tiene que crear otro arraylist que va a ser el que se va a visualizar en la vista

        this.tblProductosDisponibles.setItems(productosVista); //Para setear los elementos de nuestro array original al que se muestra en pantalla


        //Mapeo de las columnas de la tabla con los atributos de los objetos persona
        this.colProuctoDisp.setCellValueFactory((new PropertyValueFactory<>("Producto")));
        this.colCategoriaDisp.setCellValueFactory((new PropertyValueFactory<>("Categoria")));
        this.colInventarioDisp.setCellValueFactory((new PropertyValueFactory<>("Inventario")));
        this.colPrecioUnitarioDisp.setCellValueFactory((new PropertyValueFactory<>("Precio unitario")));



    }






}
