/*@author Abdul Hadi
*Controller for all module's fxml
*/
//importing packages
package common.logic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControllerModules {

        //Instance variables
    @FXML
    private Pane modulesPane;

    @FXML
    private Tab home;

    @FXML
    private TabPane t;

    @FXML
    private AnchorPane homeT;

    @FXML
    private Tab customer;

    @FXML
    private AnchorPane customerT;

    @FXML
    private Tab vehicle;

    @FXML
    private AnchorPane vehicleT;

    @FXML
    private TableColumn regNumber;

    @FXML
    private TableColumn model;

    @FXML
    private TableColumn make;

    @FXML
    private TableColumn engine;

    @FXML
    private TableColumn fuel;

    @FXML
    private TableColumn colour;

    @FXML
    private TableColumn mot;

    @FXML
    private TableColumn mileage;

    @FXML
    private TableColumn lastServiceDate;

    @FXML
    private Button search;

    @FXML
    private Tab bookings;

    @FXML
    private AnchorPane bookT;

    @FXML
    private Tab parts;

    @FXML
    private AnchorPane partT;

    @FXML
    private Tab specialParts;

    @FXML
    private AnchorPane specialT;

    @FXML
    private Tab admin;

    @FXML
    private AnchorPane adminT;

    @FXML
    private TabPane tab;

        // Hiding Admin Module
    public void hideTab() {
        tab.getTabs().remove(5);
    }
}
