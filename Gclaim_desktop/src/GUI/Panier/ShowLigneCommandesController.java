/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import Entities.Commande;
import Entities.LigneCommande;
import Services.LigneCommandeService;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class ShowLigneCommandesController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btngetback;
    @FXML
    private ListView<LigneCommande> txtListLigneCommandes;
    LigneCommandeService ligneCommandeService = new LigneCommandeService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               ObservableList<LigneCommande> items =FXCollections.observableArrayList();
        List<LigneCommande> listLigneCommande = ligneCommandeService.ShowLigneCommande();
        for(LigneCommande c : listLigneCommande) {
            items.add(c);
        }
       
    txtListLigneCommandes.setItems(items);
        
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
    
    
    
}
