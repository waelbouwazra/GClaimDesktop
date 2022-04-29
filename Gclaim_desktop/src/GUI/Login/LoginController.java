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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import nl.captcha.Captcha;
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
    @FXML
    private TextField code;
    Captcha captcha;
    @FXML
    private ImageView captchaIV;
    private ServiceTournoi ts = new ServiceTournoi();
    ServiceEquipe es=new ServiceEquipe();
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
         txtmail.setText("yuss@esprit.tn");
        txtpassword.setText("azerazer");
        captcha =setCaptcha();
    }
public Captcha setCaptcha() {
        Captcha captcha = new Captcha.Builder(450, 450)
                .addText()
                .build();

        System.out.println(captcha.getImage());
        Image image = SwingFXUtils.toFXImage(captcha.getImage(), null);

        captchaIV.setImage(image);

        return captcha;
    }
    @FXML
    private void SeConnecter(ActionEvent event) throws IOException {

        String email = txtmail.getText();
        String pwd = txtpassword.getText();
        if (email.isEmpty() || pwd.isEmpty()) {
            addNotifications("erreur", "Les champs sont vides ou incorrects");
            //JOptionPane.showMessageDialog(null, "Les champs sont vides ou incorrects");
        } else if (!captcha.isCorrect(code.getText())) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Captcha inncorrect ");
            alert.show();
            captcha = setCaptcha();
            code.setText("");
          
        } 
        else {
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
                  // US.sensSMS();
                    try {
                         List<Tournoi> listuser = ts.ShowTournoi();
                         for(Tournoi r : listuser) {
                             
                              System.out.println(ChronoUnit.DAYS.between(LocalDate.parse(java.sql.Date.valueOf(LocalDate.now()).toString()),LocalDate.parse(r.getDatev())));
                             if((ChronoUnit.DAYS.between(LocalDate.parse(java.sql.Date.valueOf(LocalDate.now()).toString()),LocalDate.parse(r.getDatev()))<7)&&(ChronoUnit.DAYS.between(LocalDate.parse(java.sql.Date.valueOf(LocalDate.now()).toString()),LocalDate.parse(r.getDatev()))>0))
                             {if(es.afficheEquipeUt(US.currentUser.getId()).size()>0)
                                 {
                                 if(!ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).isEmpty())
                                 {
                                     if((ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getNomtournoi()!=""))
                                     {
                                addNotifications("Coming Soon !",ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getNomtournoi());
                                     }
                                 }
                                 }
                             }
                              if(ChronoUnit.DAYS.between(LocalDate.parse(java.sql.Date.valueOf(LocalDate.now()).toString()),LocalDate.parse(r.getDatev()))==0)
                             {
                                 if(es.afficheEquipeUt(US.currentUser.getId()).size()>0)
                                 {
                                 if(!ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).isEmpty())
                                 {
                                     
                                     for(int s : ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0))){
                                         System.out.println(s+"dddddddddddd");
                                     if(r.getId()==ts.ShowTournoiById(s).getId())
                                     {
                                addNotifications("Votre tournoi est programmé pour aujourdhui à !",ts.ShowTournoiById(s).getHeurev());
                                     }
                                     }
                                 }
                                 }
                             }
                             if(java.sql.Date.valueOf(r.getDatev()).compareTo(java.sql.Date.valueOf(LocalDate.now()))==-1)
                             {
                                 if(es.afficheEquipeUt(US.currentUser.getId()).size()>0)
                                 {
                                 if(!ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).isEmpty())
                                 {
                                     if((ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getNomtournoi()!="")&&(r.getId()==ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getId()))
                                     {
                                addNotifications("Les tournois ci-dessous ont ete annulé",ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getNomtournoi());
                                ts.quitterTournoi(new Equipe(es.afficheEquipeUt(US.currentUser.getId()).get(0)),r);    
                                     }
                                 }
                                 
                                 }
                             }
                             
                           }
                        Parent root = loader.load();
                        txtmail.getScene().setRoot(root);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else if (currentUser.getRoles().equals("[\"ROLE_COACH\"]") && currentUser.isIsVerfied()) {
                    System.out.println(currentUser.isIsVerfied());

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../MenuFront.fxml"));

                    try {
                        List<Tournoi> listuser = ts.ShowTournoi();
                         for(Tournoi r : listuser) {
                             
                              System.out.println(ChronoUnit.DAYS.between(LocalDate.parse(java.sql.Date.valueOf(LocalDate.now()).toString()),LocalDate.parse(r.getDatev())));
                             if((ChronoUnit.DAYS.between(LocalDate.parse(java.sql.Date.valueOf(LocalDate.now()).toString()),LocalDate.parse(r.getDatev()))<7)&&(ChronoUnit.DAYS.between(LocalDate.parse(java.sql.Date.valueOf(LocalDate.now()).toString()),LocalDate.parse(r.getDatev()))>0))
                             {
                                 if(es.afficheEquipeUt(US.currentUser.getId()).size()>0)
                                 {
                                 if(!ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).isEmpty())
                                 {
                                     if((ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getNomtournoi()!=""))
                                     {
                                addNotifications("Coming Soon !",ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getNomtournoi());
                                     }
                                 }
                                 }
                             }
                              if(ChronoUnit.DAYS.between(LocalDate.parse(java.sql.Date.valueOf(LocalDate.now()).toString()),LocalDate.parse(r.getDatev()))==0)
                             {
                                 if(es.afficheEquipeUt(US.currentUser.getId()).size()>0)
                                 {
                                 if(!ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).isEmpty())
                                 {
                                     
                                     for(int s : ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0))){
                                         System.out.println(s+"dddddddddddd");
                                     if(r.getId()==ts.ShowTournoiById(s).getId())
                                     {
                                addNotifications("Votre tournoi est programmé pour aujourdhui à !",ts.ShowTournoiById(s).getHeurev());
                                     }
                                     }
                                 }
                                 }
                             }
                             if(java.sql.Date.valueOf(r.getDatev()).compareTo(java.sql.Date.valueOf(LocalDate.now()))==-1)
                             {
                                 if(es.afficheEquipeUt(US.currentUser.getId()).size()>0)
                                 {
                                 if(!ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).isEmpty())
                                 {
                                     if((ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getNomtournoi()!="")&&(r.getId()==ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getId()))
                                     {
                                addNotifications("Les tournois ci-dessous ont ete annulé",ts.ShowTournoiById(ts.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).get(0)).getNomtournoi());
                                ts.quitterTournoi(new Equipe(es.afficheEquipeUt(US.currentUser.getId()).get(0)),r);  

                                     }
                                 }
                                 }
                                 
                                 
                                 
                             }
                           }
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
               
                sendEmail = new SendEmail("gclaimpidev@gmail.com", "Gclaim2022", emailverif.getText(), "Mot de passe oublié"," <center style=\"width: 100%; background-color: #f1f1f1;\"><div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\"></div><div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">"
                + "  <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\"border=\"0\" width=\"100% \" style=\"margin: auto;\"><tr>"
                + "<td valign=\"top\" class=\"bg_white\" style=\"padding: 1em 2.5em 0 2.5em;\">"
                + "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr>"
                + "<td class=\"logo\" style=\"text-align: center; color: #c24400;\" ><h1>G_CLAIM</a></h1></td></tr></table></td> </tr><br> <br>"
              
                + " <tr> <td valign=\"middle\" class=\"hero bg_white\" style=\"background-image: url('https://www.coastline.edu/_files/img/750-421/esports-fist-bump.jpg');opacity: 0.88; background-size: cover; height: 400px;\">"
                + "<div class=\"overlay\"></div> <table><tr><td><div class=\"text\" style=\"padding: 0 4em; text-align: center;\">"
                + "<h2 >A PROPOS DE G_CLAIM</h2><h4 style=\"color: #ffffff;\" +\">G-Claim est Bien évidemment les MOBA comme DOTA et STR sont les incontournables du esport car ce sont les jeux qui rassemblent la plus grosse communauté de joueurs. Vous ne serez donc pas surpris de pourvoir parier sur des matches de League of Legends, Dota 2 et Starcraft. Ce trio est proposé par les meilleurs bookmakers du monde.</h4>"
                + "</div></td></tr> </table></td> </tr>"
                + "<tr> <td valign=\"middle\" class=\"intro bg_white\" style=\"padding: 2em 0 4em 0;\"><table><tr><td><div class=\"text\" style=\"padding: 10 2.5em; text-align: center; margin-left:500\">"
                + "<h2 style=\"margin-left:150px;\" +\" >Récupérer votre mot de passe</h2><h2 style=\"margin-left:150px;\" +\">Bonjour!" + US.getUtilisateurByEmail1(emailverif.getText()) +" </h2><p  style=\"margin-left:150px;\" +\" >Voici le code pour récuperer votre mot de passe</p>"
                + "<p><a  style=\"margin-left:150px;color: #c24400;\" +\">" + generatedCode + "</a></p></div></td></tr></table> </td></tr> <div style=\"color: #c24400\"> <p>Cordialement </p> <p>	Gclaim by BITS&BAYTES</p>"
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
