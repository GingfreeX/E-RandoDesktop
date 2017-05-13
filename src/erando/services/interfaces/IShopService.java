/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Ging
 */
public interface IShopService <T, R> {

    void add(T t);

    void update(T t);

    void delete(R id);
    
    List<T> getAll();
    List<T> getTitles();
    List<T> getOwn(int id);
    List<T> find(String toFind);
    T findById(R id);
    T findByTitle(String T);
    void like(int idProd,int idMembre);
    void dislike(int idProd,int idMembre);
    void subscribe(int idMembre ,String email , String type);
    void unsubscribe(int idMembre , String type);
    List<String> getSubscribes(String type);
    boolean checkifLiked(int idProd,int idMembre);
    boolean checkifSubscribed(String Type,int idMembre);
    int countLikes(int idProd);
    int count();
}
