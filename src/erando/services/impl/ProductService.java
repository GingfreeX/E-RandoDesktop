/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.Product;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import erando.services.interfaces.IShopService;

/**
 *
 * @author Ging
 */
public class ProductService implements IShopService<Product, Integer> {
    private Connection connection;

    public ProductService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Product product) {
        try {
            String req = "insert into produit(titre,description,idMembre,prix,dateAdd,type,rating,imageName,commentaires,approved) values (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, product.getTitre());
            ps.setString(2, product.getDescription());
            ps.setInt(3,product.getIdMember());
            ps.setFloat(4,product.getPrix());
            ps.setString(5,product.getDate());
            ps.setString(6,product.getType());
            ps.setFloat(7,product.getRating());
            ps.setString(8,product.getImage());
            ps.setString(9,product.getCommentaires());
            ps.setInt(10,product.getApproved());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Product product) {
        try {
            String req = "update produit set titre=?,description=?,idMembre=?,prix=?,dateAdd=?,type=?,rating=?,imageName=?,commentaires=?,approved=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, product.getTitre());
            ps.setString(2, product.getDescription());
            ps.setInt(3,product.getIdMember());
            ps.setFloat(4,product.getPrix());
            ps.setString(5,product.getDate());
            ps.setString(6,product.getType());
            ps.setFloat(7,product.getRating());
            ps.setString(8,product.getImage());
            ps.setString(9,product.getCommentaires());
            ps.setInt(10,product.getApproved());
            ps.setInt(11,product.getId());
            System.err.println(ps);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }
    @Override
    public void like(int idProd, int idMembre) {
        try {
            String req = "insert into productlike(idProduit,idMembre) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idProd);
            ps.setInt(2, idMembre);
            System.err.println(ps);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
    }
    @Override
    public void dislike(int idProd, int idMembre) {
        try {
            String req = "delete from productlike where idProduit =? AND idMembre=? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idProd);
            ps.setInt(2, idMembre);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public boolean checkifLiked(int idProd, int idMembre) {
        int count = 0 ;
        try {
            String req = "select id from productlike where idProduit =? AND idMembre =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idProd);
            ps.setInt(2, idMembre);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
               count = rs.getInt(1);     
            }
             if (count>0){
                 System.err.println("product already liked !");
                 return true;
             }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;    
    }
    @Override
    public void subscribe(int idMembre ,String email , String type) {
        try {
            String req = "insert into subscribers(idMembre,type,emailMembre) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idMembre);
            ps.setString(2, type);
            ps.setString(3, email);
            System.err.println(ps);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public boolean checkifSubscribed(String Type,int idMembre) {
        int count = 0 ;
        try {
            String req = "select id from subscribers where type =? AND idMembre =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, Type);
            ps.setInt(2, idMembre);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
               count = rs.getInt(1);     
            }
             if (count>0){
                 System.err.println("user already subscribed !");
                 return true;
             }
        } catch (SQLException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;    
    }
     @Override
    public void unsubscribe(int idMembre , String type) {
        try {
            String req = "delete from subscribers where idMembre =? AND type=? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idMembre);
            ps.setString(2, type);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from produit where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList();
        try {
            String req = "select id,titre,description,idMembre,prix,dateAdd,imageName,type from produit";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"),rs.getString("titre"),rs.getString("description"),rs.getInt("idMembre"), rs.getFloat("prix"),rs.getString("type"),rs.getString("imageName"));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
     @Override
    public List<Product> getTitles() {
        List<Product> products = new ArrayList();
        try {
            String req = "select id,titre from produit";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"),rs.getString("titre"));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
     @Override
    public List<Product> getOwn(int id) {
        List<Product> products = new ArrayList();
        try {
            String req = "select id,titre from produit where idMembre="+id;
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"),rs.getString("titre"));
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }
    @Override
    public List<String> getSubscribes(String type) {
        List<String> subscribers = new ArrayList();
        try {
            String req = "select emailMembre from subscribers where type =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                subscribers.add(rs.getString("emailMembre"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subscribers;
    }
    @Override
    public List<Product> find(String toFind) {
        List<Product> products = new ArrayList();
        try {
            String req = "select id,titre,description,idMembre,prix,dateAdd,imageName from produit where titre LIKE ? or prix like ? ";       
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,'%'+toFind+'%');
            ps.setString(2,'%'+toFind+'%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("id"),rs.getString("titre"),rs.getString("description"),rs.getInt("idMembre"), rs.getFloat("prix"),rs.getString("dateAdd"),rs.getString("imageName"));
                products.add(product);
                System.err.println(products);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }    
    @Override
    public Product findById(Integer id) {
        Product product = new Product();   
        try {
            String req = "select * from produit where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getFloat(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getString(9),rs.getString(10),rs.getInt(11));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }
    @Override
    public Product findByTitle(String T) {
        Product product = new Product();   
        try {
            String req = "select * from produit where titre =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, T);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product = new Product(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4), rs.getFloat(5),rs.getString(6),rs.getString(7),rs.getFloat(8),rs.getString(9),rs.getString(10),rs.getInt(11));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return product;
    }
    
     @Override 
    public int count(){
        int total = 0 ;
        try {   
            String req = "select COUNT(*) from produit ";
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
    @Override 
    public int countLikes(int idProd){
        int total = 0 ;
        try {   
            String req = "select COUNT(*) from productlike where idProduit =? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,idProd);
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
