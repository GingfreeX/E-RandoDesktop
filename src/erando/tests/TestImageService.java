/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.tests;

import erando.models.Groupe;
import erando.models.ImageG;
import erando.models.Membre;
import erando.services.impl.GroupeService;
import erando.services.impl.ImageService;
import erando.services.impl.MembreService;
import erando.services.interfaces.IMembreService;

/**
 *
 * @author wassim
 */
public class TestImageService {
    public static void main(String[] args) {
        ImageService im = new ImageService();
        GroupeService gs = new GroupeService();
        IMembreService s = new MembreService();
        Membre m = s.getUser(2);
        Groupe g = gs.getGroupe(3);
        //Image i = new Image("testimg2",g,m);
        //im.add(i);
       // im.delete(2);
       ImageG i = im.getImage(1);
        //System.out.println(im.getImagewithId(2,2));
       
       im.getAllGroupImage(i).forEach(System.out::println);
        
    }
    
}
