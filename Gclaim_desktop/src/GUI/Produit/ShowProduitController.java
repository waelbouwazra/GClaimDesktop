/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Produit;

import Entities.Categorie;
import Entities.Produit;
import Services.CategorieService;
import Services.ProduitService;
import Tools.MaConnection;
import Tools.PDFProd;
import com.itextpdf.text.DocumentException;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.controlsfx.control.Notifications;


/**
 *
 * @author azizk
 */
public class ShowProduitController implements Initializable{
    PreparedStatement pst= null;
ResultSet rs= null;
   MaConnection con = new MaConnection();
  private Connection cnx = MaConnection.getInstance().getConnection();
     @FXML
    private ListView<Produit> txtListProd;
    @FXML
    private Button btnRetour;
    ProduitService ps = new ProduitService();
    CategorieService cs = new CategorieService();
   

    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button btnsupp;
    
    @FXML
    private TextField nomp;
    @FXML
    private TextField descp;
    @FXML
    private TextField prixp;
    @FXML
    private TextField qtep;
    @FXML
    private Button btnmodif;
    int id_prod;
    @FXML
    private ChoiceBox<Integer> EtatBox;
    @FXML
    private Button btnplusvu;
    @FXML
    private Text nbr;
    @FXML
    private TextField nbrproduit;
    @FXML
    private Button btnExcel;
    @FXML
    private Button btnpdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ObservableList<Produit> items =FXCollections.observableArrayList();
                List<Produit> listProduit = ps.ShowProduit();
                for(Produit p : listProduit) {
                    items.add(p);
                    
                }
                txtListProd.setItems(items);
                nbrproduit.setText(ps.nbrprod(listProduit));
        
         
      
    }    
@FXML
    private void DeleteProduct(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir modifier cette Categorie ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            if(txtListProd.getSelectionModel().getSelectedItem()!=null){                      
                ps.DeleteProduit(txtListProd.getSelectionModel().getSelectedItem().getId_produit());
addNotifications("suppression avec succes", "produit supprimé");
            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
        }
      ObservableList<Produit> items =FXCollections.observableArrayList();
        List<Produit> listprod = ps.ShowProduit();
        for(Produit r : listprod) {
            String ch = r.toString();
            items.add(r);
        }
       
    txtListProd.setItems(items);
    
    }
     
    
    @FXML
    private void loadMenu(ActionEvent event) {
        AnchorPane pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("MenuProduit.fxml"));
            mainPane.getChildren().setAll(pane);
            //defaultStateButtons();
            btnRetour.setTextFill(Color.WHITE);
            btnRetour.setStyle("-fx-background-color :#5b4ebd");
        } catch (IOException ex) {
            //Logger.getLogger(TemplateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void updateProduit(ActionEvent event) {
            
        ProduitService ps = new ProduitService();
        
          String nom_produit= nomp.getText();
          String description= descp.getText();
          Double prix= Double.parseDouble(prixp.getText());
          Integer qte= Integer. parseInt(qtep.getText());
        Integer categorie=  EtatBox.getValue();
      
       Categorie c = new Categorie(categorie);
         System.out.println("id="+id_prod);
         
          Produit a =new Produit(id_prod,nom_produit,description,prix,qte,c);
          System.out.println(a);
         // a.setNom_produit(nom_produit);
         // a.setDescription(description);
          //a.setPrix_produit(Double.parseDouble(prix));
         // a.setQte_produit(Integer. parseInt(qte));
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer votre demande");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir modifier cette Categorie ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
       ps.UpdateProduit(a, id_prod);
       addNotifications("modification avec succes", "produit modifié");
        }
    ObservableList<Produit> items =FXCollections.observableArrayList();
        List<Produit> listprod = ps.ShowProduit();
        for(Produit r : listprod) {
            String ch = r.toString();
            items.add(r);
        }
                txtListProd.setItems(items);

  
    }
    @FXML
    private void ProduitSelect(MouseEvent event) {
        if(txtListProd.getSelectionModel().getSelectedItem()!=null){                      
                nomp.setText(txtListProd.getSelectionModel().getSelectedItem().getNom_produit());  
          
            descp.setText(txtListProd.getSelectionModel().getSelectedItem().getDescription());
            prixp.setText(""+txtListProd.getSelectionModel().getSelectedItem().getPrix_produit());
            qtep.setText(""+txtListProd.getSelectionModel().getSelectedItem().getQte_produit());
            
            EtatBox.setValue(txtListProd.getSelectionModel().getSelectedItem().getCategorie().getId_categorie());
            
          id_prod=ps.getIdPro(txtListProd.getSelectionModel().getSelectedItem().getNom_produit(), txtListProd.getSelectionModel().getSelectedItem().getDescription(), 
                  txtListProd.getSelectionModel().getSelectedItem().getPrix_produit(), txtListProd.getSelectionModel().getSelectedItem().getQte_produit());
         ObservableList<Integer> langss = FXCollections.observableArrayList(cs.getIdcategorie());
          System.out.println(langss);
     EtatBox.setItems(langss);

            }else 
            {
            Alert alertt = new Alert(Alert.AlertType.WARNING);
            alertt.setTitle("Champs Vide");
            alertt.setContentText("Veuiller selectionner l'un des Champs disponible");
            alertt.show();  
            }
          
        
    }
    
   @FXML
     private void plusvues() {
         
        ProduitService ps = new ProduitService();
        List<Produit> list = ps.ShowProduit();
        List<Produit> listProd = ps.plusvu(list);
        listProd.stream().forEach(p->System.out.println(p.toString()));
    ObservableList<Produit> items =FXCollections.observableArrayList();
       
                   
            for (Produit r : listProd) {
                
                 String ch = r.toString();
            items.add(r);
               // System.out.println("aaaa");
            }
         txtListProd.setItems(items);
        
}
     
    @FXML
     public void generateExcel() 
         {
           String sql = "select * from produit";
        Statement ste;
        try {
       
          ste=cnx.prepareStatement(sql);
               ResultSet rs = ste.executeQuery(sql);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Produit details ");
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id_produit");
            header.createCell(1).setCellValue("nom_produit");
            header.createCell(2).setCellValue("description");
            header.createCell(3).setCellValue("prix_produit");
            header.createCell(4).setCellValue("date_ajout_produit");
            header.createCell(5).setCellValue("Qte_produit");
            header.createCell(6).setCellValue("nbr_vu");
            header.createCell(7).setCellValue("categorie");
            int index = 1;
            while (rs.next()) {
                System.out.println(rs.getString("id_produit"));
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id_produit"));
                row.createCell(1).setCellValue(rs.getString("nom_produit"));
                row.createCell(2).setCellValue(rs.getString("description"));
                row.createCell(3).setCellValue(rs.getString("prix_produit"));
                row.createCell(4).setCellValue(rs.getString("date_ajout_produit"));
                row.createCell(5).setCellValue(rs.getString("Qte_produit"));
                row.createCell(5).setCellValue(rs.getString("nbr_vu"));
                row.createCell(5).setCellValue(rs.getString("categorie"));
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\azizk\\Desktop\\excell\\produitsDetails.xlsm");
            wb.write(fileOut);
            fileOut.close();
           ste.close();
           rs.close();

        } catch (SQLException e) {
        } catch (IOException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void PDFprod(ActionEvent event) throws DocumentException, MalformedURLException, IOException, FileNotFoundException, URISyntaxException {
        PDFProd pdf=new PDFProd ();
        pdf.pdfGeneration ();
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("Produits.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }
    }
}
