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
        this("jdbc:sqlserver://localhost:1433;databaseName=Hogwart",
=======
<<<<<<< HEAD
        this("jdbc:sqlserver://localhost:1433;databaseName=howart11",
                "sa","123123");

=======
        this("jdbc:sqlserver://localhost:1433;databaseName=hw11",
>>>>>>> 934c762eb3fa13cf0cf25226b466c4e8fb19da2d

                "sa","12345678");

>>>>>>> 658b10b26b13d12cf70fb94269bcf07d7ab68c95
    }

    public static void main(String[] args) {
        new DBConnect();
    }

}
