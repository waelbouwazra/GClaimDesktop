/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Produit;
import Services.ProduitService;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

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
   
   
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnsupp;
    @FXML
    private ChoiceBox<Integer> suppchoicebox;

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
                  ObservableList<Integer> langs = FXCollections.observableArrayList(ps.getIdproduit());
        suppchoicebox.setItems(langs);
     
      
    }    
@FXML
    private void DeleteProduct(ActionEvent event) {
        ps.DeleteProduit(suppchoicebox.getValue());
      ObservableList<Produit> items =FXCollections.observableArrayList();
        List<Produit> listprod = ps.ShowProduit();
        for(Produit r : listprod) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtListProd.setItems(items);
    ObservableList<Integer> langs = FXCollections.observableArrayList(ps.getIdproduit()
    );
        suppchoicebox.setItems(langs);
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
    
   

}
