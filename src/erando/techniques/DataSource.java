/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.techniques;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wassim
 */
public class DataSource {
    
    private String url;
    private String login;
    private String password;
    private static DataSource dataSource;
    private Connection connection;
    
    
    private DataSource(){
    
        url="jdbc:mysql://localhost:3306/e-rando2";
        login="root";
        password="";
        
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
               ex.printStackTrace();
        }
        
    }

    public Connection getConnection() {
        return connection;
    }
    
     public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }
    
}
