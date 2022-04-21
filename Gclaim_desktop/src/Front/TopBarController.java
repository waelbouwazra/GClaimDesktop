package Front;

import GUI.MenuFrontController;
import Services.ServiceUser;
import Tools.Animations;
import Tools.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class TopBarController implements Initializable {

    private final Color COLOR_GRAY = new Color(0.9, 0.9, 0.9, 1);
    private final Color COLOR_BLUE = new Color(0.7, 0.7, 0.20, 1);
    private final Color COLOR_DARK = new Color(0.20, 0.23, 0.25, 1);
    private Button[] liens;

    @FXML
    private Button btnAbos;
    @FXML
    private Button btnRdvs;
    @FXML
    private AnchorPane mainComponent;
   
    @FXML
    private Button article;
   private ServiceUser US=new ServiceUser();
    @FXML
    private Button panier;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        liens = new Button[]{
                btnAbos,
                btnRdvs,article,panier
        };

        mainComponent.setBackground(new Background(new BackgroundFill(COLOR_BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
            lien.setBackground(new Background(new BackgroundFill(COLOR_BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                        Animations.animateButton(lien, COLOR_GRAY, Color.WHITE, COLOR_BLUE, 0, false);

          
        }
        btnAbos.setTextFill(Color.WHITE);
        btnRdvs.setTextFill(Color.WHITE);
               
                 article.setTextFill(Color.WHITE);
              panier.setTextFill(Color.WHITE);

    }

    @FXML
    private void afficherAbos(ActionEvent event) {
        goToLink(Constants.FXML_DISPLAY_ALL_ABO);

        btnAbos.setTextFill(COLOR_BLUE);
      
    }

    @FXML
    public void afficherRdvs(ActionEvent event) {
        goToLink(Constants.FXML_DISPLAY_PROFIL);

        btnRdvs.setTextFill(COLOR_BLUE);
        
    }
   
   
    private void goToLink(String link) {
        for (Button lien : liens) {
            lien.setTextFill(COLOR_DARK);
           
        }
        MainWindowController.getInstance().loadInterface(link);
    }

    @FXML
    private void article(ActionEvent event) {
        
    }

  

    @FXML
    private void acceuil(MouseEvent event) {
         goToLink(Constants.FXML_HOME);
    }

    @FXML
    private void panier(ActionEvent event) {
         goToLink(Constants.FXML_DISPLAY_PANIER);

        panier.setTextFill(COLOR_BLUE);
    }

    @FXML
    private void profile(MouseEvent event) {
         goToLink(Constants.FXML_DISPLAY_PROFILE);
    }
}
