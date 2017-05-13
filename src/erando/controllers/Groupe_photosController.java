/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import erando.models.Groupe;
import erando.models.ImageG;
import erando.models.Membre;
import erando.services.impl.GroupeService;
import erando.services.impl.ImageService;
import erando.services.impl.MembreService;
import erando.services.impl.PublicationService;
import erando.services.interfaces.IMembreService;
import erando.techniques.Navigation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.textfield.TextFields;
import erando.services.interfaces.IPublicationGroupService;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class Groupe_photosController implements Initializable {

    @FXML
    private TextField txtsearch;

    @FXML
    private Button addmember;

    @FXML
    private Label lblmsgmember;

    @FXML
    private Label membresnbr;

    @FXML
    private Label groupedesc;

    @FXML
    private Button changecover;

    @FXML
    private ImageView coverpic;

    @FXML
    private HBox membresphoto;

    @FXML
    private ScrollPane imagepane;

    private ImageView selectedimg;

    IPublicationGroupService ps = new PublicationService();
    IMembreService ms = new MembreService();
    ImageService im = new ImageService();

    GroupeService gs = new GroupeService();
    Groupe g = gs.getGroupe(2);
    Membre m = ms.getUser(2);
    //ImageG i = im.getImage(2);
    ImageView imgv;
    Image imagedownold;
    Stage secondStage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadMemberNumber();
        loadMemberPhoto();
        loadGroupeDescription();
        loadCoverPhoto();
        AutocompleteAddMember();
        changeCoverPhoto();
        loadImage();

       

    }

    @FXML
    void gotoMembres(ActionEvent event) {
        new Navigation().switchScene("groupe_membres.fxml", event);

    }

    @FXML
    void gotoPublication(ActionEvent event) {

        new Navigation().switchScene("groupe_home.fxml", event);

    }

    @FXML
    void createGroupe(ActionEvent event) {

        new Groupe_homeController().createGroupe(event);

    }

    public void loadMemberNumber() {

        int nbm = gs.getGroupeMemberNumber(g);
        membresnbr.setText(nbm + " " + membresnbr.getText());

    }
    
    public void loadImage(){
            List<ImageG> ls = im.getAllGroupImage(new ImageG(g));

         FlowPane fl = new FlowPane();
        selectedimg = new ImageView();

        for (int i = 0; i < ls.size(); i++) {
            File f = new File("C:\\Users\\wassim\\Documents\\NetBeansProjects\\ERandoPi\\src\\erando\\images\\" + ls.get(i).getName());
            Image image = new Image(f.toURI().toString());
            imgv = new ImageView();
            imgv.setImage(image);
            imgv.setFitHeight(120);
            imgv.setFitWidth(120);
            imgv.setCursor(Cursor.HAND);
            imgv.setId(String.valueOf(ls.get(i).getId()));
            imgv.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    StackPane secondaryLayout = new StackPane();

                    Scene secondScene = new Scene(secondaryLayout, 990, 650);
                    secondStage = new Stage();

                    ImageG selectimg = im.getImage(Integer.parseInt(((ImageView) event.getSource()).getId()));
                    File fselected = new File("C:\\Users\\wassim\\Documents\\NetBeansProjects\\ERandoPi\\src\\erando\\images\\" + selectimg.getName());

                    imagedownold = new Image(fselected.toURI().toString());
                    selectedimg.setImage(imagedownold);
                    selectedimg.setFitWidth(secondScene.getWidth());
                    selectedimg.setFitHeight(secondScene.getHeight());
                    Hyperlink download_btn = new Hyperlink();
                    download_btn.setText("Telecharger");
                    download_btn.translateXProperty().setValue(430);
                    download_btn.translateYProperty().setValue(300);
                    download_btn.getStyleClass().add("btndownload");
                    download_btn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            FileChooser fileChooser = new FileChooser();
                            fileChooser.setTitle("Save Image");
                            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                            //Show save file dialog
                            File file = fileChooser.showSaveDialog(secondStage);

                            if (file != null) {
                                try {
                                    ImageIO.write(SwingFXUtils.fromFXImage(selectedimg.getImage(),
                                            null), "png", file);

                                } catch (IOException ex) {
                                    Logger.getLogger(Groupe_photosController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }

                    });

                    secondaryLayout.getChildren().addAll(selectedimg, download_btn);
                    secondScene.getStylesheets().add("erando/utils/groupe_home.css");

                    secondStage.setTitle(selectimg.getName());
                    secondStage.setScene(secondScene);
                    secondStage.show();

                }

            });

            fl.setPrefWidth(imagepane.getPrefWidth());
            fl.setPrefHeight(imagepane.getPrefHeight());
            fl.getChildren().add(imgv);
            fl.setOrientation(Orientation.HORIZONTAL);
            fl.setMargin(imgv, new Insets(10, 8, 4, 8));

        }

        imagepane.setContent(fl);
    }

    public void loadMemberPhoto() {

        List<String> lspic = gs.getGroupeMemberPhoto(g);

        for (int j = 0; j < lspic.size(); j++) {
            File f = new File("C:\\Users\\wassim\\Documents\\NetBeansProjects\\ERandoPi\\src\\erando\\images\\" + lspic.get(j));
            Image im1 = new Image(f.toURI().toString());
            ImageView imv = new ImageView();

            imv.setImage(im1);
            imv.setFitHeight(60.0);
            imv.setFitWidth(60.0);
            membresphoto.getChildren().add(imv);
            membresphoto.setMargin(imv, new Insets(0, 0, 0, 8));

        }
    }

    public void loadGroupeDescription() {

        groupedesc.setText(g.getDescription());

    }

    public void loadCoverPhoto() {
        File file = new File("C:\\Users\\wassim\\Documents\\NetBeansProjects\\ERandoPi\\src\\erando\\images\\" + g.getPhoto_couverture());
        Image image = new Image(file.toURI().toString());
        coverpic.setImage(image);

    }

    public void AutocompleteAddMember() {

        Map<Integer, String> s = ms.getUsernamesWithId(m.getListamis());
        List<String> ls = ms.getUsernameOnly(s);

        TextFields.bindAutoCompletion(txtsearch, ls);

        addmember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = ms.getUsernameId(s, txtsearch.getText());
                if (!"".equals(txtsearch.getText())) {
                    if (id != 0) {
                        if (!gs.existemembre(g, id)) {
                            gs.addmembre(g, id);
                            lblmsgmember.setText("Membre ajouté avec succés");

                            lblmsgmember.translateXProperty().setValue(30);
                            txtsearch.setText("");
                            new Navigation().switchScene("groupe_photos.fxml", event);

                        } else {
                            lblmsgmember.setText("Membre existe deja");
                            lblmsgmember.translateXProperty().setValue(40);

                        }
                    } else {
                        lblmsgmember.setText("Membre introuvable");
                        lblmsgmember.translateXProperty().setValue(40);

                    }

                } else {

                    lblmsgmember.setText("Champ vide");
                    lblmsgmember.translateXProperty().setValue(40);

                }

            }
        });

    }

    public void changeCoverPhoto() {
        changecover.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                File file = fileChooser.showOpenDialog(null);
                Image image = new Image(file.toURI().toString());
                coverpic.setImage(image);
                g.setPhoto_couverture(file.getName());
                gs.update(g);

            }
        });

    }

}
