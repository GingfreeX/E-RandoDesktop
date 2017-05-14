/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.tests;

import erandopi.services.interfaces.IService;
import erandopi.models.Membre;
import erandopi.services.impl.MembreService;
/**
 *
 * @author student1
 */
public class TestMembreService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Membre member = new Membre("khaoulaaa","jemai","rando");
        MembreService MembreService = new MembreService();
       // MembreService.add(member);
                //    MembreService.getAll().forEach(System.out::println);
        // MembreService.delete(1);
       //member.setDescription("jemai");
      // member.setId(25);
      //MembreService.update(member);
    }
    
}
