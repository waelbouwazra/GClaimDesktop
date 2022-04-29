/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

import Entities.Tournoi;
import Entities.Jeu;
import Services.ServiceJeu;
import Services.ServiceTournoi;
import Tools.Constants;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author elyes
 */
public class CreerTournoiController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnAjouter;
    @FXML
    
    private TextField txtDescription;
    @FXML
    private TextField txtNOM;
    @FXML
    private DatePicker dateE;
    @FXML
    private TextField txtHeure;
    @FXML
    private ChoiceBox<String> EtatBox;
    ServiceTournoi rs = new ServiceTournoi();
    ServiceJeu rs2 = new ServiceJeu();
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
    @FXML
    private Label nomHebr;
    @FXML
    private ImageView ImageviewID;
    @FXML
    private Button btnImage;
    @FXML
    private Label imgpath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> langs = FXCollections.observableArrayList(rs2.getNomJeu());
        EtatBox.setItems(langs);
    }    



    @FXML
    private void addTournoi(ActionEvent event) {
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
               List<Tournoi> listuser = rs.ShowTournoi();
        for(Tournoi r : listuser) {
            String ch = r.toString();
            if((r.getNomtournoi().equals(txtNOM.getText()))&&(r.getDescription().equals(txtDescription.getText()))&&(r.getDatev().equals(dateE.getValue().toString()))&&(r.getHeurev().equals(txtHeure.getText())))
            {
                verif=false;
                Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setTitle("Ce Tournoi existe deja");
            alertt.setContentText("Veuiller changer l'un des Champs !");
            alertt.show(); 
            }
        }
        if (verif == true) {
      if (!txtNOM.getText().isEmpty() && !txtDescription.getText().isEmpty() && !EtatBox.getValue().isEmpty())
        {
            Tournoi e=new Tournoi(txtNOM.getText(),txtDescription.getText(),dateE.getValue().toString(),txtHeure.getText(),imgpath.getText(),rs2.ShowJeuByNom(EtatBox.getValue()));
            rs.AddTournoiPst(e);
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
         //   btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
             JOptionPane.showMessageDialog(null, "Le TOURNOI EST INVALIDE");
        }
    }
 @FXML
    private void loadMenu(ActionEvent event) {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuTournoi.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void Addimage(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            ImageviewID.setImage(image);
            ImageviewID.setFitWidth(200);
            ImageviewID.setFitHeight(200);
            ImageviewID.scaleXProperty();
            ImageviewID.scaleYProperty();
            ImageviewID.setSmooth(true);
            ImageviewID.setCache(true);
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            byte[] person_image = bos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger("ss");
        }
      imgpath.setText(file.getAbsolutePath());
    }
    
}
