/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Equipe;
import Entities.Utilisateur;
import Entities.Produit;
import Front.MainWindowController;
import static GUI.Login.ShowAllController.currentAbo;
import Services.ProduitService;
import Services.ServiceUser;
import Tools.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author azizk
 */
public class ShowAllProdController implements Initializable {

    @FXML
    public static Produit currentProd;
    private AnchorPane mainPain;
    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox;
    ProduitService ps=new ProduitService();
    private ServiceUser US;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        US = new ServiceUser();
        List<Produit> listProd = ps.ShowProduit();
        Collections.reverse(listProd);

        if (!listProd.isEmpty()) {
            for (Produit abo : listProd) {
                mainVBox.getChildren().add(makeProdModel(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }
public Parent makeProdModel( Produit abo )  {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_PROD)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            
            ((Text) innerContainer.lookup("#createdAtText")).setText("Nom Produit : " + abo.getNom_produit());
            ((Text) innerContainer.lookup("#desctxt")).setText("Description : " + abo.getDescription());
            ((Text) innerContainer.lookup("#prixtxt")).setText("prix : " + abo.getPrix_produit());
            ((Text) innerContainer.lookup("#qtetxt")).setText("quantitée : " + abo.getQte_produit());
            ((Text) innerContainer.lookup("#nbrvu")).setText("nombre de vu : " + abo.getNbr_vu());
            ((Text) innerContainer.lookup("#dateajouttxt")).setText("Date ajout : " + abo.getDateAjout_produit());
            
            ((Pane) innerContainer.lookup("#supppane")).setVisible(false);
             ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierProd(abo));
             ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerProd(abo));
             
              
           
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    private void modifierProd(Produit abo) {
        currentProd = abo;
       // rs.updateEquipe(abo);
        MainWindowController.getInstance().loadInterface(Constants.FXML_UPDATE_PRODUIT);
    }

    private void supprimerProd(Produit abo) {
        currentProd = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer ce produit ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
           ps.DeleteProduit(abo.getId_produit());
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_PRODUIT);
            
            }
        }
    
}
