/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author Polman
 */
public class DBConnect {
    public Connection conn;
    public Statement stat;
    public ResultSet result;
    public PreparedStatement pstat;
    
    public DBConnect(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;"+
                     "databaseName = RTCollection;user = sa; password = polman";
            conn = DriverManager.getConnection(url);
            stat = conn.createStatement();
            System.out.println("Connection berhasil");
        }
        catch(Exception e){
            System.out.println("Error DBConnect!"+e.toString());
        }
    }
    
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;"+
                     "databaseName = RTCollection;user = sa; password = polman"; //ganti dengan database mu
        String user = "sa";
        String password = "polman";
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Bisa");
            } catch (ClassNotFoundException | SQLException error) {
                System.out.println("Gabisa " + error);
               System.exit(0);
            }

        }
        return connection;
    }
}
