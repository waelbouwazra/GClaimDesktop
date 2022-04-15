/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author moham
 */
public class PanierModel extends Produit{
    private int quantite;
    public PanierModel(){
       super();
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }

    public PanierModel(int quantite) {
        this.quantite = quantite;
    }

    public PanierModel(int quantite, int id_produit) {
        super(id_produit);
        this.quantite = quantite;
    }

    public PanierModel(int quantite, String nom_produit, String description, double prix_produit, Date dateAjout_produit, int Qte_produit) {
        super(nom_produit, description, prix_produit, dateAjout_produit, Qte_produit);
        this.quantite = quantite;
    }

    public PanierModel(int quantite, String nom_produit, String description, double prix_produit, int Qte_produit) {
        super(nom_produit, description, prix_produit, Qte_produit);
        this.quantite = quantite;
    }

    public PanierModel(int quantite, String nom_produit, String description, double prix_produit, int Qte_produit, Categorie categorie) {
        super(nom_produit, description, prix_produit, Qte_produit, categorie);
        this.quantite = quantite;
    }

    public PanierModel(int quantite, String nom_produit, String description, double prix_produit, int Qte_produit, Categorie categorie, Image image) {
        super(nom_produit, description, prix_produit, Qte_produit, categorie, image);
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Product Name :" +this.getNom_produit()+ " \t| Price: "+this.getPrix_produit()+ "\t| Quantite :" + quantite ;
    }

 
    
}
