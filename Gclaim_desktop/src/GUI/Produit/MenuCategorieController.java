/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import GUI.Panier.*;
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
 * @author azizk
 */
public class MenuCategorieController implements Initializable {

    @FXML
    private Button ajoutcateg;
    @FXML
    private Button showcateg;
    @FXML
    private Button updatecateg;
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
    private void showCategs(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("showCategorie.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            showcateg.setTextFill(Color.WHITE);
            showcateg.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 @FXML
    private void addCateg(ActionEvent event) {
                AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("addcategorie.fxml"));
            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            ajoutcateg.setTextFill(Color.WHITE);
            ajoutcateg.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            
        }
    }

    
    private void defaultStateButtons() {
        updatecateg.setTextFill(Color.web("#5b4ebd"));
        updatecateg.setStyle("-fx-background-color :#ffffff");

        showcateg.setTextFill(Color.web("#5b4ebd"));
        showcateg.setStyle("-fx-background-color :#ffffff");

        ajoutcateg.setTextFill(Color.web("#5b4ebd"));
        ajoutcateg.setStyle("-fx-background-color :#ffffff");

        

    }
}
