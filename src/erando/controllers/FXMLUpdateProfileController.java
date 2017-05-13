
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import erando.models.User;
import erando.services.impl.UserServices;
import erando.services.interfaces.IUserService;
import erando.techniques.Navigation;
import erando.utils.BCrypt;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javax.management.Query.value;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;

/**
 *
 * @author cimope
 */
public class FXMLUpdateProfileController implements Initializable {
  @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField age;
    
    @FXML
    private JFXTextField numeroTel;
    

    @FXML
    private JFXTextField imagepath;
    private static Path destination;
    private static File selectedfile;
     @FXML
    void AddProfilePic(ActionEvent event) {
    FileChooser fc = new FileChooser();
       selectedfile = fc.showOpenDialog(null);
        
        destination = Paths.get("D:/Nouveau dossier/ERandopi/src/erando/images/",selectedfile.getName());
       
        imagepath.setText(selectedfile.getAbsolutePath());
    }

    @FXML
    void UpdateUserAction(ActionEvent event) throws IOException {
        ValidationSupport validationSupport = new ValidationSupport();
        if(nom.getText().isEmpty() || prenom.getText().isEmpty() ||description.getText().isEmpty()||age.getText().isEmpty()){
         validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(nom, "required name"));
         validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(prenom, "required name"));
         validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(description, "required name"));
         validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(age, "required name"));
        Notifications NotificationBuilder = Notifications.create()
                                .title("Erreur")
                                .text("veuillerz remplir tous les  champs s'il vous plait")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showWarning();
            
    }else{
       
       IUserService userservice = new UserServices();
      
         User user1 = new User(nom.getText(), prenom.getText(),Integer.parseInt(age.getText()),description.getText(),null,null,Integer.parseInt(numeroTel.getText()));
      User ConnectedUser = userservice.getUserbyId(User.getIdofuserAlreadyloggedin()); 
         if(selectedfile==null){
        user1.setImagePath(ConnectedUser.getImagePath());
        }else{
            Files.copy(selectedfile.toPath(),destination,StandardCopyOption.REPLACE_EXISTING); 
             user1.setImagePath(destination.toString());
        }
         
         user1.setId(User.getIdofuserAlreadyloggedin());
         userservice.update(user1);
         User u = userservice.getUserbyId(User.getIdofuserAlreadyloggedin());
         if(u.getRole().equals("a:1:{i:0;s:10:\"ROLE_MEMBRE\";}")){
        Navigation.getInstance().switching("FXMLProfile.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());
    }else{
             Navigation.getInstance().switching("FXMLProfile.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());   
         }
         Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text("Profil mis à jour")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
        
    }

    } 
     @FXML
    void backtoprofile(ActionEvent event) throws IOException {
     
         Navigation.getInstance().switching("FXMLProfile.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
         
          LoadData();
                  
   
      
    }    
public void LoadData(){
   int id= User.getIdofuserAlreadyloggedin();
 
          IUserService userservice = new UserServices();
          User user = userservice.getUserbyId(id);
          nom.setText(user.getNom());
          prenom.setText(user.getPrenom());
          description.setText(user.getDescrption()); 
   
     
}
   
    
}

