package GUI.Panier;


import Entities.Profil;
import Services.ProfilService;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class ModifprofilController {


    private Stage dialogStage;
    private Profil person;
    private boolean okClicked = false;
    @FXML
    private TextField username;
    @FXML
    private TextField game;
    @FXML
    private TextField description;
    @FXML
    private TextField numero;
 ProfilService profilService = new ProfilService();
 /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setProfil(ActionEvent event) {
        this.person = person;
 // Step 1
  Node node = (Node) event.getSource();
  Stage stage = (Stage) node.getScene().getWindow();
  // Step 2
  Profil person = (Profil) stage.getUserData();
  System.out.println(person.getUsername());
        username.setText(person.getUsername());
        game.setText(person.getGame());
        description.setText(person.getDescription());
        numero.setText(Integer.toString(person.getNumero()));
       
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
     /*   if (isInputValid()) {
             List<Profil> listProfil =profilService.ShowProfil();
                       
              
                    
              
          //  person.setUsername(username.getText());
            person.setGame(game.getText());
            person.setDescription(description.getText());
            person.setNumero(Integer.parseInt(numero.getText()));
        for(Profil p : listProfil) {
                if (p.getUsername().equals(username.getText()))
                {     
Profil p1 = new Profil(person.getDescription(),person.getGame(),person.getNumero());
ProfilService ps = new ProfilService();
ps.UpdateProfil(p1,p.getId() );
  }
                }*/
            okClicked = true;
            dialogStage.close();
      //  }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (username.getText() == null || username.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }
        if (game.getText() == null || game.getText().length() == 0) {
            errorMessage += "No valid game!\n";
        }
        if (description.getText() == null || description.getText().length() == 0) {
            errorMessage += "No valid description!\n";
        }

        if (numero.getText() == null || numero.getText().length() == 0) {
            errorMessage += "No valid number!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(numero.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid number code (must be an integer)!\n";
            }
        }

    


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}