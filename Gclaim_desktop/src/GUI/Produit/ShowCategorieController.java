/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Categorie;
import Services.CategorieService;
import Tools.MaConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author azizk
 */
public class ShowCategorieController implements Initializable {
PreparedStatement pst= null;
ResultSet rs= null;
   MaConnection con = new MaConnection();
    @FXML
    private Button btnretour;
    CategorieService cs = new CategorieService();
    @FXML
    private TextField nomc;
    @FXML
    private TextField nomt;
    @FXML
    private ListView<Categorie> listviewcateg;
    @FXML
    private Button btnsupp;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnmodif;
    int id_categ;
    @FXML
    private Text alerteajout;
    @FXML
    private Text alerteajout1;
    @FXML
    private TextField recherche;
    @FXML
    private Button tricateg;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<Categorie> items =FXCollections.observableArrayList();
                List<Categorie> listCateg = cs.ShowCategorie();
                for(Categorie p : listCateg) {
                    items.add(p);
                }
                listviewcateg.setItems(items);

       
          
    }
    @FXML
    private void DeleteCateg(ActionEvent event) {
        Categorie c = new Categorie();
        c = listviewcateg.getSelectionModel().getSelectedItem();
        cs.DeleteCategorie(c.getId_categorie());
        
         ObservableList<Categorie> items =FXCollections.observableArrayList();
                List<Categorie> listCateg = cs.ShowCategorie();
                for(Categorie p : listCateg) {
                    items.add(p);
                }
                listviewcateg.setItems(items);

     
    }

    
    @FXML
    private void updateCategorie(ActionEvent event) {
                   Boolean verif = true;

        CategorieService cs = new CategorieService();
        
          String nom_categorie= nomc.getText();
          String type_categorie= nomt.getText();
        
        //controle saisie nom categorie
       if (nomc.getText().equals("")) {
            alerteajout.setText("Remplir le champs !!");
            verif = false;
        }else if (nomt.getText().equals("")) {
            alerteajout1.setText("Remplir le champs !!");
            verif = false;
        } else {
            Categorie a =new Categorie(id_categ,nom_categorie,type_categorie);
          
       cs.UpdateCategorie(a, id_categ);
            alerteajout1.setText("modification avec succes!");
          alerteajout.setText("");
      }
       
         System.out.println("id="+id_categ);
         
          
          
    ObservableList<Categorie> items =FXCollections.observableArrayList();
        List<Categorie> listprod = cs.ShowCategorie();
        for(Categorie r : listprod) {
            String ch = r.toString();
            items.add(r);
        }
                listviewcateg.setItems(items);

  
    }
    @FXML
    private void CategorieSelect(MouseEvent event) {
        
          nomc.setText(listviewcateg.getSelectionModel().getSelectedItem().getNom_categorie());  
          
            nomt.setText(listviewcateg.getSelectionModel().getSelectedItem().getType_categorie());
           

          id_categ=cs.getIdCateg(listviewcateg.getSelectionModel().getSelectedItem().getNom_categorie(), listviewcateg.getSelectionModel().getSelectedItem().getType_categorie());
         
        
    }
    
    @FXML
    private void chercherCateg(KeyEvent event) {
          
     
       List<Categorie> listCateg = cs.ShowCategorie();
       List<Categorie> c = new ArrayList<>();
        String tchoix=recherche.getText();
       if (tchoix.equals("")){
           
       
           // int nchoix = Integer.parseInt(tchoix);
            c = listCateg;
              
        } else {
          c =  cs.Rechercher(listCateg,tchoix);
       }
          ObservableList<Categorie> items =FXCollections.observableArrayList();
       for(Categorie r : c) {
            String ch = r.toString();
            items.add(r);
        } 
      
        listviewcateg.setItems(items);
    }
 @FXML
    private void tric(ActionEvent event) {
         ObservableList<Categorie> items =FXCollections.observableArrayList();
        List<Categorie> listcat = cs.ShowCategorie();
       Set<Categorie> liste= cs.TriCategorie(listcat);
       for(Categorie r : liste) {
            String ch = r.toString();
            items.add(r);
        }
        listviewcateg.setItems(items);
    }
    
}
