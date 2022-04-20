/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

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
 * @author moham
 */
public class MenuPanierController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnPanier;
    @FXML
    private Button btnCommande;
    @FXML
    private Button btnLigneCommande;
    @FXML
    private Button btnPanierFront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showPanier(ActionEvent event) {
             AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("ShowPanier.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnLigneCommande.setTextFill(Color.WHITE);
            btnLigneCommande.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());    
        }
    }

    @FXML
    private void showcommande(ActionEvent event) {
                AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("showCommandes.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnCommande.setTextFill(Color.WHITE);
            btnCommande.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());    
        }
    }

    @FXML
    private void showLignesCommandes(ActionEvent event) {
                AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("showLigneCommandes.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnLigneCommande.setTextFill(Color.WHITE);
            btnLigneCommande.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());    
        }
    }

    @FXML
    private void affPanier(ActionEvent event) {
           AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("showCart.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnLigneCommande.setTextFill(Color.WHITE);
            btnLigneCommande.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());    
        }
    }

    
}
