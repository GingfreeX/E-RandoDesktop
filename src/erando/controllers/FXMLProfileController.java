
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import erando.chat.client.ChatController;
import erando.chat.client.Listener;
import static erando.chat.client.LoginController.con;
import erando.models.Publication;
import erando.models.User;
import erando.services.impl.PublicationServices;
import erando.services.impl.UserServices;
import erando.services.interfaces.IService;
import erando.services.interfaces.IUserService;
import erando.techniques.Navigation;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cimope
 */
public class FXMLProfileController implements Initializable {

    private Scene scene;
    @FXML
    private Label label;

    @FXML
    private Label idl;
    @FXML
    public ImageView profilepic;

    public static ChatController con;

    @FXML
    private ScrollPane maincontainer;
    String imageSource;

    @FXML
    void UpdateParametersAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/erando/gui/FXMLUpatadeProfile.fxml"));
        maincontainer.setContent(pane);

    }

    @FXML
    void LoadHomeScene(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/erando/gui/FXMLHome.fxml"));
        maincontainer.setContent(pane);
    }

    @FXML
    void DisplayPersonnaInformations(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/erando/gui/FXMLInformationPersonnelles.fxml"));
        maincontainer.setContent(pane);
    }

    @FXML
    void postarticleAction(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/erando/gui/FXMLAjouterPublication.fxml"));
        maincontainer.setContent(pane);

    }

    @FXML
    void ViewAllArticles(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/erando/gui/FXMLListArticles.fxml"));
        maincontainer.setContent(pane);

    }

    @FXML
    void LogoutAction(ActionEvent event) throws IOException {
        User.setIdofuserAlreadyloggedin(0);
        Navigation.getInstance().switching("FXMLAuthentification.fxml", (Stage) ((Node) event.getSource()).getScene().getWindow());

    }

    @FXML
    void loadchat(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("/erando/gui/FXMLchat.fxml"));
        maincontainer.setContent(pane);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
       LoadData();
        loadpublication();

    }

    public void LoadData() {
        File file = null;
        int id = User.getIdofuserAlreadyloggedin();
        IUserService userservice = new UserServices();
        User user = userservice.getUserbyId(id);
        String prenom = null;
        if (user.getPrenom() == null) {
            prenom = "";
        } else {
            prenom = user.getPrenom();
        }

        if (user.getImagePath() == null) {

            imageSource = "http://localhost/webservicepi/images/anonymous.jpg";

        } else {
            imageSource = "http://localhost/webservicepi/images/" + user.getImagePath();

        }
        profilepic = ImageViewBuilder.create()
                .image(new Image(imageSource))
                .build();
//        Image image = new Image(file.toURI().toString());
//
//        profilepic.setImage(image);

        idl.setText("" + user.getNom() + "-" + prenom);

    }

    public void loadpublication() {
        IService publicationservices = new PublicationServices();
        List<Publication> listdespublication = publicationservices.getAll();
        Label lblusername;
        Label lblusername1;
        Label lbldescription;
        Label lbldatepub;
        ImageView imagev;
        ImageView profilepic;

        VBox vb = new VBox();
        for (Publication pub : listdespublication) {
            lblusername = new Label("Publier Par :  " + pub.getUser().getNom());
            lbldescription = new Label(pub.getDescription());

            String imageSource = "http://localhost/webservicepi/images/" + pub.getUser().getImagePath();
            profilepic = ImageViewBuilder.create()
                    .image(new Image(imageSource))
                    .build();

            profilepic.setFitHeight(50);
            profilepic.setFitWidth(50);
            lbldatepub = new Label("le : " + pub.getDatepub() + "");
            if (pub.getImagepath() == null) {

                lbldescription.translateXProperty().setValue(120);
                lbldescription.translateYProperty().setValue(20);
                lbldescription.setStyle("-fx-font-size:20px;");

                lblusername.translateXProperty().setValue(60);
                lblusername.translateYProperty().setValue(5);

                lbldatepub.translateXProperty().set(60);
                lbldatepub.translateYProperty().setValue(7);

                profilepic.translateYProperty().setValue(-67);

                vb.getChildren().addAll(lblusername, lbldatepub, lbldescription, profilepic, new Separator(Orientation.HORIZONTAL));
            } else {

                String imageS = "http://localhost/webservicepi/images/" + pub.getImagepath();

                imagev = ImageViewBuilder.create()
                        .image(new Image(imageS))
                        .build();

                imagev.setFitWidth(450);
                imagev.setFitHeight(200);
                imagev.translateYProperty().setValue(-30);
                imagev.translateXProperty().setValue(130);

                lblusername.translateXProperty().setValue(62);
                lbldatepub.translateXProperty().setValue(62);
                lbldescription.setStyle("-fx-font-size:20px;");
                lbldescription.translateXProperty().setValue(120);
                lbldescription.translateYProperty().setValue(240);
                profilepic.translateYProperty().setValue(-265);
                Separator S = new Separator(Orientation.HORIZONTAL);

                S.prefWidth(800);

                vb.getChildren().addAll(lblusername, lbldatepub, lbldescription, imagev, profilepic, S);
            }
            ;
        }
        maincontainer.setContent(vb);
    }

}
