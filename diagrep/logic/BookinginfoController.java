/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import common.logic.DBConnection;
import diagrep.logic.Bookings;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Sajjad
 */
public class BookinginfoController implements Initializable {

	DBConnection db = DBConnection.getInstance();
        Connection connection = db.getConnection();

    @FXML
    private TextArea info;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void showDetails(Bookings b){
        
               
        info.setText("First name: " +b.getFirstName() + "\nLast name: "+b.getSurname() + "\nStart Booking date: " + b.getDate()+ "\nReturn"
                + " Booking date: " + b.getNextBooking());
        
        
         String sql = "SELECT * FROM Booking WHERE bookingID =" + b.getID();
          String sqlpart = "SELECT * FROM PartsUsed INNER JOIN Part ON PartsUsed.PartID=Part.PartID WHERE PartsUsed.bookingID =" + b.getID();
          String sqlvehicle = "SELECT * FROM Vehicle WHERE Registration_Number ='" + b.getReg()+"'";
         
                  
          ResultSet rs1;
          ResultSet rs2;
          ResultSet rs3;
          String details="";
           try {
            Statement st1 = connection.createStatement();
            Statement st2 = connection.createStatement();
            Statement st3 = connection.createStatement();
            
             rs1 = st1.executeQuery(sql);
           rs2 = st2.executeQuery(sqlpart);
           rs3 = st3.executeQuery(sqlvehicle);
            details = "First name: " +b.getFirstName() + "\nLast name: "+b.getSurname() + "\nStart Booking date: " + b.getDate()+ "\nReturn"
                + " Booking date: " + b.getNextBooking()+"\nBooking type: " +rs1.getString("booking_type") + "\nVehicle REG: " + rs1.getString("Registration_Number")
                    + "\nMake: " + b.getMaker()
                    +"\nCurrent Mileage: "+rs3.getInt("Current_Mileage")
                    + "\nLabour cost: " + rs1.getInt("LabourCost")
                    + "\nHours spent on booking: "+ rs1.getString("Hoursspent")+"\nPart(s) used: ";
            
            while(rs2.next()){
                details = details + " " + rs2.getString("name")+",";
            }
            
            details = details + " " + "  ";
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("could not fine mechanic ");
        }
        finally{
            if(connection!= null)
            {
                try{
                    connection.close();
                    connection = db.getConnection();
                }
                catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
           
            info.setText(details);
          
            }
  
   
}
