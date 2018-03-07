/**
 * Created by abdul hadi on 2/20/2017.
 */
/*
 * This DBConnection class is created to make connection with the database.
 * Its object type is used for connecting with the database from other classes.  
 */

package common.logic;

// importing required packages 
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;



public class DBConnection {

    private static final DBConnection INSTANCE = new DBConnection();

    private Connection connection = null;

    private DBConnection() {

        try {
            Driver driver1 = new org.sqlite.JDBC();
            DriverManager.registerDriver(driver1);
            connection = DriverManager.getConnection("jdbc:sqlite::resource:Database.db");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
    }



    public static DBConnection getInstance() {

        return INSTANCE;
    }

    public Connection getConnection(){
        try {
           connection = DriverManager.getConnection("jdbc:sqlite:Database.db");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        return this.connection;
    }
}

