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
import erando.controllers.Randonne;
import erando.services.impl.RandonneService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Duration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author amrouche
 */
public class ListeRandonneeController extends Application implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private TableView<Randonne> tbRando;

    @FXML
    private TableColumn<?, ?> cltitre;

    @FXML
    private TableColumn<?, ?> cldestination;

    @FXML
    private TableColumn<?, ?> cldate;

    @FXML
    private TableColumn<?, ?> clnbrdeplaces;

      public static List<Randonne> groupes;
    private final ObservableList<Randonne> ListGroupes = FXCollections.observableArrayList();
    RandonneService rando = new RandonneService();
    int id ;
    String titre ;
    String description ;
    String destination ;
    double prix ;
    String image ;
    int nbrplace ;
    String depart ;
    int ageminimale ;
    String transport;
    String plan ;
    Date date ;
    String type ;
    int niveau ;
    @FXML
    private JFXTextField txtitre;

    @FXML
    private JFXTextField txdescription;

    @FXML
    private JFXTextField txdestination;

    @FXML
    private JFXTextField txprix;

    @FXML
    private JFXTextField tximage;

    @FXML
    private JFXTextField txplaces;

    @FXML
    private JFXTextField txdepart;

    @FXML
    private JFXTextField txageminimale;

    @FXML
    private JFXTextField txtransport;

    @FXML
    private JFXTextField txplan;

    @FXML
    private JFXButton btnmodifier;

    @FXML
    private JFXButton btnsupprimer;

    @FXML
    private JFXDatePicker ddate;

    @FXML
    private JFXComboBox<String> cbtype;

    @FXML
    private JFXComboBox<Integer> cbniveau;

    @FXML
    private JFXTextField txid;
    @FXML
    void modifierAction(ActionEvent event) throws IOException {
titre=txtitre.getText();
description=txdescription.getText();
destination=txdestination.getText();
prix=Double.parseDouble(txprix.getText());
image=tximage.getText();
nbrplace=Integer.parseInt(txplaces.getText());
depart=txdepart.getText();
ageminimale=Integer.parseInt(txageminimale.getText());
transport=txtransport.getText();
plan=txplan.getText();
date=Date.valueOf(ddate.getValue());
type=cbtype.getSelectionModel().getSelectedItem();
id=Integer.parseInt(txid.getText());
niveau =cbniveau.getSelectionModel().getSelectedItem();
Randonne rando=new Randonne(id,titre, destination, date, prix, ageminimale, description, 1, transport, type, plan, destination, nbrplace, depart, niveau);
  RandonneService serv1=new RandonneService();
  serv1.update(rando);
 /************************************/
  Notifications notificationBuilder = Notifications.create()
                .title("sucess")
                .text("randonne modifier")
                 .graphic(null)
                
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    System.out.println("Demande refusée");
                    
                }     
        });
          notificationBuilder.darkStyle();
       notificationBuilder.showConfirm();
       /************************************/
  Parent creerGroupe = FXMLLoader.load(getClass().getResource("listeRandonnee.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(sceneAffichage);
        stage.show();
  
    }

    @FXML
    void supprimerAction(ActionEvent event) throws IOException {
        id =Integer.parseInt(txid.getText());
        RandonneService rando1= new RandonneService();
        rando1.delete(id);
         Parent creerGroupe = FXMLLoader.load(getClass().getResource("listeRandonnee.fxml"));
        Scene sceneAffichage = new Scene(creerGroupe);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        stage.setScene(sceneAffichage);
        stage.show();

    }
    void setcelltable(){
    
     cltitre.setCellValueFactory(new PropertyValueFactory<>("Titre"));
      cldestination.setCellValueFactory(new PropertyValueFactory<>("Destination"));
       cldate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        clnbrdeplaces.setCellValueFactory(new PropertyValueFactory<>("NbrePlace"));
      groupes = rando.getAll();
        for (Randonne gr : groupes) {
            ListGroupes.add(gr);
                  

            // System.out.println(gr.getId_demande());
            // System.out.println(gr.getUser().getId());
        }
     tbRando.setItems(ListGroupes);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
        cbtype.setItems(FXCollections.observableArrayList("camping","caving"));
     cbniveau.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9));
        setcelltable();
        setCellValueFromTableToText();
        txid.setVisible(false);
         
        
       
             

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
 private void setCellValueFromTableToText() {
        tbRando.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                Randonne gr = tbRando.getItems().get(tbRando.getSelectionModel().getSelectedIndex());
              txtitre.setText(gr.getTitre());
              txdescription.setText(gr.getDescription());
              txdestination.setText(gr.getDestination());
              txdepart.setText(gr.getPointDepart());
              txplaces.setText(Integer.toString(gr.getNbrePlace()));
              txplan.setText(gr.getPlan());
              txtransport.setText(gr.getMoyenTransport());
              txageminimale.setText(Integer.toString(gr.getAgeMin()));
              txprix.setText(Double.toString(gr.getPrix()));
                
            ddate.setValue(gr.getDate().toLocalDate());
            
                cbniveau.getSelectionModel().select(gr.getNiveau());
                cbtype.getSelectionModel().select(gr.getType());
                txid.setText(Integer.toString(gr.getId()));
            }
            
        });
        
    }
      @Override
    public void start(Stage stage) throws Exception {
 Parent root = FXMLLoader.load(getClass().getResource("listeRandonnee.fxml"));
        
        Scene scene = new Scene(root);
               // scene.getStylesheets().add(getClass().getResource("../images/theme.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
            }
    
    
     public static void main(String[] args) {
        launch(args);
    }
    
}
