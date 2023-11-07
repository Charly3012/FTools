package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * clase encargada de ser el controlador del Registro de ventas
 * @author Jhonatan Solis
 * @version 1.0
 */
public class ControladorRegistroVentas {

    @FXML
    public TextField txtbusqueda;
    @FXML
    public TableView tblvista;
    @FXML
    public TableView tblVenta;

    @FXML
    private Button btnBusca;


    @FXML
    private MenuButton btnMenu;

    @FXML
    private Button btnpagar;

    @FXML
    private TableColumn<?, ?> tbltablaregistroventas;

    /**
     * Maneja el evento de clic en el botón de búsqueda.
     *
     * @param event El evento de acción generado por el clic.
     */

    @FXML
    void clickBuscar(ActionEvent event) {

    }
    /**
     * Maneja el evento de clic en el menú desplegable.
     *
     * @param event El evento de acción generado por el clic.
     */

    @FXML
    void clickMenu(ActionEvent event) {

    }
    /**
     * Maneja el evento de clic en el botón de pagar.
     *
     * @param event El evento de acción generado por el clic.
     */

    @FXML
    void clickpagar(ActionEvent event) {

    }
    /**
     * Maneja el evento de clic en el textfiel .
     * <p>
     *  El evento de acción generado por el clic.
     */

    @FXML
    public void txtfield(ActionEvent actionEvent) {
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
            Stage myStage = (Stage) this.btnMenu.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
