/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import Entities.Commande;
import Services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class StatistiqueController implements Initializable {

    @FXML
    private Text nomProduit;
    @FXML
    private Text nomProduit1;
    @FXML
    private Text nomProduit11;
    @FXML
    private Text nomProduit111;
    @FXML
    private Text txtMax;
    @FXML
    private Text txtMin;
    @FXML
    private Text txtMoy;
    @FXML
    private Text txtCA;
    @FXML
    private Text maxUsername;
    @FXML
    private Text maxDateAchat;
    @FXML
    private Text maxTotal;
    @FXML
    private Text maxLivrer;
    @FXML
    private Text minUsername;
    @FXML
    private Text minDateAchat;
    @FXML
    private Text minTotal;
    @FXML
    private Text minLivrer;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btngetback;
    @FXML
    private Text nomProduit1111;
    @FXML
    private Text txtNb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CommandeService commandeService = new CommandeService();
        List <Commande> l = new ArrayList<>();
        List <Commande> l1 = new ArrayList<>();
        l=commandeService.getTopCommande();
        Commande c = l.get(0);
        maxUsername.setText("Username :"+c.getUser().getUsername());
        maxDateAchat.setText("Date Achat :"+c.getDate_achat());
        maxTotal.setText("Total :"+c.getTotal());
        maxLivrer.setText("Livrer :"+c.isLivrer());
        l1=commandeService.getLeastCommande();
        Commande cmin= l1.get(0);
        minUsername.setText("Username :"+cmin.getUser().getUsername());
        minDateAchat.setText("Date Achat :"+cmin.getDate_achat()+"");
        minTotal.setText("Total :"+cmin.getTotal()+"");
        minLivrer.setText("Livrer :"+cmin.isLivrer()+"");
        IntSummaryStatistics iss = commandeService.getStatistics();
        txtMin.setText(iss.getMin()+"");
        txtMax.setText(iss.getMax()+"");
        txtMoy.setText(iss.getAverage()+"");
        txtCA.setText(iss.getSum()+"");
        txtNb.setText(iss.getCount()+"");
    }    

    @FXML
    private void retourAction(ActionEvent event) {
                      AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuPanier.fxml"));
            mainPane.getChildren().setAll(pane);
            btngetback.setTextFill(Color.WHITE);
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
