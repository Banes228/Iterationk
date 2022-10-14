package com.example.iteration.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.iteration.server.DatabaseHandler;
import com.example.iteration.server.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Authorization {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private Button open;

    @FXML
    private PasswordField password;

    @FXML
    private Label text;

    @FXML
    void initialize() {
        open.setOnAction(actionEvent -> {
            String login = this.login.getText().trim(); // Из формы ввода добавляет в перменную. Trim убирает пробел
            String password = this.password.getText().trim(); // Из формы ввода добавляет в перменную. Trim убирает пробел
            if (!login.equals("") && !password.equals("")) { // проверка на пустоту
                try {
                    loginUser(login, password); // вызывате метод авторизации
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                text.setText("Пустые данные");
            }
        });

    }

    private void loginUser(String login, String password) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User(); // объект юзера создается
        user.setLogin(login); // передает значения в объект
        user.setPassword(password); // передает значени я в объект
        ResultSet resultAuto = dbHandler.login(user); // запрос в бд
        if(resultAuto.next()){
            MainController.check = true; // если авторизация успешеа
            open("/com/example/iteration/samples/main.fxml"); // в скобках ссылка на новое окно
        }
        else text.setText("Такой пользователь не существует");
    }

    private void open(String path) { // метод для открытия окна
        open.getScene().getWindow().hide(); // это строчка закрывает данное окно
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.getIcons().add(new Image("file:src/main/resources/picture/icon.ico"));
        stage.setTitle("Iteration");
        stage.show();
    }
}

