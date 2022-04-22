/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author User
 */
public class cat {
   private int id;
   private String nom;
   private String couleur;

    public cat() {
    }

    public cat(int id, String nom, String couleur) {
        this.id = id;
        this.nom = nom;
        this.couleur = couleur;
    }

    public cat(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "cat :" + " \t| nom=" + nom + ",  \t| couleur=" + couleur +"" ;
    }

    

    public cat(int id) {
        this.id = id;
    }
   
}
