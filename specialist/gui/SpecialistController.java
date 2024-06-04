/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist.gui;

import common.logic.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import javafx.event.ActionEvent;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.Initializable;
import specialist.logic.SpcController;
import specialist.logic.SpcList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import parts.logic.partsUsed;
import specialist.logic.SpcBookings;

import vehicles.logic.Vehicle;

/**
 *
 * @author Yousuf
 */
public class SpecialistController implements Initializable {

    SpcController control = new SpcController();

    private String retVehicleId;

    private Integer retSpcId;

    private Integer retPartId;

    @FXML
    private Button viewVehiclesAtSpc;
    @FXML
    private Button viewBookingButton;
    @FXML
    private TextField Spc_Search;

    @FXML
    private TableView<SpcList> SPC_DisplayTable;

    @FXML
    private TableColumn<SpcList, String> Spc_Search_Name;

    @FXML
    private TableColumn<SpcList, Integer> Spc_Search_Id;

    @FXML
    private TableColumn<SpcList, String> Spc_Search_Address;

    @FXML
    private TableColumn<SpcList, String> Spc_Search_Phone;

    @FXML
    private TableColumn<SpcList, String> Spc_Search_Email;

    @FXML
    private TableColumn<SpcList, Integer> Spc_Search_PartNumber;

    @FXML
    private TableColumn<SpcList, Integer> Spc_Search_VehicleNumber;

    @FXML
    private TableColumn<partsUsed, Integer> part_search_usedPartID;
    @FXML
    private Button refresh_vehicles_add_table;
    @FXML
    private TableColumn<SpcBookings, Integer> View_SpcBookingId;

    @FXML
    private TableColumn<SpcBookings, String> viewDeliveryDate;

    @FXML
    private TableColumn<SpcBookings, Double> viewSpcCost;

    @FXML
    private TableColumn<SpcBookings, String> viewReturnDate;

    @FXML
    private TableColumn<SpcBookings, String> View_Registration;

    @FXML
    private TableColumn<SpcBookings, Integer> View_PartsUsed;

    @FXML
    private TextField Spc_Search_Button;

    @FXML
    private Button Spc_Search_DeletePart;

    @FXML
    private Button Spc_Search_EditPart;

    @FXML
    private DatePicker deliveryDate;

    @FXML
    private DatePicker returnDate;

    @FXML
    private Button AddButton;

    @FXML
    private TableView<partsUsed> Search_PartsTable;

    @FXML
    private ChoiceBox<String> select_parameter_search_vehicles;

    @FXML
    private TableColumn<partsUsed, Integer> Parts_Search_ID;

    @FXML
    private TableColumn<partsUsed, String> Parts_Search_Name;

    @FXML
    private TableColumn<partsUsed, Integer> Parts_Search_StockNumber;

    @FXML
    private TableColumn<partsUsed, Integer> Parts_Search_Description;

    // @FXML
    //private TableColumn<partsUsed, java.math.BigDecimal> Parts_Search_Price;
    @FXML
    private TextField search_part_table_add;

    @FXML
    private TableView<SpcBookings> SpcBookingsTable;

    @FXML
    private TableView<Vehicle> Search_VehiclesTable;

    @FXML
    private TableColumn<Vehicle, String> Vehicle_Search_Registration;

    @FXML
    private TableColumn<Vehicle, String> Vehicle_Search_Manafacturer;

    @FXML
    private TableColumn<Vehicle, String> Vehicle_Search_Make;

    @FXML
    private TableColumn<Vehicle, String> Vehicle_Search_Type;

    @FXML
    private TableColumn<Vehicle, Integer> Vehicle_Search_Mileage;

    @FXML
    private TableColumn<Vehicle, String> Vehicle_Search_Warranty;

    @FXML
    private Button Spc_Search_AddPart;

    @FXML
    private MenuItem Search_Spc_Parameter_Registration;

    @FXML
    private MenuItem Search_Spc_Parameter_Surname;

    @FXML
    private MenuItem Search_Spc_Parameter_FirstName;

    @FXML
    private Button SPC_DisplayTable_Refresh;

    @FXML
    private RadioButton searchRegistration;

    @FXML
    private RadioButton searchManafacturer;

    @FXML
    private TextField search_vehicle_table_add;

    @FXML
    private ToggleGroup groupVehicle;
    @FXML
    private ToggleGroup groupPart;

    @FXML
    private RadioButton searchRegistrationParts;

    @FXML
    private RadioButton searchFirstNameParts;

    @FXML
    private RadioButton searchSurnameParts;

    @FXML
    private TableColumn<?, ?> View_FirstName;

    @FXML
    private TableColumn<?, ?> View_Surname;

    @FXML
    private Button view_outstanding_parts_details;

    @FXML
    private Button view_outstanding_vehicle_details;

    @FXML
    private TableView<Vehicle> vehicleExtraDetailTable;

    @FXML
    private TableView<partsUsed> partsUsedExtraDetailTable;

    @FXML
    private Button SpcSearchRefresh;

    @FXML
    private TextArea bookingExtraInfo;

    @FXML
    private Button viewPartsAtSpcButton;

    @FXML
    private TableView extraPartDetailsTable;

    @FXML
    private TextField bookingCostField;
    private String spcBookingRetSpcId;

    @FXML
    private Button spcLogOut;
    
        @FXML
    public void logoutSPCButtonAction(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("common/gui/login.fxml"));
        Scene scene = new Scene(parent);
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        searchRegistration.setToggleGroup(groupVehicle);
        searchRegistration.setSelected(true);

        searchManafacturer.setToggleGroup(groupVehicle);

        searchRegistrationParts.setToggleGroup(groupPart);
        searchRegistrationParts.setSelected(true);
        searchFirstNameParts.setToggleGroup(groupPart);
        searchSurnameParts.setToggleGroup(groupPart);

        try {
            deliveryDate.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(date.isBefore(LocalDate.now()));

                }
            });
            deliveryDate.setEditable(false);

            returnDate.setDayCellFactory(picker -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(date.isBefore(LocalDate.now()));
                    //|| date.isBefore(deliveryDate.getValue())
                }
            });
            returnDate.setEditable(false);
        } catch (NullPointerException e) {

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    void spcDisplayTableRefreshButton(ActionEvent event) {
        ObservableList<SpcList> obList = control.getSpcData(Spc_Search.getText().toLowerCase());

        Spc_Search_Name.setCellValueFactory(new PropertyValueFactory<>("spcName"));
        Spc_Search_Address.setCellValueFactory(new PropertyValueFactory<>("spcAddress"));
        Spc_Search_Phone.setCellValueFactory(new PropertyValueFactory<>("spcPhone"));
        Spc_Search_Email.setCellValueFactory(new PropertyValueFactory<>("spcEmail"));
        Spc_Search_PartNumber.setCellValueFactory(new PropertyValueFactory<>("noOutstandingParts"));
        Spc_Search_VehicleNumber.setCellValueFactory(new PropertyValueFactory<>("noOutstandingVehicles"));
        Spc_Search_Id.setCellValueFactory(new PropertyValueFactory<>("spcId"));
        SPC_DisplayTable.setItems(obList);

    }

    @FXML
    void refreshPartAddTable(ActionEvent event) {

        ObservableList<partsUsed> partTableObList;

        if (searchFirstNameParts.isSelected()) {
            partTableObList = control.getPartData(search_part_table_add.getText(), "first");
        } else if (searchSurnameParts.isSelected()) {
            partTableObList = control.getPartData(search_part_table_add.getText(), "last");
        } else {
            partTableObList = control.getPartData(search_part_table_add.getText(), "reg");
        }

        part_search_usedPartID.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("partUsedID"));
        Parts_Search_ID.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("fPartID"));

        Parts_Search_Name.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("name"));

        Parts_Search_Description.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("fDescription"));

        Search_PartsTable.setItems(partTableObList);

    }

    @FXML
    void vehicleDetailButton(ActionEvent event) {

        if (SPC_DisplayTable.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Click on a Desired Spc");
            return;
        }
        ObservableList<Vehicle> obList = control.getVehicleDetails(SPC_DisplayTable.getSelectionModel().getSelectedItem().getSpcId(), "");

        TableColumn<Vehicle, String> vehicleIdCol = new TableColumn<>("Vehicle Registration");
        vehicleIdCol.setMinWidth(100);
        vehicleIdCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("registrationNumber"));

        TableColumn<Vehicle, String> vehicleMakeCol = new TableColumn<>("Make");
        vehicleMakeCol.setMinWidth(100);
        vehicleMakeCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("make"));

        TableColumn<Vehicle, String> vehicleModelCol = new TableColumn<>("Model");
        vehicleModelCol.setMinWidth(100);
        vehicleModelCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));

        TableColumn<Vehicle, String> deliveryCol = new TableColumn<>("Delivery Date");
        deliveryCol.setMinWidth(100);
        deliveryCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("colour")); //disguised

        TableColumn<Vehicle, String> returnCol = new TableColumn<>("Return Date");
        returnCol.setMinWidth(100);
        returnCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("fuelType")); // disguised

        /* TableColumn<Vehicle , String> vehicleIdCol= new TableColumn<>("Vehicle Registration"); 
        vehicleIdCol.setMinWidth(200);
        vehicleIdCol.setCellValueFactory(new PropertyValueFactory<Vehicle , String >("registrationNumber"));*/
        vehicleExtraDetailTable = new TableView<>();
        vehicleExtraDetailTable.setItems(obList);

        vehicleExtraDetailTable.getColumns().addAll(vehicleIdCol, vehicleMakeCol, vehicleModelCol, deliveryCol, returnCol);

        TextField vehicleSearchTextField = new TextField();
        vehicleSearchTextField.setPromptText("Search Held Vehicles By Registration");
        Button buttonRefresh = new Button();
        buttonRefresh.setText("Refresh");

        Label extraDetailVehicleLabel = new Label();
        buttonRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ObservableList<Vehicle> OooooList = control.getVehicleDetails(SPC_DisplayTable.getSelectionModel().getSelectedItem().getSpcId(), vehicleSearchTextField.getText());
                vehicleExtraDetailTable.setItems(OooooList);
                extraDetailVehicleLabel.setText("");
            }
        });

        vehicleExtraDetailTable.setRowFactory(c -> {
            TableRow<Vehicle> row = new TableRow();
            row.setOnMouseClicked(evt -> {
                if (!row.isEmpty()) {

                    extraDetailVehicleLabel.setText(control.getExtraVehicleDetailsForLabelMethod(row.getItem().getRegistrationNumber()));

                    //control.getExtraPartDetailsForLabelMethod(row.getItem().getPartUsedID());
                }
            });
            return row;
        });

        Stage dialog = new Stage();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(vehicleSearchTextField, buttonRefresh, vehicleExtraDetailTable, extraDetailVehicleLabel);
        Scene scene = new Scene(vBox, 600, 550);
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();

    }

    @FXML
    void partDetailsButton(ActionEvent event) {
        if (SPC_DisplayTable.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Click on a Desired Spc");
            return;
        }

        ObservableList<partsUsed> obList = control.getPartsDetails(SPC_DisplayTable.getSelectionModel().getSelectedItem().getSpcId());

        TableColumn<partsUsed, Integer> partIdCol = new TableColumn<>("Part ID");
        partIdCol.setMinWidth(100);
        partIdCol.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("fPartID"));

        TableColumn<partsUsed, String> partNameCol = new TableColumn<>("Name");
        partNameCol.setMinWidth(100);
        partNameCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("name"));

        TableColumn<partsUsed, String> partDescriptionCol = new TableColumn<>("Description");
        partDescriptionCol.setMinWidth(100);
        partDescriptionCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("fDescription"));

        TableColumn<partsUsed, String> partDeliveryDateCol = new TableColumn<>("Delivery Date");
        partDeliveryDateCol.setMinWidth(100);
        partDeliveryDateCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("dateInstalled")); // delivert date swap //

        TableColumn<partsUsed, String> partReturnDateCol = new TableColumn<>("Return Date");
        partReturnDateCol.setMinWidth(100);
        partReturnDateCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("warrantyExpiry"));  // return date swap//

        partsUsedExtraDetailTable = new TableView<>();
        partsUsedExtraDetailTable.setItems(obList);

        partsUsedExtraDetailTable.getColumns().addAll(partIdCol, partNameCol, partDescriptionCol, partDeliveryDateCol, partReturnDateCol);

        Label extraDetailsLabel = new Label();

        partsUsedExtraDetailTable.setRowFactory(c -> {
            TableRow<partsUsed> row = new TableRow();
            row.setOnMouseClicked(evt -> {
                if (!row.isEmpty()) {

                    extraDetailsLabel.setText(control.getExtraPartDetailsForLabelMethod(row.getItem().getPartUsedID()));

                    //control.getExtraPartDetailsForLabelMethod(row.getItem().getPartUsedID());
                }
            });
            return row;
        });
        Stage dialog = new Stage();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(partsUsedExtraDetailTable, extraDetailsLabel);
        Scene scene = new Scene(vBox, 600, 520);
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();

    }

    @FXML
    void viewPartsAtSpcAction(ActionEvent event) {

        if (SPC_DisplayTable.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Click on a Desired Spc");
            return;
        }
        ObservableList<partsUsed> obList = control.viewAllSpcParts(SPC_DisplayTable.getSelectionModel().getSelectedItem().getSpcId());

        TableColumn<partsUsed, Integer> partIdCol = new TableColumn<>("Part ID");
        partIdCol.setMinWidth(100);
        partIdCol.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("partUsedID"));

        TableColumn<partsUsed, String> partNameCol = new TableColumn<>("Name");
        partNameCol.setMinWidth(100);
        partNameCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("name"));

        TableColumn<partsUsed, String> partDeliveryDateCol = new TableColumn<>("Date Installed");
        partDeliveryDateCol.setMinWidth(100);
        partDeliveryDateCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("dateInstalled")); // delivert date swap //

        TableColumn<partsUsed, String> partReturnDateCol = new TableColumn<>("Warranrty Expiry");
        partReturnDateCol.setMinWidth(100);
        partReturnDateCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("warrantyExpiry"));  // return date swap//

        extraPartDetailsTable = new TableView<>();
        extraPartDetailsTable.setItems(obList);

        extraPartDetailsTable.getColumns().addAll(partIdCol, partNameCol, partDeliveryDateCol, partReturnDateCol);

        Label extraDetailsLabel = new Label();

        extraPartDetailsTable.setRowFactory(c -> {
            TableRow<partsUsed> row = new TableRow();
            row.setOnMouseClicked(evt -> {
                if (!row.isEmpty()) {

                    extraDetailsLabel.setText(control.getExtraPartDetailsForLabelMethod(row.getItem().getPartUsedID()));

                    //control.getExtraPartDetailsForLabelMethod(row.getItem().getPartUsedID());
                }
            });
            return row;
        });

        Stage dialog = new Stage();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(extraPartDetailsTable, extraDetailsLabel);
        Scene scene = new Scene(vBox, 600, 520);
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();

    }

    @FXML
    void refreshVehicleAddTable(ActionEvent event) {
        ObservableList<Vehicle> vehicleTableObList;
        if (searchRegistration.isSelected()) {
            vehicleTableObList = control.getVehicleData(search_vehicle_table_add.getText(), "reg");
        } else {
            vehicleTableObList = control.getVehicleData(search_vehicle_table_add.getText(), "man");
        }

        Vehicle_Search_Registration.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("registrationNumber"));
        Vehicle_Search_Manafacturer.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));
        Vehicle_Search_Make.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("make"));
        Vehicle_Search_Type.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("vehicleType"));
        Vehicle_Search_Mileage.setCellValueFactory(new PropertyValueFactory<Vehicle, Integer>("currentMileage"));
        Vehicle_Search_Warranty.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("hasWarranty"));
        Search_VehiclesTable.setItems(vehicleTableObList);

    }

    @FXML
    void selectVehicleAction(MouseEvent event) {

        retVehicleId = null;
        if (event.getClickCount() == 1) {
            retVehicleId = Search_VehiclesTable.getSelectionModel().getSelectedItem().getRegistrationNumber();
            System.out.println(retVehicleId);
        } else if (event.getClickCount() == 2) {
            Search_VehiclesTable.getSelectionModel().clearSelection();
            event.consume();
            retVehicleId = null;

        }
    }

    @FXML
    void selectSpcAction(MouseEvent event) {
        retSpcId = null;
        try {
            if (event.getClickCount() == 1) {
                retSpcId = SPC_DisplayTable.getSelectionModel().getSelectedItem().getSpcId();
            } else if (event.getClickCount() == 2) {
                SPC_DisplayTable.getSelectionModel().clearSelection();
                event.consume();
                retSpcId = null;
            }
        } catch (NullPointerException e) {

        }
    }

    @FXML
    void selectPartsAction(MouseEvent event) {
        retPartId = null;
        if (event.getClickCount() == 1) {
            retPartId = Search_PartsTable.getSelectionModel().getSelectedItem().getPuID();
        } else if (event.getClickCount() == 2) {
            Search_PartsTable.getSelectionModel().clearSelection();
            event.consume();
            retPartId = null;
        }
    }

    boolean validate() {

        try {
            if (returnDate.getValue() == null) {
                JOptionPane.showMessageDialog(null, "ENTER a return date");
                return false;
            }
            if (deliveryDate.getValue() == null) {
                JOptionPane.showMessageDialog(null, "Enter a delivery Date");
                return false;
            }
            if (retSpcId == null) {
                JOptionPane.showMessageDialog(null, "add a specified SPC");
                return false;
            }

            if (retVehicleId == null && retPartId == null) {
                JOptionPane.showMessageDialog(null, "Add a part or a vehicle to the booking");
                return false;
            }
            if (retVehicleId != null && retPartId != null) {
                JOptionPane.showMessageDialog(null, "You can only book a part a vehicle one at a time please unselect one and try again");
                return false;
            }
            if (bookingCostField == null) {
                JOptionPane.showMessageDialog(null, "Enter a booking cost");

                return false;
            }
            if (bookingCostField != null) {
                Double.parseDouble(bookingCostField.getText());

            }
            if (deliveryDate.getValue().isAfter(returnDate.getValue())) {
                JOptionPane.showMessageDialog(null, "Enter a return date after delivery date ");

                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter A Valid Currency Format");
            return false;
        }

        return true;
    }

    @FXML
    void addSpcBooking(ActionEvent event) {
        if (validate()) {
            control.addBooking(deliveryDate.getValue(), returnDate.getValue(), retSpcId, retVehicleId, retPartId, Double.parseDouble(bookingCostField.getText()));
        }
    }

    @FXML
    void searchSpcBooking(ActionEvent event) {

        ObservableList<SpcBookings> obList = control.getSpcBookingsData(Spc_Search_Button.getText().toLowerCase());
        View_SpcBookingId.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("spcBookingId"));
        View_Registration.setCellValueFactory(new PropertyValueFactory<SpcBookings, String>("vehicleId"));
        //View_FirstName.setCellValueFactory(new PropertyValueFactory<SpcBookings, String>(""));
        viewSpcCost.setCellValueFactory(new PropertyValueFactory<SpcBookings, Double>("cost"));
        viewDeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        viewReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        SpcBookingsTable.setItems(obList);
        bookingExtraInfo.clear();
    }

    @FXML
    void extraInfoLoader(MouseEvent event) {

        if (event.getClickCount() == 1) {
            bookingExtraInfo.setText(control.getDetailsInfo(SpcBookingsTable.getSelectionModel().getSelectedItem().getSpcBookingId()));
            spcBookingRetSpcId = SpcBookingsTable.getSelectionModel().getSelectedItem().getVehicleId();
        }
    }

    @FXML
    void DeletePartToSpcBooking(ActionEvent event) {

        if (SpcBookingsTable.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Click on a Desired Spc");
            return;
        }
        ObservableList obList = control.getDeletionPartList(SpcBookingsTable.getSelectionModel().getSelectedItem().getVehicleId());

        TableColumn<partsUsed, Integer> partIdCol = new TableColumn<>("Used Part ID");
        partIdCol.setMinWidth(100);
        partIdCol.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("partUsedID"));

        TableColumn<partsUsed, String> partNameCol = new TableColumn<>("Name");
        partNameCol.setMinWidth(100);
        partNameCol.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("name"));

        TableColumn<partsUsed, String> partInstalled = new TableColumn<>("Date Installed");
        partInstalled.setMinWidth(100);
        partInstalled.setCellValueFactory(new PropertyValueFactory<partsUsed, String>("dateInstalled"));

        TableColumn<partsUsed, Integer> partId = new TableColumn<>("part Id");
        partId.setMinWidth(100);
        partId.setCellValueFactory(new PropertyValueFactory<partsUsed, Integer>("fPartID"));

        TableView deletePartDetails = new TableView<partsUsed>();

        deletePartDetails = new TableView<>();
        deletePartDetails.setItems(obList);

        deletePartDetails.getColumns().addAll(partIdCol, partNameCol, partInstalled, partId);

        Label label = new Label();
        label.setText("Click On a Field To Delete a Part");

        Stage dialog = new Stage();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, deletePartDetails);
        Scene scene = new Scene(vBox, 600, 600);
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();

    }

    @FXML
    void addPartToSpcBookingButton(ActionEvent event) {

        if (SpcBookingsTable.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Click on a Desired Spc Booking");
            return;
        }
        Stage dialog = new Stage();

        ComboBox<String> combo = new ComboBox();
        try {
            DBConnection dbCon = DBConnection.getInstance();
            Connection conn = dbCon.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * From Part");
            while (rs.next()) {
                combo.getItems().add(rs.getString("PartID") + "," + " " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        TextField quantity = new TextField();
        quantity.setPromptText("Enter The Specified Number Of Selected Parts");

        Button addButton = new Button();
        addButton.setText("Add Part to Vehicle");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(combo, quantity, addButton);
        Scene scene = new Scene(vBox, 200, 200);
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                String[] breakPartID = combo.getValue().split(",");
                String partid = breakPartID[0];
                int quant = Integer.parseInt(quantity.getText());
                control.popupAddPartToVehicle(SpcBookingsTable.getSelectionModel().getSelectedItem().getVehicleId(), partid, quant);
                dialog.close();
            }
        });
    }

    @FXML
    void editPartToSpcBooking(ActionEvent event) {
        if (SpcBookingsTable.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Click on a Desired Spc Booking");
            return;
        }
    }

    @FXML
    void deleteBookingButtonAction(ActionEvent event) {

        ObservableList obList = control.getListOfBookings();

        TableColumn<SpcBookings, Integer> bookingCol = new TableColumn<>("booking Id");
        bookingCol.setMinWidth(100);
        bookingCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("spcBookingId"));

        TableColumn<SpcBookings, Integer> partSpcId = new TableColumn<>("spc id");
        partSpcId.setMinWidth(100);
        partSpcId.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("spcId"));

        TableColumn<SpcBookings, Integer> partInstalled = new TableColumn<>("part used id");
        partInstalled.setMinWidth(100);
        partInstalled.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("partId"));

        TableColumn<SpcBookings, Integer> vehicleCol = new TableColumn<>("Registration");
        vehicleCol.setMinWidth(100);
        vehicleCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("vehicleId"));

        TableColumn<SpcBookings, Integer> deliveryDateCol = new TableColumn<>("Delivery");
        deliveryDateCol.setMinWidth(100);
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("deliveryDate"));

        TableColumn<SpcBookings, String> returnDateCol = new TableColumn<>("return");
        returnDateCol.setMinWidth(100);
        returnDateCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, String>("returnDate"));

        TableColumn<SpcBookings, Double> costCol = new TableColumn<>("cost");
        costCol.setMinWidth(100);
        costCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, Double>("cost"));

        TableView deleteBookings = new TableView<partsUsed>();

        deleteBookings = new TableView<>();
        deleteBookings.setItems(obList);

        deleteBookings.getColumns().addAll(bookingCol, partSpcId, vehicleCol, partInstalled, deliveryDateCol, returnDateCol, costCol);

        deleteBookings.setRowFactory(c -> {
            TableRow<SpcBookings> row = new TableRow();
            row.setOnMouseClicked(evt -> {
                if (!row.isEmpty()) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirm Delete");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to remove this part from the system?");
                    Optional<ButtonType> action = alert.showAndWait();
                    if (action.get() == ButtonType.OK) {

                        control.deleteBookingPopUpAction(row.getItem().getSpcBookingId());

                    }
                    //click on crap to delete it
                    //control.getExtraPartDetailsForLabelMethod(row.getItem().getPartUsedID());
                }
            });
            return row;
        });
        Label label = new Label();
        label.setText("Click On a Field To Delete a Spc Booking");

        Stage dialog = new Stage();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, deleteBookings);
        Scene scene = new Scene(vBox, 700, 400);
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();
    }

    @FXML
    void viewBookingButton(ActionEvent event) {
        ObservableList obList = control.getListOfBookings();

        TableColumn<SpcBookings, Integer> bookingCol = new TableColumn<>("booking Id");
        bookingCol.setMinWidth(100);
        bookingCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("spcBookingId"));

        TableColumn<SpcBookings, Integer> partSpcId = new TableColumn<>("spc id");
        partSpcId.setMinWidth(100);
        partSpcId.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("spcId"));

        TableColumn<SpcBookings, Integer> partInstalled = new TableColumn<>("part used id");
        partInstalled.setMinWidth(100);
        partInstalled.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("partId"));

        TableColumn<SpcBookings, Integer> vehicleCol = new TableColumn<>("Registration");
        vehicleCol.setMinWidth(100);
        vehicleCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("vehicleId"));

        TableColumn<SpcBookings, Integer> deliveryDateCol = new TableColumn<>("Delivery");
        deliveryDateCol.setMinWidth(100);
        deliveryDateCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, Integer>("deliveryDate"));

        TableColumn<SpcBookings, String> returnDateCol = new TableColumn<>("return");
        returnDateCol.setMinWidth(100);
        returnDateCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, String>("returnDate"));

        TableColumn<SpcBookings, Double> costCol = new TableColumn<>("cost");
        costCol.setMinWidth(100);
        costCol.setCellValueFactory(new PropertyValueFactory<SpcBookings, Double>("cost"));

        TableView deleteBookings = new TableView<partsUsed>();

        deleteBookings = new TableView<>();
        deleteBookings.setItems(obList);

        deleteBookings.getColumns().addAll(bookingCol, partSpcId, vehicleCol, partInstalled, deliveryDateCol, returnDateCol, costCol);

        deleteBookings.setRowFactory(c -> {
            TableRow<SpcBookings> row = new TableRow();
            row.setOnMouseClicked(evt -> {

            });
            return row;
        });
        Label label = new Label();
        label.setText("View All Bookings");

        Stage dialog = new Stage();

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, deleteBookings);
        Scene scene = new Scene(vBox, 700, 400);
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();
    }

    @FXML
    void viewVehiclesAtSpcAction(ActionEvent event) {

        if (SPC_DisplayTable.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Click on a Desired Spc");
            return;
        }
        ObservableList<Vehicle> obList = control.getVehicleDetailsAll(SPC_DisplayTable.getSelectionModel().getSelectedItem().getSpcId(), "");

        TableColumn<Vehicle, String> vehicleIdCol = new TableColumn<>("Vehicle Registration");
        vehicleIdCol.setMinWidth(100);
        vehicleIdCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("registrationNumber"));

        TableColumn<Vehicle, String> vehicleMakeCol = new TableColumn<>("Make");
        vehicleMakeCol.setMinWidth(100);
        vehicleMakeCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("make"));

        TableColumn<Vehicle, String> vehicleModelCol = new TableColumn<>("Model");
        vehicleModelCol.setMinWidth(100);
        vehicleModelCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("model"));

        TableColumn<Vehicle, String> deliveryCol = new TableColumn<>("Delivery Date");
        deliveryCol.setMinWidth(100);
        deliveryCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("colour")); //disguised

        TableColumn<Vehicle, String> returnCol = new TableColumn<>("Return Date");
        returnCol.setMinWidth(100);
        returnCol.setCellValueFactory(new PropertyValueFactory<Vehicle, String>("fuelType")); // disguised

        TableView<Vehicle> vehicleExtraDetailTableAll = new TableView<>();
        vehicleExtraDetailTableAll.setItems(obList);

        vehicleExtraDetailTableAll.getColumns().addAll(vehicleIdCol, vehicleMakeCol, vehicleModelCol, deliveryCol, returnCol);

        TextField vehicleSearchTextField = new TextField();
        vehicleSearchTextField.setPromptText("Search Held Vehicles By Registration");
        Button buttonRefresh = new Button();
        buttonRefresh.setText("Refresh");

        Label extraDetailVehicleLabel = new Label();

        buttonRefresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                ObservableList<Vehicle> OooooList = control.getVehicleDetails(SPC_DisplayTable.getSelectionModel().getSelectedItem().getSpcId(), vehicleSearchTextField.getText());
                vehicleExtraDetailTableAll.setItems(OooooList);
                extraDetailVehicleLabel.setText("");
            }
        });

        vehicleExtraDetailTableAll.setRowFactory(c -> {
            TableRow<Vehicle> row = new TableRow();
            row.setOnMouseClicked(evt -> {
                if (!row.isEmpty()) {

                    extraDetailVehicleLabel.setText(control.getExtraVehicleDetailsForLabelMethod(row.getItem().getRegistrationNumber()));

                    //control.getExtraPartDetailsForLabelMethod(row.getItem().getPartUsedID());
                }
            });
            return row;
        });

        Stage dialog = new Stage();
        VBox vBox = new VBox();
        vBox.getChildren().addAll(vehicleSearchTextField, buttonRefresh, vehicleExtraDetailTableAll, extraDetailVehicleLabel);
        Scene scene = new Scene(vBox, 600, 550);
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.show();

    }

}
