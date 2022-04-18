/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Login;

import Entities.Equipe;
import Front.MainWindowController;
import Services.ServiceEquipe;
import Services.ServiceUser;
import static Services.ServiceUser.currentUser;
import Tools.Constants;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author souma
 */
public class ModifierEquipeController implements Initializable {

    @FXML
    private AnchorPane mainPain;
    @FXML
    private Text topText;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtNOM;
    @FXML
    private ChoiceBox<String> EtatBox;
private ServiceUser US=new ServiceUser();
ServiceEquipe rs=new ServiceEquipe();
 public static Equipe currentAbo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentAbo=ShowAllController.currentAbo;
       ObservableList<String> langs = FXCollections.observableArrayList("open", "close");
        EtatBox.setItems(langs);
        txtNOM.setText(currentAbo.getNomEquipe());
        txtDescription.setText(currentAbo.getDescription());
        EtatBox.setValue(currentAbo.getEtat());
    }    

    @FXML
    private void Modifier_votre_Equipe(ActionEvent event) {
        if (!txtNOM.getText().isEmpty() && !txtDescription.getText().isEmpty() && !EtatBox.getValue().isEmpty())
        {
            Equipe e=new Equipe(currentAbo.getId(),txtNOM.getText(),txtDescription.getText(),EtatBox.getValue(),US.currentUser.getUsername(),US.currentUser);
           
            System.out.println(e);
            rs.updateEquipe(e);
             MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_ABO);
        }
        else
             JOptionPane.showMessageDialog(null, "L'UTILISATEUR EST INVALIDE");
    }
    
}
