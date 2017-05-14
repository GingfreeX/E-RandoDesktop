/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.models;

import java.util.Objects;

/**
 *
 * @author student1
 */
public class Membre {
     private int Id;
    private String username ;
    private String username_canonical ;
    private String description;

   
    public Membre() {
    }
    
 public Membre(String description, String username) {
        this.description = description;
        this.username = username;
    }
    public Membre(String username, String username_canonical, String description) {
        this.username = username;
        this.username_canonical = username_canonical;
        this.description = description;
    }

    public Membre(int Id, String username, String username_canonical, String description) {
        this.Id = Id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.description = description;
    }

    public Membre(int Id) {
        this.Id = Id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Membre{" + "Id=" + Id + ", username=" + username + ", username_canonical=" + username_canonical + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.Id;
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.username_canonical);
        hash = 53 * hash + Objects.hashCode(this.description);
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
        final Membre other = (Membre) obj;
        return true;
    }
            
}
