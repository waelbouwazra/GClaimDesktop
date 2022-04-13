/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.*;
import java.net.URL;
import java.util.List;
import Services.*;
import java.io.IOException;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author souma
 */
public class AffichetusersController implements Initializable {

    @FXML
    private ListView<Utilisateur> txtlistusers;
    private ServiceUser rs = new ServiceUser();
    @FXML
    private ChoiceBox<Integer> comboBoxID;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btngetback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleUser();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
      ObservableList<Integer> langs = FXCollections.observableArrayList(rs.getIDUSER());
        comboBoxID.setItems(langs);
    }    

    @FXML
    private void prepareUD(MouseEvent event) {
    }
    @FXML
    private void deleteuser(ActionEvent event) {
      rs.DeleteUser(comboBoxID.getValue());
      ObservableList<Utilisateur> items =FXCollections.observableArrayList();
        List<Utilisateur> listuser = rs.afficheSimpleUser();
        for(Utilisateur r : listuser) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtlistusers.setItems(items);
    ObservableList<Integer> langs = FXCollections.observableArrayList(rs.getIDUSER());
        comboBoxID.setItems(langs);
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

}
