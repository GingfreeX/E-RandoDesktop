/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import erando.models.Groupe;
import erando.models.Publication;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface IPublicationGroupService extends IGroupService<Publication, Integer>{
    
   Publication getPubById(int id);
   List<Publication> getPubOrderByDate(Groupe groupe);
   void updateNbrLike(Publication publication);

    
}
