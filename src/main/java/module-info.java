module com.example.eventsdashboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.eventsdashboard to javafx.fxml;
    exports com.example.eventsdashboard;
}