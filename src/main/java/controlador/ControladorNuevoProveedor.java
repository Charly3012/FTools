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
public class ControladorNuevoProveedor implements Initializable{

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

    public void initAttributtes(ObservableList<Proveedor> proveedores){
        this.proveedores = proveedores;
    }

    @FXML
    void aceptarGuardar(ActionEvent event) {
        String nombre = this.txtNuevoProveedorNombre.getText();
        String direccion = this.txtNuevoProveedorDireccion.getText();
        String correo = this.txtNuevoProveedorCorreo.getText();
        int numero = Integer.parseInt(this.txtNuevoProveedorCel.getText());


        Proveedor p = new Proveedor(nombre, direccion, correo, numero);


        if (!validarEmail(correo)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El correo no es valido");
            alert.showAndWait();
        }


        if (!proveedores.contains(p)){
            this.proveedor = p;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("El proveedor se ha añadido correctamente");
            alert.showAndWait();

            Stage stage = (Stage) this.btnNuevoProveedorAceptar.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("El proveedor ya existe");
            alert.showAndWait();
        }
    }

    private boolean validarDireccion(String direccion){
        String validDireccion = "^[a-zA-Z0-9#.-]*$";
        return direccion.matches(validDireccion);
    }

    private boolean validarEmail(String correo) {
        String validCorreo = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(validCorreo);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
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
}

