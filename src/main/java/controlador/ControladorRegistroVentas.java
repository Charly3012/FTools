package controlador;

import com.fasterxml.jackson.databind.type.ArrayType;
import javafx.application.Platform;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * clase encargada de ser el controlador del Registro de ventas
 * @author Jhonatan Solis
 * @version 1.0
 */

//ventas
public class ControladorRegistroVentas implements Initializable {
    @FXML
    public Button btnAñadir;

    @FXML
    public Label labTotalMostrar;

    @FXML
    public TextField txtCliente;

    @FXML
    public Button btnSeleccionarCliente;

    @FXML
    public Label labClienteSeleccionado;

    @FXML
    public Label labHoraMostrar;

    @FXML
    private AnchorPane anchorDetalleVenta;

    @FXML
    private Button btnBuscar;

    @FXML
    private MenuButton btnMenu;

    @FXML
    private Button btnPagar;

    @FXML
    private TableColumn<DatosVenta, String> colCantidadDv;

    @FXML
    private TableColumn<?, ?> colCategoriaDisp;

    @FXML
    private TableColumn<?, ?> colInventarioDisp;

    @FXML
    private TableColumn<?, ?> colPrecioUnitarioDisp;

    @FXML
    private TableColumn<DatosVenta, Integer> colProductoDv;

    @FXML
    private TableColumn<?, ?> colProuctoDisp;

    @FXML
    private TableColumn<DatosVenta, Integer> colTotalDv;

    @FXML
    private TableView<DatosVenta> tblDetalleVenta;

    @FXML
    private TableView<Producto> tblProductosDisponibles;

    @FXML
    private TextField txtBuscarProducto;

    @FXML
    private TextField txtTotal;
    @FXML
    protected ObservableList<DatosVenta> produc;

    @FXML //Lista que se muestra de productos
    private ObservableList<Producto> productosVista;

    @FXML //Lista que se muestra cuando se esta buscando entre los productos
    private ObservableList<Producto> buscarProductos;

    private Cliente clienteCompra;

    private String fecha;

    private ArrayList<Venta> ventas = new ArrayList<>();

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
     * @param rb El evento de acción generado por el clic.
     */

    public void initialize(URL url, ResourceBundle rb) {

        assert colPrecioUnitarioDisp != null : "fx:id=\"colPrecioUnitarioDisp \" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert colInventarioDisp != null : "fx:id=\"colInventarioDisp\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert colCategoriaDisp != null : "fx:id=\"colCategoriaDisp\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert colProuctoDisp!= null : "fx:id=\"colProuctoDisp\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert tblProductosDisponibles != null : "fx:id=\"tblProductosDisponibles\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert txtBuscarProducto != null : "fx:id=\"tblProductosDisponibles\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert btnBuscar != null : "fx:id=\"tblProductosDisponibles\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        assert tblDetalleVenta != null : "fx:id=\"tblDetalleVenta\" was not injected: check your FXML file 'VistaRegistroComprasVentas.fxml'.";
        iniciarDatosObservables();
        persistenciaLeer();
        mostrarHora();
    }

    public void mostrarHora(){
        ControladorReloj controladorReloj = new ControladorReloj();

        controladorReloj.setOnUpdateListener(new ControladorReloj.OnUpdateListener() {
            @Override
            public void onUpdate(String fechaMostrada) {
                Platform.runLater(() -> {
                    fecha = fechaMostrada;

                });
            }

            @Override
            public void onUpdate(String horaMostrada, String fechaMostrada) {
                Platform.runLater(() -> {
                    labHoraMostrar.setText(horaMostrada);

                });
            }
        });

        controladorReloj.start();
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

            //Persistencia - Guardar los datos en un archivo
            ArrayList<Producto> productosGuardar= new ArrayList<>(productosVista);
            try{
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/persistencia/gestionInventarios.cja"));
                oos.writeObject(productosGuardar);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void persistenciaLeer(){
        File comprobracionExist = new File("src/main/resources/persistencia/categorias.cja");
        if(comprobracionExist.exists()){
            //Persistencia - Leer el archivo de datos
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/persistencia/gestionInventarios.cja"));
                ArrayList<Producto> productosGuardar = (ArrayList<Producto>) ois.readObject();
                productosVista.addAll(productosGuardar);
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void iniciarDatosObservables(){
        //Iniciar la visualización de objetos en la tabla
        productosVista = FXCollections.observableArrayList();
        buscarProductos = FXCollections.observableArrayList();
        this.tblProductosDisponibles.setItems(productosVista);
        //Para setear los elementos de nuestro array original al que se muestra en pantalla


        //Mapeo de las columnas de la tabla con los atributos de los objetos persona

        this.colProuctoDisp.setCellValueFactory((new PropertyValueFactory<>("nombre")));
        this.colCategoriaDisp.setCellValueFactory((new PropertyValueFactory<>("categoria")));
        this.colInventarioDisp.setCellValueFactory((new PropertyValueFactory<>("cantExistencia")));
        this.colPrecioUnitarioDisp.setCellValueFactory((new PropertyValueFactory<>("precioUnitario")));

        //Iniciar datos de detalle venta
        produc = FXCollections.observableArrayList();


        this.colProductoDv.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colCantidadDv.setCellValueFactory(new PropertyValueFactory<DatosVenta, String>("cantidad"));

        this.colTotalDv.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        //Para poder editar la tabla de detalle venta
        this.tblDetalleVenta.setEditable(true);
        this.colCantidadDv.setCellFactory(TextFieldTableCell.forTableColumn());

        this.tblDetalleVenta.setItems(produc);
    }






    @FXML
    public void escribirBuscar(KeyEvent keyEvent) {

        String busqueda = this.txtBuscarProducto.getText();

        if(busqueda.isEmpty()){
            this.tblProductosDisponibles.setItems(productosVista);
        }
        else{
            this.buscarProductos.clear();
            for (Producto productoVista : this.productosVista){
                if(productoVista.getNombre().toLowerCase().contains(busqueda.toLowerCase())){
                    this.buscarProductos.add(productoVista);
                }
            }
            this.tblProductosDisponibles.setItems(buscarProductos);
        }
    }



    @FXML
    private void seleccionar(MouseEvent event){
        Producto p = this.tblProductosDisponibles.getSelectionModel().getSelectedItem();

        if(p !=null){

        }

    }


    @FXML
    public void carrito(ActionEvent actionEvent) {
        Producto p = this.tblProductosDisponibles.getSelectionModel().getSelectedItem();

        String nombreProducto = p.getNombre();
        int cantidadExistencia = p.getCantExistencia();
        String cantidad = "1";
        double subtotal = p.getPrecioUnitario() * (Integer.parseInt(cantidad));

        DatosVenta pVenta = new DatosVenta(nombreProducto, cantidad, subtotal, p.getPrecioUnitario(), cantidadExistencia);

        if (p != null) {
            // Añadir el producto seleccionado a tblDetalleVenta
            this.tblDetalleVenta.getItems().add(pVenta);
            // Opcional: Limpiar la selección en tblProductosDisponibles
            this.tblProductosDisponibles.getSelectionModel().clearSelection();
        }

        actualizarTotal();
    }

    @FXML
    public void clickEditarCantidad(TableColumn.CellEditEvent<?,?> cellEditEvent) {

        try {
            DatosVenta pVenta = this.tblDetalleVenta.getSelectionModel().getSelectedItem();

            pVenta.setCantidad((String) cellEditEvent.getNewValue());
            int aux = Integer.parseInt(((String) cellEditEvent.getNewValue()));
            pVenta.setSubtotal(pVenta.getPrecioUnitario() * aux);

            tblDetalleVenta.refresh();
        } catch (NumberFormatException e) {
            Alerta alerta = new Alerta("Error", "El valor tiene que ser de tipo entero");
            alerta.mostrarAlertaError();
            DatosVenta pVenta = this.tblDetalleVenta.getSelectionModel().getSelectedItem();

            pVenta.setCantidad("1");
            int aux = Integer.parseInt("1");
            pVenta.setSubtotal(pVenta.getPrecioUnitario() * aux);
            tblDetalleVenta.refresh();
        }

        actualizarTotal();
    }

    public double suma = 0;

   public double getSuma() {
        return suma;
    }




    public void actualizarTotal(){
        ArrayList<DatosVenta> auxDetalleVenta = new ArrayList<>();
        auxDetalleVenta.addAll(produc);
        if(auxDetalleVenta.isEmpty()){
            this.labTotalMostrar.setText("----");
        }else{

            double suma = 0;
            for (int i = 0; i < produc.size(); i++){
                suma = suma + produc.get(i).getSubtotal();
            }

            this.labTotalMostrar.setText(suma + "");



        }

    }





    @FXML
    public void clickSeleccionarCliente(ActionEvent actionEvent) {


        //Persistencia - Leer el archivo de datos
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/persistencia/gestionClientes.cja"));
            ArrayList<Cliente> clientesGuardar = (ArrayList<Cliente>) ois.readObject();

            for(int j = 0; j < clientesGuardar.size(); j++){
                if (clientesGuardar.get(j).getCelular() == Long.parseLong(this.txtCliente.getText())){
                    clienteCompra = clientesGuardar.get(j);
                    break;
                }
            }
            if(clienteCompra == null){
                Alerta clienteNoExiste = new Alerta("Error", "El cliente no existe");
                clienteNoExiste.mostrarAlertaError();
            }else{
                this.labClienteSeleccionado.setText(clienteCompra.getNombre());
            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }catch (NumberFormatException f){
            Alerta formatoIncorrecto = new Alerta("Error", "Ingrese un número de celular valido");
            formatoIncorrecto.mostrarAlertaError();

        }

        this.txtCliente.setText("");


    }

    @FXML
    void clickPagar(ActionEvent event) throws IOException {

        try{ FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/VistaPago.fxml"));
            if(clienteCompra != null){
                Parent root = loader.load();
                ControladorPagar controlador = loader.getController();

                controlador.setProduc(produc);

                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();

                //Genera el objeto venta
                ArrayList<DatosVenta> aux = new ArrayList<>();
                aux.addAll(produc);
                double precioCompraTotal = Double.parseDouble(labTotalMostrar.getText());
                Venta nuevaVenta = new Venta(clienteCompra.getCelular(), precioCompraTotal, fecha, aux);
                ventas.add(nuevaVenta);

                //Para disminuir el inventario
                ArrayList<Producto> nuevoInventario = new ArrayList<>();
                nuevoInventario.addAll(productosVista);
                for(int i = 0; i < aux.size(); i++ ){
                    for(int j = 0; j < nuevoInventario.size(); j ++){
                        if (nuevoInventario.get(j).getNombre().equals(aux.get(i).getNombre())){
                            int inventario;
                            inventario = nuevoInventario.get(j).getCantExistencia() - (Integer.parseInt(aux.get(i).getCantidad()));
                            nuevoInventario.get(j).setCantExistencia(inventario);
                        }
                    }
                }

                productosVista.removeAll(productosVista);
                productosVista.addAll(nuevoInventario);

                tblDetalleVenta.refresh();

                produc.removeAll(produc);
                tblDetalleVenta.refresh();

            }

            else{
                Alerta alerta = new Alerta("Error", "Debe ingresar un cliente valido");
                alerta.mostrarAlertaError();
            }


        }catch (IOException e) {
            Alerta alerta = new Alerta("Error", e.getMessage());
            alerta.mostrarAlertaError();
        }

    }

}
