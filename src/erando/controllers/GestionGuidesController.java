/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.controllers;

import com.jfoenix.controls.JFXButton;
import erandopi.models.Guide;

import erandopi.services.impl.GuideService;
import erandopi.services.impl.MembreService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author student1
 */
public class GestionGuidesController implements Initializable {

    @FXML
    private TableView<Guide> listGuides;

    @FXML
    private TableColumn<?, ?> Nom;

    @FXML
    private TableColumn<?, ?> Prenom;

    @FXML
    private TableColumn<?, ?> email;

    @FXML
    private TableColumn<?, ?> Statut;

    @FXML
    private JFXButton bannir;
   @FXML
    private JFXButton approuv;
       @FXML
    void approuvGuide(ActionEvent event) throws IOException {
GuideService service = new GuideService();
        System.out.println(listGuides.getSelectionModel().selectedItemProperty().getValue().getIdguide());
        service.ApprouvGuide(listGuides.getSelectionModel().getSelectedItem());

        changescene("/erandopi/gui/GestionGuides.fxml", event);

        TrayNotification tray = new TrayNotification("OK", "Le guide a été Approuvé", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }
    @FXML
    void bannirGuide(ActionEvent event) throws IOException {
        GuideService service = new GuideService();
        System.out.println(listGuides.getSelectionModel().selectedItemProperty().getValue().getIdguide());
        service.BannGuide(listGuides.getSelectionModel().getSelectedItem());

        changescene("/erandopi/gui/GestionGuides.fxml", event);

        TrayNotification tray = new TrayNotification("OK", "Le guide a été Banni", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }
    @FXML
    private JFXButton retour;

    @FXML
    void Retour(ActionEvent event) throws IOException {
        changescene("/erandopi/gui/AdminProfile.fxml", event);
    }
    List lst = new ArrayList<>();
    GuideService tm = new GuideService();

    void setcelltable() {
        listGuides.setRowFactory(tv -> new TableRow<Guide>() {
            @Override
            public void updateItem(Guide item, boolean empty) {
                super.updateItem(item, empty);
         
                if ("inactif".equals(listGuides.getColumns().get(3).getCellData(item))) {
                    
                setStyle("-fx-background-color: tomato;");    
                }
                else{
                setStyle("");
                }
                

            }
        });
        
        Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        Statut.setCellValueFactory(new PropertyValueFactory<>("statut"));

        lst = tm.getAll2();
        ObservableList<Guide> ob = FXCollections.observableArrayList(lst);

        listGuides.setItems(ob);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setcelltable();
    }

    void changescene(String gui, ActionEvent event) throws IOException {
        FXMLLoader fxmlloder = new FXMLLoader(getClass().getResource(gui));

        Parent root1 = fxmlloder.load();
        Scene home_scene = new Scene(root1);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scene);
        app_stage.show();
    }
}
