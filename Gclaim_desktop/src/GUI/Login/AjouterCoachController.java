/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.Coach;
import Entities.SimpleUtilisateur;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
public class AjouterCoachController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField specialite;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btngetback;
    @FXML
    private Label nomHebr;
    @FXML
    private TextField mdp;
    @FXML
    private TextField mdp1;
    private ServiceUser US;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          US = new ServiceUser();
    }    

    @FXML
    private void addcoach(ActionEvent event) {
          if (username.getText().isEmpty() == false
                && email.getText().isEmpty() == false
                && mdp.getText().isEmpty() == false && specialite.getText().isEmpty() == false ) {
            Coach u = new Coach(0,username.getText(),mdp.getText(), mdp1.getText(),email.getText(),specialite.getText());
            
         if(email.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )==false)
         {
           JOptionPane.showMessageDialog(null, "erreur !!! remplir Correctement le champs EMAIL,EMAIL incorrect");
         }
         else if (mdp.getText().equals(mdp1.getText()) == false) {
                JOptionPane.showMessageDialog(null, "mot de passe incompatible");
                
            }
         else if (US.getUtilisateurByEmail(email.getText()) == true) {
                JOptionPane.showMessageDialog(null, "Un compte lié à cette adresse est déjà crée");
                System.out.println("utilisateur existe deja (email)");
            } else if (US.getUtilisateurByCin(username.getText()) == true) {
                JOptionPane.showMessageDialog(null, "Un compte lié à cet nom est déjà crée");
                System.out.println("utilisateur existe deja (username)");
            } else {
                int resultat = US.ajouterPersonne(u);

                System.out.println("ajout");
                 JOptionPane.showMessageDialog(null, "AJOUT DONE");
                 AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("afficheCoachs.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
            }

        } else {
           JOptionPane.showMessageDialog(null, "erreur !!! remplir  les champs");
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
    
}
