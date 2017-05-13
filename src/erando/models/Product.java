/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.models;

/**
 *
 * @author Ging
 */
public class Product {
    private int id;
    private String titre;
    private String description;
    private String image;
    private String commentaires;
    private String type;
    private int idMembre;
    private String date;
    private int approved;
    private float rating;
    private float prix;
    
    /////////////////////////////////////////////
    ////            Constructors            /////
    ///////////////////////////////////////////// 
    public Product() {
    }

    public Product(String titre, String description, String image, String commentaires, String type, String date, int approved, float rating, float prix) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.commentaires = commentaires;
        this.type = type;
        this.date = date;
        this.approved = approved;
        this.rating = rating;
        this.prix = prix;
    }

    public Product(int id, String titre, String description,int idMember, float prix, String date, String type, float rating, String image, String commentaires, int approved) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.commentaires = commentaires;
        this.type = type;
        this.idMembre = idMember;
        this.date = date;
        this.approved = approved;
        this.rating = rating;
        this.prix = prix;
    }
   
    public Product(String titre, String description,int idMember, float prix, String date) {
        this.titre = titre;
        this.description = description;
        this.idMembre = idMember;
        this.date = date;
        this.prix = prix;
    }
    


    public Product(int id, String titre, String description, int idMembre, float prix, String type, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.idMembre = idMembre;
        this.type = type;
        this.prix = prix;
    }
 public Product(int id, String titre) {
        this.id = id;
        this.titre = titre;
      
    }
  public Product(String titre, String description,int idMembre, float prix, String type,String date,String imageName){
        this.titre = titre;
        this.description = description;
        this.idMembre = idMembre;
        this.type = type;
        this.prix = prix;
        this.date = date;
        this.image = imageName;
    }
   
    
     /////////////////////////////////////////////
    ////            get&set               /////
    /////////////////////////////////////////////
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdMember() {
        return idMembre;
    }

    public void setIdMember(int idMember) {
        this.idMembre = idMember;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    /////////////////////////////////////////////
    ////            overrides               /////
    /////////////////////////////////////////////
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", image=" + image + ", commentaires=" + commentaires + ", type=" + type + ", idMembre=" + idMembre + ", date=" + date + ", approved=" + approved + ", rating=" + rating + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id;
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
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

   
    
    
}
