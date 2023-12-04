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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Alerta;
import modelo.Categoria;
import modelo.Producto;

public class ControladorCategorias implements Initializable {

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
    private TableColumn<?, ?> colDescripcionCategoria;

    @FXML
    private TableColumn<?, ?> colIdCategoria;

    @FXML
    private TableColumn<?, ?> colNombreCategoria;

    @FXML
    private MenuButton comMenuDesplegable;

    @FXML
    private Label labNombrePestana;

    @FXML
    private TableView<Categoria> tblCategoriasVista;

    @FXML
    private TextField txtBuscarCategoria;

    @FXML
    private TextArea txtDescripcionCategoria;

    @FXML
    private TextField txtIdCategoria;

    @FXML
    private TextField txtNombreCategoria;

    @FXML
    private ObservableList<Categoria> categorias;

    @FXML
    private ObservableList<Categoria> busquedaCategorias;

    private Categoria categoriaSeleccionado;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        assert btnEliminar != null : "fx:id=\"btnEliminar\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert btnGuardar != null : "fx:id=\"btnGuardar\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert btnLimpiar != null : "fx:id=\"btnLimpiar\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert colDescripcionCategoria != null : "fx:id=\"colDescripcionCategoria\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert colIdCategoria != null : "fx:id=\"colIdCategoria\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert colNombreCategoria != null : "fx:id=\"colNombreCategoria\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert comMenuDesplegable != null : "fx:id=\"comMenuDesplegable\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert labNombrePestana != null : "fx:id=\"labNombrePestana\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert tblCategoriasVista != null : "fx:id=\"tblCategoriasVista\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert txtBuscarCategoria != null : "fx:id=\"txtBuscarCategoria\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert txtDescripcionCategoria != null : "fx:id=\"txtDescripcionCategoria\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert txtIdCategoria != null : "fx:id=\"txtIdCategoria\" was not injected: check your FXML file 'VistaCategorias.fxml'.";
        assert txtNombreCategoria != null : "fx:id=\"txtNombreCategoria\" was not injected: check your FXML file 'VistaCategorias.fxml'.";

        iniciarDatos(); //Mapeo de la tabla
        persistenciaLeer(); //Leer registros anteriores en caso de existir

    }

    public void iniciarDatos(){
        categorias = FXCollections.observableArrayList();
        tblCategoriasVista.setItems(categorias);

        busquedaCategorias = FXCollections.observableArrayList();

        this.colIdCategoria.setCellValueFactory((new PropertyValueFactory<>("idCategoria")));
        this.colDescripcionCategoria.setCellValueFactory((new PropertyValueFactory<>("descripcionCategoria")));
        this.colNombreCategoria.setCellValueFactory((new PropertyValueFactory<>("nombreCategoria")));
    }

    @FXML
    void clickEliminar(ActionEvent event) {
        //Para seleccionar un objeto
        categoriaSeleccionado = this.tblCategoriasVista.getSelectionModel().getSelectedItem();

        if(categoriaSeleccionado == null){
            Alerta alertaNoSeleccionado = new Alerta("Error", "Debes seleccionar una categoria");
            alertaNoSeleccionado.mostrarAlertaError();
        }else{
            if(comprobacionEliminar()){
                this.categorias.remove(categoriaSeleccionado);
                this.busquedaCategorias.remove(categoriaSeleccionado);
                this.tblCategoriasVista.refresh();

                Alerta alertaCategoriaEliminado = new Alerta("Categoria eliminado", "La categoria seleccionada se ha eliminado con exito");
                alertaCategoriaEliminado.mostrarAlertaInformation();

                this.tblCategoriasVista.getSelectionModel().clearSelection();
            }
            else {
                Alerta categoriaEnUso = new Alerta("Error al eliminar", "No es posible eliminar la categoria ya que existen productos en esta categoría");
                categoriaEnUso.mostrarAlertaError();
            }

        }

        this.txtIdCategoria.setText("");
        this.txtNombreCategoria.setText("");
        this.txtDescripcionCategoria.setText("");


    }

    public boolean comprobacionEliminar(){

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/persistencia/gestionInventarios.cja"));
            ArrayList<Producto> productosComprobacion = (ArrayList<Producto>) ois.readObject();

            for (int i = 0; i < productosComprobacion.size(); i++) {
                if(productosComprobacion.get(i).getNombre().equals(categoriaSeleccionado.getNombreCategoria())){
                    return false;
                }

            }
            return true;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

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
        this.txtIdCategoria.setText("");
        this.txtNombreCategoria.setText("");
        this.txtDescripcionCategoria.setText("");


    }

    @FXML
    public void escribirEnBuscar(KeyEvent keyEvent) {

        String busqueda = this.txtBuscarCategoria.getText();

        if(busqueda.isEmpty()){
            this.tblCategoriasVista.setItems(categorias);
        }
        else{
            this.busquedaCategorias.clear();
            for (Categoria categoria : this.categorias){
                if(categoria.getNombreCategoria().toLowerCase().contains(busqueda.toLowerCase())){
                    this.busquedaCategorias.add(categoria);
                }
            }
            this.tblCategoriasVista.setItems(busquedaCategorias);
        }
    }


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
        File comprobracionExist = new File("src/main/resources/persistencia/categorias.cja");

        if(comprobracionExist.exists()){
            //Persistencia - Leer el archivo de datos
            try{
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/main/resources/persistencia/categorias.cja"));
                ArrayList<Categoria> categoriasGuardar = (ArrayList<Categoria>) ois.readObject();
                categorias.addAll(categoriasGuardar);

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            System.out.println(" ");
        }

    }

    /**
     * Guarda y actualiza los cambios realizados en las tablas permitiendo la persistencia
     * En caso de que sea primera vez que se guarda algún dato, permite la creación del archivo.
     */
    public void persistenciaEscribir(){
        //Persistencia - Guardar los datos en un archivo
        ArrayList<Categoria> categoriasGuardar= new ArrayList<>(categorias);
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/persistencia/categorias.cja"));
            oos.writeObject(categoriasGuardar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

