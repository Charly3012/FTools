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
import modelo.Proveedor;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la pestaña encargada de gestionar los provedores existentes en el sistema,
 * realiza operaciones como agregar nuevos, actualizar o eliminar
 * @author Zared
 * @version 1.0
 */
public class ControladorGestionDeProveedores implements Initializable, Serializable {

    @FXML
    private Button btnAgregarProveedor;

    @FXML
    private Button btnBorrarProveedor;

    @FXML
    private Button btnBuscarProveedor;

    @FXML
    private Button btnEditarProveedor;

    @FXML
    private MenuButton btnMenuProveedores;

    @FXML
    private TableColumn colCorreoProveedor;

    @FXML
    private TableColumn colDireccionProveedor;

    @FXML
    private TableColumn colNombreProveedor;

    @FXML
    private TableColumn colNumeroProveedor;

    @FXML
    private Label labProveedoresMenu;

    @FXML
    private TableView<Proveedor> tlbrBarraProveedor;

    @FXML
    private TextField txtBuscarProveedores;

    private ObservableList<Proveedor> proveedores;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        proveedores = FXCollections.observableArrayList();
        this.tlbrBarraProveedor.setItems(proveedores);

        this.colNombreProveedor.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colDireccionProveedor.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.colCorreoProveedor.setCellValueFactory(new PropertyValueFactory<>("correo"));
        this.colNumeroProveedor.setCellValueFactory(new PropertyValueFactory<>("numero"));

    }
    /**
     * Abre una ventana para agregar un nuevo proveedor.
     * @param event Evento de clic en el botón de agregar nuevo.
     */
    @FXML
    void agregarNuevoP(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaNuevoProveedor.fxml"));

            Parent root = loader.load();

            ControladorNuevoProveedor controlador = loader.getController();
            controlador.initAttributtes(proveedores);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            Proveedor p = controlador.getProveedor();

            if (p != null){
                this.proveedores.add(p);
                this.tlbrBarraProveedor.refresh();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void borrarNuevoP(ActionEvent event) {
        Proveedor seleccionar = this.tlbrBarraProveedor.getSelectionModel().getSelectedItem();

        if (seleccionar == null){
            Alerta alertaDeSeleccion = new Alerta("Error", "Debes seleccionar un proveedor");
            alertaDeSeleccion.mostrarAlertaError();
        }else {
            this.proveedores.remove(seleccionar);
            this.tlbrBarraProveedor.refresh();

            Alerta alertaDeEliminacion = new Alerta("Proveedor eliminado", "El proveedor seleccionado se ha eliminado con exito");
            alertaDeEliminacion.mostrarAlertaInformation();
        }
    }

    @FXML
    void editarNuevoP(ActionEvent event) {
        Proveedor p = this.tlbrBarraProveedor.getSelectionModel().getSelectedItem();

        if (p == null){
            Alerta alertaDeSeleccion = new Alerta("Error", "Debes seleccionar un proveedor");
            alertaDeSeleccion.mostrarAlertaError();
        }else {

            try {
                FXMLLoader vistaEmergente = new FXMLLoader(getClass().getResource("/vista/VistaNuevoProveedor.fxml"));
                Parent root = vistaEmergente.load();

                ControladorNuevoProveedor controlador = vistaEmergente.getController();
                controlador.initAttributtes(proveedores, p);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                Proveedor proveedorEditado = controlador.getProveedor();

                if (proveedorEditado != null){
                    this.tlbrBarraProveedor.refresh();
                }

            } catch (IOException e) {
                Alerta alerta = new Alerta("Error", e.getMessage());
                alerta.mostrarAlertaError();
            }
        }
    }

    /**
     * Cierra la pestaña de la vista y retorna a la pestaña principal
     * El evento de acción generado por el clic.
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
            Stage myStage = (Stage) this.btnAgregarProveedor.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


