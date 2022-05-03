 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Article;

import Entities.Article;
import Entities.Commentaire;
import Front.MainWindowController;
import static GUI.Article.ShowArticleFrontController.currentAbo;
import Services.ArticleService;
import Services.CommentaireService;
import Services.ServiceUser;
import Tools.Constants;
import Tools.SendEmail;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
   
/**
 * FXML Controller class
 *
 * @author User
 */
public class CommentaireController implements Initializable {

    @FXML
    private Button btnAjCom;
    @FXML
    private TextField txtusr;
    @FXML
    private TextArea txtCom;
    private SendEmail sendEmail;
private ServiceUser US=new ServiceUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtusr.setText(US.currentUser.getUsername());
    }    

    @FXML
    private void AddCommentaire(ActionEvent event) throws Exception {
                    System.out.println(currentAbo);

       CommentaireService ps = new CommentaireService();
       if (txtusr.getText().isEmpty() == false
                && txtCom.getText().isEmpty() == false) {

       Commentaire p = new Commentaire();
       p.setUser(txtusr.getText());
       p.setCommentaire(txtCom.getText());
   
       p.setCreation(Date.valueOf(LocalDate.now()));
       Article c = new Article(currentAbo.getId());
       p.setArticle_id(c);
           
       ps.AddCommentairePst(p);
        System.out.println("ajout");
            JOptionPane.showMessageDialog(null, "AJOUT commentaire  DONE");
            //ps.envoyer(p.getArticle_id().getTitre());
            sendEmail = new SendEmail("gclaimpidev@gmail.com", "Gclaim2022", "soumaya.bensassi@esprit.tn", "Mot de passe oublié"," <center style=\"width: 100%; background-color: #f1f1f1;\"><div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\"></div><div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">"
                + "  <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\"border=\"0\" width=\"100% \" style=\"margin: auto;\"><tr>"
                + "<td valign=\"top\" class=\"bg_white\" style=\"padding: 1em 2.5em 0 2.5em;\">"
                + "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr>"
                + "<td class=\"logo\" style=\"text-align: center; color: #c24400;\" ><h1>G_CLAIM</a></h1></td></tr></table></td> </tr><br> <br>"
              
                + " <tr> <td valign=\"middle\" class=\"hero bg_white\" style=\"background-image: url('https://www.coastline.edu/_files/img/750-421/esports-fist-bump.jpg');opacity: 0.88; background-size: cover; height: 400px;\">"
                + "<div class=\"overlay\"></div> <table><tr><td><div class=\"text\" style=\"padding: 0 4em; text-align: center;\">"
                + "<h2 >A PROPOS DE G_CLAIM</h2><h4 style=\"color: #ffffff;\" +\">G-Claim est Bien évidemment les MOBA comme DOTA et STR sont les incontournables du esport car ce sont les jeux qui rassemblent la plus grosse communauté de joueurs. Vous ne serez donc pas surpris de pourvoir parier sur des matches de League of Legends, Dota 2 et Starcraft. Ce trio est proposé par les meilleurs bookmakers du monde.</h4>"
                + "</div></td></tr> </table></td> </tr>"
                + "<tr> <td valign=\"middle\" class=\"intro bg_white\" style=\"padding: 2em 0 4em 0;\"><table><tr><td><div class=\"text\" style=\"padding: 10 2.5em; text-align: center; margin-left:500\">"
                + "<h2 style=\"margin-left:150px;\" +\" ></h2><h2 style=\"margin-left:150px;\" +\">Bonjour! un commentaire a ete ajouter</h2>"
                +"<p>Cordialement </p> <p>	Gclaim by BITS&BAYTES</p>"
                + " </center>"); 
              
              MainWindowController.getInstance().loadInterface("../GUI/Article/ShowCommentaire.fxml");
       } else {
            JOptionPane.showMessageDialog(null, "erreur !!! remplir Correctement les champs");
        }
    }
    
}
