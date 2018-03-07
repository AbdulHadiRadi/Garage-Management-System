package vehicles.logic;
import common.logic.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by abdul on 2/28/2017.
 * This class is created to Delete Vehicle from Database
 */
public class DeleteFromDB {
    //Instance variables
    private String registrationNumber;

    public DeleteFromDB(String reg) {
        registrationNumber = reg;
    }
    //delete Vehicle Table row with the given registration number from the database
    public void getVehicleDeleted() {
        //Getting Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();

        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("DELETE FROM Vehicle WHERE Registration_Number ='" + registrationNumber + "'");//Delete the vehicle of the given registration number
             //Close Statement and Connection
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    //delete Warranty Table row with the given registration number from the database
    public void getWarrantyDeleted() {
           //Get Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();

        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("DELETE FROM Warranty WHERE Registration_Number ='" + registrationNumber + "'");//Delete the vehicle's warranty with the given registration number
             //Close Statement and Connection
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    //delete Parts Table row with the given registration number from the database
    public void getPartsDeleted() {
        //Get Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();

        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("DELETE FROM PartsUsed WHERE regNumber ='" + registrationNumber + "'");//Delete UsedParts of the vehilce with the given registration number.
             //Close Statement and Connection
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    //delete Bookings Table row with the given registration number from the database
    public void getBookingDeleted() {
        //Get Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();

        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("DELETE FROM Booking WHERE Registration_Number='" + registrationNumber + "'");//Delete Bookings of the vehilce with the given registration number.
            //Close Statement and Connection
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

}
