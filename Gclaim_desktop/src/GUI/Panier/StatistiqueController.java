/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import Entities.Commande;
import Entities.Produit;
import Services.CommandeService;
import Services.LigneCommandeService;
import Services.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private PieChart pieChart;

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
        ProduitService ps = new ProduitService();
        LigneCommandeService lcs = new LigneCommandeService();
        List<Produit> lProduit = new ArrayList<>();
        ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
        PieChart pie = new PieChart();
        lProduit=ps.ShowProduit();
        for (Produit p :lProduit){
            
            System.out.println(p.getNom_produit()+"quantite="+lcs.getSommeQuantite(p.getId_produit()));
            PieChart.Data slice = new PieChart.Data(p.getNom_produit(), lcs.getSommeQuantite(p.getId_produit()));
            pieChartData.add(slice);
        }

       pieChart.setData(pieChartData);
       final Label caption = new Label("");
        caption.setTextFill(Color.WHITE);
        caption.setStyle("-fx-font: 12 arial;");
      for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    caption.setTranslateX(e.getSceneX());
                    caption.setTranslateY(e.getSceneY());

                    caption.setText(String.valueOf(data.getPieValue()));
                }
            });
        }
      // pieChart.setLegendSide(Side.LEFT);
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
