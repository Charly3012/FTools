package modelo;

import javafx.scene.control.Alert;

public class Alerta {
    private String titulo;
    private String contenido;


    public Alerta(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public Alerta() {
    }

    public void mostrarAlertaError(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle(this.titulo);
        alerta.setContentText(this.contenido);
        alerta.showAndWait();
    }

    public void mostrarAlertaInformation(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle(this.titulo);
        alerta.setContentText(this.contenido);
        alerta.showAndWait();
    }

    public void mostrarAlertaConfirmation(){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText(null);
        alerta.setTitle(this.titulo);
        alerta.setContentText(this.contenido);
        alerta.showAndWait();
    }

    public void mostrarAlertaWarning(){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);
        alerta.setTitle(this.titulo);
        alerta.setContentText(this.contenido);
        alerta.showAndWait();
    }
}
