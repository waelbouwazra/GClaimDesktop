/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;

import Entities.Profil;
import Entities.Rdv;
import Front.MainWindowController;
import Services.ProfilService;
import Services.RdvService;
import Services.ServiceUser;
import static Services.ServiceUser.currentUser;
import Tools.Constants;
import Tools.MaConnection;
import Tools.SendEmail;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author yusse
 */
public class AjoutRdvController implements Initializable {
private ServiceUser US=new ServiceUser();
PreparedStatement pst= null;
ResultSet rst= null;
//   MaConnection con = new MaConnection();
RdvService rs=new RdvService();
 public static Profil currentAbo;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnSubmit;
    @FXML
    private DatePicker dateP;
    @FXML
    private Text topText;
     int generatedCode;
 private SendEmail sendEmail;
    @FXML
    private Pane code;
    @FXML
    private Button confirmer;
    @FXML
    private TextField codeInput;
    @FXML
    private Pane choixdate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentAbo=ShowAllProfilController.currentAbo;
                                generatedCode = 0;
                 code.setVisible(false);



    }    


 @FXML
    private void addRdv(ActionEvent event) {
        
        int verif=0;
        if(dateP.getValue()!=null){
        LocalDate value = dateP.getValue();
         LocalDate today = LocalDate.now();
         
         if(value.isAfter(today)){
             
                try {
                String query ="select * from rdv where coachname = ?";
         
           
       
                pst = MaConnection.getInstance().getConnection().prepareStatement(query);
                pst.setString(1,currentAbo.getUsername());
                rst = pst.executeQuery();

               while (rst.next()){
               System.out.println("nom coach: "+rst.getString("coachname"));

             
                     String AnotherDate=value.toString();
                    if(rst.getString("date").equals(AnotherDate))
                    {
                        verif=1;
                                          

                    }}
               pst.close();
                rst.close();
            } catch (SQLException ex) {
                // Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                if(verif==1)
                {
                Alert alert = new Alert(Alert.AlertType.WARNING, "La date est deja prise", ButtonType.OK);            
     alert.showAndWait();
                    }
               else{
             
         Rdv e=new Rdv(currentAbo,US.currentUser,value);
           
            System.out.println(e);
            rs.AddRdv(e);
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Verification");
		alert.setHeaderText("Results:");
		alert.setContentText("Rdv ajouter avec succes.  Confirmer votre rendez-vous en entrant le code envoyer sur votre boite email! ");
                alert.showAndWait();
           try {
                int min = 10000;
                int max = 99999;
 
                generatedCode = (int) Math.floor(Math.random() * (max - min + 1) + min);
                sendEmail = new SendEmail("gclaimpidev@gmail.com", "Gclaim2022", US.currentUser.getEmail(), "Confirmer le rendez-vous ", "<center style=\"width: 100%; background-color: #f1f1f1;\"><div style=\"display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;\"></div><div style=\"max-width: 600px; margin: 0 auto;\" class=\"email-container\">"
                + "  <table align=\"center\" role=\"presentation\" cellspacing=\"0\" cellpadding=\"0\"border=\"0\" width=\"100% \" style=\"margin: auto;\"><tr>"
                + "<td valign=\"top\" class=\"bg_white\" style=\"padding: 1em 2.5em 0 2.5em;\">"
                + "<table role=\"presentation\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tr>"
                + "<td class=\"logo\" style=\"text-align: center; color: #c24400;\" ><h1>G_CLAIM</a></h1></td></tr></table></td> </tr><br> <br>"
              
                + " <tr> <td valign=\"middle\" class=\"hero bg_white\" style=\"background-image: url('https://www.coastline.edu/_files/img/750-421/esports-fist-bump.jpg');opacity: 0.88; background-size: cover; height: 400px;\">"
                + "<div class=\"overlay\"></div> <table><tr><td><div class=\"text\" style=\"padding: 0 4em; text-align: center;\">"
                + "<h2 >A PROPOS DE G_CLAIM</h2><h4 style=\"color: #ffffff;\" +\">G-Claim est Bien évidemment les MOBA comme DOTA et STR sont les incontournables du esport car ce sont les jeux qui rassemblent la plus grosse communauté de joueurs. Vous ne serez donc pas surpris de pourvoir parier sur des matches de League of Legends, Dota 2 et Starcraft. Ce trio est proposé par les meilleurs bookmakers du monde.</h4>"
                + "</div></td></tr> </table></td> </tr>"
                + "<tr> <td valign=\"middle\" class=\"intro bg_white\" style=\"padding: 2em 0 4em 0;\"><table><tr><td><div class=\"text\" style=\"padding: 10 2.5em; text-align: center; margin-left:500\">"
                + "<h2 style=\"margin-left:150px;\" +\" >Récupérer votre mot de passe</h2><h2 style=\"margin-left:150px;\" +\">Bonjour!" + US.currentUser.getUsername() +" </h2><p  style=\"margin-left:150px;\" +\" >Voici le code pour confirmer le rendez-vous</p>"
                + "<p><a  style=\"margin-left:150px;color: #c24400;\" +\">" + generatedCode + "</a></p></div></td></tr></table> </td></tr>"
                + " </center>"); 
                
 code.setVisible(true);
 choixdate.setVisible(false);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            
                }
         }
         else {
              Alert alert = new Alert(Alert.AlertType.WARNING, "La date choisie est fausse", ButtonType.OK);            
     alert.showAndWait();
         }}
        else
        {
        Alert alert = new Alert(Alert.AlertType.WARNING, "Select a Date", ButtonType.OK);            
     alert.showAndWait();
        }
    }

    @FXML
    private void test(MouseEvent event) {
         LocalDate value = dateP.getValue();
         System.out.println(value);
    }

    @FXML
    private void confirmercode(ActionEvent event) {
         if (Integer.toString(generatedCode).equals(codeInput.getText())) {
            System.out.println("code correct");
      
     
            rs.verify(currentAbo,US.currentUser);
            MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_PROFIL);
       
        } else {
            addNotifications("erreur", "code incorrect");
        }
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
