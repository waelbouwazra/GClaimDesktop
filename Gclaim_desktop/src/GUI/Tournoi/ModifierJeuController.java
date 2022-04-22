/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

import Entities.Jeu;
import Entities.Tournoi;
import static GUI.Tournoi.ModifierJeuController.currentAbo;
import static GUI.Tournoi.ModifierTournoiController.currentAbo;
import Services.ServiceJeu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author elyes
 */
public class ModifierJeuController implements Initializable {
    public static Jeu currentAbo;
    ServiceJeu rs=new ServiceJeu();
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Text topText;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtNOM;
    @FXML
    private Button btnmodif;
    @FXML
    private TextField txtCreateur;
    @FXML
    private Button btngetback;
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
        
        currentAbo=ListejeuxController.currentAbo;
        txtNOM.setText(currentAbo.getNomjeu());
        txtCreateur.setText(currentAbo.getCreateur());
        txtDescription.setText(currentAbo.getDescription());    }    

    @FXML
    private void Modifier_votre_Tournoi(ActionEvent event) {
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
            Jeu e=new Jeu(currentAbo.getId(),txtNOM.getText(),txtDescription.getText(),txtCreateur.getText());
           
     

            rs.UpdateJeu(e,currentAbo.getId());
            alertN.setText("");
            alertD.setText("");
            alerc.setText("");
            AnchorPane pane; 
            try {
            pane = FXMLLoader.load(getClass().getResource("listejeux.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
          //  btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }        }
        else
             JOptionPane.showMessageDialog(null, "Le JEU EST INVALIDE");
    }
    }
       @FXML
    private void loadMenu(ActionEvent event) {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuTournoi.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            //btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
