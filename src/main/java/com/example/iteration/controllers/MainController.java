package com.example.iteration.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class  MainController {

    @FXML
    private TextField accuracy;

    @FXML
    private Button authoButton;

    @FXML
    private Button decideButton;

    @FXML
    private Button helpButton;
    @FXML
    private Button registrButton;

    @FXML
    private TextField inputX1;

    @FXML
    private TextField inputX11;

    @FXML
    private TextField inputX111;

    @FXML
    private TextField inputX2;

    @FXML
    private TextField inputX22;

    @FXML
    private TextField inputX222;

    @FXML
    private TextField inputX3;

    @FXML
    private TextField inputX33;

    @FXML
    private TextField inputX333;

    @FXML
    private TextField result1;

    @FXML
    private TextField result2;

    @FXML
    private TextField result3;

    @FXML
    private Label text;

    public static boolean check = false; // та самая переменная, для проверки авторизации и регистрации

    // в этих переменных в дальнейшем буду храниться ответы
    public static double x01;
    public static double x02;
    public static double x03;




    @FXML
    void initialize() {
        if(!check){ // если пользователь авторизирован, кнопка помощи будет доступна
            helpButton.setDisable(true);
        }

        // слушатели для кнопок
        registrButton.setOnAction(actionEvent -> {
            open("/com/example/iteration/samples/registration.fxml", registrButton);
        });

        authoButton.setOnAction(actionEvent -> {
            open("/com/example/iteration/samples/authorization.fxml", authoButton);
        });

        helpButton.setOnAction(actionEvent -> {
            openModal("/com/example/iteration/samples/help.fxml");
        });

        ArrayList<Float> dataRecording = new ArrayList<Float>(); // массив для хранения проверенных значений

        ArrayList<TextField> textFields = new ArrayList<TextField>(); // массив для хранения изначальных значений

        // массив заполняется
        textFields.add(inputX1);
        textFields.add(inputX11);
        textFields.add(inputX111);
        textFields.add(inputX2);
        textFields.add(inputX22);
        textFields.add(inputX222);
        textFields.add(inputX3);
        textFields.add(inputX33);
        textFields.add(inputX333);

        textFields.add(result1);
        textFields.add(result2);
        textFields.add(result3);
        textFields.add(accuracy);

        decideButton.setOnAction(actionEvent -> { // слушает кнопку решения
            text.setText("");
            boolean bool = false;
            // происходит проверка введенных значений
            for (TextField i: textFields) {
                bool = inputValidation(i); // вызывает метод проверки
                if (bool) {
                    text.setText("Введите правильно.");
                    break;
                } else {
                    dataRecording.add(data_recording(i)); // записывается в новый массив
                }
            }

            if (!bool) {
                // записывает из "правильного" массива в переменные
                double a11 = dataRecording.get(0);
                double a12 = dataRecording.get(1);
                double a13 = dataRecording.get(2);
                double a21 = dataRecording.get(3);
                double a22 = dataRecording.get(4);
                double a23 = dataRecording.get(5);
                double a31 = dataRecording.get(6);
                double a32 = dataRecording.get(7);
                double a33 = dataRecording.get(8);
                double b1 = dataRecording.get(9);
                double b2 = dataRecording.get(10);
                double b3 = dataRecording.get(11);
                float t = dataRecording.get(12);

                x01 = b1 / a11;
                x02 = b2 / a22;
                x03 = b3 / a33;

                System.out.println(x01);
                System.out.println(x02);
                System.out.println(x03);

                double x11;
                double x12;
                double x13;
                double t01 = 1;
                double t02 = 1;
                double t03 = 1;

                for( int i = 0; i < 100; i++) {
                    if (Math.abs(a11) < Math.abs(a12) || Math.abs(a11) < Math.abs(a13)) {
                        double aa11 = a11 + a31 * 2 + a21 * 2;
                        double aa12 = a12 + a32 * 2 + a22 * 2;
                        double aa13 = a13 + a33 * 2 + a23 * 2;
                        double bb1 = b1 + b3 * 2 + b2 * 2;
                        a11 = aa11;
                        a12 = aa12;
                        a13 = aa13;
                        b1 = bb1;

                    } else if (Math.abs(a22) < Math.abs(a21) || Math.abs(a22) < Math.abs(a23)) {
                        double aa21 = a21 - a31 * 2 - a11;
                        double aa22 = a22 - a32 * 2 - a12;
                        double aa23 = a23 - a33 * 2 - a13;
                        double bb2 = b2 - b3 * 2 - b1;
                        a21 = aa21;
                        a22 = aa22;
                        a23 = aa23;
                        b2 = bb2;

                    } else if (Math.abs(a33) < Math.abs(a31) || Math.abs(a33) < Math.abs(a32)) {
                        double aa31 = a31 - a11;
                        double aa32 = a32 - a12;
                        double aa33 = a33 - a13;
                        double bb3 = b3 - b1;
                        a31 = aa31;
                        a32 = aa32;
                        a33 = aa33;
                        b3 = bb3;
                    } else {
                        break;
                    }
                }

                for (int i = 0; i < 100; i++) {
                    if (Math.abs(t01) > t || Math.abs(t02)  > t || Math.abs(t03) > t) {
                        x11 = (b1 - a12 * x02 - a13 * x03) / a11;
                        x12 = (b2 - a21 * x01 - a23 * x03) / a22;
                        x13 = (b3 - a31 * x01 - a32 * x02) / a33;
                        System.out.println("x:" + i + "-aя " + x11);
                        System.out.println(x12);
                        System.out.println(x13);
                        t01 = (x11 - x01);
                        t02 = (x12 - x02);
                        t03 = (x13 - x03);
                        String result1 = String.format("%.8f",t01);
                        String result2 = String.format("%.8f",t02);
                        String result3 = String.format("%.8f",t03);
                        System.out.println("т: " + i + "-aя " + result1);
                        System.out.println(result2);
                        System.out.println(result3);
                        x01 = x11;
                        x02 = x12;
                        x03 = x13;
                    }
                    else {
                        System.out.println(x01 + " " + x02 + " " + x03);
                        break;
                    }
                }

                int lenT = Float.toString(t).split("\\.")[1].length(); // определяет показатель степени
                double scale = Math.pow(10, lenT); // в степень
                x01 = Math.ceil(x01 * scale) / scale;
                x02 = Math.ceil(x02 * scale) / scale;
                x03 = Math.ceil(x03 * scale) / scale;
                open("/com/example/iteration/samples/result.fxml", decideButton); // новое окно

            }


        });







    }
    private void open(String path, Button button) {
        button.getScene().getWindow().hide();
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
        stage.setTitle("Авторизация");
        stage.show();
    } // открывает новое окно, закрывая данное

    private void openModal(String path) {
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
        stage.setTitle("Авторизация");
        stage.initModality(Modality.APPLICATION_MODAL); // нельзя будет двигать родительское окно
        stage.showAndWait();
    } // открывает модальное окно (окно поверх другого)

    private boolean inputValidation(TextField num){ // метод проверки значений
        String text1 = num.getText().trim();
        boolean noNum = false;
        try {
            Float.parseFloat(text1);
        } catch (Exception e) {
            noNum = true;
        }

        if(!text1.matches("-?\\d*.?\\d*") || text1 == "" ) { // регулярное выражение для проверки
            noNum = true;
        }
        return noNum;
    }

    private float data_recording(TextField text){
        String text1 = text.getText();
        float text2 = Float.parseFloat(text1);
        return text2;
    }


}