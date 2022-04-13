package GUI.Login;

import Entities.*;
import Services.*;
import Tools.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

/**
 *
 * @author Hassen Chouadah
 */
public class FXMain extends Application {

    ServiceUser US;
    RealTimeService realTime;
    @Override
     public void start(Stage primaryStage) {
        try {
                    Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                    Scene scene = new Scene(root, 1366, 768);
                    primaryStage.setTitle("GClaim");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
    }
    @Override
    public void stop() throws Exception {
        System.out.println("closed connection");
        realTime.closeConnection();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
