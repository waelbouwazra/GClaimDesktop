/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import Entities.Panier;
import Entities.PanierModel;
import Entities.Produit;
import static GUI.Panier.ShowCartController.currentAbo;
import Services.PanierService;
import Tools.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class ShowPanierController implements Initializable {

    Panier panier = new Panier().getInstance();
    PanierService ps = new PanierService();
    @FXML
    private AnchorPane mainPain;
    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox;
    @FXML
    private Button confirmCart;
    @FXML
    private Button deleteCart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.afficherPanier();
    }    
    private void modifierAbo(PanierModel abo) {
        currentAbo = abo;
       // rs.updateEquipe(abo);
        //MainWindowController.getInstance().loadInterface(Constants.FXML_UPDATE_EQUIPE);

        ps.addProductQuantity(abo);
        mainVBox.getChildren().clear();
        this.afficherPanier();
    }
private void supprimerAbo(PanierModel abo) {
        currentAbo = abo;
       // rs.updateEquipe(abo);
        //MainWindowController.getInstance().loadInterface(Constants.FXML_UPDATE_EQUIPE);
        ps.MinusProductQuantity(abo);
         mainVBox.getChildren().clear();
        this.afficherPanier();
    }
  public Parent makeAboModel(PanierModel pm) {
        Parent parent = null;
        
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_PANIER)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
             
          //  ((Pane) innerContainer.lookup("#addBtn")).setVisible(false);
           // ((Pane) innerContainer.lookup("#minusBtn")).setVisible(false);

            ((Text) innerContainer.lookup("#nomProduit")).setText("Produit : " + pm.getNom_produit());
            ((Text) innerContainer.lookup("#descriptionProduit")).setText("Description : " + pm.getDescription());
            ((Text) innerContainer.lookup("#prixProduit")).setText("Prix : " + pm.getPrix_produit());
            ((Text) innerContainer.lookup("#quantiteProduit")).setText("Quantite : " + pm.getQuantite());
          
          
   
            ((Pane) innerContainer.lookup("#addBtn")).setVisible(true);
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierAbo(pm));
             
            
            ((Pane) innerContainer.lookup("#minusBtn")).setVisible(true);
           ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerAbo(pm));
            
              

        } catch (IOException ex) {
             System.out.println(pm);
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    
    private void afficherPanier(){
      
       ObservableList<PanierModel> items =FXCollections.observableArrayList();
         for (Produit i : panier.getPanier().keySet()) {
                    //System.out.println(i.getNom_produit() + i.getPrix_produit() +  + );
                    PanierModel pm = new PanierModel();
                  pm.setId_produit(i.getId_produit());
                  pm.setNom_produit(i.getNom_produit());
                  pm.setDescription(i.getDescription());
                  pm.setPrix_produit(i.getPrix_produit());
                    pm.setQuantite(panier.getPanier().get(i));
                      System.out.println(pm);
                    
                    items.add(pm);
                }
        if (!items.isEmpty()) {
            for (PanierModel pmodel : items) {

                mainVBox.getChildren().add(makeAboModel(pmodel));

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
}

    @FXML
    private void confirmCart(ActionEvent event) {
        ps.ConfirmCart();
        this.afficherPanier();
    }

    @FXML
    private void deleteCart(ActionEvent event) {
         ps.CancelCart();
         this.afficherPanier();
    }
}
