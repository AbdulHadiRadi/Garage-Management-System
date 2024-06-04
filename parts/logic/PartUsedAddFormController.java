/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import common.logic.DBConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wajid
 */
// class to install parts to a vehicle through a repair
public class PartUsedAddFormController implements Initializable {

    DBConnection dbCon = DBConnection.getInstance();
    Connection c = dbCon.getConnection();

    @FXML
    private TextField partUsedIDField;

    @FXML
    private ComboBox<String> pickPartCombo;

    @FXML
    private ComboBox<String> pickBookingCombo;

    @FXML
    private TextField addDateInstalledField;
    @FXML
    private TextField addWarrantyExpField;

    @FXML
    private TextField quantityField;

    @FXML
    private Button addPartUsedToDbBtn;
    @FXML
    private Button cancelPartUsedToDbBtn;
    private customer c1;

    // cancel action to close fxml 
    @FXML
    private void handleClosePartUsedButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelPartUsedToDbBtn.getScene().getWindow();
        stage.close();

    }

    // validate fields when add installed parts
    private boolean validateUsedFields() {
        if (pickPartCombo.getSelectionModel().isEmpty() | pickBookingCombo.getSelectionModel().isEmpty() | quantityField.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Data");
            alert.setHeaderText(null);
            alert.setContentText("Please input information in all of the fields before adding or click cancel!");
            alert.showAndWait();

            return false;
        }

        return true;
    }

    public void getpart(customer c2) {
        this.c1 = c2;
    }

    // button action when adding installed parts
    @FXML
    private void handleAddUsedButtonAction(ActionEvent event) {

        String ss = c1.getReg();
        if (validateUsedFields()) {
            try {

                int quan = Integer.parseInt(quantityField.getText());

                String[] breakPartID = pickPartCombo.getValue().split(",");
                String partid = breakPartID[0];

                String sql1 = "SELECT part_cost"
                        + " FROM Part WHERE '" + partid + "' = Part.PartID";
                PreparedStatement stmt = c.prepareStatement(sql1);
                ResultSet rs = stmt.executeQuery();

                double costofPart = rs.getDouble("part_cost");
                double total = costofPart * quan;

                String sql = "INSERT INTO PartsUsed (DateInstalled, WarrantyExpiry, PartID, bookingID, regNumber, quantityParts, totalPartCost) VALUES (DATETIME('now'), DATETIME('now','+1 year'), ?, ?, '" + ss + "', ?, '" + total + "');";
                PreparedStatement pst = c.prepareStatement(sql);

                String sql2 = "UPDATE Part SET stock_levels = stock_levels - '" + quan + "' WHERE '" + partid + "' = Part.PartID";
                PreparedStatement pst2 = c.prepareStatement(sql2);

                String sql3 = "UPDATE Part SET partsWithdrawn = partsWithdrawn + '" + quan + "' WHERE '" + partid + "' = Part.PartID";
                PreparedStatement pst3 = c.prepareStatement(sql3);

                pst.setString(1, partid);
                pst.setString(2, pickBookingCombo.getValue());
                pst.setInt(3, Integer.parseInt(quantityField.getText()));

                stmt.execute();
                stmt.close();
                pst.execute();
                pst.close();
                pst2.execute();
                pst2.close();
                pst3.execute();
                pst3.close();

                Stage stage = (Stage) addPartUsedToDbBtn.getScene().getWindow();
                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("The amount of parts withdrawn for this stock item: " + quan);
                alert.show();

            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incorrect Data Type");
                alert.setHeaderText(null);
                alert.setContentText("Please input correct data!");
                alert.showAndWait();

            } catch (SQLException e) {
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
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showParts();
    }

    // method for combox box to select a part to install on a vehicle through repair 
    public void showParts() {

        try {
            String sql = "SELECT * FROM 'Part'";
            ResultSet rs;

            Statement st = c.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                pickPartCombo.getItems().add(rs.getString("PartID") + "," + " " + rs.getString("name"));

            }

        } catch (Exception e) {

            System.err.print(e.getClass().getName() + ":" + e.getMessage());
            e.printStackTrace(System.out);

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

    // method for combo box to select booking ID for installing a part to a vehicle 
    public void showBooking() {

        try {

            String sql = "SELECT bookingID FROM Booking WHERE Registration_Number = '" + c1.getReg() + "'";
            ResultSet rs;

            Statement st = c.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                pickBookingCombo.getItems().add(rs.getString("bookingID"));

            }

        } catch (Exception e) {

            System.err.print(e.getClass().getName() + ":" + e.getMessage());
            e.printStackTrace(System.out);

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
