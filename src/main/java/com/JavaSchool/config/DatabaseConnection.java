package com.JavaSchool.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/JavaSchoolProject";; // Update DB name
    private static final String USER = "postgres"; // Update username
    private static final String PASSWORD = "123456"; // Update password
    private static Connection connection = null;

    // Private constructor to prevent instantiation
    private DatabaseConnection() {}

    // Establish and return a single connection instance
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to connect to database.");
            }
        }
        return connection;
    }

    // Close connection when application terminates
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
