/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.controllers;

import erando.models.User;
import java.util.Objects;

/**
 *
 * @author amrouche
 */
public class Reservation {
    private int  id;
    private Randonne randonne ;
    private User user ;

    public Reservation() {
    }

    public Reservation(Randonne randonne, User user) {
        this.randonne = randonne;
        this.user = user;
    }

    public Reservation(int id, Randonne randonne, User user) {
        this.id = id;
        this.randonne = randonne;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Randonne getRandonne() {
        return randonne;
    }

    public void setRandonne(Randonne randonne) {
        this.randonne = randonne;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.randonne);
        hash = 97 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.randonne, other.randonne)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", randonne=" + randonne + ", user=" + user + '}';
    }
    
    
}
