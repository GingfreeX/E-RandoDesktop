/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.models;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author wassim
 */
public class ImageG {
    private int id;
    private String name;
    private Date date_pub;
    private Groupe groupe;
    private Membre membre;

    public ImageG() {
    }

    public ImageG(int id) {
        this.id = id;
    }

    public ImageG(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    

    public ImageG(String name) {
        this.name = name;
    }

    public ImageG(String name, Groupe groupe) {
        this.name = name;
        this.groupe = groupe;
    }

    public ImageG(String name, Groupe groupe, Membre membre) {
        this.name = name;
        this.groupe = groupe;
        this.membre = membre;
        this.date_pub = new Date(Calendar.getInstance().getTime().getTime());
    }

    public ImageG(int id, String name, Date date_pub, Membre membre,Groupe groupe) {
        this.id = id;
        this.name = name;
        this.date_pub = date_pub;
        this.membre = membre;
        this.groupe = groupe;
    }

    
    

    public ImageG(Groupe groupe) {
        this.groupe = groupe;
    }
    
    
    

    public Date getDate_pub() {
        return date_pub;

    }

    public void setDate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.id;
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
        final ImageG other = (ImageG) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", name=" + name + ", date_pub=" + date_pub + ", groupe=" + groupe + ", membre=" + membre + '}';
    }

  
    
    
    
}
