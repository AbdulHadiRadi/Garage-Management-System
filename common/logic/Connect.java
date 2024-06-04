package common.logic;

/**
 * 
 */

//@@author Yousuf

//package common;
import java.sql.*;
import javax.swing.*;
//import javafx.*;
public class Connect {

    Connection conn=null;
    public static Connection dbConnector(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn= DriverManager.getConnection("jdbc:sqlite:src/Database.db");
            
            return conn;
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;

        }
    }

    public boolean authenticate(String user , String pass)
    {	conn = Connect.dbConnector();
        System.out.println(user + "   " + pass );
        System.out.println("[OUTPUT FROM SELECT]");
        //String query= "SELECT username,password WHERE username = '"+ user +"'";
        String query = "SELECT username, password FROM User WHERE username = '"+user+"'";
        try
        {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println(rs.getString("username"));
            if(rs.getString("username").equals(user)&& rs.getString("password").equals(pass))
            {
                System.out.println("access granted");
                conn.close();
                return true;
            }
            else {
                System.out.println("access denied");
                conn.close();
                return false;
            }


            //System.out.println("uncessfull mate ");
        }
        catch (SQLException ex)
        {
            System.err.println(ex.getMessage());
            //conn.close();
        }

        return false;
    }
}



