package com.example.iteration.server;

import java.sql.*;


public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC"; // поддключение

        Class.forName("com.mysql.cj.jdbc.Driver"); // ссылка на коннектор
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void registration(User user) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO users (firstname,lastname,login,password)VALUES(?,?,?,?)"; // Запрос  на ввод данных в БД
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstname());
            prSt.setString(2, user.getLastname());
            prSt.setString(3, user.getLogin());
            prSt.setString(4, user.getPassword());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ResultSet  login(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM users WHERE login =? AND password =?"; // Запрос  на получение  данных из БД

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

    public ResultSet  getLogin(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM users WHERE login =?"; // Запрос  на получение  данных из БД

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;

    }

}
