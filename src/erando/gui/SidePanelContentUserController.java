package erando.gui;



import com.jfoenix.controls.JFXButton;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class SidePanelContentUserController  {


    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton exit;

     


    
      @FXML
    void retourOnAction(ActionEvent event) throws IOException {


      
    }
    
    
        @FXML
    void ajouterAnnonceOnAction(ActionEvent event) {
 
    }
   @FXML
    void listeReserve(ActionEvent event) throws IOException {
            //Navigation.getInstance().switching(, s);
            Navigation.getInstance().switching("ReservationByuser.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());

    }
    @FXML
    void accueilRando(ActionEvent event) throws IOException {
                    Navigation.getInstance().switching("affichelisteRandonnee.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());

    }
    
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

  
    
}
