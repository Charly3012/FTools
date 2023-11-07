package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorGestionDeProveedores {

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
    private TableColumn<?, ?> colCorreoProveedor;

    @FXML
    private TableColumn<?, ?> colDireccionProveedor;

    @FXML
    private TableColumn<?, ?> colNombreProveedor;

    @FXML
    private TableColumn<?, ?> colNumeroProveedor;

    @FXML
    private TableColumn<?, ?> colProductosProveedor;

    @FXML
    private Label labProveedoresMenu;

    @FXML
    private TableView<?> tlbrBarraProveedor;

    @FXML
    private TextField txtBuscarProveedores;

    @FXML
    void agregarNuevoP(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaNuevoProveedor.fxml"));

        try {
            Parent root = loader.load();

            ControladorNuevoProveedor controlador = loader .getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
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


