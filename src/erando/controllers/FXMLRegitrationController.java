
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import erando.models.User;
import erando.services.impl.UserServices;
import erando.services.interfaces.IUserService;
import erando.techniques.Navigation;
import erando.utils.BCrypt;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;


/**
 *
 * @author cimope
 */
public class FXMLRegitrationController implements Initializable {
   @FXML
    private JFXTextField pseudo;

   @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password1;

    @FXML
    private JFXPasswordField password2;
    @FXML
    private JFXComboBox<String> role;
 @FXML
    void back(ActionEvent event) throws IOException {
            Navigation.getInstance().switching("FXMLAuthentification.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());

    }   
    
    
 
    @FXML
    void RegisterAction(ActionEvent event) throws IOException {
 ValidationSupport validationSupport = new ValidationSupport();
 if(pseudo.getText().isEmpty() || email.getText().isEmpty() || password1.getText().isEmpty()||password2.getText().isEmpty()){
         validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(pseudo, "required name"));
   validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(email, "required name"));
      validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(password1, "required name"));
      validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(password2, "required name"));
        Notifications NotificationBuilder = Notifications.create()
                                .title("Erreur")
                                .text("veuillerz remplir tous les  champs s'il vous plait")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showWarning();
            
    }else if( ! password1.getText().equals(password2.getText())){
    
             Notifications NotificationBuilder = Notifications.create()
                                .title("Erreur")
                                .text("les deux mot de passe ne sont pas identiques")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showWarning();

    }else{
        IUserService userservice = new UserServices();
        String password = BCrypt.hashpw(password1.getText(),BCrypt.gensalt(13));
        User user = new User(pseudo.getText(),password, email.getText(),role.getValue());
        userservice.add(user);
        User.setMyemail(email.getText());
        Navigation.getInstance().switching("FXMLConfirmAccount.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
        Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text("un email à été envoyer à l'adresse "+email.getText()+" contien un code de confirmation "
                                        + "veuiller le saisir pour activer votre compte ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(7))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
        
    }

    }
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData();
    }    
    public void LoadData(){
        role.getItems().addAll(
                "Guide","membre"
        );
    }
    
}

