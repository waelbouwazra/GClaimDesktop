/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.Utilisateur;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author souma
 */
public class AfficheCoachsController implements Initializable {

   @FXML
    private ListView<Utilisateur> txtlistusers;
    private ServiceUser rs = new ServiceUser();
    private ChoiceBox<Integer> comboBoxID;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btngetback;
    @FXML
    private RadioButton coachaactives;
    @FXML
    private RadioButton coachdesactives;
    @FXML
    private RadioButton tout;
    @FXML
    private Button addcoach;
    @FXML
    private Pane descactivepane;
    @FXML
    private Button activepane;
    @FXML
    private TextField nbruser;
    @FXML
    private TextField rechrche;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coachdesactives.setSelected(false);
             tout.setSelected(true);
                 coachaactives.setSelected(false);
                   activepane.setVisible(false);
                descactivepane.setVisible(false);
        ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleCoach();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
  nbruser.setText(rs.nbrprod(listuser));
    }    

    @FXML
    private void prepareUD(MouseEvent event) {
    }
    @FXML
    private void deleteuser(ActionEvent event) {
      rs.DeleteUser(txtlistusers.getSelectionModel().getSelectedItem().getId());
      ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleCoach();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }
    @FXML
     private void loadMenu(ActionEvent event) {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("menuuser.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addcoach(ActionEvent event) {
          AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("AjouterCoach.fxml"));
            
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void coachaactives(ActionEvent event) {
        coachdesactives.setSelected(false);
             tout.setSelected(false);
             descactivepane.setVisible(true);
           activepane.setVisible(false);
         ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichecoachsactiver();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

    @FXML
    private void coachdesactives(ActionEvent event) {
         tout.setSelected(false);
         activepane.setVisible(true);
           coachaactives.setSelected(false);
            descactivepane.setVisible(false);
              
        ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichecoachdesactiver();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

    @FXML
    private void initialize(ActionEvent event) {
              coachdesactives.setSelected(false);
             tout.setSelected(true);
                 coachaactives.setSelected(false);
                   activepane.setVisible(false);
                descactivepane.setVisible(false);
          ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleCoach();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

    @FXML
    private void desactiveruncoach(ActionEvent event) {
         tout.setSelected(false);
         activepane.setVisible(true);
           coachaactives.setSelected(false);
            descactivepane.setVisible(false);
              coachdesactives.setSelected(true);
        rs.desactiveruncompte(txtlistusers.getSelectionModel().getSelectedItem().getId());
         ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichecoachdesactiver();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

    @FXML
    private void activeuncoach(ActionEvent event) {
         coachdesactives.setSelected(false);
             tout.setSelected(false);
             descactivepane.setVisible(true);
           activepane.setVisible(false);
             coachaactives.setSelected(true);
          rs.activeruncompte(txtlistusers.getSelectionModel().getSelectedItem().getId());
         ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichecoachsactiver();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

    @FXML
    private void triee(ActionEvent event) {
        ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleCoach();
       Set<Utilisateur> liste= rs.tri(listuser);
       for(Utilisateur r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void chercherUser(KeyEvent event) {
         ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.chercheCpoch(rechrche.getText());
       for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }
}
