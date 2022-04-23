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
                sendEmail = new SendEmail("gclaimpidev@gmail.com", "Gclaim2022", US.currentUser.getEmail(), "Desactiver votre compte ", "<div style=\"color: #c24400\"> <h1>Bonjour! " + US.currentUser.getUsername() +" </h1> </div> <h3> Voici un CODE pour desactiver votre compte : " + generatedCode + "\n </h3> <div style=\"color: #c24400\"> <p>Cordialement </p> <p>	Gclaim by BITS&BAYTES</p> <img src=\"C:/xampp/htdocs/GClaimDesktop/Gclaim_desktop/src/GUI/src_image/Logo.png \"> </div>");
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
