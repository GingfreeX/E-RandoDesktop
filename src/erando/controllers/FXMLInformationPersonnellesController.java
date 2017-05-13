/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import erando.models.User;
import erando.services.impl.UserServices;
import erando.services.interfaces.IUserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLInformationPersonnellesController implements Initializable {
    
      @FXML
    private Label nomprenom;

    @FXML
    private Label email;

    @FXML
    private Label VilleOrigine;

    @FXML
    private Label Age;

    @FXML
    private Label Description;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       LoadData();
    }    
    public void LoadData(){
        int id = User.getIdofuserAlreadyloggedin();
        IUserService userservice =  new UserServices();
      User user =  userservice.getUserbyId(id);
      nomprenom.setText(user.getNom()+"    "+user.getPrenom());
      email.setText(user.getEmail());
      Age.setText(user.getAge()+"");
      Description.setText(user.getDescrption());
    }
    
}
