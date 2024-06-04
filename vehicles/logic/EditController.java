package vehicles.logic;
import common.logic.DBConnection;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditController implements Initializable {
    //Instance variables
    private String reg;
    
    private Vehicle vehicle;
    
    @FXML
    private Pane addPane;

    @FXML
    private ComboBox<String> quickPick;

    @FXML
    private TextField regNumber;

    @FXML
    private TextField model;

    @FXML
    private TextField make;

    @FXML
    private TextField engine;

    @FXML
    private TextField fuel;

    @FXML
    private TextField colour;

    @FXML
    private TextField mileage;

    @FXML
    private Button submitButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button closeButton;

    @FXML
    private DatePicker last_service;

    @FXML
    private DatePicker mot_renewal;
 
    @FXML
    private ComboBox<String> customer;
    
    @FXML
    private ComboBox<String> vehicleType;

    @FXML
    private ComboBox<String> warrantyStatus;

    private String foundWarranty;

    @FXML
    private String y_warranty;

    @FXML
    private String non_warranty;

    @FXML
    private int customerID;

    @FXML
    private TextField warrantyCompany;
    
    @FXML
    private TextField address;

    @FXML
    private DatePicker expiryDate;
    
    //Clearing the all the fields of edit vehicle window
    @FXML
    public void clearButtonAction() throws Exception{
        Pane pane = FXMLLoader.load(getClass().getClassLoader().getResource("vehicles/gui/editVehicle.fxml"));
        addPane.getChildren().setAll(pane);

    }
    //Closing the edit vehicle window
    @FXML
    public void closeButtonAction(ActionEvent event) {
        Stage main = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        main.close();
    }
    //Warranty Combobox Action
    @FXML
    public void warrantyAction(){
        //If NO Warranty selected then set the compane name, address and expire date invicible
        if(warrantyStatus.getValue().equals("No Warranty")){
            warrantyCompany.setVisible(false);
            address.setVisible(false);
            expiryDate.setVisible(false);
        }
        //If Warranty selected then set the compane name, address and expire date vicible
        else {
            warrantyCompany.setVisible(true);
            address.setVisible(true);
            expiryDate.setVisible(true);
        }
    }
    //Submit Button Action Method
    @FXML
    public void submitButtonAction(ActionEvent event) throws Exception{
       try{        
           //if registration number is changed and it exists, then show invalid Registration message 
        if( !(regNumber.getText().equals(reg)) && existRegistrationNumber( regNumber.getText())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registration Number");
            alert.setContentText("One vehicle with the same Registration number already exist in the system!");
            alert.showAndWait();        
        }
        else {
             //If Engine Size and Current Mileage are negative number then show invalid message 
            if((0 > Double.parseDouble(engine.getText().trim())) && (0>(Integer.parseInt(mileage.getText().trim())))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("INVALID ENGINE SIZE AND CURRENT MILEAGE");
                alert.setContentText("Engine Size AND Current Mileage can't be negative number");
                alert.showAndWait();
            }
            //If Engine Size is negative number then show invalid message 
            else if(0 > Double.parseDouble(engine.getText().trim())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("INVALID ENGINE SIZE");
                    alert.setContentText("Engine Size can't be negative number");
                    alert.showAndWait();
                 }
            //If Current Mileage is negative number then show invalid message 
            else if((0>(Integer.parseInt(mileage.getText().trim())))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("INVLID CURRENT MILEAGE");
                    alert.setContentText("Current Mileage can't be negative number");
                    alert.showAndWait();  
                }
            else {
                String detail = customer.getValue();
                String [] IDs = detail.split(" ");
                customerID = Integer.parseInt(IDs[0]);
                double engineSize = Double.parseDouble(engine.getText().trim());
                int currentMileage = Integer.parseInt(mileage.getText().trim());
                 String type = (String) vehicleType.getValue();
                 String mot = mot_renewal.getValue().toString().trim();
                 String service = last_service.getValue().toString().trim();
                if (warrantyStatus.getValue().equals("No Warranty")) {
                    vehicle = new Vehicle(vehicleType.getValue(), regNumber.getText(), model.getText(), make.getText(), engineSize, fuel.getText(), colour.getText(), mot, service, currentMileage, "No Warranty", customerID);
                    AddToDB db = new AddToDB(vehicle,reg);
                    db.getVehicleUpdated();//Update vehicle to database
                    DeleteFromDB deleteWarranty = new DeleteFromDB(vehicle.getRegistrationNumber());
                    deleteWarranty.getWarrantyDeleted();//delete warranty from database
                } else {
                    String ex_date = expiryDate.getValue().toString();
                    Warranty warrantyDetail = new Warranty(warrantyCompany.getText(), address.getText(), ex_date);
                    vehicle = new Vehicle(vehicleType.getValue(), regNumber.getText(), model.getText(), make.getText(), engineSize, fuel.getText(), colour.getText(), mot, service, currentMileage, "Warranty", customerID);
                    AddToDB db = new AddToDB(vehicle, warrantyDetail, reg);
                    db.getVehicleUpdated();//Update vehicle to database
                    if(foundWarranty.equals("Warranty")){
                        db.getWarrantyUpdated();//Update the warranty from database
                    }
                    else{
                        db.getWarrantyAdded();//Added warranty to the database
                    }
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Updating Vehicle");
                alert.setContentText("The vehicle of "+reg+" registration number has been Updated");
                alert.showAndWait();
                Stage main = (Stage) ((Node) event.getSource()).getScene().getWindow();
                main.close();
               }
            }
        }catch(NumberFormatException e){
               try{
          
                   Double.parseDouble(engine.getText().trim());
               
            }
           //If Engine Size is not number then show invalid message 
     catch(NumberFormatException eng){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Engine Size");
            alert.setContentText("Engine Size should be any positive number");
            alert.showAndWait();
      }
          try{
     
              Integer.parseInt(mileage.getText().trim());
      
            }
        //If Current Mileage is not number then show invalid message
     catch(NumberFormatException mile){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Current Mileage");
            alert.setContentText("Current Mileage should be non-decimal positive number");
            alert.showAndWait();
      }

        }
    }
        /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //Set disable the submit button untill all the fields fill up and selected 
          submitButton.setDisable(true);
          if(warrantyStatus.getValue()==null){  
                submitButton.disableProperty().unbind();
                submitButton.disableProperty().bind(regNumber.textProperty().isEmpty().or(fuel.textProperty().isEmpty()).or(model.textProperty().isEmpty()).or(colour.textProperty().isEmpty()).or(make.textProperty().isEmpty()).or(engine.textProperty().isEmpty()).or(customer.valueProperty().isNull()).or(vehicleType.valueProperty().isNull()).or(mot_renewal.valueProperty().isNull()).or(last_service.valueProperty().isNull()).or(mileage.textProperty().isEmpty()).or(warrantyStatus.valueProperty().isNull()));
            }
        warrantyStatus.getSelectionModel().selectedIndexProperty().addListener(
                (ob,oldVal,newVal) -> {
                    if(warrantyStatus.getValue().equals("No Warranty")){                       
                    submitButton.disableProperty().bind(regNumber.textProperty().isEmpty().or(fuel.textProperty().isEmpty()).or(model.textProperty().isEmpty()).or(colour.textProperty().isEmpty()).or(make.textProperty().isEmpty()).or(engine.textProperty().isEmpty()).or(customer.valueProperty().isNull()).or(vehicleType.valueProperty().isNull()).or(mot_renewal.valueProperty().isNull()).or(last_service.valueProperty().isNull()).or(mileage.textProperty().isEmpty()).or(warrantyStatus.valueProperty().isNull()));
                }
                else{
                     submitButton.disableProperty().bind(regNumber.textProperty().isEmpty().or(fuel.textProperty().isEmpty()).or(model.textProperty().isEmpty()).or(colour.textProperty().isEmpty()).or(make.textProperty().isEmpty()).or(engine.textProperty().isEmpty()).or(customer.valueProperty().isNull()).or(vehicleType.valueProperty().isNull()).or(mot_renewal.valueProperty().isNull()).or(last_service.valueProperty().isNull()).or(mileage.textProperty().isEmpty()).or(warrantyStatus.valueProperty().isNull()).or(warrantyCompany.textProperty().isEmpty()).or(address.textProperty().isEmpty()).or(expiryDate.valueProperty().isNull()));
                }       
             
                }
        );


        getCustomer();//Loaded the customer combobox with all the customers from database 
        
          //Set all the future date disable
        last_service.setDayCellFactory(picker -> new DateCell() {
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            setDisable(date.isAfter(LocalDate.now()) );

        }
        });
          //Set all the past date disable
        mot_renewal.setDayCellFactory(picker -> new DateCell() {
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            setDisable(date.isBefore(LocalDate.now()) );

        }
        });
           //Set all the past date disable
        expiryDate.setDayCellFactory(picker -> new DateCell() {
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            setDisable(date.isBefore(LocalDate.now()) );
            
        }
        });     
    }
    //getting customer from database and filling the customer combobox with them
      public void getCustomer() {
        mot_renewal.setEditable(false);
        last_service.setEditable(false);
        expiryDate.setEditable(false);
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT * FROM 'Customer'");
            while (rs.next()) {
                customer.getItems().add(Integer.toString(rs.getInt("CustomerID"))+" "+rs.getString("Firstname")+" "+rs.getString("Lastname")+", "+rs.getString("Phone"));
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
    //filling all the fields by the selected vehicle details
    public void editingVehicle(Vehicle vehicle,ObservableList<Warranty> warrantyData){

        reg = vehicle.getRegistrationNumber();
        customer.getSelectionModel().select(getCustomerSelected(vehicle.getCustomerID()));
        foundWarranty = vehicle.getHasWarranty();
        customerID = vehicle.getCustomerID();
        regNumber.setText(vehicle.getRegistrationNumber());
        make.setText(vehicle.getMake());
        model.setText(vehicle.getModel());
        String currentMileage = Integer.toString(vehicle.getCurrentMileage());
        mileage.setText(currentMileage);
        String size = Double.toString(vehicle.getEngineSize());
        engine.setText(size);
        fuel.setText(vehicle.getFuelType());
        colour.setText(vehicle.getColour());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyy-M-d");
        LocalDate mot = LocalDate.parse(vehicle.getMoTRenewalDate(), format);
        LocalDate last = LocalDate.parse(vehicle.getLastServiceDate(), format);
        last_service.setValue(last);
        last_service.setEditable(false);
        mot_renewal.setValue(mot);
        mot_renewal.setEditable(false);
        vehicleType.getSelectionModel().select(vehicle.getVehicleType());
        warrantyStatus.getSelectionModel().select(vehicle.getHasWarranty());
        if(vehicle.getHasWarranty().equals("No Warranty")){
            warrantyCompany.setVisible(false);
            address.setVisible(false);
            expiryDate.setVisible(false);
            expiryDate.setEditable(false);
        }
        else{
            Warranty warranty = warrantyData.get(0);          
            warrantyCompany.setVisible(true);
            address.setVisible(true);
            expiryDate.setVisible(true);
            LocalDate exp = LocalDate.parse(warranty.getExpirationDate(), format);
            expiryDate.setValue(exp);
            warrantyCompany.setText(warranty.getCompanyName());
            address.setText(warranty.getCompanyAddress());
        }

    }
    //get Customer detail from database 
     public String getCustomerSelected(int id) {
        String customerDetail ="";
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT * FROM 'Customer' where CustomerID = '"+id+"'");
                customerDetail =(Integer.toString(rs.getInt("CustomerID"))+" "+rs.getString("Firstname")+" "+rs.getString("Lastname")+", "+rs.getString("Phone"));
            statement.close();
            conn.close();
            return customerDetail;
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
           return customerDetail;
    }
     //get quickPickAction button action method which select the template
    @FXML
    public void quickPickAction() {
        String pick = (String) quickPick.getValue();
        String[] quick = pick.split(" ");
        make.setText(quick[0]);
        model.setText(quick[1]);
        engine.setText(quick[2]);
        fuel.setText(quick[3]);
    }
     //checking existing registration number in the database.If it exists then return true and else false
    public boolean existRegistrationNumber(String reg){
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT *From 'Vehicle'");
            while(rs.next()){
                if(rs.getString("Registration_Number").equals(reg)){
                    rs.close();
                    conn.close();
                    return true;
                } 
              }
                 rs.close();
                 conn.close();
                 return false;
                
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
        return false;
    }
}
