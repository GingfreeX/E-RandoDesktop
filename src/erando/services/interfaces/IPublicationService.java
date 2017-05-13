/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import erando.models.PublicationGroup;

/**
 *
 * @author cimope
 */
public interface IPublicationService extends IService<PublicationGroup, Integer>{
 public PublicationGroup getPublicationById(Integer id);
}
