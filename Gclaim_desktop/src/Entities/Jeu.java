/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author azizk
 */
public class Jeu {
    private int id;
    private String nomjeu;
    private String description;
    private String createur;
    private Date datec;
    private String image;

   

    public Jeu(int id) {
        this.id = id;
    }

    public Jeu(String nomjeu, String description, String createur) {
        this.nomjeu = nomjeu;
        this.description = description;
        this.createur = createur;
    }

    public Jeu(int id, String nomjeu, String description, String createur, String image) {
        this.id = id;
        this.nomjeu = nomjeu;
        this.description = description;
        this.createur = createur;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Jeu(int id, String nomjeu, String description, String createur, Date datec) {
        this.id = id;
        this.nomjeu = nomjeu;
        this.description = description;
        this.createur = createur;
        this.datec = datec;
       
    }

   

    public Jeu(int id, String nomjeu, String description, String createur) {
        this.id = id;
        this.nomjeu = nomjeu;
        this.description = description;
        this.createur = createur;
    }

  

    public Jeu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomjeu() {
        return nomjeu;
    }

    public void setNomjeu(String nomjeu) {
        this.nomjeu = nomjeu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    @Override
    public String toString() {
        return  " Nom du jeu : " + nomjeu + ",  \n \n Description=" + description + ",  \n \n Createur=" + createur + ",  \n \n Date Creation  :" + datec  +",  \n \n \n";
    }







   
}
