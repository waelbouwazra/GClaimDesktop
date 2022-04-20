/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;

import Entities.Profil;
import Entities.Rdv;
import Services.ProfilService;
import Services.RdvService;
import Services.ServiceUser;
import Tools.MaConnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author yusse
 */
public class AjoutRdvController implements Initializable {
private ServiceUser US=new ServiceUser();
PreparedStatement pst= null;
ResultSet rst= null;
   MaConnection con = new MaConnection();
RdvService rs=new RdvService();
 public static Profil currentAbo;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnSubmit;
    @FXML
    private Button btnSubmit1;
    @FXML
    private DatePicker dateP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        currentAbo=ShowAllProfilController.currentAbo;
               


    }    


    @FXML
    private void handleRetour(ActionEvent event) {
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
         
           
       
                pst = con.MaConnection().prepareStatement(query);
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
		alert.setContentText("Rdv ajouter avec succes");
                alert.showAndWait();
         
            
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

   
    
}
