/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.services.impl;

import erandopi.models.Membre;
import erandopi.models.Temoignage;
import erandopi.services.interfaces.IMembreService;
import erandopi.techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author student1
 */
public class MembreService implements IMembreService{
     private Connection connection;
     public MembreService(){
        connection = DataSource.getInstance().getConnection(); 
     }
    @Override 
    public void add(Membre m) {
       
        
        try {
            String req = "insert into member(username_canonical,username,description) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
           ps.setString(1, m.getUsername());
           ps.setString(2, m.getUsername_canonical());
           ps.setString(3, m.getDescription());
           ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
   @Override
    public void delete(Integer id) {
         try {
            String req = "delete from member where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
 @Override
    public List<Membre> getAll() {
 List<Membre> membre = new ArrayList<>();
        try {
            String req = "select * from member";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Membre m = new Membre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                membre.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return membre;    
    }
    public List<Membre> getAll2() {
 List<Membre> membre = new ArrayList<>();
        try {
            String req = "select id,username,username_canonical,description from member";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Membre m = new Membre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
                membre.add(m);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return membre; 
    }

            @Override
    public Membre findById(Integer id) {
 Membre membre = null;
      
            String req = "select * from member where id =?";
              try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Membre m = new Membre(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return membre;  
    }
    
  public ResultSet consulter() {
        
        ResultSet result=null;
        String requete="select * from member ;";
         try{
             PreparedStatement ps=connection.prepareStatement(requete);
             result= ps.executeQuery();
    
         }catch(SQLException ex){
             Logger.getLogger(MembreService.class.getName()).log(Level.SEVERE, null, ex);
             
         } 
        return result;
        
       }  

    @Override
    public void update(Membre m) {
try {
     // thabet f base de donne message fama wala la
            String req = "update member set description = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
           ps.setString(1, m.getDescription());
            ps.setInt(2, m.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
    }
}
