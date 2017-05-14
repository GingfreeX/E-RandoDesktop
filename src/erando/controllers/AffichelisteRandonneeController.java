/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import erando.controllers.Randonne;
import erando.services.impl.RandonneService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author amrouche
 */
public class AffichelisteRandonneeController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
     private List<Randonne> annonces;
     @FXML
    private AnchorPane prim;

    @FXML
    private VBox annoncesVBox;

    @FXML
    private JFXButton ajouterOfferButton;

    @FXML
    private JFXButton ajouterDemandeButton;

    @FXML
    private AnchorPane annoncesAnchorPane;

    @FXML
    private Label fromToLabel;

    @FXML
    private JFXButton reserverButton;

    @FXML
    private Label distanceLabel;

    @FXML
    private Label prixLabel;

    @FXML
    private Label dateDepartLabel;

    @FXML
    private Label placesLabel;

    @FXML
    private Separator separator;

    @FXML
    private Label prixLabel1;

    @FXML
    private Label placesLabel1;

    @FXML
    private ImageView userImageView;

    @FXML
    private Label driverNameLabel;

    @FXML
    private Label experianceLabel;

    @FXML
    private JFXTextField txdepart;

    @FXML
    private JFXTextField txdestination;

    @FXML
    private JFXDatePicker txdate;

    @FXML
    private JFXButton btnrechercher;

    @FXML
    private JFXButton rechercheravancée;

    @FXML
    private JFXTextField txprix;

    @FXML
    private JFXComboBox<?> cbtype;

    @FXML
    private JFXComboBox<?> cbsexe;

    @FXML
    private JFXButton btnrechercheavancee2;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
 
    
    private GeocodingService geocodingService;
    GeocodingResult[] georesults;
    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
         List<String> m = new ArrayList();
String[] s;
    @FXML
    void rechercheavancéeAction(ActionEvent event) {

    }
 @FXML
    void fromOnkeyTypedEvent(KeyEvent event) {

//        System.out.println("testettstst");
//         //GeocoderRequest request = new GeocoderRequest(from.get());
//                /*    geocodingService.getGeocoding(new GeocoderRequest(from.get()), (GeocodingResult[] results, GeocoderStatus status)->{
//                                     System.out.println("results "+results[0].getFormattedAddress());
//                        System.out.println("results number "+results.length);
//                    
//                    });*/
//
//   geocodingService.geocode(txdepart.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
//        m.clear();
//        //int i;                 
//  for(int i =0;i<results.length;i++){
//        s=new String[results.length];
//      s[i] = results[i].getFormattedAddress();
//    
//         m.add(results[i].getFormattedAddress());
//         
//  }
//     
//            
//       for (GeocodingResult result : results) {
//           
//       
//                              TextFields.bindAutoCompletion(txdepart, s);
//
//            }
//       
//        TextFields.bindAutoCompletion(txdepart, t-> {
// 
//            return m;
// 
//        });
//       
//          
//
//        });
    }
    @FXML
    void rechercherannonce(ActionEvent event) {
if(txdepart.getText().length()==0){
      Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("veuillez remplir au moins un champs");
                a1.setContentText("il faut au moins remplir un champs");
                a1.show();
                txdepart.setText("");}
else {
  String depart =txdepart.getText();
          String destination=txdestination.getText() ;
          Date date=new Date(10112017);
           if(txdate.getValue()!= null){
               date = Date.valueOf(txdate.getValue());
           }
               
       RandonneService annService = new RandonneService();
     annonces = annService.rechercherannonceselontrajet(depart, destination,date);
        annoncesVBox.getChildren().clear();
         if(!annonces.isEmpty()){
           for (Randonne annonce : annonces) {
                
       
                AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(annoncesAnchorPane.getStyle());
        newAnnoncesAnchorPane.setEffect(annoncesAnchorPane.getEffect());

       
        //fromtolabel         
        Label fromToLabel2 = new Label();
        fromToLabel2.setFont(fromToLabel.getFont());
        fromToLabel2.setTextFill(fromToLabel.getTextFill());
        fromToLabel2.setLayoutX(fromToLabel.getLayoutX());
        fromToLabel2.setLayoutY(fromToLabel.getLayoutY());
        fromToLabel2.setText(annonce.getTitre());
       //datedepartlabel
        Label dateDepartLabel2 = new Label();
        dateDepartLabel2.setFont(dateDepartLabel.getFont());
        dateDepartLabel2.setTextFill(dateDepartLabel.getTextFill());
        dateDepartLabel2.setLayoutX(dateDepartLabel.getLayoutX());
        dateDepartLabel2.setLayoutY(dateDepartLabel.getLayoutY());
        dateDepartLabel2.setText("Date Depart: "+annonce.getDate());
        
//distanceLabel
        /*Label distanceLabel2 = new Label();
        distanceLabel2.setFont(distanceLabel.getFont());
        distanceLabel2.setTextFill(distanceLabel.getTextFill());
        distanceLabel2.setLayoutX(distanceLabel.getLayoutX());
        distanceLabel2.setLayoutY(distanceLabel.getLayoutY());
        distanceLabel2.setText(distanceLabel.getText());*/
        
        
        
       // PrixLabel
        Label distanceLabel2 = new Label();
        distanceLabel2.setFont(distanceLabel.getFont());
        distanceLabel2.setTextFill(distanceLabel.getTextFill());
        distanceLabel2.setLayoutX(distanceLabel.getLayoutX());
        distanceLabel2.setLayoutY(distanceLabel.getLayoutY());
        distanceLabel2.setText(distanceLabel.getText());
        distanceLabel2.setText("Prix: "+annonce.getPrix());

        
        //prixLabel
        Label prixLabel2 = new Label();
        prixLabel2.setFont(prixLabel.getFont());
        prixLabel2.setTextFill(prixLabel.getTextFill());
        prixLabel2.setLayoutX(prixLabel.getLayoutX());
        prixLabel2.setLayoutY(prixLabel.getLayoutY());
        prixLabel2.setText(""+annonce.getPrix());
        
        
        
        //prixLabel1
        Label prixLabel4 = new Label();
        prixLabel4.setFont(prixLabel1.getFont());
        prixLabel4.setTextFill(prixLabel1.getTextFill());
        prixLabel4.setLayoutX(prixLabel1.getLayoutX());
        prixLabel4.setLayoutY(prixLabel1.getLayoutY());
        prixLabel4.setText(prixLabel1.getText());
        //tempDepartLabel
        /*Label tempDepartLabel2 = new Label();
        tempDepartLabel2.setFont(tempDepartLabel.getFont());
        tempDepartLabel2.setTextFill(tempDepartLabel.getTextFill());
        tempDepartLabel2.setLayoutX(tempDepartLabel.getLayoutX());
        tempDepartLabel2.setLayoutY(tempDepartLabel.getLayoutY());
        tempDepartLabel2.setText("Temp Depart: "+annonce.getTripDate().toString());*/
        
        //userImageView
        ImageView userImageView2 = new ImageView();
        
        
         
        
        userImageView2.setLayoutX(userImageView.getLayoutX());
        userImageView2.setLayoutY(userImageView.getLayoutY());
        userImageView2.setStyle(userImageView.getStyle());
        userImageView2.setFitWidth(userImageView.getFitWidth());
        userImageView2.setFitHeight(userImageView.getFitHeight());
        
        //drivername
        Label driverNameLabel2 = new Label();
        driverNameLabel2.setFont(driverNameLabel.getFont());
        driverNameLabel2.setStyle(driverNameLabel.getStyle());
        driverNameLabel2.setTextFill(driverNameLabel.getTextFill());
        driverNameLabel2.setLayoutX(driverNameLabel.getLayoutX());
        driverNameLabel2.setLayoutY(driverNameLabel.getLayoutY());
        driverNameLabel2.setText("Sortie à :"+annonce.getDestination());
        //driverexperiance
        Label experiancLabel1 = new Label();
        experiancLabel1.setFont(experianceLabel.getFont());
        
        experiancLabel1.setTextFill(experianceLabel.getTextFill());
        experiancLabel1.setLayoutX(experianceLabel.getLayoutX());
        experiancLabel1.setLayoutY(experianceLabel.getLayoutY());
       experiancLabel1.setText("Sortie en : "+annonce.getMoyenTransport());

//experiancLabel1.setText("experience todo");
        
        
        //tempDepartLabel1
      /*  Label placesLabel2 = new Label();
        placesLabel2.setFont(placesLabel.getFont());
        placesLabel2.setTextFill(placesLabel.getTextFill());
        placesLabel2.setLayoutX(placesLabel.getLayoutX());
        placesLabel2.setLayoutY(placesLabel.getLayoutY());
        placesLabel2.setText(""+annonce.getNbrPersonne());*/
       Label placesLabel2 = new Label();
    
        //placesLabel2
         Label placesLabel3 = new Label();
            if(annonce.getNbrePlace()>0){
        placesLabel3.setFont(placesLabel1.getFont());
        placesLabel3.setTextFill(placesLabel1.getTextFill());
        placesLabel3.setLayoutX(placesLabel1.getLayoutX());
        placesLabel3.setLayoutY(placesLabel1.getLayoutY());
        placesLabel3.setText("Place disponible : "+annonce.getNbrePlace());
            }
            else{
            
             placesLabel3.setFont(placesLabel1.getFont());
        placesLabel3.setTextFill(placesLabel1.getTextFill());
        placesLabel3.setLayoutX(placesLabel1.getLayoutX());
        placesLabel3.setLayoutY(placesLabel1.getLayoutY());
        placesLabel3.setText("Complet ");
            }
        //separator
        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
        //reserverButton
       /* JFXButton reserverButton2 = new JFXButton();
        reserverButton2.setFont(reserverButton.getFont());
        reserverButton2.setTextFill(reserverButton.getTextFill());
        reserverButton2.setLayoutX(reserverButton.getLayoutX());
        reserverButton2.setLayoutY(reserverButton.getLayoutY());
        reserverButton2.setButtonType(JFXButton.ButtonType.RAISED);
        reserverButton2.setRipplerFill(reserverButton.getRipplerFill());
        reserverButton2.setText(reserverButton.getText());*/
        
      //          newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,dateDepartLabel2,prixLabel2,prixLabel4,separator1,placesLabel2,placesLabel3,userImageView2,driverNameLabel2,experiancLabel1);

       newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,dateDepartLabel2,distanceLabel2,prixLabel2,prixLabel4,separator1,placesLabel2,placesLabel3,userImageView2,driverNameLabel2,experiancLabel1);
        annoncesVBox.getChildren().add(newAnnoncesAnchorPane);
//       if (newAnnoncesAnchorPane.isPressed())
//       {
//        ActionEvent event = new ActionEvent() ;
//           Navigation.getInstance().switching("InterfaceRando.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
//       }
        newAnnoncesAnchorPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                              RecupererIdRando.setIdRando(annonce.getId());
                            Navigation.getInstance().switching("InterfaceRando.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
                        } catch (IOException ex) {
                            Logger.getLogger(AffichelisteRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
            
            
            });
//            		
//
//          RecupererIdRando.setIdRando(annonce.getId());
//           
//        
////                    try {
////                
////            passage();         
////                    } catch (IOException ex) {
////                        Logger.getLogger(AffichelisteRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
////                    }
////                   
//       ActionEvent event = new ActionEvent() ;
//                    try {
//                        Navigation.getInstance().switching("InterfaceRando.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
//                    } catch (IOException ex) {
//                        Logger.getLogger(AffichelisteRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                 
//        });
                
            }
         }
}
    }

    @FXML
    void rechercherannonceavanceeAction(ActionEvent event) {

    }
      List<String> l =new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try {
             try {
                 getallAnnoncesList();
                 
                 // TODO
             } catch (IOException ex) {
                 Logger.getLogger(AffichelisteRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
             }
                         StackPane box = FXMLLoader.load(getClass().getResource("SidePanelContentUser.fxml"));

             //AnchorPane box = FXMLLoader.load(getClass().getResource("menuRando.fxml"));
             drawer.setSidePane(box);
             HamburgerBackArrowBasicTransition burgerTask2  = new HamburgerBackArrowBasicTransition(hamburger);
             burgerTask2.setRate(-1);
             hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e)-> {
                 burgerTask2.setRate(burgerTask2.getRate()*-1);
                 burgerTask2.play();
                 if(drawer.isShown()){
                     drawer.close();
                 }
                 else {
                     drawer.open();
                 }
             });
             
         } catch (IOException ex) {
             Logger.getLogger(AffichelisteRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }    
   

    @Override
    public void start(Stage stage) throws Exception {
Parent root = FXMLLoader.load(getClass().getResource("affichelisteRandonnee.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();    }
    
     public void getallAnnoncesList() throws IOException{
            RandonneService RonnService = new RandonneService();
            annonces = RonnService.getAll();
File file=null;
            if(!annonces.isEmpty()){

                for (Randonne annonce : annonces) {

       
                AnchorPane newAnnoncesAnchorPane = new AnchorPane();
        newAnnoncesAnchorPane.setStyle(annoncesAnchorPane.getStyle());
        newAnnoncesAnchorPane.setEffect(annoncesAnchorPane.getEffect());

       
        //fromtolabel         
        Label fromToLabel2 = new Label();
        fromToLabel2.setFont(fromToLabel.getFont());
        fromToLabel2.setTextFill(fromToLabel.getTextFill());
        fromToLabel2.setLayoutX(fromToLabel.getLayoutX());
        fromToLabel2.setLayoutY(fromToLabel.getLayoutY());
        fromToLabel2.setText(annonce.getTitre());
       //datedepartlabel
        Label dateDepartLabel2 = new Label();
        dateDepartLabel2.setFont(dateDepartLabel.getFont());
        dateDepartLabel2.setTextFill(dateDepartLabel.getTextFill());
        dateDepartLabel2.setLayoutX(dateDepartLabel.getLayoutX());
        dateDepartLabel2.setLayoutY(dateDepartLabel.getLayoutY());
        dateDepartLabel2.setText("Date Depart: "+annonce.getDate());
        //distanceLabel
       /* distanceLabel2.setFont(distanceLabel.getFont());
        distanceLabel2.setTextFill(distanceLabel.getTextFill());
        distanceLabel2.setLayoutX(distanceLabel.getLayoutX());
        distanceLabel2.setLayoutY(distanceLabel.getLayoutY());
        distanceLabel2.setText(distanceLabel.getText());*/
       
       
        // PrixLabel
        Label distanceLabel2 = new Label();
        distanceLabel2.setFont(distanceLabel.getFont());
        distanceLabel2.setTextFill(distanceLabel.getTextFill());
        distanceLabel2.setLayoutX(distanceLabel.getLayoutX());
        distanceLabel2.setLayoutY(distanceLabel.getLayoutY());
        distanceLabel2.setText(distanceLabel.getText());
        distanceLabel2.setText("Prix: "+annonce.getPrix());

       
        //prixLabel
        Label prixLabel2 = new Label();
        prixLabel2.setFont(prixLabel.getFont());
        prixLabel2.setTextFill(prixLabel.getTextFill());
        prixLabel2.setLayoutX(prixLabel.getLayoutX());
        prixLabel2.setLayoutY(prixLabel.getLayoutY());
        prixLabel2.setText(""+annonce.getPrix());
        
        //prixLabel1
        Label prixLabel4 = new Label();
        prixLabel4.setFont(prixLabel1.getFont());
        prixLabel4.setTextFill(prixLabel1.getTextFill());
        prixLabel4.setLayoutX(prixLabel1.getLayoutX());
        prixLabel4.setLayoutY(prixLabel1.getLayoutY());
        prixLabel4.setText(prixLabel1.getText());
        //tempDepartLabel
        /*Label tempDepartLabel2 = new Label();
        tempDepartLabel2.setFont(tempDepartLabel.getFont());
        tempDepartLabel2.setTextFill(tempDepartLabel.getTextFill());
        tempDepartLabel2.setLayoutX(tempDepartLabel.getLayoutX());
        tempDepartLabel2.setLayoutY(tempDepartLabel.getLayoutY());
        tempDepartLabel2.setText("Temp Depart: "+annonce.getTripDate().toString());*/
         Image image = new Image("file:///C://Users//aloulou//Desktop//aladin//ERandopi ala//userFiles/"+annonce.getImagepath());
                    System.err.println("ssssssssssssssssssssssssss"+annonce.getImagepath());
        //userImageView
        ImageView userImageView2 = new ImageView();
        userImageView2.setImage(image);
        userImageView2.setImage(image);
        userImageView2.setLayoutX(userImageView.getLayoutX());
        userImageView2.setLayoutY(userImageView.getLayoutY());
        userImageView2.setStyle(userImageView.getStyle());
        userImageView2.setFitWidth(userImageView.getFitWidth());
        userImageView2.setFitHeight(userImageView.getFitHeight());
        
        //drivername
        Label driverNameLabel2 = new Label();
        driverNameLabel2.setFont(driverNameLabel.getFont());
        driverNameLabel2.setStyle(driverNameLabel.getStyle());
        driverNameLabel2.setTextFill(driverNameLabel.getTextFill());
        driverNameLabel2.setLayoutX(driverNameLabel.getLayoutX());
        driverNameLabel2.setLayoutY(driverNameLabel.getLayoutY());
        driverNameLabel2.setText("Sortie à : "+annonce.getDestination());
        //driverexperiance
        Label experiancLabel1 = new Label();
        experiancLabel1.setFont(experianceLabel.getFont());
        
        experiancLabel1.setTextFill(experianceLabel.getTextFill());
        experiancLabel1.setLayoutX(experianceLabel.getLayoutX());
        experiancLabel1.setLayoutY(experianceLabel.getLayoutY());
        experiancLabel1.setText("Sortie en : "+annonce.getMoyenTransport());
        
        
        //tempDepartLabel1
      /*  Label placesLabel2 = new Label();
        placesLabel2.setFont(placesLabel.getFont());
        placesLabel2.setTextFill(placesLabel.getTextFill());
        placesLabel2.setLayoutX(placesLabel.getLayoutX());
        placesLabel2.setLayoutY(placesLabel.getLayoutY());
        placesLabel2.setText(""+annonce.getNbrPersonne());*/
       Label placesLabel2 = new Label();
    
        //placesLabel2
         Label placesLabel3 = new Label();
            if(annonce.getNbrePlace()>0){
        placesLabel3.setFont(placesLabel1.getFont());
        placesLabel3.setTextFill(placesLabel1.getTextFill());
        placesLabel3.setLayoutX(placesLabel1.getLayoutX());
        placesLabel3.setLayoutY(placesLabel1.getLayoutY());
        placesLabel3.setText("Place disponible : "+annonce.getNbrePlace());
            }
            else{
            
             placesLabel3.setFont(placesLabel1.getFont());
        placesLabel3.setTextFill(placesLabel1.getTextFill());
        placesLabel3.setLayoutX(placesLabel1.getLayoutX());
        placesLabel3.setLayoutY(placesLabel1.getLayoutY());
        placesLabel3.setText("Complet ");
            }
        //separator
        Separator separator1 = new Separator();
        separator1.setLayoutX(separator.getLayoutX());
        separator1.setLayoutY(separator.getLayoutY());
        separator1.setPrefSize(separator.getPrefWidth(), separator.getPrefHeight());
        separator1.setOrientation(Orientation.VERTICAL);
        //reserverButton
       /* JFXButton reserverButton2 = new JFXButton();
        reserverButton2.setFont(reserverButton.getFont());
        reserverButton2.setTextFill(reserverButton.getTextFill());
        reserverButton2.setLayoutX(reserverButton.getLayoutX());
        reserverButton2.setLayoutY(reserverButton.getLayoutY());
        reserverButton2.setButtonType(JFXButton.ButtonType.RAISED);
        reserverButton2.setRipplerFill(reserverButton.getRipplerFill());
        reserverButton2.setText(reserverButton.getText());*/
        
                //newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,dateDepartLabel2,prixLabel2,prixLabel4,separator1,placesLabel2,placesLabel3,userImageView2,driverNameLabel2,experiancLabel1);

        newAnnoncesAnchorPane.getChildren().addAll(fromToLabel2,dateDepartLabel2,distanceLabel2,prixLabel2,prixLabel4,separator1,placesLabel2,placesLabel3,userImageView2,driverNameLabel2,experiancLabel1);
        annoncesVBox.getChildren().add(newAnnoncesAnchorPane);
//       if (newAnnoncesAnchorPane.isPressed())
//       {
//        ActionEvent event = new ActionEvent() ;
//           Navigation.getInstance().switching("InterfaceRando.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
//       }
        newAnnoncesAnchorPane.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                              RecupererIdRando.setIdRando(annonce.getId());
                            Navigation.getInstance().switching("InterfaceRando.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
                        } catch (IOException ex) {
                            Logger.getLogger(AffichelisteRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
            
            
            });
//            		
//
//          RecupererIdRando.setIdRando(annonce.getId());
//           
//        
////                    try {
////                
////            passage();         
////                    } catch (IOException ex) {
////                        Logger.getLogger(AffichelisteRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
////                    }
////                   
//       ActionEvent event = new ActionEvent() ;
//                    try {
//                        Navigation.getInstance().switching("InterfaceRando.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());
//                    } catch (IOException ex) {
//                        Logger.getLogger(AffichelisteRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                 
//        });
                
            }
        }
    }
     
     public void passage() throws IOException
     {
          ActionEvent event = new ActionEvent() ;
                          Parent creerGroupe;
                        creerGroupe = FXMLLoader.load(getClass().getResource("InterfaceRando.fxml"));
                          Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       
        stage.setScene(sceneAffichage);
        stage.show();
     }
     public static void main(String[] args) {
        launch(args);
    }
    
}
