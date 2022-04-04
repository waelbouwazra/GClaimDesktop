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
public class Tournoi {
    private int id;
    private String nomtournoi;
    private String description;
    private Date datec;
    private Date datev;
    private String heurev;
    private Jeu jeu;

    public Tournoi(int id, String nomtournoi, String description, Date datec, Date datev, String heurev, Jeu jeu) {
        this.id = id;
        this.nomtournoi = nomtournoi;
        this.description = description;
        this.datec = datec;
        this.datev = datev;
        this.heurev = heurev;
        this.jeu = jeu;
    }

    public Tournoi(int id, String nomtournoi, String description) {
        this.id = id;
        this.nomtournoi = nomtournoi;
        this.description = description;
    }

    public Tournoi() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomtournoi() {
        return nomtournoi;
    }

    public void setNomtournoi(String nomtournoi) {
        this.nomtournoi = nomtournoi;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public Date getDatev() {
        return datev;
    }

    public void setDatev(Date datev) {
        this.datev = datev;
    }

    public String getHeurev() {
        return heurev;
    }

    public void setHeurev(String heurev) {
        this.heurev = heurev;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", nomtournoi=" + nomtournoi + ", description=" + description + ", datec=" + datec + ", datev=" + datev + ", heurev=" + heurev + ", jeu" + jeu + '}';
    }



   
}
