/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.Randonne;
import erando.models.Reservation;
import erando.models.User;
import erando.services.interfaces.IReservationService;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amrouche
 */
public class ReservationService implements IReservationService{
private Connection connection;
    public ReservationService() {
          connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Reservation t) {

   try {
            String req = "insert into reservation(id_Randonne,id_user) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
          ps.setInt(1, t.getRandonne().getId());
          ps.setInt(2, t.getUser().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Reservation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
       try {
            String req = "delete from reservation where id_reservation =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public List<Reservation> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reservation findById(Integer id) {
Reservation rando = null;
        try {
            String req = "select * from reservation where id_reservation =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
rando = new Reservation(rs.getInt(1), new RandonneService().findById(rs.getInt(2)), finduserbyid(rs.getInt(3)));
                //   rando =new Randonne(rs.getInt(1),rs.getString(2),rs.getString(5), rs.getDate(6), rs.getDouble(8));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rando;    }
    
    
    public User finduserbyid(int id)
    {
     User user = null ;
        try{
    String req = "select * from user where id_user=? ";
   PreparedStatement st = connection.prepareStatement(req);
   st.setInt(1,id);
   ResultSet rs = st.executeQuery();
   
    while(rs.next()){
user = new User(rs.getInt(1),rs.getString(2));
    }
   }catch(Exception a){
        a.printStackTrace();
    }
       return user; 
    
    }
    
    public List<Reservation> findreservationbyuser(int id)
    {
        List<Reservation> randos = new ArrayList<>();
        try {
            String req = "select * from reservation where id_user=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
Reservation                rando = new Reservation(rs.getInt(1), new RandonneService().findById(rs.getInt(2)), finduserbyid(rs.getInt(3)));

              //  Randonne rando =new Randonne (rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getInt(4),rs.getString(5), rs.getDate(6),rs.getString(7),rs.getDouble(8),rs.getInt(9),rs.getString(10),rs.getString(11),rs.getInt(12),rs.getInt(13),rs.getString(14),rs.getString(15));
                //Randonne rando = new Randonne(rs.getInt(1),rs.getString(2),rs.getString(5), rs.getDate(6), rs.getDouble(8));
              // Randonne rando = new Randonne(rs.getInt(1),rs.getString(2),rs.getString(5), rs.getDate(6), rs.getDouble(8), rs.getInt(14),rs.getString(4), rs.getInt(15), rs.getString(12), rs.getString(16), rs.getString(7), rs.getString(12), rs.getInt(13),rs.getString(14), rs.getInt(15));
              //Randonne rando = new Randonne(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getDate(6), rs.getDouble(8), rs.getInt(14), rs.getString(3), rs.getInt(4), rs.getString(15), rs.getString(12), rs.getString(16), rs.getString(7), rs.getInt(10), rs.getString(11), rs.getInt(13));
              randos.add(rando);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return randos;  
    }
    
}
