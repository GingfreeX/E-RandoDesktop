/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import erando.models.Publication;
import erando.services.impl.PublicationServices;
import erando.services.interfaces.IService;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author cimope
 */
public class FXMLHomeController implements Initializable {

    Label lblusername;
    Label lblusername1;
    Label lbldescription;
    Label lbldatepub;
    ImageView imagev;
    ImageView profilepic;

    VBox vb = new VBox();

    @FXML
    private AnchorPane maincontent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadpublication();
    }

    public void loadpublication() {
        IService publicationservices = new PublicationServices();
        List<Publication> listdespublication = publicationservices.getAll();

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
                File fimgpost = new File(pub.getImagepath());
                Image imgpost = new Image(fimgpost.toURI().toString());
                imagev = new ImageView();
                imagev.setImage(imgpost);
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
        maincontent.getChildren().addAll(vb);
    }

}
