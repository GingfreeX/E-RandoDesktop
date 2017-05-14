/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.models;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 *
 * @author cimope
 */
public class User {
    private int id ; 
    private String  nom ; 
    private String  prenom ;
    private int age;
    private String Descrption;
    private String password;
    private String enabled;
    private String Role ;
    private String ImagePath;
    private int numTel;
    private ArrayList<User> listinvitation;
    private ArrayList<User> listAmis;
    private String email; 
    private static int idofuserAlreadyloggedin;
    private static String myemail;

    public User() {
    }

    public User(String name, String email) {
        this.nom = name;
        this.email = email;
    }


    public User(String name, String password, String email,String Role) {
        this.nom = name;
        this.password = password;
        this.email = email;
        this.Role=Role;
    }

     public User(String name, String password, String email) {
        this.nom = name;
        this.password = password;
        this.email = email;
        
    }
    public User(String nom, String prenom, int age, String Descrption, String password, String email,int numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.Descrption = Descrption;
        this.password = password;
        this.email = email;
        this.numTel=numtel;
    }
 
      public User(int id ,String nom, String prenom, int age, String Descrption, String password, String email,String Role,int numTel) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.Descrption = Descrption;
        this.password = password;
        this.email = email;
        this.Role=Role;
        this.numTel=numTel;
    }
    public User(int id ,String nom, String prenom, int age, String Descrption, String password, String email,String Role,String imagePath) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.Descrption = Descrption;
        this.password = password;
        this.email = email;
        this.Role=Role;
        this.ImagePath=imagePath;
       
    }
    public User(int id ,String nom, String prenom,String email) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
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

    public void setName(String name) {
        this.nom = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public ArrayList<User> getListinvitation() {
        return listinvitation;
    }

    public void setListinvitation(ArrayList<User> listinvitation) {
        this.listinvitation = listinvitation;
    }

    public ArrayList<User> getListAmis() {
        return listAmis;
    }

    public void setListAmis(ArrayList<User> listAmis) {
        this.listAmis = listAmis;
    }

    public static int getIdofuserAlreadyloggedin() {
        return idofuserAlreadyloggedin;
    }

    public static void setIdofuserAlreadyloggedin(int idofuserAlreadyloggedin) {
        User.idofuserAlreadyloggedin = idofuserAlreadyloggedin;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescrption() {
        return Descrption;
    }

    public void setDescrption(String Descrption) {
        this.Descrption = Descrption;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public static String getMyemail() {
        return myemail;
    }

    public static void setMyemail(String myemail) {
        User.myemail = myemail;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }
 
    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", Descrption=" + Descrption + ", password=" + password + ", enabled=" + enabled + ", listinvitation=" + listinvitation + ", listAmis=" + listAmis + ", email=" + email + '}';
    }
   
    
}
