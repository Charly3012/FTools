package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modelo.Alerta;
import modelo.Categoria;
import modelo.ProductoProveedor;
import modelo.Proveedor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControladorProveedorEmAgregarProducto implements Initializable{

    @FXML
    private Button btnGuardar;


    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSalir;

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
    private TextField txtNombreProveedor;

    @FXML
    private TextField txtPrecioPublico;

    @FXML
    private TextField txtSKU;

    private ProductoProveedor productoProveedor;

    private ObservableList<ProductoProveedor> proveedoresProductos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initAttributtes(ObservableList<ProductoProveedor> proveedoresProductos) {
        this.proveedoresProductos = proveedoresProductos;
    }

    public void initAttributtes(ObservableList<ProductoProveedor> proveedoresProductos, ProductoProveedor p) {
        this.proveedoresProductos = proveedoresProductos;
        this.productoProveedor = p;

        this.txtNombre.setText(p.getNombre());
        this.txtDescripcion.setText(p.getDescripcion());
        this.txtInventario.setText(String.valueOf(p.getCantExistencia()));
        this.txtSKU.setText(p.getSku());
        this.txtMarca.setText(p.getMarca());
        this.txtCodigoBarras.setText(String.valueOf(p.getCodigoBarras()));
        this.txtPrecioPublico.setText(String.valueOf(p.getPrecioUnitario()));
    }


    @FXML
    void clickGuardar(ActionEvent event) {
        String nombre = this.txtNombre.getText();
        String marca = this.txtMarca.getText();
        String sku = this.txtSKU.getText().toUpperCase();
        String codigoBarras = txtCodigoBarras.getText();
        String cantExistencia = txtInventario.getText();
        String precioUnitario = txtPrecioPublico.getText();
        String descripcion = this.txtDescripcion.getText();
        String nombreProveedor = this.txtNombreProveedor.getText();

        ProductoProveedor p = new ProductoProveedor(nombre, marca, sku, codigoBarras, cantExistencia, precioUnitario, descripcion, nombreProveedor);

        if ((validarNombre(nombre) && validarMarca(marca) && validarSku(sku) && validarCodigoBarras(codigoBarras) && validarCantExistencia(cantExistencia) && validarPrecioUnitario(precioUnitario) && validarDescripcion(descripcion) && validarNombreProveedor(nombreProveedor))) {


            if (!proveedoresProductos.contains(p)) {

                if (this.productoProveedor != null){
                    this.productoProveedor.setNombre(nombre);
                    this.productoProveedor.setMarca(marca);
                    this.productoProveedor.setSku(sku);
                    this.productoProveedor.setCodigoBarras(codigoBarras);
                    this.productoProveedor.setCantExistencia(cantExistencia);
                    this.productoProveedor.setPrecioUnitario(precioUnitario);
                    this.productoProveedor.setNombreProveedor(nombreProveedor);


                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("El proveedor se ha modificado correctamente");
                    alert.showAndWait();

                }else{
                    this.productoProveedor = p;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("El producto se ha añadido correctamente");
                    alert.showAndWait();
                }

                Stage stage = (Stage) this.btnGuardar.getScene().getWindow();
                stage.close();
            } else {
                mostrarError("El producto ya existe");
            }


        } else {

            String mensajeError = "Error al agregar un nuevo proveedor:\n";
            if (!validarNombre(nombre)) {
                mensajeError += "- Error en el campo nombre\n";
            }
            if (!validarMarca(marca)) {
                mensajeError += "- Error en el campo marca\n";
            }
            if (!validarSku(sku)) {
                mensajeError += "- Error en el campo sku\n";
            }
            if (!validarCodigoBarras(codigoBarras)) {
                mensajeError += "- Error en el campo codigo de barras\n";
            }
            if (!validarCantExistencia(cantExistencia)) {
                mensajeError += "- Error en el campo Existencia\n";
            }
            if (!validarPrecioUnitario(precioUnitario)) {
                mensajeError += "- Error en el campo precioUnitario\n";
            }
            if (!validarDescripcion(descripcion)) {
                mensajeError += "- Error en el campo descripcion\n";
            }
            if (!validarNombreProveedor(nombreProveedor)) {
                mensajeError += "- Error en el campo: nombre del proveedor\n";
            }

            mostrarError(mensajeError);
        }
    }

    private boolean validarNombre(String nombre) {
        return !nombre.isEmpty() && !nombre.matches(".*\\d.*");
    }

    private boolean validarMarca(String marca) {
        return !marca.isEmpty();
    }

    private boolean validarSku(String sku) {
        return sku.matches("^[0-9]{0,15}$");
    }

    private boolean validarCodigoBarras(String codigoBarras) {
        return codigoBarras.matches("^[0-9]{0,15}$");
    }

    private boolean validarCantExistencia(String cantExistencia) {
        return cantExistencia.matches("^[0-9]{0,500}$");
    }

    private boolean validarPrecioUnitario(String precioUnitario) {
        return precioUnitario.matches("^[0-9]{0,15}$");
    }

    private boolean validarDescripcion(String descripcion) {
        return !descripcion.isEmpty();
    }

    private boolean validarNombreProveedor(String nombreProveedor) {
        return !nombreProveedor.isEmpty();
    }



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

    @FXML
    void clickSalir(ActionEvent event) {
        this.productoProveedor = null;
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }


    public ProductoProveedor getProductoProveedor() {
        return productoProveedor;
    }
}


