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

// class controller to increase stock to a part, increases parts added and stock
public class StockIncreaseController implements Initializable {

    DBConnection dbCon = DBConnection.getInstance();
    Connection c = dbCon.getConnection();

    @FXML
    private ComboBox<String> stockIncreaseCombo;
    @FXML
    private TextField quanIncrease;

    @FXML
    private Button addStockBtn;

    @FXML
    private Button cancelStockBtn;

    // validate fields if empty and wrong type
    private boolean validateAddStockFields() {
        if (stockIncreaseCombo.getSelectionModel().isEmpty() | quanIncrease.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Data");
            alert.setHeaderText(null);
            alert.setContentText("Please input information in all of the fields before adding or click cancel!");
            alert.showAndWait();

            return false;
        }

        return true;
    }

    // close on action to close add stock fxml 
    @FXML
    private void handleCloseAddStockButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelStockBtn.getScene().getWindow();
        stage.close();

    }

    // on action when adding stock, updates details in database of part
    @FXML
    private void handleAddStockButtonAction(ActionEvent event) {

        if (validateAddStockFields()) {
            try {

                int quan = Integer.parseInt(quanIncrease.getText());

                String[] breakPartID = stockIncreaseCombo.getValue().split(",");
                String partid = breakPartID[0];

                String sql = "UPDATE Part SET stock_levels = stock_levels + '" + quan + "' WHERE '" + partid + "' = Part.PartID";
                PreparedStatement pst = c.prepareStatement(sql);

                String sql2 = "UPDATE Part SET partsAdded = partsAdded + '" + quan + "' WHERE '" + partid + "' = Part.PartID";
                PreparedStatement pst2 = c.prepareStatement(sql2);

                pst.execute();
                pst.close();
                pst2.execute();
                pst2.close();

                Stage stage = (Stage) addStockBtn.getScene().getWindow();
                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Parts added for this stock item: " + quan);
                alert.show();

            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incorrect Data Type");
                alert.setHeaderText(null);
                alert.setContentText("Please input correct data!");
                alert.showAndWait();

            } catch (SQLException e) {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPartsStock();
        // TODO
    }

    // combo box to select part to increase stock 
    public void showPartsStock() {

        try {
            String sql = "SELECT * FROM 'Part'";
            ResultSet rs;

            Statement st = c.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                stockIncreaseCombo.getItems().add(rs.getString("PartID") + "," + " " + rs.getString("name"));

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

}
