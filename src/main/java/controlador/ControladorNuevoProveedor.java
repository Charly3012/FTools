package controlador;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Alerta;
import modelo.Proveedor;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase encargada de agregar nuevos proveedores
 * @author Zared
 * @version 1.0
 */
public class ControladorNuevoProveedor implements Initializable {

    /**
     * Botón para aceptar y guardar el proveedor.
     */
    @FXML
    private Button btnNuevoProveedorAceptar;

    /**
     * Botón para cancelar y cerrar la ventana de agregar proveedor.
     */
    @FXML
    private Button btnNuevoProveedorCancelar;

    @FXML
    private Label labNuevoProveedorCel;

    @FXML
    private Label labNuevoProveedorContacto;

    @FXML
    private Label labNuevoProveedorCorreo;

    @FXML
    private Label labNuevoProveedorDireccion;

    @FXML
    private Label labNuevoProveedorNombre;
    /**
     * Campo para ingresar el número de contacto del proveedor.
     */
    @FXML
    private TextField txtNuevoProveedorCel;

    /**
     * Campo para ingresar el correo electronico del proveedor.
     */
    @FXML
    private TextField txtNuevoProveedorCorreo;
    /**
     * Campo para ingresar la dirección  del proveedor.
     */
    @FXML
    private TextField txtNuevoProveedorDireccion;

    /**
     * Campo para ingresar el nombre del proveedor.
     */
    @FXML
    private TextField txtNuevoProveedorNombre;

    private Proveedor proveedor;

    private ObservableList<Proveedor> proveedores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initAttributtes(ObservableList<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

    public void initAttributtes(ObservableList<Proveedor> proveedores, Proveedor p) {
        this.proveedores = proveedores;
        this.proveedor = p;
        this.txtNuevoProveedorNombre.setText(p.getNombre());
        this.txtNuevoProveedorDireccion.setText(p.getDireccion());
        this.txtNuevoProveedorCorreo.setText(p.getCorreo());
        this.txtNuevoProveedorCel.setText(p.getNumero());
    }

    @FXML
    void aceptarGuardar(ActionEvent event) {
        String nombre = this.txtNuevoProveedorNombre.getText();
        String direccion = this.txtNuevoProveedorDireccion.getText();
        String correo = this.txtNuevoProveedorCorreo.getText();
        String numero = this.txtNuevoProveedorCel.getText();

        Proveedor p = new Proveedor(nombre, direccion, correo, numero);


        if ((validarNombre(nombre) && validarDireccion(direccion) && validarCorreo(correo) && validarNumero(numero))) {


            if (!proveedores.contains(p)) {

                if (this.proveedor != null){
                    this.proveedor.setNombre(nombre);
                    this.proveedor.setDireccion(direccion);
                    this.proveedor.setNumero(numero);
                    this.proveedor.setCorreo(correo);

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("El proveedor se ha modificado correctamente");
                    alert.showAndWait();

                }else {
                    this.proveedor = p;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Info");
                    alert.setContentText("El proveedor se ha añadido correctamente");
                    alert.showAndWait();
                }

                Stage stage = (Stage) this.btnNuevoProveedorAceptar.getScene().getWindow();
                stage.close();
            } else {
                mostrarError("El proveedor ya existe");
            }


        } else {
            String mensajeError = "Error al agregar un nuevo proveedor:\n";
            if (!validarNombre(nombre)) {
                mensajeError += "- Error en el campo nombre\n";
            }
            if (!validarDireccion(direccion)) {
                mensajeError += "- Error en el campo dirección\n";
            }
            if (!validarCorreo(correo)) {
                mensajeError += "- Error en el campo correo\n";
            }
            if (!validarNumero(numero)) {
                mensajeError += "- Error en el campo número\n";
            }

            mostrarError(mensajeError);
        }

    }

    private boolean validarNombre(String nombre) {
        return !nombre.isEmpty() && !nombre.matches(".*\\d.*");
    }

    private boolean validarDireccion(String direccion) {
        return !direccion.isEmpty();
    }

    private boolean validarCorreo(String correo) {
        return correo.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
    }

    private boolean validarNumero(String numero) {
        return numero.matches("[0-9 ]{10}");
    }

    @FXML
    void cancelarSalir(ActionEvent event) {
        this.proveedor = null;
        Stage stage = (Stage) this.btnNuevoProveedorCancelar.getScene().getWindow();
        stage.close();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }



    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}