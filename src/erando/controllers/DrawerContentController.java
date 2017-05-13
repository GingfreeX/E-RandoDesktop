/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author student1
 */
public class DrawerContentController implements Initializable {

    @FXML
    private VBox VBox;

    @FXML
    private ImageView img;

    @FXML
    private JFXButton btnM;

    @FXML
    private JFXButton btnG;

    @FXML
    private JFXButton btnP;

    @FXML
    private JFXButton btnS;

    @FXML
    private JFXButton btnE;
    @FXML
    private JFXButton Membres;

    @FXML
    void GestMembres(ActionEvent event) throws IOException {

        JFXButton btn = (JFXButton) event.getSource();

        switch (btn.getText()) {

            case "Membres": {
                System.out.println(btn.getText());
                AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/erandopi/gui/GestionMembres.fxml"));
                AdminProfileController.rootP.getChildren().clear();
                AdminProfileController.rootP.getChildren().setAll(pane1);

                break;
            }

        }
    }

    @FXML
    void GestGuides(ActionEvent event) throws IOException {
        JFXButton btn = (JFXButton) event.getSource();
        switch (btn.getText()) {
            case "Guides": {
                System.out.println(btn.getText());
                AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/erandopi/gui/GestionGuides.fxml"));
                AdminProfileController.rootP.getChildren().clear();
                AdminProfileController.rootP.getChildren().setAll(pane1);

                break;
            }
        }
    }

    @FXML
    void GestProduit(ActionEvent event) throws IOException {

        JFXButton btn = (JFXButton) event.getSource();

        switch (btn.getText()) {
            case "Produit": {
                System.out.println(btn.getText());
                AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/erandopi/gui/AfficheTemoignage.fxml"));
                AdminProfileController.rootP.getChildren().clear();
                AdminProfileController.rootP.getChildren().setAll(pane1);

                break;
            }
        }

    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void changescene(String gui, ActionEvent event) throws IOException {
        FXMLLoader fxmlloder = new FXMLLoader(getClass().getResource(gui));

        Parent root1 = fxmlloder.load();
        Scene home_scene = new Scene(root1);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_scene);
        app_stage.show();
    }

    @FXML
    void GestStatistiques(ActionEvent event) throws IOException {

        JFXButton btn = (JFXButton) event.getSource();

        switch (btn.getText()) {
            case "Statistiques": {
                System.out.println(btn.getText());
                AnchorPane pane1 = FXMLLoader.load(getClass().getResource("/erandopi/FXMLDocument.fxml"));
                AdminProfileController.rootP.getChildren().clear();
                AdminProfileController.rootP.getChildren().setAll(pane1);

                break;
            }
        }

    }

}
