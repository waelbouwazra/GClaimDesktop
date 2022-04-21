/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Equipe;
import Entities.Produit;
import static GUI.Login.ModifierEquipeController.currentAbo;
import GUI.Login.ShowAllController;
import Services.ProduitService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author azizk
 */
public class ModifierProduitController implements Initializable {

    @FXML
    private AnchorPane mainPain;
    @FXML
    private Text topText;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtNOM;
    @FXML
    private TextField txtprix1;
    @FXML
    private TextField txtqt1;
    @FXML
    private ChoiceBox<Integer> comboBoxID1;
    public static Produit currentProd;
    ProduitService ps = new ProduitService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void Modifier_votre_Equipe(ActionEvent event) {
        
    }
    
}
