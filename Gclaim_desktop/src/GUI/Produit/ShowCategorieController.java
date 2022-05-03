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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author azizk
 */
public class ShowCategorieController implements Initializable {
PreparedStatement pst= null;
ResultSet rs= null;

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
         
        ;
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer cette Categorie ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if(listviewcateg.getSelectionModel().getSelectedItem()!=null){                      
        c = listviewcateg.getSelectionModel().getSelectedItem();
        addNotifications("categorie suprimée avec succes", "categorie supprimée");
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
        cs.DeleteCategorie(c.getId_categorie());
        }
        
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir modifier cette Categorie ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            addNotifications("categorie modifiée avec succes", "categorie modifiée");
       cs.UpdateCategorie(a, id_categ);
        alerteajout1.setText("modification avec succes!");
          alerteajout.setText("");
        }
            
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
        if(listviewcateg.getSelectionModel().getSelectedItem()!=null){                      
        nomc.setText(listviewcateg.getSelectionModel().getSelectedItem().getNom_categorie());  
          
            nomt.setText(listviewcateg.getSelectionModel().getSelectedItem().getType_categorie());
           

          id_categ=cs.getIdCateg(listviewcateg.getSelectionModel().getSelectedItem().getNom_categorie(), listviewcateg.getSelectionModel().getSelectedItem().getType_categorie());
         
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
         
        
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
    private void addNotifications(String title, String content) {

        if (null != content) {
            if (content.length() > 50) {
                content = content.substring(0, 49) + "......";
            }
        }
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(content)
                .hideAfter(Duration.seconds(360))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }
}
