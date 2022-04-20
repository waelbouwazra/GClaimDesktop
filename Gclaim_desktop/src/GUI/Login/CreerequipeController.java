/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.*;
import Front.MainWindowController;
import Services.*;
import Tools.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author souma
 */
public class CreerequipeController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    private ServiceEquipe US;
    @FXML
    private TextField email;
    @FXML
    private ChoiceBox<String> etat;
    @FXML
    private TextField username;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btngetback;
    @FXML
    private Label nomHebr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> langs = FXCollections.observableArrayList("open", "close");
        etat.setItems(langs);
        US = new ServiceEquipe();
    }

    @FXML
    private void addcoach(ActionEvent event) {
        if (username.getText().isEmpty() == false
                && email.getText().isEmpty() == false) {
            Equipe u = new Equipe(0, username.getText(), email.getText(), etat.getValue(), ServiceUser.currentUser.getUsername(), ServiceUser.currentUser);

            US.AddEquipe(u);

            System.out.println("ajout");
            JOptionPane.showMessageDialog(null, "AJOUT Equipe  DONE");
              MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_ABO);

        } else {
            JOptionPane.showMessageDialog(null, "erreur !!! remplir Correctement les champs");
        }

    }

    @FXML
    private void loadMenu(ActionEvent event) {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("modifierprofil.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
