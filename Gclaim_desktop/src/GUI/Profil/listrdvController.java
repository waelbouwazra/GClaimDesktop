/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;

import Entities.Equipe;
import Entities.Rdv;
import GUI.Login.*;
import Entities.Utilisateur;
import static GUI.Tournoi.ListejeuxController.currentAbo;
import Services.ProfilService;
import Services.RdvService;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
public class listrdvController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private ListView<Rdv> txtlistusers;
    @FXML
    private Button btngetback;
    @FXML
    private RadioButton coachaactives;
    @FXML
    private RadioButton coachdesactives;
    @FXML
    private RadioButton tout;
     private RdvService rs = new RdvService();
  
   
    @FXML
    private TextField rechrche;
    @FXML
    private Pane descactivepane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             coachdesactives.setSelected(false);
             tout.setSelected(true);
                 coachaactives.setSelected(false);
                   
        ObservableList<Rdv> items =FXCollections.observableArrayList();
        List<Rdv> listuser = rs.ShowRdv();
        for(Rdv r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }    

    @FXML
    private void prepareUD(MouseEvent event) {
    }


    @FXML
    private void loadMenu(ActionEvent event) throws IOException {
          
       FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../Menu.fxml"));
            Parent root = loader.load();
           
            rechrche.getScene().setRoot(root);
    }

  

    @FXML
    private void coachaactives(MouseEvent event) {
       coachdesactives.setSelected(false);
             tout.setSelected(false);
                 coachaactives.setSelected(true);
             
          ObservableList<Rdv> items =FXCollections.observableArrayList();
        List<Rdv> listuser = rs.ShowRdvVerified();
        for(Rdv r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

    @FXML
    private void coachdesactives(MouseEvent event) {
          coachdesactives.setSelected(true);
             tout.setSelected(false);
                 coachaactives.setSelected(false);
             
          ObservableList<Rdv> items =FXCollections.observableArrayList();
        List<Rdv> listuser = rs.ShowRdvNotVerified();
        for(Rdv r : listuser) {
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
                 
         ObservableList<Rdv> items =FXCollections.observableArrayList();
        List<Rdv> listuser = rs.ShowRdv();
        for(Rdv r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

    @FXML
    private void triee(ActionEvent event) {
         ObservableList<Rdv> items =FXCollections.observableArrayList();
        List<Rdv> listuser = rs.ShowRdv();
       Set<Rdv> liste= rs.tripardate(listuser);
       for(Rdv r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void chercherUser(KeyEvent event) {
          
      List<Rdv> listuser;
        String tchoix=rechrche.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listuser = rs.chercheequipe(nchoix);
        } catch (NumberFormatException e) {
            listuser = rs.chercheequipe(tchoix);
        }
          ObservableList<Rdv> items =FXCollections.observableArrayList();
       for(Rdv r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void deleteuser(ActionEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer ce rendez-vous ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if(txtlistusers.getSelectionModel().getSelectedItem()!=null){                      
            System.out.println(txtlistusers.getSelectionModel().getSelectedItem().getId());
             rs.DeleteProfil(txtlistusers.getSelectionModel().getSelectedItem().getId());            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
        }
       
        
         ObservableList<Rdv> items =FXCollections.observableArrayList();
        List<Rdv> listuser = rs.ShowRdv();
        for(Rdv r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items); 
    }
}
