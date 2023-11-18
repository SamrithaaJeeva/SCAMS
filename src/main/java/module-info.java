module com.example.scams {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;


    opens com.example.scams to javafx.fxml;
    exports com.example.scams;
}