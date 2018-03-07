/*Abdul Hadi : Software Group Project
* Controller of the Vechile module
*/
package vehicles.logic;
import common.logic.DBConnection;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.stage.Modality;


public class Vcon implements Initializable{
    
    //Instance Variables declaration
    private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();

    private ObservableList<Warranty> warrantyData = FXCollections.observableArrayList();

    private ObservableList<BookedVehicle> bookingData = FXCollections.observableArrayList();

    private ObservableList<VehiclePartsUsed> usedPartsData = FXCollections.observableArrayList();

    private ObservableList<VehicleCustomer> customerData = FXCollections.observableArrayList();

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> template;

    @FXML
    private ComboBox<String> vehicleType;

    @FXML
    private AnchorPane vehicleTab;

    @FXML
    private Button searchButton;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TableView<Vehicle> vehicleTable;

    @FXML
    private TableColumn<Vehicle , String> model;

    @FXML
    private TableColumn<Vehicle , String> colour;

    @FXML
    private TableColumn<Vehicle , String> regNumber;

    @FXML
    private TableColumn<Vehicle , String> make;

    @FXML
    private TableColumn<Vehicle , Double> engine;

    @FXML
    private TableColumn<Vehicle , String> fuel;

    @FXML
    private TableColumn<Vehicle , String> type;

    @FXML
    private TableColumn<Vehicle , String> warranty;

    @FXML
    private TableColumn<Vehicle , String> mot;

    @FXML
    private TableColumn<Vehicle , Integer> mileage;

    @FXML
    private TableColumn<Vehicle , String> lastService;

    @FXML
    private Button displayVehiclesButton;

    @FXML
    private TableView<Warranty> warrantyTable;

    @FXML
    private TableColumn<Warranty, String> company;

    @FXML
    private TableColumn<Warranty, String> address;

    @FXML
    private TableColumn<Warranty, String> expDate;

    @FXML
    private TableView<BookedVehicle> bookingTable;

    @FXML
    private TableColumn<BookedVehicle, String> bookings;
    
    @FXML
    private TableColumn<BookedVehicle, String> bookingType;

    @FXML
    private TableView<VehiclePartsUsed> partsTable;

    @FXML
    private TableColumn<VehiclePartsUsed, String> usedParts;

    @FXML
    private TableColumn<VehiclePartsUsed, String> dateParts;

    @FXML
    private TableColumn<VehiclePartsUsed, String> totalCost;

    @FXML
    private TableView<VehicleCustomer> customerTable;

    @FXML
    private TableColumn<VehicleCustomer, String> firstName;

    @FXML
    private TableColumn<VehicleCustomer, String> lastName;

    @FXML
    private TableColumn<VehicleCustomer, String> phone;

    @FXML
    private TableColumn<VehicleCustomer, String> email;

    @FXML
    private Button logout;
    
    @FXML
    private Button help;
    
    private DecimalFormat df=new DecimalFormat(".##");

    // Initializing Vehicle Table View
    public void initialisingVehicleTableView(){
        type.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        regNumber.setCellValueFactory(new PropertyValueFactory<>("RegistrationNumber"));
        make.setCellValueFactory(new PropertyValueFactory<>("Make"));
        model.setCellValueFactory(new PropertyValueFactory<>("Model"));
        engine.setCellValueFactory(new PropertyValueFactory<>("EngineSize"));
        fuel.setCellValueFactory(new PropertyValueFactory<>("FuelType"));
        colour.setCellValueFactory(new PropertyValueFactory<>("Colour"));
        mot.setCellValueFactory(new PropertyValueFactory<>("MoTRenewalDate"));
        lastService.setCellValueFactory(new PropertyValueFactory<>("LastServiceDate"));
        warranty.setCellValueFactory(new PropertyValueFactory<>("HasWarranty"));
        mileage.setCellValueFactory(new PropertyValueFactory<>("CurrentMileage"));
        vehicleTable.setItems(null);
        vehicleTable.setItems(vehicleData);
    }

    // Initializing Warranty Table View
    public void initialisingWarrantyTableView() {
        company.setCellValueFactory(new PropertyValueFactory<>("CompanyName"));
        address.setCellValueFactory(new PropertyValueFactory<>("CompanyAddress"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("ExpirationDate"));
        warrantyTable.setItems(warrantyData);
    }

    // Initializing Booking Table View
    public void initialisingBookingTableView() {
        bookings.setCellValueFactory(new PropertyValueFactory<>("Booking"));
        bookingType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        totalCost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
        bookingTable.setItems(bookingData);
    }
     // Initializing Parts Table View
    public void initialisingPartsTableView() {
        usedParts.setCellValueFactory(new PropertyValueFactory<>("UsedParts"));
        dateParts.setCellValueFactory(new PropertyValueFactory<>("Date"));
        partsTable.setItems(usedPartsData);
    }
     // Initializing Customer Table View
    public void initialisingCustomerTableView(){
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        phone.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        email.setCellValueFactory(new PropertyValueFactory<>("Email"));
        customerTable.setItems(customerData);
    }
    
    //delete button action method which deletes the selected vehicle from the Vehicle Table View.
    @FXML
    void deleteButtonAction(ActionEvent event) throws Exception{
        Vehicle vehicle = vehicleTable.getSelectionModel().getSelectedItem();//get the slected vehicle from Vehicle Table
        if(vehicle!=null) {
            //Confirmation for Deleting the vehicle
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting Vehicle");
            alert.setContentText("Do you wish to delete the selected vehicle of "+vehicle.getRegistrationNumber()+" registration number?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                //If permission is given.
                DeleteFromDB delete = new DeleteFromDB(vehicle.getRegistrationNumber());
                delete.getVehicleDeleted();//delete the selected vehicle from the database
                delete.getPartsDeleted();//delete the selected Vehicle's used Parts from the database
                delete.getBookingDeleted();//delete the selected Vehicle's Bookings from the database
                if (vehicle.getHasWarranty().equals("Warranty")) {
                    delete.getWarrantyDeleted();//delete the selected Vehicle's Warranty from the database
                }
                vehicleData.remove(vehicle);//remove the deleted vehicle from Vehicle Table view.
                //Clean all the arraylist
                usedPartsData.clear();
                bookingData.clear();
                customerData.clear();
                warrantyData.clear();
            }
        }
        else{
            //If delete button click without selecting a vehicle from Vehicle Table then shows error Message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deleting Vehicle");
            alert.setContentText("You had to select one vehicle from the table to delete!");
            alert.showAndWait();
        }
    }
    //Search field on key released action method
    @FXML
    void searchKeyAction(KeyEvent event) throws Exception {
        //Clearing all the table views except vehicle
        warrantyData.clear();
        customerData.clear();
        usedPartsData.clear();
        bookingData.clear();
        //If vehicle type was selected then make it unselect
        if (vehicleType.getValue()!=null){            
            vehicleType.setValue(null);  
        }
         //If Template was selected then make it unselect  
        if (template.getValue()!=null){            
            template.setValue(null);
        }
        String searchText = searchField.getText().trim();
        if((!searchText.equals("")) ){
         loadToTable(searchText);//Load the Vehicle Table view according to the search field
        }      
    }
    
    //Search Button Action Method 
    @FXML
    void searchButtonAction() throws Exception {
          //Clearing all the table views except vehicle
        warrantyData.clear();
        customerData.clear();
        usedPartsData.clear();
        bookingData.clear();
        //If vehicle type was selected then make it unselect
        if (vehicleType.getValue()!=null){            
            vehicleType.setValue(null);  
        }
         //If Template was selected then make it unselect   
        if (template.getValue()!=null){            
            template.setValue(null);
        }
        String searchText = searchField.getText().trim();
        if((!searchText.equals("")) ){
            loadToTable(searchText);//Load the Vehicle Table view according to the search field
        }
        else{
            // If search field is empty shows an error Message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Vehicle");
            alert.setContentText("Empty! You had to fill up the search text field!");
            alert.showAndWait();
        }
    }
    
    //Add button action method which open a Add New Vehicle window
    @FXML
    void addButtonAction(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("vehicles/gui/addVehicle.fxml"));
        Scene scene = new Scene(loader.load());
        Stage addStage = new Stage();
        addStage.setTitle("Add New Vehicle");
        addStage.setResizable(false);
        addStage.setScene(scene);
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.showAndWait();
        refreshTable();//Load the Vehicle Table view with all the Vehicles from database
    }

    //edit button Action method open a Update Vehicle window if vehicle is selected else shows error message
    @FXML
    public void editButtonAction(ActionEvent event) throws Exception{
       Vehicle editableVehicle = vehicleTable.getSelectionModel().getSelectedItem();//get the slected vehicle from Vehicle Table
       //if vehicle is selected from the vehicle table
       if(editableVehicle!=null) {
            vehicleTable.setEditable(true);
            warrantyTable.setEditable(true);

           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("vehicles/gui/editVehicle.fxml"));
           Parent root = (Parent)fxmlLoader.load();
           EditController controller = fxmlLoader.<EditController>getController();
           controller.editingVehicle( editableVehicle,warrantyData );
           Scene scene = new Scene(root);
           Stage stage = new Stage();
           stage.setTitle("Updating Vehicle");
           stage.setResizable(false);
           stage.setScene(scene);
           stage.initModality(Modality.APPLICATION_MODAL);
           stage.showAndWait();//show and wait 
           refreshTable();// refresh the vehicle table view with all the vehicle details
       }
        else{
           //if vehicle is not selected shows error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Updating Vehicle");
            alert.setContentText("You had to select one vehicle from the table to update!");
            alert.showAndWait();
        }
    }
    
    //get all the Vehicles from database and initialize the vehicle table view  
    @FXML
    public void loadToTable(String search) {
        //Getting Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            vehicleData.clear();
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
                ResultSet rs = null;
                    rs = statement.executeQuery("SELECT * FROM 'Vehicle' WHERE Registration_Number LIKE '%" + search + "%' OR Manufacturer LIKE '%" + search + "%'");//Search vehicles by given partial registration number and Manufacturer
                while (rs.next()) {
                    vehicleData.add(new Vehicle(rs.getString("Vehicle_Type"), rs.getString("Registration_Number"), rs.getString("Model"), rs.getString("Manufacturer"), rs.getDouble("Engine_Size"), rs.getString("Fuel_Type"), rs.getString("Colour"), rs.getString("MOT_Renewal_Date"), rs.getString("Last_Service"), rs.getInt("Current_Mileage"), rs.getString("Warranty_Status"), rs.getInt("CustomerID")));
                }

            initialisingVehicleTableView();//Initializing Vehicle Table View
            //Closing Statement and Connection
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

    //display All button action method
    @FXML
    void displayVehiclesAction() throws Exception{
          //Clearing all the table views except vehicle
        warrantyData.clear();
        customerData.clear();
        usedPartsData.clear();
        bookingData.clear();
        //If vehicle type was selected then make it unselect
        if (vehicleType.getValue()!=null){            
            vehicleType.setValue(null);  
        }
        //If Template was selected then make it unselect  
        if (template.getValue()!=null){            
            template.setValue(null);
        }
        searchField.setText("");
        //Getting Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            vehicleData.clear();
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT * FROM 'Vehicle'");
        while (rs.next()) {
                 vehicleData.add(new Vehicle(rs.getString("Vehicle_Type"),rs.getString("Registration_Number"),rs.getString("Model"),rs.getString("Manufacturer"),rs.getDouble("Engine_Size"), rs.getString("Fuel_Type"), rs.getString("Colour"),rs.getString("MOT_Renewal_Date"),rs.getString("Last_Service"),rs.getInt("Current_Mileage"),rs.getString("warranty_status"), rs.getInt("CustomerID")));
            }
            initialisingVehicleTableView();//Initializing Vehicle Table View
            //Closing Statement and Connection
            statement.close();
            conn.close();
        }
        catch (SQLException ex) {
        System.err.println(ex.getMessage());
        }
        finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
         }
       }
    }
    //Template Action method which search by selected template and populate the Vehicle Table view
    @FXML
    public void templateSearchAction(ActionEvent event) throws Exception{
          //Clearing all the table views except vehicle
        warrantyData.clear();
        customerData.clear();
        usedPartsData.clear();
        bookingData.clear();
        //If vehicle type was selected then make it unselect
        if (vehicleType.getValue()!=null){            
            vehicleType.setValue(null);
        }
        //If Template is selected 
        if(template.getValue()!=null){
            searchField.setText("");
            String templateDetail = template.getValue();// get the selected value
            String[] templateInfo = templateDetail.split(" ");
            searchByTemplate(templateInfo);
         }
    }

    public void searchByTemplate(String[] template){
          //Clearing all the table views except vehicle
        warrantyData.clear();
        customerData.clear();
        usedPartsData.clear();
        bookingData.clear();
        //Getting Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            vehicleData.clear();
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            //Searching vehicle by selected template from database
            ResultSet rs = statement.executeQuery("SELECT * FROM 'Vehicle' WHERE Model  =  '"+template[1]+"' AND Manufacturer = '"+template[0]+"' AND Engine_Size  =  '"+template[2]+"' AND Fuel_Type = '"+template[3]+"'");
            while (rs.next()) {
                vehicleData.add(new Vehicle(rs.getString("Vehicle_Type"),rs.getString("Registration_Number"),rs.getString("Model"),rs.getString("Manufacturer"),rs.getDouble("Engine_Size"), rs.getString("Fuel_Type"), rs.getString("Colour"),rs.getString("MOT_Renewal_Date"),rs.getString("Last_Service"),rs.getInt("Current_Mileage"),rs.getString("Warranty_Status"), rs.getInt("CustomerID")));
            }
            initialisingVehicleTableView();//Initializing Vehicle Table View
            //Closing Statement and Connection
            statement.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        } 
    }
    
    public void vehicleTypeAction(ActionEvent event) throws Exception{
          //Clearing all the table views except vehicle
        warrantyData.clear();
        customerData.clear();
        usedPartsData.clear();
        bookingData.clear();
        //If Template was selected then make it unselect
        if (template.getValue()!=null){            
            template.setValue(null);
        }
        //If vehicle type is selected
        if(vehicleType.getValue()!=null){    
            searchField.setText("");
            getDisplayVehicleType(vehicleType.getValue());
        }
    }
    //Displaying all the vehicles from Vehicle Table of databaseand and initialize the vehicle table view.  
    @FXML
    public void getDisplayVehicleType(String vehicleType){
        //Getting Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            vehicleData.clear();
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            //Get all the vehicles details of the selected type
            ResultSet rs = statement.executeQuery("SELECT * FROM 'Vehicle' WHERE Vehicle_Type  =  '"+vehicleType+"'");
            while (rs.next()) {
                vehicleData.add(new Vehicle(rs.getString("Vehicle_Type"),rs.getString("Registration_Number"),rs.getString("Model"),rs.getString("Manufacturer"),rs.getDouble("Engine_Size"), rs.getString("Fuel_Type"), rs.getString("Colour"),rs.getString("MOT_Renewal_Date"),rs.getString("Last_Service"),rs.getInt("Current_Mileage"),rs.getString("Warranty_Status"), rs.getInt("CustomerID")));
            }
            initialisingVehicleTableView();//Initializing Vehicle Table View
            //Closing Statement and Connection
            statement.close();
            conn.close();
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    //Mouse key selection handle method
    @FXML
    void vehicleDetailAction(MouseEvent event) throws Exception {
        //If mouse clicked twice unselect
         if (event.getClickCount() == 2) {
             vehicleTable.getSelectionModel().clearSelection();//Clear the slected vehicle from Vehicle Table
             event.consume();
        } 
          //If mouse clicked once select the row of the table
        else if (event.getClickCount() == 1) {           
        Vehicle vehicle = vehicleTable.getSelectionModel().getSelectedItem();//get the slected vehicle from Vehicle Table
         if (vehicle != null) {
             //If Vehicle is selected
            if(vehicle.getHasWarranty().equals("Warranty")){
                getWarrantyDetail(vehicle); // if warranty then get warranty table view populate with warranty detail 
            }
            else{
                warrantyData.clear();//clear the warranty table view
            }
            getCustomerDetail(vehicle); //Customer table view populate with Customer detail
            getPartsDetail(vehicle);  //Parts table view populate with UsedParts 
            getBookingDetail(vehicle);  //Booking table view populate with Booking detail        
         }
      }
    }
    //keybord key selection handle method
    @FXML
    public void vehicleDetailKey(KeyEvent event) {
        Vehicle vehicle = vehicleTable.getSelectionModel().getSelectedItem();//get the slected vehicle from Vehicle Table
            if (vehicle != null) {
                //If Vehicle is selected
            if(vehicle.getHasWarranty().equals("Warranty")){
                getWarrantyDetail(vehicle);// if warranty then get warranty table view populate with warranty detail 
            }
            else{
                warrantyData.clear();
            }
            getCustomerDetail(vehicle);     //Customer table view populate with Customer detail
            getPartsDetail(vehicle);    //Parts table view populate with UsedParts 
            getBookingDetail(vehicle);    //Booking table view populate with Booking detail           
        }
    }
    //get warranty detail from database and populate warranty table view with warranty detail
    public void getWarrantyDetail( Vehicle vehicle){
        //Get Connection
            DBConnection db = DBConnection.getInstance();
            Connection conn = db.getConnection();
            Statement statement;
            try {           
                warrantyData.clear();
                String expire = "";
                statement = conn.createStatement();
                statement.setQueryTimeout(10);
                //Getting Warranty detail of the given registration number vehicle
                ResultSet rs = statement.executeQuery("SELECT * FROM Warranty WHERE Registration_Number  =  '" + vehicle.getRegistrationNumber() + "'");
                while(rs.next()){
                    expire = rs.getString("expiry_date");
                    warrantyData.add(new Warranty(rs.getString("company_name"), rs.getString("company_address"), expire));
                }
                initialisingWarrantyTableView();
                
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-M-d");
                LocalDate warrantyDate = LocalDate.parse(expire, format);
                //if warranty date expires then warning for Upadte message 
                if(checkingOutdatedWarranty(warrantyDate)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warranty Date Expired");
                    alert.setContentText("The Warranty date is expired, So you need to update it!");
                    alert.showAndWait();
                }
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
    //Checking the warranty expire date if it's out dated or not
    public boolean checkingOutdatedWarranty(LocalDate date){
        LocalDate today = LocalDate.now();
        if(date.isBefore(today)){
            return true;
        }
        return false;
    }
     //get Used Parts detail of the given vehicle from the UsedParts Table of the database and initialize the Parts Table View
    public void getPartsDetail( Vehicle vehicle){
        //Getting Connectiion
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            usedPartsData.clear();
            ArrayList partID = new ArrayList(); 
            ArrayList date = new ArrayList(); 
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            //Getting used parts of the given registration number
            ResultSet rs = statement.executeQuery("SELECT * FROM PartsUsed WHERE regNumber  =  '" + vehicle.getRegistrationNumber()+ "'");
    
            while (rs.next()) {
                partID.add( rs.getInt("PartID"));
                date.add( rs.getString("DateInstalled"));
            }
            rs.close();
            for(int i=0; i<partID.size(); i++){
                  ResultSet rs2 = statement.executeQuery("SELECT * FROM Part WHERE PartID  =  '" + partID.get(i)+ "'");
                     while (rs2.next()) {
                     usedPartsData.add(new VehiclePartsUsed((rs2.getString("name")),date.get(i).toString()));
                }
                     rs2.close();//Closing rs2 statement
            }
            initialisingPartsTableView();//Initializing Parts Table View
            //Closing Statement and Connection
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
        //geting booking details of the given vehicle from the Booking Table of the database and initialize the Booking Table View
       public void getBookingDetail( Vehicle vehicle){
           //Getting Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            bookingData.clear();
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT * FROM Booking WHERE Registration_Number  = '" + vehicle.getRegistrationNumber()+ "'");//select from the Booking table with the given registration number
            while (rs.next()) {           
                double total = getCost(rs.getInt("bookingID"));               
                double cost = (total + rs.getDouble("LabourCost"));
                bookingData.add(new BookedVehicle(rs.getString("booking_date"),rs.getString("booking_type"), df.format(cost)));   //adding the Booking table row to the bookingData arraylist            
            }
            rs.close();//close rs statement
            initialisingBookingTableView();//Initializing to the Booking Table View
            //Closing Statement and Connection.
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
    //getting total cost of parts cost and spc cost against the given bookingID
    public double getCost(int bookingID){
        double cost = 0;  
        //Getting Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {  
               statement = conn.createStatement();
               statement.setQueryTimeout(10);
               ResultSet rs = null;
               ResultSet rs1=null;
                     rs = statement.executeQuery("SELECT * FROM PartsUsed WHERE bookingID  = '" +bookingID+ "'");//Select from PartsUsed Table of database
                    while(rs.next()){
                        cost += (rs.getDouble("totalPartCost"));//getting Parts cost against the bookingID 
                    }
                    //Closing ResultSet rs
                     rs.close();
                     rs1= statement.executeQuery("SELECT * FROM Spc_Bookings WHERE bookingID  = '" +bookingID+ "'");//getting Spc_Bookings cost against the bookingID 
                    while(rs1.next()){
                        cost += rs1.getDouble("bookingCost");
                    }   
                    //Closing ResultSet rs1
                    rs1.close();
                //Closing Statement and Connection
                statement.close();
                conn.close();
                return cost;
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
        return cost;
    }
    //get Customer detail from Customer Table of database and Initializing to the Customer Table View
    public void getCustomerDetail( Vehicle vehicle){
        //Getting Connection
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            customerData.clear();
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT * FROM Customer WHERE CustomerID  = '" + vehicle.getCustomerID()+ "'");//Select the Customer with the selected CustomerID from Customer Table 
            while (rs.next()) {
                customerData.add(new VehicleCustomer(rs.getString("Firstname"),rs.getString("Lastname"),rs.getString("Phone"),rs.getString("Email")));//adding the Customer table row to the customerData arraylist
            }
            initialisingCustomerTableView();//Initializing to the Customer Table View
            //Closing Statement and Connection
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
    //loading all the Vehicle Table data from database and Initializing to the Vehicle Table View
    public void refreshTable(){
        //Getting Connection
         DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            vehicleData = FXCollections.observableArrayList();
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT * FROM 'Vehicle'");//Select Vehicle table from database
        while (rs.next()) {
                 vehicleData.add(new Vehicle(rs.getString("Vehicle_Type"),rs.getString("Registration_Number"),rs.getString("Model"),rs.getString("Manufacturer"),rs.getDouble("Engine_Size"), rs.getString("Fuel_Type"), rs.getString("Colour"),rs.getString("MOT_Renewal_Date"),rs.getString("Last_Service"),rs.getInt("Current_Mileage"),rs.getString("warranty_status"), rs.getInt("CustomerID")));//adding all the vehicle table rows to the vehicleData arraylist
            }
            //Initializing to the Vehicle Table View
            initialisingVehicleTableView();
            //Closing Statement and Connection
            statement.close();
            conn.close();
        }
        catch (SQLException ex) {
        System.err.println(ex.getMessage());
        }
        finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
         }
       }
    }
    //Logout the system and open the login page
    @FXML
    public void logoutButtonAction(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("common/gui/login.fxml"));
        Scene scene = new Scene(parent);
        Stage mainWindow;//get reference of main Stage
        mainWindow = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(scene);
    }
    //Implemented from Initilazable class
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshTable();//loaded table when the vehicle record tab open
    }
    //A method of help button action which shows all the information to the user to help for using Vehicle Record.
    @FXML
    void helpAction(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("GMSIS- Vehicle Record");
        alert.setContentText("Search - Searching by Registration Number and Manufacturer \nVehicleType - Searching Vehicle by type \nTemplate - Seaching Vehicle by template \nDisplayALL - Display existing Vehicles \nAdd New - Adding new Vehicle \nUpdate - Updating Existing Vehicle \nDelete - Deleting Existing Vehicle \nLog Out - Log out the system \nSelect vehicle to see the Warranty, Customer, Used Parts and Booking Details");
        alert.showAndWait();
    }
}
