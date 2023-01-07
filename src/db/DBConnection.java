package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection ;
    private Connection connection;
    private DBConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/vehicle_service","root","Pasan$@776144");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static DBConnection getInstance(){
        return(dbConnection==null) ? dbConnection= new DBConnection() : dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
