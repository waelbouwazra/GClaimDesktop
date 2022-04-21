/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import Entities.Commande;
import Entities.Produit;
import Services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class showCommandesController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnChangeStatus;
    @FXML
    private Button btngetback;
    @FXML
    private ListView<Commande> txtListCommandes;
    CommandeService commandeService = new CommandeService();
    @FXML
    private Button Details;
      public static Commande comm;
    @FXML
    private Button tri;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficheCommande();
    }    



    @FXML
    private void changeStatus(ActionEvent event) {
        Commande c = txtListCommandes.getSelectionModel().getSelectedItem();
        commandeService.UpdateCommande(c);
        ObservableList<Commande> n =FXCollections.observableArrayList();
        txtListCommandes.setItems(n );
           afficheCommande();
    }

    @FXML
    private void loadMenu(ActionEvent event) {
              AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuPanier.fxml"));
            mainPane.getChildren().setAll(pane);
            btngetback.setTextFill(Color.WHITE);
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void afficheCommande(){
             ObservableList<Commande> items =FXCollections.observableArrayList();
        List<Commande> listCommande = commandeService.ShowCommande();
        for(Commande c : listCommande) {
            items.add(c);
        }
       
    txtListCommandes.setItems(items);
        
    }

    @FXML
    private void details(ActionEvent event) {
               AnchorPane pane;
              
        try {
           comm=txtListCommandes.getSelectionModel().getSelectedItem();
            pane = FXMLLoader.load(getClass().getResource("AfficheCommandeLigneCommande.fxml"));
            mainPane.getChildren().setAll(pane);
            btngetback.setTextFill(Color.WHITE);
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void triAction(ActionEvent event) {
          ObservableList<Commande> items =FXCollections.observableArrayList();
          
        List<Commande> listCommande = commandeService.triCommande();
        System.out.println(items);
        for(Commande c : listCommande) {
            items.add(c);
        }
       
    txtListCommandes.setItems(items);
        
    }
}
