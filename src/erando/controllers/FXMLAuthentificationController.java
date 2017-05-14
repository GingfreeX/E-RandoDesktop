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
import erando.utils.BCrypt;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.lang.Object;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 *
 * @author cimope
 */
public class FXMLAuthentificationController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField password;
    
    @FXML
    private Pane maincontainer;
    
    @FXML
    private AnchorPane container;
   
    @FXML
void LoginAction(ActionEvent event) throws IOException {
     int i =1;
   ValidationSupport validationSupport = new ValidationSupport();
   if( login.getText().isEmpty() ){
    validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(login, "required name"));
   }else if( password.getText().isEmpty()){
    validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error( password, "required name"));      
   }else if ( login.getText().isEmpty()  && password.getText().isEmpty()){
    validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(login, "required name"));
    validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error( password, "required name"));
   }
   else{
           IUserService userservice = new UserServices();
           Boolean result = userservice.Authentification(login.getText(), password.getText());
     
       if(result==true){
       int id = User.getIdofuserAlreadyloggedin();
       User user = userservice.getUserbyId(id);
       if(userservice.checkEnabled(user)==false){
   Navigation.getInstance().switching("/erando/gui/FXMLConfirmAccount.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());

       }else{
       if(userservice.CheckRole(user).equals("guide")){
          Navigation.getInstance().switching("/erando/gui/FXMLGuideProfile.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());
       }else{
            Navigation.getInstance().switching("/erando/gui/FXMLProfile.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());
       }

      
      }}else{
                        Notifications NotificationBuilder = Notifications.create()
                                .title("Erreur")
                                .text("vérifier vos parametres ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.BASELINE_LEFT);
                        NotificationBuilder.showError();
        }
    }
}
     @FXML
    void movetoRegistration(ActionEvent event) throws IOException {
      
      Navigation.getInstance().switching("/erando/gui/FXMLRegistration.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());

       
    }
    
    @FXML
    void ForgetPassword(ActionEvent event) throws IOException {
    Navigation.getInstance().switching("/erando/gui/FXMLForgotPassword.fxml",(Stage)((Node)event.getSource()).getScene().getWindow());
    }

    
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          int numberOfSquares = 100;
        while (numberOfSquares > 0){
            generateAnimation();
            numberOfSquares--;
        }
     
      }    

  public void generateAnimation(){
        Random rand = new Random();
        int sizeOfSqaure = rand.nextInt(50) + 1;
        int speedOfSqaure = rand.nextInt(10) + 5;
        int startXPoint = rand.nextInt(420);
        int startYPoint = rand.nextInt(350);
        int direction = rand.nextInt(5) + 1;

        KeyValue moveXAxis = null;
        KeyValue moveYAxis = null;
        Rectangle r1 = null;

        switch (direction){
            case 1 :
                // MOVE LEFT TO RIGHT
                r1 = new Rectangle(0,startYPoint,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), 1000 -  sizeOfSqaure);
                break;
            case 2 :
                // MOVE TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), 1000 - sizeOfSqaure);
                break;
            case 3 :
                // MOVE LEFT TO RIGHT, TOP TO BOTTOM
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), 1000 -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(), 1000 - sizeOfSqaure);
                break;
            case 4 :
                // MOVE BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,420-sizeOfSqaure ,sizeOfSqaure,sizeOfSqaure);
                moveYAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 5 :
                // MOVE RIGHT TO LEFT
                r1 = new Rectangle(420-sizeOfSqaure,startYPoint,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(), 0);
                break;
            case 6 :
                //MOVE RIGHT TO LEFT, BOTTOM TO TOP
                r1 = new Rectangle(startXPoint,0,sizeOfSqaure,sizeOfSqaure);
                moveXAxis = new KeyValue(r1.xProperty(),1000 -  sizeOfSqaure);
                moveYAxis = new KeyValue(r1.yProperty(),1000 - sizeOfSqaure);
                break;

            default:
                System.out.println("default");
        }

        r1.setFill(Color.web("#F89406"));
        r1.setOpacity(0.1);

        KeyFrame keyFrame = new KeyFrame(Duration.millis(speedOfSqaure * 1000), moveXAxis, moveYAxis);
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
        container.getChildren().add(container.getChildren().size()-1,r1);
    }    
}
