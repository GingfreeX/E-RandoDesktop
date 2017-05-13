/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import java.util.List;

/**
 *
 * @author wassim
 */
public interface IGroupService <T,R> {
    
    void add(T t);
    void update(T t);
    void delete(R id);
    List<T> getAll();
    
    
    
}
