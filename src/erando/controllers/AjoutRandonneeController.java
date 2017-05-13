/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import erando.models.Randonne;
import erando.services.impl.RandonneService;
import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author aloulou
 */
public class AjoutRandonneeController extends Application implements MapComponentInitializedListener, DirectionsServiceCallback, Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private GoogleMapView mapView;
    @FXML
    private JFXHamburger hamburger;
        @FXML
    private JFXTextField txdescription;

    @FXML
    private JFXTextField txdestination;

    @FXML
    private JFXTextField txtitre;

    @FXML
    private JFXTextField txprix;

    @FXML
    private JFXTextField tximage;

    @FXML
    private JFXTextField txnbrplaces;

    @FXML
    private JFXTextField txdepart;

    @FXML
    private JFXTextField txageminim;

    @FXML
    private JFXTextField txmoyentransport;

    

    @FXML
    private JFXDatePicker ddate;
    

    @FXML
    private JFXComboBox<String> cbtype;

    @FXML
    private JFXComboBox<Integer> cbniveau;

    @FXML
    private JFXButton btnajouter;
    
     @FXML
    private JFXButton newPhotoButton;
    @FXML
    private JFXTextField imagepath;
     @FXML
    private ImageView pImage;
     @FXML
    private JFXDrawer drawer;
     
    private static Path destinationn;
    
    private static File selectedfile;
    
    private String imageName;
    
    private GeocodingService geocodingService ;
    
    protected StringProperty to = new SimpleStringProperty();
    protected StringProperty from = new SimpleStringProperty();
    List<String> l = new ArrayList();
    String[] s;
    private MarkerOptions markerOptions;

    protected DirectionsService directionsService;

    protected DirectionsPane directionsPane;
    
 @FXML
    void destOnTyped(KeyEvent event) {

        System.out.println("erando"+txdestination.getText());
        geocodingService.geocode(to.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
        l.clear();
        //int i;                 
  for(int i =0;i<results.length;i++){
        s=new String[results.length];
      s[i] = results[i].getFormattedAddress();
    
         l.add(results[i].getFormattedAddress());
         
  }
     
            
       for (GeocodingResult result : results) {
           
       
                              TextFields.bindAutoCompletion(txdestination, s);

            }
       
        TextFields.bindAutoCompletion(txdestination, t-> {
 
            return l;
 
        });
       
          

        });
    }
     @FXML
    void depOnTyped(KeyEvent event) {
         System.out.println("erando"+txdepart.getText());
        geocodingService.geocode(from.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
        l.clear();
        //int i;                 
  for(int i =0;i<results.length;i++){
        s=new String[results.length];
      s[i] = results[i].getFormattedAddress();
    
         l.add(results[i].getFormattedAddress());
         
  }
     
            
       for (GeocodingResult result : results) {
           
       
                              TextFields.bindAutoCompletion(txdepart, s);

            }
       
        TextFields.bindAutoCompletion(txdepart, t-> {
 
            return l;
 
        });
       
          

        });

    }
    @FXML
     private void addimage(ActionEvent event) {
        Stage currentStage = (Stage) newPhotoButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File f = new File("C://Users//aloulou//Desktop");
        fileChooser.setInitialDirectory(f);
        File selectedFile = fileChooser.showOpenDialog(currentStage);
            Path movefrom = FileSystems.getDefault().getPath(selectedFile.getPath());
            imageName = selectedFile.getName();
            Path target = FileSystems.getDefault().getPath("userfiles/"+selectedFile.getName());
            Path targetDir = FileSystems.getDefault().getPath("userfiles/");
            try{
                Files.move(movefrom,target,StandardCopyOption.ATOMIC_MOVE);
            }catch (IOException e){}
        Image image = new Image("file:///C://Users//aloulou//Desktop//aladin//ERandopi ala//userFiles/"+imageName);
        pImage.setImage(image);
        
    }
    
    
   /* @FXML
    void addimage(ActionEvent event) throws IOException {

        FileChooser fc = new FileChooser();
       selectedfile = fc.showOpenDialog(null);
        
        destinationn = Paths.get("C:\\Users\\aloulou\\Desktop\\ERandopi\\src\\erando\\images\\",selectedfile.getName());
       
        imagepath.setText(selectedfile.getAbsolutePath());
  
        
    }*/
   
    final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
    {  @Override
      public DateCell call(final DatePicker datePicker) {
        return new DateCell() {
          @Override
          public void updateItem(LocalDate item, boolean empty) {
            super.updateItem(item, empty);

            if (item.isBefore(LocalDate.now().plusDays(1))) {
              setDisable(true);
              setStyle("-fx-background-color: #EEEEEE;");
            }
          }
        };
      }
    };

    @FXML
    void ajouterRandonnee(ActionEvent event) throws IOException {
String titre =txtitre.getText();
String description=txdescription.getText();
String destination = txdestination.getText();
  
    
             

        LocalDate ddatee=ddate.getValue();
        Date date = Date.valueOf(ddatee); // Magic happens here!
        int nbrplace = Integer.parseInt(txnbrplaces.getText());
        String pointdepart =txdepart.getText();
        String type=cbtype.getValue();
        int niveau =cbniveau.getValue();
        int agenmin=Integer.parseInt(txageminim.getText());
        String moyentransport=txmoyentransport.getText();
//        String plan=txplan.getText();
        double prix =Double.parseDouble(txprix.getText());
        
        Randonne rando =new Randonne(titre, destination, date, prix, agenmin, description, 1, moyentransport, type, "", "", nbrplace, pointdepart, niveau);
       
            rando.setImagepath(imageName);
      
             
        
        
        
        RandonneService serv=new RandonneService();
        serv.add(rando);
          Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("success");
                a1.setContentText("ajout avec succes");
                a1.show();
                
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ddate.setDayCellFactory(dayCellFactory);
       ddate.setValue(LocalDate.now().plusDays(1)); 
        //ddate.setValue(LocalDate.MIN.plusDays(1)); 

        // TODO
        mapView.addMapInializedListener(this);
        to.bindBidirectional(txdestination.textProperty());
                from.bindBidirectional(txdepart.textProperty());

//mapView.addMapInializedListener(this);
     cbtype.setItems(FXCollections.observableArrayList("camping","caving","hunting"));
     cbniveau.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9));
     //cbniveau.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9));

     /*AnchorPane box;
        try {
            box = FXMLLoader.load(getClass().getResource("menuRando.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(ListeRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
        }
             
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
             });}*/
             

             
                  StackPane box = null;
        try {
            box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(AjoutRandonneeController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
             
    }    

    @Override
    public void start(Stage stage) throws Exception {
 Parent root = FXMLLoader.load(getClass().getResource("ajoutRandonnee.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
            }
    
     public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void mapInitialized() {
        
            // geocodingService = new GeocodingService();
geocodingService = new GeocodingService();
        System.out.println("map init");
        MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();        
    }

    @Override
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
