/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import erando.models.Parameters;
import erando.models.Product;
import erando.models.ProductComment;
import erando.services.impl.ProductCommentService;
import erando.services.impl.ProductService;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.animation.Interpolator.EASE_BOTH;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.annotation.PostConstruct;
import org.controlsfx.control.Notifications;
import erando.services.interfaces.IShopService;

/**
 * FXML Controller class
 *
 * @author F.Mouhamed
 */
public class ProductInfoController implements Initializable {
    @FXML
    private ImageView pImage;
    @FXML
    private Label pTitre;
    @FXML
    private Label pPrix;
    @FXML
    private JFXTextArea commentDescription;
    private int idProduit ;
    private int idCurrentUser;
    private String pType;

    @FXML
    private VBox comments;
    @FXML
    private Label cuser;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label cdescription;

    @FXML
    private Label cdate;
    @FXML
    private AnchorPane comment;
    @FXML
    private JFXButton likeButton;
    @FXML
    private ImageView likeImage;
    @FXML
    private Label likesNumber;
    @FXML
    private JFXCheckBox subscribed;
    
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idProduit =Parameters.idP;
        pType = Parameters.typeP;
         IShopService productService = new ProductService();
            if (productService.checkifLiked(idProduit, Parameters.user.getId())){ 
            Image image = new Image("file:///C:/Users/F.Mouhamed/Desktop/Esprit/ERandoPi/src/icons/iconLiked.png");
            likeImage.setImage(image);
            }
            checkLikes();
            if (productService.checkifSubscribed(pType, Parameters.user.getId())){ 
            subscribed.setSelected(true);
            }
        ProductInfoController(idProduit);
       
    }
    @PostConstruct
    public void ProductInfoController(int x) {
        
        idProduit=x;
        ArrayList<Node> children = new ArrayList<>();
        children.clear();
        IShopService commentService = new ProductCommentService();
        List<ProductComment> cList = commentService.getOwn(idProduit);

        for (ProductComment p : cList) {
            
                    Label desc2 = new Label();
                    desc2.setStyle(cdescription.getStyle());
                    desc2.setPrefSize(cdescription.getPrefWidth(), cdescription.getPrefHeight());
                    desc2.setMinSize(cdescription.getPrefWidth(), cdescription.getPrefHeight());
                    desc2.setLayoutX(cdescription.getLayoutX());
                    desc2.setLayoutY(cdescription.getLayoutY());
                    desc2.setText(p.getDescription());
                    Label date2 = new Label();
                    desc2.setStyle(cdate.getStyle());
                    date2.setLayoutX(cdate.getLayoutX());
                    date2.setLayoutY(cdate.getLayoutY());
                    date2.setText(p.getDate().toString());
                    Label user2 = new Label(); 
                    desc2.setStyle(cuser.getStyle());
                    user2.setLayoutX(cuser.getLayoutX());
                    user2.setLayoutY(cuser.getLayoutY());
                    user2.setText(""+Parameters.user.getNom());
                    JFXButton button = new JFXButton("x");
                    button.setButtonType(JFXButton.ButtonType.RAISED);
                    button.setPrefSize(40, 40);
                    button.setLayoutX(390);
                    button.setLayoutY(-10);
                    button.setOnMouseClicked(e->{
                    Stage stage = new Stage();
                    deletecomment(p.getId());
                     });
            AnchorPane comment2 = new AnchorPane();
            comment2.setPrefSize(comment.getWidth(), comment.getHeight());
            comment2.getChildren().addAll(desc2,date2,user2,button);
            comments.getChildren().addAll(comment2);
        }
        Platform.runLater(() -> scrollPane.requestLayout());
        JFXScrollPane.smoothScrolling(scrollPane);
        
    }
    void refreshComments(){
        ArrayList<Node> children = new ArrayList<>();
        children.clear();
        IShopService commentService = new ProductCommentService();
        List<ProductComment> cList = commentService.getOwn(idProduit);

        for (ProductComment p : cList) {
            
                    Label desc2 = new Label();
                    desc2.setStyle(cdescription.getStyle());
                    desc2.setPrefSize(cdescription.getPrefWidth(), cdescription.getPrefHeight());
                    desc2.setMinSize(cdescription.getPrefWidth(), cdescription.getPrefHeight());
                    desc2.setLayoutX(cdescription.getLayoutX());
                    desc2.setLayoutY(cdescription.getLayoutY());
                    desc2.setText(p.getDescription());
                    Label date2 = new Label();
                    desc2.setStyle(cdate.getStyle());
                    date2.setLayoutX(cdate.getLayoutX());
                    date2.setLayoutY(cdate.getLayoutY());
                    date2.setText(p.getDate().toString());
                    Label user2 = new Label(); 
                    desc2.setStyle(cuser.getStyle());
                    user2.setLayoutX(cuser.getLayoutX());
                    user2.setLayoutY(cuser.getLayoutY());
                    user2.setText(""+Parameters.user.getNom());
                    JFXButton button = new JFXButton("x");
                    button.setButtonType(JFXButton.ButtonType.RAISED);
                    button.setPrefSize(40, 40);
                    button.setLayoutX(390);
                    button.setLayoutY(-10);
                    button.setOnMouseClicked(e->{
                    Stage stage = new Stage();
                    deletecomment(p.getId());
                     });
            AnchorPane comment2 = new AnchorPane();
            comment2.setPrefSize(comment.getWidth(), comment.getHeight());
            comment2.getChildren().addAll(desc2,date2,user2,button);
            comments.getChildren().addAll(comment2);
        }
        Platform.runLater(() -> scrollPane.requestLayout());
        JFXScrollPane.smoothScrolling(scrollPane);
    }

    public void setInfos (Product p){
        Image image = new Image("file:///C:/Users/F.Mouhamed/Desktop/Esprit/ERandoPi/userfiles/"+p.getImage());
        pImage.setImage(image);
        pTitre.setText(p.getTitre());
        pPrix.setText("Prix :"+p.getPrix());
        pType = p.getType() ;
    }
    @FXML
    void addComment(ActionEvent event) {
        IShopService commentService = new ProductCommentService();
        ProductComment cmt = new ProductComment();
        cmt.setDescription(commentDescription.getText().toString());
        cmt.setIdProduct(idProduit);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY:MM/HH:mm:ss");
        cmt.setDate(sdf.format(cal.getTime()));
        commentService.add(cmt);
        //refresh comments list
        comments.getChildren().clear();
        refreshComments();
        //push notification to notifie user 
        Notifications notificationBuilder = Notifications.create()
                .title("sucess")
                .text("commentaire envoyee")
                .graphic(null)
                .hideAfter(Duration.seconds(2))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.showConfirm();
    }
     @FXML
    void likeProduct(ActionEvent event) {
        IShopService productService = new ProductService();
       if (!productService.checkifLiked(idProduit, Parameters.user.getId())){
        productService.like(idProduit, Parameters.user.getId());
        Image image = new Image("file:///C:/Users/F.Mouhamed/Desktop/Esprit/ERandoPi/src/icons/iconLiked.png");
        likeImage.setImage(image);
        checkLikes();
       }
       else if (productService.checkifLiked(idProduit, Parameters.user.getId())){
        productService.dislike(idProduit, Parameters.user.getId());
        Image image = new Image("file:///C:/Users/F.Mouhamed/Desktop/Esprit/ERandoPi/src/icons/icon.png");
        likeImage.setImage(image);
        checkLikes();
       }
    }
    void checkLikes() {
        IShopService productService = new ProductService();
        likesNumber.setText(""+productService.countLikes(idProduit));
    }
     @FXML
    void subscribe(ActionEvent event) {
        IShopService productService = new ProductService();
        if (subscribed.isSelected()){
            productService.subscribe(Parameters.user.getId(),Parameters.user.getEmail(),pType);
        }
        else {
            productService.unsubscribe(Parameters.user.getId(), pType);
        }
    }
    void deletecomment(int idComment){
        IShopService commentService = new ProductCommentService();
        commentService.delete(idComment);
        comments.getChildren().clear();
        refreshComments();
         Notifications notificationBuilder = Notifications.create()
                .title("sucess")
                .text("commentaire supprimee")
                .graphic(null)
                .hideAfter(Duration.seconds(2))
                .position(Pos.BOTTOM_RIGHT);
                notificationBuilder.showConfirm();
    }
    

}
