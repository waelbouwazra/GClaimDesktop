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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    @FXML
    private Button excel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficheCommande();
    }    



    @FXML
    private void changeStatus(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Commande c = new Commande();

        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir changer le status ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
         if(txtListCommandes.getSelectionModel().getSelectedItem()!=null){                      
         c = txtListCommandes.getSelectionModel().getSelectedItem();
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
        commandeService.UpdateCommande(c);
        }
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
               if(txtListCommandes.getSelectionModel().getSelectedItem()!=null){                      
           comm=txtListCommandes.getSelectionModel().getSelectedItem();
           try {
           
         
            pane = FXMLLoader.load(getClass().getResource("AfficheCommandeLigneCommande.fxml"));
            mainPane.getChildren().setAll(pane);
            btngetback.setTextFill(Color.WHITE);
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
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

    @FXML
    private void excelAction(ActionEvent event) {
        CommandeService cs = new CommandeService();
        cs.generateExcel();
    }
}
