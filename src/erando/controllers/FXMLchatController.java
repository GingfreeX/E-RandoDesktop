/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import erando.models.User;
import erando.models.messages;
import erando.services.impl.UserServices;
import erando.services.impl.messagesServices;
import erando.services.interfaces.IUserService;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author cimope
 */
public class FXMLchatController implements Initializable {

    @FXML
    private JFXListView list;
    private static int idreciver;

    @FXML
    private TextArea messagezone;

    @FXML
    private JFXTextField chattextzone;

    @FXML
    private Label idreciv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadFreinds();
    }

    @FXML
    void send(ActionEvent event) {
        messagesServices messageservices = new messagesServices();
        messages mes = new messages(new UserServices().getUserbyId(User.getIdofuserAlreadyloggedin()),
                new UserServices().getUserbyId(FXMLchatController.idreciver),
                new UserServices().getUserbyId(User.getIdofuserAlreadyloggedin()).getNom() + " : " + chattextzone.getText());
        messageservices.add(mes);
        messagezone.appendText(new UserServices().getUserbyId(User.getIdofuserAlreadyloggedin()).getNom() + " : " + chattextzone.getText() + "\n");
    }

    public void loadFreinds() {
        IUserService userservices = new UserServices();
        User user = userservices.getUserbyId(User.getIdofuserAlreadyloggedin());
        String[] parts = user.getListAmis().split("/");
        List<User> listamis = new ArrayList<User>();

        int[] tabs = new int[parts.length];;
        int count = 0; // Declare variables when they are needed not at the top of methods as there is no reason to allocate memory before it is ready to be used
        for (count = 0; count < parts.length; count++) {
            if (!parts[count].isEmpty()) {
                tabs[count] = Integer.parseInt(parts[count]);
            }
        }
        for (int i = 1; i < tabs.length; i++) {
            System.out.println(tabs[i]);
            listamis.add(new UserServices().getUserbyId(tabs[i]));
        }
        File file = null;

        ImageView imagev1 = null;
        Button b = null;
        List<messages> listmsg = null;

        ObservableList<User> ListUser = FXCollections.observableArrayList(listamis);

        for (User u : ListUser) {
            HBox H = new HBox();
            VBox v = new VBox();
            Label lbl = new Label();
            JFXButton button = new JFXButton();
            button.setText("Rejoindre");
            button.setStyle("-fx-background-color:#2196F3");
            Image image = null;
            System.out.println(u.getImagePath());
            String imageSource = "http://localhost/webservicepi/images/" + u.getImagePath();
//         
//        ImageView imageView = 
////            file = new File(u.getImagePath());
////            image = new Image(file.toURI().toString());

            imagev1 = ImageViewBuilder.create()
                    .image(new Image(imageSource))
                    .build();
            imagev1.setFitHeight(80);
            imagev1.setFitWidth(90);

            H.setPrefHeight(100);
            H.setPrefWidth(1000);

            b = new Button("contacter");
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Thread t1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true) {
                                try {
                                    Thread.sleep(5000);
                                    FXMLchatController.idreciver = u.getId();
                                    messagesServices messageservices = new messagesServices();
                                    messagezone.clear();
                                    for (messages m : messageservices.getAll().stream().filter(x -> x.getSender().getId() == User.getIdofuserAlreadyloggedin()
                                            && x.getReciver().getId() == idreciver
                                            || x.getReciver().getId() == User.getIdofuserAlreadyloggedin()
                                            && x.getSender().getId() == idreciver).collect(Collectors.toList())) {

                                        messagezone.appendText(m.getMessage() + "\n");
                                    }

                                } catch (InterruptedException ex) {

                                }
                            }
                        }
                    });
                    FXMLchatController.idreciver = u.getId();
                    messagesServices messageservices = new messagesServices();
                    messagezone.clear();
                    for (messages m : messageservices.getAll().stream().filter(x -> x.getSender().getId() == User.getIdofuserAlreadyloggedin()
                            && x.getReciver().getId() == idreciver
                            || x.getReciver().getId() == User.getIdofuserAlreadyloggedin()
                            && x.getSender().getId() == idreciver).collect(Collectors.toList())) {

                        messagezone.appendText(m.getMessage() + "\n");
                    }

                }
            });
            v.getChildren().addAll(new Label(u.getNom()), b);
            H.getChildren().addAll(imagev1, v);
            H.setSpacing(50);
            list.getItems().addAll(H);

        }

    }

}
