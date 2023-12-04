module com.example.ftoolss {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;

    opens controlador to javafx.fxml;
    exports controlador;
    exports modelo;
}
