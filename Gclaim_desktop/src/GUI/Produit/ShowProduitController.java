/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Categorie;
import Entities.Produit;
import Services.CategorieService;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author azizk
 */
public class ShowProduitController implements Initializable{
     @FXML
    private ListView<Produit> txtListProd;
    @FXML
    private Button btnRetour;
    ProduitService ps = new ProduitService();
    CategorieService cs = new CategorieService();
   
   
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnsupp;
    
    @FXML
    private TextField nomp;
    @FXML
    private TextField descp;
    @FXML
    private TextField prixp;
    @FXML
    private TextField qtep;
    @FXML
    private Button btnmodif;
    int id_prod;
    @FXML
    private ChoiceBox<Integer> EtatBox;
    @FXML
    private Button btnplusvu;
    @FXML
    private Text nbr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ObservableList<Produit> items =FXCollections.observableArrayList();
                List<Produit> listProduit = ps.ShowProduit();
                for(Produit p : listProduit) {
                    items.add(p);
                }
                txtListProd.setItems(items);
               
        
          
      
    }    
@FXML
    private void DeleteProduct(ActionEvent event) {
        ps.DeleteProduit(txtListProd.getSelectionModel().getSelectedItem().getId_produit());
      ObservableList<Produit> items =FXCollections.observableArrayList();
        List<Produit> listprod = ps.ShowProduit();
        for(Produit r : listprod) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtListProd.setItems(items);
    
    }
     
    
    @FXML
    private void loadMenu(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuProduit.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnRetour.setTextFill(Color.WHITE);
            btnRetour.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void updateProduit(ActionEvent event) {
            
        ProduitService ps = new ProduitService();
        
          String nom_produit= nomp.getText();
          String description= descp.getText();
          Double prix= Double.parseDouble(prixp.getText());
          Integer qte= Integer. parseInt(qtep.getText());
        Integer categorie=  EtatBox.getValue();
      
       Categorie c = new Categorie(categorie);
         System.out.println("id="+id_prod);
         
          Produit a =new Produit(id_prod,nom_produit,description,prix,qte,c);
          System.out.println(a);
         // a.setNom_produit(nom_produit);
         // a.setDescription(description);
          //a.setPrix_produit(Double.parseDouble(prix));
         // a.setQte_produit(Integer. parseInt(qte));
       ps.UpdateProduit(a, id_prod);
    ObservableList<Produit> items =FXCollections.observableArrayList();
        List<Produit> listprod = ps.ShowProduit();
        for(Produit r : listprod) {
            String ch = r.toString();
            items.add(r);
        }
                txtListProd.setItems(items);

  
    }
    @FXML
    private void ProduitSelect(MouseEvent event) {
        
          nomp.setText(txtListProd.getSelectionModel().getSelectedItem().getNom_produit());  
          
            descp.setText(txtListProd.getSelectionModel().getSelectedItem().getDescription());
            prixp.setText(""+txtListProd.getSelectionModel().getSelectedItem().getPrix_produit());
            qtep.setText(""+txtListProd.getSelectionModel().getSelectedItem().getQte_produit());
            
            EtatBox.setValue(txtListProd.getSelectionModel().getSelectedItem().getCategorie().getId_categorie());
            
          id_prod=ps.getIdPro(txtListProd.getSelectionModel().getSelectedItem().getNom_produit(), txtListProd.getSelectionModel().getSelectedItem().getDescription(), 
                  txtListProd.getSelectionModel().getSelectedItem().getPrix_produit(), txtListProd.getSelectionModel().getSelectedItem().getQte_produit());
         ObservableList<Integer> langss = FXCollections.observableArrayList(cs.getIdcategorie());
          System.out.println(langss);
     EtatBox.setItems(langss);
        
    }
    
}
