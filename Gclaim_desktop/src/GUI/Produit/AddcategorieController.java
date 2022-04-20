/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Categorie;
import Services.CategorieService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author azizk
 */
public class AddcategorieController implements Initializable {
    @FXML
 private CategorieService cs = new CategorieService();
    @FXML
    private AnchorPane mainPain;
    @FXML
    private TextField nomc;
    @FXML
    private TextField typec;
    @FXML
    private Button btnajout;
    @FXML
    private Button btnretour;
    
    @FXML
private AnchorPane mainPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Categorie> items =FXCollections.observableArrayList();
        List<Categorie> listcateg = cs.ShowCategorie();
        for(Categorie r : listcateg) {
            String ch = r.toString();
            items.add(r);
        }
    }
@FXML
private void addCateg(ActionEvent event) {
        
       
       CategorieService cs = new CategorieService();
       Categorie c = new Categorie();
       c.setNom_categorie( nomc.getText());
      
       c.setType_categorie(typec.getText());
      
       
       
    
    
     
       
       cs.AddCategoriePst(c);
    }    
    
@FXML
    private void loadMenu(ActionEvent event) {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuCategorie.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnretour.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
