/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.*;
import Services.*;
import Tools.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Hassen Chouadah
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField nomtxt;
    @FXML
    private TextField prenomtxt;
    @FXML
    private TextField emailtxt;
    
   
    @FXML
    private TextField mdptxt;
   
    @FXML
    private PasswordField confirmationpassword;
    @FXML
    private Button Confirmerinscription;
    private ServiceUser US;
   
    @FXML
    private Label codeTxt;
    int generatedCode;
    @FXML
    private AnchorPane anchorHide;
    @FXML
    private Button confirmerCodebtn;
    @FXML
    private TextField codeActivation;
    @FXML
    private TextArea messageCode;
    @FXML
    private Pane voiturePane;
    @FXML
    private Button quitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        US = new ServiceUser();
        generatedCode = 0;
     
        
    }

    @FXML
    private void SignUpAction(ActionEvent event) {
        System.out.println(emailtxt.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" ));
        if (nomtxt.getText().isEmpty() == false
                && prenomtxt.getText().isEmpty() == false
                && emailtxt.getText().isEmpty() == false && emailtxt.getText().matches("(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)*\\@(?:\\w|[\\-_])+(?:\\.(?:\\w|[\\-_])+)+" )&& mdptxt.getText().isEmpty() == false
                && confirmationpassword.getText().isEmpty() == false && mdptxt.getText().equals(confirmationpassword.getText())) {
            SimpleUtilisateur u = new SimpleUtilisateur(0,nomtxt.getText(),mdptxt.getText(),confirmationpassword.getText(), emailtxt.getText(),prenomtxt.getText());
            if (US.getUtilisateurByEmail(emailtxt.getText()) == true) {
               addNotifications("erreur", "Un compte lié à cette adresse est déjà crée");
                System.out.println("utilisateur existe deja (email)");
            } else if (US.getUtilisateurByCin(nomtxt.getText()) == true) {
                addNotifications("erreur", "Un compte lié à cet nom est déjà crée");
                System.out.println("utilisateur existe deja (cin)");
            } else {
                int resultat = US.ajouterPersonne(u);
                 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));

        try {

            Parent root = loader.load();

            quitBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
                
            }

        } else {
           addNotifications("erreur", "veuillez remplir correctement les champs");
                    }
    }

    

    private void addNotifications(String title, String content) {

        if (null != content) {
            if (content.length() > 50) {
                content = content.substring(0, 49) + "......";
            }
        }
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(content)
                .hideAfter(Duration.seconds(31536000))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }

    
    @FXML
    private void confirmCode(ActionEvent event) {
    }

    @FXML
    private void LoginRedirect(MouseEvent event) {
    }

  
}
