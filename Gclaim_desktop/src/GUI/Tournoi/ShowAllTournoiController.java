/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Tournoi;

import Entities.Equipe;
import Entities.Tournoi;
import Services.ServiceUser;
import Services.ServiceTournoi;
import Front.*;
import GUI.MenuFrontController;
import Services.ServiceEquipe;
import Tools.Constants;
import Tools.PDFProd;
import Tools.PDFTourn;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;

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
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx_qrcodewriter.JavaFX_QRCodeWriter;

/**
 * FXML Controller class
 *
 * @author elyes
 */
public class ShowAllTournoiController implements Initializable {

 public static Tournoi currentAbo;

    @FXML
    public Text topText;
    public Button addButton;
    public VBox mainVBox;
    ServiceTournoi rs=new ServiceTournoi();
    ServiceEquipe es=new ServiceEquipe();
            StackPane stackPane = new StackPane();
             int f=0;

    private ServiceUser US;
    @FXML
    private AnchorPane mainPain;
    @FXML
    private Button ButtF;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
         US = new ServiceUser();

        List<Tournoi> listAbo = rs.ShowTournoi();
        Collections.reverse(listAbo);

        if (!listAbo.isEmpty()) {
            for (Tournoi abo : listAbo) {
                if(java.sql.Date.valueOf(abo.getDatev()).compareTo(java.sql.Date.valueOf(LocalDate.now()))==1)
                             {
                mainVBox.getChildren().add(makeAboModel(abo));
                             }
                         

            }
        } else {
            
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
            mainVBox.getChildren().clear();
        }
    }

    public Parent makeAboModel(Tournoi abo) {
       
        Parent parent = null;
        try {
            System.out.println(abo.getImage());
            File file= new File(abo.getImage());
                         

            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_TOUR)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
          
            ((Pane) innerContainer.lookup("#btnmodif")).setVisible(false);
           
           ((Pane) innerContainer.lookup("#rejoindre")).setVisible(true);
            ((Text) innerContainer.lookup("#nomtournoi")).setText("Nom Tournoi : " + abo.getNomtournoi());
            ((Text) innerContainer.lookup("#description")).setText("Description : " + abo.getDescription());
            ((Text) innerContainer.lookup("#Jeu")).setText("Jeu : " + abo.getJeu().getNomjeu());
            ((Text) innerContainer.lookup("#datecreation")).setText("Date de creation : " + abo.getDatec());
            ((Text) innerContainer.lookup("#dateevenement")).setText("Date de l'evenement : " + abo.getDatev());
            ((Text) innerContainer.lookup("#heureevenement")).setText("Heure de l'evenement : " + abo.getHeurev());
            ((ImageView) innerContainer.lookup("#imageView")).setImage(new Image(file.toURI().toString()));

        if(es.afficheEquipeUt(US.currentUser.getId()).size()>0)
                                 {
                                 if(!rs.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0)).isEmpty())
                                 {
                                     
                                     for(int s : rs.afficheTourEq(es.afficheEquipeUt(US.currentUser.getId()).get(0))){
                                        
                                     if(abo.getId()==rs.ShowTournoiById(s).getId())
                                     {
            ((Pane) innerContainer.lookup("#btnmodif")).setVisible(true);
            ((Pane) innerContainer.lookup("#rejoindre")).setVisible(false);

                         }
                                 }
                                 }
                                 }
       
       
            
            
            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> {  QRCodeWriter qrCodeWriter = new QRCodeWriter();
       
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(abo.toString(), BarcodeFormat.QR_CODE, width, height);
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
          
            stackPane.getChildren().clear();
            
            stackPane.getChildren().add(qrView);
            mainVBox.getChildren().add(stackPane);
            innerContainer.getChildren().add(stackPane); 
});
            
            
            
            
            
            
            
            ((Button) innerContainer.lookup("#btnrj")).setOnAction((event) -> supprimerAbo(abo));
            ((Button) innerContainer.lookup("#Badge")).setOnAction((event) -> {
                try {
                    PDFtourn(abo);
                } catch (DocumentException ex) {
                    Logger.getLogger(ShowAllTournoiController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ShowAllTournoiController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (URISyntaxException ex) {
                    Logger.getLogger(ShowAllTournoiController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            //((Button) innerContainer.lookup("#ButtF")).setOnAction((event) -> affT());
   
           ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerT(abo));
            
            

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
    private void modifierAbo(Tournoi abo) {
        currentAbo = abo;
        MainWindowController.getInstance().loadInterface(Constants.FXML_UPDATE_TOURNOI);
    }
    @FXML
    private void affT() {
        mainVBox.getChildren().clear();
        List<Integer> t =es.afficheEquipeUt(US.currentUser.getId());
                for(Integer r : t) {
            for(Integer s : rs.afficheTourEq(r)){
                for(Tournoi f :rs.ShowTournoiByIds(s))
                {
                                    mainVBox.getChildren().add(makeAboModel(f));

                }
                }
                
            }
        

    }
     private void supprimerT(Tournoi abo) {
        currentAbo = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir rejoindre ce Tournoi ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
                                  System.out.println(es.afficheEquipeUt(US.currentUser.getId()));
                  if(!es.afficheEquipeUt(US.currentUser.getId()).isEmpty()){
                  rs.quitterTournoi(new Equipe(es.afficheEquipeUt(US.currentUser.getId()).get(0)),abo);
                  }
                  else 
                  {
                  es.Rejoindreuneequipe(es.Equipdispo().get(0), US.currentUser);
                  rs.RejoindreTournoi(es.Equipdispo().get(0),abo);

                  }
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_TOUR);
            
            }
        }

    private void supprimerAbo(Tournoi abo) {
        currentAbo = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir rejoindre ce Tournoi ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
                                  System.out.println(es.afficheEquipeUt(US.currentUser.getId()));
                  if(!es.afficheEquipeUt(US.currentUser.getId()).isEmpty()){
                  rs.RejoindreTournoi(new Equipe(es.afficheEquipeUt(US.currentUser.getId()).get(0)),abo);
                  }
                  else 
                  {
                  es.Rejoindreuneequipe(es.Equipdispo().get(0), US.currentUser);
                  rs.RejoindreTournoi(es.Equipdispo().get(0),abo);

                  }
                MainWindowController.getInstance().loadInterface(Constants.FXML_DISPLAY_ALL_TOUR);
            
            }
        }
    public void Qr(String myWeb) {
     QRCodeWriter qrCodeWriter = new QRCodeWriter();
       
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
          
            stackPane.getChildren().clear();
            
            stackPane.getChildren().add(qrView);
            mainVBox.getChildren().add(stackPane);
    }
        private void PDFtourn(Tournoi abo) throws DocumentException, MalformedURLException, IOException, FileNotFoundException, URISyntaxException {
        PDFTourn pdf=new PDFTourn ();
        pdf.pdfGeneration (abo,US.currentUser);
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("Tournois.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }

    
}   
