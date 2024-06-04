package customers.logic;

import common.logic.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 * Created by Fahim on 19/02/2017.
 */
public class CustomerController implements Initializable {

    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> id;
    @FXML
    private TableColumn<Customer, String> fName;
    @FXML
    private TableColumn<Customer, String> sName;
    @FXML
    private TableColumn<Customer, String> add;
    @FXML
    private TableColumn<Customer, String> postcode;
    @FXML
    private TableColumn<Customer, String> phone;
    @FXML
    private TableColumn<Customer, String> email;
    @FXML
    private TableColumn<Customer, String> type;

    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> reg;
    @FXML
    private TableColumn<Vehicle, String> model;
    @FXML
    private TableColumn<Vehicle, String> make;
    @FXML
    private TableColumn<Vehicle, String> size;
    @FXML
    private TableColumn<Vehicle, String> fuel;
    @FXML
    private TableColumn<Vehicle, String> colour;
    @FXML
    private TableColumn<Vehicle, String> mot;
    @FXML
    private TableColumn<Vehicle, String> last;
    @FXML
    private TableColumn<Vehicle, Integer> mile;

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, Integer> bookingID;
    @FXML
    private TableColumn<Booking, String> bookingType;
    @FXML
    private TableColumn<Booking, Integer> bookingDate;
    @FXML
    private TableColumn<Booking, String> vehicleReg;
    @FXML
    private TableColumn<Booking, Integer> mechanic;
    @FXML
    private TableColumn<Booking, Integer> duration;
    @FXML
    private TableColumn<Booking, String> bookingTime;
    @FXML
    private TableColumn<Booking, Integer> nextBookingDate;
    @FXML
    private TableColumn<Booking, Integer> labourCost;
    @FXML
    private TableColumn<Booking, String> hoursSpent;

    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> part_UsedID;
    @FXML
    private TableColumn<Part, String> date_installed;
    @FXML
    private TableColumn<Part, Integer> partid;
    @FXML
    private TableColumn<Part, Integer> booking_id;
    @FXML
    private TableColumn<Part, String> warrExp;
    @FXML
    private TableColumn<Part, String> reg_num;

    @FXML
    private TableView<Bill> billTable;
    @FXML
    private TableColumn<Bill, Integer> billID;
    @FXML
    private TableColumn<Bill, Integer> billBookingID;
    @FXML
    private TableColumn<Bill, Integer> totalCost;
    @FXML
    private TableColumn<Bill, Float> billStatus;

    @FXML
    private Button custLogOut;

    //logs out of the system
    @FXML
    public void logoutButtonAction(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("common/gui/login.fxml"));
        Scene scene = new Scene(parent);
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.setScene(scene);
    }

    @FXML
    private TextField search_bar;

    private static Customer selected;

    private static Bill selected1;

    //connects to database using instance connection
    DBConnection conn = DBConnection.getInstance();
    Connection db = conn.getConnection();

    //innitalliazes the table and fill in the customer data
    public void initialize(URL location, ResourceBundle resourceBundle) {
        fillTable();
        search_bar.textProperty().addListener((ob, oldVal, newVal) -> {
            search();
        });
        String sql = "SELECT *  FROM Customer";
        try {

            Statement st = db.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                customers.add(new Customer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn.getConnection() != null) {
                try {
                    conn.getConnection().close();
                    conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

        customerTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    selected = customerTable.getSelectionModel().getSelectedItem();

                    fillVehicleTable();
                    fillBookingTable();
                    fillPartTable();
                    fillBillsTable();
                }
        );

        sql = "SELECT *  FROM Vehicle";
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                vehicles.add(new Vehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn.getConnection() != null) {
                try {
                    conn.getConnection().close();
                    conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    //quesries the table and fills up the table
    public void fillTable() {
        setValueFactories();
        ArrayList<Customer> tableValues = new ArrayList<>();
        String sql = "SELECT *  FROM Customer";
        try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                tableValues.add(new Customer(rs));
            }
            customerTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn.getConnection() != null) {
                try {
                    conn.getConnection().close();
                    conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    //loads up the add pop up window 
    @FXML
    public void add() {
        try {
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("customers/gui/AddCustomer.fxml"));
            newScreen.setTitle("New Customer");
            newScreen.setScene(new Scene(parent));
            newScreen.showAndWait();
            fillTable();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn.getConnection() != null) {
                try {
                    conn.getConnection().close();
                    conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    //loads up the add bill window
    @FXML
    public void addBill() {
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please select a customer you wish to add a bill to");
            alert.showAndWait();
        } else {
            try {
                selected = customerTable.getSelectionModel().getSelectedItem();
                Stage newScreen = new Stage();
                newScreen.initModality(Modality.APPLICATION_MODAL);
                Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("customers/gui/addNewBill.fxml"));
                newScreen.setTitle("New Bill");
                newScreen.setScene(new Scene(parent));
                newScreen.showAndWait();
                fillBillsTable();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn.getConnection() != null) {
                    try {
                        conn.getConnection().close();
                        conn = DBConnection.getInstance();
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        }
    }

    //loads up the edit window
    @FXML
    public void edit() {
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Please select the customer you want to edit");
            alert.showAndWait();
        } else {
            try {
                selected = customerTable.getSelectionModel().getSelectedItem();
                Stage newScreen = new Stage();
                newScreen.initModality(Modality.APPLICATION_MODAL);
                Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("customers/gui/EditCustomer.fxml"));
                newScreen.setTitle("New Customer");
                newScreen.setScene(new Scene(parent));
                newScreen.showAndWait();
                fillTable();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn.getConnection() != null) {
                    try {
                        conn.getConnection().close();
                        conn = DBConnection.getInstance();
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        }
    }

    //deletes the selected customer
    @FXML
    public void delete() {
        Customer selected = customerTable.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("GMSIS");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete " + selected.getfName() + " " + selected.getsName() + "?");
        Optional<ButtonType> response = alert.showAndWait();
        if (response.get() == ButtonType.OK) {
            update("DELETE FROM Customer WHERE CustomerID = " + selected.getId());
            update("DELETE FROM Vehicle WHERE CustomerID = " + selected.getId());
            update("DELETE FROM Booking WHERE CustomerID = " + selected.getId());
            String sql = "SELECT Registration_Number FROM Vehicle WHERE CustomerID = " + selected.getId();
        
            try {
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                while(rs.next()) {
                    update("DELETE FROM PartsUsed WHERE regNumber = " + rs);
                }
            }
            } catch (SQLException e) {
            e.printStackTrace();
            }
            
            fillTable();
        }

    }

    public static Customer getSelected() {
        return selected;
    }

    public static Bill getSelected1() {
        return selected1;
    }

    private void fillVehicleTable() {
        reg.setCellValueFactory(new PropertyValueFactory<>("reg"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        make.setCellValueFactory(new PropertyValueFactory<>("make"));
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        fuel.setCellValueFactory(new PropertyValueFactory<>("fuel"));
        colour.setCellValueFactory(new PropertyValueFactory<>("colour"));
        mot.setCellValueFactory(new PropertyValueFactory<>("mot"));
        last.setCellValueFactory(new PropertyValueFactory<>("last"));
        mile.setCellValueFactory(new PropertyValueFactory<>("mile"));
        ArrayList<Vehicle> tableValues = new ArrayList<>();
        String sql;
        try {
            sql = "SELECT *  FROM Vehicle WHERE CustomerID = " + selected.getId();

            try {
                Statement st = db.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    tableValues.add(new Vehicle(rs));
                }
                vehicleTable.setItems(FXCollections.observableArrayList(tableValues));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn.getConnection() != null) {
                    try {
                        conn.getConnection().close();
                        conn = DBConnection.getInstance();
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        } catch (NullPointerException e) {

        }

    }

    //sets the cell values for booking table and queries the booking table 
    private void fillBookingTable() {
        bookingID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        bookingType.setCellValueFactory(new PropertyValueFactory<>("bookingType"));
        vehicleReg.setCellValueFactory(new PropertyValueFactory<>("vehicleReg"));
        mechanic.setCellValueFactory(new PropertyValueFactory<>("mechanic"));
        nextBookingDate.setCellValueFactory(new PropertyValueFactory<>("nextBookingDate"));
        labourCost.setCellValueFactory(new PropertyValueFactory<>("labourCost"));
        hoursSpent.setCellValueFactory(new PropertyValueFactory<>("hoursSpent"));
        bookingTime.setCellValueFactory(new PropertyValueFactory<>("bookingTime"));
        bookingDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));

        ArrayList<Booking> tableValues = new ArrayList<>();
        try {

            String sql = "SELECT Registration_Number FROM Vehicle WHERE CustomerID = " + selected.getId();
            System.out.println(selected.getId());
            //ResultSet rs = db.query(sql);
            try {
                Statement st = db.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    ResultSet rs1 = st.executeQuery("SELECT * FROM Booking WHERE Registration_Number = '" + rs.getString("Registration_Number") + "'");
                    while (rs1.next()) {
                        tableValues.add(new Booking(rs1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn.getConnection() != null) {
                    try {
                        conn.getConnection().close();
                        conn = DBConnection.getInstance();
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
            bookingTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (NullPointerException e) {

        }

    }

    //sets the cell values for booking table and queries the parts used table 
    private void fillPartTable() {
        part_UsedID.setCellValueFactory(new PropertyValueFactory<>("part_UsedID"));
        date_installed.setCellValueFactory(new PropertyValueFactory<>("DateInstalled"));
        partid.setCellValueFactory(new PropertyValueFactory<>("PartID"));
        booking_id.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
        warrExp.setCellValueFactory(new PropertyValueFactory<>("warrExp"));
        reg_num.setCellValueFactory(new PropertyValueFactory<>("reg_num"));

        ArrayList<Part> tableValues = new ArrayList<>();
        try {
            String sql = "SELECT Registration_Number FROM Vehicle WHERE CustomerID = " + selected.getId();
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                ResultSet rs2 = st.executeQuery("SELECT * FROM PartsUsed WHERE regNumber = '" + rs.getString("Registration_Number") + "'");
                while (rs2.next()) {
                    tableValues.add(new Part(rs2));
                }
            }
            partTable.setItems(FXCollections.observableArrayList(tableValues));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
        } finally {
            if (conn.getConnection() != null) {
                try {
                    conn.getConnection().close();
                    conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

    }

    //sets the cell values for booking table and queries the Bill table 
    public void fillBillsTable() {

        billBookingID.setCellValueFactory(new PropertyValueFactory<>("billBookingID"));
        billID.setCellValueFactory(new PropertyValueFactory<>("billID"));
        totalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        billStatus.setCellValueFactory(new PropertyValueFactory<>("billStatus"));

        ArrayList<Bill> tableValues = new ArrayList<>();

        tableValues.clear();
        try {
            String sql = "SELECT Registration_Number FROM Vehicle WHERE CustomerID = " + selected.getId();
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ResultSet rs1 = st.executeQuery("SELECT bookingID FROM Booking WHERE Registration_Number = '" + rs.getString("Registration_Number") + "'");
                while (rs1.next()) {
                    ResultSet rs2 = st.executeQuery("SELECT Spc_Bookings.bookingCost, Bill.*, Booking.LabourCost, PartsUsed.totalPartCost FROM Bill \n"
                            + "Inner JOIN Booking \n"
                            + "ON Booking.bookingID = Bill.bookingID \n"
                            + "Inner Join Spc_Bookings\n"
                            + "On Spc_Bookings.spcbookingID = Bill.spcBookingID\n"
                            + "Inner Join PartsUsed\n"
                            + "On PartsUsed.regNumber = Booking.Registration_Number\n"
                            + "WHERE bill.bookingID =" + rs1.getString("bookingID"));
                    while (rs2.next()) {
                        tableValues.add(new Bill(rs2));
                    }
                }
            }

            billTable.setItems(FXCollections.observableArrayList(tableValues));

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

        } finally {
            if (conn.getConnection() != null) {
                try {
                    conn.getConnection().close();
                    conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }

    }
    
    //search for customers by first name, surname and vehicle registration
     @FXML
    public void search()
    {
        try {
            customerTable.getItems().clear();
            setValueFactories();
            ArrayList<Customer> searchedItems = new ArrayList<>();
            ArrayList<Customer> listCustomers = new ArrayList<>(customers);
            for (Customer customer : listCustomers)
            {
                if ((customer.getfName().toLowerCase().contains(search_bar.getText().toLowerCase())) ||
                        (customer.getsName().toLowerCase().contains(search_bar.getText().toLowerCase())) ||
                        (search_bar.getText().toLowerCase().contains(customer.getfName().toLowerCase())) ||
                        (search_bar.getText().toLowerCase().contains(customer.getsName().toLowerCase())))
                {
                    searchedItems.add(customer);
                    listCustomers.remove(customer);
                    break;
                }
            }
            for (Vehicle vehicle : vehicles)
            {
                if(vehicle.getReg().toLowerCase().contains(search_bar.getText().toLowerCase()))
                {
                    for(Customer c : listCustomers)
                    {
                        if (c.getId() == vehicle.getCustomer_vehicle())
                        {
                            searchedItems.add(c);
                            listCustomers.remove(c);
                            break;
                        }
                    }
                }
            }

            ObservableList searched = FXCollections.observableArrayList(searchedItems);
            customerTable.setItems(searched);
        }
        catch (NullPointerException e)
        {
            System.out.println("Empty");
        }
        finally
        {
            if (conn.getConnection() != null) {
                try
                {
                    conn.getConnection().close();
                    DBConnection conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    //sets the cell value for the customer 
    private void setValueFactories() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        fName.setCellValueFactory(new PropertyValueFactory<>("fName"));
        sName.setCellValueFactory(new PropertyValueFactory<>("sName"));
        add.setCellValueFactory(new PropertyValueFactory<>("add"));
        postcode.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    //update the database 
    public void update(String sql) {
        try {
            Statement stmt = db.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (NullPointerException ne) {
        } finally {
            if (conn.getConnection() != null) {
                try {
                    conn.getConnection().close();
                    conn = DBConnection.getInstance();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
