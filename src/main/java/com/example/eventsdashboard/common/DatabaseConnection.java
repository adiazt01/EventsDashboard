package com.example.eventsdashboard.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private Connection connection;
    private String url = "jdbc:sqlite:events.db";

    private DatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(url);
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                    return instance;
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return this.connection;
    }
}

