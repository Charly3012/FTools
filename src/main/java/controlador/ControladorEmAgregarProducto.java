package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Alerta;
import modelo.Producto;

public class ControladorEmAgregarProducto implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private ComboBox<?> cmbCategoria;

    @FXML
    private TextField txtCodigoBarras;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtInventario;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecioPublico;

    @FXML
    private TextField txtSKU;

    private Producto nuevoProducto;

    private ObservableList<Producto> productos;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void initialize() {
        assert btnGuardar != null : "fx:id=\"btnGuardar\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";
        assert btnLimpiar != null : "fx:id=\"btnLimpiar\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";
        assert cmbCategoria != null : "fx:id=\"cmbCategoria\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";
        assert txtCodigoBarras != null : "fx:id=\"txtCodigoBarras\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";
        assert txtDescripcion != null : "fx:id=\"txtDescripcion\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";
        assert txtInventario != null : "fx:id=\"txtInventario\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";
        assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";
        assert txtPrecioPublico != null : "fx:id=\"txtPrecioPublico\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";
        assert txtSKU != null : "fx:id=\"txtSKU\" was not injected: check your FXML file 'VistaEmAgregarProducto.fxml'.";

    }

    public void inicializarAtributos(ObservableList<Producto> productos){
        this.productos = productos;

    }

    @FXML
    void clickGuardar(ActionEvent event) {

        String nombre = this.txtNombre.getText();
        String marca = this.txtNombre.getText();
        String sku = this.txtSKU.getText();
        int codigoBarras = Integer.parseInt(txtCodigoBarras.getText());
        int cantExistencia = Integer.parseInt(txtInventario.getText());
        double precioUnitario = Double.parseDouble(txtPrecioPublico.getText());
        String categoria = this.cmbCategoria.getTypeSelector();
        String descripcion = this.txtDescripcion.getText();

        Producto nuevoProductoTemp = new Producto(nombre, marca, sku, codigoBarras, cantExistencia, precioUnitario, categoria, descripcion);

        //Comprueba que no exista uno igual
        if(!(productos.contains(nuevoProductoTemp))){
            this.nuevoProducto = nuevoProductoTemp;

            Alerta agregadoCorrectamente = new Alerta("Producto agregado", "El producto se ha agregado con éxito");
            agregadoCorrectamente.mostrarAlertaInformation();

            //Cierra la pestaña y termina el guardado
            Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
            stage.close();
        }else{
            Alerta productoExistente = new Alerta("Error", "El producto ya existe");
            productoExistente.mostrarAlertaError();
        }




    }

    @FXML
    void clickLimpiar(ActionEvent event) {

    }

    public Producto getNuevoProducto() {
        return nuevoProducto;
    }
}
