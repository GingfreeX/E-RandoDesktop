/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXTextField;
import erando.models.User;
import erando.services.impl.UserServices;
import erando.services.interfaces.IUserService;
import erando.techniques.Navigation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;

/**
 * FXML Controller class
 *
 * @author cimope
 */
public class FXMLConfirmAccountController implements Initializable {

    @FXML
    private JFXTextField code;

    @FXML
    void confirm(ActionEvent event) {
        IUserService userservice = new UserServices();
        User u = userservice.getUserByEmail(User.getMyemail());
     Boolean yes =  userservice.Checkconfirmationtoken(u,code.getText());
     if(code.getText().isEmpty()){
       ValidationSupport validationSupport = new ValidationSupport();
      validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(code, "required name"));
     }
       if(yes==false){
           Notifications NotificationBuilder = Notifications.create()
                                .title("erreur ")
                                .text(" code incorrect ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(7))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();
           
       }else{
           userservice.ConfirmAccount(u,code.getText());
                      Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text(" Votre compte est maintenat activé ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(7))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
           
       }
       
        
    }
    
    @FXML
    void back(ActionEvent event) throws IOException {
   Navigation.getInstance().switching("FXMLAuthentification.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
