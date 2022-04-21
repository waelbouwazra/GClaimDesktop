/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Article;

import Entities.Article;
import Entities.cat;
import Front.MainWindowController;
import Services.ArticleService;
import Services.CatService;
import Tools.Constants;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddCatController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtCouleur;
    @FXML
    private Button addCat;
    @FXML
    private ListView<cat> listCategorie;
    CatService ps = new CatService();
    @FXML
    private Button modifCat;
    @FXML
    private Button suppCat;
int idCat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                  ObservableList<cat> items =FXCollections.observableArrayList();
                List<cat> listProduit = ps.ShowCategorie();
                for(cat p : listProduit) {
                    items.add(p);
                }
                listCategorie.setItems(items);
    }    

    @FXML
    private void AddCategorie(ActionEvent event) {
         CatService ps = new CatService();
       if (txtNom.getText().isEmpty() == false
                && txtCouleur.getText().isEmpty() == false ) {

       cat p = new cat();
       p.setCouleur(txtCouleur.getText());
       p.setNom(txtNom.getText());
       ps.AddCatPst(p);

        System.out.println("ajout");
            JOptionPane.showMessageDialog(null, "AJOUT Categorie  DONE");
       } else {
            JOptionPane.showMessageDialog(null, "erreur !!! remplir Correctement les champs");
        }
        ObservableList<cat> items =FXCollections.observableArrayList();
                List<cat> listProduit = ps.ShowCategorie();
                for(cat p : listProduit) {
                    items.add(p);
                }
                listCategorie.setItems(items);
    }

    @FXML
    private void ModifCateg(ActionEvent event) {
                
        CatService cs=new CatService();
          String nom= txtNom.getText();
          String couleur= txtCouleur.getText();
         System.out.println("cat a modifier est de numero id="+idCat);
          cat a =new cat(idCat,nom,couleur);
                   System.out.println(a);

       cs.UpdateCategorie(a,idCat);
    ObservableList<cat> items =FXCollections.observableArrayList();
                List<cat> listProduit = cs.ShowCategorie();
                for(cat p : listProduit) {
                    items.add(p);
                }
                listCategorie.setItems(items);

      
            
         
   
  
    }

    @FXML
    private void catSelect(MouseEvent event) {
        
          txtNom.setText(listCategorie.getSelectionModel().getSelectedItem().getNom());  
          
            txtCouleur.setText(listCategorie.getSelectionModel().getSelectedItem().getCouleur());

          idCat=ps.getidCat(listCategorie.getSelectionModel().getSelectedItem().getNom());
  
    }

  /*  @FXML
    private void triee(ActionEvent event) {
                ObservableList<Article> items =FXCollections.observableArrayList();
        List<Article> listarticle = ps.ShowArticle();
       Set<Article> liste= ps.tri(listarticle);
       for(Article r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        txtListArticle.setItems(items);
    }
*/
    

    @FXML
    private void supprimerCat(ActionEvent event) {
           CatService ps = new CatService();
          ps.DeleteCategorie(idCat);
            ObservableList<cat> items =FXCollections.observableArrayList();
                List<cat> listProduit = ps.ShowCategorie();
                for(cat p : listProduit) {
                    items.add(p);
                }
                listCategorie.setItems(items);

    }
    
}
