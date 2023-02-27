package com.eshop.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static Connection connection = null;
    private DbUtil() {
    }
    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("database url", "name", "passward");
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
            return connection;
        }
    }
    public static void closeConnection() {
        try {
            connection.close();
            connection = null;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
