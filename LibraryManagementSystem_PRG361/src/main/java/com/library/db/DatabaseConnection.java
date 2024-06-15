package com.library.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Explicitly load the Derby driver
                Class.forName("org.apache.derby.jdbc.ClientDriver");

                // Provide the correct URL with user and password
                String url = "jdbc:derby://localhost:1527/LibraryDB;create=true";
                String user = "root"; // Replace with your actual username
                String password = "root"; // Replace with your actual password

                // Connect to the database
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }
}
