package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * clase encargada de ser el controlador del Registro de ventas
 * @author Jhonatan Solis
 * @version 1.0
 */
public class ControladorRegistroVentas {

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
    private TableView<?> tblProductosDisponibles;

    @FXML
    private TextField txtBuscarProducto;

    @FXML
    private TextField txtTotal;

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
}
