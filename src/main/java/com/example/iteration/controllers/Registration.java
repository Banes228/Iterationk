package com.example.iteration.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import com.example.iteration.server.DatabaseHandler;

public class Registration {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

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
            String firstname = this.firstname.getText().trim(); // Из формы ввода добавляет в перменную. Trim убирает пробел
            String lastname = this.lastname.getText().trim(); // Из формы ввода добавляет в перменную. Trim убирает пробел
            if (!login.equals("") && !password.equals("") && !firstname.equals("") && !lastname.equals(""))
                loginUser(login, password, firstname, lastname); //Проверяет на пустоту

            else {
                text.setText("Вы что-то не ввели");
            }
        });
    }

    private void loginUser(String login, String password, String firstname, String lastname) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User(); // объект юзера создается
        System.out.println(login);
        user.setLogin(login);  // передает значения в объект
        ResultSet result = dbHandler.getLogin(user); // запрос в бд
        /*
        Если логин находится уже в БД, получается, пользователь уже есть в бд и сработает 75 строка, иначе сработает
        регистрация, в БД запишутся данные и отткроется главное окно.
         */
        try {
            if (result.next()) {
                text.setText("Пользователь уже существует");
            } else {
                /*
                Здесь как и в авторизации
                 */
                User userLogin = new User();
                userLogin.setFirstname(firstname);
                userLogin.setLastname(lastname);
                userLogin.setLogin(login);
                userLogin.setPassword(password);
                try {
                    dbHandler.registration(userLogin); // здесь вот запрос в БД насчет регистрации
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                MainController.check = true;
                open("/com/example/iteration/samples/main.fxml"); // в скобках ссылка на новое окно
            }
        } catch (SQLException e) { // Это исключения на случай непредвиденных ситуаций
            e.printStackTrace();
        }
    }

    private void open(String window) { // Также открывает окно
        open.getScene().getWindow().hide(); // это строчка закрывает данное окно
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
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
