/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import common.logic.DBConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;

/**
 * FXML Controller class
 *
 * @author Wajid
 */
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

// main parts controller, used for mainly loading data to tables and on action events
public class PartsController implements Initializable {

    DBConnection dbCon = DBConnection.getInstance();
    Connection c = dbCon.getConnection();

    @FXML
    private TableView<parts> partsTable1;

    @FXML
    private TableColumn IdCol;

    @FXML
    private TableColumn NameCol;

    @FXML
    private TableColumn DescCol;

    @FXML
    private TableColumn StockCol;

    @FXML
    private TableColumn CostCol;

    @FXML
    private TableColumn partWithdrawn;

    @FXML
    private Button addBtn;

    @FXML
    private Button deletePartBtn, searchBtn;

    @FXML
    private TextField searchField;

    private int PartID;

    private int PartUID;

    @FXML
    private TableView<customer> tableReg2;

    @FXML
    private TableColumn regID;

    @FXML
    private TableColumn firstname;

    @FXML
    private TableColumn surname;

    @FXML
    private TextField searchCustomerField;

    @FXML
    private Button searchCustomerBtn;

    @FXML
    private TextField regField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField colourField;

    @FXML
    private TextField makeField;

    @FXML
    private TextField engineField;

    @FXML
    private TextField fuelField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField warrantyField;

    @FXML
    private TextField motField;

    @FXML
    private TextField serviceField;

    @FXML
    private TextField mileageField;

    @FXML
    private TextField fNameField;

    @FXML
    private TextField sNameField;

    @FXML
    private TextField addressField;
    @FXML
    private TextField customerIdField;

    @FXML
    private TextField pcField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TableView<booking> bookingTable;

    @FXML
    private TableColumn BookingTypeField;

    @FXML
    private TableColumn FutureDateField;
    @FXML
    private TableColumn bregField;

    @FXML
    private TableView<booking> pastBookingTable;

    @FXML
    private TableColumn bookingTypePast;

    @FXML
    private TableColumn PastDate;

    @FXML
    private TableView<partsUsed> partUsedTable;

    @FXML
    private TableColumn partUsedIDCol;

    @FXML
    private TableColumn pnameUsedCol;

    @FXML
    private TableColumn dateInstalledCol;

    @FXML
    private TableColumn warrantyExpCol;

    @FXML
    private TableColumn pusedCostCol;

    @FXML
    private TableColumn quanCol;

    @FXML
    private TableColumn bookingIDPartUsed;

    @FXML
    private Button deletePartUsedBtn;

    @FXML
    private Button addPartUsedBtn;

    @FXML
    private TextField descClickField;

    customer c1;

    parts p1;

    partsUsed p2;

    @FXML
    private Button addStockBtn;

    @FXML
    private TableColumn stockIncreaseCol;

    @FXML
    private Button partsLogOutBtn;

    @FXML
    private Button refreshBtn;

    // to refresh changes made to customer details from other modules
    @FXML
    public void refresh(ActionEvent event) {
        loadData2();
    }

    // logout button method to logout
    @FXML
    public void logoutPartsButtonAction(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("common/gui/login.fxml"));
        Scene scene = new Scene(parent);
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.setScene(scene);
    }

    // increase stock on action to load fxml to increase stock
    @FXML
    void handleAddStockButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("parts/gui/stockIncrease.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            loadData();

        } catch (Exception e) {
            // do nothing 
        }
    }

    // add part button action to load fxml to add parts to system
    @FXML
    void handleButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("parts/gui/addPartForm.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            loadData();
        } catch (Exception e) {
            // do nothing
        }
    }

    @FXML
    private Button editBtn;

    // button action to load edit fxml to parts in stock 
    @FXML
    void handleEditButtonAction(ActionEvent event) {
        if (partsTable1.getSelectionModel().getSelectedItem() != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("parts/gui/editPartStock.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                parts part = partsTable1.getSelectionModel().getSelectedItem();
                EditPartStockController cont = fxmlLoader.getController();

                cont.getEditInfo(part.getID(), part.getName(), part.getDesc(), part.getStock(), part.getCost());

                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                loadData();
                updateDataUsed();
            } catch (Exception e) {
                // do nothing
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("No Part Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part to edit!");
            Optional<ButtonType> action = alert.showAndWait();
        }

    }

    // on action to delete a part from the system 
    @FXML
    void handleDeleteButtonAction(ActionEvent event) {

        if (partsTable1.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to remove this part from the system?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {

                try {
                    String sql = "DELETE FROM Part WHERE PartID = '" + PartID + "'";
                    PreparedStatement pst = c.prepareStatement(sql);
                    pst.execute();
                    loadData();
                    updateDataUsed();
                    int i = index.get();
                    if (i > -1) {
                        data.remove(i);
                        partsTable1.getSelectionModel().clearSelection();

                        pst.close();

                    }
                } catch (Exception e) {
                    // do nothing 
                } finally {
                    if (c != null) {
                        try {
                            c.close();
                            c = dbCon.getConnection();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("No Part Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part to delete!");
            Optional<ButtonType> action = alert.showAndWait();
        }
    }

    // add part used button to load up fxml, allow to add part to booking/vehicle
    @FXML
    void handleAddPartUsedButtonAction(ActionEvent event) {
        try {
            if (tableReg2.getSelectionModel().getSelectedItem() != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("parts/gui/partUsedAddForm.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                PartUsedAddFormController controller = fxmlLoader.getController();
                controller.getpart(c1);
                controller.showBooking();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
                loadData();
                updateDataUsed();

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("No Customer Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please select a customer to add a part for a repair!");
                Optional<ButtonType> action = alert.showAndWait();
            }

        } catch (Exception e) {
            // do nothing
        }
    }

    private final ObservableList<parts> data = FXCollections.observableArrayList();
    private final ObservableList<parts> datan = FXCollections.observableArrayList();

    private final ObservableList<customer> data3 = FXCollections.observableArrayList();
    private final ObservableList<customer> data3n = FXCollections.observableArrayList();

    private final ObservableList<booking> data4 = FXCollections.observableArrayList();

    private final ObservableList<partsUsed> data5 = FXCollections.observableArrayList();

    private final ObservableList<booking> data7 = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    private IntegerProperty index = new SimpleIntegerProperty();

    // initialize to load data staright away, when module clicked on and set columns 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        index.set(-1);

        IdCol.setCellValueFactory(new PropertyValueFactory<parts, Integer>("ID"));
        NameCol.setCellValueFactory(new PropertyValueFactory<parts, String>("Name"));

        NameCol.setCellFactory(TextFieldTableCell.<parts>forTableColumn());
        NameCol.setOnEditCommit(new EventHandler<CellEditEvent<parts, String>>() {
            @Override
            public void handle(CellEditEvent<parts, String> tb) {
                (tb.getTableView().getItems().get(
                        tb.getTablePosition().getRow())).setName((tb.getNewValue()));
            }
        });

        DescCol.setCellValueFactory(new PropertyValueFactory<parts, String>("Desc"));
        StockCol.setCellValueFactory(new PropertyValueFactory<parts, Integer>("Stock"));
        CostCol.setCellValueFactory(new PropertyValueFactory<parts, Integer>("Cost"));
        partWithdrawn.setCellValueFactory(new PropertyValueFactory<parts, Integer>("PartsWithdrawn"));
        stockIncreaseCol.setCellValueFactory(new PropertyValueFactory<parts, Integer>("PartsAdded"));

        regID.setCellValueFactory(new PropertyValueFactory<customer, String>("Reg"));
        firstname.setCellValueFactory(new PropertyValueFactory<customer, Integer>("FName"));
        surname.setCellValueFactory(new PropertyValueFactory<customer, Integer>("SName"));

        // bregField.setCellValueFactory(new PropertyValueFactory<booking, String>("RegBooking"));
        BookingTypeField.setCellValueFactory(new PropertyValueFactory<booking, String>("BType"));
        FutureDateField.setCellValueFactory(new PropertyValueFactory<booking, String>("FBDate"));

        //bregField.setCellValueFactory(new PropertyValueFactory<booking, String>("RegBooking"));
        bookingTypePast.setCellValueFactory(new PropertyValueFactory<booking, String>("BType"));
        PastDate.setCellValueFactory(new PropertyValueFactory<booking, String>("FBDate"));

        // partUsedIDCol.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("PuID"));
        pnameUsedCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("PuName"));
        dateInstalledCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("DateInstalled"));
        warrantyExpCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("WExpiry"));
        pusedCostCol.setCellValueFactory(new PropertyValueFactory<partsUsed, Double>("TotalCost"));
        quanCol.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("Quan"));
        bookingIDPartUsed.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("BIDused"));

        loadData();
        loadData2();

        partsTable1.setItems(data);

        partsTable1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PartID = newValue.getID();
            }
        });

        partUsedTable.setItems(data5);

        partUsedTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PartUID = newValue.getPuID();
            }
        });

    }

    // method to load data to parts table 
    public void loadData() {
        data.clear();
        try {
            String sql = "SELECT * FROM Part;";
            PreparedStatement stmt = c.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data.add(new parts(
                        rs.getInt("PartID"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("stock_levels"),
                        rs.getDouble("part_cost"),
                        rs.getInt("partsWithdrawn"),
                        rs.getInt("partsAdded")
                ));
            }
            partsTable1.setItems(data);
            stmt.close();

        } catch (Exception e) {
            //   do nothing 

        } finally {
            if (c != null) {
                try {
                    c.close();
                    c = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

    // search method for part by name of a part
    public void searchPart() {

        datan.removeAll(datan);

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).getName().toLowerCase().contains(searchField.getText().toLowerCase())) {

                datan.add(data.get(i));

            }
        }

        partsTable1.setItems(datan);
    }

    // load data to table view method for customer details
    public void loadData2() {
        data3.clear();
        try {
            String sql = "SELECT Vehicle.Registration_Number, Customer.CustomerID, Firstname, Lastname, Address, Postcode, Phone, Manufacturer, Email, Model, Colour, Fuel_Type, Vehicle_Type, Warranty_Status, MOT_Renewal_Date, Engine_Size, Last_Service, Current_Mileage"
                    + " FROM Vehicle INNER JOIN Customer ON Vehicle.CustomerID = Customer.CustomerID";
            PreparedStatement stmt = c.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data3.add(new customer(
                        rs.getString("Registration_Number"),
                        rs.getInt("CustomerID"),
                        rs.getString("Firstname"),
                        rs.getString("Lastname"),
                        rs.getString("Address"),
                        rs.getString("Postcode"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getString("Manufacturer"),
                        rs.getString("Model"),
                        rs.getString("Colour"),
                        rs.getString("Fuel_Type"),
                        rs.getString("Vehicle_Type"),
                        rs.getString("Warranty_Status"),
                        rs.getString("MOT_Renewal_Date"),
                        rs.getDouble("Engine_Size"),
                        rs.getString("Last_Service"),
                        rs.getInt("Current_Mileage")));

            }
            tableReg2.setItems(data3);
            stmt.close();

        } catch (Exception e) {

            // do nothing
        } finally {
            if (c != null) {
                try {
                    c.close();
                    c = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

    // search method for customer details to see parts installed, by reg, or name
    public void searchCustomer() {

        data3n.removeAll(data3n);

        for (int i = 0; i < data3.size(); i++) {
            if (data3.get(i).getReg().toLowerCase().contains(searchCustomerField.getText().toLowerCase()) || data3.get(i).getFName().toLowerCase().contains(searchCustomerField.getText().toLowerCase())
                    || data3.get(i).getSName().toLowerCase().contains(searchCustomerField.getText().toLowerCase())) {

                data3n.add(data3.get(i));
            }
        }

        tableReg2.setItems(data3n);
    }

    // gets customer object c1 for next method
    public customer getCurrentCustomer() {
        return c1;

    }

    // mouse click event matching customer object on click with details for other tables like part used
    public void handleMouseClick(MouseEvent event) {
        c1 = tableReg2.getSelectionModel().getSelectedItem();
        String y = String.valueOf(c1.getCID());
        String f = String.valueOf(c1.getCusPhone());
        String es = String.valueOf(c1.getESize());
        String mil = String.valueOf(c1.getCMile());
        regField.setText(c1.getReg());
        customerIdField.setText(y);
        makeField.setText(c1.getMake());
        modelField.setText(c1.getModel());
        colourField.setText(c1.getColour());
        engineField.setText(es);
        fuelField.setText(c1.getFuel());
        typeField.setText(c1.getVType());
        warrantyField.setText(c1.getWStatus());
        motField.setText(c1.getMRen());
        serviceField.setText(c1.getLServ());
        mileageField.setText(mil);

        // customer info
        fNameField.setText(c1.getFName());
        sNameField.setText(c1.getSName());
        addressField.setText(c1.getAddress());
        pcField.setText(c1.getPostcode());
        phoneField.setText(f);
        emailField.setText(c1.getEmail());

        data4.clear();
        try {
            String sql = "SELECT Registration_Number, booking_type, booking_date"
                    + " FROM Booking WHERE Registration_Number = '" + c1.getReg() + "' AND booking_date > date('now')";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data4.add(new booking(
                        rs.getString("Registration_Number"),
                        rs.getString("booking_type"),
                        rs.getString("booking_date")));
            }

            bookingTable.setItems(data4);
            stmt.close();

        } catch (Exception e) {
            //  do nothing
        } finally {
            if (c != null) {
                try {
                    c.close();
                    c = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        data7.clear();
        try {
            String sql = "SELECT Registration_Number, booking_type, booking_date"
                    + " FROM Booking WHERE Registration_Number = '" + c1.getReg() + "' AND booking_date < date('now')";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data7.add(new booking(
                        rs.getString("Registration_Number"),
                        rs.getString("booking_type"),
                        rs.getString("booking_date")));
            }

            pastBookingTable.setItems(data7);
            stmt.close();

        } catch (Exception e) {

            // do nothing
        } finally {
            if (c != null) {
                try {
                    c.close();
                    c = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        data5.clear();
        try {
            String sql = "SELECT PartUsedID, name, DateInstalled, WarrantyExpiry, totalPartCost, quantityParts, bookingID"
                    + " FROM PartsUsed, Part WHERE '" + c1.getReg() + "' = PartsUsed.regNumber AND PartsUsed.PartID = Part.PartID";
            PreparedStatement stmt = c.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data5.add(new partsUsed(
                        rs.getInt("PartUsedID"),
                        rs.getString("name"),
                        rs.getString("DateInstalled"),
                        rs.getString("WarrantyExpiry"),
                        rs.getInt("quantityParts"),
                        rs.getDouble("totalPartCost"),
                        rs.getInt("bookingID")
                ));

            }
            partUsedTable.setItems(data5);
            stmt.close();

        } catch (Exception e) {

            // do nothing 
        } finally {
            if (c != null) {
                try {
                    c.close();
                    c = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

    // delete action when deleting parts installed to a vehicle and booking
    @FXML
    void handleDeletePartUsedButtonAction(ActionEvent event) {

        if (partUsedTable.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirm Delete");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to remove this part from a repair?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {

                try {
                    String sql = "DELETE FROM PartsUsed WHERE PartUsedID = '" + PartUID + "'";
                    PreparedStatement pst = c.prepareStatement(sql);
                    pst.execute();
                    updateDataUsed();

                    int i = index.get();
                    if (i > -1) {
                        data5.remove(i);
                        partUsedTable.getSelectionModel().clearSelection();

                        pst.close();

                    }
                } catch (Exception e) {
                    // do nothing 
                } finally {
                    if (c != null) {
                        try {
                            c.close();
                            c = dbCon.getConnection();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("No Part Installed Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a part installed to delete!");
            Optional<ButtonType> action = alert.showAndWait();
        }
    }

    // clicked part used info 
    public void handleClickPartsUsedInfo(MouseEvent event) {

        p2 = partUsedTable.getSelectionModel().getSelectedItem();
        descClickField.setText(p1.getDesc());

    }

    // to update details in every table related to a customer and vehicles
    public void updateDataUsed() {
        c1 = tableReg2.getSelectionModel().getSelectedItem();
        String y = String.valueOf(c1.getCID());
        String f = String.valueOf(c1.getCusPhone());
        String es = String.valueOf(c1.getESize());
        String mil = String.valueOf(c1.getCMile());
        regField.setText(c1.getReg());
        customerIdField.setText(y);
        makeField.setText(c1.getMake());
        modelField.setText(c1.getModel());
        colourField.setText(c1.getColour());
        engineField.setText(es);
        fuelField.setText(c1.getFuel());
        typeField.setText(c1.getVType());
        warrantyField.setText(c1.getWStatus());
        motField.setText(c1.getMRen());
        serviceField.setText(c1.getLServ());
        mileageField.setText(mil);

        // customer info
        fNameField.setText(c1.getFName());
        sNameField.setText(c1.getSName());
        addressField.setText(c1.getAddress());
        pcField.setText(c1.getPostcode());
        phoneField.setText(f);
        emailField.setText(c1.getEmail());

        data4.clear();
        try {
            String sql = "SELECT Registration_Number, booking_type, booking_date"
                    + " FROM Booking WHERE Registration_Number = '" + c1.getReg() + "' AND booking_date > date('now')";
            PreparedStatement stmt = c.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data4.add(new booking(
                        rs.getString("Registration_Number"),
                        rs.getString("booking_type"),
                        rs.getString("booking_date")));
            }

            bookingTable.setItems(data4);
            stmt.close();

        } catch (Exception e) {
            // do nothing
        } finally {
            if (c != null) {
                try {
                    c.close();
                    c = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        data7.clear();
        try {
            String sql = "SELECT Registration_Number, booking_type, booking_date"
                    + " FROM Booking WHERE Registration_Number = '" + c1.getReg() + "' AND booking_date < date('now')";
            PreparedStatement stmt = c.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data7.add(new booking(
                        rs.getString("Registration_Number"),
                        rs.getString("booking_type"),
                        rs.getString("booking_date")));
            }
            pastBookingTable.setItems(data7);
            stmt.close();

        } catch (Exception e) {

            // do nothing 
        } finally {
            if (c != null) {
                try {
                    c.close();
                    c = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        data5.clear();
        try {
            String sql = "SELECT PartUsedID, name, DateInstalled, WarrantyExpiry, totalPartCost, quantityParts, bookingID"
                    + " FROM PartsUsed, Part WHERE '" + c1.getReg() + "' = PartsUsed.regNumber AND PartsUsed.PartID = Part.PartID";
            PreparedStatement stmt = c.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                data5.add(new partsUsed(
                        rs.getInt("PartUsedID"),
                        rs.getString("name"),
                        rs.getString("DateInstalled"),
                        rs.getString("WarrantyExpiry"),
                        rs.getInt("quantityParts"),
                        rs.getDouble("totalPartCost"),
                        rs.getInt("bookingID")
                ));

            }
            partUsedTable.setItems(data5);
            stmt.close();

        } catch (Exception e) {

            // do nothing
        } finally {
            if (c != null) {
                try {
                    c.close();
                    c = dbCon.getConnection();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }

}
