    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.Groupe;
import erando.models.Membre;
import erando.services.interfaces.IGroupeService;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wassim
 */
public class GroupeService implements IGroupeService{
    private Connection connection;

    public GroupeService() {
        connection = DataSource.getInstance().getConnection();
    }
    
    

    @Override
    public void add(Groupe groupe) {
        try {
            String req = "insert into groupe(nom,description,membres,id_createur,date_creation) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,groupe.getNom());
            ps.setString(2,groupe.getDescription());
            ps.setString(3,groupe.getMembres()+groupe.getCreateur().getId()+"-");
            ps.setInt(4,groupe.getCreateur().getId());
            ps.setDate(5,groupe.getDate_creation());
            ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Groupe groupe) {

        try {
            String req = "update groupe set nom = ? , description = ?, photoCouverture = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,groupe.getNom());
            ps.setString(2,groupe.getDescription());
            ps.setString(3,groupe.getPhoto_couverture());
            ps.setInt(4,groupe.getId());
            ps.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Groupe> getAll() {
        List<Groupe> groups = new ArrayList<>();
        try {
            
            String req = "select * from groupe";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                 Groupe groupe = new Groupe(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),new Membre(rs.getInt(6)),rs.getDate(8));
                 groups.add(groupe);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groups;
    }

    @Override
    public Groupe getGroupe(int id) {
    Groupe g  = new Groupe();
        try {
            String req = "select * from groupe where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                g.setId(rs.getInt(1));
                g.setNom(rs.getString(2));
                g.setMembres(rs.getString(3));
                g.setDescription(rs.getString(4));
                g.setPhoto_couverture(rs.getString(5));
                g.setCreateur(new Membre(rs.getInt(6)));
                g.setDate_creation(rs.getDate(8));


            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return g;
    }

    @Override
    public void addmembre(Groupe groupe,int idmembre) {
        try {
            String req = "update groupe set membres = ? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1,groupe.getMembres()+idmembre+"-");
            ps.setInt(2,groupe.getId());
            ps.executeUpdate();
            
            String req2 ="insert into users_groups(member_id,groupe_id)values (?,?)";
            PreparedStatement ps2 = connection.prepareStatement(req2);
            ps2.setInt(1,idmembre);
            ps2.setInt(2,groupe.getId());
            ps2.executeUpdate();



        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }

    @Override
    public void deletemembre(Groupe groupe, int idmembre) {
      
            //manque la partie de suppression de la liste des membres
           try {
            String req = "delete from users_groups where member_id = ? and groupe_id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,idmembre);
            ps.setInt(2, groupe.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            
          
        
    }

    @Override
    public boolean existemembre(Groupe groupe, int idmembre) {
        boolean test = false;
        try {
            String req = "select member_id from users_groups where groupe_id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,groupe.getId());
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

    @Override
    public int getGroupeMemberNumber(Groupe groupe) {
        int nb = 0;
          try {
            String req = "select count(member_id) from users_groups where groupe_id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,groupe.getId());
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                nb = rs.getInt(1);


            }
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          return nb;
    }

    @Override
    public List<String> getGroupeMemberPhoto(Groupe groupe) {
        List<String> lspic = new ArrayList<>();
          try {
            String req = "select (member_id) from users_groups where groupe_id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,groupe.getId());
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                    String req2 = "select (profile_pic) from member where id =?";
                    PreparedStatement ps2 = connection.prepareStatement(req2);
                    ps2.setInt(1,rs.getInt(1));
                    ResultSet rs2 = ps2.executeQuery();
                    
             while (rs2.next()) {
                            lspic.add(rs2.getString(1));
                            }
                    

                       
                }
             
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          return lspic;
        
    }

    @Override
    public List<Membre> getGroupMembers(Groupe groupe) {
        
         List<Membre> lsm = new ArrayList<>();
          try {
            String req = "select (member_id) from users_groups where groupe_id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,groupe.getId());
            ResultSet rs = ps.executeQuery();
             while (rs.next()) {
                    String req2 = "select * from member where id =?";
                    PreparedStatement ps2 = connection.prepareStatement(req2);
                    ps2.setInt(1,rs.getInt(1));
                    ResultSet rs2 = ps2.executeQuery();
                    
                    while (rs2.next()) {
                        Membre m = new Membre();
                        m.setId(rs2.getInt(1));
                        m.setUsername(rs2.getString(3));
                        m.setProfil_pic(rs2.getString(23));
                        m.setNom(rs2.getString(15));
                        m.setPrenom(rs2.getString(14));
                        m.setAge(rs2.getInt(13));
                        m.setEmail(rs2.getString(4));
                        m.setPays(rs2.getString(17));
                                   lsm.add(m);
                                   }


                       
                }
             
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
          return lsm;
        
        
    }
    
}
