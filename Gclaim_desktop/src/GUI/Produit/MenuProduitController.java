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
 * @author moham
 */
public class MenuProduitController implements Initializable {

    @FXML
    private Button btnaffr;
    private Button btntrtr;
    @FXML
    private Button btnaffp;
    private Button btnrect;
    private Button btnstat;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnajp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showProducts(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("showProduit.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnaffr.setTextFill(Color.WHITE);
            btnaffr.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnupp(ActionEvent event) {
    }

    @FXML
    private void addProduct(ActionEvent event) {
                AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("addProduct.fxml"));
            mainPane.getChildren().setAll(pane);
            defaultStateButtons();
            btnaffr.setTextFill(Color.WHITE);
            btnaffr.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            
        }
    }
    private void defaultStateButtons() {
        btnaffp.setTextFill(Color.web("#5b4ebd"));
        btnaffp.setStyle("-fx-background-color :#ffffff");

        btnaffr.setTextFill(Color.web("#5b4ebd"));
        btnaffr.setStyle("-fx-background-color :#ffffff");

        btnajp.setTextFill(Color.web("#5b4ebd"));
        btnajp.setStyle("-fx-background-color :#ffffff");

       

    }

    
}
