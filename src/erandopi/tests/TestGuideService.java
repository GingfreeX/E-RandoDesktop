/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.tests;

import erandopi.models.Guide;
import erandopi.services.impl.GuideService;

/**
 *
 * @author student1
 */
public class TestGuideService {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        //Guide guide = new Guide("guide1","guide","guide@gmail.com","rando");
        GuideService GuideService = new GuideService();
        //GuideService.add(guide);
                  //  GuideService.getAll().forEach(System.out::println);
         GuideService.delete(1);
       //guide.setPrenom("jemai");
       //0guide.setId(25);
      //GuideService.update(guide);
    }
    
}
