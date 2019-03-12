package src;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class db_cnn {

    public static void main(String[] argv) {

        db_cnn a = new db_cnn();
        a.connectionDB();
    }
    public static Connection connectionDB(){
    	
    	System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
           

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "admin", "eisti");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
           

        }

        if (conn != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return conn;
    	
    }

}