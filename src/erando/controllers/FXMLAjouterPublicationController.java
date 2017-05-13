/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import erando.models.PublicationGroup;
import erando.models.User;
import erando.services.impl.PublicationServices;
import erando.services.impl.UserServices;
;
import erando.services.interfaces.IService;
import erando.services.interfaces.IUserService;
import erando.techniques.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;import erando.services.interfaces.IService;
import erando.services.interfaces.IUserService;
import erando.techniques.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;import erando.services.interfaces.IService;
import erando.services.interfaces.IUserService;
import erando.techniques.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;import erando.services.interfaces.IService;
import erando.services.interfaces.IUserService;
import erando.techniques.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;

/**
 * FXML Controller class
 *
 * @author cimope
 */
public class FXMLAjouterPublicationController implements Initializable {

   
   @FXML
    private JFXComboBox<String> Section;

    @FXML
    private JFXTextField description;
     @FXML
    private JFXTextField imagepath;
    private static Path destination;
    private static File selectedfile;
    @FXML
    void addimage(ActionEvent event) throws IOException {

        FileChooser fc = new FileChooser();
       selectedfile = fc.showOpenDialog(null);
        
        destination = Paths.get("D:/Nouveau dossier/ERandopi/src/erando/images/",selectedfile.getName());
       
        imagepath.setText(selectedfile.getAbsolutePath());
  
        
    }
    @FXML
    void ajouterPublicationAction(ActionEvent event) throws SQLException, IOException {
   ValidationSupport validationSupport = new ValidationSupport();
   if(Section.getValue().isEmpty() ){
           validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(Section, "required name"));

            
    }else if(description.getText().isEmpty()){
 validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(description, "required name"));

    }else{
        
        IService Publicationservice = new PublicationServices();
        IUserService  userservice = new UserServices();
        IService es =new PublicationServices();
        int id = User.getIdofuserAlreadyloggedin();
        User user = userservice.getUserbyId(id);
     PublicationGroup pub = new PublicationGroup(description.getText(),Section.getValue() ,new Date(Calendar.getInstance().getTime().getTime()), user);

        if(selectedfile==null){
        pub.setImagepath(null);
        }else{
            Files.copy(selectedfile.toPath(),destination); 
             pub.setImagepath(destination.toString());
        }

           Publicationservice.add(pub);
            Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text("Article publiée  ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showConfirm();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      int id = User.getIdofuserAlreadyloggedin();
       LoadData();
    }    
    public void LoadData(){
        Section.getItems().addAll(
                "chasse","peche","camping"
        );
    }
}
