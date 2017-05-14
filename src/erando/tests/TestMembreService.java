/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.tests;

import erando.models.Groupe;
import erando.models.Membre;
import erando.services.impl.GroupeService;
import erando.services.impl.MembreService;
import erando.services.interfaces.IMembreService;
import erando.services.interfaces.IService;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 *
 * @author wassim
 */
public class TestMembreService {
    public static void main(String[] args) {
       // Membre m = new Membre("ali", "alouou.benhassen@esprit.tn");
        IMembreService s = new MembreService();
        Membre m = s.getUser(2);
         GroupeService gs = new GroupeService();
         Groupe g = gs.getGroupe(2);
        //s.delete(m.getId());
        Map<Integer,String> ma = s.getUsernamesWithId(m.getListamis());
        s.getUsernameOnly(ma).forEach(System.out::println);
        //System.out.println(ma);

       // System.out.println(s.getUser(3));
        //if (s.existemembre(3)) {
        //    System.out.println("existe");
       // }
       // else System.out.println("nexiste pas");
        
    
    }
           
    
}
