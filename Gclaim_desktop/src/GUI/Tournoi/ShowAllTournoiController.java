/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

import Entities.Equipe;
import Entities.Tournoi;
import Services.ServiceUser;
import Services.ServiceTournoi;
import Front.*;
import GUI.MenuFrontController;
import Services.ServiceEquipe;
import Tools.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.scene.layout.Pane;


/**
 * FXML Controller class
 *
 * @author elyes
 */
public class ShowAllTournoiController implements Initializable {

 public static Tournoi currentAbo;

    @FXML
    public Text topText;
    public Button addButton;
    public VBox mainVBox;
    ServiceTournoi rs=new ServiceTournoi();
    ServiceEquipe es=new ServiceEquipe();

    private ServiceUser US;
    @FXML
    private AnchorPane mainPain;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         US = new ServiceUser();

        List<Tournoi> listAbo = rs.ShowTournoi();
        Collections.reverse(listAbo);

        if (!listAbo.isEmpty()) {
            for (Tournoi abo : listAbo) {
                mainVBox.getChildren().add(makeAboModel(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeAboModel(Tournoi abo) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_TOUR)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Pane) innerContainer.lookup("#btnmodif")).setVisible(false);
           ((Pane) innerContainer.lookup("#rejoindre")).setVisible(true);
            ((Text) innerContainer.lookup("#nomtournoi")).setText("Nom Tournoi : " + abo.getNomtournoi());
            ((Text) innerContainer.lookup("#description")).setText("Description : " + abo.getDescription());
            ((Text) innerContainer.lookup("#Jeu")).setText("Jeu : " + abo.getJeu().getNomjeu());
            ((Text) innerContainer.lookup("#datecreation")).setText("Date de creation : " + abo.getDatec());
            ((Text) innerContainer.lookup("#dateevenement")).setText("Date de l'evenement : " + abo.getDatev());
            ((Text) innerContainer.lookup("#heureevenement")).setText("Heure de l'evenement : " + abo.getHeurev());
            System.out.println(abo.getJeu());
       
            ((Pane) innerContainer.lookup("#rejoindre")).setVisible(true);
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierAbo(abo));
            ((Button) innerContainer.lookup("#btnrj")).setOnAction((event) -> supprimerAbo(abo));

            ((Pane) innerContainer.lookup("#btnmodif")).setVisible(false);
           ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerT(abo));
            
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    private void modifierAbo(Tournoi abo) {
        currentAbo = abo;
        MainWindowController.getInstance().loadInterface(Constants.FXML_UPDATE_TOURNOI);
    }
     private void supprimerT(Tournoi abo) {
        currentAbo = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer ce tournoi ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
           rs.DeleteTournoi(abo.getId());
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_TOUR);
            
            }
        }

    private void supprimerAbo(Tournoi abo) {
        currentAbo = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir rejoindre ce Tournoi ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
                                  System.out.println(es.afficheEquipeUt(US.currentUser.getId()));
                  if(!es.afficheEquipeUt(US.currentUser.getId()).isEmpty()){
                  rs.RejoindreTournoi(new Equipe(es.afficheEquipeUt(US.currentUser.getId()).get(0)),abo);
                  }
                  else 
                  {
                  es.Rejoindreuneequipe(es.Equipdispo().get(0), US.currentUser);
                  rs.RejoindreTournoi(es.Equipdispo().get(0),abo);

                  }
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_TOUR);
            
            }
        }

    
}
