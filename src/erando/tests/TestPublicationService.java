/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.tests;

import erando.models.Groupe;
import erando.models.Membre;
import erando.models.Publication;
import erando.services.impl.GroupeService;
import erando.services.impl.MembreService;
import erando.services.impl.PublicationService;
import erando.services.interfaces.IMembreService;
import erando.services.interfaces.IPublicationService;

/**
 *
 * @author wassim
 */
public class TestPublicationService {
    public static void main(String[] args) {
          IMembreService s = new MembreService();
         Membre m = s.getUser(5);
         GroupeService gs = new GroupeService();
         Groupe g = gs.getGroupe(3);
       // Publication p = new Publication("What a wonderful adventure today friends:D",m,g);
        IPublicationService ps = new PublicationService();
        ps.getPubOrderByDate(g).forEach(System.out::println);
       // ps.add(p);
        
       // ps.getPubOrderByDate(g);
       // System.out.println(ps.getPubOrderByDate(g));
       // Publication p = ps.getPubById(1);
       // p.setDescription("post updated");
       // System.out.println(p);
       // ps.update(p);
      //ps.delete(1);
     //ps.getAll().forEach(System.out::println);
     
       // ps.getPubByOrderByDate().forEach(System.out::println);
    }
    
}
