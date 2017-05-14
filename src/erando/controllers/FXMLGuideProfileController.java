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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author cimope
 */
public class FXMLGuideProfileController implements Initializable {

     @FXML
    private Label idl;
        @FXML
    private ScrollPane maincontainer;   
    

    
    @FXML
    private ImageView profilepic;

    @FXML
  public     void UpdateParametersAction(ActionEvent event) throws IOException {
       AnchorPane pane =  FXMLLoader.load(getClass().getResource("/erando/gui/FXMLUpatadeProfile.fxml"));
       maincontainer.setContent(pane);
        

    }
     @FXML
  public     void LoadHomeScene(ActionEvent event) throws IOException {
  AnchorPane pane =  FXMLLoader.load(getClass().getResource("/erando/gui/FXMLHome.fxml"));
       maincontainer.setContent(pane);
    }
       @FXML
    void DisplayPersonnaInformations(ActionEvent event) throws IOException {

 AnchorPane pane =  FXMLLoader.load(getClass().getResource("/erando/gui/FXMLInformationPersonnelles.fxml"));
 maincontainer.setContent(pane);
    }

    @FXML
  public     void postarticleAction(ActionEvent event) throws IOException {

 AnchorPane pane =  FXMLLoader.load(getClass().getResource("/erando/gui/FXMLAjouterPublication.fxml"));
 maincontainer.setContent(pane);
        
    }
    
   
    @FXML
  public     void ViewAllArticles(ActionEvent event) throws IOException {

 AnchorPane pane =  FXMLLoader.load(getClass().getResource("/erando/gui/FXMLListArticles.fxml"));
 maincontainer.setContent(pane);
     
    }
   @FXML
  public  void LogoutAction(ActionEvent event) throws IOException {
        User.setIdofuserAlreadyloggedin(0);
        
        Navigation.getInstance().switching("/erando/gui/FXMLAuthentification.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadData();
    }    
     public void LoadData(){
          File file=null ;
         int id = User.getIdofuserAlreadyloggedin();
        IUserService userservice = new UserServices();
       User user =  userservice.getUserbyId(id);
       String prenom  = user.getPrenom();
       if(user.getImagePath()==null){
            file = new File("D:\\Nouveau dossier\\ERandopi\\src\\erando\\images\\anonymous.jpg"); 
       }else{
             file = new File(user.getImagePath()); 
       }
     
        Image image = new Image(file.toURI().toString());
      
         profilepic.setImage(image);
      
        idl.setText(""+user.getNom()+"-"+prenom);
     
    }   
    
}
