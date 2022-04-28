/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;
import Entities.Categorie;
import Entities.Jeu;
import Entities.Produit;
import Services.ProfilService;
import Entities.Profil;
import Entities.Utilisateur;
import Entities.cat;
import Front.TopBarController;
import Services.ProduitService;
import Services.ServiceJeu;
import Services.ServiceUser;
import Tools.Animations;
import Tools.Constants;
import Tools.MaConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author yusse
 */
public class AjouterProfilController implements Initializable {
PreparedStatement pst= null;
ResultSet rs= null;
   MaConnection con = new MaConnection();
    private TextField txtmail;
    @FXML
    private TextField username;
    @FXML
    private ChoiceBox<String> game;
    @FXML
    private TextField numero;
    @FXML
    private Button btnSubmit;
    private ProfilService ProfilService;
    @FXML
    private TextField description;
    @FXML
    private AnchorPane mainPane;
    private ServiceUser US;
    @FXML
    private Text topText;

     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        US = new ServiceUser();
        

 username.setText(US.currentUser.getUsername());
 
   numero.setText("");
    description.setText("");
        this.ProfilService= new ProfilService();
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
     private void addProfil(ActionEvent event) throws UnsupportedEncodingException, MalformedURLException, IOException {
     
       
        
 if (numero.getText().equals("") || description.getText().equals("") || username.getText().equals("")) {
                                       addNotifications("erreur","Enter Full Details");

 }else if (!numero.getText().matches("[\\d\\.]+")){
                                    addNotifications("erreur","Phone number cannot contain letters");

 }else{
     
     //Else Part
       ProfilService ps = new ProfilService();
       Profil p = new Profil();
       
       p.setUser(US.currentUser);
       
       p.setDescription( description.getText());
       p.setNumero(Integer.parseInt(numero.getText()));
       p.setUsername(username.getText());
       p.setGame(game.getValue());
    
       try {
                String query ="select * from profil where username = ?";
         
           
       
                pst = con.MaConnection().prepareStatement(query);
                pst.setString(1,US.currentUser.getUsername());
                rs = pst.executeQuery();

               if (rs.next()==true){ 
                    
                   
                    
                                     addNotifications("erreur", "Votre profil existe deja");

               }else{
                    
                         Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Verification");
		alert.setHeaderText("Results:");
		alert.setContentText("Profil ajouter avec succes");

		alert.showAndWait();
      
  
       
      ps.AddProfil(p); 
              System.out.println("message 1");

                // TODO Auto-generated method stub
		String message = "Junk characters? method sendMultipartTextMessage only send text message. If you want to send non text message, you should look to method sendDataMessage. Below is the code excerpt from android cts. It has example on how to send long messages.";		
		String phone = "+21620655933";
		String username = "abcd";
		String password = "1234";
		String address = "http://192.168.1.101";
		String port = "8090";
		
		URL url = new URL(
				address+":"+port+"/SendSMS?username="+username+"&password="+password+
				"&phone="+phone+"&message="+URLEncoder.encode(message,"UTF-8"));
		
		URLConnection connection = url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String inputLine;
		while((inputLine = bufferedReader.readLine()) !=null){
			System.out.println(inputLine);
		}
		bufferedReader.close();
               
               }
                                   System.out.println("message 2");

                
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                // Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
       
       
 }
    }

    private void handleRetour(ActionEvent event) {
      
          
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("ShowAllProfil.fxml"));
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

    @FXML
    private void control(KeyEvent event) {
        event.consume();
        
                 addNotifications("erreur", "Can't modify username");

        
    }
    private void addNotifications(String title, String content) {

        if (null != content) {
            if (content.length() > 50) {
                content = content.substring(0, 49) + "......";
            }
        }
        Notifications notificationBuilder = Notifications.create()
                .title(title)
                .text(content)
                .hideAfter(Duration.seconds(360))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }
}
