/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import erando.models.Membre;
import erando.models.Temoignage;
import erando.services.impl.MembreService;
import erando.services.impl.TemoignageService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author student1
 */
public class GestionMembresController implements Initializable {

    @FXML
    private Button btnsuppM;
    @FXML
    private TableView<Membre> tableMembres;

    @FXML
    private TableColumn<?, ?> username;

    @FXML
    private TableColumn<?, ?> username_canonical;

    @FXML
    private TableColumn<?, ?> description;
        @FXML
    private TableColumn<?, ?> id;
    @FXML
    private JFXButton retour;

    @FXML
    private Label labl;
    
    List lst = new ArrayList<>();
    MembreService tm = new MembreService();

    void setcelltable() {
        
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        username_canonical.setCellValueFactory(new PropertyValueFactory<>("pays"));
        description.setCellValueFactory(new PropertyValueFactory<>("email"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        lst = tm.getAll();
        ObservableList<Membre> ob = FXCollections.observableArrayList(lst);

        tableMembres.setItems(ob);
    }

    @FXML
    void adminsuppM(ActionEvent event) throws IOException {

        MembreService service = new MembreService();
        System.out.println(tableMembres.getSelectionModel().selectedItemProperty().getValue().getId());
        service.delete(tableMembres.getSelectionModel().getSelectedItem().getId());
        
        
         changescene("/erando/gui/GestionMembres.fxml", event);
  
                Notifications notificationBuilder = Notifications.create()
                .title("OK")
                .text("Le Membre a été supprimé")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();
    }
    @FXML
    private JFXButton Exporter;

    @FXML
    void Exporter(ActionEvent event) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setcelltable();
    }
    @FXML
    void retourAction(ActionEvent event) throws IOException {
        changescene("/erando/gui/AdminProfile.fxml", event);
    }
    void changescene(String gui, ActionEvent event) throws IOException {
        FXMLLoader fxmlloder = new FXMLLoader(getClass().getResource(gui));

        Parent root1 = fxmlloder.load();
        Scene home_scene = new Scene(root1);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_scene);
        app_stage.show();
    }

}
