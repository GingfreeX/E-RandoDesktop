/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.User;
import erando.services.interfaces.IService;
import erando.services.interfaces.IUserService;
import erando.techniques.DataSource;
import erando.techniques.EmailAttachmentSender;
import erando.techniques.Navigation;
import erando.utils.BCrypt;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author cimope
 */
public class UserServices implements IUserService{
private Event event;
    private Connection connection;
    public UserServices() {
        connection = DataSource.getInstance().getConnection();
    }

   



    @Override
    public boolean Authentification(String login, String password) {
        boolean status=false ;
       try{
     String req = "select * from member where username=? ";
        PreparedStatement st = connection.prepareStatement(req);
       st.setString(1,login);
    
        ResultSet rs = st.executeQuery();
          
     while(rs.next()){
        if(BCrypt.checkpw(password,rs.getString(8))==true){
         User.setIdofuserAlreadyloggedin(rs.getInt(1));
         User.setMyemail(rs.getString(4));
         status= true ;
          
            
         }else{
            status= false;
         }
         
     }
        }catch(Exception ex){
            ex.printStackTrace();
        }
       return status; 
    }


 
   @Override
    public User getUserbyId(int id){
     User user = null ;
        try{
    String req = "select * from member where id=? ";
   PreparedStatement st = connection.prepareStatement(req);
   st.setInt(1,id );
   ResultSet rs = st.executeQuery();
   
    while(rs.next()){
   user = new User(rs.getInt(1),rs.getString(2),rs.getString(14),rs.getInt(13),rs.getString(16),rs.getInt(6),rs.getString(8),rs.getString(4),rs.getString(12),rs.getString(22),rs.getString(23));
         
    }
   }catch(Exception a){
        a.printStackTrace();
    }
       return user; 
    }

    @Override
    public void add(User user) {
          try{
        String req = "insert into member (username,username_canonical,email,email_canonical,password,enabled,confirmation_token,roles) values (?,?,?,?,?,?,?,?)";
        PreparedStatement st = connection.prepareStatement(req);
         String token = UUID.randomUUID().toString();
        String Role = user.getRole();
        st.setString(1, user.getNom());
        st.setString(2, user.getNom());
        st.setString(3, user.getEmail());
        st.setString(4, user.getEmail());
        st.setString(5,user.getPassword());
        st.setInt(6, 0);
         st.setString(7,token);
        if(Role.equals("Guide")){
            st.setString(8,"a:1:{i:0;s:10:\"ROLE_GUIDE\";}");  
        }else{
           st.setString(8,"a:1:{i:0;s:11:\"ROLE_MEMBRE\";}");   
        }
        
        EmailAttachmentSender.sendEmailWithAttachments(user.getEmail(),"code de confirmation de compte ",token);

        st.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
         try{
        String req = "update member SET username=?,username_canonical=?,age=?,prenom=?,description=?,profile_pic=?,mobile_number=? where id=?";
        PreparedStatement st = connection.prepareStatement(req);
        st.setString(1, user.getNom());
        st.setString(2, user.getNom());
        st.setInt(3, user.getAge());
        st.setString(4, user.getPrenom());
        st.setString(5, user.getDescrption());
         if((user.getImagePath())==null){
              st.setString(6, null);
        }else{
         st.setString(6, user.getImagePath());
        }
        st.setInt(7, user.getNumTel());
    
        st.setInt(8, user.getId());
        st.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAll() {
    List<User> users = new ArrayList<>();
        try {
            String req = "select * from member";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;    
    
    
    }

    @Override
    public String CheckRole(User u) {
        String role=null;
            if(u.getRole().equals("a:1:{i:0;s:10:\"ROLE_GUIDE\";}")){
             role = "guide";
                     }else if(u.getRole().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")){
             role = "admin";
                     }
                  else{
             role = "membre";
                     }
           
        return role;
    }

    @Override
    public User getUserByEmail(String email) {
       
             User user = null ;
        try{
    String req = "select * from member where email=? ";
   PreparedStatement st = connection.prepareStatement(req);
   st.setString(1,email );
   ResultSet rs = st.executeQuery();
   
    while(rs.next()){
   user = new User(rs.getInt(1),rs.getString(2),rs.getString(14),rs.getInt(13),rs.getString(16),rs.getString(8),rs.getString(4),rs.getString(12),rs.getInt(31));
         
    }
   }catch(Exception a){
        a.printStackTrace();
    }
       return user; 
        
        
        
    }

    @Override
    public void SendMailAndAddTokenToUser(User u,String HashedToken) {
            try{
        String req = "update member SET tokenForPassword=?  where email=?";
        PreparedStatement st = connection.prepareStatement(req);
        st.setString(1, HashedToken);
        st.setString(2, u.getEmail());

         st.executeUpdate();
       EmailAttachmentSender.sendEmailWithAttachments(u.getEmail(),"code de récupération de mot de passe ",HashedToken);
       }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Boolean CheckIfUserExist(String email) {
                  boolean check = false ;
        try{
    String req = "select * from member where email=? ";
   PreparedStatement st = connection.prepareStatement(req);
   st.setString(1,email );
   ResultSet rs = st.executeQuery();
   int i =0;
    while(rs.next()){
         i++;
                 if(i==1){
                     check=true;
                 }else{
                     check=false;
                 }
    }
   }catch(Exception a){
        a.printStackTrace();
    }
       return check; 
    }

    @Override
    public Boolean CheckToken(User user, String token) {
                boolean check = false ;
        try{
    String req = "select * from member where email=? and tokenForPassword=?  ";
   PreparedStatement st = connection.prepareStatement(req);
   st.setString(1,user.getEmail());
   st.setString(2,token);
   ResultSet rs = st.executeQuery();
   int i =0;
    while(rs.next()){
         i++;
                 if(i==1){
                     check=true;
                 }else{
                     check=false;
                 }
    }
   }catch(Exception a){
        a.printStackTrace();
    }
       return check;

    }

    @Override
    public void ressettingpassword(User u) {

        try{
        String req = "update member SET password=?,tokenForPassword=?  where email=?";
        PreparedStatement st = connection.prepareStatement(req);
      String password = BCrypt.hashpw(u.getPassword(),BCrypt.gensalt(13));
        st.setString(1, password);
        st.setString(2, null);
        st.setString(3, u.getEmail());
      st.executeUpdate();

       }catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void SendSMSAndAddTokenToUser(String HashedToken,User u) {
             try{
        String req = "update member SET tokenForPassword=?  where email=?";
        PreparedStatement st = connection.prepareStatement(req);
        st.setString(1, HashedToken);
        st.setString(2, u.getEmail());
    
         st.executeUpdate();
          
      new SmsService().sendSms(new Sms("+216"+u.getNumTel(), "Code de Recuperation : "+HashedToken));
  
       }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
        
    }

    @Override
    public  Boolean Checkconfirmationtoken(User user, String Token) {
        boolean exisit=false;
        try{
        String req = "select * from member where email=? and confirmation_token=? ";
        PreparedStatement st = connection.prepareStatement(req);
        st.setString(1, user.getEmail());
        st.setString(2, Token);
        ResultSet rs = st.executeQuery();
        int i=0;
        while(rs.next()){
         exisit=true;
       
                    
        }
    
  
       }catch(Exception ex){
            ex.printStackTrace();
        }
        return exisit;
    }

    @Override
    public void ConfirmAccount(User user, String Token) {
        String req2 ="update member set enabled=? , confirmation_token=? where email=?  ";
    
    try {
         PreparedStatement st1 = connection.prepareStatement(req2);
         st1.setInt(1,1);
         st1.setString(2, null);
         st1.setString(3,user.getEmail());
         st1.executeUpdate();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
     
    }

    @Override
    public boolean checkEnabled(User u) {
            boolean exisit=false;
        try{
        String req = "select * from member where email=? ";
        PreparedStatement st = connection.prepareStatement(req);
        st.setString(1, u.getEmail());
        
        ResultSet rs = st.executeQuery();
      
        while(rs.next()){
        
       if(rs.getInt(6)==0){
            exisit=false;
       }else{
           exisit=true; 
       }
                    
        }
    
  
       }catch(Exception ex){
            ex.printStackTrace();
        }
        return exisit;
    }

   
    
    
    }

   

   

   
    

