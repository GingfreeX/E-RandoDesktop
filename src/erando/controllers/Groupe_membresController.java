/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import erando.models.Groupe;
import erando.models.Membre;
import erando.services.impl.GroupeService;
import erando.services.impl.MembreService;
import erando.services.impl.PublicationService;
import erando.services.interfaces.IMembreService;
import erando.techniques.Navigation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import org.controlsfx.control.textfield.TextFields;
import erando.services.interfaces.IPublicationGroupService;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class Groupe_membresController implements Initializable, MapComponentInitializedListener {

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
    private ListView<Membre> listmembre;

    @FXML
    private Pane infopanel;

    @FXML
    private Label lblnom;

    @FXML
    private Label lblprenom;

    @FXML
    private Label lblage;

    @FXML
    private Label lblpays;

    @FXML
    private Label lblmail;

    @FXML
    private ImageView userimgdetail;

    @FXML
    private GoogleMapView GMap;

    @FXML
    private Pane gmappane;

    private GoogleMap gm;

    public GeocodingService geocodingService;
        @FXML
    private AnchorPane anchormain;

    IPublicationGroupService ps = new PublicationService();
    IMembreService ms = new MembreService();
    GroupeService gs = new GroupeService();
    Groupe g = gs.getGroupe(2);
    Membre m = ms.getUser(2);
    int idcreateur = gs.getIdCreateur(g);

    List<Membre> lsm = new ArrayList<>();
    String pays = "";
    LatLong latLong = null;

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
        loadmemberlist();
        showMemberDeatail();

    }

    @FXML
    void gotoPublication(ActionEvent event) throws IOException {


        new Navigation().switchScene("/erando/gui/groupe_home.fxml", event);

    }

    @FXML
    void gotoPhotos(ActionEvent event) {
        new Navigation().switchScene("/erando/gui/groupe_photos.fxml", event);
    }

    @FXML
    void createGroupe(ActionEvent event) {

        new Groupe_homeController().createGroupe(event);

    }

    public void loadMemberNumber() {

        int nbm = gs.getGroupeMemberNumber(g);
        membresnbr.setText(nbm + " " + membresnbr.getText());

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
                            new Navigation().switchScene("/erando/gui/groupe_membres.fxml", event);

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

    public void loadmemberlist() {

        lsm = gs.getGroupMembers(g);
        ObservableList<Membre> ob = FXCollections.observableArrayList(lsm);

        listmembre.setCellFactory(new Callback<ListView<Membre>, ListCell<Membre>>() {
            @Override
            public ListCell<Membre> call(ListView<Membre> param) {
                ListCell<Membre> cell = new ListCell<Membre>() {

                    @Override
                    protected void updateItem(Membre me, boolean bool) {
                        super.updateItem(me, bool);
                        if (me != null) {
                            Image im = new Image("erando/images/" + me.getProfil_pic());
                            ImageView imv = new ImageView(im);
                            imv.setFitHeight(70.0);
                            imv.setFitWidth(80.0);
                            imv.setLayoutX(-10.0);

                            Button btnsup = new Button("Supprimer");
                            btnsup.setVisible(false);
                            btnsup.setId((String.valueOf(me.getId())));
                            btnsup.translateXProperty().setValue(580.0);
                            btnsup.translateYProperty().setValue(15.0);
                            btnsup.getStyleClass().add("btnsup");
                            // si user connecte est admin
                            if (me.getId() != idcreateur) {
                                btnsup.setVisible(true);
                            }
                            // si user connecte n'est pas admin
                            if (m.getId() != idcreateur) {
                                btnsup.setVisible(false);
                            }
                            
                          

                            listmembre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Membre>() {

                                @Override
                                public void changed(ObservableValue<? extends Membre> observable, Membre oldValue, Membre newValue) {
                                    btnsup.translateXProperty().setValue(300.0);
                                    infopanel.setVisible(true);
                                    gmappane.setVisible(true);
                                }
                            });

                            btnsup.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {
                                    ButtonType oui = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                                    ButtonType non = new ButtonType("Non", ButtonBar.ButtonData.NO);
                                    Alert alert = new Alert(Alert.AlertType.WARNING,
                                            "Voulez vous vraiment supprimer ce membre ?", oui, non);
                                    alert.setTitle("Attention");
                                    alert.setHeaderText(null);
                                    Optional<ButtonType> result = alert.showAndWait();

                                    if (result.isPresent() && result.get() == oui) {
                                        gs.deletemembre(g, Integer.parseInt(((Control) event.getSource()).getId()));
                                        Alert info = new Alert(Alert.AlertType.INFORMATION);
                                        info.setTitle("Information");
                                        info.setHeaderText(null);
                                        info.setContentText("Ce Membre est supprimée avec success");

                                        info.showAndWait();
                                        new Navigation().switchScene("/erando/gui/groupe_membres.fxml", event);

                                    }

                                }
                            });

                            Label username = new Label();
                            username.setText(me.getUsername());
                            username.setFont(Font.font("family", 18));
                            username.translateYProperty().setValue(15.0);

                            HBox hbox = new HBox();
                            hbox.getChildren().addAll(btnsup);
                            hbox.getChildren().addAll(imv);
                            hbox.getChildren().addAll(username);
                            hbox.setMargin(imv, new Insets(0, 5, 0, -80));

                            setGraphic(hbox);
                        }
                    }

                };

                return cell;
            }

        });

        listmembre.setItems(ob);
    }

    public void showMemberDeatail() {
        listmembre.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Membre>() {

            @Override
            public void changed(ObservableValue<? extends Membre> observable, Membre oldValue, Membre newValue) {
                listmembre.setPrefWidth(390);
                lblnom.setText(listmembre.getSelectionModel().selectedItemProperty().getValue().getNom());
                lblprenom.setText(listmembre.getSelectionModel().selectedItemProperty().getValue().getPrenom());
                lblage.setText(String.valueOf(listmembre.getSelectionModel().selectedItemProperty().getValue().getAge()));
                lblmail.setText(listmembre.getSelectionModel().selectedItemProperty().getValue().getEmail());
                lblpays.setText(listmembre.getSelectionModel().selectedItemProperty().getValue().getPays());
                userimgdetail.setImage(new Image("erando/images/" + listmembre.getSelectionModel().selectedItemProperty().getValue().getProfil_pic()));
                pays = listmembre.getSelectionModel().selectedItemProperty().getValue().getPays();
                if (pays != "") {

                    geocodingService = new GeocodingService();

                    geocodingService.geocode(pays, (GeocodingResult[] results, GeocoderStatus status) -> {

                        if (status == GeocoderStatus.ZERO_RESULTS) {
                            Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                            alert.show();
                            return;
                        } else if (results.length > 1) {
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                            alert.show();
                            latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                        } else {
                            latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                        }

                        mapInitialized();
                    });
                } else {
                    pays = listmembre.getSelectionModel().selectedItemProperty().getValue().getPays();

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

    @Override
    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(latLong)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(10);

        gm = GMap.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(latLong);
        Marker m = new Marker(markerOptions1);

        gm.addMarker(m);
    }

}
