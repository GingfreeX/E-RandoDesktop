/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.controllers;

import com.jfoenix.controls.JFXDrawer;

import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import erandopi.controllers.DrawerContentController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author student1
 */
public class AdminProfileController implements Initializable {

    @FXML
    private AnchorPane anchropane;

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer Drawer;
    @FXML
    private JFXDrawer Drawer2;

    public static AnchorPane rootP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rootP = anchropane;
        try {

            VBox box = FXMLLoader.load(getClass().getResource("/erandopi/gui/DrawerContent.fxml"));

            Drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);

            transition.setRate(-1);

            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {

                transition.setRate(transition.getRate() * -1);

                transition.play();

                if (Drawer.isShown()) {
                    Drawer.close();

                } else {
                    Drawer.open();
                }

            });
        } catch (IOException ex) {
            Logger.getLogger(AdminProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
