package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/recipe_app";
    private static final String USER = "root";
    private static final String PASSWORD = "ndtdragon8796";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("OK");
        } catch (ClassNotFoundException e) {
            System.out.println("Not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed");
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Closed");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
