/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Front.*;
import Tools.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author souma
 */
public class MenuFrontController implements Initializable {
    private static MenuFrontController instance;

    @FXML
       static AnchorPane staticContent;
    @FXML
    private AnchorPane topBar;
    @FXML
    private AnchorPane content;
    @FXML
    private Button btnAbos;
    @FXML
    private Button btnRdvs;

    /**
     * Initializes the controller class.
     */
    public static MenuFrontController getInstance() {
        if (instance == null) {
            instance = new MenuFrontController();
        }
        return instance;
    }
     public void loadInterface(String location) {
      staticContent.getChildren().clear();
        if (getClass().getResource(location) == null) {
            System.out.println("Could not load FXML check the path");
        } else {
            System.out.println("Loading fxml : " + location);
            try {
                Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(location)));
                AnchorPane.setTopAnchor(parent, 0.0);
                AnchorPane.setBottomAnchor(parent, 0.0);
                AnchorPane.setRightAnchor(parent, 0.0);
                AnchorPane.setLeftAnchor(parent, 0.0);
                staticContent.getChildren().add(parent);
            } catch (IOException e) {
                System.out.println("Could not load FXML : " + e.getMessage() + " check your controller");
                e.printStackTrace();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 try {            staticContent = content;

            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_TOP_BAR)));
            AnchorPane.setTopAnchor(parent, 0.0);
            AnchorPane.setBottomAnchor(parent, 0.0);
            AnchorPane.setRightAnchor(parent, 0.0);
            AnchorPane.setLeftAnchor(parent, 0.0);
          
            
            topBar.getChildren().add(parent);

        } catch (IOException e) {
            System.out.println("Error loading " + e.getMessage());
            e.printStackTrace();
        }

        loadInterface(Constants.FXML_HOME);
        }    

    @FXML
    private void afficherAbos(ActionEvent event) {
    }

    @FXML
    private void afficherRdvs(ActionEvent event) {
    }
    
}
