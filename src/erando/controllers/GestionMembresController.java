/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.controllers;

import com.jfoenix.controls.JFXButton;
import erandopi.models.Membre;
import erandopi.models.Temoignage;
import erandopi.services.impl.MembreService;
import erandopi.services.impl.TemoignageService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author student1
 */
public class GestionMembresController implements Initializable {

    @FXML
    private Button btnsuppM;
    @FXML
    private TableView<Membre> tableMembres;

    @FXML
    private TableColumn<?, ?> username;

    @FXML
    private TableColumn<?, ?> username_canonical;

    @FXML
    private TableColumn<?, ?> description;
        @FXML
    private TableColumn<?, ?> id;
    @FXML
    private JFXButton retour;

    @FXML
    private Label labl;
    
    List lst = new ArrayList<>();
    MembreService tm = new MembreService();

    void setcelltable() {
        
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        username_canonical.setCellValueFactory(new PropertyValueFactory<>("username_canonical"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));

        lst = tm.getAll2();
        ObservableList<Membre> ob = FXCollections.observableArrayList(lst);

        tableMembres.setItems(ob);
    }

    @FXML
    void adminsuppM(ActionEvent event) throws IOException {

        MembreService service = new MembreService();
        System.out.println(tableMembres.getSelectionModel().selectedItemProperty().getValue().getId());
        service.delete(tableMembres.getSelectionModel().getSelectedItem().getId());
        
        
         changescene("/erandopi/gui/GestionMembres.fxml", event);
        TrayNotification tray = new TrayNotification("OK", "Le Membre a été supprimé", NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.seconds(3));
    }
    @FXML
    private JFXButton Exporter;

    @FXML
    void Exporter(ActionEvent event) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        
        Class.forName("com.mysql.jdbc.Driver");
      Connection connect = DriverManager.getConnection( 
      "jdbc:mysql://localhost:3306/e-rando2" , 
      "root" , 
      ""
      );
      Statement statement = connect.createStatement();
      ResultSet resultSet = statement
      .executeQuery("select * from member");
      XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook
      .createSheet("Membre");
      XSSFRow row=spreadsheet.createRow(1);
      XSSFCell cell;
      cell=row.createCell(1);
      cell.setCellValue("username");
      cell=row.createCell(2);
      cell.setCellValue("username_canonical");
      cell=row.createCell(3);
      cell.setCellValue("description");

      int i=2;
      while(resultSet.next())
      {
         row=spreadsheet.createRow(i);
         cell=row.createCell(1);
         cell.setCellValue(resultSet.getString("username"));
         cell=row.createCell(2);
         cell.setCellValue(resultSet.getString("username_canonical"));
         cell=row.createCell(3);
         cell.setCellValue(resultSet.getString("description"));

         i++;
      }
      FileOutputStream out = new FileOutputStream(
      new File("Membre.xlsx"));
      workbook.write(out);
      out.close();
      System.out.println(
      "Membre.xlsx written successfully");

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setcelltable();
    }
    @FXML
    void retourAction(ActionEvent event) throws IOException {
        changescene("/erandopi/gui/AdminProfile.fxml", event);
    }
    void changescene(String gui, ActionEvent event) throws IOException {
        FXMLLoader fxmlloder = new FXMLLoader(getClass().getResource(gui));

        Parent root1 = fxmlloder.load();
        Scene home_scene = new Scene(root1);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_scene);
        app_stage.show();
    }

}
