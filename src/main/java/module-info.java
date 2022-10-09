module com.example.iteration{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.iteration.controllers to javafx.fxml;
    exports com.example.iteration.controllers;
    exports com.example.iteration;
    opens com.example.iteration to javafx.fxml;

}