/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.Commentaire;
import erando.models.Membre;
import erando.models.Publication;
import erando.services.interfaces.ICommentaireService;
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
 * @author wassim
 */
public class CommentaireService implements ICommentaireService{

      private Connection connection;

    public CommentaireService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Commentaire comment) {
        try {
            String req = "insert into comment (text,id_user,id_pub,date_pub)  values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, comment.getCommenttxt());
            ps.setInt(2, comment.getUser().getId());
            ps.setInt(3, comment.getPub().getId());
            ps.setDate(4, comment.getDate_pub());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Commentaire comment) {
        try {
            String req = "update comment set text = ?, date_pub =?  where id_user = ? and id_pub =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, comment.getCommenttxt());
            ps.setDate(2, comment.getDate_pub());
            ps.setInt(3, comment.getUser().getId());
            ps.setInt(4, comment.getPub().getId());
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
  try {
            String req = "delete from comment where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

  

    @Override
    public Commentaire getCommentaireById(int id) {
Commentaire c = new Commentaire();
        try {
            String req = "select * from comment where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt(1));
                c.setCommenttxt(rs.getString(2));
                c.setUser(new Membre(rs.getInt(3)));
                c.setPub(new Publication(rs.getInt(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return c;
    }

    @Override
    public List<Commentaire> getAllCommentByPub(int idpub) {
          List<Commentaire> cmt = new ArrayList<>();
        try {

            String req = "select * from comment where id_pub = ? order by date_pub DESC";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idpub);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Commentaire c = new Commentaire(rs.getInt(1), rs.getString(2), new MembreService().getUser(rs.getInt(3)),rs.getDate(5));
                cmt.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cmt;
    }

    @Override
    public List<Commentaire> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
