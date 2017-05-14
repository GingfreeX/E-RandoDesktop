/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package erandopi;

import erando.models.Publication;
import erando.services.impl.MembreService;
import erando.services.impl.PublicationService;
import erando.services.interfaces.IMembreService;
import erando.services.interfaces.IPublicationService;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author wassim
 */
public class ERandoPi extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/erando/gui/groupe_home.fxml"));
       stage.setTitle("E-Rando");
       stage.setResizable(false);
       stage.centerOnScreen();
       stage.getIcons().add(new Image(this.getClass().getResource("/erando/images/logo.png").toString()));

        
        
        Scene scene = new Scene(root);
                 stage.setScene(scene);
                 
       
        scene.getStylesheets().add("erando/utils/groupe_home.css");
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
