/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import Entities.Categorie;
import Entities.Produit;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtprix;
    @FXML
    private Button btnAjouter;
    @FXML
    private ChoiceBox txtcategorie;
    @FXML
    private Button btngetback;
    @FXML
    private Label nomHebr;
    @FXML
    private TextField txtqt;
     String nom;
     String qt;
     String desc;
     String prix;
     String Categorie;
    @FXML
    private AnchorPane mainPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> langs = FXCollections.observableArrayList("1", "2", "3", "4");
        txtcategorie.setItems(langs);
    }    

    @FXML
    private void addProduct(ActionEvent event) {
       ProduitService ps = new ProduitService();
       Produit p = new Produit();
       p.setDescription( txtdesc.getText());
       p.setQte_produit(Integer.parseInt(txtqt.getText()));
       p.setNom_produit(txtnom.getText());
       p.setPrix_produit(Double.parseDouble(txtprix.getText()));
       p.setNbr_vu(0);
       p.setDateAjout_produit(Date.valueOf(LocalDate.now()));
       Categorie c = new Categorie(1);
       p.setCategorie(c);
       ps.AddProduitPst(p);
    }

    @FXML
    private void loadMenu(ActionEvent event) {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuProduit.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
