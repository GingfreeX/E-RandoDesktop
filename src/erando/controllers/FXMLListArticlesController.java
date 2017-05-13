/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import erando.models.PublicationGroup;
import erando.models.User;
import erando.services.impl.PublicationServices;
import erando.services.interfaces.IService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.controlsfx.control.Notifications;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import erando.services.interfaces.IPublicationGroupService;

/**
 * FXML Controller class
 *
 * @author cimope
 */
public class FXMLListArticlesController implements Initializable {

    @FXML
    private TableView<PublicationGroup> tableview;
    
    @FXML
    private TableColumn<PublicationGroup, String> sectioncolumn;

    @FXML
    private TableColumn<PublicationGroup, String> Descriptioncolumn;
     @FXML
    private TableColumn<PublicationGroup, String> imagecolumn;
    
 
    @FXML
    private JFXComboBox<String> Section;

    @FXML
    private JFXTextField description;
     @FXML
    private Label hiddenid;
    @FXML
    private JFXTextField imagepath;
        private static Path destination;
    private static File selectedfile;
    
    @FXML
    void DeleteButton(ActionEvent event) {
int id = Integer.parseInt(hiddenid.getText());
    IPublicationGroupService publicationservice = new PublicationServices();
    publicationservice.delete(id);
   tableview.getColumns().get(0).setVisible(false);
   tableview.getColumns().get(0).setVisible(true);
     Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text(" Publication supprimée ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.TOP_LEFT);
                        NotificationBuilder.showConfirm();

    }
    @FXML
    void AddImage(ActionEvent event) {
     FileChooser fc = new FileChooser();
       selectedfile = fc.showOpenDialog(null);
        
        destination = Paths.get("D:/Nouveau dossier/ERandopi/src/erando/images/",selectedfile.getName());
       
        imagepath.setText(selectedfile.getAbsolutePath());
    }
    @FXML
    void UpdateButton(ActionEvent event) throws IOException{
           ValidationSupport validationSupport = new ValidationSupport();

      if(description.getText().isEmpty()){
  validationSupport.getValidationDecorator().applyValidationDecoration(ValidationMessage.error(description, "required name"));

      }else{
        IPublicationGroupService publicationservice = new PublicationServices();
        PublicationGroup publication = publicationservice.getPublicationById(Integer.parseInt(hiddenid.getText()));
        publication.setSection(Section.getValue());
         publication.setDescription(description.getText()); if(selectedfile==null){
        publication.setImagepath(null);
        }else{
            Files.copy(selectedfile.toPath(),destination); 
             publication.setImagepath(destination.toString());
        }
         
        publicationservice.update(publication);
         tableview.getColumns().get(0).setVisible(false);
   tableview.getColumns().get(0).setVisible(true);
         Notifications NotificationBuilder = Notifications.create()
                                .title("Succée")
                                .text(" Publication  mise à jour ")
                                .graphic(null)
                                .hideAfter(Duration.seconds(4))
                                .position(Pos.TOP_LEFT);
                        NotificationBuilder.showConfirm();
                
    } 
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    sectioncolumn.setCellValueFactory(new PropertyValueFactory<>("section"));
    Descriptioncolumn.setCellValueFactory(new PropertyValueFactory<>("description"));
    imagecolumn.setCellValueFactory(new PropertyValueFactory<>("imagepath"));
    tableview.setItems(getMyPublications());
        Section.getItems().addAll(
                "chasse","peche","camping"
        );
    tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
      ObservableList<PublicationGroup>   selecteditem,allitems;
      allitems = tableview.getItems();
       selecteditem = tableview.getSelectionModel().getSelectedItems();
       
        description.setText(selecteditem.get(0).getDescription());
        hiddenid.setText( selecteditem.get(0).getId()+"");
        imagepath.setText( selecteditem.get(0).getImagepath());
        
        if (newSelection != null) {
        tableview.getSelectionModel().clearSelection();
    }
    });



    
    
    }    
    public void LoadData(){
        
    }
    public ObservableList<PublicationGroup> getMyPublications(){
        int id = User.getIdofuserAlreadyloggedin();
        IService publicationsService = new PublicationServices();
         List<PublicationGroup> List1 = publicationsService.getAll();
        List <PublicationGroup> List2 = List1.stream().filter(x->x.getUser().getId()==id).collect(Collectors.toList());
          List2.forEach(System.out::println);
         ObservableList<PublicationGroup> listpub =FXCollections.observableArrayList(List2);
   return  listpub ;
        
    }
 
}
