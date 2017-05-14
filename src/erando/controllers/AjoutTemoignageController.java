/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import erando.models.Temoignage;
import erando.services.impl.TemoignageService;
import erando.services.interfaces.ITemoignageService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author student1
 */
public class AjoutTemoignageController implements Initializable {

       @FXML
    private Button btn_Temoignage;

     @FXML
    private JFXTextArea txt_Temoignage;

    @FXML
    private Label lbl_Temoignage;
   @FXML
    private JFXButton btnRetour;


    @FXML
    void btnRetour(ActionEvent event) throws IOException {
changescene("/erando/gui/MenuTemoignage.fxml",event);

    }
    @FXML
    void AjoutTemoignage(ActionEvent event) {
        TemoignageService temoignageService = new TemoignageService();
        Temoignage temoignage = new Temoignage(txt_Temoignage.getText());
   temoignageService.add(temoignage);
             Notifications notificationBuilder = Notifications.create()
                .title("Ok")
                .text("Votre Avis a été Ajouté")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    void changescene(String gui, ActionEvent event) throws IOException {
 FXMLLoader fxmlloder = new FXMLLoader(getClass().getResource(gui));

            Parent root1 = fxmlloder.load();
            Scene home_scene = new Scene(root1);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_scene);
            app_stage.show();
}


    
 
}
