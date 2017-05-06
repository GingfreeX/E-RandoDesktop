/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import erando.gui.Navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aloulou
 */
public class MenuRandoController implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    void modifsupp(ActionEvent event) throws IOException {
                            Navigation.getInstance().switching("listeRandonnee.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());

    }

    @FXML
    void showajout(ActionEvent event) throws IOException {
                            Navigation.getInstance().switching("ajoutRandonnee.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
