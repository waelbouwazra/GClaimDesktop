/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Tools.Constants;
import java.io.IOException;
import java.util.Objects;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author moham
 */
public class FXMain extends Application {
      public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
       try {
                    Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                    Scene scene = new Scene(root, 1366, 768);
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
