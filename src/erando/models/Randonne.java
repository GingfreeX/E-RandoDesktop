/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.models;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author aloulou
 */
public class Randonne {
    private int Id ;
    private String Titre ;
    private String Description ;
    private int IdGuide ;
    private String Destination ;
    private Date Date ;
    private String ListeInscrits ;
    private int NbrePlace ;
    private String PointDepart ;
    private String Type ;
    private int Niveau ;
    private int AgeMin ;
    private String MoyenTransport ;
    private String Plan ;
    private double Prix ;
    private String imagepath;

    public Randonne() {
    }

    
    
    
    
    public Randonne(String Titre, String Destination, Date Date, double Prix,int AgeMin,String Description,int IdGuide,String MoyenTransport,
            String Type ,String Plan ,String ListeInscrits,int NbrePlace,String PointDepart,int Niveau) {
        this.Titre = Titre;
        this.Destination = Destination;
        this.Date = Date;
        this.Prix = Prix;
        this.AgeMin= AgeMin;
        this.Description =Description;
        this.IdGuide=IdGuide;
        this.MoyenTransport=MoyenTransport;
        this.Type=Type;
        this.Plan=Plan ;
        this.ListeInscrits=ListeInscrits;
        this.NbrePlace=NbrePlace;
        this.PointDepart=PointDepart;
        this.Niveau=Niveau;
                
    }
        
    
    public Randonne(int Id,String Titre, String Destination, Date Date, double Prix,int AgeMin,String Description,int IdGuide,String MoyenTransport,
            String Type ,String Plan ,String ListeInscrits,int NbrePlace,String PointDepart,int Niveau)
    {
        this.Id=Id;
        this.Titre = Titre;
        this.Destination = Destination;
        this.Date = Date;
        this.Prix = Prix;
        this.AgeMin= AgeMin;//
        this.Description =Description;//
        this.IdGuide=IdGuide;//
        this.MoyenTransport=MoyenTransport;//
        this.Type=Type;//
        this.Plan=Plan ;//
        this.ListeInscrits=ListeInscrits;//
        this.NbrePlace=NbrePlace;//
        this.PointDepart=PointDepart;
        this.Niveau=Niveau;
                
    }
 public Randonne(int Id,String Titre, String Destination, Date Date, double Prix,String image,int AgeMin,String Description,int IdGuide,String MoyenTransport,
            String Type ,String Plan ,String ListeInscrits,int NbrePlace,String PointDepart,int Niveau)
    {
        this.Id=Id;
        this.Titre = Titre;
        this.Destination = Destination;
        this.Date = Date;
        this.Prix = Prix;
        this.AgeMin= AgeMin;//
        this.Description =Description;//
        this.IdGuide=IdGuide;//
        this.MoyenTransport=MoyenTransport;//
        this.Type=Type;//
        this.Plan=Plan ;//
        this.imagepath=image;
        this.ListeInscrits=ListeInscrits;//
        this.NbrePlace=NbrePlace;//
        this.PointDepart=PointDepart;
        this.Niveau=Niveau;
                
    }
 
 


    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String Destination) {
        this.Destination = Destination;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getIdGuide() {
        return IdGuide;
    }

    public void setIdGuide(int IdGuide) {
        this.IdGuide = IdGuide;
    }

    public String getListeInscrits() {
        return ListeInscrits;
    }

    public void setListeInscrits(String ListeInscrits) {
        this.ListeInscrits = ListeInscrits;
    }

    public int getNbrePlace() {
        return NbrePlace;
    }

    public void setNbrePlace(int NbrePlace) {
        this.NbrePlace = NbrePlace;
    }

    public String getPointDepart() {
        return PointDepart;
    }

    public void setPointDepart(String PointDepart) {
        this.PointDepart = PointDepart;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public int getNiveau() {
        return Niveau;
    }

    public void setNiveau(int Niveau) {
        this.Niveau = Niveau;
    }

    public int getAgeMin() {
        return AgeMin;
    }

    public void setAgeMin(int AgeMin) {
        this.AgeMin = AgeMin;
    }

    public String getMoyenTransport() {
        return MoyenTransport;
    }

    public void setMoyenTransport(String MoyenTransport) {
        this.MoyenTransport = MoyenTransport;
    }

    public String getPlan() {
        return Plan;
    }

    public void setPlan(String Plan) {
        this.Plan = Plan;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
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
        final Randonne other = (Randonne) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (this.IdGuide != other.IdGuide) {
            return false;
        }
        if (this.NbrePlace != other.NbrePlace) {
            return false;
        }
        if (this.AgeMin != other.AgeMin) {
            return false;
        }
        if (Double.doubleToLongBits(this.Prix) != Double.doubleToLongBits(other.Prix)) {
            return false;
        }
        if (!Objects.equals(this.Titre, other.Titre)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        if (!Objects.equals(this.Destination, other.Destination)) {
            return false;
        }
        if (!Objects.equals(this.ListeInscrits, other.ListeInscrits)) {
            return false;
        }
        if (!Objects.equals(this.PointDepart, other.PointDepart)) {
            return false;
        }
        if (!Objects.equals(this.Type, other.Type)) {
            return false;
        }
        if (!Objects.equals(this.MoyenTransport, other.MoyenTransport)) {
            return false;
        }
        if (!Objects.equals(this.Plan, other.Plan)) {
            return false;
        }
        if (!Objects.equals(this.Date, other.Date)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Randonne{" + "Id=" + Id + ", Titre=" + Titre + ", Description=" + Description + ", IdGuide=" + IdGuide + ", Destination=" + Destination + ", Date=" + Date + ", ListeInscrits=" + ListeInscrits + ", NbrePlace=" + NbrePlace + ", PointDepart=" + PointDepart + ", Type=" + Type + ", Niveau=" + Niveau + ", AgeMin=" + AgeMin + ", MoyenTransport=" + MoyenTransport + ", Plan=" + Plan + ", Prix=" + Prix + '}';
    }

   

   
  
}
