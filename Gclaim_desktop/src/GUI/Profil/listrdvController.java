/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;

import Entities.Rdv;
import GUI.Login.*;
import Entities.Utilisateur;
import Services.ProfilService;
import Services.RdvService;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
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
    private Pane descactivepane;
    private Button activepane;

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
    private void deleteuser(ActionEvent event) {
        rs.DeleteProfil(txtlistusers.getSelectionModel().getSelectedItem().getId());
         ObservableList<Rdv> items =FXCollections.observableArrayList();
        List<Rdv> listuser = rs.ShowRdv();
        for(Rdv r : listuser) {
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
    private void coachaactives(MouseEvent event) {
        coachdesactives.setSelected(false);
             tout.setSelected(false);
             descactivepane.setVisible(true);
           activepane.setVisible(false);
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
           coachdesactives.setSelected(false);
             tout.setSelected(false);
             descactivepane.setVisible(true);
           activepane.setVisible(false);
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
                   activepane.setVisible(false);
                descactivepane.setVisible(false);
         ObservableList<Rdv> items =FXCollections.observableArrayList();
        List<Rdv> listuser = rs.ShowRdv();
        for(Rdv r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    }

}
