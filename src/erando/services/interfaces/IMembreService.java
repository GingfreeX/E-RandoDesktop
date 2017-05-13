/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import erando.models.Groupe;
import erando.models.Membre;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wassim
 */
public interface IMembreService  extends IGroupService<Membre, Integer>{
    Membre getUser(int id);
    boolean existemembre( int idmembre);
    Map<Integer,String> getUsernamesWithId (String list_amis);
    List<String> getUsernameOnly(Map<Integer,String> m);
    int getUsernameId(Map<Integer,String> m , String username);
    
}
