/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.techniques;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author wassim
 */
public  class  Navigation {

    public Navigation() {
    }
    
    
    public void switchScene(String fxmlfile,ActionEvent event){
        
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/erando/gui/"+fxmlfile));
            
            Scene scene = new Scene(parent);
            
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.hide();
            stage.setScene(scene);
            scene.getStylesheets().add("erando/utils/groupe_home.css");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
