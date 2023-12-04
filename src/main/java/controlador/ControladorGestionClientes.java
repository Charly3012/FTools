package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Alerta;
import modelo.Categoria;
import modelo.Cliente;

public class ControladorGestionClientes implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private ComboBox<?> cmbGeneroCliente;

    @FXML
    private TableColumn<?, ?> colCelularCliente;

    @FXML
    private TableColumn<?, ?> colGeneroCliente;

    @FXML
    private TableColumn<?, ?> colNombreCliente;

    @FXML
    private MenuButton comMenuDesplegable;

    @FXML
    private Label labNombrePestana;

    @FXML
    private TableView<Cliente> tblClientes;

    @FXML
    private TextField txtBuscarCliente;

    @FXML
    private TextField txtCelularCliente;

    @FXML
    private TextField txtNombreCliente;

    @FXML
    private ObservableList<Cliente> clientes;

    @FXML
    private ObservableList<Cliente> busquedaClientes;

    @FXML
    public void clickSeleccionar(MouseEvent mouseEvent) {
        categoriaSeleccionado = this.tblCategoriasVista.getSelectionModel().getSelectedItem();
        if(categoriaSeleccionado != null){
            this.txtIdCategoria.setText(categoriaSeleccionado.getIdCategoria() + "");
            this.txtNombreCategoria.setText(categoriaSeleccionado.getNombreCategoria());
            this.txtDescripcionCategoria.setText(categoriaSeleccionado.getDescripcionCategoria());
        }
    }

    @FXML
    void clickEliminar(ActionEvent event) {

    }

    @FXML
    void clickGuardar(ActionEvent event) {

        try{
            int idCategoria = Integer.parseInt(this.txtIdCategoria.getText());
            String nombre = this.txtNombreCategoria.getText();
            String descripcion = this.txtDescripcionCategoria.getText();

            Categoria categoriaNueva = new Categoria(idCategoria, nombre, descripcion);
            categoriaSeleccionado = this.tblCategoriasVista.getSelectionModel().getSelectedItem();


            if(!(categorias.contains(categoriaNueva))){


                //Editar
                if (categorias.contains(categoriaSeleccionado)){
                    categoriaSeleccionado.setIdCategoria(idCategoria);
                    categoriaSeleccionado.setNombreCategoria(nombre);
                    categoriaSeleccionado.setDescripcionCategoria(descripcion);
                    this.tblCategoriasVista.refresh();

                    Alerta categoriaModificada = new Alerta("Categoría modificada", "La categoría ha sido modificada con éxito");
                    categoriaModificada.mostrarAlertaInformation();

                    this.txtIdCategoria.setText("");
                    this.txtNombreCategoria.setText("");
                    this.txtDescripcionCategoria.setText("");

                }



                //Nuevo
                else{
                    this.categorias.add(categoriaNueva);

                    Alerta agregadoCorrecto = new Alerta("Agregado correctamente", "La categoría " + this.txtNombreCategoria.getText() +" fue agregado correctamente");
                    agregadoCorrecto.mostrarAlertaInformation();

                    this.txtIdCategoria.setText("");
                    this.txtNombreCategoria.setText("");
                    this.txtDescripcionCategoria.setText("");

                    //Para evitar conflictos cuando se está buscando
                    if (categoriaNueva.getNombreCategoria().toLowerCase().contains(this.txtBuscarCategoria.getText().toLowerCase())){
                        this.busquedaCategorias.add(categoriaNueva);

                    }
                    this.tblCategoriasVista.refresh();

                }





            }
            else {
                Alerta categoriaExiste = new Alerta("Producto ya existe", "¡Error! el producto ya existe");
                categoriaExiste.mostrarAlertaInformation();
            }



        } catch (NumberFormatException | ArithmeticException e) {
            Alerta alertacategoria = new Alerta("Error", "Compruebe los datos e intente nuevamente\n" + "Detalles del error: " + e.getMessage());
            alertacategoria.mostrarAlertaError();
        }finally{
            this.tblCategoriasVista.getSelectionModel().clearSelection();
        }



    }

    @FXML
    void clickLimpiar(ActionEvent event) {

    }

    @FXML
    void clickSeleccionar(MouseEvent event) {
        categoriaSeleccionado = this.tblCategoriasVista.getSelectionModel().getSelectedItem();
        if(categoriaSeleccionado != null){
            this.txtIdCategoria.setText(categoriaSeleccionado.getIdCategoria() + "");
            this.txtNombreCategoria.setText(categoriaSeleccionado.getNombreCategoria());
            this.txtDescripcionCategoria.setText(categoriaSeleccionado.getDescripcionCategoria());
        }
    }

    @FXML
    void escribirEnBuscar(KeyEvent event) {

    }


    @FXML
    void initialize() {
        assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert btnGuardar != null : "fx:id=\"btnGuardar\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert btnLimpiar != null : "fx:id=\"btnLimpiar\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert cmbGeneroCliente != null : "fx:id=\"cmbGeneroCliente\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert colCelularCliente != null : "fx:id=\"colCelularCliente\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert colGeneroCliente != null : "fx:id=\"colGeneroCliente\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert colNombreCliente != null : "fx:id=\"colNombreCliente\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert comMenuDesplegable != null : "fx:id=\"comMenuDesplegable\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert labNombrePestana != null : "fx:id=\"labNombrePestana\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert tblClientes != null : "fx:id=\"tblClientes\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert txtBuscarCliente != null : "fx:id=\"txtBuscarCliente\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert txtCelularCliente != null : "fx:id=\"txtCelularCliente\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";
        assert txtNombreCliente != null : "fx:id=\"txtNombreCliente\" was not injected: check your FXML file 'VistaGestionClientes.fxml'.";

    }

    /**
     * Método que se ejecuta al abrir la ventana
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iniciarDatos();
    }

    public void iniciarDatos(){
        clientes = FXCollections.observableArrayList();
        tblClientes.setItems(clientes);

        busquedaClientes = FXCollections.observableArrayList();

        this.colCelularCliente.setCellValueFactory((new PropertyValueFactory<>("celular")));
        this.colNombreCliente.setCellValueFactory((new PropertyValueFactory<>("nombre")));
        this.colGeneroCliente.setCellValueFactory((new PropertyValueFactory<>("genero")));
    }


    public void cerrarVentana() {
        //persistenciaEscribir();

        //Retorna a la ventana anterior cuando se cierra
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaMenuPrincipal.fxml"));
            Parent root = loader.load();
            ControladorMenuPrincipal controlador = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("FTools");
            stage.setScene(scene);
            stage.show();
            Stage myStage = (Stage) this.btnGuardar.getScene().getWindow();
            myStage.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
