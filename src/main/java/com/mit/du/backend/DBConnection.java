package main.java.com.mit.du.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

//    public static final String DB_NAME = "hotelmanagementsystem?autoReconnect=true&verifyServerCertificate=false&useSSL=false";
    public static final String DB_NAME = "hotelmanagementsystem";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "bjit1234";

    public static Connection connection = null;

    public static Connection getConnections() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("DB_Name: "+DB_NAME);
            System.out.println("Username: "+USERNAME);
            System.out.println("Password: "+PASSWORD);
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + DB_NAME, USERNAME, PASSWORD
            );
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean checkConnections() {
        return connection != null;
    }

    public static void closeConnections() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
