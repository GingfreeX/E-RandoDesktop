/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.controllers.Randonne;
import erando.services.interfaces.IRandonneService;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aloulou
 */
public class RandonneService implements IRandonneService {
 private Connection connection;
 
 public RandonneService(){
    connection = DataSource.getInstance().getConnection();
 }
    @Override
    public void add(Randonne t) {
       
          
        
        
        try {
            String req = "insert into randonne(titre, destination,date,prix,age_min,description,moyen_transport,type,plan,liste_inscrits,image,nbr_places,point_depart,niveau) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
           ps.setString(1, t.getTitre());
           ps.setString(2, t.getDestination());
           ps.setDate(3, t.getDate());
           ps.setDouble(4, t.getPrix());
           ps.setInt(5, t.getAgeMin());
           ps.setString(6, t.getDescription());
           ps.setString(7, t.getMoyenTransport());
           ps.setString(8, t.getType());
           ps.setString(9, t.getPlan());
           ps.setString(10, t.getListeInscrits());
           ps.setString(11, t.getImagepath());
           ps.setInt(12, t.getNbrePlace());
           ps.setString(13, t.getPointDepart());
           ps.setInt(14, t.getNiveau());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(Randonne t) {
 try {
            String req = "update randonne set titre=?, destination=?,date=?,prix=?,age_min=?,description=?,moyen_transport=?,type=?,plan=?,liste_inscrits=?,nbr_places=?,point_depart=?,niveau=?  where id = ?";
           
// String req = "update randonne set titre=?, destination=?,date=?,prix=?,age_min=?,description=?,moyen_transport=?,type=?,plan=?,liste_inscrits=?,nbr_places=?,point_depart=?,niveau=?  where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
           ps.setString(1, t.getTitre());
           ps.setString(2, t.getDestination());
           ps.setDate(3, t.getDate());
           ps.setDouble(4, t.getPrix());
           ps.setInt(5, t.getAgeMin());
           ps.setString(6, t.getDescription());
           ps.setString(7, t.getMoyenTransport());
           ps.setString(8, t.getType());
           ps.setString(9, t.getPlan());
           ps.setString(10, t.getListeInscrits());
           ps.setInt(11, t.getNbrePlace());
           ps.setString(12, t.getPointDepart());
           ps.setInt(13, t.getNiveau());
           
            ps.setInt(14, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public void delete(Integer id) {
         try {
            String req = "delete from randonne where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Randonne> getAll() {
 List<Randonne> randos = new ArrayList<>();
        try {
            String req = "select * from randonne";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              //  Randonne rando =new Randonne (rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getInt(4),rs.getString(5), rs.getDate(6),rs.getString(7),rs.getDouble(8),rs.getInt(9),rs.getString(10),rs.getString(11),rs.getInt(12),rs.getInt(13),rs.getString(14),rs.getString(15));
                //Randonne rando = new Randonne(rs.getInt(1),rs.getString(2),rs.getString(5), rs.getDate(6), rs.getDouble(8));
              // Randonne rando = new Randonne(rs.getInt(1),rs.getString(2),rs.getString(5), rs.getDate(6), rs.getDouble(8), rs.getInt(14),rs.getString(4), rs.getInt(15), rs.getString(12), rs.getString(16), rs.getString(7), rs.getString(12), rs.getInt(13),rs.getString(14), rs.getInt(15));
              Randonne rando = new Randonne(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getDate(6), rs.getDouble(8),rs.getString(9), rs.getInt(14), rs.getString(3), rs.getInt(4), rs.getString(15), rs.getString(12), rs.getString(16), rs.getString(7), rs.getInt(10), rs.getString(11), rs.getInt(13));
              randos.add(rando);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return randos;    }

    @Override
    public Randonne findById(Integer id) {
 Randonne rando = null;
        try {
            String req = "select * from randonne where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
   rando = new Randonne(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getDate(6), rs.getDouble(8), rs.getInt(14), rs.getString(3), rs.getInt(4), rs.getString(15), rs.getString(12), rs.getString(16), rs.getString(7), rs.getInt(10), rs.getString(11), rs.getInt(13));
             //   rando =new Randonne(rs.getInt(1),rs.getString(2),rs.getString(5), rs.getDate(6), rs.getDouble(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rando;    }
    
     public List<Randonne> rechercherannonceselontrajet(String depart, String arrivee, Date date) {
  String req = "select * from randonne where point_depart LIKE ? or destination LIKE ? or date LIKE ? ";
        List<Randonne> annonces = new ArrayList<>();
        try {
          PreparedStatement  ps = connection.prepareStatement(req);
            ps.setString(1, depart);
            ps.setString(2, arrivee);
            ps.setDate(3, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
 Randonne  rando = new Randonne(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getDate(6), rs.getDouble(8), rs.getInt(14), rs.getString(3), rs.getInt(4), rs.getString(15), rs.getString(12), rs.getString(16), rs.getString(7), rs.getInt(10), rs.getString(11), rs.getInt(13));
                annonces.add(rando);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annonces;   }
    
}
