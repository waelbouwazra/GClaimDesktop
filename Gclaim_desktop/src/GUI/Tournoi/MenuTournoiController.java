/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

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
public class MenuTournoiController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    
    @FXML
    private Button btndemande;
    @FXML
    private Button btnequipe;
    @FXML
    private Button btncoachT;
    @FXML
    private Button btnuserA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void listeddesdemandes(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("listetournois.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btndemande.setTextFill(Color.WHITE);
            btndemande.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listedesequipes(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("listejeux.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnequipe.setTextFill(Color.WHITE);
            btnequipe.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @FXML
    private void ajouterjeu(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("CreerJeu.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            //btnequipe.setTextFill(Color.WHITE);
            //btnequipe.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ajouterT(ActionEvent event) {
          AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("CreerTournoi.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            //btnequipe.setTextFill(Color.WHITE);
           // btnequipe.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
