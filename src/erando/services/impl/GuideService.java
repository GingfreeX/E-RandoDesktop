/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.Guide;
import erando.services.interfaces.IGuideService;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student1
 */
public class GuideService implements IGuideService{
     private Connection connection;
     public GuideService(){
        connection = DataSource.getInstance().getConnection(); 
     }
     
     @Override 
    public void add(Guide g) {
       
        
        try {
            String req = "insert into guide(nom, prenom, email, statut) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
           ps.setString(1, g.getNom());
           ps.setString(2, g.getPrenom());
           ps.setString(3, g.getEmail());
           ps.setString(4, g.getStatut());
           ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
   @Override
    public void delete(Integer idguide) {
         try {
            String req = "delete from guide where idguide =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,idguide);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }  
    
    @Override
    public List<Guide> getAll() {
 List<Guide> guide = new ArrayList<>();
        try {
            String req = "select * from guide";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Guide g = new Guide(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
                guide.add(g);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return guide;    
    }
  
  public List<Guide> getAll2() {
 List<Guide> guide = new ArrayList<>();
        try {
            String req = "select idguide, nom,prenom,email,statut from guide";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Guide g = new Guide(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                guide.add(g);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return guide; 
    }  
  
  
    public Guide findById(Integer id) {
 Guide guide = null;
        try {
            String req = "select * from guide where idguide =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              Guide m = new Guide(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return guide;  
    }
    @Override
    public void update(Guide m) {
try {
            String req = "update guide set nom = ? where idguide = ?";
            PreparedStatement ps = connection.prepareStatement(req);
           ps.setString(1, m.getNom());
            ps.setInt(2, m.getIdguide());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    
    }

    @Override
    public void BannGuide(Guide g) {
try {
            String req = "update guide set enabled = 0 , statut='inactif' where idguide = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, g.getIdguide());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }

    @Override
    public void ApprouvGuide(Guide g) {
        try {
            String req = "update guide set enabled = 1 , statut='actif' where idguide = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, g.getIdguide());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }  
    }
}
