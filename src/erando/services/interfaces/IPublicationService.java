/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import erando.models.Publication;

/**
 *
 * @author cimope
 */
public interface IPublicationService extends IService<Publication, Integer>{
 public Publication getPublicationById(Integer id);
}
