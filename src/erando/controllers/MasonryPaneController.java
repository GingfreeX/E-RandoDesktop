package erando.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import erando.models.Parameters;
import erando.Product;
import erando.services.impl.ProductService;
import java.io.IOException;
import java.net.URL;
//import io.datafx.controller.ViewController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.animation.Interpolator.EASE_BOTH;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import erando.services.interfaces.IShopService;

//@ViewController(value = "/fxml/ui/Masonry.fxml", title = "Material Design Example")
public class MasonryPaneController implements Initializable {

    @FXML
    private ScrollPane scrollPane;

  
    @FXML
    private JFXTextField toFind;
    private List<Product> pList;
    private int x =0;
        @FXML
    private AnchorPane test;
    @FXML
    private JFXSlider maxPrice;   
        
    @FXML
    private StackPane root;
    
    @FXML
    private Label foundX; 
    /**
     * init fxml when loaded.
     */
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IShopService pservice = new ProductService();
        List<Product> pList = pservice.getAll();
        int x = pservice.count();
        foundX.setText("Totale :"+x);
       // x = productService.count();
        init( pList, x); 
    }
    
    @PostConstruct
    public void init(List<Product> pList,int x) {
        ArrayList<Node> children = new ArrayList<>();
        children.clear();

        for (Product p : pList) {
            if (p.getPrix() <= maxPrice.getValue()){
            Label titre = new Label();
            Label prix = new Label();
            StackPane child = new StackPane();
            double width = Math.random() * 150 + 150;
             child.setMinWidth(width);
            child.setMaxWidth(width);
            child.setPrefWidth(width);
            double height = Math.random() * 150 + 150;
            child.setMinHeight(height);
            child.setMaxHeight(height);
            child.setPrefHeight(height);
            JFXDepthManager.setDepth(child, 1);
            
            children.add(child);

            // create content
            StackPane header = new StackPane();
            Image image = new Image("file:///C:/Users/F.Mouhamed/Desktop/Esprit/ERandoPi/userfiles/"+p.getImage(), 100, 100, false, false);
            System.out.println(p.getImage());
            ImageView img = new ImageView(image);
         
            header.getChildren().add(img);
            String headerColor = getDefaultColor(p.getId() % 12);
            header.setStyle("-fx-background-radius: 5 5 0 0; -fx-background-color: " + headerColor);
            VBox.setVgrow(header, Priority.ALWAYS);
            StackPane body = new StackPane();
            body.setMinHeight(Math.random() * 30 + 50);
            VBox bodyv = new VBox();
            titre.setStyle("-fx-font-family:Segoe Marker;-fx-font-size:25px");
            titre.setText(p.getTitre());
            prix.setStyle("-fx-font-family:Segoe Marker;-fx-font-size:25px;");
            prix.setAlignment(Pos.BOTTOM_RIGHT);
            prix.setText(""+p.getPrix());
            bodyv.getChildren().addAll(titre,prix);
            body.getChildren().add(bodyv);
            VBox content = new VBox();
            content.getChildren().addAll(header, body);
            body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");
              

            // create button
            JFXButton button = new JFXButton("");
            button.setButtonType(ButtonType.RAISED);
            button.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
            button.setPrefSize(40, 40);
            button.setRipplerFill(Color.valueOf(headerColor));
            button.setScaleX(0);
            button.setScaleY(0);
            SVGGlyph glyph = new SVGGlyph(-1,
                "test",
                "M1008 6.286q18.857 13.714 15.429 36.571l-146.286 877.714q-2.857 16.571-18.286 25.714-8 4.571-17.714 4.571-6.286 "
                + "0-13.714-2.857l-258.857-105.714-138.286 168.571q-10.286 13.143-28 13.143-7.429 "
                + "0-12.571-2.286-10.857-4-17.429-13.429t-6.571-20.857v-199.429l493.714-605.143-610.857 "
                + "528.571-225.714-92.571q-21.143-8-22.857-31.429-1.143-22.857 18.286-33.714l950.857-548.571q8.571-5.143 18.286-5.143"
                + " 11.429 0 20.571 6.286z",
                Color.WHITE);
            glyph.setSize(20, 20);
            button.setGraphic(glyph);
            button.translateYProperty().bind(Bindings.createDoubleBinding(() -> {
                return header.getBoundsInParent().getHeight() - button.getHeight() / 2;
            }, header.boundsInParentProperty(), button.heightProperty()));
            StackPane.setMargin(button, new Insets(0, 12, 0, 0));
            StackPane.setAlignment(button, Pos.TOP_RIGHT);

            Timeline animation = new Timeline(new KeyFrame(Duration.millis(240),
                                                           new KeyValue(button.scaleXProperty(),
                                                                        1,
                                                                        EASE_BOTH),
                                                           new KeyValue(button.scaleYProperty(),
                                                                        1,
                                                                        EASE_BOTH)));
            animation.setDelay(Duration.millis(10 * p.getId() + 10));
            animation.play();
            button.setOnMouseClicked(e->{
                Stage stage = new Stage();
                try {
                    showmoredetails(stage, p);
                } catch (IOException ex) {
                    Logger.getLogger(MasonryPaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            child.getChildren().addAll(content, button);
        }
        }
        JFXMasonryPane mansour = new JFXMasonryPane();
        mansour.setPrefSize(1030, 600);
       
        mansour.getChildren().addAll(children);
        test.getChildren().add(mansour);
      //  masonryPane.getChildren().addAll(children);
        
        Platform.runLater(() -> scrollPane.requestLayout());

        JFXScrollPane.smoothScrolling(scrollPane);
    }
    
    public void add(List<Product> pList,int x){
         ArrayList<Node> children = new ArrayList<>();
        children.clear();

        for (Product p : pList) {
            if (p.getPrix() <= maxPrice.getValue()){
            Label titre = new Label();
            Label prix = new Label();
            StackPane child = new StackPane();
            double width = Math.random() * 150 + 150;
             child.setMinWidth(width);
            child.setMaxWidth(width);
            child.setPrefWidth(width);
            double height = Math.random() * 150 + 150;
            child.setMinHeight(height);
            child.setMaxHeight(height);
            child.setPrefHeight(height);
            JFXDepthManager.setDepth(child, 1);
            
            children.add(child);

            // create content
            StackPane header = new StackPane();
            Image image = new Image("file:///C:/Users/F.Mouhamed/Desktop/Esprit/ERandoPi/userfiles/"+p.getImage(), 100, 100, false, false);
            System.out.println(p.getImage());
            ImageView img = new ImageView(image);
         
            header.getChildren().add(img);
            String headerColor = getDefaultColor(p.getId() % 12);
            header.setStyle("-fx-background-radius: 5 5 0 0; -fx-background-color: " + headerColor);
            VBox.setVgrow(header, Priority.ALWAYS);
            StackPane body = new StackPane();
            body.setMinHeight(Math.random() * 30 + 50);
            VBox bodyv = new VBox();
            titre.setStyle("-fx-font-family:Segoe Marker;-fx-font-size:25px");
            titre.setText(p.getTitre());
            prix.setStyle("-fx-font-family:Segoe Marker;-fx-font-size:25px;");
            prix.setAlignment(Pos.BOTTOM_RIGHT);
            prix.setText(""+p.getPrix());
            bodyv.getChildren().addAll(titre,prix);
            body.getChildren().add(bodyv);
            VBox content = new VBox();
            content.getChildren().addAll(header, body);
            body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");
              

            // create button
            JFXButton button = new JFXButton("");
            button.setButtonType(ButtonType.RAISED);
            button.setStyle("-fx-background-radius: 40;-fx-background-color: " + getDefaultColor((int) ((Math.random() * 12) % 12)));
            button.setPrefSize(40, 40);
            button.setRipplerFill(Color.valueOf(headerColor));
            button.setScaleX(0);
            button.setScaleY(0);
            SVGGlyph glyph = new SVGGlyph(-1,
                "test",
                "M1008 6.286q18.857 13.714 15.429 36.571l-146.286 877.714q-2.857 16.571-18.286 25.714-8 4.571-17.714 4.571-6.286 "
                + "0-13.714-2.857l-258.857-105.714-138.286 168.571q-10.286 13.143-28 13.143-7.429 "
                + "0-12.571-2.286-10.857-4-17.429-13.429t-6.571-20.857v-199.429l493.714-605.143-610.857 "
                + "528.571-225.714-92.571q-21.143-8-22.857-31.429-1.143-22.857 18.286-33.714l950.857-548.571q8.571-5.143 18.286-5.143"
                + " 11.429 0 20.571 6.286z",
                Color.WHITE);
            glyph.setSize(20, 20);
            button.setGraphic(glyph);
            button.translateYProperty().bind(Bindings.createDoubleBinding(() -> {
                return header.getBoundsInParent().getHeight() - button.getHeight() / 2;
            }, header.boundsInParentProperty(), button.heightProperty()));
            StackPane.setMargin(button, new Insets(0, 12, 0, 0));
            StackPane.setAlignment(button, Pos.TOP_RIGHT);

            Timeline animation = new Timeline(new KeyFrame(Duration.millis(240),
                                                           new KeyValue(button.scaleXProperty(),
                                                                        1,
                                                                        EASE_BOTH),
                                                           new KeyValue(button.scaleYProperty(),
                                                                        1,
                                                                        EASE_BOTH)));
            animation.setDelay(Duration.millis(10 * p.getId() + 10));
            animation.play();
            button.setOnMouseClicked(e->{
                   Stage stage = new Stage();
                try {
                    showmoredetails(stage, p);
                } catch (IOException ex) {
                    Logger.getLogger(MasonryPaneController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            child.getChildren().addAll(content, button);
        }
        }
        JFXMasonryPane mansour = new JFXMasonryPane();
        mansour.setPrefSize(1030, 600);
       
        mansour.getChildren().addAll(children);
        test.getChildren().add(mansour);
      //  masonryPane.getChildren().addAll(children);
        
        Platform.runLater(() -> scrollPane.requestLayout());

        JFXScrollPane.smoothScrolling(scrollPane);
   
    }

    private String getDefaultColor(int i) {
        String color = "#FFFFFF";
        switch (i) {
            case 0:
                color = "#8F3F7E";
                break;
            case 1:
                color = "#B5305F";
                break;
            case 2:
                color = "#CE584A";
                break;
            case 3:
                color = "#DB8D5C";
                break;
            case 4:
                color = "#DA854E";
                break;
            case 5:
                color = "#E9AB44";
                break;
            case 6:
                color = "#FEE435";
                break;
            case 7:
                color = "#99C286";
                break;
            case 8:
                color = "#01A05E";
                break;
            case 9:
                color = "#4A8895";
                break;
            case 10:
                color = "#16669B";
                break;
            case 11:
                color = "#2F65A5";
                break;
            case 12:
                color = "#4E6A9C";
                break;
            default:
                break;
        }
        return color;
    }
    @FXML
    private void seearchAction(KeyEvent event) {
        IShopService productService = new ProductService();
        System.err.println("trying to search for :"+toFind.getText().toString());
        pList = productService.find(toFind.getText().toString());
        x = pList.size();
        System.err.println("results found ="+x);
       // root.getChildren().clear();
       foundX.setText(x+"produits");
       test.getChildren().clear();
       add(pList,x); 
    }
    
    //function to show more infos about the product when clicked on button 
     private void showmoredetails(Stage stage,Product p) throws IOException {
        Parameters.idP = p.getId();
        Parameters.typeP = p.getType();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/erando/gui/productInfo.fxml"));
        Parent root = (Parent)fxmlLoader.load();  
        ProductInfoController controller = fxmlLoader.<ProductInfoController>getController();
        controller.setInfos(p);
        JFXDecorator decorator = new JFXDecorator(stage, root);
        Scene scene = new Scene(decorator);        
        stage.setScene(scene);
        stage.show();
    }


}
