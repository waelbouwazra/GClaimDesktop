package GUI.Login;


import Entities.Equipe;
import Front.*;
import GUI.MenuFrontController;
import Services.ServiceEquipe;
import Services.ServiceUser;
import Tools.Constants;
import com.sun.glass.ui.Cursor;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ShowAllController implements Initializable {

    public static Equipe currentAbo;

    @FXML
    public Text topText;
    public Button addButton;
    public VBox mainVBox;
    ServiceEquipe rs=new ServiceEquipe();
    private ServiceUser US;
    @FXML
    private AnchorPane mainPain;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         US = new ServiceUser();
        List<Equipe> listAbo = rs.afficheEquipe();
        Collections.reverse(listAbo);

        if (!listAbo.isEmpty()) {
            for (Equipe abo : listAbo) {
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

    public Parent makeAboModel(Equipe abo) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_ABO)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Pane) innerContainer.lookup("#modifierequipe")).setVisible(false);
            ((Pane) innerContainer.lookup("#rejoindreequipe")).setVisible(false);
            ((Text) innerContainer.lookup("#createdAtText")).setText("NOM Equipe : " + abo.getNomEquipe());
            ((Text) innerContainer.lookup("#userIdText")).setText("Description : " + abo.getDescription());
            ((Text) innerContainer.lookup("#sdpIdText")).setText("Etat : " + abo.getEtat());
            ((Text) innerContainer.lookup("#dureeText")).setText("membre de l'équipe : " + rs.afficheUtilisateursduneEquipe(abo.getId()));
            ((Text) innerContainer.lookup("#etatText")).setText("Chef : " + abo.getChef());
            ((Text) innerContainer.lookup("#datetext")).setText("Date de création : " + abo.getDateCreation());
            
            if(US.currentUser.getUsername().equals(abo.getChef()))
            {((Pane) innerContainer.lookup("#modifierequipe")).setVisible(true);
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierAbo(abo));
            }
            if(abo.getEtat().equals("open")&& rs.userexisteinEquipe(abo.getId(), US.currentUser.getEmail()))
            {((Pane) innerContainer.lookup("#rejoindreequipe")).setVisible(true);
           ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerAbo(abo));
            }
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    private void modifierAbo(Equipe abo) {
        currentAbo = abo;
       // rs.updateEquipe(abo);
        MainWindowController.getInstance().loadInterface(Constants.FXML_UPDATE_EQUIPE);
    }

    private void supprimerAbo(Equipe abo) {
        currentAbo = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir rejoindre l'equipe ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
           rs.Rejoindreuneequipe(abo,US.currentUser);
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_ABO);
            
            }
        }

    
    }

