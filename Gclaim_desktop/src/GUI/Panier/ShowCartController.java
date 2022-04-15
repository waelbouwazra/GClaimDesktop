/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import Entities.Panier;
import Entities.PanierModel;
import Entities.Produit;
import Services.PanierService;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
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
public class ShowCartController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ListView<PanierModel> txtListCommandes;
    @FXML
    private Button btngetback;
    @FXML
    private Button btnConfirmCart;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnMinus;
    @FXML
    private Button btnDeleteCart;
PanierService ps = new PanierService();
Panier panier = new Panier().getInstance();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              ObservableList<PanierModel> items =FXCollections.observableArrayList();
                items.clear();
       for (Produit i : panier.getPanier().keySet()) {
                    //System.out.println(i.getNom_produit() + i.getPrix_produit() +  + );
                    PanierModel pm = new PanierModel();
                  pm.setId_produit(i.getId_produit());
                  pm.setNom_produit(i.getNom_produit());
                  pm.setPrix_produit(i.getPrix_produit());
                    pm.setQuantite(panier.getPanier().get(i));
                    
                    items.add(pm);
                }
                txtListCommandes.setItems(items);
               
    
    }


    @FXML
    private void loadMenu(ActionEvent event) {
            AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuPanier.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            btngetback.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());    
        }
    }

    @FXML
    private void confirmCart(ActionEvent event) {
        ps.ConfirmCart();
        this.afficherPanier();
    }

    @FXML
    private void AddQuantity(ActionEvent event) {
               Produit p = txtListCommandes.getSelectionModel().getSelectedItem();
        ps.addProductQuantity(p);
         this.afficherPanier();
    }

    @FXML
    private void minusQuantity(ActionEvent event) {
           Produit p = txtListCommandes.getSelectionModel().getSelectedItem();
        ps.MinusProductQuantity(p);
           this.afficherPanier();
    }

    @FXML
    private void deleteCart(ActionEvent event) {
        ps.CancelCart();
        this.afficherPanier();
    }
    private void afficherPanier(){
         ObservableList<PanierModel> items =FXCollections.observableArrayList();
          items.clear();
       for (Produit i : panier.getPanier().keySet()) {
                    //System.out.println(i.getNom_produit() + i.getPrix_produit() +  + );
                    PanierModel pm = new PanierModel();
                  pm.setId_produit(i.getId_produit());
                  pm.setNom_produit(i.getNom_produit());
                  pm.setPrix_produit(i.getPrix_produit());
                    pm.setQuantite(panier.getPanier().get(i));
                    
                    items.add(pm); System.out.println(items);
                }
                txtListCommandes.setItems(items);
    }
}
