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
public class Commentaire {
    
    private int id;
    private String commenttxt;
    private Membre user;
    private PublicationGroup pub;
    private Date date_pub;

    public Commentaire() {
    }

    public Commentaire(String commenttxt, Membre user, PublicationGroup pub) {
        this.commenttxt = commenttxt;
        this.user = user;
        this.pub = pub;
        this.date_pub = new Date(Calendar.getInstance().getTime().getTime());
    }

    public Commentaire(int id, String commenttxt, Membre user, Date date_pub) {
        this.id = id;
        this.commenttxt = commenttxt;
        this.user = user;
        this.date_pub = date_pub;
    }

    
    public Commentaire(int id, String commenttxt, Membre user, PublicationGroup pub) {
        this.id = id;
        this.commenttxt = commenttxt;
        this.user = user;
        this.pub = pub;
    }

    public Commentaire(int id, String commenttxt) {
        this.id = id;
        this.commenttxt = commenttxt;
    }

    public Commentaire(int id, String commenttxt, Membre user) {
        this.id = id;
        this.commenttxt = commenttxt;
        this.user = user;
    }

    public Commentaire(int id, String commenttxt, PublicationGroup pub) {
        this.id = id;
        this.commenttxt = commenttxt;
        this.pub = pub;
    }

    public Date getDate_pub() {
        return date_pub;
    }

    public void setDate_pub(Date date_pub) {
        this.date_pub = date_pub;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommenttxt() {
        return commenttxt;
    }

    public void setCommenttxt(String commenttxt) {
        this.commenttxt = commenttxt;
    }

    public Membre getUser() {
        return user;
    }

    public void setUser(Membre user) {
        this.user = user;
    }

    public PublicationGroup getPub() {
        return pub;
    }

    public void setPub(PublicationGroup pub) {
        this.pub = pub;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Commentaire other = (Commentaire) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", commenttxt=" + commenttxt + ", user=" + user + ", pub=" + pub + ", date_pub=" + date_pub + '}';
    }

   
      
}
