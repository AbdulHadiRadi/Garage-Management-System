//Importing required packages
package vehicles.logic;
import common.logic.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by abdul on 2/28/2017.
 * This class is created for adding and updating Vehicle detail and warrant detail to the Database
 */
public class AddToDB {
    //instance Variables Declaration
    private Vehicle vehicle;
    private Warranty warranty;
    private String regNumb;
     //Constructor
    public AddToDB(Vehicle veh,Warranty war, String reg){
        vehicle = veh;
        warranty = war;
        regNumb = reg;
    }
     //Constructor
    public AddToDB(Vehicle veh,Warranty war){
        vehicle = veh;
        warranty = war;
    }
     //Constructor
    public AddToDB(Vehicle vehicle){
        this.vehicle = vehicle;
    }
     //Constructor
    public AddToDB(Vehicle vehicle,String reg){
        this.vehicle = vehicle;
        this.regNumb = reg;
    }
    // Adding new Vehicle Table row to the database
    public void getVehicleAdded(){
        //Getting Connection
        DBConnection db =  DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("insert into `Vehicle` values('"+vehicle.getModel()+"','"+vehicle.getColour()+"','"+vehicle.getRegistrationNumber()+"','"+vehicle.getMake()+"','"+vehicle.getEngineSize()+"','"+vehicle.getFuelType()+"','"+vehicle.getVehicleType()+"','"+vehicle.getHasWarranty()+"','"+vehicle.getMoTRenewalDate()+"','"+vehicle.getLastServiceDate()+"','"+vehicle.getCurrentMileage()+"','"+vehicle.getCustomerID()+"')");//Insert Querry
            //Closing Statement and Connection
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (db.getConnection() != null) {
                try {
                    db.getConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
     // Adding new Warranty Table row to the database
    public void getWarrantyAdded(){
        //Getting Connection
        DBConnection db =  DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("insert into 'Warranty' values ('"+vehicle.getRegistrationNumber()+"','"+warranty.getCompanyName()+"','"+warranty.getCompanyAddress()+"','"+warranty.getExpirationDate()+"')");//Inset Querry
            //Closing Statement and Connection
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (db.getConnection() != null) {
                try {
                    db.getConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    //Updating Vehicle Table row with the given registration in the database
    public void getVehicleUpdated(){
        //Getting Connection
        DBConnection db =  DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("UPDATE `Vehicle` SET Model= '"+vehicle.getModel()+"', Colour= '"+vehicle.getColour()+"', Manufacturer = '"+vehicle.getMake()+"', Engine_Size='"+vehicle.getEngineSize()+"', Fuel_Type = '"+vehicle.getFuelType()+"', Vehicle_Type = '"+vehicle.getVehicleType()+"', Warranty_Status = '"+vehicle.getHasWarranty()+"', MOT_Renewal_Date= '"+vehicle.getMoTRenewalDate()+"', Last_Service = '"+vehicle.getLastServiceDate()+"', Current_Mileage = '"+vehicle.getCurrentMileage()+"',  Registration_Number = '"+vehicle.getRegistrationNumber()+"', CustomerID= '"+vehicle.getCustomerID()+"' WHERE Registration_Number= '"+regNumb+"'");//Update Vehicle of the given registration Number
            //Closing Statement and Connection
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (db.getConnection() != null) {
                try {
                    db.getConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    //Updating Warranty Table row of the vehicle of the given registration in the database
    public void getWarrantyUpdated(){
        //Getting Connection
        DBConnection db =  DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("UPDATE 'Warranty' SET Registration_Number ='"+vehicle.getRegistrationNumber()+"', company_name = '"+warranty.getCompanyName()+"', company_address = '"+warranty.getCompanyAddress()+"', expiry_date = '"+warranty.getExpirationDate()+"'WHERE Registration_Number = '"+regNumb+"'");//update warranty of the Vehicle of given registration number
            //Closing Statement and Connection
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (db.getConnection() != null) {
                try {
                    db.getConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
