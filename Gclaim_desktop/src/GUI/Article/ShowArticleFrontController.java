/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Article;

import Entities.Article;
import Front.MainWindowController;
import static GUI.Login.ShowAllController.currentAbo;
import Services.ArticleService;
import Tools.Constants;
import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ShowArticleFrontController implements Initializable {
    public static Article currentAbo;

    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox;
    @FXML
    private AnchorPane mainPane;
    ArticleService rs=new ArticleService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          List<Article> listAbo = rs.ShowArticleFront();
        Collections.reverse(listAbo);

        if (!listAbo.isEmpty()) {
            for (Article abo : listAbo) {
                mainVBox.getChildren().add(makeAboModel(abo));
                System.out.println(abo);
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
        // TODO
    }    
    public Parent makeAboModel(Article abo) {
        Parent parent = null;

        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ModelA.fxml")));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
           // ((ImageView) innerContainer.lookup("#myimageview")).setImage(file.toString());
            ((Text) innerContainer.lookup("#createdAtText")).setText("Titre : " + abo.getTitre());
            ((Text) innerContainer.lookup("#sdpIdText")).setText("Description : " + abo.getDescription());
            ((Text) innerContainer.lookup("#datetext")).setText("Date de création : " + abo.getCreate_at());
            ((Text) innerContainer.lookup("#etatText")).setText("nombre de vu : " + abo.getNbr_vu());
            ((Text) innerContainer.lookup("#categtext")).setText("categorie : " + abo.getCat_id().getNom());
System.out.println(abo.getCat_id());

            
         //((Pane) innerContainer.lookup("#ajouCommentaire")).setVisible(true);
      ((Button) innerContainer.lookup("#ajouterCom")).setOnAction((event) -> ajoutComAbo(abo));
     
     //   if (setOnAction(event)==true){ abo.setNbr_vu(abo.getNbr_vu()+1);}
      ((Button) innerContainer.lookup("#btnAfficheCom")).setOnAction((event) -> AfficheCom(abo));
       

        
        }

        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
      private void ajoutComAbo(Article abo) {
        currentAbo = abo;
       // rs.updateEquipe(abo);
        MainWindowController.getInstance().loadInterface(Constants.FXML_ADD_COM);
         rs.increment(abo);
        
    }
         private void AfficheCom(Article abo) {
        currentAbo = abo;
       // rs.updateEquipe(abo);
        MainWindowController.getInstance().loadInterface("../GUI/Article/ShowCommentaire.fxml");
    }
  
}
