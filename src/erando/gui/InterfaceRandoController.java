/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSnackbar;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.directions.DirectionStatus;
import com.lynden.gmapsfx.service.directions.DirectionsRenderer;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.directions.DirectionsResult;
import com.lynden.gmapsfx.service.directions.DirectionsService;
import com.lynden.gmapsfx.service.directions.DirectionsServiceCallback;
import com.lynden.gmapsfx.service.directions.TravelModes;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import erando.models.Randonne;
import erando.models.Reservation;
import erando.models.User;
import erando.services.impl.RandonneService;
import erando.services.impl.ReservationService;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author amrouche
 */
public class InterfaceRandoController extends Application implements Initializable, MapComponentInitializedListener, DirectionsServiceCallback {

     private GoogleMap map;

    private GeocodingService geocodingService;

    //ScreensController screen;

    private MarkerOptions markerOptions;

    protected DirectionsService directionsService;

    protected DirectionsPane directionsPane;

    protected StringProperty from = new SimpleStringProperty();
    protected StringProperty to = new SimpleStringProperty();
    @FXML
    private StackPane root;

    @FXML
    private Pane annoncePane;

    @FXML
    private GoogleMapView mapView;

    @FXML
    private JFXRippler rippler1;

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
    private ImageView citere1Label;

    @FXML
    private ImageView citere2Label;

    @FXML
    private ImageView citere3Label;

    @FXML
    private ImageView citere4Label;

    @FXML
    private JFXRippler favoriteToggle;

    @FXML
    private JFXSnackbar snackbar;

    @FXML
    private Label tempRest;

    @FXML
    private Label tempRest1;

    @FXML
    private Label voitureLabel;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXPopup popup;

    @FXML
    private JFXListView<?> popupList;

    @FXML
    private JFXDialog dialog;

    @FXML
    private JFXButton acceptButton;
    
      @FXML
    private TextArea DescriptionArea;

    @FXML
    private JFXButton closetButton;
        @FXML
    private JFXButton btnreturn;
 private String distance;

    Randonne annonces = new Randonne();
    RandonneService annService = new RandonneService();
      public static AnchorPane rootP;

    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
        DirectionsResult e = results;
        try {
            distance = e.getRoutes().get(0).getLegs().get(0).getDistance().getText();
            distanceLabel.setText("Distance: " + distance);

            System.out.println("Distance total = " + e.getRoutes().get(0).getLegs().get(0).getDistance().getText());
        } catch (Exception ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
    }
    @FXML
    void returnAction(ActionEvent event) throws IOException {
                            Navigation.getInstance().switching("affichelisteRandonnee.fxml", (Stage)((Node)event.getSource()).getScene().getWindow());

    }
    @FXML
    void demandereserverAction(ActionEvent event) throws IOException, FileNotFoundException, DocumentException {
        
        ReservationService serv = new ReservationService();
        RandonneService rand = new RandonneService();
        Randonne r1 = rand.findById(RecupererIdRando.getIdRando());
        r1.setNbrePlace(r1.getNbrePlace()-1);
        rand.update(r1);
        System.out.println("test    :  "+r1.getNbrePlace());
        User us= new User(2);

       // User us= new User(3);
        Reservation res = new Reservation(new RandonneService().findById(RecupererIdRando.getIdRando()),us );
        serv.add(res);
        System.out.println("hello");
        genererpdf();
        envoyerfacture();
          Parent creerGroupe = FXMLLoader.load(getClass().getResource("InterfaceRando.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(sceneAffichage);
        stage.show();

    }
    /**
     * Initializes the controller class.
     */
     void envoyerfacture(){
      String host="smtp.gmail.com";  
  final String user="amrouchefcb8@gmail.com";//change accordingly  
  final String password="amine11374284";//change accordingly  
    
  String to="aladin.nefzi@esprit.tn ";//change accordingly  
  //amrouchefcb8@gmail.com
   //Get the session object  
   Properties props = new Properties();  
   props.put("mail.smtp.host",host);  
   props.put("mail.smtp.auth", "true");  
     props.put("mail.smtp.starttls.enable", "true");
     props.put("mail.smtp.ssl.trust", "*"); 
   Session session = Session.getDefaultInstance(props,  
    new javax.mail.Authenticator() {  
      protected PasswordAuthentication getPasswordAuthentication() {  
    return new PasswordAuthentication(user,password);  
      }  
    });  
  
   //Compose the message  
    try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("facture de reservation");  
     message.setText("bonjour vous trouvez si joint la facture de votre reservation ");  
         MimeBodyPart mbp2 = new MimeBodyPart();
     FileDataSource fichier_joint = new FileDataSource("C:\\Users\\aloulou\\Desktop\\ERandopi\\test.pdf");
      mbp2.setDataHandler(new DataHandler(fichier_joint));
      mbp2.setFileName(fichier_joint.getName());
 
      // Créer un conteneur multipart pour les deux contenus
      Multipart mp = new MimeMultipart();
      
      mp.addBodyPart(mbp2);
 
      // Ajouter le Multipart au message
      message.setContent(mp);
    //send the message
  // message.setTLS(true);
     Transport.send(message);  
  
     System.out.println("message sent successfully...");  
   
     } catch (MessagingException e) {e.printStackTrace();} 
    }
      void genererpdf() throws IOException,
			FileNotFoundException, DocumentException
    {
    
    PdfReader pdfTemplate = new PdfReader("templatefacture.pdf");
    RandonneService rando = new RandonneService();
    Randonne r = new Randonne();
    r=rando.findById(RecupererIdRando.getIdRando());
		FileOutputStream fileOutputStream = new FileOutputStream("test.pdf");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);
		stamper.setFormFlattening(true);

		stamper.getAcroFields().setField("name", r.getTitre());
		stamper.getAcroFields().setField("montant", String.valueOf(r.getPrix()));
		stamper.getAcroFields().setField("depart",
				r.getPointDepart());
		stamper.getAcroFields().setField("arrivee", r.getDestination());
                stamper.getAcroFields().setField("date",String.valueOf(r.getDate()));
		stamper.close();
		pdfTemplate.close();
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(RecupererIdRando.getIdRando());
         mapView.addMapInializedListener((MapComponentInitializedListener) this);
        popupList.depthProperty().set(1);
        popup.setPopupContainer(root);

        setAnnonceDetails();
       
        // TODO
    }    
  public void setAnnonceDetails() {
        annonces = annService.findById(RecupererIdRando.getIdRando()); //4 //ScreensFramework.annonceId
        fromToLabel.setText(annonces.getPointDepart()+ "->" + annonces.getDestination());
        /*  if (!distance.isEmpty()){
        distanceLabel.setText("Distance: "+distance);
        }
        else*/
        //distanceLabel.setText("Distance: --");

        prixLabel.setText((int) annonces.getPrix() + "Dt");
        dateDepartLabel.setText("Date Depart: " + annonces.getDate());
if(annonces.getNbrePlace()==0){placesLabel.setText("complet");
reserverButton.setVisible(false);}
else
{    placesLabel.setText("" + annonces.getNbrePlace());}
        tempRest1.setText(annonces.getDescription());
        tempRest.setText("Description : ");
       DescriptionArea.setText(""+annonces.getDescription());
        
        //driverNameLabel.setText("Les tickets sont disponible sur"+annonces.getDescription());
        experianceLabel.setText("niveu de difficulté : "+annonces.getNiveau());
        voitureLabel.setText("Type : "+annonces.getType());

     /*   Image image = new Image("http://localhost/covoituragetn/" + user.getPhoto_Profil());
        userImageView.setImage(image);
String[] parts = annonces.getCritere().split(";");
/*citere1Label.setImage(new Image("http://localhost/covoituragetn/"+parts[0]));
citere2Label.setImage(new Image("http://localhost/covoituragetn/"+parts[1]));
citere3Label.setImage(new Image("http://localhost/covoituragetn/"+parts[2]));
citere4Label.setImage(new Image("http://localhost/covoituragetn/"+parts[3]));*/
        snackbar.registerSnackbarContainer(root);

    
    }
    @Override
    public void mapInitialized() {
  MapOptions options = new MapOptions();

        options.center(new LatLong(34.3055732, 11.255412))
                .zoomControl(true)
                .zoom(6)
                .overviewMapControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);
        GoogleMap map = mapView.createMap(options);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
        DirectionsRequest request = new DirectionsRequest(annonces.getPointDepart(), annonces.getDestination(), TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
        // JSObject obj ;

        /* map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            //System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            lblClick.setText(ll.toString());
            
        });*/
 /* annoncePane.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            //System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
            //lblClick.setText(ll.toString());
            
        });*/
        LatLong ll = null;

        System.out.println("longlag ");
    }

    @Override
    public void start(Stage stage) throws Exception {
     Parent root = FXMLLoader.load(getClass().getResource("InterfaceRando.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
            }
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
