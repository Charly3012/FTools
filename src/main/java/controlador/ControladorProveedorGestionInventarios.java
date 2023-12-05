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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Alerta;
import modelo.ProductoProveedor;
import modelo.Proveedor;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControladorProveedorGestionInventarios implements Initializable{

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnEditarProducto;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private TableColumn<?, ?> colCodigoBarrasProducto;

    @FXML
    private TableColumn<?, ?> colInventarioProducto;

    @FXML
    private TableColumn<?, ?> colMarcaProducto;

    @FXML
    private TableColumn<?, ?> colNombreProducto;

    @FXML
    private TableColumn<?, ?> colPrecioUnitario;

    @FXML
    private TableColumn<?, ?> colProveedor;
    @FXML
    private TableColumn<?, ?> colSkuProducto;



    @FXML
    private Label labNombrePestana;

    private ObservableList<ProductoProveedor> proveedoresProductos;


    @FXML
    private TableView<ProductoProveedor> tblProductosGestionInventarios;

    /**
     * Métodos que se ejecutan al abrir la pestaña
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        proveedoresProductos = FXCollections.observableArrayList();
        this.tblProductosGestionInventarios.setItems(proveedoresProductos);

        this.colNombreProducto.setCellValueFactory((new PropertyValueFactory<>("nombre")));
        this.colMarcaProducto.setCellValueFactory((new PropertyValueFactory<>("marca")));
        this.colSkuProducto.setCellValueFactory((new PropertyValueFactory<>("sku")));
        this.colCodigoBarrasProducto.setCellValueFactory((new PropertyValueFactory<>("codigoBarras")));
        this.colInventarioProducto.setCellValueFactory((new PropertyValueFactory<>("cantExistencia")));
        this.colPrecioUnitario.setCellValueFactory((new PropertyValueFactory<>("precioUnitario")));
        this.colProveedor.setCellValueFactory((new PropertyValueFactory<>("nombreProveedor")));

        /*persistenciaLeer();*/
    }

    /**
     * Agregar un producto
     * @param event
     */
    @FXML
    void ClickAgregarProducto(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaNuevoProveedorEmAgregarProducto.fxml"));

            Parent root = loader.load();

            ControladorProveedorEmAgregarProducto controlador = loader.getController();
            controlador.initAttributtes(proveedoresProductos);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            ProductoProveedor p = controlador.getProductoProveedor();

            if (p != null){
                this.proveedoresProductos.add(p);
                this.tblProductosGestionInventarios.refresh();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Editar producto
     * @param event
     */
    @FXML
    void ClickEditarProducto(ActionEvent event) {
        ProductoProveedor p = this.tblProductosGestionInventarios.getSelectionModel().getSelectedItem();

        if (p == null){
            Alerta alertaDeSeleccion = new Alerta("Error", "Debes seleccionar un proveedor");
            alertaDeSeleccion.mostrarAlertaError();
        }else {

            try {
                FXMLLoader vistaEmergente = new FXMLLoader(getClass().getResource("/vista/VistaNuevoProveedor.fxml"));
                Parent root = vistaEmergente.load();

                ControladorProveedorEmAgregarProducto controlador = vistaEmergente.getController();
                controlador.initAttributtes(proveedoresProductos, p);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                ProductoProveedor proveedorEditado = controlador.getProductoProveedor();

                if (proveedorEditado != null){
                    this.tblProductosGestionInventarios.refresh();
                }

            } catch (IOException e) {
                Alerta alerta = new Alerta("Error", e.getMessage());
                alerta.mostrarAlertaError();
            }
        }
    }

    /**
     * Elimiar producto
     * @param event
     */
    @FXML
    void ClickEliminarProducto(ActionEvent event) {

    }

    /**
     * Cerrar la ventana
     */
    public void cerrarVentana(){

        /*persistenciaEscribir();*/

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaGestionDeProvedores.fxml"));
            Parent root = loader.load();
            ControladorGestionDeProveedores controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("FTools");
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnEditarProducto.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
