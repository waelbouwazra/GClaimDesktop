/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.Coach;
import Entities.Utilisateur;
import Services.ServiceUser;
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
import javafx.scene.control.Label;
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
public class AfficheuserController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ListView<Utilisateur> txtlistusers;
    @FXML
    private Button btngetback;
    @FXML
    private RadioButton coachaactives;
    @FXML
    private RadioButton coachdesactives;
    @FXML
    private RadioButton tout;
     private ServiceUser rs = new ServiceUser();
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
        List<Utilisateur> listuser = rs.afficheSimpleUser();
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
        if(txtlistusers.getSelectionModel().getSelectedItem()!=null){ 
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de supprimer user ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if(txtlistusers.getSelectionModel().getSelectedItem()!=null){  
             rs.DeleteUser(txtlistusers.getSelectionModel().getSelectedItem().getId());
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
         ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleUser();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
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
    private void coachaactives(MouseEvent event) {
        coachdesactives.setSelected(false);
             tout.setSelected(false);
             descactivepane.setVisible(true);
           activepane.setVisible(false);
          ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichesimpleutilisateursactiver();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

    @FXML
    private void coachdesactives(MouseEvent event) {
      
           tout.setSelected(false);
         activepane.setVisible(true);
           coachaactives.setSelected(false);
            descactivepane.setVisible(false);
        ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichesimpleutilisateursdesactiver();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

    @FXML
    private void initialize(MouseEvent event) {
           coachdesactives.setSelected(false);
             tout.setSelected(true);
                 coachaactives.setSelected(false);
                   activepane.setVisible(false);
                descactivepane.setVisible(false);
         ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleUser();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
   
    }

    @FXML
    private void desactiveruncoach(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de Desactiver un user?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
             if(txtlistusers.getSelectionModel().getSelectedItem()!=null){  
             tout.setSelected(false);
         activepane.setVisible(true);
           coachaactives.setSelected(false);
            descactivepane.setVisible(false);
              coachdesactives.setSelected(true);
        rs.desactiveruncompte(txtlistusers.getSelectionModel().getSelectedItem().getId());
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
          
         ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichesimpleutilisateursdesactiver();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }}

    @FXML
    private void activeuncoach(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr d'activer user ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
               if(txtlistusers.getSelectionModel().getSelectedItem()!=null){  
           coachdesactives.setSelected(false);
        coachaactives.setSelected(true);
             tout.setSelected(false);
             descactivepane.setVisible(true);
           activepane.setVisible(false);
          rs.activeruncompte(txtlistusers.getSelectionModel().getSelectedItem().getId());
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
        
         ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichesimpleutilisateursactiver();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }}

    @FXML
    private void triee(ActionEvent event) {
        ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleUser();
       Set<Utilisateur> liste= rs.tri(listuser);
       for(Utilisateur r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

   

    @FXML
    private void recherchhhe(ActionEvent event) {
        ObservableList<Utilisateur> items =FXCollections.observableArrayList();
         List<Utilisateur> listuser2 = rs.afficheSimpleUser();
         
        Set<Utilisateur> listuser = rs.Rechercher(listuser2,rechrche.getText());
       for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }
@FXML
    private void chercherUser(KeyEvent event) {
        ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.chercheUtilisateur(rechrche.getText());
       for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }
    
}
