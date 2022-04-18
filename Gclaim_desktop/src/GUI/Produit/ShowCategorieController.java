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
import java.util.List;
import java.util.ResourceBundle;
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
    private ChoiceBox<Integer> suppchoicebox;
    @FXML
    private Button btnsupp;
 
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
                  ObservableList<Integer> langs = FXCollections.observableArrayList(cs.getIdcategorie());
        suppchoicebox.setItems(langs);
       
          
    }
@FXML
    private void DeleteCateg(ActionEvent event) {
        cs.DeleteCategorie(suppchoicebox.getValue());
      ObservableList<Categorie> items =FXCollections.observableArrayList();
        List<Categorie> listprod = cs.ShowCategorie();
        for(Categorie r : listprod) {
            String ch = r.toString();
            items.add(r);
        }
       
    listviewcateg.setItems(items);
    ObservableList<Integer> langs = FXCollections.observableArrayList(cs.getIdcategorie()
    );
        suppchoicebox.setItems(langs);
    }    
    
}
