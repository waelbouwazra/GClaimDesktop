/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.*;
import Front.MainWindowController;
import static GUI.FXMain.mainStage;
import Services.*;
import static Services.ServiceUser.currentUser;

import Tools.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Hassen Chouadah
 */
public class ModifierprofilController implements Initializable {

    @FXML
    private TextField TelText;
    
    @FXML
    private TextField CinTxt;
    @FXML
    private TextField EmailTxt;
    private PasswordField MdpTxt;

    static AnchorPane staticContent;
    private String imgName;
    private Path imgPath;
    private ServiceUser US;
    private LocalStorage localStorage;
    @FXML
    private PasswordField oldpwtxt;
    @FXML
    private PasswordField newpwTxt;
    @FXML
    private PasswordField newpwTxt2;
    @FXML
    private Pane ModalPane;
    @FXML
    private Label UserRole;
    @FXML
    private Label username;
    @FXML
    private Button creerequipe;
    @FXML
    private Button desactivercompte;
    @FXML
    private Button modifierprofile;
    @FXML
    private Label username1;
    @FXML
    private Button devenircoach;
    private AnchorPane mainPane;
    @FXML
    private Button LOG_OUT;
    private Button LOG_OUT1;
    @FXML
    private AnchorPane mainPain;
    @FXML
    private Pane devenircoachPane;
    @FXML
    private Button ProfilCoach;

    private Pane supppane;
    @FXML
    private Pane creercoach;
     int generatedCode;
       private SendEmail sendEmail;
    @FXML
    private Pane code;
    @FXML
    private Button confirmer;
    @FXML
    private TextField codeInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        US = new ServiceUser();
        devenircoachPane.setVisible(false);
                 generatedCode = 0;
                 code.setVisible(false);
        try {
            localStorage = new LocalStorage();
        } catch (IOException ex) {
            System.out.println("error init localstorage");
        }
        TelText.setText(currentUser.getUsername());
        username.setText(currentUser.getUsername());
        EmailTxt.setText(currentUser.getEmail());
        if (currentUser.getRoles().equals("[\"ROLE_USER\"]")&& currentUser.isRole() == false) {
            UserRole.setText("simpleUtilisateur");
            devenircoachPane.setVisible(true);
            creercoach.setVisible(false);
            CinTxt.setText(((SimpleUtilisateur)currentUser).getFullname());
            
        } else if (currentUser.getRoles().equals("[\"ROLE_COACH\"]")) {
            UserRole.setText("Coach");
            devenircoachPane.setVisible(false);
            creercoach.setVisible(true);
                        CinTxt.setText(((Coach)currentUser).getSpecialite());
        }

        ModalPane.setVisible(false);

    }

    @FXML
    private void ModifierInfromations(ActionEvent event) {
        if (!TelText.getText().isEmpty() && !CinTxt.getText().isEmpty() && !EmailTxt.getText().isEmpty()) {
            if (US.currentUser.getRoles().equals("[\"ROLE_USER\"]")) {
                SimpleUtilisateur newUser = new SimpleUtilisateur();
                newUser.setId(US.currentUser.getId());
                newUser.setUsername(US.currentUser.getUsername());
                newUser.setEmail(US.currentUser.getEmail());
   
                newUser.setFullname(CinTxt.getText());

                US.UpdatePersonne(newUser, newUser.getId());
                US.updateCurrentUser(newUser);
                localStorage.deleteStorage();
                try {
                    localStorage.writeToStorage(newUser);
                } catch (IOException ex) {
                    System.out.println("error write to storage");
                }

            } else if (US.currentUser.getRoles().equals("[\"ROLE_COACH\"]")) {
                Coach newUser = new Coach();
                newUser.setId(US.currentUser.getId());
                newUser.setUsername(US.currentUser.getUsername());
                newUser.setEmail(US.currentUser.getEmail());
              
                newUser.setSpecialite(CinTxt.getText());

                US.UpdatePersonne(newUser, newUser.getId());
                US.updateCurrentUser(newUser);
                localStorage.deleteStorage();
                try {
                    localStorage.writeToStorage(newUser);
                } catch (IOException ex) {
                    System.out.println("error write to storage");
                }

            }

        } else {
            addNotifications("erreur", "Remplir les champs correctement");
        }
    }

    @FXML
    private void ProfilRedirect(ActionEvent event) {
    }

    @FXML
    private void closeModal(ActionEvent event) {
        ModalPane.setVisible(false);
    }

    private void openModal(ActionEvent event) {
        ModalPane.setVisible(true);
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
                .hideAfter(Duration.seconds(360))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }

   

    @FXML
    private void desactivervotrecompte(ActionEvent event) {
     
        try {
                int min = 10000;
                int max = 99999;

                generatedCode = (int) Math.floor(Math.random() * (max - min + 1) + min);
                sendEmail = new SendEmail("gclaimpidev@gmail.com", "Gclaim2022", US.currentUser.getEmail(), "Desactiver votre compte ", "<center style=\"width: 100%; background-color: #f1f1f1;\"><div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\"></div><div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">"
                + "  <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\"border=\"0\" width=\"100% \" style=\"margin: auto;\"><tr>"
                + "<td valign=\"top\" class=\"bg_white\" style=\"padding: 1em 2.5em 0 2.5em;\">"
                + "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr>"
                + "<td class=\"logo\" style=\"text-align: center; color: #c24400;\" ><h1>G_CLAIM</a></h1></td></tr></table></td> </tr><br> <br>"
              
                + " <tr> <td valign=\"middle\" class=\"hero bg_white\" style=\"background-image: url('https://www.coastline.edu/_files/img/750-421/esports-fist-bump.jpg');opacity: 0.88; background-size: cover; height: 400px;\">"
                + "<div class=\"overlay\"></div> <table><tr><td><div class=\"text\" style=\"padding: 0 4em; text-align: center;\">"
                + "<h2 >A PROPOS DE G_CLAIM</h2><h4 style=\"color: #ffffff;\" +\">G-Claim est Bien évidemment les MOBA comme DOTA et STR sont les incontournables du esport car ce sont les jeux qui rassemblent la plus grosse communauté de joueurs. Vous ne serez donc pas surpris de pourvoir parier sur des matches de League of Legends, Dota 2 et Starcraft. Ce trio est proposé par les meilleurs bookmakers du monde.</h4>"
                + "</div></td></tr> </table></td> </tr>"
                + "<tr> <td valign=\"middle\" class=\"intro bg_white\" style=\"padding: 2em 0 4em 0;\"><table><tr><td><div class=\"text\" style=\"padding: 10 2.5em; text-align: center; margin-left:500\">"
                + "<h2 style=\"margin-left:150px;\" +\" >Récupérer votre mot de passe</h2><h2 style=\"margin-left:150px;\" +\">Bonjour!" + US.currentUser.getUsername() +" </h2><p  style=\"margin-left:150px;\" +\" >Voici le code pour confirmer la désactivation de votre compte</p>"
                + "<p><a  style=\"margin-left:150px;color: #c24400;\" +\">" + generatedCode + "</a></p></div></td></tr></table> </td></tr>"
                + " </center>"); 
                
               code.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

    }

    @FXML
    private void DemandededevenirCoach(ActionEvent event) {
        
            US.demandedevenircoach(currentUser);
            JOptionPane.showMessageDialog(null, "Demande de devenir coach est en cours de traitement");
            //addNotifications("Demande", "Demande de devenir coach est en cours de traitement");

      

    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
       US.logOut();
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Login.fxml"));
            Parent root = loader.load();
           
            LOG_OUT.getScene().setRoot(root);
    }

    @FXML
    private void CreerEquipe(ActionEvent event) {

        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Creerequipe.fxml"));

            mainPain.getChildren().setAll(pane);
            //defaultStateButtons();
            creerequipe.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @FXML
    private void initialize(MouseEvent event) {
    }

    private void supp(MouseEvent event) {
        supppane.setVisible(false);
    }

    @FXML
    private void ProfilCoach(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("../Profil/AjouterProfil.fxml"));
            mainPain.getChildren().setAll(pane);
            //defaultStateButtons();
            LOG_OUT1.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmercode(ActionEvent event) throws IOException {
        if (Integer.toString(generatedCode).equals(codeInput.getText())) {
            System.out.println("code correct");
            US.desactiveruncompte(currentUser.getId());
            US.logOut();
         US.logOut();
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Login.fxml"));
            Parent root = loader.load();
           
            LOG_OUT.getScene().setRoot(root);
       
        } else {
            addNotifications("erreur", "code incorrect");
        }
    }
}
