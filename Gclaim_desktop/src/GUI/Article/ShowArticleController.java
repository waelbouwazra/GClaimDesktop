/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Article;

import Entities.Article;
import Entities.cat;
import Services.ArticleService;
import Services.CatService;

import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ShowArticleController implements Initializable {
    @FXML
    private ListView<Article> txtListArticle;

    ArticleService ps = new ArticleService();
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnRetour;
    @FXML
    private TextField txtModifTitre;
    @FXML
    private TextField txtModifDesc;
    @FXML
    private TextField txtModifImage;
    @FXML
    private ChoiceBox<String> txtModifCat;
    @FXML
    private Button btnSupp;
    @FXML
    private Button btnModif;
    int id_article;
 CatService catservice = new CatService();
    @FXML
    private TextField inputRech;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ObservableList<Article> items =FXCollections.observableArrayList();
                List<Article> listProduit = ps.ShowArticle();
                for(Article p : listProduit) {
                    items.add(p);
                }
                txtListArticle.setItems(items);
 /**s  FilteredList<Article> filteredData = new FilteredList<>(list, e -> true);
            inputRech.setOnKeyReleased(e -> {
                inputRech.textProperty().addListener((ObservableValue, oldValue, newValue) -> {
                    filteredData.setPredicate((Predicate<? super Article>) articles -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();
                        if (articles.getTitre().toLowerCase().contains(lower)) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Article> sortedData = new SortedList<>(filteredData);
                sortedData.comparatorProperty().bind(ListView.());
                ListView.setItems(sortedData);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());*/
        

                
    }    

    @FXML
    private void loadMenu(ActionEvent event) {
              AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("ArticleMenu.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
           // mainPane.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void deleteArticle(ActionEvent event) {
          ArticleService ps = new ArticleService();
          ps.DeleteArticle(id_article);
            ObservableList<Article> items =FXCollections.observableArrayList();
                List<Article> listProduit = ps.ShowArticle();
                for(Article p : listProduit) {
                    items.add(p);
                }
                txtListArticle.setItems(items);

    }

    @FXML
    private void updateArticle(ActionEvent event) {
            
        ArticleService ps = new ArticleService();
        CatService cs=new CatService();
          String titre= txtModifTitre.getText();
          String desc= txtModifDesc.getText();
         String image= txtModifImage.getText();
         String cat=txtModifCat.getSelectionModel().getSelectedItem();
         int idCat=cs.getidCat(cat);
         cat c=new cat(idCat);
       
         System.out.println("id="+id_article);
         System.out.println("id="+idCat);
          Article a =new Article(id_article,titre,desc,image,c);
       ps.UpdateArticle(a, id_article);
    ObservableList<Article> items =FXCollections.observableArrayList();
                List<Article> listProduit = ps.ShowArticle();
                for(Article p : listProduit) {
                    items.add(p);
                }
                txtListArticle.setItems(items);

      
            
         
   
  
    }

    @FXML
    private void articleSelect(MouseEvent event) {
        
          txtModifTitre.setText(txtListArticle.getSelectionModel().getSelectedItem().getTitre());  
          
            txtModifDesc.setText(txtListArticle.getSelectionModel().getSelectedItem().getDescription());
            txtModifImage.setText(txtListArticle.getSelectionModel().getSelectedItem().getImage());

          id_article=ps.getIdArticle(txtListArticle.getSelectionModel().getSelectedItem().getTitre(), txtListArticle.getSelectionModel().getSelectedItem().getDescription());
   ObservableList<String> items = FXCollections.observableArrayList();

// Set the ComboBox to use the items list
 List<cat> listProfil =catservice.ShowCategorie();
 // System.out.println(catservice.ShowCategorie());
       //profilService.TriProfil(listProfil);
                for(cat p : listProfil) {
// Allow the user to update the items in the list
items.add(p.getNom());
System.out.println(p.getNom());

                }
txtModifCat.setItems(items);
int idCa=txtListArticle.getSelectionModel().getSelectedItem().getCat_id().getId();
cat ca=catservice.getIdCat(idCa);
txtModifCat.getSelectionModel().select(ca.getNom());
    }

    @FXML
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
    
}
