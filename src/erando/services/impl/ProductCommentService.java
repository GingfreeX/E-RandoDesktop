/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.Product;
import erando.models.ProductComment;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import erando.services.interfaces.IShopService;

/**
 *
 * @author Ging
 */
public class ProductCommentService implements IShopService<ProductComment, Integer> {
     private Connection connection;

    public ProductCommentService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(ProductComment productComment) {
        try {
            String req = "insert into productComment(productid,userid,description,date) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, productComment.getIdProduct());
            ps.setInt(2, productComment.getIdMember());
            ps.setString(3,productComment.getDescription());
            ps.setString(4,productComment.getDate());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void like(int idProd, int idMembre) {
     throw new UnsupportedOperationException("Not supported yet in this class."); 
    }
    @Override
    public void dislike(int idProd, int idMembre) {
     throw new UnsupportedOperationException("Not supported yet in this class."); 
    }
    @Override
    public boolean checkifLiked(int idProd, int idMembre) {
     throw new UnsupportedOperationException("Not supported yet in this class."); 
    }
    @Override
    public int countLikes(int idProd) {
     throw new UnsupportedOperationException("Not supported yet in this class."); 
    }
    @Override
    public void subscribe(int idMembre ,String email , String type) {
     throw new UnsupportedOperationException("Not supported yet in this class."); 
    }
    @Override
    public void unsubscribe(int idMembre , String type) {
     throw new UnsupportedOperationException("Not supported yet in this class."); 
    }
    @Override
    public boolean checkifSubscribed(String Type,int idMembre) {
     throw new UnsupportedOperationException("Not supported yet in this class."); 
    }
     @Override
    public List<String> getSubscribes(String Type) {
     throw new UnsupportedOperationException("Not supported yet in this class."); 
    }
    
    @Override
    public void update(ProductComment productComment) {
        try {
            String req = "update productComment set (productid,userid,description,date) values (?,?,?,?) where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, productComment.getIdProduct());
            ps.setInt(2, productComment.getIdMember());
            ps.setString(3,productComment.getDescription());
            ps.setString(4,productComment.getDate());
            ps.setInt(5,productComment.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from productComment where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ProductComment> getAll() {
        ObservableList<ProductComment> comments = FXCollections.observableArrayList();
        try {
            String req = "select * from productcomment";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductComment comment = new ProductComment(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                comments.add(comment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }
     @Override
    public ObservableList<ProductComment> getTitles() {
        ObservableList<ProductComment> comments = FXCollections.observableArrayList();
        try {
            String req = "select * from productcomment";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductComment comment = new ProductComment(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                comments.add(comment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }
    @Override
    public ObservableList<ProductComment> find(String toFind) {
        ObservableList<ProductComment> comments = FXCollections.observableArrayList();
        try {
            String req = "select * from productcomment";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductComment comment = new ProductComment(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                comments.add(comment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }
     @Override
    public List<ProductComment> getOwn(int id) {
        ObservableList<ProductComment> comments = FXCollections.observableArrayList();
        try {
            String req = "select * from productcomment where productid="+id;
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductComment comment = new ProductComment(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5));
                comments.add(comment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return comments;
    }

    @Override
    public ProductComment findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     @Override
    public ProductComment findByTitle(String T) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override 
    public int count(){
        int total = 0 ;
        try {   
            String req = "select COUNT(*) from productcomment ";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet  rs = ps.executeQuery();
            rs.next();
            total = rs.getInt(1);
            System.out.println(total);
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return total;
    }
    
    
}
