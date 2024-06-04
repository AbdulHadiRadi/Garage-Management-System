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
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wajid
 */

// controller class to add new parts to system 
public class AddPartFormController implements Initializable {

    DBConnection dbCon = DBConnection.getInstance();
    Connection c = dbCon.getConnection();

    @FXML
    private TextField inputID;
    @FXML
    private TextField inputName;
    @FXML
    private TextField inputDesc;
    @FXML
    private TextField inputStock;
    @FXML
    private TextField inputCost;
    @FXML
    private Button addToDbBtn;
    @FXML
    private Button cancelAddBtn;

    // method to cancel add part fxml
    @FXML
    private void handleCloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelAddBtn.getScene().getWindow();
        stage.close();

    }

    // method to validate fields
    private boolean validateFields() {
        if (inputID.getText().isEmpty() | inputName.getText().isEmpty() | inputDesc.getText().isEmpty() | inputStock.getText().isEmpty() | inputCost.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Missing Data");
            alert.setHeaderText(null);
            alert.setContentText("Please input information in all of the fields before adding or click cancel!");
            alert.showAndWait();

            return false;
        }

        return true;
    }

    // add button action, inserting to database using sql statement
    @FXML
    private void handleAddButtonAction(ActionEvent event) {
        if (validateFields()) {
            try {
                String sql = "INSERT INTO Part (PartID, name, description, stock_levels, part_cost) VALUES (?, ?, ?, ?, ?);";
                PreparedStatement pst = c.prepareStatement(sql);

                pst.setInt(1, Integer.parseInt(inputID.getText()));
                pst.setString(2, inputName.getText());
                pst.setString(3, inputDesc.getText());
                pst.setInt(4, Integer.parseInt(inputStock.getText()));
                pst.setDouble(5, Double.parseDouble(inputCost.getText()));

                pst.execute();
                pst.close();

                Stage stage = (Stage) addToDbBtn.getScene().getWindow();
                stage.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText(inputName.getText() + " has been added to the stock!" + "\nNumber of parts added: " + inputStock.getText());
                alert.show();

            } catch (NumberFormatException e) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Incorrect Data Type");
                alert.setHeaderText(null);
                alert.setContentText("Please input correct data!");
                alert.showAndWait();
                
            } catch (SQLException e) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Existig Part");
                alert.setHeaderText(null);
                alert.setContentText("This part already exists. Please input a different Part ID! ");
                alert.showAndWait();

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
    }

}
