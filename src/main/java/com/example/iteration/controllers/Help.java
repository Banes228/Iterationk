package com.example.iteration.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Help {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button okeyButton;

    @FXML
    void initialize() {
        okeyButton.setOnAction(actionEvent -> { // при нажатии кнопки, данное окно закроется
            okeyButton.getScene().getWindow().hide();
        });

    }

}
