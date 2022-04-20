package GUI.Profil;


import Entities.Profil;
import Front.*;
import GUI.MenuFrontController;
import Services.ProfilService;
import Services.ServiceUser;
import Tools.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.scene.layout.Pane;

public class ShowAllProfilController implements Initializable {

    public static Profil currentAbo;

    @FXML
    public Text topText;
    public Button addButton;
    @FXML
    public VBox mainVBox;
    ProfilService rs=new ProfilService();
    private ServiceUser US;
    @FXML
    private AnchorPane mainPain;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         US = new ServiceUser();
        List<Profil> listAbo = rs.ShowProfil();
        Collections.reverse(listAbo);

        if (!listAbo.isEmpty()) {
            for (Profil abo : listAbo) {
                mainVBox.getChildren().add(makeAboModel(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeAboModel(
            Profil abo
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_PRO)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
                   ((Pane) innerContainer.lookup("#modifierequipe")).setVisible(false);
            ((Pane) innerContainer.lookup("#rejoindreequipe")).setVisible(false);
            ((Pane) innerContainer.lookup("#reserverrdv")).setVisible(false);
            ((Text) innerContainer.lookup("#createdAtText")).setText("Username Coach : " + abo.getUsername());
            ((Text) innerContainer.lookup("#sdpIdText")).setText("Description : " + abo.getDescription());
            ((Text) innerContainer.lookup("#etatText")).setText("Jeu : " + abo.getGame());
            ((Text) innerContainer.lookup("#etatText1")).setText("Numero : " + abo.getNumero());
                if(US.currentUser.getId()==abo.getUser().getId())
            {((Pane) innerContainer.lookup("#modifierequipe")).setVisible(true);
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierAbo(abo));
            
           ((Pane) innerContainer.lookup("#rejoindreequipe")).setVisible(true);
           ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerAbo(abo));
            }
                else 
                {
                            ((Pane) innerContainer.lookup("#reserverrdv")).setVisible(true);
                            ((Button) innerContainer.lookup("#btnrdv")).setOnAction((event) -> reserverRdv(abo));
            }

                

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    private void modifierAbo(Profil abo) {
        currentAbo = abo;
MainWindowController.getInstance().loadInterface(Constants.FXML_UPDATE_PROFIL);   
    
    }

    private void supprimerAbo(Profil abo) {
        currentAbo = abo;
System.out.println(currentAbo);
System.out.println("id: "+abo.getId());

        rs.DeleteProfil(abo.getId());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Verification");
		alert.setHeaderText("Results:");
		alert.setContentText("Profil Supprimer avec succes");

		alert.showAndWait();
        }

    private void reserverRdv(Profil abo) {
        currentAbo = abo;
        MainWindowController.getInstance().loadInterface(Constants.FXML_RDV_COACH);   

        
    }
    }

