/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;  
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;  


/**
 *
 * @author yuss
 */


 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author Amine JAOUADI
 */
public class mainProfil extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        try {
            /*root = FXMLLoader.load(getClass().getResource("AjouterProfil.fxml"));       
            Scene scene = new Scene(root);
            primaryStage.setTitle("Hello World!");
            primaryStage.setScene(scene);
            primaryStage.show();*/
            
             Parent root = FXMLLoader.load(getClass().getResource("AfficherProfil.fxml"));
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("GClaim");
                    primaryStage.setScene(scene);
                    primaryStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        } 
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


    

