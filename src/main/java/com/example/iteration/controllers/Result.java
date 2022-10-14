package com.example.iteration.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Result {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label result1;

    @FXML
    private Label result2;

    @FXML
    private Label result3;
    @FXML
    void initialize() {
        result1.setText(String.valueOf(MainController.x01)); // Икс 1
        result2.setText(String.valueOf(MainController.x02)); // Икс 2
        result3.setText(String.valueOf(MainController.x03)); // Икс 3
    }

}
