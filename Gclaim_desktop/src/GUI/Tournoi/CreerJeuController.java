/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

import Entities.Jeu;
import Services.ServiceJeu;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
public class CreerJeuController implements Initializable {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnAjouter;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtNOM;
    @FXML
    private Button btngetback;
    @FXML
    private TextField txtCreateur;
    @FXML
    private Label nomHebr;
    ServiceJeu rs = new ServiceJeu();
    @FXML
    private Label alertN;
    @FXML
    private Label alertD;
    @FXML
    private Label alerc;
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
        // TODO
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
    private void addJeu(ActionEvent event) {
        alertN.setText("");
        alertD.setText("");
        alerc.setText("");
        Boolean verif = true;

        if (txtNOM.getText().equals("")) {
            alertN.setText("Remplir le champs !!");
            verif = false;
        } else if (txtNOM.getText().length() <= 8) {
            alertN.setText("Nom Jeu peut contenir au moins 8 caractéres !!");
            verif = false;
        }
        
         if (txtDescription.getText().equals("")) {
            alertD.setText("Remplir ce champs !!");
            verif = false;
        } 
          if (txtCreateur.getText().equals("")) {
            alerc.setText("Remplir ce champs !!");
            verif = false;
        } 
            List<Jeu> listuser = rs.ShowJeu();
        for(Jeu r : listuser) {
            String ch = r.toString();
            if((r.getNomjeu().equals(txtNOM.getText()))&&(r.getDescription().equals(txtDescription.getText()))&&(r.getCreateur().equals(txtCreateur.getText())))
            {
                verif=false;
                Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setTitle("Ce jeu existe deja");
            alertt.setContentText("Veuiller changer l'un des Champs !");
            alertt.show(); 
            }
        }
       
        if (verif == true) {
        if (!txtNOM.getText().isEmpty() && !txtDescription.getText().isEmpty() && !txtCreateur.getText().isEmpty())
        {
            Jeu e= new Jeu( txtNOM.getText(), txtDescription.getText(), txtCreateur.getText());  
            e.setImage(imgpath.getText());
            rs.AddJeuPst(e);
            alertN.setText("");
            alertD.setText("");
            alerc.setText("");
            AnchorPane pane; 
            try {
            pane = FXMLLoader.load(getClass().getResource("listejeux.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
           // btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else
             JOptionPane.showMessageDialog(null, "Le JEU EST INVALIDE");
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
