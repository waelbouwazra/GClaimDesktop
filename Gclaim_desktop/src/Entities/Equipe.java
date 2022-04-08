/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author souma
 */
public class Equipe {

    private int id;
    private String nomEquipe;
    private String Description;
    private Date dateCreation;
    private String Etat;
    private String chef;
    private Utilisateur User;
    private int nb;

    public Equipe(String nomEquipe, String description, String Etat, String chef,int nb,Utilisateur User) {
        this.nomEquipe = nomEquipe;
        this.Description = description;
        this.Etat = Etat;
        this.chef = chef;
                this.nb = nb;

        this.User=User;
    }

    public Equipe() {
    }
public Equipe(int id) {
    this.id = id;
    }
    public Equipe(int id, String nomEquipe, String Description, Date dateCreation, String Etat, String chef, Utilisateur idUser) {
        this.id = id;
        this.nomEquipe = nomEquipe;
        this.Description = Description;
        this.dateCreation = dateCreation;
        this.Etat = Etat;
        this.chef = chef;
        this.User = idUser;
    }
 public Equipe(String nomEquipe, String Description,String Etat) {
         
        this.nomEquipe = nomEquipe;
        this.Description = Description;
        this.Etat = Etat;
       
    }
public Equipe(int id,Utilisateur User) {
         this.id = id;
       this.User = User;
       
    }
    public Utilisateur getUser() {
        return User;
    }

    public void setUser( Utilisateur idUser) {
        this.User = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public String getChef() {
        return chef;
    }

    public void setChef(String chef) {
        this.chef = chef;
    }

  

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }
    
 @Override
    public String toString() {
        return "Equipe{" + "id=" + id + ", nomEquipe=" + nomEquipe + ", description=" + Description + ", dateCreation=" + dateCreation + ", Etat=" + Etat + ", chef=" + chef + '}';
    }

}

