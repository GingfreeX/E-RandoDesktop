/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.Groupe;
import erando.models.ImageG;
import erando.models.Membre;
import erando.models.Publication;
import erando.services.interfaces.IImageService;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author wassim
 */
public class ImageService implements IImageService{
        private Connection connection;

    public ImageService() {
        connection = DataSource.getInstance().getConnection();

    }


    @Override
    public void add(ImageG image) {
        try {
            String req = "insert into image(name,id_user,id_groupe,date_publication) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,image.getName());
            ps.setInt(2,image.getMembre().getId());
            ps.setInt(3,image.getGroupe().getId());
            ps.setDate(4, image.getDate_pub());
            ps.executeUpdate();
          
           


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(ImageG t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        
        try {
            String req = "delete from image where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ImageG> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImageG> getAllGroupImage(ImageG image) {
        
         List<ImageG> img = new ArrayList<>();
        try {
            
            String req = "select * from image where id_groupe =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,image.getGroupe().getId());
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 ImageG i = new ImageG(rs.getInt(1),rs.getString(2),rs.getDate(5),new Membre(rs.getInt(3)),new Groupe(rs.getInt(4)));
                 img.add(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return img;
    }

    @Override
    public ImageG getImage(int id) {
   
    ImageG i = new ImageG();
        try {
            String req = "select * from image where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                i.setId(rs.getInt(1));
                i.setName(rs.getString(2));
                i.setMembre(new Membre(rs.getInt(3)));
                i.setGroupe(new Groupe(rs.getInt(4)));
                i.setDate_pub(rs.getDate(5));


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return i;
    }

    @Override
    public List<ImageG> getImagewithId(int id_user, int id_group) {
        
          List<ImageG> img = new ArrayList<>();
        try {
            
            String req = "select * from image where id_groupe =? and id_user =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id_group);
            ps.setInt(2, id_group);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 ImageG i = new ImageG(rs.getInt(1),rs.getString(2));
                 img.add(i);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return img;
    }


    }

   
    

