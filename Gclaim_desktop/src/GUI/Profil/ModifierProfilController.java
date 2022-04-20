/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;

import Entities.Profil;
import Front.MainWindowController;
import Services.ProfilService;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private TextField game;
    @FXML
    private TextField numero;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button listh;
    @FXML
    private TextField description;
    @FXML
    private Button btnSubmit1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentAbo=ShowAllProfilController.currentAbo;
       ObservableList<String> langs = FXCollections.observableArrayList("open", "close");
       
        username.setText(currentAbo.getUsername());
        description.setText(currentAbo.getDescription());
        game.setText(currentAbo.getGame());
        numero.setText(String.valueOf(currentAbo.getNumero()));
        

        
        
    }    

    @FXML
    private void Modifier_votre_Equipe(ActionEvent event) {
        if (!description.getText().isEmpty() && !game.getText().isEmpty() && !numero.getText().isEmpty())
        {
            Profil e=new Profil(currentAbo.getId(),username.getText(),description.getText(),game.getText(),Integer.parseInt(numero.getText()),US.currentUser);
           
            System.out.println(e);
            rs.UpdateProfil(e);
             MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_PROFIL);
        }
        else
             JOptionPane.showMessageDialog(null, "L'UTILISATEUR EST INVALIDE");
    }

    @FXML
    private void control(KeyEvent event) {
    }

    @FXML
    private void key(KeyEvent event) {
    }


    @FXML
    private void GotoHotelList(ActionEvent event) {
    }

    @FXML
    private void handleRetour(ActionEvent event) {
    }
    
}