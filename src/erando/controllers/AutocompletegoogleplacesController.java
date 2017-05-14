/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Date.from;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;




/**
 * FXML Controller class
 *
 * @author adel
 */
public class AutocompletegoogleplacesController extends Application implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

    /**
     * Initializes the controller class.
     */
        @FXML
    private JFXTextField fromTextField;

    @FXML
    private JFXTextField toTextField;

    @FXML
    private GoogleMapView mapView;
    
      protected DirectionsService directionsService;

    protected DirectionsPane directionsPane;

 
   private GeocodingService geocodingService;
    GeocodingResult[] georesults;
    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
         List<String> l = new ArrayList();
String[] s;
         




    @FXML
    void fromOnkeyTypedEvent(KeyEvent event) {
System.out.println("testettstst");
         //GeocoderRequest request = new GeocoderRequest(from.get());
                /*    geocodingService.getGeocoding(new GeocoderRequest(from.get()), (GeocodingResult[] results, GeocoderStatus status)->{
                                     System.out.println("results "+results[0].getFormattedAddress());
                        System.out.println("results number "+results.length);
                    
                    });*/

   geocodingService.geocode(fromTextField.getText(), (GeocodingResult[] results, GeocoderStatus status) -> {
        l.clear();
        //int i;                 
  for(int i =0;i<results.length;i++){
        s=new String[results.length];
      s[i] = results[i].getFormattedAddress();
    
         l.add(results[i].getFormattedAddress());
         
  }
     
            
       for (GeocodingResult result : results) {
           
       
                              TextFields.bindAutoCompletion(fromTextField, s);

            }
       
        TextFields.bindAutoCompletion(fromTextField, t-> {
 
            return l;
 
        });
       
          

        });
    }

    @FXML
    void toOnkeyTypedEvent(KeyEvent event) {

    }



        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        mapView.addMapInializedListener(this);
    }    

    
    public void mapInitialized() {
geocodingService = new GeocodingService();
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

    
    public void directionsReceived(DirectionsResult dr, DirectionStatus ds) {

 DirectionsResult e = dr;
        try {
           // distance = e.getRoutes().get(0).getLegs().get(0).getDistance().getText();
            

            System.out.println("Distance total = " + e.getRoutes().get(0).getLegs().get(0).getDistance().getText());
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("autocompletegoogleplaces.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
            }
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
