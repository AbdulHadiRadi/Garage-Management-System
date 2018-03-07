/*
*author Abdul Hadi
* Controller for Adim module's fxml
*/
package common.logic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import specialist.logic.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javax.swing.JOptionPane;

/**
 * Created by abdul on 3/18/2017.
 */
public class adminController implements Initializable{

// intsance variables
    private ObservableList<User> userData = FXCollections.observableArrayList();

     DBConnection db =  DBConnection.getInstance();
            Connection conn = db.getConnection();
    @FXML
    private Button registration;

    @FXML
    private Button viewUser;

    @FXML
    private Button updateUser;

    @FXML
    private Button deleteReg;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<?, ?> userID;

    @FXML
    private TableColumn<?, ?> type;

    @FXML
    private TableColumn<?, ?> firstName;

    @FXML
    private TableColumn<?, ?> surName;

    @FXML
    private TableColumn<?, ?> password;

    @FXML
    private TextField username;

    @FXML
    private TextField pass;

    @FXML
    private TextField first;

    @FXML
    private TextField sur;

    @FXML
    private Button clear;

    @FXML
    private Button submit;

    @FXML
    private Button cancel;

    @FXML
    private Label label;


    
    /////////////////////////////////////////////////////////////////
    //SPC Admin Stuff
 

    
    @FXML
    private Button logout;

    
    

    @FXML
    private TableView<SpcList> changeSpcTable;

    @FXML
    private TableColumn<SpcList,Integer > changeSpcIDCol;

    @FXML
    private TableColumn<SpcList, String > changNameCol;

    @FXML
    private TableColumn<SpcList, String> changeAddressCol;

    @FXML
    private TableColumn<SpcList, String> changePhoneCol;

    @FXML
    private TableColumn<SpcList, String> changeEmailCol;

    @FXML
    private TextField AddNameBox;

    @FXML
    private TextField addAddressBox;

    @FXML
    private TextField addPhoneBox;

    @FXML
    private TextField addEmailBox;

    @FXML
    private Button addSpcbutton;
    
     @FXML
    private TextField editNameBox;

    @FXML
    private TextField editSpcIdField;

    @FXML
    private TextField editEmailBox;

    @FXML
    private TextField editPhoneBox;

    @FXML
    private TextField editAddressBox;

    @FXML
    private Button deleteSpcButton;

        @FXML
    private Separator sep; 
                
    private User userSelect;

    //initial loaded fields for the fxml
    public void initializingTable(){
        userID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        surName.setCellValueFactory(new PropertyValueFactory<>("SecondName"));
        password.setCellValueFactory(new PropertyValueFactory<>("Password"));
        userTable.setItems(userData);
    }
    //setting Field's Visibility
    public void setFieldsVisibility(boolean set){
        username.setVisible(set);
        pass.setVisible(set);
        first.setVisible(set);
        sur.setVisible(set);
        cancel.setVisible(set);
        clear.setVisible(set);
        submit.setVisible(set);
        label.setVisible(set);
        sep.setVisible(set);
    }
    //Delete Registration Action
    @FXML
    void deleteRegAction(ActionEvent event) {
        User user= userTable.getSelectionModel().getSelectedItem();
        if(user!=null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting User");
            alert.setContentText("Do you wish to delete the selected User?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                deleteUser(user);
                userData.remove(user);
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deleting User");
            alert.setContentText("You had to select one User from the User Table to delete!");
            alert.showAndWait();
        }
    }
    // Deleting user from the database
        public void deleteUser(User user){
           //Using DBConnection object type for the connection with the database.
        DBConnection db =  DBConnection.getInstance();
        Connection conn = db.getConnection();

        Statement statement;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            // Delete Query for deleting user from the database
            statement.executeUpdate("DELETE FROM User WHERE username ='"+user.getId()+"'");
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

    //Registration Button Action
        @FXML
        void registrationAction(ActionEvent event) throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/register.fxml"));
            Scene scene = new Scene(loader.load());
            Stage addStage = new Stage();
            addStage.setTitle("Add New User");
            addStage.setResizable(false);
            addStage.setScene(scene);
             addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.showAndWait();
            viewUserAction();
        }
    
    //Logout Button Action
        @FXML
        public void logoutButtonAction(ActionEvent event) throws Exception {
            Parent parent = FXMLLoader.load(getClass().getClassLoader().getResource("common/gui/login.fxml"));
            Scene scene = new Scene(parent);
            Stage mainWindow;
            mainWindow = (Stage)  ((Node)event.getSource()).getScene().getWindow();
            mainWindow.setScene(scene);
        }
    
    //Displaying existing Users from Database
        public void viewUserAction(){
             //Using DBConnection object type for the connection with the database.
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        try {
            userData.clear();
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = null;
            rs = statement.executeQuery("SELECT * FROM 'User' ");
            while (rs.next()) {
                userData.add(new User(rs.getInt("username"), rs.getString("type"), rs.getString("firstname"), rs.getString("surname"), rs.getString("password")));
            }

            initializingTable();
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
        public void  updaterUserAction(){
             userSelect = userTable.getSelectionModel().getSelectedItem();
            if(userSelect==null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update User");
                alert.setContentText("You had to select one user to Update!");
                alert.showAndWait();
            }
            
            else {
                setFieldsVisibility(true);
                fillingFields(userSelect);
            }
        }
        public void fillingFields(User user){
            username.setText(Integer.toString(user.getId()));
            first.setText(user.getFirstName());
            sur.setText(user.getSecondName());
            pass.setText(user.getPassword());
        }

        public void setUpdateUser(){
            DBConnection db =  DBConnection.getInstance();
            Connection conn = db.getConnection();
            Statement statement;
            try {
                statement = conn.createStatement();
                statement.setQueryTimeout(10);

                statement.executeUpdate("UPDATE 'User' SET username = '"+username.getText()+"', firstname= '"+first.getText()+"', surname = '"+sur.getText()+"', password = '"+pass.getText()+"'WHERE username = '"+userSelect.getId()+"'");
                statement.close();
                conn.close();
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
        }
    //Cancel Button Action
        public void cancelButtonAction(){

            setFieldsVisibility(false);

        }

    //Submit Button Action
        public void submitButtonAction(){
        try{
            // Empty fields error
            if((username.getText().isEmpty() || first.getText().isEmpty() || sur.getText().isEmpty() || pass.getText().isEmpty())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty Fields");
                alert.setContentText("You had to fill all the fields. Some fields are still empty!");
                alert.showAndWait();
            }
            // User id length error
            else if(username.getText().length()!=5){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User ID");
                alert.setContentText("ID had to be exjactly 5digit Numbers");
                alert.showAndWait();
            }
            // Existing userid error
            else if((username.getText().length()==5) && ((userSelect.getId()!=(Integer.parseInt(username.getText()))) && (!uniqueIDCheck(Integer.parseInt(username.getText()))))){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User ID");
                alert.setContentText("This ID already exist in the system!");
                alert.showAndWait();
            }
            //
            else {
                if(Integer.parseInt(username.getText())!=userSelect.getId()){
                    if(uniqueIDCheck(Integer.parseInt(username.getText()))){
                        setUpdateUser();
                        setFieldsVisibility(false);
                        viewUserAction();
                    }
                }
                else {
                    setUpdateUser();
                    setFieldsVisibility(false);
                    viewUserAction();
                }
            }
            //Numeric userid error
        }catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User ID");
                alert.setContentText("The ID had to be only numbers!");
                alert.showAndWait();
        }        
    }
    //Clear Button Action 
     public void clearButtonAction(){
            username.setText("");
            first.setText("");
            sur.setText("");
            pass.setText("");
        }

    //Checking uniqueness of userid
    public boolean uniqueIDCheck(int id){
        DBConnection db = DBConnection.getInstance();
        Connection conn = db.getConnection();
        Statement statement;
        boolean isFound = true;
        try {
            statement = conn.createStatement();
            statement.setQueryTimeout(10);
            ResultSet rs = null;
            rs = statement.executeQuery("SELECT *From 'User'");
            while (rs.next()){
                if(rs.getInt("username") == id ){
                    isFound = false;
                }
            }
            statement.close();
            conn.close();
            return isFound;
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
        return isFound;
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
    public void initialize(URL location, ResourceBundle resources) {
        setFieldsVisibility(false);
        viewUserAction();
        submit.disableProperty().bind(first.textProperty().isEmpty().or(sur.textProperty().isEmpty()).or(username.textProperty().isEmpty()).or(pass.textProperty().isEmpty()));
        displaySpcTable();
 
    }
    
    // Populating Display Special Parts Table
void displaySpcTable(){
     //Using DBConnection object type for the connection with the database.
        DBConnection db =  DBConnection.getInstance();
        Connection conn = db.getConnection();
        ObservableList<SpcList> obList = FXCollections.observableArrayList();
        try{
            
            ResultSet rs = conn.createStatement().executeQuery("Select * From Spc");
            while(rs.next())
            {
              //  obList.add(new SpcList(rs.getInt("spcID"), rs.getString("name"), rs.getString("address"), rs.getString("phone"), rs.getString("email")));
            }
            
            changeSpcIDCol.setCellValueFactory(new PropertyValueFactory<> ("spcId"));
            changNameCol.setCellValueFactory(new PropertyValueFactory<> ("spcName"));
            changeAddressCol.setCellValueFactory(new PropertyValueFactory<> ("spcAddress"));
            changePhoneCol.setCellValueFactory(new PropertyValueFactory<> ("spcPhone"));
            changeEmailCol.setCellValueFactory(new PropertyValueFactory<> ("spcEmail"));
            changeSpcTable.setItems(obList);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    
    //Filling the boxes though mouse selection
    @FXML
    void fillBoxes(MouseEvent event) {
        
        editNameBox.setText(changeSpcTable.getSelectionModel().getSelectedItem().getSpcName());
        editAddressBox.setText(changeSpcTable.getSelectionModel().getSelectedItem().getSpcAddress());
        editEmailBox.setText(changeSpcTable.getSelectionModel().getSelectedItem().getSpcEmail());
       // editPhoneBox.setText(changeSpcTable.getSelectionModel().getSelectedItem().getSpcPhone());
        editSpcIdField.setText((Integer.toString(changeSpcTable.getSelectionModel().getSelectedItem().getSpcId())));
    }
    
    //Add new special part Button Actioin
    @FXML
    void addNewSpcAction(ActionEvent event) {

        if(AddNameBox.getText().trim().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Enter a spc name");
             return;
         }
         if(addAddressBox.getText().trim().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Enter a spc address");
             return;
         }
         if(addEmailBox.getText().trim().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Enter a spc email");
             return;
         }
         if(addPhoneBox.getText().trim().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Enter a spc phone number");
             return;
         }
         else if(addPhoneBox.getText().length() != 11) {
             JOptionPane.showMessageDialog(null, "Enter a spc an 11 digit phone number");
             return;
         }
         
         DBConnection db =  DBConnection.getInstance();
            Connection conn = db.getConnection();
         try {
              Long.parseLong(addPhoneBox.getText());
             String query = "Insert Into Spc (name, address, email, phone)  Values ('" + AddNameBox.getText() +  "', '" +addAddressBox.getText() + "' , '" +
                     addEmailBox.getText() + "' , '" + addPhoneBox.getText() +"')";
             conn.createStatement().execute(query);
             displaySpcTable();
             AddNameBox.clear();
             addAddressBox.clear();
             addEmailBox.clear();
             addPhoneBox.clear();
             conn.close();
         }
         
         catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
          catch(NumberFormatException e)
         {
              JOptionPane.showMessageDialog(null, "Enter a valid phone number");
             return;
         }
            
        finally {
            if (db.getConnection() != null) {
                try {
                    db.getConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        
    }

    

    
// Delete Special Parts button Action
    @FXML
    void deleteSpcButton(ActionEvent event) {
         
         DBConnection db =  DBConnection.getInstance();
            Connection conn = db.getConnection();
        if(changeSpcTable.getSelectionModel().getSelectedItem() ==null )
        {
            JOptionPane.showMessageDialog(null, "Click on a Desired Spc to delete");
            return ;
        }
        try {
            String query = "Delete From Spc Where spcID = '" + changeSpcTable.getSelectionModel().getSelectedItem().getSpcId() + "'";
            conn.createStatement().execute(query);
             query = "DELete From Spc_Bookings Where spcId = '" + changeSpcTable.getSelectionModel().getSelectedItem().getSpcId() + "'";
           conn.createStatement().execute(query);
             displaySpcTable();
            editAddressBox.clear();
            editEmailBox.clear();
            editNameBox.clear();
            editPhoneBox.clear();
            editSpcIdField.clear();
            conn.close();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }     
        finally {
            if (db.getConnection() != null) {
                try {
                    db.getConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
        
    }

    //Edit button action for special parts
    @FXML
    void editSpcButton(ActionEvent event) {

         
         DBConnection db =  DBConnection.getInstance();
            Connection conn = db.getConnection();
         if(editNameBox.getText().trim().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Enter a spc name");
             return;
         }
         if(editAddressBox.getText().trim().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Enter a spc address");
             return;
         }
         if(editEmailBox.getText().trim().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Enter a spc email");
             return;
         }
         if(editPhoneBox.getText().trim().isEmpty())
         {
             JOptionPane.showMessageDialog(null, "Enter a spc phone number");
             return;
         }
          else if(editPhoneBox.getText().length() != 11) {
             JOptionPane.showMessageDialog(null, editPhoneBox.getText().length() +  "Enter a spc an 11 digit phone number");
             return;
         }
          
         try {
             Long.parseLong(editPhoneBox.getText());
             String query = "Update Spc Set name = '" + editNameBox.getText() +  "' , address = '" + editAddressBox.getText() + "' , phone = '" +
                     editPhoneBox.getText() + "' , email = '" + editEmailBox.getText() + "' WHERE spcID = '" + editSpcIdField.getText() + "'";
             conn.createStatement().execute(query);
             displaySpcTable();
             conn.close();
         }
         catch(SQLException e)
         {
             System.out.println(e.getMessage());
         }
         catch(NumberFormatException e)
         {
              JOptionPane.showMessageDialog(null, "Enter a valid phone number");
             return;
         }
         finally {
            if (db.getConnection() != null) {
                try {
                    db.getConnection().close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
         
    }


    
   

}




