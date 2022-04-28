/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Categorie;
import Entities.Image;
import Entities.Produit;
import Entities.Utilisateur;
import Services.CategorieService;
import Services.ProduitService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField txtnom;
    private ProduitService ps = new ProduitService();
    private CategorieService cs = new CategorieService();
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtprix;
    @FXML
    private Button btnAjouter;
     @FXML
    
     private ChoiceBox<String> comboBoxID;
      ObservableList<String> items =FXCollections.observableArrayList();
    @FXML
    private Button btngetback;
    @FXML
    private Label nomHebr;
    @FXML
    private TextField txtqt;
     String nom;
     String qt;
     String desc;
     String prix;
     String Categorie;
   
    @FXML
    private AnchorPane mainPane;
    @FXML
    private ImageView ImageviewID;
    String url_image;
    @FXML
    private Button btnImage;
    @FXML
    private Text alertnom;
    @FXML
    private Text alertdesc;
    @FXML
    private Text alertprix;
    @FXML
    private Text alertqte;
    @FXML
    private Label imgpath;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Categorie> listproduit = cs.ShowCategorie();
        for(Categorie r : listproduit) {
            String ch = r.toString();
            items.add(r.getNom_categorie());
            System.out.println(r.getNom_categorie());
        }
 
     
        comboBoxID.setItems(items);
       // ObservableList<Integer> langs = FXCollections.observableArrayList(ps.getIdcategorie());
        //comboBoxID.setItems(langs);
    }    

    @FXML
    private void addProduct(ActionEvent event) {
       Boolean verif = true;
       ProduitService ps = new ProduitService();
       Produit p = new Produit();
       p.setDescription( txtdesc.getText());
       p.setQte_produit(Integer.parseInt(txtqt.getText()));
       p.setNom_produit(txtnom.getText());
       p.setPrix_produit(Double.parseDouble(txtprix.getText()));
       p.setNbr_vu(0);
       p.setDateAjout_produit(Date.valueOf(LocalDate.now()));
       Categorie c = new Categorie(cs.getIdCategnom(comboBoxID.getValue()));
       p.setCategorie(c);
          // Image i = new Image();
            //p.setImage(i);
           
       if (txtnom.getText().equals("")) {
            alertnom.setText("Remplir le champs !!");
            verif = false;
        }
       if (txtdesc.getText().equals("")) {
            alertdesc.setText("Remplir le champs !!");
            verif = false;
        }
              List<Produit> listuser = ps.ShowProduit();
        for(Produit r : listuser) {
            String ch = r.toString();
            if((r.getNom_produit().equals(txtnom.getText()))&&(r.getDescription().equals(txtdesc.getText())))
            {
                verif=false;
                Alert alertt = new Alert(Alert.AlertType.ERROR);
            alertt.setTitle("Ce produit existe deja");
            alertt.setContentText("Veuiller changer l'un des Champs !");
            alertt.show(); 
            }
        }
      
      if(verif==true) {
          addNotifications("ajout avec succes", "produit ajouté");
         ObservableList<Integer> langs = FXCollections.observableArrayList(cs.getIdcategorie() );
       // comboBoxID.setItems(langs);
     
       
       ps.AddProduitPst(p, imgpath.getText());
          //alerteajout1.setText("Ajout avec succes!");
          alertnom.setText("");
          alertdesc.setText("");
          alertprix.setText("");
          alertqte.setText("Ajout avec succes!");
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
    
    @FXML
    private void loadMenu(ActionEvent event) {
        
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuProduit.fxml"));
            mainPane.getChildren().setAll(pane);
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
                .hideAfter(Duration.seconds(360))
                .position(Pos.BOTTOM_RIGHT);

        notificationBuilder.showInformation();
    }
    
    
    
}
