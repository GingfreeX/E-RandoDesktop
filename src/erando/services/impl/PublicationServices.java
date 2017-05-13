/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.PublicationGroup;
import erando.models.User;
import erando.services.interfaces.IPublicationService;

import erando.services.interfaces.IService;
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
 * @author cimope
 */
public class PublicationServices implements IPublicationService{
private Connection connection;
 public PublicationServices() {
    connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(PublicationGroup pub) {
        try{
            System.out.println(pub.getUser().getId());
            System.out.println(pub.getDescription());
            System.out.println(pub.getSection());
            System.out.println(pub.getDatepub());
             System.out.println(pub.getImagepath());
        String req = "INSERT INTO publication_e SET user_id=?,description=?,image=?,section=?,datepub=? ";
        PreparedStatement st = connection.prepareStatement(req);
       st.setInt(1, pub.getUser().getId());
       st.setString(2, pub.getDescription());
        if((pub.getImagepath())==null){
              st.setString(3, null);
        }else{
         st.setString(3, pub.getImagepath());
        }
        st.setString(4, pub.getSection());
        st.setDate(5, pub.getDatepub());
        st.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void update(PublicationGroup pub) {
             try{
        String req = "update publication_e SET user_id=?,description="+pub.getDescription()+",image=?,section=?,datepub=? where id = ?";
        PreparedStatement st = connection.prepareStatement(req);
        st.setInt(1, pub.getUser().getId());
        st.setString(2, pub.getDescription());
       if((pub.getImagepath())==null){
              st.setString(3, null);
        }else{
         st.setString(3, pub.getImagepath());
        }
        st.setString(4, pub.getSection());
        st.setDate(5, pub.getDatepub());
        st.setInt(6, pub.getId());
        st.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try{
     String req = "delete from publication_e  where id = ?";
        PreparedStatement st = connection.prepareStatement(req); 
         st.setInt(1, id);
         st.executeUpdate();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<PublicationGroup> getAll() {
         List<PublicationGroup> publications = new ArrayList<PublicationGroup>();
          try {
            String req = "select * from publication_e";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            
     PublicationGroup publication = new PublicationGroup(rs.getInt(1),rs.getString(3), rs.getString(5), rs.getDate(6),new UserServices().getUserbyId(rs.getInt(2)),rs.getString(4));
     publications.add(publication);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return publications;
    }

    @Override
    public PublicationGroup getPublicationById(Integer id) {
        
         PublicationGroup publication = null ;
        try{
    String req = "select * from publication_e  where id=? ";
   PreparedStatement st = connection.prepareStatement(req);
   st.setInt(1,id );
   ResultSet rs = st.executeQuery();
   
    while(rs.next()){
   publication = new PublicationGroup(rs.getInt(1),rs.getString(3), rs.getString(5), rs.getDate(6),new UserServices().getUserbyId(rs.getInt(2)));
         
    }
   }catch(Exception a){
        a.printStackTrace();
    }
       return publication; 
    }
    
    
}
