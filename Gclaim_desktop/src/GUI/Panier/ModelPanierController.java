/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class ModelPanierController implements Initializable {

    @FXML
    private Button deleteButton;
    @FXML
    private Text nomProduit;
    @FXML
    private Text descriptionProduit;
    @FXML
    private Text prixProduit;
    @FXML
    private Text quantiteProduit;
    @FXML
    private Pane addBtn;
    @FXML
    private Pane minusBtn;
    @FXML
    private Button editButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
