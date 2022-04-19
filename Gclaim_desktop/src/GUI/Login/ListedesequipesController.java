/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.Equipe;
import Entities.Utilisateur;
import Services.ServiceEquipe;
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
public class ListedesequipesController implements Initializable {

    @FXML
    private ListView<Equipe> txtlistusers;
    @FXML
    private Button btngetback;
   
    @FXML
    private AnchorPane mainPane;
     private ServiceEquipe rs = new ServiceEquipe();
    @FXML
    private TextField rechrche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Equipe> items =FXCollections.observableArrayList();
        List<Equipe> listuser = rs.afficheEquipe();
        for(Equipe r : listuser) {
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
    private void deleteequipe(ActionEvent event) {
        rs.DeleteEquipe(txtlistusers.getSelectionModel().getSelectedItem().getId());
      ObservableList<Equipe> items =FXCollections.observableArrayList();
        List<Equipe> listuser = rs.afficheEquipe();
        for(Equipe r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    
    }

    @FXML
    private void chercherUser(KeyEvent event) {
       
       
      List<Equipe> listuser;
        String tchoix=rechrche.getText();
        try{
            int nchoix = Integer.parseInt(tchoix);
            listuser = rs.chercheequipe(nchoix);
        } catch (NumberFormatException e) {
            listuser = rs.chercheequipe(tchoix);
        }
          ObservableList<Equipe> items =FXCollections.observableArrayList();
       for(Equipe r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    @FXML
    private void triee(ActionEvent event) {
         ObservableList<Equipe> items =FXCollections.observableArrayList();
        List<Equipe> listuser = rs.afficheEquipe();
       Set<Equipe> liste= rs.tripardate(listuser);
       for(Equipe r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        txtlistusers.setItems(items);
    }

    

    
}
