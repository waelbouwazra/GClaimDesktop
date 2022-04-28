/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Article;

import Services.ArticleService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pie;

     private ArticleService ca = new ArticleService();
    @FXML
    private Button btnRetour;
    @FXML
    private AnchorPane mainPane;
    /**
     * Initializes the controller class.
     */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             pie.setTitle("Statistique des interactions :"); 
        pie.getData().setAll(new PieChart.Data("nombre vu faible <5", ca.Recherche3()), new PieChart.Data("nombre de vu [6,10]", ca.Recherche1()),
                new PieChart.Data("nombre vu [10,15]", ca.Recherche2()), new PieChart.Data("nombre [15,20]", ca.Recherche4()));
        
    }    

    @FXML
    private void loadMenu(ActionEvent event) {
                   AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("ShowArticle.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
           // mainPane.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
