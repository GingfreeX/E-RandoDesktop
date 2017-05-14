/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import erando.models.Groupe;
import erando.models.Membre;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface IGroupeService extends IService<Groupe, Integer>{
    Groupe getGroupe(int id); 
    void addmembre(Groupe groupe,int idmembre);
    void deletemembre(Groupe groupe,int idmembre);
    boolean existemembre(Groupe groupe , int idmembre);
    int getGroupeMemberNumber(Groupe groupe);
    List<String> getGroupeMemberPhoto(Groupe groupe);
    List<Membre> getGroupMembers(Groupe groupe);

    
}
