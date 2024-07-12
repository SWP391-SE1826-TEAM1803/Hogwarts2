package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    Connection conn = null;
    // Connection

    public DBConnect(String URL, String userName, String password) {
        try {
            //driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //connect
            conn = DriverManager.getConnection(URL, userName, password);
            System.out.println("Connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public DBConnect() {
<<<<<<< HEAD
        this("jdbc:sqlserver://localhost:1433;databaseName=hw10",
                "sa","123123");
=======
        this("jdbc:sqlserver://localhost:1433;databaseName=Hogwart",
                "sa","12345678");

>>>>>>> fea1e6a80145972132f235d26c73de184bcfc602
    }
    
    public static void main(String[] args) {
        new DBConnect();
    }

}
