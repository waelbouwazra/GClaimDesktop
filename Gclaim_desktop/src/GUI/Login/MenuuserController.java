/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author souma
 */
public class MenuuserController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnuserT;
    @FXML
    private Button btncoachT;
    private Button btnuserA;
    private Button btncoachA;
    @FXML
    private Button btndemande;
    @FXML
    private Button btnequipe;
    @FXML
    private Button excel;
   ServiceUser RS =new ServiceUser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showusers(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("afficheuser.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnuserT.setTextFill(Color.WHITE);
            btnuserT.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void showcoach(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("afficheCoachs.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btncoachT.setTextFill(Color.WHITE);
            btncoachT.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    @FXML
    private void listeddesdemandes(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("listedesdemandes.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btndemande.setTextFill(Color.WHITE);
            btndemande.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void listedesequipes(ActionEvent event) {
         AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("listedesequipes.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnequipe.setTextFill(Color.WHITE);
            btnequipe.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void excel(ActionEvent event) {
        RS.generateExcel();
         Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("excel generer ");
            alertt.setContentText("voir le fichier utilisateur.Xls");
            alertt.show();  
    }
}
