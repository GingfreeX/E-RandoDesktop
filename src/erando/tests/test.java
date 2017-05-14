/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.tests;

import erando.models.Randonne;
import erando.services.impl.RandonneService;
import erando.services.interfaces.IRandonneService;
import java.sql.Date;
import static javafx.application.Application.launch;

/**
 *
 * @author amrouche
 */
public class test {
      public static void main(String[] args) {
          IRandonneService rando =new RandonneService();
          Date d = new Date(1999,01,20);
          System.out.println(rando.getAll());
          Randonne rando1=new Randonne(33,"aaaaaaaaaaaaaa","sdfsdfdsfsdf",d, 0, 0, "qfsfdsf", 0, "sdfsfdsfsd", "svdsvds", "svdsvs", "sezfezfz", 0,"dsvdsvsdv", 0);
   rando.update(rando1);
      }
    
}
