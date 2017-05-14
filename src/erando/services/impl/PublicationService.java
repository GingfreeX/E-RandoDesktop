/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.Groupe;
import erando.models.Membre;
import erando.models.Publication;
import erando.services.interfaces.IPublicationService;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wassim
 */
public class PublicationService  implements IPublicationService{
    
    private Connection connection;

    public PublicationService() {
          connection = DataSource.getInstance().getConnection();
    }
    

    @Override
    public void add(Publication pub) {
        try {
            String req = "insert into publication(user_id,group_id,description,datepub) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,pub.getCreateur().getId());
            ps.setInt(2,pub.getGroupe().getId());
            ps.setString(3,pub.getDescription());
            ps.setDate(4, (Date) pub.getDate_publication());
            ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Publication pub) {
        
        try {
            String req = "update publication set description = ? , datepub = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,pub.getDescription());
            ps.setDate(2,new Date(Calendar.getInstance().getTime().getTime()));
            ps.setInt(3,pub.getId());
            ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
    }

    @Override
    public void delete(Integer id) {
        
        try {
            String req = "delete from publication where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Publication> getAll() {
        List<Publication> pubs = new ArrayList<>();
        try {
            
            String req = "select * from publication";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 Publication pub = new Publication(rs.getString(2),rs.getDate(7),new Membre(rs.getInt(2)),new Groupe(rs.getInt(3)));
                 System.out.println(pub);
                 pubs.add(pub);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pubs;
    }

    @Override
    public Publication getPubById(int id) {
        Publication p = new Publication();
        try {
            String req = "select * from publication where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setDescription(rs.getString(4));
                p.setDate_publication(rs.getDate(6));
                p.setCreateur(new Membre(rs.getInt(2)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return p;
          

    }

    @Override
    public List<Publication> getPubById_Createur(int id_createur) {
        
        List<Publication> pubs = new ArrayList<>();
        try {
            String req = "select * from publication where id_createur =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id_createur);
             ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 Publication pub = new Publication(rs.getInt(1),rs.getString(2),rs.getDate(7),new Membre(rs.getInt(5)));
                 pubs.add(pub);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return pubs;
        
    }

    @Override
    public List<Publication> getPubOrderByDate(Groupe groupe) {
        List<Publication> pubs = new ArrayList<>();
        try {
            
            String req = "select * from publication where group_id = ? order by datepub ASC";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,groupe.getId());
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 Publication p = new Publication(rs.getInt(1),rs.getString(4),rs.getDate(6),new Membre(rs.getInt(2)));
                 pubs.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pubs;    }

   
    
}
