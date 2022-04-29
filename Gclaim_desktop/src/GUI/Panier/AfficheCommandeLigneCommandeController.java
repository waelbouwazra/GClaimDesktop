/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Panier;

import Entities.Commande;
import Entities.Equipe;
import Entities.LigneCommande;
import Entities.PanierModel;
import Entities.Produit;
import static GUI.Panier.showCommandesController.comm;
import Services.CommandeService;
import Services.LigneCommandeService;
import Tools.Constants;
import Tools.PDFCart;
import Tools.PDFProd;
import Tools.SendEmail;
import com.itextpdf.text.DocumentException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class AfficheCommandeLigneCommandeController implements Initializable {

    @FXML
    private Text topText;
    @FXML
    private VBox mainVBox;
    @FXML
    private VBox commandeVBox;
    @FXML
    private Text Total;
    @FXML
    private Text DateAchat;
    @FXML
    private Text Status;
    @FXML
    private Text userName;
    @FXML
    private Pane addBtn;
    @FXML
    private Button deleteButton;
    @FXML
    private Pane minusBtn;
    @FXML
    private Button retour;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button sendMail;
    private SendEmail sendEmail;
    @FXML
    private Button exportPdf;
    String msg = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherLigneCommande();

        Total.setText("Total :"+ comm.getTotal());
        DateAchat.setText("Date Achat :"+comm.getDate_achat());
        userName.setText("Username :"+comm.getUser().getUsername());
        Status.setText("Status :"+comm.isLivrer());
        
    }    
     
    private void afficherLigneCommande(){
      LigneCommandeService lcs = new LigneCommandeService();
        List<LigneCommande> listLC = lcs.getLigneCommandeByCommandeID(comm.getId());
        Collections.reverse(listLC);

     
        if (!listLC.isEmpty()) {
            for (LigneCommande pmodel : listLC) {

                mainVBox.getChildren().add(makeAboModel(pmodel));

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
}
    public Parent makeAboModel(LigneCommande pm) {
        Parent parent = null;
        
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_MODEL_LIGNECOMMANDE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
             
          //  ((Pane) innerContainer.lookup("#addBtn")).setVisible(false);
           // ((Pane) innerContainer.lookup("#minusBtn")).setVisible(false);

            ((Text) innerContainer.lookup("#nomProduit")).setText("Produit : " + pm.getProduit().getNom_produit());
            ((Text) innerContainer.lookup("#total")).setText("Total : " + pm.getProduit().getPrix_produit()* pm.getQuantite());
            ((Text) innerContainer.lookup("#prixProduit")).setText("Prix : " + pm.getProduit().getPrix_produit());
            ((Text) innerContainer.lookup("#quantiteProduit")).setText("Quantite : " + pm.getQuantite());
          
              

        } catch (IOException ex) {
         
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void changeStatus(ActionEvent event) {
          CommandeService cs = new CommandeService();
          cs.UpdateCommande(comm);
          Commande c=cs.getSingleCommande(comm.getId());
          Status.setText("Status :"+c.isLivrer());
    }

    @FXML
    private void retourAction(ActionEvent event) {
                    AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuPanier.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            retour.setTextFill(Color.WHITE);
            retour.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());    
        }
    }

    @FXML
    private void sendMail(ActionEvent event) {
        
    LigneCommandeService sc = new LigneCommandeService();
        List<LigneCommande> l =sc.getLigneCommandeByCommandeID(comm.getId());
       /* msg+= "<div style=\"color: #c24400\"> <h1>Bonjour! " + comm.getUser().getUsername() +" </h1> </div> <h3> Voici Votre commande</h3>"
                                    +"<div>Username ="+comm.getUser().getUsername()+"</div> <div>Total ="+comm.getTotal()+"</div> <div>Date ="+comm.getDate_achat()+"</div>"
                                    ;
        for (LigneCommande c : l){
            msg = msg +"<div>Produit = "+c.getProduit().getNom_produit()+"</div><div>Prix ="
                    +c.getProduit().getPrix_produit()+"</div><div>Quantite ="+c.getQuantite()+"</div>";
        }
        msg+=                    " : \n </h3> <div style=\"color: #c24400\"> <p>Cordialement </p> <p>	"
                                    + "Gclaim by BITS&BAYTES</p> <img src=\"C:/xampp/htdocs/GClaimDesktop/Gclaim_desktop/src/GUI/src_image/Logo.png \"> </div>";
             ;
        System.out.println(msg);*/
       msg +="<style>\n" +
"         /*** @media all  ***/\n" +
"* {\n" +
"  box-sizing: border-box;\n" +
"}\n" +
"html {\n" +
"  height: 100%;\n" +
"}\n" +
"body {\n" +
"  min-height: 100%;\n" +
"  margin: 0;\n" +
"  display: flex;\n" +
"  flex-flow: column nowrap;\n" +
"  justify-content: center;\n" +
"  align-items: sretch;\n" +
"  font: 12pt/1.5 'Raleway', 'Cambria', sans-serif;\n" +
"  font-weight: 300;\n" +
"  background: #fff;\n" +
"  color: #666;\n" +
"  -webkit-print-color-adjust: exact;\n" +
"}\n" +
"header {\n" +
"  padding: 16px;\n" +
"  position: relative;\n" +
"  color: #888;\n" +
"}\n" +
"header h1,\n" +
"header h2 {\n" +
"  font-weight: 200;\n" +
"  margin: 0;\n" +
"}\n" +
"header h1 {\n" +
"  font-size: 27pt;\n" +
"  letter-spacing: 4px;\n" +
"}\n" +
"body > * {\n" +
"  width: 100%;\n" +
"  max-width: 7in;\n" +
"  margin: 3px auto;\n" +
"  background: #f0f0f0;\n" +
"  text-align: center;\n" +
"}\n" +
"footer {\n" +
"  padding: 16px;\n" +
"}\n" +
"footer p {\n" +
"  font-size: 9pt;\n" +
"  margin: 0;\n" +
"  font-family: 'Nunito';\n" +
"  color: #777;\n" +
"}\n" +
"section,\n" +
"table {\n" +
"  padding: 8px 0;\n" +
"  position: relative;\n" +
"}\n" +
"dl {\n" +
"  margin: 0;\n" +
"  letter-spacing: -4px;\n" +
"}\n" +
"dl dt,\n" +
"dl dd {\n" +
"  letter-spacing: normal;\n" +
"  display: inline-block;\n" +
"  margin: 0;\n" +
"  padding: 0px 6px;\n" +
"  vertical-align: top;\n" +
"}\n" +
"dl.bloc > dt,\n" +
"dl:not(.bloc) dt:not(:last-of-type),\n" +
"dl:not(.bloc) dd:not(:last-of-type) {\n" +
"  border-bottom: 1px solid #ddd;\n" +
"}\n" +
"dl:not(.bloc) dt {\n" +
"  border-right: 1px solid #ddd;\n" +
"}\n" +
"dt {\n" +
"  width: 49%;\n" +
"  text-align: right;\n" +
"  letter-spacing: 1px !important;\n" +
"  overflow: hidden;\n" +
"}\n" +
"dd {\n" +
"  width: 49%;\n" +
"  text-align: left;\n" +
"}\n" +
"dd,\n" +
"tr>td {\n" +
"  font-family: 'Nunito';\n" +
"}\n" +
"section.flex {\n" +
"  display: flex;\n" +
"  flex-flow: row wrap;\n" +
"  padding: 8px 16px;\n" +
"  justify-content: space-around;\n" +
"}\n" +
"dl.bloc {\n" +
"  padding: 0;\n" +
"  flex: 1;\n" +
"  vertical-align: top;\n" +
"  min-width: 240px;\n" +
"  margin: 0 8px 8px;\n" +
"}\n" +
"dl.bloc>dt {\n" +
"  text-align: left;\n" +
"  width: 100%;\n" +
"  margin-top: 12px;\n" +
"}\n" +
"dl.bloc>dd {\n" +
"  text-align: left;\n" +
"  width: 100%;\n" +
"  padding: 8px 0 5px 16px;\n" +
"  line-height: 1.25;\n" +
"}\n" +
"dl.bloc>dd>dl dt {\n" +
"  width: 33%;\n" +
"}\n" +
"dl.bloc>dd>dl dd {\n" +
"  width: 60%;\n" +
"}\n" +
"dl.bloc dl {\n" +
"  margin-top: 12px;\n" +
"}\n" +
"dl.bloc dd {\n" +
"  font-size: 11pt;\n" +
"}\n" +
"table {\n" +
"  width: 100%;\n" +
"  padding: 0;\n" +
"  border-spacing: 0px;\n" +
"}\n" +
"table tr {\n" +
"  margin: 0;\n" +
"  padding: 0;\n" +
"  background: #fdfdfd;\n" +
"  border-right: 1px solid #ddd;\n" +
"  width: 100%;\n" +
"}\n" +
"table tr td,\n" +
"table tr th {\n" +
"  border: 1px solid #e3e3e3;\n" +
"  border-top: 1px solid #fff;\n" +
"  border-left-color: #fff;\n" +
"  font-size: 11pt;\n" +
"  background: #fdfdfd;\n" +
"}\n" +
"table thead th {\n" +
"  background: #e9e9e9;\n" +
"  background: linear-gradient(to bottom, #f9f9f9, #e9e9e9) !important;\n" +
"  font-weight: 300;\n" +
"  letter-spacing: 1px;\n" +
"  padding: 15px 0 5px;\n" +
"/*&:not(:last-child)*/\n" +
"  border: none !important;\n" +
"}\n" +
"table tbody tr:last-child td {\n" +
"  border-bottom: 1px solid #ddd;\n" +
"}\n" +
"table tbody td {\n" +
"  min-width: 75px;\n" +
"  padding: 3px 6px;\n" +
"  line-height: 1.25;\n" +
"}\n" +
"table tfoot tr td {\n" +
"/*border 1px solid #e3e3e3\n" +
"      border-top 1px solid white\n" +
"      border-left-color #fff*/\n" +
"  height: 40px;\n" +
"  padding: 6px 0 0;\n" +
"  color: #000;\n" +
"  text-shadow: 0 0 1px rgba(0,0,0,0.25);\n" +
"  font-family: 'Cambria', 'Raleway', sans-serif;\n" +
"  font-weight: 400;\n" +
"  letter-spacing: 1px;\n" +
"}\n" +
"table tfoot tr td:first-child {\n" +
"  font-style: italic;\n" +
"  color: #997b7b;\n" +
"}\n" +
"a {\n" +
"  color: #992c2c;\n" +
"}\n" +
"a:hover {\n" +
"  color: #b00;\n" +
"}\n" +
"@page {\n" +
"  margin: 0.5cm;\n" +
"}\n" +
"/*** @media screen  ***/\n" +
"html,\n" +
"body {\n" +
"  background: #333231;\n" +
"}\n" +
"header:before {\n" +
"  content: '';\n" +
"  position: absolute;\n" +
"  top: 0;\n" +
"  right: 0;\n" +
"  border-top: 12px solid #333;\n" +
"  border-left: 12px solid #ddd;\n" +
"  width: 0;\n" +
"  box-shadow: 1px 1px 2px rgba(0,0,0,0.18);\n" +
"}</style>\n" +
"<head>\n" +
"  <meta charset='UTF-8'/>\n" +
"  <title>Facture \".$username.\"</title>\n" +
"  <link href='https://fonts.googleapis.com/css?family=Nunito:300|Raleway:200,300' rel='stylesheet' type='text/css'/>\n" +
"</head>\n" +
"<body>\n" +
"  <header>\n" +
"    <h1>FACTURE\n" +
"      <h2>"+comm.getUser().getUsername()+"</h2>\n" +
"    </h1>\n" +
"  </header>\n" +
"  <section class='flex'>\n" +
"    <dl>\n" +
"      <dd>Facture </dt>\n" +
"      <dd>"+comm.getUser().getUsername()+"</dd>\n" +
"      <dd>Date d'Achat</dt>\n" +
"      <dd>"+comm.getDate_achat()+"</dd>\n" +
"    </dl>\n" +
"  </section>\n" +
"  <section class='flex'>\n" +
"    <dl class='bloc'>\n" +
"      <dt>Facturé à:</dt>\n" +
"      <dd>\n" +
""+comm.getUser().getEmail()+"\n" +
"        \n" +
"      </dd>\n" +
"    </dl>\n" +
"    <dl class='bloc'>\n" +
"      <dt>Description de service:</dt>\n" +
"      <dd>Facturation</dd>\n" +
"      <dt>Date de facturation</dt>\n" +
"      <dd>"+new Date()+"</dd>\n" +
"    </dl>\n" +
"  </section>\n" +
"  <table>\n" +
"    <thead>\n" +
"      <tr> \n" +
"        <th>Nom Produit</th>\n" +
"        <th>Description</th>\n" +
"        <th>Prix HT</th>\n" +
"        <th>Prix TTC</th>\n" +
"        <th>Quantite</th>\n" +
"        \n" +
"      </tr>\n" +
"    </thead>\n" +
"    <tbody>\n" +
"    \n" ;
               for( LigneCommande ligne :l){
                msg+=  " <tr>\n" +
"      \n" +
"        <td>"+ ligne.getProduit().getNom_produit()+"</td>\n" +
"        <td>"+ ligne.getProduit().getDescription()+"</td>\n" +
"        <td>"+ligne.getProduit().getPrix_produit()+"</td>\n" +
"        <td>"+ligne.getProduit().getPrix_produit()*1.19+"$</td>\n" +
"        <td>"+ligne.getQuantite()+"</td>\n" +
"        \n" +
"      </tr>\n";
               };

msg+="    </tbody>\n" +
"    <tfoot>\n" +
"      <tr> \n" +
"        <td colspan='3'> Votre Total TTC est  </td>\n" +
"        <td>Total:</td>\n" +
"        <td>"+comm.getTotal()*1.19 +"$</td>\n" +
"      </tr>\n" +
"    </tfoot>\n" +
"  </table>\n" +
"  <footer>\n" +
"    <p>PI DEV Best Team EVer!!!</a></p>\n" +
" </footer>\n" +
"</body>";
        ExecutorService emailExecutor = Executors.newSingleThreadExecutor();
        emailExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                   
                    sendEmail = new SendEmail("gclaimpidev@gmail.com",
                            "Gclaim2022",
                           comm.getUser().getEmail(),
                            "Détaile commande",
                           msg);
             } catch (AddressException e) {
                    e.printStackTrace ();
                } catch (MessagingException e) {
                    e.printStackTrace ();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());}
//        try {
//            sm.sendMail(email, "ajout confirmé", msg);
//        } catch (MessagingException ex) {
//            Logger.getLogger(AddReservationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//

            }});
        emailExecutor.shutdown();
    }

    @FXML
    private void exportPdf(ActionEvent event) throws DocumentException, MalformedURLException, IOException, FileNotFoundException, URISyntaxException {
     
        PDFCart pdf=new PDFCart ();
        pdf.pdfGeneration (comm.getId());
     
        if (Desktop.isDesktopSupported()) {
            try {
               
                File myFile = new File("commande.pdf");
                 
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
}
