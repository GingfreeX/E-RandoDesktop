/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXPasswordField;
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
    
public class FXMLResetPasswordController implements Initializable {
    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXPasswordField password2;

    @FXML
    void NewPasswordAction(ActionEvent event) {
    ValidationSupport validationSupport = new ValidationSupport();
        if(password1.getText().isEmpty()){
     validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(password1, "required name"));

        }else if(password2.getText().isEmpty()){
            validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(password2, "required name"));
        }else if(!password1.getText().equals(password2.getText())){
             Notifications NotificationBuilder = Notifications.create()
                                .title("erreur")
                                .text("les mot de passes ne sont pas identiques")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();
        }else{
        IUserService userservice = new UserServices();
        User u = userservice.getUserByEmail(User.getMyemail());
        u.setPassword(password1.getText());
        userservice.ressettingpassword(u);
        Notifications NotificationBuilder = Notifications.create()
                                .title("succée")
                                .text("mot de passe changé avec succée")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
        }
       
        
        
    }
    
    
    @FXML
    void backtoauth(ActionEvent event) throws IOException {
    Navigation.getInstance().switching("FXMLAuthentification.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());

    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
