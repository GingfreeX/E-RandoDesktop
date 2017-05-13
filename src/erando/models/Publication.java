/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.models;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author wassim
 */
public class Publication {

    private int id;
    private String description;
    private Date date_publication;
    private Membre createur;
    private Groupe groupe;
    private String photo;
    private int nbrjaime;

    public Publication() {

    }

    public Publication(int id) {
        this.id = id;
    }
    

    public Publication(int id, String description, Date date_publication, Membre createur,String photo) {
        this.id = id;
        this.description = description;
        this.date_publication = date_publication;
        this.createur = createur;
        this.photo = photo;
    }   

    public Publication(int id, String description, Date date_publication, Membre createur) {
        this.id = id;
        this.description = description;
        this.date_publication = date_publication;
        this.createur = createur;
    }

    
    public Publication(String description, Date date_publication, Membre createur, Groupe groupe) {

        this.description = description;
        this.date_publication = date_publication;
        this.createur = createur;
        this.groupe = groupe;
    }

    public Publication(String description, Membre createur, Groupe groupe, String photo) {
        this.description = description;
        this.createur = createur;
        this.groupe = groupe;
        this.date_publication = new Date(Calendar.getInstance().getTime().getTime());
        this.photo = photo;
    }

    public Publication(String description, Date date_publication) {
        this.description = description;
        this.date_publication = date_publication;
    }

    public Publication(String description, Date date_publication, Membre createur) {
        this.description = description;
        this.date_publication = date_publication;
        this.createur = createur;
    }

    public Publication(String description, Membre createur) {
        this.description = description;
        this.createur = createur;
        this.date_publication = new Date(Calendar.getInstance().getTime().getTime());

    }

    public Publication(int id, String description, Date date_publication, Membre createur, String photo, int nbrjaime) {
        this.id = id;
        this.description = description;
        this.date_publication = date_publication;
        this.createur = createur;
        this.photo = photo;
        this.nbrjaime = nbrjaime;
    }

  

    public int getNbrjaime() {
        return nbrjaime;
    }

    public void setNbrjaime(int nbrjaime) {
        this.nbrjaime = nbrjaime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public Membre getCreateur() {
        return createur;
    }

    public void setCreateur(Membre createur) {
        this.createur = createur;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
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
        final Publication other = (Publication) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", description=" + description + ", date_publication=" + date_publication + ", createur=" + createur + ", groupe=" + groupe + ", photo=" + photo + ", nbrjaime=" + nbrjaime + '}';
    }

 

   

}
