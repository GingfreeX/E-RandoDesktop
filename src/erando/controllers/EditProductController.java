/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import erando.models.Parameters;
import erando.Product;
import erando.services.impl.ProductService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import erando.services.interfaces.IShopService;
/**
 * FXML Controller class
 *
 * @author F.Mouhamed
 */
public class EditProductController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> options = 
    FXCollections.observableArrayList(
        "Trucs de camping",
        "Matériaux de chasse",
        "Vêtements"
    );
    @FXML private JFXListView<String> editList;
    @FXML private JFXButton btn;
    @FXML
    private JFXTextField pTitre;
    @FXML 
    private JFXComboBox  pType =  new JFXComboBox(options);
    @FXML
    private JFXTextField pPrix;
    @FXML
    private JFXTextArea pDescription;
    @FXML
    private ImageView pImage;
    @FXML
    private JFXButton newPhotoButton;
    private String imageName;
    private int idP;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
            IShopService productService = new ProductService();
            List<Product> list = productService.getOwn(Parameters.user.getId());
            ObservableList<String> l = FXCollections.observableArrayList();
            for (Product p : list) {
                l.add(p.getTitre());
            }
            editList.getItems().addAll(l);
            pType.getItems().addAll(options); 
    }    
    @FXML
    public void load(ActionEvent event) {
        editList.setExpanded(true);
        editList.depthProperty().set(1);
    }
    @FXML 
    public void handleMouseClick() {
    IShopService productService = new ProductService();
    Product p = (Product)productService.findByTitle(editList.getSelectionModel().getSelectedItem().toString());
    System.out.println(p);
    pTitre.setText(p.getTitre());
    pPrix.setText(""+p.getPrix());
    pDescription.setText(p.getDescription());
    pType.setValue(p.getType());
    Image image = new Image("file:///C:/Users/F.Mouhamed/Desktop/Esprit/ERandoPi/userfiles/"+p.getImage());
    imageName=p.getImage();
    idP=p.getId();
    pImage.setImage(image);
    }
    @FXML
    private void uploadPictureAction(ActionEvent event) {
        Stage currentStage = (Stage) newPhotoButton.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File f = new File("C://Users//F.Mouhamed//Desktop");
        fileChooser.setInitialDirectory(f);
        File selectedFile = fileChooser.showOpenDialog(currentStage);
            Path movefrom = FileSystems.getDefault().getPath(selectedFile.getPath());
            imageName = selectedFile.getName();
            Path target = FileSystems.getDefault().getPath("userfiles/"+selectedFile.getName());
            Path targetDir = FileSystems.getDefault().getPath("userfiles/");
            try{
                Files.move(movefrom,target,StandardCopyOption.ATOMIC_MOVE);
            }catch (IOException e){}
        Image image = new Image("file:///C:/Users/F.Mouhamed/Desktop/Esprit/ERandoPi/userfiles/"+imageName);
        pImage.setImage(image);
        
    }
    @FXML
    private void editProductAction(ActionEvent event) {
        Product p = new Product();
        IShopService productService = new ProductService();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM/HH:mm:ss");
        
        p.setTitre(pTitre.getText());
        p.setPrix(Float.parseFloat(pPrix.getText()));
        p.setDescription(pDescription.getText());
        p.setType(pType.getValue().toString());
        p.setDate(sdf.format(cal.getTime()));
        p.setImage(imageName);
        p.setId(idP);
        if (!pTitre.getText().isEmpty() && !pPrix.getText().isEmpty() && !pDescription.getText().isEmpty() && !pType.getValue().toString().isEmpty() ){
        productService.update(p);
        //refresh listview after editing
        refreshListView();
        //show notification
        Notifications notificationBuilder = Notifications.create()
                .title("sucess")
                .text("produit modifier")
                .graphic(null)
                .hideAfter(Duration.seconds(2))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.darkStyle();
                notificationBuilder.showConfirm();
        }
    }
    private void refreshListView() {
        IShopService productService = new ProductService();
            List<Product> list = productService.getTitles();
            ObservableList<String> l = FXCollections.observableArrayList();
            for (Product p : list) {
                l.add(p.getTitre());
            }
            editList.getItems().clear();
            editList.getItems().addAll(l);
    }
    @FXML
    private void removeProductAction(ActionEvent event) {
        IShopService productService = new ProductService();
        productService.delete(idP);
        refreshListView();
    }
    
}
