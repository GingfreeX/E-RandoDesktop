/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import erando.models.Temoignage;
import erando.services.impl.TemoignageService;
import erando.services.interfaces.IService;
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
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
public class AfficheTemoignageController implements Initializable {

    @FXML
    private Label lbl_listT;
    @FXML
    private JFXButton btnRetourAffich;

    @FXML
    private JFXListView<Temoignage> txt_listT;

    @FXML
    private JFXTextField Textupdate;

    @FXML
    void Textupdate(ActionEvent event) {
    }

    @FXML
    private Button selection;

    @FXML
    void selection(ActionEvent event) {

        Temoignage selectedEvent = AvisT.getSelectionModel().getSelectedItem();

        Textupdate.setText(String.valueOf(selectedEvent.getMessage()));
        txtid.setText(String.valueOf(selectedEvent.getId()));

    }

    @FXML
    private JFXTextField txtid;

    @FXML
    private Button update;

    @FXML
    void update(ActionEvent event) throws IOException {

        TemoignageService tem = new TemoignageService();

        Temoignage Temoignage = new Temoignage();

        Temoignage.setId(Integer.parseInt(txtid.getText()));
        Temoignage.setMessage(Textupdate.getText());

        tem.update(Temoignage);

        Parent parent3 = FXMLLoader.load(getClass().getResource("/erando/gui/AfficheTemoignage.fxml"));
        Scene scene3 = new Scene(parent3);
        Stage stage3 = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage3.setScene(scene3);
        stage3.show();
    }

    @FXML
    private JFXButton supprimer;

    @FXML
    void supprimer(ActionEvent event) throws IOException {
        TemoignageService service = new TemoignageService();
        System.out.println(AvisT.getSelectionModel().selectedItemProperty().getValue().getId());
        service.delete(AvisT.getSelectionModel().getSelectedItem().getId());
        changescene("/erando/gui/AfficheTemoignage.fxml", event);
        Notifications notificationBuilder = Notifications.create()
                .title("Ok")
                .text("Votre Avis a été supprimé")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();
    }
    @FXML
    private TableView<Temoignage> AvisT;

    @FXML
    private TableColumn<Temoignage, String> Avis;

    @FXML
    void AvisT(ActionEvent event) {

    }
    List lst = new ArrayList<>();

    TemoignageService tm = new TemoignageService();

    void setcelltable() {

        Avis.setCellValueFactory(new PropertyValueFactory<>("message"));
        lst = tm.getAll();
        ObservableList<Temoignage> ob = FXCollections.observableArrayList(lst);
        //AvisT.setRowFactory(setStyle("-fx-background-color: tomato;"));

        AvisT.setItems(ob);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        /* lst = tm.getAllTem();
                ObservableList<Temoignage> ob = FXCollections.observableArrayList(lst);
                System.out.println(ob);

            txt_listT.setItems(ob);*/
        setcelltable();
        // TODO
    }

    @FXML
    void btnRetour(ActionEvent event) throws IOException {
        changescene("/erando/gui/MenuTemoignage.fxml", event);

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
