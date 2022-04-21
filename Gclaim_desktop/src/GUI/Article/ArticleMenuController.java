/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Article;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ArticleMenuController implements Initializable {

    @FXML
    private Button btnaffA;
    @FXML
    private Button btnadd;
    @FXML
    private AnchorPane mainPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showArticle(ActionEvent event) {
          AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("showArticle.fxml"));
          mainPane.getChildren().setAll(pane);

            btnaffA.setTextFill(Color.WHITE);
            btnaffA.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
                     System.out.println(ex.getMessage());
                             }
    }

    @FXML
    private void addArticle(ActionEvent event) {
                   AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("addArticle.fxml"));
                        mainPane.getChildren().setAll(pane);

            btnadd.setTextFill(Color.WHITE);
            btnadd.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            
        }
    }


   
}
