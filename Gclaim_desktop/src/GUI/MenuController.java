/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class MenuController implements Initializable {

    private Button btnDisplayProducts;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private AnchorPane sideBarPane;
    @FXML
    private Button gestionUserButton;
    @FXML
    private Button logoutButton;
    @FXML
    private AnchorPane ProfilePane;
    @FXML
    private Label connectedFullname;
    @FXML
    private Label connectedRole;
    @FXML
    private ImageView connectedImage;
    @FXML
    private Button gestionCoachButton;
    @FXML
    private Button gestionProduitButton;
    @FXML
    private Button gestionPanierButton;
    @FXML
    private Button gestionTournoiButton;
    @FXML
    private Button gestionArticlesButton;
     private ServiceUser US;
    @FXML
    private Button gestionCoachButton1;
    @FXML
    private Button gestionCateg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         US = new ServiceUser();
    }    


    private void showProducts(ActionEvent event) {
      
    }

 @FXML
    private void gestionCoach(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Profil/listrdv.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            gestionUserButton.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showProductsMenu(ActionEvent event) {
          AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Produit/MenuProduit.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
//            btnDisplayProducts.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
     private void showusers(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("login/menuuser.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            gestionUserButton.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   @FXML
    private void logout(ActionEvent event) throws IOException {
        US.logOut();
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../GUI/login/Login.fxml"));
            Parent root = loader.load();
           
            logoutButton.getScene().setRoot(root);
       
      

    }
    @FXML
    private void showPanierMenu(ActionEvent event) {
          AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Panier/MenuPanier.fxml"));
            mainPane.getChildren().setAll(pane);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void showCategorieMenu(ActionEvent event) {
        
             AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Produit/MenuCategorie.fxml"));
            mainPane.getChildren().setAll(pane);
            
        } catch (IOException ex) {
            
        }
    }

    @FXML
    private void ShowArticleMenu(ActionEvent event) {
           AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Article/ArticleMenu.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
//            btnDisplayProducts.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
    }

    @FXML
    private void showCatArticle(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Article/AddCat.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
//            btnDisplayProducts.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
    }
    }

