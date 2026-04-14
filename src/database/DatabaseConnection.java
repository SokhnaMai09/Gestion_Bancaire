package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/gestion_bancaire";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)
            ;
            
            System.out.println("Connexion réussie !");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}