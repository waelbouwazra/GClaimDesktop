/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Equipe;
import Entities.Panier;
import Entities.Utilisateur;
import Entities.Produit;
import Front.MainWindowController;
import static GUI.Login.ShowAllController.currentAbo;
import Services.ProduitService;
import Services.ServiceUser;
import Tools.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx_qrcodewriter.JavaFX_QRCodeWriter;

/**
 * FXML Controller class
 *
 * @author azizk
 */
public class ShowAllProdController implements Initializable {

    public static Produit currentProd;
    @FXML
    private AnchorPane mainPain;
    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox;

    ProduitService ps=new ProduitService();
    private ServiceUser US;
    Panier pan = new Panier().getInstance();
    private static HashMap< Produit,Integer> panier = new HashMap< Produit,Integer>();
    @FXML
    private TextField min;
    @FXML
    private TextField max;
    @FXML
    private Button btnprix;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        US = new ServiceUser();
        List<Produit> listProd = ps.ShowProduit();
        Collections.reverse(listProd);

        if (!listProd.isEmpty()) {
            for (Produit abo : listProd) {
                mainVBox.getChildren().add(makeProdModel(abo));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }
public Parent makeProdModel( Produit abo  )  {
        Parent parent = null;
        
        try {
            
             System.out.println(abo.getImage());
             abo.setImage( ps.getImage(abo.getId_produit()));
             System.out.println(abo.getImage());
             File file= new File(abo.getImage());
             parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_PROD)));
             System.out.println(abo.getImage());
         
              
            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            
            ((Text) innerContainer.lookup("#createdAtText")).setText("Nom Produit : " + abo.getNom_produit());
            ((Text) innerContainer.lookup("#desctxt")).setText("Description : " + abo.getDescription());
            ((Text) innerContainer.lookup("#prixtxt")).setText("prix : " + abo.getPrix_produit());
            ((Text) innerContainer.lookup("#qtetxt")).setText("quantitée : " + abo.getQte_produit());
            ((Text) innerContainer.lookup("#nbrvu")).setText("nombre de vu : " + abo.getNbr_vu());
            ((Text) innerContainer.lookup("#dateajouttxt")).setText("Date ajout : " + abo.getDateAjout_produit());
            
            ((ImageView) innerContainer.lookup("#imgView")).setImage(new Image(file.toURI().toString()));
            ((Pane) innerContainer.lookup("#supppane")).setVisible(false);
             ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> {
                 QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = abo.toString();
        int width = 300;
        int height = 300;
        String fileType = "png";
        ps.updatevu(abo);
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(JavaFX_QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
          
       StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(qrView);
            
            innerContainer.getChildren().add(stackPane); 
                 
             }
             
             );
             ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerProd(abo));
             
             ((Button) innerContainer.lookup("#btnajoutpanier")).setOnAction((event) -> addToCartProd(abo));
           
           
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    } 
    private void modifierProd(Produit abo) {
        currentProd = abo;
       // rs.updateEquipe(abo);
        MainWindowController.getInstance().loadInterface(Constants.FXML_UPDATE_PRODUIT);
    }
    private void addToCartProd(Produit abo) {
        currentProd = abo;
       // rs.updateEquipe(abo);
        //MainWindowController.getInstance().loadInterface(Constants.FXML_UPDATE_PRODUIT);
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir ajouter ce produit au panier ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
           pan.getInstance().getPanier().putIfAbsent(abo,1);
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_PRODUIT);
            
            }
        
    }

    private void supprimerProd(Produit abo) {
        currentProd = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer ce produit ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
           ps.DeleteProduit(abo.getId_produit());
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_PRODUIT);
            
            }
        }
    @FXML
     private void filtrepri() {
         //System.out.println("test");
        double min1=Double.valueOf(min.getText());
        double max1=Double.valueOf(max.getText());
        ProduitService ps = new ProduitService();
        List<Produit> list = ps.ShowProduit();
        List<Produit> listProd = ps.filtreprix(list, min1, max1);
        listProd.stream().forEach(p->System.out.println(p.toString()));
   
         if (!listProd.isEmpty()) {
                   mainVBox.getChildren().clear();
            for (Produit abo : listProd) {
                mainVBox.getChildren().add(makeProdModel(abo));
                
               // System.out.println("aaaa");
            }
        } else {
            
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
        }
     public void Qr() {
     QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "qr code";
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(JavaFX_QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
          
       StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(qrView);
            mainVBox.getChildren().add(stackPane); 
     
    }
     
       
}
