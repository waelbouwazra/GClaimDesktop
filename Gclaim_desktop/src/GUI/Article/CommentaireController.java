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
    private void AddCommentaire(ActionEvent event) {
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
            ps.envoyer(p.getArticle_id().getTitre());
              MainWindowController.getInstance().loadInterface("../GUI/Article/ShowCommentaire.fxml");
       } else {
            JOptionPane.showMessageDialog(null, "erreur !!! remplir Correctement les champs");
        }
    }
    
}
