/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.*;
import Services.*;
import static Services.ServiceUser.currentUser;

import Tools.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Hassen Chouadah
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtmail;
    @FXML
    private TextField txtpassword;
    @FXML
    private Button seconnecter;
    @FXML
    private Button sinscrire;
    @FXML
    private Label mdpoublie;
    private Label erreur;
private SendEmail sendEmail;
    
    @FXML
    private TextField emailverif;
    @FXML
    private Label label_email;

    int generatedCode;
    @FXML
    private Pane forgetPasswordPane;
    @FXML
    private TextField codeInput;
    @FXML
    private Pane VerifyCodePane;
    @FXML
    private Pane ChangePasswordPane;
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField newPassword;
    private ServiceUser US;
    private CheckBox rememberMe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        generatedCode = 0;
        forgetPasswordPane.setVisible(false);
        VerifyCodePane.setVisible(false);
        ChangePasswordPane.setVisible(false);
        US = new ServiceUser();
    }

    @FXML
    private void SeConnecter(ActionEvent event) throws IOException {

        String email = txtmail.getText();
        String pwd = txtpassword.getText();
        if (email.isEmpty() || pwd.isEmpty()) {
            addNotifications("erreur", "Les champs sont vides ou incorrects");
           //JOptionPane.showMessageDialog(null, "Les champs sont vides ou incorrects");
        } else {
            ServiceUser us = new ServiceUser();

            String output = us.Seconnecter(email, pwd);

            if (output == "success") {
                if (currentUser.getRoles().equals("[\"ROLE_ADMIN\"]")) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Menu.fxml"));

                    try {
                        Parent root = loader.load();
                        txtmail.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else if (currentUser.getRoles().equals("[\"ROLE_USER\"]") && currentUser.isIsVerfied()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../MenuFront.fxml"));

                    try {
                        Parent root = loader.load();
                        txtmail.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } 
                else if (currentUser.getRoles().equals("[\"ROLE_COACH\"]") && currentUser.isIsVerfied()) {
                    System.out.println(currentUser.isIsVerfied());
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../MenuFront.fxml"));

                    try {
                        Parent root = loader.load();
                        txtmail.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } 
                else {
                     addNotifications("erreur", "L'UTILISATEUR EST INVALIDE");
                   // JOptionPane.showMessageDialog(null, "L'UTILISATEUR EST INVALIDE");
                }
            } else {
               addNotifications("erreur", "Mot de passe ou email invalide");
                //JOptionPane.showMessageDialog(null, "Mot de passe ou email invalide");
            }

        }
    }

    private void setLblError(Color color, String text) {
        erreur.setTextFill(color);
        erreur.setText(text);
        System.out.println(text);
    }

    @FXML
    private void Sinscrire(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));

        try {

            Parent root = loader.load();

            txtmail.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void mdpoublieAction(MouseEvent event) {

        forgetPasswordPane.setVisible(true);
    }

   /*
    

    @FXML
    private void ChangePassword(ActionEvent event) 
*/
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
    private void VerifyCode(ActionEvent event) {
        if (Integer.toString(generatedCode).equals(codeInput.getText())) {
            System.out.println("code correct");

            VerifyCodePane.setVisible(false);
            ChangePasswordPane.setVisible(true);
        } else {
            addNotifications("erreur", "code incorrect");
        }
    }

    @FXML
    private void sendForgetPasswordCode(ActionEvent event) {
        boolean exist = US.getUtilisateurByEmail(emailverif.getText());
        if (exist) {
            try {
                int min = 10000;
                int max = 99999;

                generatedCode = (int) Math.floor(Math.random() * (max - min + 1) + min);
                sendEmail = new SendEmail("gclaimpidev@gmail.com", "Gclaim2022", emailverif.getText(), "Mot de passe oublié", "<h3> Voici un CODE pour modifier votre mot de passe : " + generatedCode + "\n </h3>");
                forgetPasswordPane.setVisible(false);
                VerifyCodePane.setVisible(true);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            addNotifications("erreur", "utlisateur n'exsite pas");
        }
    }

    @FXML
    private void ChangePassword(ActionEvent event) {
        if (oldPassword.getText().equals(newPassword.getText())) {
            US.ChangePasswordWithEmail(emailverif.getText(), newPassword.getText());
            ChangePasswordPane.setVisible(false);
            addNotifications("erreur", "Mot de passe modifier avec succées");
        } else {
            addNotifications("erreur", "les mots de passes ne sont pas identiques");
        }

    }


}
