package controlador;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
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
import modelo.Producto;

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
    private ComboBox<String> cmbGeneroCliente;

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

    /**
     * Tabla que se visualiza en GUI
     */
    @FXML
    private ObservableList<Cliente> clientes;

    /**
     * Tabla que se visualiza en GUI al realizar una búsqueda
     */
    @FXML
    private ObservableList<Cliente> busquedaClientes;

    /**
     * Cliente que se selecciona para diferentes propositos
     */
    @FXML
    private Cliente clienteSeleccionado;

    /**
     * Observable list para visualizar los generos de los clientes
     */
    @FXML
    private ObservableList<String> generos;

    /**
     * Para leeer la selección de un objeto de la tabla
     * @param mouseEvent
     */
    @FXML
    public void clickSeleccionar(MouseEvent mouseEvent) {
        clienteSeleccionado = this.tblClientes.getSelectionModel().getSelectedItem();
        if(clienteSeleccionado != null){
            this.txtNombreCliente.setText(clienteSeleccionado.getNombre());
            this.txtCelularCliente.setText(clienteSeleccionado.getCelular() + "");
            this.cmbGeneroCliente.setValue(clienteSeleccionado.getGenero());
        }
    }


    /**
     * Botón para guardar/editar según sea el caso
     * @param event
     */
    @FXML
    void clickGuardar(ActionEvent event) {

        try{
            long celular = Long.parseLong(this.txtCelularCliente.getText());
            String nombre = this.txtNombreCliente.getText();
            String genero = this.cmbGeneroCliente.getSelectionModel().getSelectedItem() + "";

            Cliente clienteNuevo = new Cliente(nombre, celular, genero);
            clienteSeleccionado = this.tblClientes.getSelectionModel().getSelectedItem();


            if(!(clientes.contains(clienteNuevo))){


                //Editar
                if (clientes.contains(clienteSeleccionado)){
                    clienteSeleccionado.setCelular(celular);
                    clienteSeleccionado.setNombre(nombre);
                    clienteSeleccionado.setGenero(genero);
                    this.tblClientes.refresh();

                    Alerta categoriaModificada = new Alerta("Categoría modificada", "La categoría ha sido modificada con éxito");
                    categoriaModificada.mostrarAlertaInformation();

                    this.txtNombreCliente.setText("");
                    this.txtCelularCliente.setText("");
                    this.cmbGeneroCliente.setValue(null);

                }



                //Nuevo
                else{
                    this.clientes.add(clienteNuevo);

                    Alerta agregadoCorrecto = new Alerta("Agregado correctamente", "El cliente " + this.txtNombreCliente.getText() +" fue agregado correctamente");
                    agregadoCorrecto.mostrarAlertaInformation();

                    this.txtNombreCliente.setText("");
                    this.txtCelularCliente.setText("");
                    this.cmbGeneroCliente.setValue(null);

                    //Para evitar conflictos cuando se está buscando
                    if (clienteNuevo.getNombre().toLowerCase().contains(this.txtBuscarCliente.getText().toLowerCase())){
                        this.busquedaClientes.add(clienteNuevo);

                    }
                    this.tblClientes.refresh();

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
            this.tblClientes.getSelectionModel().clearSelection();
        }



    }

    /**
     * Limpia el formulario
     * @param event
     */
    @FXML
    void clickLimpiar(ActionEvent event) {
        this.txtNombreCliente.setText("");
        this.txtCelularCliente.setText("");
        this.cmbGeneroCliente.setValue(null);
    }

    /**
     * Barra de busqueda para filtrar por nombre
     * @param event
     */
    @FXML
    void escribirEnBuscar(KeyEvent event) {

        String busqueda = this.txtBuscarCliente.getText();

        if(busqueda.isEmpty()){
            this.tblClientes.setItems(clientes);
        }
        else{
            this.busquedaClientes.clear();
            for (Cliente clienteBus : this.clientes){
                if(clienteBus.getNombre().toLowerCase().contains(busqueda.toLowerCase())){
                    this.busquedaClientes.add(clienteBus);
                }
            }
            this.tblClientes.setItems(busquedaClientes);
        }
    }

    /**
     * Elimina el registro seleccionado
     * @param event
     */
    @FXML
    void clickEliminar(ActionEvent event) {
        //Para seleccionar un objeto
        clienteSeleccionado = this.tblClientes.getSelectionModel().getSelectedItem();

        if(clienteSeleccionado == null){
            Alerta alertaNoSeleccionado = new Alerta("Error", "Debes seleccionar un cliente");
            alertaNoSeleccionado.mostrarAlertaError();
        }else{
            this.clientes.remove(clienteSeleccionado);
            this.busquedaClientes.remove(clienteSeleccionado);
            this.tblClientes.refresh();

            Alerta alertaClienteEliminado = new Alerta("Cliente eliminado", "El cliente seleccionado se ha eliminado con éxito");
            alertaClienteEliminado.mostrarAlertaInformation();

            this.tblClientes.getSelectionModel().clearSelection();

        }

        this.txtNombreCliente.setText("");
        this.txtCelularCliente.setText("");
        this.cmbGeneroCliente.setValue(null);


    }


    /**
     * Método que se inicializa cuando se abré la pestaña
     */
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
        persistenciaLeer();
    }

    /**
     * Iniciar datos de tablas visibles
     */
    public void iniciarDatos(){
        generos = FXCollections.observableArrayList("Hombre", "Mujer", "Prefiere no decir");
        cmbGeneroCliente.setItems(generos);

        clientes = FXCollections.observableArrayList();
        tblClientes.setItems(clientes);

        busquedaClientes = FXCollections.observableArrayList();

        this.colCelularCliente.setCellValueFactory((new PropertyValueFactory<>("celular")));
        this.colNombreCliente.setCellValueFactory((new PropertyValueFactory<>("nombre")));
        this.colGeneroCliente.setCellValueFactory((new PropertyValueFactory<>("genero")));
    }

    /**
     * Métodos que se ejectan al cerra la ventana
     */
    public void cerrarVentana() {
        persistenciaEscribir();

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




    /**
     * Lee los registros de sesiones pasadas permitiendo la persistencia de datos
     */
    public void persistenciaLeer(){
        File comprobracionExist = new File("src/main/resources/persistencia/gestionClientes.cja");

        if(comprobracionExist.exists()){
            //Persistencia - Leer el archivo de datos
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/persistencia/gestionClientes.cja"));
                ArrayList<Cliente> clientesGuardar = (ArrayList<Cliente>) ois.readObject();
                clientes.addAll(clientesGuardar);

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Guarda y actualiza los cambios realizados en las tablas permitiendo la persistencia
     * En caso de que sea primera vez que se guarda algún dato, permite la creación del archivo.
     */
    public void persistenciaEscribir(){
        //Persistencia - Guardar los datos en un archivo
        ArrayList<Cliente> clientesGuardar= new ArrayList<Cliente>(clientes);
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/persistencia/gestionClientes.cja"));
            oos.writeObject(clientesGuardar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
