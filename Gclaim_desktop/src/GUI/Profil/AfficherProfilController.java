/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Profil;
import Tools.MaConnection;
import Entities.Produit;
import Entities.Profil;
import Services.ProfilService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author yusse
 */
public class AfficherProfilController implements Initializable {

PreparedStatement pst= null;
ResultSet rs= null;
   MaConnection con = new MaConnection();
    @FXML
    TableView profilTable = new TableView();
    @FXML
    private Label username;
    @FXML
    private TextField game;
    @FXML
    private TextField description;
    @FXML
    private TextField numero;
 
 ProfilService profilService = new ProfilService();
    @FXML
    private TableColumn<Profil, String> namecolumn;
    @FXML
    private TableColumn<Profil, String> gamecolumn;
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        username.setText("");
       // System.out.println("test");
                ObservableList<String> items =FXCollections.observableArrayList();
                      //  System.out.println("test");
                        profilTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        profilTable.getSelectionModel().setCellSelectionEnabled(true);
 //System.out.println(profilService.ShowProfil());
                List<Profil> listProfil =profilService.ShowProfil();
  
       //profilService.TriProfil(listProfil);
                for(Profil p : listProfil) {
                    
         
namecolumn.setCellValueFactory(new PropertyValueFactory<>("username"));


gamecolumn.setCellValueFactory(new PropertyValueFactory<>("game"));

//profilTable.getColumns().addAll(namecolumn, gamecolumn);
Profil person = new Profil(p.getUsername(),p.getGame());
profilTable.getItems().add(person);
                    //items.add(p.getUsername(),p.getGame());
                  
                    //System.out.println(p.getUsername());
                }
              //  profilTable.setItems(items);
                
                profilTable.setOnMouseClicked(e-> {
                     try {
                String query ="select * from profil where username = ?";
           TablePosition tablePosition=(TablePosition) profilTable.getSelectionModel().getSelectedCells().get(0);
           
        int row=tablePosition.getRow();
        Object item=profilTable.getItems().get(row);
        TableColumn tableColumn=tablePosition.getTableColumn();
        String data= (String) tableColumn.getCellObservableValue(item).getValue();
        System.out.println(data);
                pst = con.MaConnection().prepareStatement(query);
                pst.setString(1,data);
                rs = pst.executeQuery();
                while(rs.next())
                {
                    System.out.println(rs.getInt("id"));
                    username.setText(rs.getString("username"));
                     description.setText(rs.getString("description"));
                      game.setText(rs.getString("game"));
                       numero.setText(rs.getString("numero"));
                }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                // Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
                 
                });
                
                
    }    
    /**
 * Called when the user clicks on the delete button.
 */
@FXML
private void handleDeleteProfil() {
    int selectedIndex = profilTable.getSelectionModel().getSelectedIndex();
    profilTable.getItems().remove(selectedIndex);
      try {
                String query ="select * from profil where username = ?";
             TablePosition tablePosition=(TablePosition) profilTable.getSelectionModel().getSelectedCells().get(0);
           
        int row=tablePosition.getRow();
        Object item=profilTable.getItems().get(row);
        TableColumn tableColumn=tablePosition.getTableColumn();
        String data= (String) tableColumn.getCellObservableValue(item).getValue();
                pst = con.MaConnection().prepareStatement(query);
                pst.setString(1,data);
                rs = pst.executeQuery();
                while(rs.next())
                {
                             

              // profilService.DeleteProfil(rs.getInt("id"));
               }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
}

    @FXML
    private void handleEditProfil(ActionEvent event) {
    
    ObservableList<String>items = FXCollections.observableArrayList();
        this.profilService= new ProfilService();
        
        if (numero.getText().equals("") || description.getText().equals("") || username.getText().equals("") || game.getText().equals("") ) {
     Alert alert = new Alert(Alert.AlertType.WARNING, "Enter Full Details", ButtonType.OK);            
     alert.showAndWait();
 
 }else{
       // String selected = (String)profilTable.getSelectionModel().getSelectedItem();
          try {
                String query ="select * from profil where username = ?";
           TablePosition tablePosition=(TablePosition) profilTable.getSelectionModel().getSelectedCells().get(0);
           
        int row=tablePosition.getRow();
        Object item=profilTable.getItems().get(row);
        TableColumn tableColumn=tablePosition.getTableColumn();
        String data= (String) tableColumn.getCellObservableValue(item).getValue();
                pst = con.MaConnection().prepareStatement(query);
                pst.setString(1,data);
                rs = pst.executeQuery();
                
                while(rs.next())
                {
                          Profil h = new Profil(rs.getString("username"),rs.getString("description"),rs.getString("game"),rs.getInt("numero"));
        ProfilService hs = new ProfilService();
        int x = rs.getInt("id");
        //System.out.println(h.toString());
        String username1 = username.getText();
        String description1 = description.getText();
        String game1 = game.getText();
        int numero1 = Integer.parseInt(numero.getText());
      
        Profil h1= new Profil(username1, description1, game1, numero1);
        //System.out.println(h1.toString());
      //  hs.UpdateProfil(h1, x);
        
       // items.addAll(profilService.ShowProfil());
      
      
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Verification");
		alert.setHeaderText("Results:");
		alert.setContentText("Profil mis a jours avec succes");

		alert.showAndWait();      

               //profilService.DeleteProfil(rs.getInt("id"));
               }
                pst.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(AfficherProfilController.class.getName()).log(Level.SEVERE, null, ex);
            }
     
    }}

    @FXML
    private void handleAjout(ActionEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AjouterProfil.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AjouterProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
               Stage mainStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }

    @FXML
    private void key(KeyEvent event) {
          if (event.getCharacter().matches("[^\\e\t\r\\d+$]")) {
      
       
       event.consume();
    }
    }

  
    
}
