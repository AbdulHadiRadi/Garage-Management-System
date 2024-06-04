/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts.logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

 
/**
*
* @author Wajid
*/
public class RunParts extends Application{
    

@Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Parts.fxml"));
        primaryStage.setTitle("GM-SIS");
        primaryStage.setScene(new Scene(root, 1156, 652));
        primaryStage.show();

    }
    public static void main (String[] args){
       launch(args);
   }

}