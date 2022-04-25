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

        // juste pour yussef
        txtmail.setText("soumaya.bensassi@esprit.tn");
        txtpassword.setText("10101010");
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
                } else if (currentUser.getRoles().equals("[\"ROLE_COACH\"]") && currentUser.isIsVerfied()) {
                    System.out.println(currentUser.isIsVerfied());

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../MenuFront.fxml"));

                    try {
                        Parent root = loader.load();
                        txtmail.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
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
                //sendEmail = new SendEmail("gclaimpidev@gmail.com", "Gclaim2022", emailverif.getText(), "Mot de passe oublié", "<div style=\"color: #c24400\"> <h1>Bonjour! " + US.getUtilisateurByEmail1(emailverif.getText()) +" </h1> </div> <h3> Voici un CODE pour modifier votre mot de passe : " + generatedCode + "\n </h3> <div style=\"color: #c24400\"> <p>Cordialement </p> <p>	Gclaim by BITS&BAYTES</p> <img src=\"C:/xampp/htdocs/GClaimDesktop/Gclaim_desktop/src/GUI/src_image/Logo.png \"> </div>");
               
                sendEmail = new SendEmail("gclaimpidev@gmail.com", "Gclaim2022", emailverif.getText(), "Mot de passe oublié"," <style>\n" +
"\n" +
"        /* What it does: Remove spaces around the email design added by some email clients. */\n" +
"        /* Beware: It can remove the padding / margin and add a background color to the compose a reply window. */\n" +
"        html,\n" +
"body {\n" +
"    margin: 0 auto !important;\n" +
"    padding: 0 !important;\n" +
"    height: 100% !important;\n" +
"    width: 100% !important;\n" +
"    background: #f1f1f1;\n" +
"}\n" +
"\n" +
"/* What it does: Stops email clients resizing small text. */\n" +
"* {\n" +
"    -ms-text-size-adjust: 100%;\n" +
"    -webkit-text-size-adjust: 100%;\n" +
"}\n" +
"\n" +
"/* What it does: Centers email on Android 4.4 */\n" +
"div[style*=\"margin: 16px 0\"] {\n" +
"    margin: 0 !important;\n" +
"}\n" +
"\n" +
"/* What it does: Stops Outlook from adding extra spacing to tables. */\n" +
"table,\n" +
"td {\n" +
"    mso-table-lspace: 0pt !important;\n" +
"    mso-table-rspace: 0pt !important;\n" +
"}\n" +
"\n" +
"/* What it does: Fixes webkit padding issue. */\n" +
"table {\n" +
"    border-spacing: 0 !important;\n" +
"    border-collapse: collapse !important;\n" +
"    table-layout: fixed !important;\n" +
"    margin: 0 auto !important;\n" +
"}\n" +
"\n" +
"/* What it does: Uses a better rendering method when resizing images in IE. */\n" +
"img {\n" +
"    -ms-interpolation-mode:bicubic;\n" +
"}\n" +
"\n" +
"/* What it does: Prevents Windows 10 Mail from underlining links despite inline CSS. Styles for underlined links should be inline. */\n" +
"a {\n" +
"    text-decoration: none;\n" +
"}\n" +
"\n" +
"/* What it does: A work-around for email clients meddling in triggered links. */\n" +
"*[x-apple-data-detectors],  /* iOS */\n" +
".unstyle-auto-detected-links *,\n" +
".aBn {\n" +
"    border-bottom: 0 !important;\n" +
"    cursor: default !important;\n" +
"    color: inherit !important;\n" +
"    text-decoration: none !important;\n" +
"    font-size: inherit !important;\n" +
"    font-family: inherit !important;\n" +
"    font-weight: inherit !important;\n" +
"    line-height: inherit !important;\n" +
"}\n" +
"\n" +
"/* What it does: Prevents Gmail from displaying a download button on large, non-linked images. */\n" +
".a6S {\n" +
"    display: none !important;\n" +
"    opacity: 0.01 !important;\n" +
"}\n" +
"\n" +
"/* What it does: Prevents Gmail from changing the text color in conversation threads. */\n" +
".im {\n" +
"    color: inherit !important;\n" +
"}\n" +
"\n" +
"/* If the above doesn't work, add a .g-img class to any image in question. */\n" +
"img.g-img + div {\n" +
"    display: none !important;\n" +
"}\n" +
"\n" +
"/* What it does: Removes right gutter in Gmail iOS app: https://github.com/TedGoas/Cerberus/issues/89  */\n" +
"/* Create one of these media queries for each additional viewport size you'd like to fix */\n" +
"\n" +
"/* iPhone 4, 4S, 5, 5S, 5C, and 5SE */\n" +
"@media only screen and (min-device-width: 320px) and (max-device-width: 374px) {\n" +
"    u ~ div .email-container {\n" +
"        min-width: 320px !important;\n" +
"    }\n" +
"}\n" +
"/* iPhone 6, 6S, 7, 8, and X */\n" +
"@media only screen and (min-device-width: 375px) and (max-device-width: 413px) {\n" +
"    u ~ div .email-container {\n" +
"        min-width: 375px !important;\n" +
"    }\n" +
"}\n" +
"/* iPhone 6+, 7+, and 8+ */\n" +
"@media only screen and (min-device-width: 414px) {\n" +
"    u ~ div .email-container {\n" +
"        min-width: 414px !important;\n" +
"    }\n" +
"}\n" +
"\n" +
"    </style>\n" +
"\n" +
"    <!-- CSS Reset : END -->\n" +
"\n" +
"    <!-- Progressive Enhancements : BEGIN -->\n" +
"    <style>\n" +
"\n" +
"	    .primary{\n" +
"	background: #ffc0d0;\n" +
"}\n" +
".bg_white{\n" +
"	background: #ffffff;\n" +
"}\n" +
".bg_light{\n" +
"	background: #fafafa;\n" +
"}\n" +
".bg_black{\n" +
"	background: #000000;\n" +
"}\n" +
".bg_dark{\n" +
"	background: rgba(0,0,0,.8);\n" +
"}\n" +
".email-section{\n" +
"	padding:2.5em;\n" +
"}\n" +
"\n" +
"/*BUTTON*/\n" +
".btn{\n" +
"	padding: 5px 20px;\n" +
"	display: inline-block;\n" +
"}\n" +
".btn.btn-primary{\n" +
"	border-radius: 5px;\n" +
"	background: #ffc0d0;\n" +
"	color: #ffffff;\n" +
"}\n" +
".btn.btn-white{\n" +
"	border-radius: 5px;\n" +
"	background: #ffffff;\n" +
"	color: #000000;\n" +
"}\n" +
".btn.btn-white-outline{\n" +
"	border-radius: 5px;\n" +
"	background: transparent;\n" +
"	border: 1px solid #fff;\n" +
"	color: #fff;\n" +
"}\n" +
".btn.btn-black{\n" +
"	border-radius: 0px;\n" +
"	background: #000;\n" +
"	color: #fff;\n" +
"}\n" +
".btn.btn-black-outline{\n" +
"	border-radius: 0px;\n" +
"	background: transparent;\n" +
"	border: 2px solid #000;\n" +
"	color: #000;\n" +
"	font-weight: 700;\n" +
"}\n" +
"\n" +
"h1,h2,h3,h4,h5,h6{\n" +
"	font-family: 'Playfair Display', sans-serif;\n" +
"	color: #000000;\n" +
"	margin-top: 0;\n" +
"	font-weight: 400;\n" +
"}\n" +
"\n" +
"body{\n" +
"	font-family: 'Lato', sans-serif;\n" +
"	font-weight: 400;\n" +
"	font-size: 15px;\n" +
"	line-height: 1.8;\n" +
"	color: rgba(0,0,0,.5);\n" +
"}\n" +
"\n" +
"a{\n" +
"	color: #ffc0d0;\n" +
"}\n" +
"\n" +
"table{\n" +
"}\n" +
"/*LOGO*/\n" +
"\n" +
".logo h1{\n" +
"	margin: 0;\n" +
"}\n" +
".logo h1 a{\n" +
"	color: #000000;\n" +
"	font-size: 30px;\n" +
"	font-weight: 700;\n" +
"	/*text-transform: uppercase;*/\n" +
"	font-family: 'Playfair Display', sans-serif;\n" +
"	font-style: italic;\n" +
"}\n" +
"\n" +
".navigation{\n" +
"	padding: 0;\n" +
"	padding: 1em 0;\n" +
"	/*background: rgba(0,0,0,1);*/\n" +
"	border-top: 1px solid rgba(0,0,0,.05);\n" +
"	border-bottom: 1px solid rgba(0,0,0,.05);\n" +
"	margin-bottom: 0;\n" +
"}\n" +
".navigation li{\n" +
"	list-style: none;\n" +
"	display: inline-block;;\n" +
"	margin-left: 5px;\n" +
"	margin-right: 5px;\n" +
"	font-size: 13px;\n" +
"	font-weight: 500;\n" +
"	text-transform: uppercase;\n" +
"	letter-spacing: 2px;\n" +
"}\n" +
".navigation li a{\n" +
"	color: rgba(0,0,0,1);\n" +
"}\n" +
"\n" +
"/*HERO*/\n" +
".hero{\n" +
"	position: relative;\n" +
"	z-index: 0;\n" +
"}\n" +
".hero .overlay{\n" +
"	position: absolute;\n" +
"	top: 0;\n" +
"	left: 0;\n" +
"	right: 0;\n" +
"	bottom: 0;\n" +
"	content: '';\n" +
"	width: 100%;\n" +
"	background: #000000;\n" +
"	z-index: -1;\n" +
"	opacity: .2;\n" +
"}\n" +
".hero .text{\n" +
"	color: rgba(255,255,255,.9);\n" +
"	max-width: 50%;\n" +
"	margin: 0 auto 0;\n" +
"}\n" +
".hero .text h2{\n" +
"	color: #fff;\n" +
"	font-size: 30px;\n" +
"	margin-bottom: 0;\n" +
"	font-weight: 300;\n" +
"	line-height: 1.4;\n" +
"}\n" +
".hero .text h2 span{\n" +
"	font-weight: 600;\n" +
"	color: #c24400;\n" +
"}\n" +
"\n" +
"/*INTRO*/\n" +
".intro{\n" +
"	position: relative;\n" +
"	z-index: 0;\n" +
"}\n" +
"\n" +
".intro .text{\n" +
"	color: rgba(0,0,0,.3);\n" +
"}\n" +
".intro .text h2{\n" +
"	color: #000;\n" +
"	font-size: 34px;\n" +
"	margin-bottom: 0;\n" +
"	font-weight: 300;\n" +
"}\n" +
".intro .text h2 span{\n" +
"	font-weight: 600;\n" +
"	color: #000;\n" +
"}\n" +
"\n" +
"\n" +
"/*PRODUCT*/\n" +
".text-product{\n" +
"}\n" +
".text-product .price{\n" +
"	font-size: 20px;\n" +
"	color: #fff;\n" +
"	display: inline-block;;\n" +
"	margin-bottom: 1em;\n" +
"	border: 2px solid #fff;\n" +
"	padding: 0 10px;\n" +
"}\n" +
".text-product h2{\n" +
"	font-family: 'Lato', sans-serif;\n" +
"}\n" +
"\n" +
"/*HEADING SECTION*/\n" +
".heading-section{\n" +
"}\n" +
".heading-section h2{\n" +
"	color: #000000;\n" +
"	font-size: 28px;\n" +
"	margin-top: 0;\n" +
"	line-height: 1.4;\n" +
"	font-weight: 400;\n" +
"}\n" +
".heading-section .subheading{\n" +
"	margin-bottom: 20px !important;\n" +
"	display: inline-block;\n" +
"	font-size: 13px;\n" +
"	text-transform: uppercase;\n" +
"	letter-spacing: 2px;\n" +
"	color: rgba(0,0,0,.4);\n" +
"	position: relative;\n" +
"}\n" +
".heading-section .subheading::after{\n" +
"	position: absolute;\n" +
"	left: 0;\n" +
"	right: 0;\n" +
"	bottom: -10px;\n" +
"	content: '';\n" +
"	width: 100%;\n" +
"	height: 2px;\n" +
"	background: #ffc0d0;\n" +
"	margin: 0 auto;\n" +
"}\n" +
"\n" +
".heading-section-white{\n" +
"	color: rgba(255,255,255,.8);\n" +
"}\n" +
".heading-section-white h2{\n" +
"	font-family: \n" +
"	line-height: 1;\n" +
"	padding-bottom: 0;\n" +
"}\n" +
".heading-section-white h2{\n" +
"	color: #ffffff;\n" +
"}\n" +
".heading-section-white .subheading{\n" +
"	margin-bottom: 0;\n" +
"	display: inline-block;\n" +
"	font-size: 13px;\n" +
"	text-transform: uppercase;\n" +
"	letter-spacing: 2px;\n" +
"	color: rgba(255,255,255,.4);\n" +
"}\n" +
"\n" +
"\n" +
"ul.social{\n" +
"	padding: 0;\n" +
"}\n" +
"ul.social li{\n" +
"	display: inline-block;\n" +
"	margin-right: 10px;\n" +
"}\n" +
"\n" +
"/*FOOTER*/\n" +
"\n" +
".footer{\n" +
"	border-top: 1px solid rgba(0,0,0,.05);\n" +
"	color: rgba(0,0,0,.5);\n" +
"}\n" +
".footer .heading{\n" +
"	color: #000;\n" +
"	font-size: 20px;\n" +
"}\n" +
".footer ul{\n" +
"	margin: 0;\n" +
"	padding: 0;\n" +
"}\n" +
".footer ul li{\n" +
"	list-style: none;\n" +
"	margin-bottom: 10px;\n" +
"}\n" +
".footer ul li a{\n" +
"	color: rgba(0,0,0,1);\n" +
"}\n" +
"\n" +
"\n" +
"@media screen and (max-width: 500px) {\n" +
"\n" +
"\n" +
"}\n" +
"\n" +
"\n" +
"    </style><center style=\"width: 100%; background-color: #f6bc9d;\"><div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\"></div><div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">"
                + "  <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\"border=\"0\" width=\"100% \" style=\"margin: auto;\"><tr>"
                + "<td valign=\"top\" class=\"bg_white\" style=\"padding: 1em 2.5em 0 2.5em;\">"
                + "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr>"
                + "<td class=\"logo\" style=\"text-align: center; background: #c24400;\" ><h1><img height='70' width='70' alt=\"Logo\" title=\"Logo\" src=\"../../src_image/Logo.jpg\" style=\"display:block\"> <a>G_CLAIM</a></h1></td></tr></table></td> </tr>"
              
                + " <tr> <td valign=\"middle\" class=\"hero bg_white\" style=\"background-image: url('../../GUI.src_image/Logo.png'); background-size: cover; height: 400px;\">"
                + "<div class=\"overlay\"></div> <table><tr><td><div class=\"text\" style=\"padding: 0 4em; text-align: center;\">"
                + "<h2>A PROPOS DE G_CLAIM</h2><p>G-Claim est Bien évidemment les MOBA comme DOTA et STR sont les incontournables du esport car ce sont les jeux qui rassemblent la plus grosse communauté de joueurs. Vous ne serez donc pas surpris de pourvoir parier sur des matches de League of Legends, Dota 2 et Starcraft. Ce trio est proposé par les meilleurs bookmakers du monde.</p>"
                + "</div></td></tr> </table></td> </tr>"
                + "<tr> <td valign=\"middle\" class=\"intro bg_white\" style=\"padding: 2em 0 4em 0;\"><table><tr><td><div class=\"text\" style=\"padding: 10 2.5em; text-align: center;\">"
                + "<h2>Récupérer votre mot de passe</h2><h2>Bonjour!" + US.getUtilisateurByEmail1(emailverif.getText()) +" </h2><p>Voici le code pour récuperer votre mot de passe</p>"
                + "<p><a  style=\"color: #ffffff;\" +\">" + generatedCode + "</a></p></div></td></tr></table> </td></tr>"
                + " </center>"); 
                
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
