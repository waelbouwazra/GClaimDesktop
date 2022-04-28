/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Article;

import Entities.Article;
import Entities.Commentaire;
import static GUI.Article.ShowArticleFrontController.currentAbo;
import Services.CommentaireService;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ShowCommentaireController implements Initializable {
    CommentaireService rs=new CommentaireService();
    @FXML
    private AnchorPane mainPain;
    @FXML
    private Text topText;
   
    @FXML
    private VBox mainVBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          List<Commentaire> list = rs.ShowCommentaireArt(currentAbo);
          System.out.println(rs.ShowCommentaireArt(currentAbo));
        Collections.reverse(list);

        if (!list.isEmpty()) {
           
            for (Commentaire abo1 : list) {
              
                mainVBox.getChildren().add(makeAboModel(abo1));
            }
        }
        else {
            
        }
        // TODO
    }
       public Parent makeAboModel(Commentaire abo1) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModelC.fxml")));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            
            ((Text) innerContainer.lookup("#usertxt")).setText("User : " + abo1.getUser());
            ((Text) innerContainer.lookup("#comtxt")).setText("Commentaire : " + abo1.getCommentaire());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    
}
