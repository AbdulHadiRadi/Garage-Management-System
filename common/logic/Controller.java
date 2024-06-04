package common.logic;

/*@author Abdul Hadi*/


import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {

    private boolean isAdmin;

    @FXML
    private Button clear;

    @FXML
    private Label label;

    @FXML
    private TextField id;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Pane rootPane;


    public void clearAction() {
        id.setText("");
        password.setText("");
    }


    public void loginAction(ActionEvent event) throws Exception{        
    try{
        if((id.getText().length()==5) && userAuthentication(Integer.parseInt(id.getText()), password.getText())) {         
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("common/gui/modules.fxml"));
        Parent root = (Parent)loader.load();
        if(!isAdmin ){
            ControllerModules controller = loader.<ControllerModules>getController();
            controller.hideTab();
        }
        Scene scene = new Scene(root, 1229 , 996);
        Stage mainWindow; //getting the reference to main Stage.
        mainWindow = (Stage)  ((Node)event.getSource()).getScene().getWindow();
        mainWindow.setScene(scene);
        }
        else{
            id.setText("");
            password.setText("");
            label.setText("Try to login again");
        }
      }catch(NumberFormatException e){
           label.setText("Try to login again");
      }  
    }

    public boolean userAuthentication(int user, String pass){
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = statement.executeQuery("SELECT *From 'User' WHERE username ='"+user+"'");
            if(rs.getString("type").equals("Admin")) isAdmin = true;
            else isAdmin = false;
            if(rs.getInt("username") == user && rs.getString("password").equals(pass)){
                statement.close();
                conn.close();
                return true;
            }
            else{
                 statement.close();
                 conn.close();
                 return false;
                }
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

