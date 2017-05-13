/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import erando.models.Commentaire;
import erando.models.Groupe;
import erando.models.ImageG;
import erando.models.Membre;
import erando.models.Publication;
import erando.services.impl.CommentaireService;
import erando.services.impl.GroupeService;
import erando.services.impl.ImageService;
import erando.services.impl.MembreService;
import erando.services.impl.PublicationService;
import erando.services.interfaces.ICommentaireService;
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
import javafx.event.Event;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javafx.stage.FileChooser;
import javafx.util.Callback;

import javafx.util.Duration;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;
import org.w3c.dom.ls.LSParser;
import erando.services.interfaces.IPublicationGroupService;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class Groupe_homeController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private TextArea txtpublication;

    @FXML
    private TextField txtsearch;
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

    @FXML
    private Button btnupload;
    @FXML
    private AnchorPane anchormain;
    TextField txtcomment;
    Label lblusername, lbldescription, lbldatepub, lblnbLike;
    Image image_user, im_delete, im_edit, im_upload;
    ImageView imagev, deleteicon, updateicon, uploadicon, postimg, imgUserlogged;
    Button btndelete, btnupdate, btnjaime, btncomment, btnpostcomment;
    ListView<Commentaire> lscomment;
    Publication p;
    IPublicationGroupService ps = new PublicationService();
    IMembreService ms = new MembreService();
    GroupeService gs = new GroupeService();
    ImageService im = new ImageService();
    ICommentaireService cs = new CommentaireService();
    Commentaire comment;
    Groupe g = gs.getGroupe(2);
    Membre m = ms.getUser(2);
    int nbclick = 0;
    int idcreateur = gs.getIdCreateur(g);

    List<Commentaire> lscmt = new ArrayList<>();

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
            Image i = new Image("/erando/images/rror.png");
            Notifications notif = Notifications.create()
                    .title("Publication vide")
                    .graphic(new ImageView(i))
                    .text("Ecrivez quelque chose svp")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_CENTER);

            notif.show();
        } else {
            lblerreur.setText("");
            p = new Publication(txtpublication.getText(), m, g, "");
            ps.add(p);

            Image i = new Image("/erando/images/tick.png");
            Notifications notif = Notifications.create()
                    .title("Succées")
                    .graphic(new ImageView(i))
                    .text("Publication publiée avec succées")
                    .hideAfter(Duration.seconds(5))
                    .darkStyle()
                    .position(Pos.TOP_CENTER);

            new Navigation().switchScene("groupe_home.fxml", event);
            notif.show();

            txtpublication.setText("");

        }

    }

    @FXML
    public void gotoMembres(ActionEvent e) {

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
        Label err = new Label("Champ requis");

        dialog.getDialogPane().getButtonTypes().addAll(btncreer);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 20, 10, 10));

        TextField groupenom = new TextField();
        groupenom.setPrefWidth(180);
        groupenom.setPromptText("Nom du groupe");
        TextField groupedes = new TextField();
        groupedes.setPromptText("Description du groupe");

        grid.add(new Label("Nom du groupe:"), 0, 0);
        grid.add(groupenom, 1, 0);
        grid.add(new Label("Description:"), 0, 1);
        grid.add(groupedes, 1, 1);

        // Enable/Disable login button depending on whether a username was entered.
        dialog.getDialogPane().setContent(grid);

        Optional<Groupe> result = dialog.showAndWait();

        if (result.isPresent()) {

            if ("".equals(groupenom.getText()) || "".equals(groupedes.getText())) {
                Alert alert1 = new Alert(AlertType.ERROR);
                alert1.setTitle("Erreur");
                alert1.setHeaderText(null);
                alert1.setContentText("Vous devez remplir tous les champs svp");

                alert1.showAndWait();
            } else {
                Groupe grp = new Groupe(groupenom.getText(), groupedes.getText(), new Membre(7));
                gs.add(grp);
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Information");
                alert1.setHeaderText(null);
                alert1.setContentText("Votre Groupe est crée avec success");

                alert1.showAndWait();
            }
        }

    }

    @FXML
    void uploadimg(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        Image i = new Image("/erando/images/tick.png");

        File file = fileChooser.showOpenDialog(null);

        p = new Publication(txtpublication.getText(), m, g, file.getName());
        ps.add(p);
        Notifications notif = Notifications.create()
                .title("Succées")
                .graphic(new ImageView(i))
                .text("Publication publiée avec succées")
                .hideAfter(Duration.seconds(5))
                .darkStyle()
                .position(Pos.TOP_CENTER);
        new Navigation().switchScene("groupe_home.fxml", event);

        notif.show();

    }

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
            Text txtdes = new Text(lp.get(i).getDescription());
            txtdes.getStyleClass().add("txtdes");

            lbldatepub = new Label((String.valueOf(lp.get(i).getDate_publication())));

            lblusername.translateXProperty().setValue(60);
            lblusername.translateYProperty().setValue(-50);

            lbldatepub.translateXProperty().set(60);
            lbldatepub.translateYProperty().setValue(-40);
            btnjaime = new Button("J'aime");
            btnjaime.getStyleClass().add("likebtn");
            btnjaime.setId(String.valueOf(lp.get(i).getId()));
            Publication pub = lp.get(i);

            btnjaime.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ps.updateNbrLike(pub);
                }
            });

            btncomment = new Button("Commenter");
            btncomment.getStyleClass().add("commentbtn");

            lblnbLike = new Label(String.valueOf(pub.getNbrjaime()) + " J'aime");
            lblnbLike.getStyleClass().add("lblnblike");
            txtcomment = new TextField();
            txtcomment.setPromptText("Votre commentaire ...");
            txtcomment.getStyleClass().add("txtcomment");
            txtcomment.setId(String.valueOf(pub.getId()));

            btnpostcomment = new Button("Publier");
            btnpostcomment.getStyleClass().add("btnpostcomment");
            btnpostcomment.setId(String.valueOf(pub.getId()));

            btnpostcomment.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (Node node : vbox.getChildren()) {
                        if (node instanceof TextField) {
                            if (node.getId().equals(((Control) event.getSource()).getId())) {
                                comment = new Commentaire(((TextField) node).getText(), m, pub);
                                cs.add(comment);
                                ((TextField) node).setText("");
                                new Navigation().switchScene("groupe_home.fxml", event);

                            }
                        }
                    }
                }
            });

            lscmt = cs.getAllCommentByPub(lp.get(i).getId());
            ObservableList<Commentaire> ob = FXCollections.observableArrayList(lscmt);
            lscomment = new ListView<>();
            Pane pane = new Pane();
            pane.getChildren().add(lscomment);
            lscomment.setPrefHeight(90);
            lscomment.setPrefWidth(450);

            imgUserlogged = imageUserlogged();
            imgUserlogged.getStyleClass().add("imgUserlogged");

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
            btndelete.setPrefSize(20, 20);
            // si id de createur est = id de user connecte 
            if (lp.get(i).getCreateur().getId() != m.getId()) {
                btndelete.setVisible(false);
            }
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
            btnupdate.setPrefSize(20, 20);

            if (lp.get(i).getCreateur().getId() != m.getId()) {
                btnupdate.setVisible(false);
            }
            btnupdate.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    TextInputDialog dialog = new TextInputDialog(ps.getPubById(Integer.parseInt(((Control) event.getSource()).getId())).getDescription());
                    dialog.setTitle("Modifier publication");
                    dialog.setHeaderText(null);
                    dialog.setContentText(null);
                    dialog.getDialogPane().setMinWidth(500);
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        Publication p = ps.getPubById(Integer.parseInt(((Control) event.getSource()).getId()));
                        p.setDescription(result.get());
                        ps.update(p);
                        Alert info = new Alert(AlertType.INFORMATION);
                        info.setTitle("Information");
                        info.setHeaderText(null);
                        info.setContentText("Votre publication est modifiée avec success");

                        info.showAndWait();
                        new Navigation().switchScene("groupe_home.fxml", event);

                    }

                }
            });

            if ("".equals(txtdes.getText()) && !"".equals(lp.get(i).getPhoto())) {
                File fimgpost = new File("C:\\Users\\wassim\\Documents\\NetBeansProjects\\ERandoPi\\src\\erando\\images\\" + lp.get(i).getPhoto());
                Image imgpost = new Image(fimgpost.toURI().toString());
                postimg = new ImageView();
                postimg.setImage(imgpost);
                postimg.setFitWidth(650);
                postimg.setFitHeight(450);
                postimg.translateYProperty().setValue(-10);
                lscomment.setCellFactory(new Callback<ListView<Commentaire>, ListCell<Commentaire>>() {
                    @Override
                    public ListCell<Commentaire> call(ListView<Commentaire> param) {
                        ListCell<Commentaire> cell = new ListCell<Commentaire>() {

                            @Override
                            protected void updateItem(Commentaire cm, boolean bool) {
                                super.updateItem(cm, bool);
                                HBox hb = customlistComment(cm);
                                setGraphic(hb);

                            }
                        };
                        return cell;
                    }
                });

                lscomment.setItems(ob);

                if (lscomment.getItems().isEmpty()) {
                    lscomment.setPlaceholder(new Label("Aucun commentaires"));

                }

                vbox.getChildren().addAll(imagev, lblusername, lbldatepub, postimg, btnjaime, btncomment, lblnbLike, txtcomment, btnpostcomment, pane, imgUserlogged, btndelete, btnupdate, new Separator(Orientation.HORIZONTAL));

            } else if (!"".equals(txtdes.getText()) && "".equals(lp.get(i).getPhoto())) {
               
                lscomment.setCellFactory(new Callback<ListView<Commentaire>, ListCell<Commentaire>>() {
                    @Override
                    public ListCell<Commentaire> call(ListView<Commentaire> param) {
                        ListCell<Commentaire> cell = new ListCell<Commentaire>() {

                            @Override
                            protected void updateItem(Commentaire cm, boolean bool) {
                                super.updateItem(cm, bool);
                                HBox hb = customlistComment(cm);
                                setGraphic(hb);

                            }
                        };
                        return cell;
                    }
                });

                lscomment.setItems(ob);

                if (lscomment.getItems().isEmpty()) {
                    lscomment.setPlaceholder(new Label("Aucun commentaires"));

                }

                vbox.getChildren().addAll(imagev, lblusername, lbldatepub, txtdes, btnjaime, btncomment, lblnbLike, txtcomment, btnpostcomment, pane, imgUserlogged, btndelete, btnupdate, new Separator(Orientation.HORIZONTAL));

            } else {
                File fimgpost = new File("C:\\Users\\wassim\\Documents\\NetBeansProjects\\ERandoPi\\src\\erando\\images\\" + lp.get(i).getPhoto());
                Image imgpost = new Image(fimgpost.toURI().toString());
                postimg = new ImageView();
                postimg.setImage(imgpost);
                postimg.setFitWidth(650);
                postimg.setFitHeight(450);

                postimg.translateYProperty().setValue(0);
                lscomment.setCellFactory(new Callback<ListView<Commentaire>, ListCell<Commentaire>>() {
                    @Override
                    public ListCell<Commentaire> call(ListView<Commentaire> param) {
                        ListCell<Commentaire> cell = new ListCell<Commentaire>() {

                            @Override
                            protected void updateItem(Commentaire cm, boolean bool) {
                                super.updateItem(cm, bool);
                                HBox hb = customlistComment(cm);
                                setGraphic(hb);

                            }
                        };
                        return cell;
                    }
                });

                lscomment.setItems(ob);

                if (lscomment.getItems().isEmpty()) {
                    lscomment.setPlaceholder(new Label("Aucun commentaires"));

                }

                vbox.getChildren().addAll(imagev, lblusername, lbldatepub, txtdes, postimg, btnjaime, btncomment, lblnbLike, txtcomment, btnpostcomment, pane, imgUserlogged, btndelete, btnupdate, new Separator(Orientation.HORIZONTAL));

            }
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

    public ImageView imageUserlogged() {

        File f = new File("C:\\Users\\wassim\\Documents\\NetBeansProjects\\ERandoPi\\src\\erando\\images\\" + m.getProfil_pic());
        Image image_user = new Image(f.toURI().toString());
        imagev = new ImageView();
        imagev.setImage(image_user);
        imagev.setFitHeight(35);
        imagev.setFitWidth(35);
        return imagev;
    }

    public HBox customlistComment(Commentaire cm) {
        HBox hbox = new HBox();

        if (cm != null) {
            Image im = new Image("erando/images/" + cm.getUser().getProfil_pic());
            ImageView imv = new ImageView(im);
            imv.setFitHeight(30.0);
            imv.setFitWidth(30.0);

            Button btnsup = new Button();
            btnsup.getStyleClass().add("deletecomment");
            btnsup.setVisible(false);
            btnsup.setId((String.valueOf(cm.getId())));

            if (m.getId() == cm.getUser().getId()) {
                btnsup.setVisible(true);

            }
            btnsup.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    cs.delete(Integer.parseInt(((Control) event.getSource()).getId()));
                }
            });
            Label username = new Label();
            username.setText(cm.getUser().getUsername());
            username.getStyleClass().add("usernamestyle");
            Label text = new Label();
            text.getStyleClass().add("commenttext");
            text.setText(cm.getCommenttxt());

            hbox.getChildren().addAll(imv);
            hbox.getChildren().addAll(username);
            hbox.getChildren().addAll(text);
            hbox.getChildren().addAll(btnsup);
            // hbox.setMargin(imv, new Insets(0, 5, 0, 0));

        }
        return hbox;
    }

}
