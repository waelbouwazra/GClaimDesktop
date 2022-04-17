/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.*;
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
    private TextField AdresseTxt;
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
    @FXML
    private Button LOG_OUT1;
    @FXML
    private AnchorPane mainPain;
    @FXML
    private Pane devenircoachPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        US = new ServiceUser();
        devenircoachPane.setVisible(false);
                
        try {
            localStorage = new LocalStorage();
        } catch (IOException ex) {
            System.out.println("error init localstorage");
        }
        TelText.setText(currentUser.getUsername());
        AdresseTxt.setText(currentUser.getPassword());
        username.setText(currentUser.getUsername());
        EmailTxt.setText(currentUser.getEmail());
        if (currentUser.getRoles().equals("[\"ROLE_USER\"]")&& currentUser.isRole() == true) {
            UserRole.setText("simpleUtilisateur");
            devenircoachPane.setVisible(true);
        } else if (currentUser.getRoles().equals("[\"ROLE_COACH\"]")) {
            UserRole.setText("Coach");
            devenircoachPane.setVisible(false);
        }

        ModalPane.setVisible(false);

    }

    @FXML
    private void ModifierInfromations(ActionEvent event) {
        if (!TelText.getText().isEmpty() && !AdresseTxt.getText().isEmpty() && !CinTxt.getText().isEmpty() && !EmailTxt.getText().isEmpty()) {
            if (US.currentUser.getRoles().equals("[\"ROLE_USER\"]")) {
                SimpleUtilisateur newUser = new SimpleUtilisateur();
                newUser.setId(US.currentUser.getId());
                newUser.setUsername(US.currentUser.getUsername());
                newUser.setEmail(US.currentUser.getEmail());
                newUser.setVerifpassword(AdresseTxt.getText());
                newUser.setPassword(AdresseTxt.getText());
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
                newUser.setVerifpassword(AdresseTxt.getText());
                newUser.setPassword(AdresseTxt.getText());
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
            System.out.println("remplir les champs");
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
        US.desactiveruncompte(currentUser.getId());
        US.logOut();
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            desactivercompte.setTextFill(Color.WHITE);
            desactivercompte.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void DemandededevenirCoach(ActionEvent event) {
        
            US.demandedevenircoach(currentUser);
            JOptionPane.showMessageDialog(null, "Demande de devenir coach est en cours de traitement");
            //addNotifications("Demande", "Demande de devenir coach est en cours de traitement");

      

    }

    @FXML
    private void logout(ActionEvent event) {
        US.logOut();
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
            mainPain.getChildren().setAll(pane);
            //defaultStateButtons();
            LOG_OUT.setTextFill(Color.WHITE);
            LOG_OUT.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    private void accueil(ActionEvent event) {
        
       AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("../MenuFront.fxml"));
            mainPain.getChildren().setAll(pane);
            //defaultStateButtons();
            LOG_OUT1.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void initialize(MouseEvent event) {
    }
}
