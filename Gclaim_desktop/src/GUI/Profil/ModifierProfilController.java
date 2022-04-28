/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;

import Entities.Jeu;
import Entities.Profil;
import Front.MainWindowController;
import Services.ProfilService;
import Services.ServiceJeu;
import Services.ServiceUser;
import static Services.ServiceUser.currentUser;
import Tools.Constants;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author souma
 */
public class ModifierProfilController implements Initializable {

    private TextField txtDescription;
    private TextField txtNOM;
    private ChoiceBox<String> EtatBox;
private ServiceUser US=new ServiceUser();

ProfilService rs=new ProfilService();
 public static Profil currentAbo;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private TextField username;
    @FXML
    private ComboBox<String> game;
    @FXML
    private TextField numero;
    @FXML
    private Button btnSubmit;
    @FXML
    private TextField description;
    @FXML
    private Text topText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentAbo=ShowAllProfilController.currentAbo;
       ObservableList<String> langs = FXCollections.observableArrayList("open", "close");
       
        username.setText(currentAbo.getUsername());
        description.setText(currentAbo.getDescription());
        
        numero.setText(String.valueOf(currentAbo.getNumero()));
        
ServiceJeu ServiceJeu =new ServiceJeu();
         ObservableList<String> items = FXCollections.observableArrayList();

// Set the ComboBox to use the items list
 List<Jeu> listProfil =ServiceJeu.ShowJeu();
 // System.out.println(catservice.ShowCategorie());
       //profilService.TriProfil(listProfil);
                for(Jeu p : listProfil) {
// Allow the user to update the items in the list
items.add(p.getNomjeu());


                }
                game.setItems(items);
        
        
    }    

    @FXML
    private void Modifier_votre_Equipe(ActionEvent event) {
        if (!description.getText().isEmpty() || !game.getValue().isEmpty() || !numero.getText().isEmpty())
        {
            Profil e=new Profil(currentAbo.getId(),username.getText(),description.getText(),game.getValue(),Integer.parseInt(numero.getText()),US.currentUser);
           
            System.out.println(e);
            rs.UpdateProfil(e);
             MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_PROFIL);
        }
        else
             JOptionPane.showMessageDialog(null, "L'UTILISATEUR EST INVALIDE");
    }

    @FXML
    private void control(KeyEvent event) {
        
        event.consume();
    }

    @FXML
    private void key(KeyEvent event) {
          if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) 
       
       
       event.consume();
    }


    
}