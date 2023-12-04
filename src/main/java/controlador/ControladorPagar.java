package controlador;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import controlador.ControladorRegistroVentas;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.DatosVenta;

import java.util.ArrayList;

public class ControladorPagar {



    public void setProduc(ObservableList<DatosVenta> produc) {
        this.producEnPagar = produc;
        actualizarTotal();
    }

    @FXML
    public Label labCambio;

    @FXML
    protected ObservableList<DatosVenta> producEnPagar;

    @FXML
    public Button btnCobrar;

    @FXML
    public TextField txtPago;
    @FXML
    public TextField txtCambio;
    @FXML
    public Label labTotal;


    @FXML
    public void CobraryGenerar(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCobrar.getScene().getWindow();
        stage.close();

    }
    public void actualizarTotal() {
        ArrayList<DatosVenta> auxDetalleVenta = new ArrayList<>();
        auxDetalleVenta.addAll(producEnPagar);
        if (auxDetalleVenta.isEmpty()) {
            this.labTotal.setText("-----");
        } else {
            double suma = 0;
            for (int i = 0; i < producEnPagar.size(); i++) {
                suma = suma + producEnPagar.get(i).getSubtotal();
            }
            this.labTotal.setText(suma + "");
        }
    }

    public void escribirPago(KeyEvent keyEvent) {
        try {
            double op1 = Double.parseDouble(this.txtPago.getText());
            double op2 = Double.parseDouble(this.labTotal.getText());

            double result = op1-op2;
            this.labCambio.setText(result + "");

        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Errror");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();

        }

    }
}





