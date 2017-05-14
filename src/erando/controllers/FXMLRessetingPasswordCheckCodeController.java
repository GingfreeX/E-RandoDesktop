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
public class FXMLRessetingPasswordCheckCodeController implements Initializable {

    @FXML
    private JFXTextField Code;

    @FXML
    void CheckTokenAction(ActionEvent event) throws IOException {
         ValidationSupport validationSupport = new ValidationSupport();
        if(Code.getText().isEmpty()){
     validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(Code, "required name"));

        }else{
        IUserService userservice = new UserServices();
        String email = User.getMyemail();
        User user = userservice.getUserByEmail(email);
        if(userservice.CheckToken(user,Code.getText())==true){
       Navigation.getInstance().switching("/erando/gui/FXMLResetPassword.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());

        }else{
              Notifications NotificationBuilder = Notifications.create()
                                .title("error")
                                .text("code incorrect")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();
        }
    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
