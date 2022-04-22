/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

import Entities.Equipe;
import Entities.Tournoi;
import Entities.Jeu;
import Front.MainWindowController;
import static GUI.Tournoi.ListetournoisController.currentAbo;
import Services.ServiceTournoi;
import Services.ServiceJeu;
import Tools.Constants;
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
public class ListejeuxController implements Initializable {
    public static Jeu currentAbo;

    @FXML
    private ListView<Jeu> txtlistusers;
    private Button btngetback;
    private ChoiceBox<Integer> comboBoxID;
    @FXML
    private AnchorPane mainPane;
     private ServiceJeu rs = new ServiceJeu();
    @FXML
    private Button btngetback1;
    @FXML
    private TextField rechrche;
    @FXML
    private Button editButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        ObservableList<Jeu> items =FXCollections.observableArrayList();
        List<Jeu> listuser = rs.ShowJeu();
        for(Jeu r : listuser) {
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
//            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deletejeu(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer ce Jeu ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if(txtlistusers.getSelectionModel().getSelectedItem()!=null){                      
        rs.DeleteJeu(txtlistusers.getSelectionModel().getSelectedItem().getId());
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
            }
            
            
      ObservableList<Jeu> items =FXCollections.observableArrayList();
        List<Jeu> listT = rs.ShowJeu();
        for(Jeu r : listT) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
   
    }

    @FXML
    private void chercherUser(KeyEvent event) {
     
      List<Jeu> listuser;
        String tchoix=rechrche.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listuser = rs.cherchejeu(nchoix);
        } catch (NumberFormatException e) {
            listuser = rs.cherchejeu(tchoix);
        }
          ObservableList<Jeu> items =FXCollections.observableArrayList();
       for(Jeu r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void triee(ActionEvent event) {
         ObservableList<Jeu> items =FXCollections.observableArrayList();
        List<Jeu> listuser = rs.ShowJeu();
       Set<Jeu> liste= rs.tripardate(listuser);
       for(Jeu r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void modifierT(ActionEvent event) {
        

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir modifier ce Jeu ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if(txtlistusers.getSelectionModel().getSelectedItem()!=null){                      
        currentAbo=txtlistusers.getSelectionModel().getSelectedItem();
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
                                  
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("ModifierJeu.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
//            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }            
            }
         
    }

    

    
}
