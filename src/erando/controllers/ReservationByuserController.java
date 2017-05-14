/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import erando.controllers.Randonne;
import erando.controllers.Reservation;
import erando.services.impl.RandonneService;
import erando.services.impl.ReservationService;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class ReservationByuserController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    int id ;
    String titre ;
    String depart ;
    String destination ;
   double prix ;
    int nbrpresonne;
    Date date;
     @FXML
    private TableView<Reservation> tbres;

       @FXML
    private TableColumn<Reservation,String> cldepart;

    @FXML
    private TableColumn<Reservation, String> cldestination;

    @FXML
    private TableColumn<Reservation, String> cltitre;

   

    @FXML
    private TableColumn<Reservation, String> xlprix;

    @FXML
    private JFXButton btnconfirmer;

    @FXML
    private JFXButton btnannuler;
    
    
    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private JFXHamburger hamburger;
    
   public static List<Reservation> groupes;
    private final ObservableList<Reservation> ListGroupes = FXCollections.observableArrayList();
    RandonneService rando = new RandonneService();
    ReservationService rs = new ReservationService();
    @FXML
    void annulerAction(ActionEvent event) throws IOException {
        Reservation res1 = new Reservation();
        Randonne randonne1=new Randonne();
ReservationService res = new ReservationService();
RandonneService rando1=new RandonneService();
res1=res.findById(id);
randonne1=rando1.findById(res1.getRandonne().getId());
        System.out.println(res1.getRandonne().getId());
randonne1.setNbrePlace(randonne1.getNbrePlace()+1);
rando1.update(randonne1);
        System.out.println("aaaaaaaaywah");
res.delete(id);
        System.out.println("finish him");
          Parent creerGroupe = FXMLLoader.load(getClass().getResource("/erando/gui/ReservationByuser.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(sceneAffichage);
        stage.show();
    }

    @FXML
    void confirmerAction(ActionEvent event) throws IOException, FileNotFoundException, DocumentException {
        genererpdf();
        envoyerfacture();

    }
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
		FileOutputStream fileOutputStream = new FileOutputStream("test.pdf");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(pdfTemplate, fileOutputStream);
		stamper.setFormFlattening(true);

		stamper.getAcroFields().setField("name", titre);
		stamper.getAcroFields().setField("montant", String.valueOf(prix));
		stamper.getAcroFields().setField("depart",
				depart);
		stamper.getAcroFields().setField("arrivee", destination);
                stamper.getAcroFields().setField("date",String.valueOf(date));
		stamper.close();
		pdfTemplate.close();
    
    }
 void setcelltable(){
    
             cldepart.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getRandonne().getPointDepart()));
             
   cldestination.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getRandonne().getDestination()));
      cltitre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getRandonne().getTitre()));
        // clplaces.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Integer.toString(cellData.getValue().getRandonne().getNbrePlace())));
        // clplaces.setCellValueFactory(1);
         xlprix.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Double.toString(cellData.getValue().getRandonne().getPrix())));
//     cltitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
//      cldestination.setCellValueFactory(new PropertyValueFactory<>("Destination"));
//       cldate.setCellValueFactory(new PropertyValueFactory<>("Date"));
//        clnbrdeplaces.setCellValueFactory(new PropertyValueFactory<>("NbrePlace"));
      groupes = rs.findreservationbyuser(2);
            //groupes = rs.findreservationbyuser(3);

      for (Reservation gr : groupes) {
            ListGroupes.add(gr);
                  

            // System.out.println(gr.getId_demande());
            // System.out.println(gr.getUser().getId());
        }
     tbres.setItems(ListGroupes);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setcelltable();
        setCellValueFromTableToText();
        btnconfirmer.setVisible(false);
        
        try {
          
                         StackPane box = FXMLLoader.load(getClass().getResource("/erando/gui/SidePanelContentUser.fxml"));

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
        
        // TODO
    }    
 private void setCellValueFromTableToText() {
        tbres.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Reservation gr = tbres.getItems().get(tbres.getSelectionModel().getSelectedIndex());
             id=gr.getId();
             titre=gr.getUser().getNom();
             depart=gr.getRandonne().getPointDepart();
             destination=gr.getRandonne().getDestination();
             prix=gr.getRandonne().getPrix();
             nbrpresonne=gr.getRandonne().getNbrePlace();
             date=gr.getRandonne().getDate();
            }
            
        });
 }
        
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("/erando/gui/ReservationByuser.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("../images/theme.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
            }
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
