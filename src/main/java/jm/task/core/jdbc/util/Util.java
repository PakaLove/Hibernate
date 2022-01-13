package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private  Connection connection;

    private final String URL = "jdbc:mysql://localhost:3306/Connection1?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "Paka1337";
    private final static String DRIVER = "com.mysql.jdbc.Driver";


    public Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Не удалось подключиться к базе");
            }
        }
        return connection;
    }
}

