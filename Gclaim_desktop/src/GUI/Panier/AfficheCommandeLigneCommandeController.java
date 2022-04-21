/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import Entities.Commande;
import Entities.Equipe;
import Entities.LigneCommande;
import Entities.PanierModel;
import Entities.Produit;
import static GUI.Panier.showCommandesController.comm;
import Services.CommandeService;
import Services.LigneCommandeService;
import Tools.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AfficheCommandeLigneCommandeController implements Initializable {

    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox;
    @FXML
    private VBox commandeVBox;
    @FXML
    private Text Total;
    @FXML
    private Text DateAchat;
    @FXML
    private Text Status;
    @FXML
    private Text userName;
    @FXML
    private Pane addBtn;
    @FXML
    private Button deleteButton;
    @FXML
    private Pane minusBtn;
    @FXML
    private Button retour;
    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherLigneCommande();

        Total.setText("Total :"+ comm.getTotal());
        DateAchat.setText("Date Achat :"+comm.getDate_achat());
        userName.setText("Username :"+comm.getUser().getUsername());
        Status.setText("Status :"+comm.isLivrer());
        
    }    
     
    private void afficherLigneCommande(){
      LigneCommandeService lcs = new LigneCommandeService();
        List<LigneCommande> listLC = lcs.getLigneCommandeByCommandeID(comm.getId());
        Collections.reverse(listLC);

     
        if (!listLC.isEmpty()) {
            for (LigneCommande pmodel : listLC) {

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
    public Parent makeAboModel(LigneCommande pm) {
        Parent parent = null;
        
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_LIGNECOMMANDE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
             
          //  ((Pane) innerContainer.lookup("#addBtn")).setVisible(false);
           // ((Pane) innerContainer.lookup("#minusBtn")).setVisible(false);

            ((Text) innerContainer.lookup("#nomProduit")).setText("Produit : " + pm.getProduit().getNom_produit());
            ((Text) innerContainer.lookup("#total")).setText("Total : " + pm.getProduit().getPrix_produit()* pm.getQuantite());
            ((Text) innerContainer.lookup("#prixProduit")).setText("Prix : " + pm.getProduit().getPrix_produit());
            ((Text) innerContainer.lookup("#quantiteProduit")).setText("Quantite : " + pm.getQuantite());
          
              

        } catch (IOException ex) {
         
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void changeStatus(ActionEvent event) {
          CommandeService cs = new CommandeService();
          cs.UpdateCommande(comm);
          Commande c=cs.getSingleCommande(comm.getId());
          Status.setText("Status :"+c.isLivrer());
    }

    @FXML
    private void retourAction(ActionEvent event) {
                    AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuPanier.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            retour.setTextFill(Color.WHITE);
            retour.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());    
        }
    }
    
}
