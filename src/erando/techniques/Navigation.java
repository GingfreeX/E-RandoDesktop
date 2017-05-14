/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.techniques;

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
    public Navigation(){
        
    }
    public   void  switching(String fxmlfile,Stage s ) throws IOException{
       this.parent = FXMLLoader.load(getClass().getResource(fxmlfile));
        this.scene = new Scene(this.parent);
       this.stage = s;
        this.stage.hide();
        this.stage.setScene(this.scene);
        this.stage.show();
    
        
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
        }
    }
    public static Navigation getInstance() {
        if (navigation  == null) {
            navigation  = new Navigation ();
        }
        return navigation;
    }
}
