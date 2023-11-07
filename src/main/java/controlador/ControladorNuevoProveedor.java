package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Clase encargada de agregar nuevos proveedores
 * @author Zared
 * @version 1.0
 */
public class ControladorNuevoProveedor {

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

}

