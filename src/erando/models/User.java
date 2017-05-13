/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.models;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import erando.chat.client.Status;

/**
 *
 * @author cimope
 */
public class User implements Serializable{
    private int id ; 
    private String  nom ; 
    private String  prenom ;
    private int age;
    private String Descrption;
    private String password;
    private int enabled;
    private String Role ;
    private String ImagePath;
    private int numTel;
    private String listinvitation;
    private String listAmis;
    private String email; 
    private static int idofuserAlreadyloggedin;
    private static String myemail;
    private Status status;


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
         public User(int id ,String nom, String prenom, int age, String Descrption,int enabled ,String password, String email,String Role,String listeamis,String imagePath) {
        this.id=id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.Descrption = Descrption;
        this.enabled=enabled;
        this.password = password;
        this.email = email;
        this.Role=Role;
        this.listAmis=listeamis;
        this.ImagePath=imagePath;
       
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getListinvitation() {
        return listinvitation;
    }

    public void setListinvitation(String listinvitation) {
        this.listinvitation = listinvitation;
    }

    public String getListAmis() {
        return listAmis;
    }

    public void setListAmis(String listAmis) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }




    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", Descrption=" + Descrption + ", password=" + password + ", enabled=" + enabled + ", Role=" + Role + ", ImagePath=" + ImagePath + ", numTel=" + numTel + ", listinvitation=" + listinvitation + ", listAmis=" + listAmis + ", email=" + email + '}';
    }

   
   
    
}
