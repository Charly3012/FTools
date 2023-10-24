module com.example.ftoolss {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ftoolss to javafx.fxml;
    exports com.example.ftoolss;
}