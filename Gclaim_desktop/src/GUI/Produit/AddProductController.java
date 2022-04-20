/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Categorie;
import Entities.Image;
import Entities.Produit;
import Entities.Utilisateur;
import Services.CategorieService;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    private ProduitService ps = new ProduitService();
    private CategorieService cs = new CategorieService();
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtprix;
    @FXML
    private Button btnAjouter;
     @FXML
    private ChoiceBox<Integer> comboBoxID;
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
    @FXML
    private ImageView ImageviewID;
    String url_image;
    @FXML
    private Button btnImage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Produit> items =FXCollections.observableArrayList();
        List<Produit> listproduit = ps.ShowProduit();
        for(Produit r : listproduit) {
            String ch = r.toString();
            items.add(r);
        }
        
       
       
      ObservableList<Integer> langs = FXCollections.observableArrayList(cs.getIdcategorie());
        comboBoxID.setItems(langs);
       // ObservableList<Integer> langs = FXCollections.observableArrayList(ps.getIdcategorie());
        //comboBoxID.setItems(langs);
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
       Categorie c = new Categorie(comboBoxID.getValue());
       p.setCategorie(c);
         Image i=new Image();
         //i.setUrl_image(url_image);
         
         
         
         p.setImage(i);
        
       
       
    
    ObservableList<Integer> langs = FXCollections.observableArrayList(cs.getIdcategorie() );
        comboBoxID.setItems(langs);
     
       
       ps.AddProduitPst(p,i);
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
