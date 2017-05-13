/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author student1
 */
public class MenuTemoignageController implements Initializable {

    
    @FXML
    private JFXButton AddAvis;

    @FXML
    private JFXButton affichAvis;

    @FXML
    private JFXButton modifAvis;

    void changescene(String gui, ActionEvent event) throws IOException {
 FXMLLoader fxmlloder = new FXMLLoader(getClass().getResource(gui));

            Parent root1 = fxmlloder.load();
            Scene home_scene = new Scene(root1);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_scene);
            app_stage.show();
}


    
    @FXML
    void btnAffich(ActionEvent event) throws IOException {
changescene("/erandopi/gui/AfficheTemoignage.fxml",event);
    }

    @FXML
    void btnAvis(ActionEvent event) throws IOException {
changescene("/erandopi/gui/AjoutTemoignage.fxml",event);
    }

    @FXML
    void btnModif(ActionEvent event) throws IOException {
changescene("/erandopi/gui/ModifTemoignage.fxml",event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
