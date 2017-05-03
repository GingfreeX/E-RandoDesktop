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
public class ProductComment {
    private int id;
    private int idProduct;
    private int userId;
    private String description;
    private String date;
    
    /////////////////////////////////////////////
    ////            Constructors            /////
    ///////////////////////////////////////////// 

    public ProductComment() {
    }

    public ProductComment(int id, int idProduct, int idMember, String description, String date) {
        this.id = id;
        this.idProduct = idProduct;
        this.userId = idMember;
        this.description = description;
        this.date = date;
    }

    public ProductComment(int idProduct, int userId, String description, String date) {
        this.idProduct = idProduct;
        this.userId = userId;
        this.description = description;
        this.date = date;
    }
    
    /////////////////////////////////////////////
    ////            get&set                 /////
    ///////////////////////////////////////////// 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdMember() {
        return userId;
    }

    public void setIdMember(int idMember) {
        this.userId = idMember;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    /////////////////////////////////////////////
    ////            overrides               /////
    ///////////////////////////////////////////// 

    @Override
    public String toString() {
        return "ProductComment{" + "id=" + id + ", idProduct=" + idProduct + ", userId=" + userId + ", description=" + description + ", date=" + date + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final ProductComment other = (ProductComment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
