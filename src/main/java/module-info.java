module com.example.eventsdashboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.eventsdashboard to javafx.fxml;
    exports com.example.eventsdashboard;
    exports com.example.eventsdashboard.controllers;
    opens com.example.eventsdashboard.controllers to javafx.fxml;
}