/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep.logic;

import common.logic.DBConnection;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.time.format.DateTimeFormatter;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Sajjad
 */
public class BookingpageController implements Initializable {

    DBConnection db = DBConnection.getInstance();
    Connection connection = db.getConnection();

    @FXML
    private TextField Searchfield;
    @FXML
    private Label labelcustomerid; // done
    @FXML
    private ComboBox<String> addFindCustomer;//done, change this to combobox
    @FXML
    private Label labelvehicleid;// done
    @FXML
    private ComboBox<String> addFindVehicle; // done, change this to choicebox
    @FXML
    private ComboBox<String> addSelectType; //done
    @FXML
    private Label addlabelbookingtype; // done
    @FXML
    private Label addlabelbookingdate;// done
    @FXML
    private DatePicker addBookingDate; // done
    @FXML
    private Label addlabelbookingtime; // done
    private TextField addBookingTimeField; // done
    @FXML
    private Label addlabelmechanicid; // done
    @FXML
    private ComboBox<String> addFindMechanic;// done, changed to combobox
    private Label addlabelrepairduration;// done
    private TextField addRepairDuration; // done
    @FXML
    private Button confirmAdd; //done
    @FXML
    private Button cancelAdd;//DONE
    @FXML
    private Label titleAddBooking;
    @FXML
    private Button mainAddBooking;
    @FXML
    private Label titleAddReport; // firstone
    @FXML
    private Label reportBookingIDLabel; // done
    @FXML
    private TextField addReportBookingIDTextField; // done
    @FXML
    private Label addReportVehicleRegLabel;// done
    @FXML
    private Label addReportFirstNameLabel; //done
    @FXML
    private TextField addReportFirstNameTextField; //done
    @FXML
    private Label addReportSurnameLabel; //done
    @FXML
    private TextField addReportSurnameTextField; //done
    @FXML
    private Label addReportModelLabel; //done
    @FXML
    private TextField addReportModelTextField;// done
    @FXML
    private Label addReportBookingDateLabel;
    @FXML
    private TextField addReportBookingDateTextField;//done
    @FXML
    private Label addReportBookingTimeLabel;
    @FXML
    private TextField addReportBookingTimeTextField;//done
    @FXML
    private Label addReporSubHeader;
    @FXML
    private Label addReportCurrentMileageLabel;
    @FXML
    private TextField addReportCurrentMileageTextfField;//done
    @FXML
    private Label addReportMechanicIDLabel;
    @FXML
    private TextField addReportMechanicIDTextField;//done
    @FXML
    private Label addReportHoursLabel;
    @FXML
    private TextField addReportHoursTextField;//done
    @FXML
    private Label addReportLabourCostLabel;
    @FXML
    private TextField addReportLabourCostTextField;//done
    @FXML
    private Button addReportButton;
    @FXML
    private Button addReportCancelButton;
    @FXML
    private Button mainAddReport;
    @FXML
    private TextField addReportVehicleRegTextField; //done
    @FXML
    private Button mainEditBooking;
    @FXML
    private Label titleEditBooking;

    @FXML
    private Button editBookingButton;
    @FXML
    private Button editBookingCancelButton;
    @FXML
    private TableView<Bookings> table;
    @FXML
    private TableColumn<Bookings, Integer> colBookingID;
    @FXML
    private TableColumn<Bookings, String> colFirstname;
    @FXML
    private TableColumn<Bookings, String> colSurname;
    @FXML
    private TableColumn<Bookings, String> colVehicleReg;
    @FXML
    private TableColumn<Bookings, String> colBookingDate;
    @FXML
    private TableColumn<Bookings, String> colBookingTime;
    private TableColumn<Bookings, String> colDuration;
    @FXML
    private TableColumn<Bookings, String> colNextBookingDate;
    @FXML
    private TableColumn<Bookings, String> colMake;
    @FXML
    private Label addBookingNextBookingDateLabel;
    @FXML
    private DatePicker addBookingNextBookingDatePicker;
    @FXML
    private Label addReportNextBookingDateLabel;
    @FXML
    private TextField addReportNextBookingDateTextField;
    @FXML
    private Button refresh;
    @FXML
    private TableColumn<Bookings, Integer> colMechanicID;
    private int hourlyRate;
    @FXML
    private Button searchButton;
    @FXML

    private Label editOriginalBookingIDLabel;
    @FXML
    private TextField editOriginalBookingIDTextField;
    @FXML
    private Label editOriginalBookingFirstNameLabel;
    @FXML
    private TextField editOriginalBookingFirstNameTextField;
    @FXML
    private Label editOriginalBookingSurnameLabel;
    @FXML
    private TextField editOriginalBookingSurnameTextField;
    @FXML
    private Label editOriginalBookingRegLabel;
    @FXML
    private TextField editOriginalBookingRegTextField;
    @FXML
    private Label editOriginalBookingMakeLabel;
    @FXML
    private TextField editOriginalBookingMakeTextField;
    @FXML
    private Label editOriginalBookingMechanicLabel;
    @FXML
    private TextField editOriginalBookingMechanicTextField;
    @FXML

    private Label editOriginalBookingStartDateLabel;
    @FXML
    private TextField editOriginalBookingStartDateTextField;
    @FXML
    private Label editOriginalBookingReturnDateLabel;
    @FXML
    private TextField editOriginalBookingReturnDateTextField;
    @FXML
    private Label editOriginalBookingTimeLabel;
    @FXML
    private TextField editOriginalBookingTimeTextField;
    @FXML
    private Label editOriginalBookingMileageLabel;
    @FXML
    private TextField editOriginalBookingMileageTextField;
    @FXML
    private Label editOriginalBookingHoursSpentLabel;
    @FXML
    private TextField editOriginalBookingHoursSpentTextField;
    @FXML
    private Label editOriginalBookingLabourCostLabel;
    @FXML
    private TextField editOriginalBookingLabourCostTextField;
    @FXML
    private Label editUpdateBookingCustomerLabel;
    @FXML
    private ComboBox<String> editUpdateBookingCustomerBox;
    @FXML
    private Label editUpdateBookingVehicleLabel;
    @FXML
    private ComboBox<String> editUpdateBookingVehicleBox;
    @FXML
    private Label editUpdateBookingMechanicLabel;
    @FXML
    private ComboBox<String> editUpdateBookingMechanicBox;
    @FXML
    private Label editBookingOriginalSubTitle;
    @FXML
    private Label editBookingUpdateSubTitle;

    @FXML
    private Label editUpdateBookingMileageLabel;
    @FXML
    private TextField editUpdateBookingMileageTextField;
    @FXML
    private Label editUpdateBookingStartDateLabel;
    @FXML
    private DatePicker editUpdateBookingStartDatePicker;
    @FXML
    private Label editUpdateBookingReturnDateLabel;
    @FXML
    private DatePicker editUpdateBookingReturnDatePicker;
    @FXML
    private Label editUpdateBookingTimeLabel;

    @FXML
    private Label editUpdateBookingHoursSpentLabel;
    @FXML
    private TextField editUpdateBookingHoursSpentTextField;

    private ComboBox<String> addReportPartUsedComboBox;
    @FXML
    private Label editOriginalBookingTypeLabel;
    @FXML
    private TextField editOriginalBookingTypeTextField;
    @FXML
    private Button mainDeleteBooking;
    @FXML
    private Label editUpdateBookingTypeLabel;
    @FXML
    private ComboBox<String> editUpdateBookingTypeBox;
    @FXML
    private ComboBox<String> addBookingTimeBox; // this is for add 
    @FXML
    private ComboBox<String> editUpdateBookingTimeBox;
    @FXML
    private Button getFutureBookings;
    @FXML
    private Button getPastBookings;
    @FXML
    private Button getAllBookings;
    @FXML
    private Label editOriginalBookingPartUsedLabel;
    @FXML
    private TextArea editOriginalBookingPartUsedTextField;
    @FXML
    private Button viewBookingButton;
    @FXML
    private Button hourly;
    @FXML
    private Button dailybutton;
    @FXML
    private Button monthlyButton;
    @FXML
    private TableColumn<Bookings, String> colBookingType;
    
    @FXML
    private Button bookingLogOut;
    
    @FXML
    public void logoutBookingButtonAction(ActionEvent event) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("common/gui/login.fxml"));
        Scene scene = new Scene(parent);
        Stage mainWindow;
        mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainWindow.setScene(scene);
    }
    //int[] noMonth = {1, 4, 4, 5, 8, 12, 12};
   // int[] noDay = {1, 14, 17, 1, 28, 25, 26};

    /**
     * Initializes the controller class.
     *
     * @param day
     */
    public void updateDropDown(String day) {
        /* This method is to display the garage times when making a booking*/
        addBookingTimeBox.getItems().clear();
        if (day.equalsIgnoreCase("Saturday")) {
            addBookingTimeBox.getItems().add("9:00");
            addBookingTimeBox.getItems().add("9:30");
            addBookingTimeBox.getItems().add("10:00");
            addBookingTimeBox.getItems().add("10:30");
            addBookingTimeBox.getItems().add("11:00");
            addBookingTimeBox.getItems().add("11:30");

        } else {
            addBookingTimeBox.getItems().add("9:00");
            addBookingTimeBox.getItems().add("9:30");
            addBookingTimeBox.getItems().add("10:00");
            addBookingTimeBox.getItems().add("10:30");
            addBookingTimeBox.getItems().add("11:00");
            addBookingTimeBox.getItems().add("11:30");
            addBookingTimeBox.getItems().add("12:00");
            addBookingTimeBox.getItems().add("12:30");
            addBookingTimeBox.getItems().add("13:00");
            addBookingTimeBox.getItems().add("13:30");
            addBookingTimeBox.getItems().add("14:00");
            addBookingTimeBox.getItems().add("14:30");
            addBookingTimeBox.getItems().add("15:00");
            addBookingTimeBox.getItems().add("15:30");
            addBookingTimeBox.getItems().add("16:00");
            addBookingTimeBox.getItems().add("16:30");
            addBookingTimeBox.getItems().add("17:00");
        }

    }

    public void updateDropDownEdit(String day) {
        /* This method is to display the garage times when editing a booking*/

        editUpdateBookingTimeBox.getItems().clear();
        if (day.equalsIgnoreCase("Saturday")) {
            editUpdateBookingTimeBox.getItems().add("9:00");
            editUpdateBookingTimeBox.getItems().add("9:30");
            editUpdateBookingTimeBox.getItems().add("10:00");
            editUpdateBookingTimeBox.getItems().add("10:30");
            editUpdateBookingTimeBox.getItems().add("11:00");
            editUpdateBookingTimeBox.getItems().add("11:30");
        } else {
            editUpdateBookingTimeBox.getItems().add("9:00");
            editUpdateBookingTimeBox.getItems().add("9:30");
            editUpdateBookingTimeBox.getItems().add("10:00");
            editUpdateBookingTimeBox.getItems().add("10:30");
            editUpdateBookingTimeBox.getItems().add("11:00");
            editUpdateBookingTimeBox.getItems().add("11:30");
            editUpdateBookingTimeBox.getItems().add("12:00");
            editUpdateBookingTimeBox.getItems().add("12:30");
            editUpdateBookingTimeBox.getItems().add("13:00");
            editUpdateBookingTimeBox.getItems().add("13:30");
            editUpdateBookingTimeBox.getItems().add("14:00");
            editUpdateBookingTimeBox.getItems().add("14:30");
            editUpdateBookingTimeBox.getItems().add("15:00");
            editUpdateBookingTimeBox.getItems().add("15:30");
            editUpdateBookingTimeBox.getItems().add("16:00");
            editUpdateBookingTimeBox.getItems().add("16:30");
            editUpdateBookingTimeBox.getItems().add("17:00");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MainTable();
       updateDropDown("");
        updateDropDownEdit("");

        /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////*
        /* these are the types of booking a customer can have when the user is creating a booking*/
        addSelectType.getItems().add("Diagnostic and Repair");
        addSelectType.getItems().add("Scheduled Maintenance");
        editUpdateBookingTypeBox.getItems().add("Diagnostic and Repair");
        editUpdateBookingTypeBox.getItems().add("Scheduled Maintenance");
        /*/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
         

 /* ************************************************************************************************ */
       addCustomerID(); // method for finding customer ID when adding a booking
        addVehicleID(); // method for finding vehicle reg when adding a booking 
        addFindMechanic(); // method for finding mechanic who deals with bookinh
        /* ************************************************************************************************ */

 /* **************************************************************************************************************************************************** */
 /* Datepicker method to block user from making appointments in the past and only allows them to make it in the future */
        addBookingDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(LocalDate.now()) || date.getDayOfWeek() == DayOfWeek.SUNDAY || date.isEqual(LocalDate.now()) 
                        || date.isEqual(LocalDate.of(2017, Month.APRIL, 14))|| date.isEqual(LocalDate.of(2017, Month.APRIL, 17))|| date.isEqual(LocalDate.of(2017, Month.DECEMBER, 25))
                        || date.isEqual(LocalDate.of(2017, Month.MAY, 1))|| date.isEqual(LocalDate.of(2017, Month.MAY, 29)) || date.isEqual(LocalDate.of(2017, Month.AUGUST, 28)) 
                        || date.isEqual(LocalDate.of(2017, Month.DECEMBER, 25))|| date.isEqual(LocalDate.of(2017, Month.DECEMBER, 26)) ||date.isEqual(LocalDate.of(2018, Month.JANUARY, 1))
                        || date.isEqual(LocalDate.of(2018, Month.MARCH, 30))|| date.isEqual(LocalDate.of(2018, Month.APRIL, 2))|| date.isEqual(LocalDate.of(2018, Month.MAY, 28))
                        || date.isEqual(LocalDate.of(2018, Month.AUGUST, 27))|| date.isEqual(LocalDate.of(2018, Month.DECEMBER, 25))
                        || date.isEqual(LocalDate.of(2018, Month.DECEMBER, 26)));
            }
        });
        addBookingDate.setEditable(false);

        addBookingNextBookingDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                try {
                    super.updateItem(date, empty);
                    setDisable(date.isBefore(addBookingDate.getValue()) || date.isEqual(LocalDate.now()) || date.getDayOfWeek() == DayOfWeek.SUNDAY  || date.isEqual(LocalDate.of(2017, Month.APRIL, 14))|| date.isEqual(LocalDate.of(2017, Month.APRIL, 17))|| date.isEqual(LocalDate.of(2017, Month.DECEMBER, 25))|| date.isEqual(LocalDate.of(2017, Month.MAY, 1))
                            || date.isEqual(LocalDate.of(2017, Month.MAY, 29)) || date.isEqual(LocalDate.of(2017, Month.AUGUST, 28)) ||
                            date.isEqual(LocalDate.of(2017, Month.DECEMBER, 25))|| date.isEqual(LocalDate.of(2017, Month.DECEMBER, 26))
                            ||date.isEqual(LocalDate.of(2018, Month.JANUARY, 1))|| date.isEqual(LocalDate.of(2018, Month.MARCH, 30))||
                            date.isEqual(LocalDate.of(2018, Month.APRIL, 2))|| date.isEqual(LocalDate.of(2018, Month.MAY, 28))||
                            date.isEqual(LocalDate.of(2018, Month.AUGUST, 27))|| date.isEqual(LocalDate.of(2018, Month.DECEMBER, 25))||
                            date.isEqual(LocalDate.of(2018, Month.DECEMBER, 26)));
                } catch (NullPointerException e) {
                }
            }
        });
        addBookingNextBookingDatePicker.setEditable(false);

        editUpdateBookingStartDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now()) || date.getDayOfWeek() == DayOfWeek.SUNDAY 
                        || date.isEqual(LocalDate.of(2017, Month.APRIL, 14))|| date.isEqual(LocalDate.of(2017, Month.APRIL, 17))
                        || date.isEqual(LocalDate.of(2017, Month.DECEMBER, 25))|| date.isEqual(LocalDate.of(2017, Month.MAY, 1))
                        || date.isEqual(LocalDate.of(2017, Month.MAY, 29)) || date.isEqual(LocalDate.of(2017, Month.AUGUST, 28)) 
                        || date.isEqual(LocalDate.of(2017, Month.DECEMBER, 25))|| date.isEqual(LocalDate.of(2017, Month.DECEMBER, 26)) 
                        ||date.isEqual(LocalDate.of(2018, Month.JANUARY, 1))|| date.isEqual(LocalDate.of(2018, Month.MARCH, 30))
                        || date.isEqual(LocalDate.of(2018, Month.APRIL, 2))|| date.isEqual(LocalDate.of(2018, Month.MAY, 28))
                        || date.isEqual(LocalDate.of(2018, Month.AUGUST, 27))|| date.isEqual(LocalDate.of(2018, Month.DECEMBER, 25))
                        || date.isEqual(LocalDate.of(2018, Month.DECEMBER, 26)));

            }
        });
        editUpdateBookingStartDatePicker.setEditable(false);

        editUpdateBookingReturnDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(editUpdateBookingStartDatePicker.getValue()) || date.isEqual(LocalDate.now()) 
                        || date.getDayOfWeek() == DayOfWeek.SUNDAY  || date.isEqual(LocalDate.of(2017, Month.APRIL, 14))
                        || date.isEqual(LocalDate.of(2017, Month.APRIL, 17))|| date.isEqual(LocalDate.of(2017, Month.DECEMBER, 25))
                        || date.isEqual(LocalDate.of(2017, Month.MAY, 1))|| date.isEqual(LocalDate.of(2017, Month.MAY, 29))
                        || date.isEqual(LocalDate.of(2017, Month.AUGUST, 28)) || date.isEqual(LocalDate.of(2017, Month.DECEMBER, 25))
                        || date.isEqual(LocalDate.of(2017, Month.DECEMBER, 26)) ||date.isEqual(LocalDate.of(2018, Month.JANUARY, 1))
                        || date.isEqual(LocalDate.of(2018, Month.MARCH, 30))|| date.isEqual(LocalDate.of(2018, Month.APRIL, 2))
                        || date.isEqual(LocalDate.of(2018, Month.MAY, 28))|| date.isEqual(LocalDate.of(2018, Month.AUGUST, 27))
                        || date.isEqual(LocalDate.of(2018, Month.DECEMBER, 25))|| date.isEqual(LocalDate.of(2018, Month.DECEMBER, 26)));
            }
        });
        editUpdateBookingReturnDatePicker.setEditable(false);
   
    }

    /* ******************************************************************************************************************************************************************* */
    @FXML
    private void addBooking(ActionEvent event) {
        // visible buttons
        show(true);

    }

    @FXML
    private void cancelAdd(ActionEvent event) {
        show(false);
    }

    public void show(boolean choice) { // this method is for addBooking and CancelAdd

        addFindVehicle.setVisible(choice);
        labelcustomerid.setVisible(choice);
        addFindCustomer.setVisible(choice);
        addSelectType.setVisible(choice);
        labelvehicleid.setVisible(choice);
        addlabelbookingtype.setVisible(choice);
        addlabelbookingdate.setVisible(choice);
        addBookingDate.setVisible(choice);
        addlabelbookingtime.setVisible(choice);
        addlabelmechanicid.setVisible(choice);
        addFindMechanic.setVisible(choice);
        confirmAdd.setVisible(choice);
        cancelAdd.setVisible(choice);
        titleAddBooking.setVisible(choice);
        addBookingNextBookingDateLabel.setVisible(choice);
        addBookingNextBookingDatePicker.setVisible(choice);
        addBookingTimeBox.setVisible(choice);
        addlabelbookingtime.setVisible(choice);

        /* ******************************************************************** */
        // hidden for add report
        titleAddReport.setVisible(false);
        reportBookingIDLabel.setVisible(false);
        addReportBookingIDTextField.setVisible(false);
        addReportBookingIDTextField.setDisable(true);
        addReportVehicleRegTextField.setVisible(false);
        addReportVehicleRegTextField.setDisable(true);
        addReportVehicleRegLabel.setVisible(false);
        addReportFirstNameLabel.setVisible(false);
        addReportFirstNameTextField.setVisible(false);
        addReportFirstNameTextField.setDisable(true);
        addReportSurnameLabel.setVisible(false);
        addReportSurnameTextField.setVisible(false);
        addReportSurnameTextField.setDisable(true);
        addReportModelLabel.setVisible(false);
        addReportModelTextField.setVisible(false);
        addReportModelTextField.setDisable(true);
        addReportBookingDateLabel.setVisible(false);
        addReportBookingDateTextField.setVisible(false);
        addReportBookingDateTextField.setDisable(true);
        addReportBookingTimeLabel.setVisible(false);
        addReportBookingTimeTextField.setVisible(false);
        addReportBookingTimeTextField.setDisable(true);
        addReporSubHeader.setVisible(false);
        addReportCurrentMileageLabel.setVisible(false);
        addReportCurrentMileageTextfField.setVisible(false);
        addReportMechanicIDLabel.setVisible(false);
        addReportMechanicIDTextField.setVisible(false);
        addReportMechanicIDTextField.setDisable(true);
        addReportHoursLabel.setVisible(false);
        addReportHoursTextField.setVisible(false);
        addReportLabourCostLabel.setVisible(false);
        addReportLabourCostTextField.setVisible(false);
        //  addReportLabourCostTextField.setDisable(true);
        addReportButton.setVisible(false);
        addReportCancelButton.setVisible(false);
        addReportNextBookingDateLabel.setVisible(false);
        addReportNextBookingDateTextField.setVisible(false);
        /* ******************************************************************** */

 /* ******************************************************************** */
        // hidden for edit booking
        titleEditBooking.setVisible(false);
        editBookingButton.setVisible(false);
        editBookingCancelButton.setVisible(false);
        editOriginalBookingIDLabel.setVisible(false);
        editOriginalBookingIDTextField.setVisible(false);
        editOriginalBookingFirstNameLabel.setVisible(false);
        editOriginalBookingFirstNameTextField.setVisible(false);
        editOriginalBookingSurnameLabel.setVisible(false);
        editOriginalBookingSurnameTextField.setVisible(false);
        editOriginalBookingRegLabel.setVisible(false);
        editOriginalBookingRegTextField.setVisible(false);
        editOriginalBookingMakeLabel.setVisible(false);
        editOriginalBookingMakeTextField.setVisible(false);
        editOriginalBookingMechanicLabel.setVisible(false);
        editOriginalBookingMechanicTextField.setVisible(false);
        editOriginalBookingPartUsedLabel.setVisible(false);
        editOriginalBookingPartUsedTextField.setVisible(false);
        editOriginalBookingStartDateLabel.setVisible(false);
        editOriginalBookingStartDateTextField.setVisible(false);
        editOriginalBookingReturnDateLabel.setVisible(false);
        editOriginalBookingReturnDateTextField.setVisible(false);
        editOriginalBookingTimeLabel.setVisible(false);
        editOriginalBookingTimeTextField.setVisible(false);
        editOriginalBookingMileageLabel.setVisible(false);
        editOriginalBookingMileageTextField.setVisible(false);
        editOriginalBookingHoursSpentLabel.setVisible(false);
        editOriginalBookingHoursSpentTextField.setVisible(false);
        editOriginalBookingLabourCostLabel.setVisible(false);
        editOriginalBookingLabourCostTextField.setVisible(false);
        editUpdateBookingCustomerLabel.setVisible(false);
        editUpdateBookingCustomerBox.setVisible(false);
        editUpdateBookingVehicleLabel.setVisible(false);
        editUpdateBookingVehicleBox.setVisible(false);
        editUpdateBookingMechanicLabel.setVisible(false);
        editUpdateBookingMechanicBox.setVisible(false);
        editBookingOriginalSubTitle.setVisible(false);
        editBookingUpdateSubTitle.setVisible(false);
        editUpdateBookingMileageLabel.setVisible(false);
        editUpdateBookingMileageTextField.setVisible(false);
        editUpdateBookingStartDateLabel.setVisible(false);
        editUpdateBookingStartDatePicker.setVisible(false);
        editUpdateBookingReturnDateLabel.setVisible(false);
        editUpdateBookingReturnDatePicker.setVisible(false);
        editUpdateBookingTimeLabel.setVisible(false);
        editUpdateBookingHoursSpentLabel.setVisible(false);
        editUpdateBookingHoursSpentTextField.setVisible(false);
        editOriginalBookingTypeLabel.setVisible(false);
        editOriginalBookingTypeTextField.setVisible(false);
        editUpdateBookingTypeLabel.setVisible(false);
        editUpdateBookingTypeBox.setVisible(false);
        editUpdateBookingTimeBox.setVisible(false);
        /* ******************************************************************** */
    }

    @FXML
    private void addReport(ActionEvent event) {

        titleAddReport.setVisible(true);
        reportBookingIDLabel.setVisible(true);
        addReportBookingIDTextField.setVisible(true);
        addReportBookingIDTextField.setDisable(true);
        addReportVehicleRegTextField.setVisible(true);
        addReportVehicleRegTextField.setDisable(true);
        addReportVehicleRegLabel.setVisible(true);
        addReportFirstNameLabel.setVisible(true);
        addReportFirstNameTextField.setVisible(true);
        addReportFirstNameTextField.setDisable(true);
        addReportSurnameLabel.setVisible(true);
        addReportSurnameTextField.setVisible(true);
        addReportSurnameTextField.setDisable(true);
        addReportModelLabel.setVisible(true);
        addReportModelTextField.setVisible(true);
        addReportModelTextField.setDisable(true);
        addReportBookingDateLabel.setVisible(true);
        addReportBookingDateTextField.setVisible(true);
        addReportBookingDateTextField.setDisable(true);
        addReportBookingTimeLabel.setVisible(true);
        addReportBookingTimeTextField.setVisible(true);
        addReportBookingTimeTextField.setDisable(true);
        addReporSubHeader.setVisible(true);
        addReportCurrentMileageLabel.setVisible(true);
        addReportCurrentMileageTextfField.setVisible(true);
        addReportMechanicIDLabel.setVisible(true);
        addReportMechanicIDTextField.setVisible(true);
        addReportMechanicIDTextField.setDisable(true);
        addReportHoursLabel.setVisible(true);
        addReportHoursTextField.setVisible(true);
        addReportLabourCostLabel.setVisible(true);
        addReportLabourCostTextField.setVisible(true);
        addReportLabourCostTextField.setDisable(true);
        addReportButton.setVisible(true);
        addReportCancelButton.setVisible(true);
        addReportNextBookingDateLabel.setVisible(true);
        addReportNextBookingDateTextField.setVisible(true);
        addReportNextBookingDateTextField.setDisable(true);

        // hidden for addbooking
        addFindVehicle.setVisible(false);
        labelcustomerid.setVisible(false);
        addFindCustomer.setVisible(false);
        addSelectType.setVisible(false);
        labelvehicleid.setVisible(false);
        addlabelbookingtype.setVisible(false);
        addlabelbookingdate.setVisible(false);
        addBookingDate.setVisible(false);
        addlabelbookingtime.setVisible(false);
        addlabelmechanicid.setVisible(false);
        addFindMechanic.setVisible(false);
        confirmAdd.setVisible(false);
        cancelAdd.setVisible(false);
        titleAddBooking.setVisible(false);
        addBookingNextBookingDateLabel.setVisible(false);
        addBookingNextBookingDatePicker.setVisible(false);
        addlabelbookingtime.setVisible(false);
        addBookingTimeBox.setVisible(false);

        // hidden for edit booking
        titleEditBooking.setVisible(false);
        editBookingButton.setVisible(false);
        editBookingCancelButton.setVisible(false);
        editOriginalBookingTypeLabel.setVisible(false);
        editOriginalBookingTypeTextField.setVisible(false);
        editOriginalBookingIDLabel.setVisible(false);
        editOriginalBookingIDTextField.setVisible(false);
        editOriginalBookingFirstNameLabel.setVisible(false);
        editOriginalBookingFirstNameTextField.setVisible(false);
        editOriginalBookingSurnameLabel.setVisible(false);
        editOriginalBookingSurnameTextField.setVisible(false);
        editOriginalBookingRegLabel.setVisible(false);
        editOriginalBookingRegTextField.setVisible(false);
        editOriginalBookingMakeLabel.setVisible(false);
        editOriginalBookingMakeTextField.setVisible(false);
        editOriginalBookingMechanicLabel.setVisible(false);
        editOriginalBookingMechanicTextField.setVisible(false);
        editOriginalBookingPartUsedLabel.setVisible(false);
        editOriginalBookingPartUsedTextField.setVisible(false);
        editOriginalBookingStartDateLabel.setVisible(false);
        editOriginalBookingStartDateTextField.setVisible(false);
        editOriginalBookingReturnDateLabel.setVisible(false);
        editOriginalBookingReturnDateTextField.setVisible(false);
        editOriginalBookingTimeLabel.setVisible(false);
        editOriginalBookingTimeTextField.setVisible(false);
        editOriginalBookingMileageLabel.setVisible(false);
        editOriginalBookingMileageTextField.setVisible(false);
        editOriginalBookingHoursSpentLabel.setVisible(false);
        editOriginalBookingHoursSpentTextField.setVisible(false);
        editOriginalBookingLabourCostLabel.setVisible(false);
        editOriginalBookingLabourCostTextField.setVisible(false);
        editUpdateBookingCustomerLabel.setVisible(false);
        editUpdateBookingCustomerBox.setVisible(false);
        editUpdateBookingVehicleLabel.setVisible(false);
        editUpdateBookingVehicleBox.setVisible(false);
        editUpdateBookingMechanicLabel.setVisible(false);
        editUpdateBookingMechanicBox.setVisible(false);
        editBookingOriginalSubTitle.setVisible(false);
        editBookingUpdateSubTitle.setVisible(false);
        editUpdateBookingMileageLabel.setVisible(false);
        editUpdateBookingMileageTextField.setVisible(false);
        editUpdateBookingStartDateLabel.setVisible(false);
        editUpdateBookingStartDatePicker.setVisible(false);
        editUpdateBookingReturnDateLabel.setVisible(false);
        editUpdateBookingReturnDatePicker.setVisible(false);
        editUpdateBookingTimeLabel.setVisible(false);
        editUpdateBookingTimeBox.setVisible(false);
        editUpdateBookingHoursSpentLabel.setVisible(false);
        editUpdateBookingHoursSpentTextField.setVisible(false);
        editUpdateBookingTypeLabel.setVisible(false);
        editUpdateBookingTypeBox.setVisible(false);

    }

    @FXML
    private void addReportCancel(ActionEvent event) {

        titleAddReport.setVisible(false);
        reportBookingIDLabel.setVisible(false);
        addReportBookingIDTextField.setVisible(false);
        addReportBookingIDTextField.setDisable(true);
        addReportVehicleRegTextField.setVisible(false);
        addReportVehicleRegTextField.setDisable(true);
        addReportVehicleRegLabel.setVisible(false);
        addReportFirstNameLabel.setVisible(false);
        addReportFirstNameTextField.setVisible(false);
        addReportFirstNameTextField.setDisable(true);
        addReportSurnameLabel.setVisible(false);
        addReportSurnameTextField.setVisible(false);
        addReportSurnameTextField.setDisable(true);
        addReportModelLabel.setVisible(false);
        addReportModelTextField.setVisible(false);
        addReportModelTextField.setDisable(true);
        addReportBookingDateLabel.setVisible(false);
        addReportBookingDateTextField.setVisible(false);
        addReportBookingDateTextField.setDisable(true);
        addReportBookingTimeLabel.setVisible(false);
        addReportBookingTimeTextField.setVisible(false);
        addReportBookingTimeTextField.setDisable(true);
        addReporSubHeader.setVisible(false);
        addReportCurrentMileageLabel.setVisible(false);
        addReportCurrentMileageTextfField.setVisible(false);
        addReportMechanicIDLabel.setVisible(false);
        addReportMechanicIDTextField.setVisible(false);
        addReportMechanicIDTextField.setDisable(true);
        addReportHoursLabel.setVisible(false);
        addReportHoursTextField.setVisible(false);
        addReportLabourCostLabel.setVisible(false);
        addReportLabourCostTextField.setVisible(false);
        addReportLabourCostTextField.setDisable(true);
        addReportButton.setVisible(false);
        addReportCancelButton.setVisible(false);
        addReportNextBookingDateLabel.setVisible(false);
        addReportNextBookingDateTextField.setVisible(false);
    }

    @FXML
    private void editBooking(ActionEvent event) {

        titleEditBooking.setVisible(true);
        editBookingButton.setVisible(true);
        editBookingCancelButton.setVisible(true);
        editOriginalBookingTypeLabel.setVisible(true);
        editOriginalBookingTypeTextField.setVisible(true);
        editOriginalBookingIDLabel.setVisible(true);
        editOriginalBookingIDTextField.setVisible(true);
        editOriginalBookingFirstNameLabel.setVisible(true);
        editOriginalBookingFirstNameTextField.setVisible(true);
        editOriginalBookingSurnameLabel.setVisible(true);
        editOriginalBookingSurnameTextField.setVisible(true);
        editOriginalBookingRegLabel.setVisible(true);
        editOriginalBookingRegTextField.setVisible(true);
        editOriginalBookingMakeLabel.setVisible(true);
        editOriginalBookingMakeTextField.setVisible(true);
        editOriginalBookingMechanicTextField.setVisible(true);
        editOriginalBookingMechanicLabel.setVisible(true);
        editOriginalBookingPartUsedLabel.setVisible(true);
        editOriginalBookingPartUsedTextField.setVisible(true);
        editOriginalBookingStartDateLabel.setVisible(true);
        editOriginalBookingStartDateTextField.setVisible(true);
        editOriginalBookingReturnDateLabel.setVisible(true);
        editOriginalBookingReturnDateTextField.setVisible(true);
        editOriginalBookingTimeLabel.setVisible(true);
        editOriginalBookingTimeTextField.setVisible(true);
        editOriginalBookingMileageLabel.setVisible(true);
        editOriginalBookingMileageTextField.setVisible(true);
        editOriginalBookingHoursSpentLabel.setVisible(true);
        editOriginalBookingHoursSpentTextField.setVisible(true);
        editOriginalBookingLabourCostLabel.setVisible(true);
        editOriginalBookingLabourCostTextField.setVisible(true);
        editUpdateBookingCustomerLabel.setVisible(true);
        editUpdateBookingCustomerBox.setVisible(true);
        editUpdateBookingVehicleLabel.setVisible(true);
        editUpdateBookingVehicleBox.setVisible(true);
        editUpdateBookingMechanicLabel.setVisible(true);
        editUpdateBookingMechanicBox.setVisible(true);
        editBookingOriginalSubTitle.setVisible(true);
        editBookingUpdateSubTitle.setVisible(true);
        editUpdateBookingMileageLabel.setVisible(true);
        editUpdateBookingMileageTextField.setVisible(true);
        editUpdateBookingStartDateLabel.setVisible(true);
        editUpdateBookingStartDatePicker.setVisible(true);
        editUpdateBookingReturnDateLabel.setVisible(true);
        editUpdateBookingReturnDatePicker.setVisible(true);
        editUpdateBookingTimeLabel.setVisible(true);
        editUpdateBookingTimeBox.setVisible(true);
        editUpdateBookingHoursSpentLabel.setVisible(true);
        editUpdateBookingHoursSpentTextField.setVisible(true);
        editUpdateBookingTypeLabel.setVisible(true);
        editUpdateBookingTypeBox.setVisible(true);

        // hidden for add report
        titleAddReport.setVisible(false);
        reportBookingIDLabel.setVisible(false);
        addReportBookingIDTextField.setVisible(false);
        addReportBookingIDTextField.setDisable(true);
        addReportVehicleRegTextField.setVisible(false);
        addReportVehicleRegTextField.setDisable(true);
        addReportVehicleRegLabel.setVisible(false);
        addReportFirstNameLabel.setVisible(false);
        addReportFirstNameTextField.setVisible(false);
        addReportFirstNameTextField.setDisable(true);
        addReportSurnameLabel.setVisible(false);
        addReportSurnameTextField.setVisible(false);
        addReportSurnameTextField.setDisable(true);
        addReportModelLabel.setVisible(false);
        addReportModelTextField.setVisible(false);
        addReportModelTextField.setDisable(true);
        addReportBookingDateLabel.setVisible(false);
        addReportBookingDateTextField.setVisible(false);
        addReportBookingDateTextField.setDisable(true);
        addReportBookingTimeLabel.setVisible(false);
        addReportBookingTimeTextField.setVisible(false);
        addReportBookingTimeTextField.setDisable(true);
        addReporSubHeader.setVisible(false);
        addReportCurrentMileageLabel.setVisible(false);
        addReportCurrentMileageTextfField.setVisible(false);
        addReportMechanicIDLabel.setVisible(false);
        addReportMechanicIDTextField.setVisible(false);
        addReportMechanicIDTextField.setDisable(true);
        addReportHoursLabel.setVisible(false);
        addReportHoursTextField.setVisible(false);
        addReportLabourCostLabel.setVisible(false);
        addReportLabourCostTextField.setVisible(false);
        addReportLabourCostTextField.setDisable(true);
        addReportButton.setVisible(false);
        addReportCancelButton.setVisible(false);
        addReportNextBookingDateLabel.setVisible(false);
        addReportNextBookingDateTextField.setVisible(false);

        // hidden for addbooking
        addFindVehicle.setVisible(false);
        labelcustomerid.setVisible(false);
        addFindCustomer.setVisible(false);
        addSelectType.setVisible(false);
        labelvehicleid.setVisible(false);
        addlabelbookingtype.setVisible(false);
        addlabelbookingdate.setVisible(false);
        addBookingDate.setVisible(false);
        addlabelmechanicid.setVisible(false);
        addFindMechanic.setVisible(false);
        confirmAdd.setVisible(false);
        cancelAdd.setVisible(false);
        titleAddBooking.setVisible(false);
        addBookingNextBookingDateLabel.setVisible(false);
        addBookingNextBookingDatePicker.setVisible(false);
        addlabelbookingtime.setVisible(false);
        addBookingTimeBox.setVisible(false);

    }

    @FXML
    private void editBookingCancelButton(ActionEvent event) {

        titleEditBooking.setVisible(false);
        editBookingButton.setVisible(false);
        editBookingCancelButton.setVisible(false);
        editOriginalBookingTypeLabel.setVisible(false);
        editOriginalBookingTypeTextField.setVisible(false);
        editOriginalBookingIDLabel.setVisible(false);
        editOriginalBookingIDTextField.setVisible(false);
        editOriginalBookingFirstNameLabel.setVisible(false);
        editOriginalBookingFirstNameTextField.setVisible(false);
        editOriginalBookingSurnameLabel.setVisible(false);
        editOriginalBookingSurnameTextField.setVisible(false);
        editOriginalBookingRegLabel.setVisible(false);
        editOriginalBookingRegTextField.setVisible(false);
        editOriginalBookingMakeLabel.setVisible(false);
        editOriginalBookingMakeTextField.setVisible(false);
        editOriginalBookingMechanicLabel.setVisible(false);
        editOriginalBookingMechanicTextField.setVisible(false);
        editOriginalBookingPartUsedLabel.setVisible(false);
        editOriginalBookingPartUsedTextField.setVisible(false);
        editOriginalBookingStartDateLabel.setVisible(false);
        editOriginalBookingStartDateTextField.setVisible(false);
        editOriginalBookingReturnDateLabel.setVisible(false);
        editOriginalBookingReturnDateTextField.setVisible(false);
        editOriginalBookingTimeLabel.setVisible(false);
        editOriginalBookingTimeTextField.setVisible(false);
        editOriginalBookingMileageLabel.setVisible(false);
        editOriginalBookingMileageTextField.setVisible(false);
        editOriginalBookingHoursSpentLabel.setVisible(false);
        editOriginalBookingHoursSpentTextField.setVisible(false);
        editOriginalBookingLabourCostLabel.setVisible(false);
        editOriginalBookingLabourCostTextField.setVisible(false);
        editUpdateBookingCustomerLabel.setVisible(false);
        editUpdateBookingCustomerBox.setVisible(false);
        editUpdateBookingVehicleLabel.setVisible(false);
        editUpdateBookingVehicleBox.setVisible(false);
        editUpdateBookingMechanicLabel.setVisible(false);
        editUpdateBookingMechanicBox.setVisible(false);
        editBookingOriginalSubTitle.setVisible(false);
        editBookingUpdateSubTitle.setVisible(false);
        editUpdateBookingMileageLabel.setVisible(false);
        editUpdateBookingMileageTextField.setVisible(false);
        editUpdateBookingStartDateLabel.setVisible(false);
        editUpdateBookingStartDatePicker.setVisible(false);
        editUpdateBookingReturnDateLabel.setVisible(false);
        editUpdateBookingReturnDatePicker.setVisible(false);
        editUpdateBookingTimeLabel.setVisible(false);
        editUpdateBookingTimeBox.setVisible(false);
        editUpdateBookingHoursSpentLabel.setVisible(false);
        editUpdateBookingHoursSpentTextField.setVisible(false);
        editUpdateBookingTypeLabel.setVisible(false);
        editUpdateBookingTypeBox.setVisible(false);
    }

    public void MainTable() {
        /* This method is to display data on the main table in the GUI */

        String sql = "SELECT * FROM Booking,Vehicle WHERE Booking.'Registration_Number'=Vehicle.Registration_Number";
        ResultSet rs;
        
        try {
            ObservableList<Bookings> tabledata = FXCollections.<Bookings>observableArrayList();
            Statement st = connection.createStatement();
         
            rs = st.executeQuery(sql);
       
            while (rs.next()) {

                tabledata.add(new Bookings(rs.getInt("BookingID"), getFirstNameCol(rs.getInt("CustomerID")),
                        getSurnameCol(rs.getInt("CustomerID")), rs.getString("Registration_Number"),
                        rs.getString("Manufacturer"), rs.getString("booking_date"), rs.getString("Booking_time"),
                        rs.getString("ReturnBookingDate"), rs.getInt("MechanicID"),rs.getString("booking_type")));
          
            }
          
            colBookingID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            colFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));// same name as getMethod
            colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            colVehicleReg.setCellValueFactory(new PropertyValueFactory<>("Reg"));
            colMake.setCellValueFactory(new PropertyValueFactory<>("Maker"));
            colBookingDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            colBookingTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
            colNextBookingDate.setCellValueFactory(new PropertyValueFactory<>("NextBooking"));
            colMechanicID.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
            colBookingType.setCellValueFactory(new PropertyValueFactory<>("Type"));
           
            table.setItems(tabledata);

        } catch (SQLException ex) {
            ex.printStackTrace();
            
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
        

    }

    public String getFirstNameCol(int customer) throws SQLException {
        /* This method is to get the first name of the customer */

        String sql = "SELECT Firstname from Customer where CustomerID = " + customer;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String fName = rs.getString("Firstname");
        return fName;
    }

    public String getSurnameCol(int customer) throws SQLException {
        /* This method is to get the last name of the customer */
        String sql = "SELECT * FROM Customer where CustomerID = " + customer;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        String sName = rs.getString("Lastname");
        return sName;
    }

    public void addCustomerID() {
        // this method is to find all customers when creating a booking
        String sql = "SELECT * FROM 'Customer'";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                addFindCustomer.getItems().add(rs.getString("CustomerID") + "," + " " + rs.getString("Firstname") + " " + rs.getString("Lastname"));
                editUpdateBookingCustomerBox.getItems().add(rs.getString("CustomerID") + "," + " " + rs.getString("Firstname") + " " + rs.getString("Lastname"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            

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
    }

    public void addVehicleID() {
        // Find vehicles 
        String sql = "SELECT * FROM Vehicle";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                addFindVehicle.getItems().add(rs.getString("Registration_Number") + ", " + rs.getString("Manufacturer")); // name of database column
                editUpdateBookingVehicleBox.getItems().add(rs.getString("Registration_Number") + ", " + rs.getString("Manufacturer"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
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
    }

    public void addFindMechanic() {
        // this method is to find the mechanic who will handle the vehicle for a booking

        String sql = "SELECT * FROM Employee";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                addFindMechanic.getItems().add(rs.getInt("MechanicID") + ", " + rs.getString("name"));
                editUpdateBookingMechanicBox.getItems().add(rs.getInt("MechanicID") + ", " + rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
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
    }

    @FXML
    private void confirmAddBooking(ActionEvent event) {

      if(addBookingNextBookingDatePicker.getValue().isBefore(addBookingDate.getValue())){
            JOptionPane.showMessageDialog(null, "Invalid booking, please change your booking date");
            return;
        
        }
        


        // this method adds the booking
        try {

            String[] breakCustomerID = addFindCustomer.getValue().split(","); // in order to store the IDs into database
            String[] breakVehicleID = addFindVehicle.getValue().split(",");
            String[] breakEmployeeID = addFindMechanic.getValue().split(",");
            String regplate = breakVehicleID[0];
            String mechanicID = breakEmployeeID[0];
            String customerID = breakCustomerID[0];
            String sql = "INSERT INTO Booking (booking_type,booking_date,Registration_Number,MechanicID,CustomerID,Booking_time,ReturnBookingDate) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement pt = connection.prepareStatement(sql);
            pt.setString(1, addSelectType.getValue());
            LocalDate x = addBookingDate.getValue();
            String datevalue = x.toString();
            pt.setString(2, datevalue);
            pt.setString(3, regplate);
            pt.setString(4, mechanicID);
            pt.setString(5, customerID);
            pt.setString(6, addBookingTimeBox.getValue());
            LocalDate y = addBookingNextBookingDatePicker.getValue();
            String api = y.toString();
            pt.setString(7, api);
            pt.execute();
            JOptionPane.showMessageDialog(null, "Booking successfully added");
            MainTable();
            
            addFindCustomer.setValue("");
            addFindVehicle.setValue("");
            addSelectType.setValue("");
            addBookingDate.setValue(null);
            addFindMechanic.setValue("");
            addBookingNextBookingDatePicker.setValue(null);
            addBookingTimeBox.setValue("");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Please fill in all fields to add a booking");
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
    }

    @FXML
    private void data(MouseEvent event) {
        try {
            // this method allows us to click on tablerows which updates the textfields so the user can see it. 

            addReportVehicleRegTextField.setText((table.getSelectionModel().getSelectedItem().getReg()));
            addReportBookingIDTextField.setText(Integer.toString(table.getSelectionModel().getSelectedItem().getID()));
            addReportFirstNameTextField.setText((table.getSelectionModel().getSelectedItem().getFirstName()));
            addReportSurnameTextField.setText((table.getSelectionModel().getSelectedItem().getSurname()));
            addReportModelTextField.setText((table.getSelectionModel().getSelectedItem().getMaker()));
            addReportBookingDateTextField.setText((table.getSelectionModel().getSelectedItem().getDate()));
            addReportBookingTimeTextField.setText((table.getSelectionModel().getSelectedItem().getTime()));
            addReportNextBookingDateTextField.setText((table.getSelectionModel().getSelectedItem().getNextBooking()));
            addReportMechanicIDTextField.setText(Integer.toString(table.getSelectionModel().getSelectedItem().getMechanicID()));

            editOriginalBookingIDTextField.setText(Integer.toString(table.getSelectionModel().getSelectedItem().getID()));
            editOriginalBookingFirstNameTextField.setText((table.getSelectionModel().getSelectedItem().getFirstName()));
            editOriginalBookingSurnameTextField.setText((table.getSelectionModel().getSelectedItem().getSurname()));
            editOriginalBookingMakeTextField.setText((table.getSelectionModel().getSelectedItem().getMaker()));
            editOriginalBookingRegTextField.setText((table.getSelectionModel().getSelectedItem().getReg()));
            editOriginalBookingStartDateTextField.setText((table.getSelectionModel().getSelectedItem().getDate()));
            editOriginalBookingReturnDateTextField.setText((table.getSelectionModel().getSelectedItem().getNextBooking()));
            editOriginalBookingTimeTextField.setText((table.getSelectionModel().getSelectedItem().getTime()));
            editUpdateBookingMechanicBox.setValue(Integer.toString(table.getSelectionModel().getSelectedItem().getMechanicID()) + ",");
            editOriginalBookingMechanicTextField.setText(Integer.toString(table.getSelectionModel().getSelectedItem().getMechanicID()) + ", " + MechanicNameByID());
                        
            LocalDate local = LocalDate.parse(table.getSelectionModel().getSelectedItem().getDate());
            editUpdateBookingStartDatePicker.setValue(local);

            LocalDate local2 = LocalDate.parse(table.getSelectionModel().getSelectedItem().getNextBooking());
            editUpdateBookingReturnDatePicker.setValue(local2);

            String sql = "SELECT * FROM PartsUsed INNER JOIN Part ON PartsUsed.PartID=Part.PartID WHERE PartsUsed.bookingID='" + table.getSelectionModel().getSelectedItem().getID() + "'";
            String text = "";
            Statement pt = connection.createStatement();
            ResultSet rs = pt.executeQuery(sql);
            while (rs.next()) {
                text = text + rs.getString("name") + "\n";
            }
            editOriginalBookingPartUsedTextField.setText(text);
            showEditBooking();

        } catch (Exception e) {
            //e.printStackTrace();
           // JOptionPane.showMessageDialog(null, "Select a valid row");

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

    }

    public String MechanicNameByID() {
        // This method is to shown the name of the mechanic

        String sql = "SELECT * from Employee WHERE MechanicID = '" + table.getSelectionModel().getSelectedItem().getMechanicID() + "'";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            ResultSet s = st.executeQuery(sql);
            return s.getString("name");
        }  
        
        catch (Exception e) {
            e.printStackTrace();
            
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
        return null;
    }

    @FXML
    private void refreshAction(ActionEvent event) {
        addFindCustomer.getItems().clear();
        addFindVehicle.getItems().clear();
        editUpdateBookingCustomerBox.getItems().clear();
        editUpdateBookingVehicleBox.getItems().clear();
        addCustomerID();
        addVehicleID();
        MainTable();
     
    }

    @FXML
    private void addReportConfirm(ActionEvent event) {
        // this method is when adding a report to a booking

        String date = addReportBookingDateTextField.getText();
        LocalDate compare = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {

            LocalDate local = LocalDate.parse(date, formatter);

            if (compare.isBefore(local)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("You cannot fill in a report for a future booking");
                alert.showAndWait();
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please select a booking to add a report to");
            addReportHoursTextField.setText("");
            addReportCurrentMileageTextfField.setText("");
            return;

        }
        

        try {
            Double.parseDouble(addReportHoursTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter number for hours worked");
            return;
        }

        try {
            Integer.parseInt(addReportCurrentMileageTextfField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter number for current mileage");
            return;
        }

        // add constraints 
        try {

            ResultSet rs;
            ResultSet rs1;
            String sql = "SELECT * FROM Employee WHERE MechanicID = '" + addReportMechanicIDTextField.getText() + "'";
            Statement st = connection.createStatement();
            st.setQueryTimeout(5);
            rs = st.executeQuery(sql);

            int hourly = rs.getInt("hourly_rate"); //    
            Double hoursspent = Double.parseDouble(addReportHoursTextField.getText());
            Double total = hourly * hoursspent;
            addReportLabourCostTextField.setText(Double.toString(total));

            Statement sts = connection.createStatement(); // Record employees hours 
            // update hours spent on booking table
            if (!addReportHoursTextField.getText().equals("")) {
                String updateHours = "Update Booking set hoursSpent =" + hoursspent + " WHERE bookingID = '" + addReportBookingIDTextField.getText() + "'";
                sts.execute(updateHours);

            }

            // record hours spent
            if (!addReportHoursTextField.getText().equals("")) {
                Statement stss = connection.createStatement(); // Record labourCost for booking
                String Labourcost = "UPDATE Booking set LabourCost =" + total + " WHERE bookingID = '" + addReportBookingIDTextField.getText() + "'";
                stss.execute(Labourcost);

            }
            // record current mileage
            if (!addReportCurrentMileageTextfField.getText().equals("")) {
                Statement cs = connection.createStatement();
                String mile = "UPDATE Vehicle set Current_Mileage =" + addReportCurrentMileageTextfField.getText() + " WHERE Registration_Number ='" + addReportVehicleRegTextField.getText() + "'";
                cs.execute(mile);

            }

            JOptionPane.showMessageDialog(null, "report successfully added");
            

        } catch (Exception e) {

            //  e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Please select a current booking in the table to add a report to");
            return;

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
        
        addReportHoursTextField.setText("");
        addReportCurrentMileageTextfField.setText("");

    }

    @FXML
    private void search(ActionEvent event) {
        // search query 
        String sql = "SELECT * from Booking, Customer, Vehicle where Booking.[Registration_Number] = Vehicle.[Registration_Number] and Booking.CustomerID = Customer.CustomerID and (Customer.[Lastname] like '%" + Searchfield.getText() + "%' or Customer.[Firstname] like '%" + Searchfield.getText() + "%' or Vehicle.[Registration_Number] like '%" + Searchfield.getText() + "%' or Vehicle.[Manufacturer] like '%" + Searchfield.getText() + "%')";
        manipulate(sql);
    }

    public void manipulate(String sql) {
        // change table according to search query
        ResultSet rs;
        try {

            ObservableList<Bookings> tabledata = FXCollections.<Bookings>observableArrayList();
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                tabledata.add(new Bookings(rs.getInt("BookingID"), getFirstNameCol(rs.getInt("CustomerID")),
                        getSurnameCol(rs.getInt("CustomerID")), rs.getString("Registration_Number"),
                        rs.getString("Manufacturer"), rs.getString("booking_date"), rs.getString("Booking_time"),
                        rs.getString("ReturnBookingDate"), rs.getInt("MechanicID"),rs.getString("booking_type")));
            }

            colBookingID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            colFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));// same name as getMethod
            colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            colVehicleReg.setCellValueFactory(new PropertyValueFactory<>("Reg"));
            colMake.setCellValueFactory(new PropertyValueFactory<>("Maker"));
            colBookingDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            colBookingTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
            colNextBookingDate.setCellValueFactory(new PropertyValueFactory<>("NextBooking"));
            colMechanicID.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
            colBookingID.setCellValueFactory(new PropertyValueFactory<>("Type"));
            table.setItems(tabledata);
            

        } catch (SQLException ex) {
            // ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
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
    }

    public void showEditBooking() {
        // to show booking information in textfields when selecting a valid row on table, this will be seen on the edit screem

        String sql = "SELECT Current_Mileage FROM Vehicle WHERE Registration_Number = '" + editOriginalBookingRegTextField.getText() + "'";
        String sql2 = "SELECT hoursSpent FROM Booking WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
        String sql4 = "SELECT LabourCost FROM Booking WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
        String sql5 = "SELECT booking_type FROM Booking WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
        String sql6 = "SELECT hoursSpent FROM Booking WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
        ResultSet rs;
        ResultSet rs2;
        ResultSet rs3;
        ResultSet rs4;
        ResultSet rs5;
        ResultSet rs6;

        try {

            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);
            editOriginalBookingMileageTextField.setText(Integer.toString(rs.getInt("Current_Mileage")));

            Statement st2 = connection.createStatement();
            rs2 = st2.executeQuery(sql2);
            editOriginalBookingHoursSpentTextField.setText(Integer.toString(rs2.getInt("hoursSpent")));

            Statement st4 = connection.createStatement();
            rs4 = st4.executeQuery(sql4);
            editOriginalBookingLabourCostTextField.setText(Integer.toString(rs4.getInt("LabourCost")));

            Statement st5 = connection.createStatement();
            rs5 = st5.executeQuery(sql5);
            editOriginalBookingTypeTextField.setText(rs5.getString("booking_type"));

            Statement st6 = connection.createStatement();
            rs6 = st6.executeQuery(sql6);
            editUpdateBookingHoursSpentTextField.setText(rs6.getString("hoursSpent"));

        } catch (SQLException ex) {
            ex.printStackTrace();
            
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
    }

    @FXML
    private void editBookingMethod(ActionEvent event) {

                
        try {

            if (!editUpdateBookingMileageTextField.getText().equals("")) {
                try {
                    Integer.parseInt(editUpdateBookingMileageTextField.getText());

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Please enter number for mileage");
                }

                Statement st = connection.createStatement();
                String sql = "UPDATE Vehicle set Current_Mileage = '" + editUpdateBookingMileageTextField.getText() + "'" + " WHERE Registration_Number = '" + editOriginalBookingRegTextField.getText() + "'";
                st.executeUpdate(sql);
            }
            if(editUpdateBookingReturnDatePicker.getValue().isBefore(editUpdateBookingStartDatePicker.getValue())){
            JOptionPane.showMessageDialog(null, "Edit cannot be made, please check your booking ");
            return;       
        }
          
            if (editUpdateBookingCustomerBox.getValue() != null) {
                // change customer
                String[] breakCustomerID = editUpdateBookingCustomerBox.getValue().split(",");
                String customer = breakCustomerID[0];
                Statement st3 = connection.createStatement();
                String sql3 = "UPDATE Booking set CustomerID =" + customer + " WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
                st3.executeUpdate(sql3);
            }

            if (editUpdateBookingVehicleBox.getValue() != null) {
                // change vehicle 
                String[] breakVehicle = editUpdateBookingVehicleBox.getValue().split(",");
                String Vehicle = breakVehicle[0];
                Statement st4 = connection.createStatement();
                String sql4 = "UPDATE Booking set Registration_Number ='" + Vehicle + "' WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
                st4.executeUpdate(sql4);

            }

            // change mechanic for a booking 
            String[] breakMechanic = editUpdateBookingMechanicBox.getValue().split(",");
            String mechanic = breakMechanic[0];
            if (editUpdateBookingMechanicBox.getValue() != null) {
                Statement st5 = connection.createStatement();
                String sql5 = "UPDATE Booking set MechanicID ='" + mechanic + "' WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
                st5.executeUpdate(sql5);
            }

            // change hours spent for a booking, works out laboue cost again 
            ResultSet rs;
            // !editUpdateBookingMileageTextField.getText().equals("")

            if (!editUpdateBookingHoursSpentTextField.getText().equals("")) {
                try {
                    Double.parseDouble(editUpdateBookingHoursSpentTextField.getText());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Enter number for hours spent");
                    return;
                }
                
                Statement st6 = connection.createStatement();
                String sql6 = "UPDATE Booking set hoursSpent = '" + editUpdateBookingHoursSpentTextField.getText() + "'" + " WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
                String sql7 = "SELECT * FROM Employee WHERE MechanicID = '" + mechanic + "'";

                Statement st7 = connection.createStatement();
                rs = st7.executeQuery(sql7);
                int hourly = rs.getInt("hourly_rate");
                Double hoursspent = Double.parseDouble(editUpdateBookingHoursSpentTextField.getText());
                Double total = hourly * hoursspent;
                st6.executeUpdate(sql6);

                Statement stss = connection.createStatement(); // Record labourCost for booking
                String Labourcost = "UPDATE Booking set LabourCost =" + total + " WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
                stss.execute(Labourcost);
            }

            // change date for a booking 
            if (editUpdateBookingStartDatePicker.getValue() != null) {
                Statement st8 = connection.createStatement();
                String sql8 = "UPDATE Booking set booking_date = '" + editUpdateBookingStartDatePicker.getValue() + "' WHERE bookingID = '"
                        + editOriginalBookingIDTextField.getText() + "'";
                st8.executeUpdate(sql8);
            }

            // change return date for a booking 
            if (editUpdateBookingReturnDatePicker.getValue() != null) {
                Statement st9 = connection.createStatement();
                String sql9 = "UPDATE Booking set ReturnBookingDate = '" + editUpdateBookingReturnDatePicker.getValue()
                        + "' WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
                st9.executeUpdate(sql9);
            }

            if (editUpdateBookingTypeBox.getValue() != null) {
                Statement st10 = connection.createStatement();
                String sql10 = "UPDATE Booking set booking_type= '" + editUpdateBookingTypeBox.getValue() + "' WHERE bookingID = '"
                        + editOriginalBookingIDTextField.getText() + "'";
                st10.executeUpdate(sql10);
            }

            // Change booking time
            if (editUpdateBookingTimeBox.getValue() != null) {
                Statement st11 = connection.createStatement();
                String sql11 = "UPDATE Booking set Booking_time = '" + editUpdateBookingTimeBox.getValue()
                        + "' WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";
                st11.executeUpdate(sql11);
            }

            showEditBooking();
            MainTable();
            JOptionPane.showMessageDialog(null, "Editing applied successfully");
          
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Please select a booking and fill in all fields to edit a booking");

        }
        finally{
            if(connection!= null)
            {
                try{
                    connection.close();
                    connection = db.getConnection();
                }
                catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
    

    @FXML
    private void delete(ActionEvent event) {
        // to delete a booking 

        if(table.getSelectionModel().getSelectedItem()!=null){
        int result = 0;
        result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this booking?", "Please Confirm", result);
        if (result == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM Booking WHERE bookingID = '" + editOriginalBookingIDTextField.getText() + "'";

            try {
                Statement stdelete = connection.createStatement();
                stdelete.execute(sql);
                MainTable();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Please select a booking to delete first");
                
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
        }
        }
              else{
             Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("No Booking Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking to delete!");
             Optional<ButtonType> action = alert.showAndWait();
        }
    }

    @FXML
    private void fillDropdown(ActionEvent event) {
        // update the dropdown according to the day and time so bookings cannot be made in days when you cannot book.
        try {
            updateDropDown(addBookingDate.getValue().getDayOfWeek().toString());
        } catch (NullPointerException e) {

        }
    }

    @FXML
    private void editDropDownTime(ActionEvent event) {
        // edit,  update the dropdown according to the day and time so bookings cannot be made in days when you cannot book.
        try {
            updateDropDownEdit(editUpdateBookingStartDatePicker.getValue().getDayOfWeek().toString());
        } catch (NullPointerException e) {
        }
    }

    @FXML
    private void futureBookings(ActionEvent event) {
        // to show future bookings 
        String sql = "SELECT * FROM Booking,Vehicle WHERE Booking.'Registration_Number'=Vehicle.Registration_Number AND booking_date > date('now')";
        ResultSet rs;
        try {

            ObservableList<Bookings> tabledata = FXCollections.<Bookings>observableArrayList();
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {
                tabledata.add(new Bookings(rs.getInt("BookingID"), getFirstNameCol(rs.getInt("CustomerID")),
                        getSurnameCol(rs.getInt("CustomerID")), rs.getString("Registration_Number"),
                        rs.getString("Manufacturer"), rs.getString("booking_date"), rs.getString("Booking_time"),
                        rs.getString("ReturnBookingDate"), rs.getInt("MechanicID"),rs.getString("booking_type")));
            }

            colBookingID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            colFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));// same name as getMethod
            colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            colVehicleReg.setCellValueFactory(new PropertyValueFactory<>("Reg"));
            colMake.setCellValueFactory(new PropertyValueFactory<>("Maker"));
            colBookingDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            colBookingTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
            colNextBookingDate.setCellValueFactory(new PropertyValueFactory<>("NextBooking"));
            colMechanicID.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
            colBookingType.setCellValueFactory(new PropertyValueFactory<>("Type"));
            table.setItems(tabledata);
        } catch (SQLException ex) {

            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
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

    }

    @FXML
    private void pastBookings(ActionEvent event) {
        // to show past bookings 

        String sql = "SELECT * FROM Booking,Vehicle WHERE Booking.'Registration_Number'=Vehicle.Registration_Number AND booking_date < date('now')";
        ResultSet rs;

        try {

            ObservableList<Bookings> tabledata = FXCollections.<Bookings>observableArrayList();
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                tabledata.add(new Bookings(rs.getInt("BookingID"), getFirstNameCol(rs.getInt("CustomerID")),
                        getSurnameCol(rs.getInt("CustomerID")), rs.getString("Registration_Number"),
                        rs.getString("Manufacturer"), rs.getString("booking_date"), rs.getString("Booking_time"),
                        rs.getString("ReturnBookingDate"), rs.getInt("MechanicID"),rs.getString("booking_type")));
            }

            colBookingID.setCellValueFactory(new PropertyValueFactory<>("ID"));
            colFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));// same name as getMethod
            colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            colVehicleReg.setCellValueFactory(new PropertyValueFactory<>("Reg"));
            colMake.setCellValueFactory(new PropertyValueFactory<>("Maker"));
            colBookingDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
            colBookingTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
            colNextBookingDate.setCellValueFactory(new PropertyValueFactory<>("NextBooking"));
            colMechanicID.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
            colMechanicID.setCellValueFactory(new PropertyValueFactory<>("Type"));
            table.setItems(tabledata);

        } catch (Exception ex) {

            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
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
    }

    @FXML
    private void allBookings(ActionEvent event) {
        // show all bookings
        MainTable();
    }

    @FXML
    private void addCustomerIDDropDown(ActionEvent event) {
        // this method is to show vehicles that a selected customer owns

        String cust = addFindCustomer.getValue();
        String[] c = cust.split(", ");
        String sql = "SELECT * FROM Vehicle WHERE CustomerID = '" + c[0] + "'";
        addFindVehicle.getItems().clear();

        try {
            Statement stmt = connection.createStatement();
            ResultSet info = stmt.executeQuery(sql);
            while (info.next()) {
                addFindVehicle.getItems().add((info.getString("Registration_Number")));
            }

        } catch (Exception e) {
            e.printStackTrace();
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
    }

    @FXML
    private void editCustomerIDDropDown(ActionEvent event) {

        // for edit page, this method is to show vehicles that a selected customer owns
        String cust = editUpdateBookingCustomerBox.getValue();
        String[] c = cust.split(", ");
        String sql = "SELECT * FROM Vehicle WHERE CustomerID = '" + c[0] + "'";
        editUpdateBookingVehicleBox.getItems().clear();

        try {
            Statement stmt = connection.createStatement();
            ResultSet info = stmt.executeQuery(sql);
            while (info.next()) {
                editUpdateBookingVehicleBox.getItems().add((info.getString("Registration_Number")));
            }

        } catch (Exception e) {
            e.printStackTrace();
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
    }

    @FXML
    private void viewBookingInformation(ActionEvent event) {
        // view details about a single booking.

        Bookings booking = table.getSelectionModel().getSelectedItem();
        if (booking == null) {
            
             Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("No Booking Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a booking to view it's information!");
             Optional<ButtonType> action = alert.showAndWait();
        
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("diagrep/gui/bookinginfo.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                BookinginfoController controller = fxmlLoader.<BookinginfoController>getController();
                controller.showDetails(booking);
                Stage stage = new Stage();
                stage.setTitle("Booking information");
                stage.setScene(new Scene(root1));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } catch (IOException e) {

            }

        }
    }

    @FXML
    private void hourlymethod(ActionEvent event) {

        List<String> selection = new ArrayList<>();
        selection.add("9");
        selection.add("10");
        selection.add("11");
        selection.add("12");
        selection.add("13");
        selection.add("14");
        selection.add("15");
        selection.add("16");
        selection.add("17");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("9", selection);

        dialog.setTitle("View hourly");
        dialog.setHeaderText("View bookings hourly");
        dialog.setContentText("Select a hour:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

            String hours = result.get();
            String sql = "SELECT * FROM Booking,Vehicle WHERE Booking.'Registration_Number'=Vehicle.Registration_Number";
            ResultSet rs;

            try {
                ObservableList<Bookings> tabledata = FXCollections.<Bookings>observableArrayList();
                Statement st = connection.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    String time = rs.getString("Booking_time");

                    if (time.substring(0, 2).contains(hours)) {

                        tabledata.add(new Bookings(rs.getInt("BookingID"), getFirstNameCol(rs.getInt("CustomerID")),
                        getSurnameCol(rs.getInt("CustomerID")), rs.getString("Registration_Number"),
                        rs.getString("Manufacturer"), rs.getString("booking_date"), rs.getString("Booking_time"),
                        rs.getString("ReturnBookingDate"), rs.getInt("MechanicID"),rs.getString("booking_type")));
                    }

                }

                colBookingID.setCellValueFactory(new PropertyValueFactory<>("ID"));
                colFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));// same name as getMethod
                colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
                colVehicleReg.setCellValueFactory(new PropertyValueFactory<>("Reg"));
                colMake.setCellValueFactory(new PropertyValueFactory<>("Maker"));
                colBookingDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
                colBookingTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
                colNextBookingDate.setCellValueFactory(new PropertyValueFactory<>("NextBooking"));
                colMechanicID.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
                colBookingID.setCellValueFactory(new PropertyValueFactory<>("Type"));
                table.setItems(tabledata);

            } catch (Exception ex) {

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
        } else {
            MainTable();
        }
    }

    @FXML
    private void daily(ActionEvent event) {
        List<String> Selection = new ArrayList<>();
        Selection.add("Monday");
        Selection.add("Tuesday");
        Selection.add("Wednesday");
        Selection.add("Thursday");
        Selection.add("Friday");
        Selection.add("Saturday");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Monday", Selection);
        dialog.setTitle("View by day");
        dialog.setHeaderText("View bookings by day");
        dialog.setContentText("Select a day:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String daily = result.get();

            String sql = "SELECT * FROM Booking,Vehicle WHERE Booking.'Registration_Number'=Vehicle.Registration_Number";
            ResultSet rs;

            try {
                ObservableList<Bookings> tabledata = FXCollections.<Bookings>observableArrayList();
                Statement st = connection.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {

                    String date = rs.getString("booking_date");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    LocalDate localDate = LocalDate.parse(date, formatter);
                    String check = localDate.getDayOfWeek().toString();

                    if (check.equalsIgnoreCase(daily)) {

                         tabledata.add(new Bookings(rs.getInt("BookingID"), getFirstNameCol(rs.getInt("CustomerID")),
                        getSurnameCol(rs.getInt("CustomerID")), rs.getString("Registration_Number"),
                        rs.getString("Manufacturer"), rs.getString("booking_date"), rs.getString("Booking_time"),
                        rs.getString("ReturnBookingDate"), rs.getInt("MechanicID"),rs.getString("booking_type")));

                    }

                }

                colBookingID.setCellValueFactory(new PropertyValueFactory<>("ID"));
                colFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));// same name as getMethod
                colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
                colVehicleReg.setCellValueFactory(new PropertyValueFactory<>("Reg"));
                colMake.setCellValueFactory(new PropertyValueFactory<>("Maker"));
                colBookingDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
                colBookingTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
                colNextBookingDate.setCellValueFactory(new PropertyValueFactory<>("NextBooking"));
                colMechanicID.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
                colBookingID.setCellValueFactory(new PropertyValueFactory<>("Type"));
                table.setItems(tabledata);

            } catch (Exception ex) {
                //ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Main table error...");
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
        } else {
            MainTable();
        }

    }

    @FXML
    private void montly(ActionEvent event) {

        List<String> Selection = new ArrayList<>();
        Selection.add("January");
        Selection.add("Febuary");
        Selection.add("March");
        Selection.add("April");
        Selection.add("May");
        Selection.add("June");
        Selection.add("July");
        Selection.add("August");
        Selection.add("September");
        Selection.add("October");
        Selection.add("November");
        Selection.add("December");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("January", Selection);
        dialog.setTitle("View by month");
        dialog.setHeaderText("View bookings by month");
        dialog.setContentText("Select a Month:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String monthly = result.get();

            ResultSet rs;
            String sql = "SELECT * FROM Booking,Vehicle WHERE Booking.'Registration_Number'=Vehicle.Registration_Number";
            try {
                ObservableList<Bookings> tabledata = FXCollections.<Bookings>observableArrayList();
                Statement st = connection.createStatement();
                rs = st.executeQuery(sql);
                while (rs.next()) {

                    String bookingDate = rs.getString("booking_date");

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    LocalDate localDate = LocalDate.parse(bookingDate, formatter);
                    String check = localDate.getMonth().toString();

                    if (check.equalsIgnoreCase(monthly)) {

                        tabledata.add(new Bookings(rs.getInt("BookingID"), getFirstNameCol(rs.getInt("CustomerID")),
                        getSurnameCol(rs.getInt("CustomerID")), rs.getString("Registration_Number"),
                        rs.getString("Manufacturer"), rs.getString("booking_date"), rs.getString("Booking_time"),
                        rs.getString("ReturnBookingDate"), rs.getInt("MechanicID"),rs.getString("booking_type")));
                    }
                }

                colBookingID.setCellValueFactory(new PropertyValueFactory<>("ID"));
                colFirstname.setCellValueFactory(new PropertyValueFactory<>("FirstName"));// same name as getMethod
                colSurname.setCellValueFactory(new PropertyValueFactory<>("Surname"));
                colVehicleReg.setCellValueFactory(new PropertyValueFactory<>("Reg"));
                colMake.setCellValueFactory(new PropertyValueFactory<>("Maker"));
                colBookingDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
                colBookingTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
                colNextBookingDate.setCellValueFactory(new PropertyValueFactory<>("NextBooking"));
                colMechanicID.setCellValueFactory(new PropertyValueFactory<>("MechanicID"));
                colBookingID.setCellValueFactory(new PropertyValueFactory<>("Type"));
                table.setItems(tabledata);

            } catch (Exception ex) {
                //ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Main table error...");
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
        } else {
            MainTable();
        }

    }

}
