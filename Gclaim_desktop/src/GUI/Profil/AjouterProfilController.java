/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;
import Entities.Categorie;
import Entities.Produit;
import Services.ProfilService;
import Entities.Profil;
import Entities.Utilisateur;
import Services.ProduitService;
import Tools.MaConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yusse
 */
public class AjouterProfilController implements Initializable {
PreparedStatement pst= null;
ResultSet rs= null;
   MaConnection con = new MaConnection();
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
    private ProfilService ProfilService;
    @FXML
    private TextField description;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnSubmit1;

     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        
 username.setText("");
  game.setText("");
   numero.setText("");
    description.setText("");
        this.ProfilService= new ProfilService();
        
    }      


    @FXML
    private void GotoHotelList(ActionEvent event) {
    }
    @FXML
     private void addProfil(ActionEvent event) {
     
       /*  
       try {
                String query ="select user_id from profil where username = ?";
         
           
       
                pst = con.MaConnection().prepareStatement(query);
                pst.setString(1,username.getText());
                rs = pst.executeQuery();
                while(rs.next())
                {
                     p.setUser(rs.getUt);
                    
                    
                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                // Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
       
        
 if (numero.getText().equals("") || description.getText().equals("") || username.getText().equals("") || game.getText().equals("") ) {
     Alert alert = new Alert(Alert.AlertType.WARNING, "Enter Full Details", ButtonType.OK);            
     alert.showAndWait();
 }else if (!numero.getText().matches("[\\d\\.]+")){
     Alert alert = new Alert(Alert.AlertType.WARNING, "It Can not contain letters", ButtonType.OK);            
     alert.showAndWait();
 }else{
     
     //Else Part
       ProfilService ps = new ProfilService();
       Profil p = new Profil();
       Utilisateur c = new Utilisateur(104);
       p.setUser(c);
       
       p.setDescription( description.getText());
       p.setNumero(Integer.parseInt(numero.getText()));
       p.setUsername(username.getText());
       p.setGame(game.getText());
 
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Verification");
		alert.setHeaderText("Results:");
		alert.setContentText("Profil ajouter avec succes");

		alert.showAndWait();
      
  
       
      ps.AddProfil(p);
 }
    }

    @FXML
    private void handleRetour(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AfficherProfil.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
                Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void key(KeyEvent event) {
         /* if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
        SOME WARNING MSG 
       
       event.consume();
    }*/
    }
}
