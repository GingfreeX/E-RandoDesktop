/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import erando.models.User;
import erando.services.impl.UserServices;
import erando.services.interfaces.IUserService;
import erando.techniques.Navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cimope
 */
public class FXMLAdminProfileController implements Initializable {
   @FXML
    private Label idl;
    
   @FXML
    void LogoutAction(ActionEvent event) throws IOException {
        User.setIdofuserAlreadyloggedin(0);
         User.setIdofuserAlreadyloggedin(0);
        Navigation.getInstance().switching("/erando/gui/FXMLAuthentification.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void LoadData(){
         int id = User.getIdofuserAlreadyloggedin();
        IUserService userservice = new UserServices();
       User user =  userservice.getUserbyId(id);
       String prenom  = user.getPrenom();
      
        idl.setText(""+user.getNom()+"-"+prenom);
     
    }
}
