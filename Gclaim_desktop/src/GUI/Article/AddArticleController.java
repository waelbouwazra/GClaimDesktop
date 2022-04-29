/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Article;

import Entities.Article;
import Entities.cat;
import Front.MainWindowController;
import Services.ArticleService;
import Services.CatService;
import Tools.Constants;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddArticleController implements Initializable {

    private TextField txtnomA;
    private TextField txtdescA;
    private TextField txtimageA;
    @FXML
    private Button btnAjouter;
    @FXML
    private ChoiceBox<String> txtcat;
    @FXML
    private Button btngetback;
    @FXML
    private Label nomHebr;
    @FXML
    private AnchorPane mainp;
    @FXML
    private TextField txtTitre;
    @FXML
    private TextField txtDesc;
    private TextField txtImage;
    
 CatService catservice = new CatService();
    @FXML
    private Button Timage;
    @FXML
    private ImageView imgajoutincours;
    @FXML
    private Label imgpathttt;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   ObservableList<String> items = FXCollections.observableArrayList();

// Set the ComboBox to use the items list
 List<cat> listProfil =catservice.ShowCategorie();
 // System.out.println(catservice.ShowCategorie());
       //profilService.TriProfil(listProfil);
                for(cat p : listProfil) {
// Allow the user to update the items in the list
items.add(p.getNom());
System.out.println(p.getNom());

                }
txtcat.setItems(items);

    }     
  @FXML
    private void addimgcours(ActionEvent event) {
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
            imgajoutincours.setImage(image);
            imgajoutincours.setFitWidth(200);
            imgajoutincours.setFitHeight(200);
            imgajoutincours.scaleXProperty();
            imgajoutincours.scaleYProperty();
            imgajoutincours.setSmooth(true);
            imgajoutincours.setCache(true);
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
      imgpathttt.setText(file.getAbsolutePath());
    }
    
    @FXML
    private void AddArticle(ActionEvent event) {
       ArticleService ps = new ArticleService();
       if (txtTitre.getText().isEmpty() == false
                && txtDesc.getText().isEmpty() == false && imgpathttt.getText().isEmpty() == false) {

       Article p = new Article();
       p.setDescription( txtDesc.getText());
       p.setTitre(txtTitre.getText());
      p.setImage(imgpathttt.getText());
       p.setNbr_vu(0);
       p.setCreate_at(Date.valueOf(LocalDate.now()));
       cat c = new cat(1);
       p.setCat_id(c);
       if (ps.Recherche(txtTitre.getText())<0){
       ps.AddArticle(p);
        System.out.println("ajout");
        JOptionPane.showMessageDialog(null, "AJOUT Article  DONE");
              MainWindowController.getInstance().loadInterface("ShowArticle.fxml");
       } else {

           addNotifications("erreur", "le titre existe deja !!!");        }
    }else 
           
           addNotifications("erreur", "veuillez remplir correctement les champs");}

    @FXML
    private void loadMenu(ActionEvent event) {
              AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("ArticleMenu.fxml"));
            mainp.getChildren().setAll(pane);
            //defaultStateButtons();
            btngetback.setTextFill(Color.WHITE);
            //gestionUserButton.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
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
                .hideAfter(Duration.seconds(31536000))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }

    @FXML
    private void traduction(ActionEvent event) {
    }
  
}
