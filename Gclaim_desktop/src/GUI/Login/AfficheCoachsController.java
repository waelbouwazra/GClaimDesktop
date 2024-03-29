/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.Coach;
import Entities.Utilisateur;
import Front.MainWindowController;
import static GUI.Tournoi.ListejeuxController.currentAbo;
import Services.ServiceUser;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

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
    @FXML
    private Pane listecoach;
    @FXML
    private Pane modifcoach;
    @FXML
    private PasswordField mdp;
    @FXML
    private PasswordField mdp1;
    @FXML
    private TextField specialite;
    @FXML
    private TextField email;
    @FXML
    private Button btngetback1;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField username;
    @FXML
    private Label nomHebr;
    ServiceUser US = new ServiceUser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listecoach.setVisible(true);
        modifcoach.setVisible(false);
        coachdesactives.setSelected(false);
        tout.setSelected(true);
        coachaactives.setSelected(false);
        activepane.setVisible(false);
        descactivepane.setVisible(false);
        ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleCoach();
        for (Utilisateur r : listuser) {
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
        alert.setContentText("Voulez-vous vraiment supprimer coach");
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
             ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleCoach();
        for (Utilisateur r : listuser) {
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
        ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichecoachsactiver();
        for (Utilisateur r : listuser) {
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

        ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichecoachdesactiver();
        for (Utilisateur r : listuser) {
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
        ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleCoach();
        for (Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }

        txtlistusers.setItems(items);
    }

    @FXML
    private void desactiveruncoach(ActionEvent event) {
        if(txtlistusers.getSelectionModel().getSelectedItem()!=null){ 
        tout.setSelected(false);
        activepane.setVisible(true);
        coachaactives.setSelected(false);
        descactivepane.setVisible(false);
        coachdesactives.setSelected(true);
            if(txtlistusers.getSelectionModel().getSelectedItem()!=null){                      
            rs.desactiveruncompte(txtlistusers.getSelectionModel().getSelectedItem().getId());
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
        ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichecoachdesactiver();
        for (Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }

        txtlistusers.setItems(items);}else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
    }

    @FXML
    private void activeuncoach(ActionEvent event) {
        if(txtlistusers.getSelectionModel().getSelectedItem()!=null){ 
        coachdesactives.setSelected(false);
        tout.setSelected(false);
        descactivepane.setVisible(true);
        activepane.setVisible(false);
        coachaactives.setSelected(true);
         if(txtlistusers.getSelectionModel().getSelectedItem()!=null){                      
        rs.activeruncompte(txtlistusers.getSelectionModel().getSelectedItem().getId());
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
        ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.affichecoachsactiver();
        for (Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }

        txtlistusers.setItems(items);
        }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
    }

    @FXML
    private void triee(ActionEvent event) {
        ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleCoach();
        Set<Utilisateur> liste = rs.tri(listuser);
        for (Utilisateur r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void chercherUser(KeyEvent event) {
        ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.chercheCpoch(rechrche.getText());
        for (Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void modifiercozch(ActionEvent event) {
         if(txtlistusers.getSelectionModel().getSelectedItem()!=null){ 
 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de modifier coach ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
             if(txtlistusers.getSelectionModel().getSelectedItem()!=null){  
                 listecoach.setVisible(false);
            modifcoach.setVisible(true);
            username.setText(txtlistusers.getSelectionModel().getSelectedItem().getUsername());
            email.setText(txtlistusers.getSelectionModel().getSelectedItem().getEmail());
            Coach c = (Coach) txtlistusers.getSelectionModel().getSelectedItem();
            specialite.setText(c.getSpecialite());
            mdp.setText(txtlistusers.getSelectionModel().getSelectedItem().getPassword());
            mdp1.setText(txtlistusers.getSelectionModel().getSelectedItem().getVerifpassword());            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
           
        
        
            
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
    private void update(ActionEvent event) {
        if (username.getText().isEmpty() == false
                && email.getText().isEmpty() == false
                && mdp.getText().isEmpty() == false && specialite.getText().isEmpty() == false && email.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+")
                && mdp.getText().equals(mdp1.getText())) {
           
                Coach u = new Coach(txtlistusers.getSelectionModel().getSelectedItem().getId(), username.getText(), mdp.getText(), mdp1.getText(), email.getText(), specialite.getText());

            
                US.UpdatePersonne(u,u.getId());
         

                System.out.println("ajout");
                JOptionPane.showMessageDialog(null, "update DONE");
 listecoach.setVisible(true);
        modifcoach.setVisible(false);
        ObservableList<Utilisateur> items = FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleCoach();
        for (Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }

        txtlistusers.setItems(items);
            
        }
    }
}
