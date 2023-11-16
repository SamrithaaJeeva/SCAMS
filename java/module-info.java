module com.example.scams {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scams to javafx.fxml;
    exports com.example.scams;
}