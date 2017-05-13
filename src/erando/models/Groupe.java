/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.models;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author wassim
 */
public class Groupe {
    private int id;
    private String nom;
    private String description;
    private String photo_couverture;
    private Date date_creation;
    private Membre createur;
    private String membres;

      public Groupe() {
        }

    public Groupe(int id) {
        this.id = id;
    }

      
    public Groupe(String nom, String description, Membre id_createur) {
        this.nom = nom;
        this.description = description;
        this.createur = id_createur;
        this.date_creation = new Date(Calendar.getInstance().getTime().getTime());
        this.membres = "";
    }

    public Groupe(int id, String nom, String membres , String description, String photo_couverture,  Membre createur ,Date date_creation) {
        this.id = id;
        this.nom = nom;
        this.membres = membres;
        this.description = description;
        this.photo_couverture = photo_couverture;
        this.createur = createur;
        this.date_creation = date_creation;

    }
    
    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto_couverture() {
        return photo_couverture;
    }

    public void setPhoto_couverture(String photo_couverture) {
        this.photo_couverture = photo_couverture;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public Membre getCreateur() {
        return createur;
    }

    public void setCreateur(Membre createur) {
        this.createur = createur;
    }  

    public String getMembres() {
        return membres;
    }

    public void setMembres(String membres) {
        this.membres = membres;
    }

 

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
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
        final Groupe other = (Groupe) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", photo_couverture=" + photo_couverture + ", date_creation=" + date_creation + ", id_createur=" + createur + ", membres=" + membres + '}';
    }

  
   
    
    
    
    
}
