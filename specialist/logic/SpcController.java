/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import common.logic.DBConnection;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.scene.control.TableView;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import parts.logic.partsUsed;
import vehicles.logic.Vehicle;

/**
 *
 * @author Yousuf
 */
public class SpcController extends Application {


    
    private TextField Spc_Search;
    TableView<SpcList> tableView;
    //Connection conn = Connect.dbConnector();
    DBConnection dbCon = DBConnection.getInstance();
    Connection conn = dbCon.getConnection();

    public ObservableList<SpcList> getSpcData(String input) {
        ObservableList<SpcList> obList = FXCollections.observableArrayList();
        try {
            DBConnection dbCon = DBConnection.getInstance();
            Connection conn = dbCon.getConnection();
            Statement st = conn.createStatement();

            String query = "SELECT * FROM Spc WHERE name LIKE '%" + input + "%' OR address = '" + input + "' OR phone = '" + input + "' OR email = '" + input + "'";

            ResultSet rsDataSpcMainList = st.executeQuery(query);

            while (rsDataSpcMainList.next()) {

                obList.add(new SpcList(rsDataSpcMainList.getInt("spcID"), rsDataSpcMainList.getString("name"),
                        rsDataSpcMainList.getString("address"), rsDataSpcMainList.getString("phone"),
                        rsDataSpcMainList.getString("email"), countOustandingParts(rsDataSpcMainList.getInt("spcID")), countOutstandingVechicles(rsDataSpcMainList.getInt("spcID"))));
            }
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return obList;
    }

    public ObservableList getVehicleData(String input, String type) {
        ObservableList<Vehicle> obList = FXCollections.observableArrayList();
        String searchParameter = "";
        ResultSet rs;

        String query;
        if (type.equals("man")) {
            searchParameter = "Manufacturer";
        } else {
            searchParameter = "Registration_Number";
        }

        try {
            Statement st = conn.createStatement();

            if (searchParameter.equals("Registration_Number")) {
                query = "SELECT * FROM Vehicle WHERE " + searchParameter + " LIKE '%" + input + "%'";

                rs = st.executeQuery(query);

            } else {
                query = "SELECT * FROM Vehicle WHERE " + searchParameter + " = '" + input + "'";
                rs = st.executeQuery(query);
            }
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();

                vehicle.setRegistrationNumber(rs.getString("Registration_Number"));

                vehicle.setMake(rs.getString("Manufacturer"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setVehicleType(rs.getString("Vehicle_Type"));
                vehicle.setCurrentMileage(rs.getInt("Current_Mileage"));
                vehicle.setHasWarranty(rs.getString("Warranty_Status"));
                obList.add(vehicle);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return obList;
    }

    public ObservableList getPartData(String input, String type) {
        ObservableList<partsUsed> obList = FXCollections.observableArrayList();
        String query = "";
        String searchParameter = "";

        int tempCustomerID = 100;
        ResultSet rsCompare = null;

        try {

            Statement st1 = conn.createStatement();

            if (type.equals("first")) {
                searchParameter = "Firstname";

                query = "select Booking.bookingID , Booking.CustomerID ,PartsUsed.DateInstalled ,PartsUsed.PartID , PartsUsed.PartUsedID, PartsUsed.WarrantyExpiry, Customer.FirstName, Customer.Lastname , Part.name, Part.part_cost , Part.description FROM Booking\n"
                        + "INNER JOIN Customer\n"
                        + "ON Booking.CustomerId = Customer.CustomerID \n"
                        + "\n"
                        + "INNER JOIN PartsUsed \n"
                        + "ON Booking.bookingID = PartsUsed.bookingID\n"
                        + "\n"
                        + "INNER JOIN Part\n"
                        + "ON Part.PartID = PartsUsed.PartID WHERE " + searchParameter + " LIKE '%" + input + "%'";
                ResultSet rs = st1.executeQuery(query);

                while (rs.next()) {

                    int partUsedId = rs.getInt("PartUsedID");
                    String dateInstalled = rs.getString("DateInstalled");
                    String warrarnty = rs.getString("WarrantyExpiry");
                    int partId = rs.getInt("PartID");
                    int bookingId = rs.getInt("bookingID");
                    String partName = rs.getString("name");
                    Double partPrice = rs.getDouble("part_cost");
                    String des = rs.getString("description");

                    partsUsed pu = new partsUsed(partUsedId, partName, dateInstalled, warrarnty, partPrice, partId, bookingId, des);
                    obList.add(pu);

                }
            } else if (type.equals("last")) {
                searchParameter = "Lastname";

                query = "select Booking.bookingID , Booking.CustomerID ,PartsUsed.DateInstalled ,PartsUsed.PartID , PartsUsed.PartUsedID, PartsUsed.WarrantyExpiry, Customer.FirstName, Customer.Lastname , Part.name, Part.part_cost , Part.description FROM Booking\n"
                        + "INNER JOIN Customer\n"
                        + "ON Booking.CustomerId = Customer.CustomerID \n"
                        + "\n"
                        + "INNER JOIN PartsUsed \n"
                        + "ON Booking.bookingID = PartsUsed.bookingID\n"
                        + "\n"
                        + "INNER JOIN Part\n"
                        + "ON Part.PartID = PartsUsed.PartID WHERE " + searchParameter + " LIKE '%" + input + "%'";
                ResultSet rs = st1.executeQuery(query);

                while (rs.next()) {

                    System.out.println("lnanjanfljansf");

                    int partUsedId = rs.getInt("PartUsedID");
                    String dateInstalled = rs.getString("DateInstalled");
                    String warrarnty = rs.getString("WarrantyExpiry");
                    int partId = rs.getInt("PartID");
                    int bookingId = rs.getInt("bookingID");
                    String partName = rs.getString("name");
                    Double partPrice = rs.getDouble("part_cost");
                    String des = rs.getString("description");

                    partsUsed pu = new partsUsed(partUsedId, partName, dateInstalled, warrarnty, partPrice, partId, bookingId, des);
                    obList.add(pu);
                }

            } else {
                searchParameter = "regNumber";
                /* query = "select Booking.bookingID , Booking.CustomerID ,PartsUsed.DateInstalled ,PartsUsed.PartID , "
                        + "PartsUsed.PartUsedID, PartsUsed.WarrantyExpiry,"
                        + " Customer.FirstName, Customer.Lastname , Part.name, Part.part_cost , Part.description FROM Booking\n"
                        + "INNER JOIN Customer\n"
                        + "ON Booking.CustomerId = Customer.CustomerID \n"
                        + "\n"
                        + "INNER JOIN PartsUsed \n"
                        + "ON Booking.bookingID = PartsUsed.bookingID\n"
                        + "\n"
                        + "INNER JOIN Part\n"
                        + "ON Part.PartID = PartsUsed.PartID "
                        
                        
                        + "WHERE Vehicle." + searchParameter + " LIKE '%" + input + "%'";*/

                query = "select PartsUsed.* , Part.* , Booking.* From Booking "
                        + "Inner Join PartsUsed ON "
                        + "Booking.Registration_Number = PartsUsed.regNumber "
                        + "Inner Join Part On "
                        + "Part.PartID = PartsUsed.PartID "
                        + "Where PartsUsed." + searchParameter + " Like '%" + input + "%' And Booking.ReturnBookingDate > datetime(date('now'))";

                ResultSet rs = st1.executeQuery(query);

                while (rs.next()) {

                    System.out.println("lnanjanfljansf");

                    int partUsedId = rs.getInt("PartUsedID");
                    String dateInstalled = rs.getString("DateInstalled");
                    String warrarnty = rs.getString("WarrantyExpiry");
                    int partId = rs.getInt("PartID");
                    int bookingId = rs.getInt("bookingID");
                    String partName = rs.getString("name");
                    Double partPrice = rs.getDouble("part_cost");
                    String des = rs.getString("description");

                    partsUsed pu = new partsUsed(partUsedId, partName, dateInstalled, warrarnty, partPrice, partId, bookingId, des);
                    obList.add(pu);
                    System.out.println(partUsedId + des);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return obList;
    }

    private Integer countOustandingParts(int spcId) {
        int numberOutstandingVehicles = 0;
        try {

            DBConnection dbCon1 = DBConnection.getInstance();
            Connection conn1 = dbCon1.getConnection();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            long dateStamp = date.getTime();

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            Statement st = conn1.createStatement();

            String query = "SELECT return_date FROM Spc_Bookings WHERE  partID IS NOT NULL AND spcID =  " + spcId;

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String tableDate = rs.getString("return_date");
                Date dateConvert = sdf.parse(tableDate);
                long tableDateUnixFormat = dateConvert.getTime();
                if (tableDateUnixFormat > dateStamp) {
                    numberOutstandingVehicles++;

                }

            }
            conn1.close();

        } catch (SQLException | ParseException e) {
            //e.printStackTrace();
            System.out.println(e.getMessage() + "yolo");
        } finally {
            if (conn != null) {
                try {
                    //conn.close();
                    //conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage() + "yodo");
                }
            }
        }
        return numberOutstandingVehicles;

    }

    private int countOutstandingVechicles(int spcId) {
        int numberOutstandingVehicles = 0;
        try {

           
            DBConnection dbCon1 = DBConnection.getInstance();
            Connection conn1 = dbCon1.getConnection();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            long dateStamp = date.getTime();

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            Statement st = conn1.createStatement();

            String query = "SELECT return_date FROM Spc_Bookings WHERE  vehicleID IS NOT NULL AND spcID =  " + spcId;

            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String tableDate = rs.getString("return_date");
                Date dateConvert = sdf.parse(tableDate);
                long tableDateUnixFormat = dateConvert.getTime();
                if (tableDateUnixFormat > dateStamp) {
                    numberOutstandingVehicles++;
                }
                

            }
            conn1.close();
        } catch (Exception e) {

            System.out.println(e.getMessage() + "ha");
        } finally {
            if (conn != null) {
                try {

                    //conn.close();
                    //conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage() + "ah");
                }
            }
        }
        return numberOutstandingVehicles;

    }

    public void addBooking(LocalDate delivery, LocalDate returned, int spcId, String reg, Integer partId, double cost) {
        try {
            Statement st = conn.createStatement();
            String query;
            if (reg == null) {

                ResultSet rs = st.executeQuery("Select Booking.*,  PartsUsed.* From PartsUsed\n"
                        + "Inner Join Booking ON\n"
                        + "  Booking.bookingID = PartsUsed.bookingID\n"
                        + "  Where Booking.ReturnBookingDate > datetime(date('now'))"
                        + "And PartsUsed.PartUsedID = '" + partId + "'");
                String extraction = "";
                //  while(rs.next())
                // {
                extraction = rs.getString("bookingID");
                //}
                query = "INSERT INTO Spc_Bookings (spcID , PartID, delivery_date, return_date, bookingID, bookingCost) VALUES ('" + spcId + "'  , '" + partId + "' , '"
                        + delivery + "' , '" + returned + "' , '" + extraction + "', '" + cost + "'" + ")";
            } else {

                ResultSet rs = st.executeQuery("select Vehicle.* , Booking.* From Vehicle \n"
                        + "Inner Join Booking on \n"
                        + "Booking.Registration_Number = Vehicle.Registration_Number\n"
                        + " Where Booking.ReturnBookingDate > datetime(date('now'))\n"
                        + "And Vehicle.Registration_Number = '" + reg + "'");
                String extraction = "";
                // while(rs.next())
                //{
                extraction = rs.getString("bookingID");
                //}
                query = "INSERT INTO Spc_Bookings (spcID , vehicleID, delivery_date, return_date, bookingID, bookingCost) VALUES ('" + spcId + "'  , '" + reg + "' , '"
                        + delivery + "' , '" + returned + "' , '" + extraction + "', '" + cost + "'" + ")";

            }
            System.out.println("jha");
            st.executeUpdate(query);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/Specialist.fxml"));
        primaryStage.setTitle("GM-SIS");
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

    public ObservableList<Vehicle> getVehicleDetails(int spcId, String reg) {
        ObservableList obList = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select Vehicle.Registration_Number, Vehicle.Model, Vehicle.Manufacturer, Spc_Bookings.delivery_date, Spc_Bookings.return_date From Spc_Bookings\n"
                    + "INNER Join Vehicle \n"
                    + "On Vehicle.Registration_Number = Spc_Bookings.vehicleID \n"
                    + "Where Spc_Bookings.return_date >= datetime(date('now')) AND Spc_Bookings.spcID =  '" + spcId + "'  And Vehicle.Registration_Number Like '%" + reg + "%'");

            System.out.println("first");;
            while (rs.next()) {

                Vehicle vehicle = new Vehicle();
                vehicle.setRegistrationNumber(rs.getString("Registration_Number"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setMake(rs.getString("Manufacturer"));
                //Really Delivery Date In disguise***********************//
                vehicle.setColour(rs.getString("delivery_date"));
                // Really Return DAte in disguise*************************//
                vehicle.setFuelType(rs.getString("return_date"));

                obList.add(vehicle);
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return obList;

    }

    public ObservableList<partsUsed> getPartsDetails(int spcId) {
        ObservableList obList = FXCollections.observableArrayList();
        try {
            System.out.println("aaaa");
            ResultSet rs = conn.createStatement().executeQuery("SELECT Part.part_cost, Part.stock_levels, PartsUsed.DateInstalled, PartsUsed.WarrantyExpiry, Part.part_cost"
                    + " , PartsUsed.bookingID, PartsUsed.PartUsedID, PartsUsed.PartID,"
                    + " Part.name, Part.description, Spc_Bookings.delivery_date, Spc_Bookings.return_date From Spc_Bookings\n"
                    + "INNER Join PartsUsed \n"
                    + "On Spc_Bookings.partID = PartsUsed.PartUsedID\n"
                    + "Inner Join Part    On Part.PartID = PartsUsed.PartID         \n"
                    + "Where Spc_Bookings.return_date >= datetime(date('now')) AND Spc_Bookings.spcID =  " + spcId);
            System.out.println("sss");
            while (rs.next()) {/////////////////////////////////////////////////////////////////////////////////////////fake delivery date (date installed)////////////////fake return date (warranty expiry)
                obList.add(new partsUsed(rs.getInt("PartUsedID"), rs.getString("name"), rs.getString("delivery_date"), rs.getString("return_date"), rs.getDouble("part_cost"), rs.getInt("PartID"), rs.getInt("bookingID"), rs.getString("description")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return obList;
    }

    public ObservableList<SpcBookings> getSpcBookingsData(String input) {

        ObservableList obList = FXCollections.observableArrayList();
        //String sql = "select * from Booking, Customer, Vehicle where Booking.[Registration number] = Vehicle.[Registration number] and Booking.CustomerID = Customer.CustomerID and (Customer.Firstname like '%" + input + "%' or Customer.[Last name] like '&" + input + "&' or Vehicle.[Registration number] like '%" + input + "%' or Vehicle.[Make] like '%" + input + "%')";
        try {

            String query = "select   Vehicle.*, Spc_Bookings.*, Customer.* From Spc_Bookings\n"
                    + "Inner Join  Vehicle ON\n"
                    + "Spc_Bookings.vehicleID= Vehicle.Registration_Number\n"
                    + "Inner Join Customer On\n"
                    + "Vehicle.CustomerID = Customer.CustomerID\n"
                    + "WHERE Customer.Firstname = '" + input + "' OR Customer.Lastname = '" + input + "' OR Vehicle.Registration_Number LIKE '%" + input + "%'";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                obList.add((new SpcBookings(rs.getInt("spcbookingID"), rs.getInt("spcID"), rs.getInt("partID"),
                        rs.getString("Registration_Number"), rs.getString("delivery_date"), rs.getString("return_date"), rs.getInt("bookingID"), rs.getDouble("bookingCost"))));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return obList;
    }

    public String getDetailsInfo(int bookingId) {
        String builder = "error getting Extra Details";
        try {
            String query = "select   Vehicle.*, Spc_Bookings.*, Customer.* From Spc_Bookings\n"
                    + "Inner Join  Vehicle ON\n"
                    + "Spc_Bookings.vehicleID= Vehicle.Registration_Number\n"
                    + "Inner Join Customer On\n"
                    + "Vehicle.CustomerID = Customer.CustomerID\n"
                    + "WHERE Spc_Bookings.spcbookingID = '" + bookingId + "'";
            System.out.println(bookingId);
            ResultSet rs = conn.createStatement().executeQuery(query);

            builder = rs.getString("Firstname") + " " + rs.getString("Lastname") + "\n" + rs.getString("Manufacturer") + "\n" + rs.getString("Model") + "\n" + rs.getString("Vehicle_Type") + "\n" + "Warranty Status " + rs.getString("Warranty_Status") + "\n" + "Service Date " + rs.getString("Last_Service") + "\nMileage "
                    + rs.getString("Current_Mileage");

            builder = builder + "\n Parts Intalled On Vehicle: ";
            String reg = rs.getString("Registration_Number");
            System.out.println(reg);
            query = "Select PartsUsed.*, Part.* From PartsUsed Inner Join Part On PartsUsed.PartID = Part.PartID Where PartsUsed.regNumber = '" + reg + "'";

            ResultSet rs2 = conn.createStatement().executeQuery(query);
            System.out.println("11");
            while (rs2.next()) {
                builder = builder + "\nPart Name: " + rs2.getString("name") + "\nDescription: " + rs2.getString("description");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return builder;
    }

    public ObservableList<partsUsed> viewAllSpcParts(int spcId) {
        ObservableList<partsUsed> obList = FXCollections.observableArrayList();
        try {

            String query = "Select Spc_Bookings.*, PartsUsed.*, Part.* From Spc_Bookings \n"
                    + "Inner Join PartsUsed On\n"
                    + "PartsUsed.PartUsedID = Spc_Bookings.partID\n"
                    + "Inner Join Part On\n"
                    + "PartsUsed.PartID = Part.PartID\n"
                    + "Where Spc_Bookings.spcID = " + spcId;
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                obList.add(new partsUsed(rs.getInt("PartUsedID"), rs.getString("name"), rs.getString("DateInstalled"), rs.getString("WarrantyExpiry"), rs.getDouble("part_cost"), rs.getInt("stock_levels")));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return obList;
    }

    public String getExtraPartDetailsForLabelMethod(int partUsedId) {
        String builder = "";
        System.out.println("ss");
        try {

            String query = "Select Spc_Bookings.*, PartsUsed.*, Part.* From Spc_Bookings \n"
                    + "Inner Join PartsUsed On\n"
                    + "PartsUsed.PartUsedID = Spc_Bookings.partID\n"
                    + "Inner Join Part On\n"
                    + "PartsUsed.PartID = Part.PartID\n"
                    + "Where PartsUsed.PartUsedID = " + partUsedId;
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                System.out.println("sd");
                builder = rs.getString("name") + "\n" + rs.getString("description") + "\npart cost: £" + rs.getInt("part_cost") + "\nStock Levels:" + rs.getInt("stock_levels") + "\nVehicle Installed In: " + rs.getString("regNumber");
                System.out.println(builder + rs.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return builder;
    }

  
    public void popupAddPartToVehicle(String vehicleId, String partid, int quant) {

        try {
            String bookingId = "";
            ResultSet rs = conn.createStatement().executeQuery("Select Booking.* , Spc_Bookings.* From Spc_Bookings\n"
                    + "Inner Join Booking \n"
                    + "ON Booking.Registration_Number = Spc_Bookings.vehicleID\n"
                    + "WHERE Booking.ReturnBookingDate > date('now') AND Booking.Registration_Number = '" + vehicleId + "'");

            //while (rs.next()) {
            bookingId = rs.getString("bookingID");

            //}
            System.out.println(bookingId + " kmkfmakmfklmasklf");
            rs = conn.createStatement().executeQuery("SELECT * from Part Where PartID = '" + partid + "'");
            System.out.println("a");
            Integer totalCost = rs.getInt("part_cost");
            System.out.println("b");
            String query = "INSERT INTO PartsUsed (DateInstalled, WarrantyExpiry, PartID, bookingID, regNumber, quantityParts, totalPartCost)"
                    + " VALUES (DATETIME('now'), DATETIME('now','+1 year'),'" + partid + "','" + bookingId + "','" + vehicleId + "', '" + quant + "','" + totalCost + "' )";

            conn.createStatement().executeUpdate(query);

            query = "UPDATE Part SET stock_levels = stock_levels - '" + quant + "' WHERE '" + partid + "' = Part.PartID";
            conn.createStatement().executeUpdate(query);

            // Stage stage = (Stage) addPartUsedToDbBtn.getScene().getWindow();
            //stage.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("The amount of parts withdrawn for this stock item: " + quant);
            alert.showAndWait();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public ObservableList getDeletionPartList(String vehicleId) {
        ObservableList obList = FXCollections.observableArrayList();

        try {
            ResultSet rs = conn.createStatement().executeQuery("Select Part.*, PartsUsed.* From PartsUsed\n"
                    + "Inner join Part\n"
                    + "on Part.PartID = PartsUsed.PartID\n"
                    + "Where PartsUsed.regNumber = '" + vehicleId + "'");

            while (rs.next()) {
                obList.add(new partsUsed(rs.getInt("PartUsedID"), rs.getString("name"),
                        rs.getString("DateInstalled"), rs.getString("WarrantyExpiry"),
                        rs.getDouble("part_cost"), rs.getInt("PartID"), rs.getInt("bookingID"), rs.getString("description")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return obList;
    }

    public void deletePartsFromBookedVehiclePopUpAction(int partUsedID, String regNum) {
        try {

            String query = "Delete From PartsUsed Where PartsUsed.PartUsedID = '" + partUsedID + "' ANd PartsUsed.regNumber = '" + regNum + "'";
            conn.createStatement().execute(query);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public ObservableList getListOfBookings() {

        ObservableList obList = FXCollections.observableArrayList();
        try {

            ResultSet rs = conn.createStatement().executeQuery("SELECT * From Spc_Bookings ");
            while (rs.next()) {
                obList.add(new SpcBookings(rs.getInt("spcbookingID"), rs.getInt("spcID"), rs.getInt("partID"),
                        rs.getString("vehicleID"), rs.getString("delivery_date"), rs.getString("return_date"),
                        rs.getInt("bookingID"), rs.getDouble("bookingCost")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        return obList;
    }

    public void deleteBookingPopUpAction(int spcBookingId) {

        try {

            String query = "Delete From Spc_Bookings Where  Spc_Bookings.spcbookingID = '" + spcBookingId + "'";
            conn.createStatement().execute(query);
            getListOfBookings();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public String getExtraVehicleDetailsForLabelMethod(String registrationNumber) {
        String builder = "";
        try {
            ResultSet rs = conn.createStatement().executeQuery("Select Vehicle.* , Customer.* From Vehicle \n"
                    + "Inner Join Customer On \n"
                    + " Customer.CustomerID  = Vehicle.CustomerID\n"
                    + " Where Vehicle.Registration_Number = '" + registrationNumber + "'");
            builder = rs.getString("Firstname") + rs.getString("Lastname") + "\n" + rs.getString("Address") + "\n" + rs.getString("Postcode")
                    + "\n" + rs.getString("Phone") + "\n" + rs.getString("email") + "\nCutomer Type:" + rs.getString("Type");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        System.out.println(builder);
        return builder;
    }

    public ObservableList<Vehicle> getVehicleDetailsAll(int spcId, String reg) {
        ObservableList obList = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select Vehicle.Registration_Number, Vehicle.Model, Vehicle.Manufacturer, Spc_Bookings.delivery_date, Spc_Bookings.return_date From Spc_Bookings\n"
                    + "INNER Join Vehicle \n"
                    + "On Vehicle.Registration_Number = Spc_Bookings.vehicleID \n"
                    + "Where Spc_Bookings.spcID =  '" + spcId + "'  And Vehicle.Registration_Number Like '%" + reg + "%'");

            System.out.println("first");;
            while (rs.next()) {

                Vehicle vehicle = new Vehicle();
                vehicle.setRegistrationNumber(rs.getString("Registration_Number"));
                vehicle.setModel(rs.getString("Model"));
                vehicle.setMake(rs.getString("Manufacturer"));
                //Really Delivery Date In disguise***********************//
                vehicle.setColour(rs.getString("delivery_date"));
                // Really Return DAte in disguise*************************//
                vehicle.setFuelType(rs.getString("return_date"));

                obList.add(vehicle);
            }
        } catch (SQLException e) {

            System.out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        return obList;
    }

}
