/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.services.interfaces;

import java.util.List;

/**
 *
 * @author student1
 */
public interface IService<T, R> {

    void add(T t);
    
    void update(T t);

    void delete(R t);

    List<T> getAll();

    T findById(R id);  
}
