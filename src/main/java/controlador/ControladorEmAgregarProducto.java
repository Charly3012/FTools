package controlador;

import java.io.IOException;
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
    public Button btnSalir;

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

    private Producto productoAux;

    private ObservableList<Producto> productos;


    /**
     * Método que se ejecuta al abrir la ventana
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Método que se ejecuta al abrir la ventana
     */
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

    /**
     * Setea los atributos y mapeo
     * @param productos
     */
    public void inicializarAtributos(ObservableList<Producto> productos){
        this.productos = productos;

    }

    /**
     * Setea los campos guardados con anterioridad para el registro seleccioinado
     * @param productos
     * @param p
     */
    public void inicializarAtributos(ObservableList<Producto> productos, Producto p){
        this.productos = productos;
        this.productoAux = p;

        this.txtNombre.setText(p.getNombre());
        this.txtDescripcion.setText(p.getDescripcion());
        this.txtInventario.setText(p.getCantExistencia() + "");
        this.txtSKU.setText(p.getSku());
        this.txtMarca.setText(p.getMarca());
        this.txtCodigoBarras.setText(p.getCodigoBarras() + "");
        this.txtPrecioPublico.setText(p.getPrecioUnitario() + "");

    }

    /**
     * Guarda los datos ingresados en los campos y los regrese a la ventana previa {@link ControladorGestionInventarios} para guardar y mostrar en la tabla en la GUI
     * @param event
     */
    @FXML
    void clickGuardar(ActionEvent event) {

        try {
            String nombre = this.txtNombre.getText().substring(0, 1).toUpperCase() + this.txtNombre.getText().substring(1);
            String marca = this.txtMarca.getText().substring(0, 1).toUpperCase() + this.txtMarca.getText().substring(1);
            String sku = this.txtSKU.getText().toUpperCase();
            int codigoBarras = Integer.parseInt(txtCodigoBarras.getText());
            int cantExistencia = Integer.parseInt(txtInventario.getText());
            double precioUnitario = Double.parseDouble(txtPrecioPublico.getText());
            String categoria = this.cmbCategoria.getTypeSelector();
            String descripcion = this.txtDescripcion.getText();

            Producto productoAuxNuevo = new Producto(nombre, marca, sku, codigoBarras, cantExistencia, precioUnitario, categoria, descripcion);

            //Comprueba que no exista uno igual
            if(!(productos.contains(productoAuxNuevo))){

                //Modificar
                if(this.productoAux != null){

                    this.productoAux.setNombre(nombre);
                    //this.productoAux.setCategoria(categoria);
                    this.productoAux.setCantExistencia(cantExistencia);
                    this.productoAux.setSku(sku);
                    this.productoAux.setDescripcion(descripcion);
                    this.productoAux.setCodigoBarras(codigoBarras);
                    this.productoAux.setPrecioUnitario(precioUnitario);

                    Alerta editadoExitoso = new Alerta("Producto modificado", "El producto se ha modificado con exito");
                    editadoExitoso.mostrarAlertaInformation();
                }
                //Nuevo
                else{
                    this.productoAux = productoAuxNuevo;

                    Alerta agregadoCorrectamente = new Alerta("Producto agregado", "El producto se ha agregado con éxito");
                    agregadoCorrectamente.mostrarAlertaInformation();
                }

                //Cierra la pestaña y termina el guardado
                Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
                stage.close();


            }else{
                Alerta productoExistente = new Alerta("Error", "El producto ya existe");
                productoExistente.mostrarAlertaError();
            }



        }catch (NumberFormatException e){
            Alerta errorGuardar = new Alerta("Error al guardar", "Verifique que todos los datos sean correctos \n Detalles de error: " + e.getMessage());
            errorGuardar.mostrarAlertaError();
        }


    }

    /**
     * Limpia todos los campos
     * @param event
     */
    @FXML
    void clickLimpiar(ActionEvent event) {

        this.txtNombre.setText("");
        this.txtMarca.setText("");
        this.txtSKU.setText("");
        this.txtPrecioPublico.setText("");
        this.txtInventario.setText("");
        this.txtCodigoBarras.setText("");
        this.txtDescripcion.setText("");

    }

    /**
     * Retorna el producto a la pestaña previa {@link ControladorGestionInventarios} para guardarla en la tabla correspondiente
     * @return {@link Producto}
     */
    public Producto getProductoAux() {
        return productoAux;
    }

    /**
     * Cierra la ventana emergente para regresar a la ventana previa {@link ControladorGestionInventarios}
     * @param actionEvent
     */
    @FXML
    public void clickSalir(ActionEvent actionEvent) {
        this.productoAux = null;
        Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
        stage.close();
    }
}
