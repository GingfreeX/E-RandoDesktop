/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.services.interfaces;

import erandopi.models.Temoignage;
import java.util.List;

/**
 *
 * @author student1
 */
public interface ITemoignageService extends IService<Temoignage, Integer> {
    List<String> getAllTem();
  // update2(Temoignage p, Integer id);
}
