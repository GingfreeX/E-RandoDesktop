/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.tests;

import erandopi.models.Temoignage;
import erandopi.services.impl.TemoignageService;
import erandopi.services.interfaces.IService;
/**
 *
 * @author student1
 */
public class erandopiTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              Temoignage temoignage = new Temoignage(10,"2222", "khaoula");
        TemoignageService TemoignageService = new TemoignageService();
        TemoignageService.add(temoignage);

          //  TemoignageService.getAll().forEach(System.out::println);
         //  TemoignageService.delete(1);
     //  temoignage.setMessage("aaaaa");
    //   temoignage.setId(2);
    //  TemoignageService.update(temoignage);
    }
    
}
