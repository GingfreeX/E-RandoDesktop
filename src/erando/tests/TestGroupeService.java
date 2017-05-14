/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.tests;

import erando.models.Groupe;
import erando.models.Membre;
import erando.services.impl.GroupeService;

/**
 *
 * @author wassim
 */
public class TestGroupeService {
    public static void main(String[] args) {
        
        Groupe g = new Groupe("groupe2","test2",new Membre(5));
        GroupeService gs = new GroupeService();
        // Groupe g = gs.getGroupe(2);
         
        // g.setPhoto_couverture("tes");
        // gs.update(g);
         //gs.addmembre(g,5);
        //gs.deletemembre(g, 5);
        //System.out.println(gs.getGroupeMemberNumber(g));
       // gs.getGroupMembers(g).forEach(System.out::println);
        
        
       // gs.getGroupeMemberPhoto(g).forEach(System.out::println);
      // gs.getGroupMembers(g).forEach(System.out::println);
     //   gs.deletemembre(g,7);
        //gs.add(g);
        //System.out.println(gs.getGroupe(2));
       // System.out.println(gs.getGroupeMemberNumber(g));
       // System.out.println(g);
        //g.setPhoto_couverture("photo11");
       // System.out.println(gs.existemembre(g,9));
       // gs.deletemembre(g,9);

        
    }
    
}
