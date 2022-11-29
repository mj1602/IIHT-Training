package com.tweetapp.dao;

import java.sql.*;

public class TweetAppDao {
    private static final String DB_url="jdbc:mysql://localhost:3306/tweetdb";
    private static final String user="root";
    private static final String pass="pass@word1";

    public static Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(DB_url, user, pass);

        } catch (SQLException e) {
            throw new RuntimeException("Error: Can't connect to database", e);
        }
        return conn;
    }
}

