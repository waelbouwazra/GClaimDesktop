/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

import Entities.Jeu;
import Services.ServiceJeu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author elyes
 */
public class CreerJeuController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtNOM;
    @FXML
    private Button btngetback;
    @FXML
    private TextField txtCreateur;
    @FXML
    private Label nomHebr;
    ServiceJeu rs = new ServiceJeu();
    @FXML
    private Label alertN;
    @FXML
    private Label alertD;
    @FXML
    private Label alerc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void addJeu(ActionEvent event) {
        alertN.setText("");
        alertD.setText("");
        alerc.setText("");
        Boolean verif = true;

        if (txtNOM.getText().equals("")) {
            alertN.setText("Remplir le champs !!");
            verif = false;
        } else if (txtNOM.getText().length() <= 8) {
            alertN.setText("Nom Jeu peut contenir au moins 8 caractéres !!");
            verif = false;
        }
        
         if (txtDescription.getText().equals("")) {
            alertD.setText("Remplir ce champs !!");
            verif = false;
        } 
          if (txtCreateur.getText().equals("")) {
            alerc.setText("Remplir ce champs !!");
            verif = false;
        } 
        if (verif == true) {
        if (!txtNOM.getText().isEmpty() && !txtDescription.getText().isEmpty() && !txtCreateur.getText().isEmpty())
        {
            Jeu e= new Jeu( txtNOM.getText(), txtDescription.getText(), txtCreateur.getText());   
            rs.AddJeuPst(e);
            alertN.setText("");
            alertD.setText("");
            alerc.setText("");
            AnchorPane pane; 
            try {
            pane = FXMLLoader.load(getClass().getResource("listejeux.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
           // btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
             JOptionPane.showMessageDialog(null, "Le JEU EST INVALIDE");
    }
    }
    
}
