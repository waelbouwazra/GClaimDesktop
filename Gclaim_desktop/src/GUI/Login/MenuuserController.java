/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author souma
 */
public class MenuuserController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnuserT;
    @FXML
    private Button btncoachT;
    @FXML
    private Button btnuserA;
    @FXML
    private Button btncoachA;
    @FXML
    private Button btnuserD;
    @FXML
    private Button btncoachD;
    @FXML
    private Button btndemande;
    @FXML
    private Button btnequipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showusers(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("affichetusers.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnuserT.setTextFill(Color.WHITE);
            btnuserT.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void showcoach(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("afficheCoachs.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btncoachT.setTextFill(Color.WHITE);
            btncoachT.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void showusersActives(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("affichetusersActive.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnuserA.setTextFill(Color.WHITE);
            btnuserA.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void showcoachActives(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("afficheCoachsActive.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btncoachA.setTextFill(Color.WHITE);
            btncoachA.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showuserDesactives(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("afficheuserDesactive.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btncoachA.setTextFill(Color.WHITE);
            btncoachA.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showcoachDesactives(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("afficheCoachsDesactive.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btncoachA.setTextFill(Color.WHITE);
            btncoachA.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listeddesdemandes(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("listedesdemandes.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btncoachA.setTextFill(Color.WHITE);
            btncoachA.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listedesequipes(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("listedesequipes.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btncoachA.setTextFill(Color.WHITE);
            btncoachA.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
