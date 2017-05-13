/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.models;

/**
 *
 * @author student1
 */
public class Guide {
    private int idguide;
    private String nom;
    private String prenom;
    private String email;
    private String statut;

    public Guide() {
    }

    public Guide(String nom, String prenom, String email, String statut) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.statut = statut;
    }

    public Guide(int idguide, String statut) {
        this.idguide = idguide;
        this.statut = statut;
    }

    public Guide(int idguide, String nom, String prenom, String email, String statut) {
        this.idguide = idguide;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.statut = statut;
    }

    public int getIdguide() {
        return idguide;
    }

    public void setIdguide(int idguide) {
        this.idguide = idguide;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Guide{" + "idguide=" + idguide + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", statut=" + statut + '}';
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
        final Guide other = (Guide) obj;
        if (this.idguide != other.idguide) {
            return false;
        }
        if (this.nom != other.nom) {
            return false;
        }
        if (this.prenom != other.prenom) {
            return false;
        }
        if (this.email != other.email) {
            return false;
        }
        if (this.statut != other.statut) {
            return false;
        }
        return true;
    }
    
}
