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
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
   

public class FXMLForgotPasswordController implements Initializable {
 @FXML
    private AnchorPane containerforforgotpwd;

    @FXML
    private JFXTextField email;

    @FXML
    void SendMyaTokenCode(ActionEvent event) {
          ValidationSupport validationSupport = new ValidationSupport();
        try{
            if(email.getText().isEmpty()){
validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(email, "required name"));
            }else{
                IUserService userservice = new UserServices();
                User user = userservice.getUserByEmail(email.getText());
                String token = UUID.randomUUID().toString();
                if(userservice.CheckIfUserExist(email.getText())==true){
                userservice.SendMailAndAddTokenToUser(user,token);
                User.setMyemail(email.getText());
                   Navigation.getInstance().switching("/erando/gui/FXMLRessetingPasswordCheckCode.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());
                   Notifications NotificationBuilder = Notifications.create()
                                .title("error")
                                .text("un mail est envoyer à l'adresse   "+email.getText()+"   contient un code de confirmation Veuiller verifier votre boite mail")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
                       
              
                       
                        }else{
                     Notifications NotificationBuilder = Notifications.create()
                                .title("error")
                                .text("email incorrect")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.TOP_LEFT);
                        NotificationBuilder.showError();
                }
                
            }
         }catch(Exception e){
            e.printStackTrace();
        }

    }
    
    
    @FXML
    void SendSMS(ActionEvent event) {
       ValidationSupport validationSupport = new ValidationSupport();
        try{
            if(email.getText().isEmpty()){
validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(email, "required name"));
            }else{
                IUserService userservice = new UserServices();
                User user = userservice.getUserByEmail(email.getText());
                String token = UUID.randomUUID().toString();
                if(userservice.CheckIfUserExist(email.getText())==true){
                userservice.SendSMSAndAddTokenToUser(token,user);
                  User.setMyemail(email.getText());
                   Navigation.getInstance().switching("/erando/gui/FXMLRessetingPasswordCheckCode.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());
                   Notifications NotificationBuilder = Notifications.create()
                                .title("error")
                                .text("un code de récuperation vous a était  envoyer par SMS  ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(8))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
                       
              
                       
                        }else{
                     Notifications NotificationBuilder = Notifications.create()
                                .title("error")
                                .text("email incorrect")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();
                }
                
            }
         }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    @FXML
    void backtoauth(ActionEvent event) throws IOException {
    Navigation.getInstance().switching("/erando/gui/FXMLAuthentification.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
