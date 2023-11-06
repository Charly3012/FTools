package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
}
