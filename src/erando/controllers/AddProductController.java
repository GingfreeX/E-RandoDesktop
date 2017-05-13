/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import com.lowagie.text.DocumentException;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.restfb.types.User;
import erando.models.Parameters;
import erando.models.Product;
import erando.services.impl.Sms;
import erando.services.impl.ProductService;
import erando.services.impl.SmsService;
import erando.services.impl.mailToSubs;
import erando.services.interfaces.ISms;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import erando.services.interfaces.IShopService;

/**
 * FXML Controller class
 *
 * @author F.Mouhamed
 */
public class AddProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Trucs de camping",
        "Matériaux de chasse",
        "Vêtements"
    );
    @FXML private JFXTextField  pTitre;
    @FXML private JFXTextField  pPrix;
    @FXML private JFXTextArea  pDescription;
    @FXML private JFXComboBox  pType =  new JFXComboBox(options);
    @FXML private RequiredFieldValidator validator;
    @FXML private ImageView pImage;
    @FXML
    private JFXCheckBox shareTwitter;
    @FXML
    private JFXCheckBox shareFacebook;
    private String imageName;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        pTitre.setLabelFloat(true);
        validator.setMessage("Input Required");
        pTitre.getValidators().add(validator);
        pTitre.focusedProperty().addListener((o,oldVal,newVal)->{
        if(!newVal) pTitre.validate();
        });
        pType.getItems().addAll(options);
        
        /********************************************************/
        //Filter Inputs 
        /********************************************************/
        UnaryOperator<Change> filter = change -> {
        String text = change.getText();
        //check if price is > 0 or not (we cant allow - values ...)
        if (text.matches("[0-9]*")) {
        return change;
        }
            return null;
        };    
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        pPrix.setTextFormatter(textFormatter);
    }  
    @FXML
    private void addProductAction(ActionEvent event) throws FileNotFoundException, IOException, TwitterException, DocumentException {
        Product p = new Product();
        Sms sms = new Sms() ;
        ISms smsservice = new SmsService();
        IShopService productService = new ProductService();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM/HH:mm:ss");
        
        p.setTitre(pTitre.getText());
        p.setPrix(Integer.parseInt(pPrix.getText()));
        p.setDescription(pDescription.getText());
        p.setType(pType.getValue().toString());
        p.setDate(sdf.format(cal.getTime()));
        p.setImage(imageName);
        if (!pTitre.getText().isEmpty() && !pPrix.getText().isEmpty() && !pDescription.getText().isEmpty() && !pType.getValue().toString().isEmpty() ){
        productService.add(p);
        ////////send sms to subs
        sms.setNum(""+Parameters.user.getNumTel());
        sms.setMessagetel("Product Added To Store go check it !");
        smsservice.sendSms(sms);
        
        ////////share on facebook (when asked by the owner ! )
        if (shareFacebook.isSelected()){
        String accessToken = "EAACEdEose0cBAKLtkZBKZCBoEkx4MApf3HxDMAR93PoJ6lAAuZAMdfY9vtob2ii78C6TN88hSV8HK0tDZBskaUz5pcbH1HqVeDRISuEHsG0qqUZBca4gHGnANPWcZBSZA9RNFHpbwVHJ46ITntn52SGQWetPPaZBsNlsFXbpcDrKytOVtmspQzfrM8GiUQtm1kQZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);
        File fs = new File("C:\\Users\\F.Mouhamed\\Desktop\\Esprit\\ERandoPi\\userfiles\\"); 
        fs.getParentFile().setExecutable(true);
        fs.getParentFile().setReadable(true);
        fs.getParentFile().setWritable(true);
        ////////FileInputStream fis = new FileInputStream(fs.getParentFile());
        User me = fbClient.fetchObject("me", User.class);
        FacebookType response;
        response = fbClient.publish("me/feed",FacebookType.class,Parameter.with("message",p.getTitre()+"\n "+p.getDescription()+"\n Prix:"+p.getPrix()));        
        }
        
        ////////share on twitter (when asked by the owner ! )
        if (shareTwitter.isSelected()){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
            .setOAuthConsumerKey("dHU6c4cXI6HDeLI3pakG8PYtp")
            .setOAuthConsumerSecret("n0NxZVXgpEMGJboWYBSD1nfbaa3Ov2qL0e9h2GzyUsa8wQ0q0p")
            .setOAuthAccessToken("729655065716346881-gukmKiOsT5WFv05t3yfQFrWgoPycQGD")
            .setOAuthAccessTokenSecret("4qTGW5YdG8j9biJeAybzIcivCPZaAOqES2PhoJI9S7WKL");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter tw = tf.getInstance();
        String StatusMessage = ("Titre :"+p.getTitre()+"\nDescription:\n"+p.getDescription()+"\nPrix:"+p.getPrix());
        StatusUpdate status = new StatusUpdate(StatusMessage);
        tw.updateStatus(status);
        }
        /////////show notification
        Notifications notificationBuilder = Notifications.create()
                .title("sucess")
                .text("produit ajouter")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();
        /////////send emails to users subscribed to this type of product
                List<String> subs = productService.getSubscribes(p.getType());
                mailToSubs mails = new mailToSubs();
                for (String s : subs){
                mails.envoyerfacture(s.toString(),p.getImage().toString(),p.getId(),p.getTitre(),p.getPrix(),p.getDescription());      
                }
        }
    }
    @FXML private JFXButton  newPhotoButton;
    @FXML
    private void uploadPictureAction(ActionEvent event) {
        Stage currentStage = (Stage) newPhotoButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File f = new File("C://Users//F.Mouhamed//Desktop");
        fileChooser.setInitialDirectory(f);
        File selectedFile = fileChooser.showOpenDialog(currentStage);
            Path movefrom = FileSystems.getDefault().getPath(selectedFile.getPath());
            imageName = selectedFile.getName();
            Path target = FileSystems.getDefault().getPath("userfiles/"+selectedFile.getName());
            Path targetDir = FileSystems.getDefault().getPath("userfiles/");
            try{
                Files.move(movefrom,target,StandardCopyOption.ATOMIC_MOVE);
            }catch (IOException e){}
        Image image = new Image("file:///C:/Users/F.Mouhamed/Desktop/Esprit/ERandoPi/userfiles/"+imageName);
        pImage.setImage(image);
        
    }
    
}
