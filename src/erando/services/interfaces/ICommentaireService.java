/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import erando.models.Commentaire;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface ICommentaireService extends IGroupService<Commentaire, Integer>{
    Commentaire getCommentaireById(int id);
    List<Commentaire> getAllCommentByPub(int idpub);
}
