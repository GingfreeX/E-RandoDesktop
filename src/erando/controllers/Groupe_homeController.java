/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import erando.models.Groupe;
import erando.models.ImageG;
import erando.models.Membre;
import erando.models.Publication;
import erando.services.impl.GroupeService;
import erando.services.impl.ImageService;
import erando.services.impl.MembreService;
import erando.services.impl.PublicationService;
import erando.services.interfaces.IMembreService;
import erando.services.interfaces.IPublicationService;
import erando.techniques.Navigation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class Groupe_homeController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML

    private TextField txtpublication, txtsearch;
    @FXML

    private Label lblerreur, lblmsgmember, membresnbr, groupedesc;
    @FXML

    private ScrollPane scrollpane;

    @FXML

    private Button addmember, changecover, btnsmile, btn1smile, btn2kissheadrt, btn3horror, btn4cry, btn5disappoint, btn6laugh;
    
    @FXML
    private Button btn7kiss, btn8tears, btn9sad, btn10shy, btn11crytears, btn12smilesimple, btn13astonlaugh, btn14surprise, btn15calme, btn16laughtears, btn17think, btn18tongue, btn19confused, btn20angry;

    
    @FXML
    private ImageView coverpic;

    @FXML

    private HBox membresphoto;

    @FXML
    private AnchorPane listsmiley;


    Label lblusername, lbldescription, lbldatepub;
    Image image_user, im_delete, im_edit, im_upload;
    ImageView imagev, deleteicon, updateicon, uploadicon, postimg;
    Button btndelete, btnupdate, btnupload;

    Publication p;
    ImageG i;
    IPublicationService ps = new PublicationService();
    IMembreService ms = new MembreService();
    GroupeService gs = new GroupeService();
    ImageService im = new ImageService();

    Groupe g = gs.getGroupe(2);
    Membre m = ms.getUser(7);
    int nbclick = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadpublication();
        loadMemberNumber();
        loadMemberPhoto();
        loadGroupeDescription();
        loadCoverPhoto();
        AutocompleteAddMember();
        changeCoverPhoto();
    }

    @FXML
    void addpublication(ActionEvent event) {

        if (txtpublication.getText().equals("")) {

            lblerreur.setText("Votre publication est vide. Ecrivez quelque chose ");

        } else {
            lblerreur.setText("");
            p = new Publication(txtpublication.getText(), m, g);
            ps.add(p);

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Votre publication est publiée avec success");

            alert.showAndWait();
            new Navigation().switchScene("groupe_home.fxml", event);

            txtpublication.setText("");

        }

    }

    @FXML
    public void gotoMembres(ActionEvent e) throws IOException {

        new Navigation().switchScene("groupe_membres.fxml", e);
    }

    @FXML
    void gotoPhotos(ActionEvent event) {
        new Navigation().switchScene("groupe_photos.fxml", event);
    }

    @FXML
    void createGroupe(ActionEvent event) {
        Dialog<Groupe> dialog = new Dialog<>();
        dialog.setTitle("CREER UN GROUPE");
        dialog.setHeaderText("CREEZ VOTRE GROUPE ET PARTAGEZ VOS PASSIONS");
        ButtonType btncreer = new ButtonType("CREER", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(btncreer, ButtonType.CLOSE);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField groupenom = new TextField();
        groupenom.setPromptText("Nom du groupe");
        TextField groupedes = new TextField();
        groupedes.setPromptText("Description du groupe");

        grid.add(new Label("Nom du groupe:"), 0, 0);
        grid.add(groupenom, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(groupedes, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        Node createButton = dialog.getDialogPane().lookupButton(btncreer);
        createButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        groupenom.textProperty().addListener((observable, oldValue, newValue) -> {
            createButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> groupenom.requestFocus());

        Optional<Groupe> result = dialog.showAndWait();

        if (result.isPresent()) {

            if (!"".equals(groupenom.getText()) && !"".equals(groupedes.getText())) {
                Groupe grp = new Groupe(groupenom.getText(), groupedes.getText(), new Membre(7));
                gs.add(grp);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Votre Groupe est crée avec success");

                alert.showAndWait();

            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("CREATION ECHOUE");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez remplir tous les champs");

                alert.showAndWait();
            }

        }

    }

   /* @FXML
    void uploadimg(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        Image image = new Image(file.toURI().toString());
        postimg.setImage(image);

        // i = new ImageG(file.getName(),g,m);
        //im.add(i);

        /* Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Votre publication est publiée avec success");

            alert.showAndWait();
            new Navigation().switchScene("groupe_home.fxml", event);
         */
        //g.setPhoto_couverture(file.getName());
        //gs.update(g);
   // }

        @FXML
    void showsmiley(MouseEvent event) {
     nbclick++;
        if (nbclick == 1) {
            listsmiley.setVisible(true);

        }

        if (nbclick == 2) {
            listsmiley.setVisible(false);
            nbclick = 0;

        }

    }

    public void loadpublication() {

        List<Publication> lp = ps.getPubOrderByDate(g);
        for (int i = 0; i < lp.size(); i++) {
            // lp.get(i).getCreateur().getId() retourne id dde user
            // getuser retroune un objet user
            lblusername = new Label(ms.getUser(lp.get(i).getCreateur().getId()).getUsername());
            lbldescription = new Label(lp.get(i).getDescription());

            lbldescription.translateYProperty().setValue(-10);
            lbldescription.setStyle("-fx-font-size:18px;");

            lbldatepub = new Label((String.valueOf(lp.get(i).getDate_publication())));

            lblusername.translateXProperty().setValue(60);
            lblusername.translateYProperty().setValue(-50);

            lbldatepub.translateXProperty().set(60);
            lbldatepub.translateYProperty().setValue(-40);

            File f = new File("C:\\Users\\wassim\\Documents\\NetBeansProjects\\ERandoPi\\src\\erando\\images\\" + ms.getUser(lp.get(i).getCreateur().getId()).getProfil_pic());
            Image image_user = new Image(f.toURI().toString());
            imagev = new ImageView();
            imagev.setImage(image_user);
            imagev.setFitHeight(50);
            imagev.setFitWidth(50);

            btndelete = new Button();
            // set icondelete into button 
            im_delete = new Image("erando/images/delete.png");
            deleteicon = new ImageView();
            deleteicon.setFitWidth(20);
            deleteicon.setFitHeight(20);
            deleteicon.setImage(im_delete);

            btndelete.setId(String.valueOf(lp.get(i).getId()));
            btndelete.setGraphic(deleteicon);
            btndelete.setStyle("-fx-background-color:transparent");
            btndelete.setCursor(Cursor.HAND);

            btndelete.translateXProperty().setValue(640);
            btndelete.translateYProperty().setValue(-95);
            btndelete.setPrefSize(20, 20);
            btndelete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ButtonType oui = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                    ButtonType non = new ButtonType("Non", ButtonBar.ButtonData.NO);
                    Alert alert = new Alert(AlertType.WARNING,
                            "Voulez vous supprimer cette publication ?", oui, non);
                    alert.setTitle("Attention");
                    alert.setHeaderText(null);
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.isPresent() && result.get() == oui) {
                        ps.delete(Integer.parseInt(((Control) event.getSource()).getId()));
                        Alert info = new Alert(AlertType.INFORMATION);
                        info.setTitle("Information");
                        info.setHeaderText(null);
                        info.setContentText("Votre publication est supprimée avec success");

                        info.showAndWait();
                        new Navigation().switchScene("groupe_home.fxml", event);

                    }
                }
            });

            btnupdate = new Button();
            // set iconedit into button 
            im_edit = new Image("erando/images/edit.png");
            updateicon = new ImageView();
            updateicon.setFitWidth(20);
            updateicon.setFitHeight(20);
            updateicon.setImage(im_edit);
            btnupdate.setId(String.valueOf(lp.get(i).getId()));
            btnupdate.setGraphic(updateicon);
            btnupdate.setStyle("-fx-background-color:transparent");
            btnupdate.setCursor(Cursor.HAND);

            btnupdate.translateXProperty().setValue(640);
            btnupdate.translateYProperty().setValue(-10);
            btnupdate.setPrefSize(20, 20);
            btnupdate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    TextInputDialog dialog = new TextInputDialog(ps.getPubById(Integer.parseInt(((Control) event.getSource()).getId())).getDescription());
                    dialog.setTitle("Modifier publication");
                    dialog.setHeaderText(null);
                    dialog.setContentText(null);

                    // Traditional way to get the response value.
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        Publication p = ps.getPubById(Integer.parseInt(((Control) event.getSource()).getId()));
                        p.setDescription(result.get());
                        ps.update(p);
                        Alert info = new Alert(AlertType.INFORMATION);
                        info.setWidth(200.5);
                        info.setTitle("Information");
                        info.setHeaderText(null);
                        info.setContentText("Votre publication est modifiée avec success");
                        info.showAndWait();
                        new Navigation().switchScene("groupe_home.fxml", event);

                    }

                }
            });
            vbox.getChildren().addAll(imagev, lblusername, lbldatepub, lbldescription, btndelete, btnupdate, new Separator(Orientation.HORIZONTAL));
            scrollpane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scrollpane.setContent(vbox);

        }
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
        File file = new File("C:\\Users\\wassim\\Desktop\\" + g.getPhoto_couverture());
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
                            new Navigation().switchScene("groupe_home.fxml", event);

                            lblmsgmember.translateXProperty().setValue(30);
                            txtsearch.setText("");
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

    @FXML
    void btn1smileaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :D");

    }

    @FXML
    void btnangryaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " 3:)");

    }

    @FXML
    void btnastonlaughaction(ActionEvent event) {

        txtpublication.setText(txtpublication.getText() + " :')");

    }

    @FXML
    void btncalmeaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " B|");

    }

    @FXML
    void btnconfusedaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " o.O");

    }

    @FXML
    void btncryaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :'(");

    }

    @FXML
    void btncrytearsaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :'(");

    }

    @FXML
    void btndisappointaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :|");

    }

    @FXML
    void btnhorroraction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " 8(");

    }

    @FXML
    void btnkissaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :*");

    }

    @FXML
    void btnkissheartaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :<3");

    }

    @FXML
    void btnlaughaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :D");

    }

    @FXML
    void btnlaughtearsaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :'D");

    }

    @FXML
    void btnsadaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :@");

    }

    @FXML
    void btnshyaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :$");

    }

    @FXML
    void btnsmilesimpleaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :)");

    }

    @FXML
    void btnsurpriseaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :S");

    }

    @FXML
    void btntearsaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " 8'(");

    }

    @FXML
    void btnthinkaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " 8#");

    }

    @FXML
    void btntongueaction(ActionEvent event) {
        txtpublication.setText(txtpublication.getText() + " :p");

    }

}
