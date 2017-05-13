/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import erando.models.messages;
import erando.services.interfaces.IService;
import erando.techniques.DataSource;
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
 * @author cimope
 */
public class messagesServices implements IService<messages, Integer>{
private Connection connection;
    public messagesServices(){
        connection = DataSource.getInstance().getConnection();
    }
    @Override
    public void add(messages message) {
        String req = "insert into belousovr_messages(author_id,addressee_id,messageText,reading) values (?,?,?,?)";
        PreparedStatement ps;
    try {
        ps = connection.prepareStatement(req);
         ps.setInt(1, message.getSender().getId());
         ps.setInt(2, message.getReciver().getId());
         ps.setString(3,message.getMessage());
         ps.setInt(4, 1);
         ps.executeUpdate();
    } catch (SQLException ex) {
     //   Logger.getLogger(messagesServices.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    }

    @Override
    public void update(messages t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<messages> getAll() {
        List<messages> listmess = new ArrayList<>();
                String req = "select * from belousovr_messages ";
        PreparedStatement ps;
    try {
        ps = connection.prepareStatement(req);
      
        ResultSet rs =   ps.executeQuery();
        while(rs.next()){
            messages messages = new messages(new UserServices().getUserbyId(rs.getInt(2)), new UserServices().getUserbyId(rs.getInt(3)), rs.getString(4));
                listmess.add(messages);
        }
    } catch (SQLException ex) {
     //   Logger.getLogger(messagesServices.class.getName()).log(Level.SEVERE, null, ex);
    }
    return listmess;
    }
    
}
