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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author azizk
 */
public class AddcategorieController implements Initializable {
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
    
private AnchorPane mainPane;
    @FXML
    private Text alerteajout;
    @FXML
    private Text alerteajout1;
    
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
        
       Boolean verif = true;
       CategorieService cs = new CategorieService();
       Categorie c = new Categorie();
       c.setNom_categorie( nomc.getText());
      
       c.setType_categorie(typec.getText());
      if (nomc.getText().equals("")) {
            alerteajout.setText("Remplir le champs !!");
            verif = false;
        }else if (typec.getText().equals("")) {
            alerteajout1.setText("Remplir le champs !!");
            verif = false;
        }
      
      else {
          
          cs.AddCategoriePst(c);
          alerteajout1.setText("Ajout avec succes!");
          alerteajout.setText("");
      }
       
       
       
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
