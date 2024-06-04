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
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Wajid
 */
// class to edit parts in system 
public class EditPartStockController implements Initializable {

    DBConnection dbCon = DBConnection.getInstance();
    Connection c = dbCon.getConnection();

    @FXML
    private TableView<parts> partsTable1;

    @FXML
    private TableColumn IdCol;

    @FXML
    private TextField namePartStockField;
    @FXML
    private TextField descPartStockField;
    @FXML
    private TextField partStockField;
    @FXML
    private TextField costPartSockField;
    @FXML
    private Button updatePartStockBtn;
    @FXML
    private Button cancelPartStockBtn;

    private int PartID = 0;
    private final ObservableList<parts> data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // cancel action to close edit fxml 
    @FXML
    private void handleCloseEditButtonAction(ActionEvent event) {
        Stage stage = (Stage) cancelPartStockBtn.getScene().getWindow();
        stage.close();

    }

    // get details into textfields to edit
    public void handlePartsEdit(MouseEvent event) {

        parts p1 = partsTable1.getSelectionModel().getSelectedItem();

        namePartStockField.setText(p1.getName());
        descPartStockField.setText(p1.getDesc());

    }

    // get info for edit parts
    public void getEditInfo(int id, String name, String desc, int stock, double cost) {
        PartID = id;
        namePartStockField.setText(name);
        namePartStockField.setText(name);
        descPartStockField.setText(desc);
        partStockField.setText(String.valueOf(stock));
        costPartSockField.setText(String.valueOf(cost));

    }

    private final ObservableList<parts> data6 = FXCollections.observableArrayList();

    // button to edit and update details
    @FXML
    private void handleUpdateButtonAction(ActionEvent event) {
        try {
            String sql = "UPDATE Part SET name=?, description=?, stock_levels=?, part_cost=? WHERE PartID ='" + PartID + "'";
            PreparedStatement pst = c.prepareStatement(sql);

            pst.setString(1, namePartStockField.getText());
            pst.setString(2, descPartStockField.getText());
            pst.setString(3, partStockField.getText());
            pst.setString(4, costPartSockField.getText());

            pst.execute();
            pst.close();

            Stage stage = (Stage) updatePartStockBtn.getScene().getWindow();
            stage.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("The part has been successfully edited!");
            alert.show();

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
