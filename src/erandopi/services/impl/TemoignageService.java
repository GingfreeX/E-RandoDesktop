/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.services.impl;

import erandopi.models.Temoignage;
import erandopi.services.interfaces.ITemoignageService;
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
public class TemoignageService implements ITemoignageService {

    private Connection connection;

    public TemoignageService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Temoignage t) {

        try {
            String req = "insert into temoignage(message) values (?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, t.getMessage());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(Temoignage t) {
        try {
            String req = "update temoignage set message = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, t.getMessage());
            ps.setInt(2, t.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update2(Temoignage p, Integer id) {
        String req = "UPDATE `temoignage`  SET `message`=? WHERE `id`=?";
        try {
            PreparedStatement ps = connection.prepareStatement(req);

            ps.setString(1, p.getMessage());

            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from temoignage where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Temoignage> getAll() {
        List<Temoignage> temoin = new ArrayList<>();
        try {
            String req = "select * from temoignage";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Temoignage t = new Temoignage(rs.getInt(1), rs.getString(2), rs.getString(3));
                temoin.add(t);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temoin;
    }

    @Override
    public Temoignage findById(Integer id) {
        Temoignage temoin = null;
        try {
            String req = "select * from temoignage where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Temoignage t = new Temoignage(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temoin;
    }

    public ResultSet consulter() {

        ResultSet result = null;
        String requete = "select * from temoignage ;";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            result = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(TemoignageService.class.getName()).log(Level.SEVERE, null, ex);

        }
        return result;

    }

    @Override
    public List<String> getAllTem() {
        List<String> temoin = new ArrayList<>();
        try {
            String req = "select message from temoignage";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Temoignage t = new Temoignage(rs.getString(1));
                temoin.add(t.getMessage());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temoin;
    }

}
