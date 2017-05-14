/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.Groupe;
import erando.models.Membre;
import erando.services.interfaces.IMembreService;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wassim
 */
public class MembreService implements IMembreService {

    private Connection connection;

    public MembreService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Membre membre) {
        try {
            String req = "insert into member (username,username_canonical,email,email_canonical) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, membre.getUsername());
            ps.setString(2, membre.getUsername());
            ps.setString(3, membre.getEmail());
            ps.setString(4, membre.getEmail());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(Membre t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        
           try {
            String req = "delete from member where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Membre> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Membre getUser(int id) {
        Membre m = new Membre();
        try {
            String req = "select * from member where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m.setId(rs.getInt(1));
                m.setEmail(rs.getString(2));
                m.setUsername(rs.getString(3));
                m.setProfil_pic(rs.getString(23));
                m.setListamis(rs.getString(22));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return m;
    }

    @Override
    public Map<Integer, String> getUsernamesWithId(String list_amis) {
        Map<Integer, String> ls = new HashMap<>();
        if(!(list_amis == ""|| list_amis == null)){
            String[] idmember = list_amis.split("/");
            for (String i : idmember) {

                ls.put(getUser(Integer.parseInt(i)).getId(), getUser(Integer.parseInt(i)).getUsername());

            }
        }
        return ls;
    }

    @Override
    public List<String> getUsernameOnly(Map<Integer, String> m) {
        List<String> ls = new ArrayList<>();
        for (String i : m.values()) {
            ls.add(i);
        }
        return ls;
    }

    @Override
    public int getUsernameId(Map<Integer, String> m, String username) {
        for (Map.Entry entry : m.entrySet()) {
            if (Objects.equals(username, entry.getValue())) {
                return (int) entry.getKey();
            }
        }
        return 0;
    }

  
    @Override
    public boolean existemembre(int idmembre) {

        boolean test = false;
        try {
            String req = "select * from member where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,idmembre);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
       
                        if(rs.getInt(1) == idmembre){
                            test = true;
                        
                     
                    
                }
                

            }
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return test;
            
    }

}
