/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import erando.services.impl.ProductService;
import java.io.IOException;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javax.xml.bind.annotation.XmlElementDecl;
import erando.services.interfaces.IShopService;
/**
 *
 * @author F.Mouhamed
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger menu;    
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Label label;
    @FXML private AnchorPane window;
    @FXML
    private AnchorPane root;
    
    

    
    public static AnchorPane rootP;
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/erando/gui/MenuContent.fxml"));
            AnchorPane windoww = FXMLLoader.load(getClass().getResource("/erando/gui/addProduct.fxml"));
            HamburgerBasicCloseTransition transition = new HamburgerBasicCloseTransition(menu);
            transition.setRate(-1);
            window.getChildren().setAll(windoww);
            IShopService productService = new ProductService();
            drawer.setSidePane(box);
            drawer.setOverLayVisible(false);
            for(Node node : box.getChildren()){
                if(node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) ->{
                switch (node.getAccessibleText()){
                    case "AjouterP":
                {
                    try {
                        window.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/erando/gui/addProduct.fxml")));
                        drawer.close();
                        transition.setRate(transition.getRate()*-1);
                        transition.play();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    case "ModifierP":
                        
                {
                    try {
                        window.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("/erando/gui/editProduct.fxml")));
                        drawer.close();
                        transition.setRate(transition.getRate()*-1);
                        transition.play();
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                          
                        break;
                    case "AfficherP":
                         {
                    try {
                        window.getChildren().setAll((StackPane)FXMLLoader.load(getClass().getResource("/erando/gui/Masonry.fxml")));
                        drawer.close();
                        transition.setRate(transition.getRate()*-1);
                        transition.play();
                        } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                        break;
                    case "Exit":
                        
                        break;
              
                }
                });
                }
            }
    
            menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                transition.setRate(transition.getRate()*-1);
                transition.play();
                
                if(drawer.isShown())
                    drawer.close();
                else
                    drawer.open();
            });
         
            
          
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
    
}
