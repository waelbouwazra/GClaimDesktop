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
public class Produit {
    private int id_produit;
    private String nom_produit;
    private String description;
    private double prix_produit;
    private Date dateAjout_produit;
    private int Qte_produit;
    private int nbr_vu;
    private Categorie categorie;
    private String image;
    public Produit() {
    }

    public Produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public Produit( String nom_produit, String description, double prix_produit, Date dateAjout_produit, int Qte_produit) {
        
        this.nom_produit = nom_produit;
        this.description = description;
        this.prix_produit = prix_produit;
        this.dateAjout_produit = dateAjout_produit;
        this.Qte_produit = Qte_produit;
        
    }

    public Produit( String nom_produit, String description, double prix_produit, int Qte_produit) {
        
        this.nom_produit = nom_produit;
        this.description = description;
        this.prix_produit = prix_produit;
        this.Qte_produit = Qte_produit;
    }

    public Produit(String nom_produit, String description, double prix_produit, int Qte_produit, Categorie categorie) {
        this.nom_produit = nom_produit;
        this.description = description;
        this.prix_produit = prix_produit;
        this.Qte_produit = Qte_produit;
        this.categorie = categorie;
    }

    public Produit(String nom_produit, String description, double prix_produit, int Qte_produit, Categorie categorie, String image) {
        this.nom_produit = nom_produit;
        this.description = description;
        this.prix_produit = prix_produit;
        this.Qte_produit = Qte_produit;
        this.categorie = categorie;
        this.image = image;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(double prix_produit) {
        this.prix_produit = prix_produit;
    }

    public Date getDateAjout_produit() {
        return dateAjout_produit;
    }

    public void setDateAjout_produit(Date dateAjout_produit) {
        this.dateAjout_produit = dateAjout_produit;
    }

    public int getQte_produit() {
        return Qte_produit;
    }

    public void setQte_produit(int Qte_produit) {
        this.Qte_produit = Qte_produit;
    }

    public int getNbr_vu() {
        return nbr_vu;
    }

    public void setNbr_vu(int nbr_vu) {
        this.nbr_vu = nbr_vu;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", nom_produit=" + nom_produit + ", description=" + description + ", prix_produit=" + prix_produit + ", dateAjout_produit=" + dateAjout_produit + ", Qte_produit=" + Qte_produit + ", nbr_vu=" + nbr_vu + ", categorie=" + categorie + '}';
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
    
        final Produit other = (Produit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        return true;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Produit(int id_produit, String nom_produit, String description, double prix_produit, int Qte_produit, Categorie categorie) {
        this.id_produit = id_produit;
        this.nom_produit = nom_produit;
        this.description = description;
        this.prix_produit = prix_produit;
        this.Qte_produit = Qte_produit;
        this.categorie = categorie;
    }

    
    
}
