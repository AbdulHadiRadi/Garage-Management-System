/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import org.sqlite.JDBC;

public class ConnectToDatabase {

    private Connection conn = null;

    private Connection connect() {
        // String url = "jdbc:sqlite:Records.db";
        conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Sajjad\\Documents\\NetBeansProjects\\SE13\\GMSIS\\Master\\working\\Database\\GarageSystem.db");
            // C:\Users\Sajjad\Documents\NetBeansProjects\SE13\GMSIS\Master\working\Database
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {
        String sql = "INSERT into Employee(id,hourly_rate) values (?,?)";
        int id = 19;
        try {

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, 99);
            stat.setInt(2, 999);

            //stat.setInt(3, 200);
            // stat.setInt(3, 19);
            stat.execute();
            conn.close();
            {
                // loop through the result set
                //  while(rs.next())
                //{
                //  System.out.println(rs.getInt("id") + "\t" );

                //}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getData(int ID) {

    }

    public static void main(String[] args) throws Exception {
        ConnectToDatabase obj = new ConnectToDatabase();

        obj.connect();
        // obj.selectAll();
        //   obj.getData();
    }

    public void authenticate(String username, String password) {

        String sql = "SELECT  from User WHERE Username=? AND Password=?";

        try {
            Connection conn = this.connect();

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, username);
            stat.setString(2, password);
            stat.execute();
            //  ResultSet result=stat.executeQuery(sql);
            //while(result.next()){
            //  System.out.println(result.getString("Username"));
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void addnewbooking(int store) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates

        Connection con = connect();
        String sql = "SELECT CustomerID,First Name,Last Name FROM Customer WHERE ID=?";
        try {
            PreparedStatement prep = con.prepareStatement(sql);
            prep.setInt(1, store);
            ResultSet rs = prep.executeQuery();
            System.out.println("we");
            while (rs.next()) {

                System.out.println(rs.getInt("CustomerID") + rs.getString("First Name"));
            }

        } catch (SQLException e) {

        }

    }
    
    public void getCustomerDetails(int id){
    
    Connection conn = connect();
    String sql = "SELECT CustomerID, First name, Last name FROM Customer WHERE CustomerID=?";
    
    try {
           // Connection conn = connect();

            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setInt(1, id);
            stat.execute();  
            ResultSet print = stat.executeQuery();
            while (print.next()) {

                System.out.println(print.getString("First name") + print.getString("Last name"));
            }
            conn.close();
        }
    catch (SQLException e) {

        }
    
    }

}
