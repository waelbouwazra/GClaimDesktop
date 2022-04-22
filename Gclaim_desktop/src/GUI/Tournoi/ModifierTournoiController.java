/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

import Entities.Equipe;
import Entities.Tournoi;
import Entities.Jeu;
import Front.MainWindowController;
import static GUI.Tournoi.ModifierTournoiController.currentAbo;
import GUI.Tournoi.ShowAllTournoiController;
import Services.ServiceTournoi;
import Services.ServiceJeu;
import Services.ServiceUser;
import Tools.Constants;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author elyes
 */
public class ModifierTournoiController implements Initializable {
    private ServiceUser US=new ServiceUser();
    ServiceTournoi rs=new ServiceTournoi();
    ServiceJeu rs2=new ServiceJeu();
    public static Tournoi currentAbo;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Text topText;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtNOM;
    @FXML
    private Button btnmodif;
    @FXML
    private DatePicker dateE;
    @FXML
    private TextField txtHeure;
    @FXML
    private ChoiceBox<String> EtatBox;
    @FXML
    private Button btngetback;
    @FXML
    private Label alertN;
    @FXML
    private Label alertD;
    @FXML
    private Label alertJ;
    @FXML
    private Label alertDA;
    @FXML
    private Label alertH;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     currentAbo=ListetournoisController.currentAbo;
      
       ObservableList<String> langs = FXCollections.observableArrayList(rs2.getNomJeu());
        EtatBox.setItems(langs);
        txtNOM.setText(currentAbo.getNomtournoi());
        txtHeure.setText(currentAbo.getHeurev());
        txtDescription.setText(currentAbo.getDescription());
        
        dateE.setValue(LocalDate.parse(currentAbo.getDatev()));
        EtatBox.setValue(currentAbo.getJeu().getNomjeu());
    }    

    @FXML
    private void Modifier_votre_Tournoi(ActionEvent event) {
        alertN.setText("");
        alertD.setText("");
        alertJ.setText("");
        alertDA.setText("");
        alertH.setText("");

        Boolean verif = true;

        if (txtNOM.getText().equals("")) {
            alertN.setText("Remplir le champs !!");
            verif = false;
        } else if (txtNOM.getText().length() <= 8) {
            alertN.setText("Nom Tournoi peut contenir au moins 8 caractéres !!");
            verif = false;
        }
          if (txtDescription.getText().equals("")) {
            alertN.setText("Remplir le champs !!");
            verif = false;
        }
          if (dateE.getValue() == null) {
            alertDA.setText("Remplir le champs !!");
            verif = false;
        }
        if (dateE.getValue() != null) {
            // Control Date Arrivé < Date Départ
            Date d1 = Date.valueOf(dateE.getValue());
            Date d = new Date(System.currentTimeMillis());
            

            if (d1.before(d)) {
                alertDA.setText(" Date Evenement est inferieur a la Date d'aujourd'hui !!");
                verif = false;
            }
        }
            if (txtHeure.getText().equals("")) {
            alertH.setText("Remplir le champs !!");
            verif = false;
        }
        if (verif == true) {
        if (!txtNOM.getText().isEmpty() && !txtDescription.getText().isEmpty() && !EtatBox.getValue().isEmpty())
        {
            Tournoi e=new Tournoi(currentAbo.getId(),txtNOM.getText(),txtDescription.getText(),dateE.getValue().toString(),txtHeure.getText(),rs2.ShowJeuByNom(EtatBox.getValue()));
           
            System.out.println(e);
            System.out.println(rs2.ShowJeuByNom(EtatBox.getValue()));

            rs.UpdateTournoi(e,currentAbo.getId());
            alertN.setText("");
            alertD.setText("");
            alertJ.setText("");
            alertDA.setText("");
            alertH.setText("");
            AnchorPane pane; 
            try {
            pane = FXMLLoader.load(getClass().getResource("listeTournois.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
          //  btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }        }
        else
             JOptionPane.showMessageDialog(null, "Le TOURNOI EST INVALIDE");
    }
    }
    @FXML
    private void test(MouseEvent event) {
    }
      @FXML
    private void loadMenu(ActionEvent event) {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuTournoi.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            //btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
