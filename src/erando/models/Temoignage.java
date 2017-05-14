/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.models;

import java.util.Objects;

/**
 *
 * @author student1
 */
public class Temoignage {
    private int Id ;
    private String Membreid ;
    private String message ;

    public Temoignage() {
    }

    public Temoignage(int Id, String Membreid, String message) {
        this.Id = Id;
        this.Membreid = Membreid;
        this.message = message;
    }

    public Temoignage(String Membreid, String message) {
        this.Membreid = Membreid;
        this.message = message;
    }

    public Temoignage(String message) {
        this.message = message;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getMembreid() {
        return Membreid;
    }

    public void setMembreid(String Membreid) {
        this.Membreid = Membreid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.Id;
        hash = 89 * hash + Objects.hashCode(this.Membreid);
        hash = 89 * hash + Objects.hashCode(this.message);
        return hash;
    }

    

    @Override
    public String toString() {
        return "Temoignage{ message=" + message + '}';
    }
     
}
