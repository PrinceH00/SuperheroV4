package com.example.superherov4;

import java.sql.*;

public class Superhero_DB {
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;
    String SQL;
    Connection connection;

    public Connection connection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Superhero_Database", "root", "Prince2660");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
