/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

import Entities.Equipe;
import Entities.Tournoi;
import Entities.Jeu;
import Services.ServiceTournoi;
import Services.ServiceJeu;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author souma
 */
public class ListetournoisController implements Initializable {
    public static Tournoi currentAbo;

    @FXML
    private ListView<Tournoi> txtlistusers;
    @FXML
    private Button btngetback;
    
    @FXML
    private AnchorPane mainPane;
     private ServiceTournoi rs = new ServiceTournoi();
    @FXML
    private TextField rechrche;
    @FXML
    private Button editButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        ObservableList<Tournoi> items =FXCollections.observableArrayList();
        List<Tournoi> listuser = rs.ShowTournoi();
        for(Tournoi r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }    

    @FXML
    private void prepareUD(MouseEvent event) {
    }
   
    @FXML
     private void loadMenu(ActionEvent event) {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuTournoi.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deletetournoi(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer ce Tournoi ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
        rs.DeleteTournoi(txtlistusers.getSelectionModel().getSelectedItem().getId());
        }
      ObservableList<Tournoi> items =FXCollections.observableArrayList();
        List<Tournoi> listT = rs.ShowTournoi();
        for(Tournoi r : listT) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    
    }
     @FXML
    private void chercherUser(KeyEvent event) {
       
       
      List<Tournoi> listuser;
        String tchoix=rechrche.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listuser = rs.cherchetournoi(nchoix);
        } catch (NumberFormatException e) {
            listuser = rs.cherchetournoi(tchoix);
        }
          ObservableList<Tournoi> items =FXCollections.observableArrayList();
       for(Tournoi r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void triee(ActionEvent event) {
         ObservableList<Tournoi> items =FXCollections.observableArrayList();
        List<Tournoi> listuser = rs.ShowTournoi();
       Set<Tournoi> liste= rs.tripardate(listuser);
       for(Tournoi r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void modifierT(ActionEvent event) {
        currentAbo=txtlistusers.getSelectionModel().getSelectedItem();
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir modifier ce Tournoi ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("ModifierTournoi.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

    

    
}
