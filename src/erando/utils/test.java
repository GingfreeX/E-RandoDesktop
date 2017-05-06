/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.utils;

import erando.models.Randonne;
import erando.services.impl.RandonneService;
import erando.services.interfaces.IRandonneService;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author aloulou
 */
public class test {
     public static void main(String[] args) throws SQLException {
             Connection conn = DataSource.getInstance().getConnection();
             IRandonneService rando =new RandonneService();
             System.out.println(rando.getAll());

     
     }
}
