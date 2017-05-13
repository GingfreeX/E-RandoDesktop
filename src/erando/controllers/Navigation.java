/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.gui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author cimope
 */
public  class  Navigation {
    private   Parent parent ;
    private   Scene scene;
    private  Stage stage;
   private ActionEvent event;
    private static Navigation navigation;
    private Navigation(){
        
    }
    public   void  switching(String fxmlfile,Stage s ) throws IOException{
       this.parent = FXMLLoader.load(getClass().getResource(fxmlfile));
        this.scene = new Scene(this.parent);
       this.stage = s;
        this.stage.hide();
        this.stage.setScene(this.scene);
        this.stage.show();
    
        
    }
    public static Navigation getInstance() {
        if (navigation  == null) {
            navigation  = new Navigation ();
        }
        return navigation;
    }
}
