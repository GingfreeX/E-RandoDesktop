/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import erando.models.Temoignage;
import erando.services.impl.TemoignageService;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author student1
 */
public class ModifTemoignageController implements Initializable {

    @FXML
    private TextArea editTemoignage;

    @FXML
    private JFXButton deleteTemoignage;

    @FXML
    private JFXButton updateTemoignage;

    @FXML
    private JFXComboBox<Integer> comboboxListT;
   @FXML
    private JFXButton btnRetour;

    @FXML
    void btnRetour(ActionEvent event) throws IOException {
changescene("/erandopi/gui/MenuTemoignage.fxml",event);

    }
    @FXML
    void deleteTemoignage(ActionEvent event) {
     Temoignage p = new Temoignage();
        TemoignageService service = new TemoignageService();
        int id=comboboxListT.getValue();
        service.delete(id);
  
          Notifications notificationBuilder = Notifications.create()
                .title("Tres bien")
                .text("Votre avis a été supprimé")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();
    }

    @FXML
    void listIdTemoignages(ActionEvent event) {
     Temoignage p = new Temoignage();
      TemoignageService se = new TemoignageService();
       p= se.findById(comboboxListT.getValue());
     
        editTemoignage.setText(p.getMessage());
          
    }
    

    @FXML
    void updateTemoignage(ActionEvent event) {
        int id=comboboxListT.getValue();
        TemoignageService service=new TemoignageService();
       Temoignage  a = new Temoignage(editTemoignage.getText());
        

        service.update2(a,id);
  
         Notifications notificationBuilder = Notifications.create()
                .title("Tres bien")
                .text("Votre avis a été modifié")
                .graphic(null)
                .hideAfter(Duration.seconds(4))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TemoignageService sp = new TemoignageService();
        ObservableList o = FXCollections.observableArrayList();
        List<Temoignage> lt = new ArrayList<>();
        lt = sp.getAll();
        o.addAll(lt.stream()
                             .map((q) -> q.getId())
                             .collect(Collectors.toList()));
        comboboxListT.setItems(o);
    }    
    void changescene(String gui, ActionEvent event) throws IOException {
 FXMLLoader fxmlloder = new FXMLLoader(getClass().getResource(gui));

            Parent root1 = fxmlloder.load();
            Scene home_scene = new Scene(root1);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setScene(home_scene);
            app_stage.show();
}
}
