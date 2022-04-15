/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author moham
 */
public class LigneCommande {
    private int id;
    private int quantite;
    private Produit Produit;
    private Commande Commande;

    public LigneCommande() {
    }

    public LigneCommande(int id, int quantite, Produit Produit, Commande Commande) {
        this.id = id;
        this.quantite = quantite;
        this.Produit = Produit;
        this.Commande = Commande;
    }

    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public Produit getProduit() {
        return Produit;
    }

    public Commande getCommande() {
        return Commande;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setProduit(Produit Produit) {
        this.Produit = Produit;
    }

    public void setCommande(Commande Commande) {
        this.Commande = Commande;
    }

    @Override
    public String toString() {
       // return "LigneCommande{" + "id=" + id + ", quantite=" + quantite + ", Produit=" + Produit + ", Commande=" + Commande + '}';
    return "Commande id :" + id + " \t| Quantite: "+quantite+ "\t| Produit :" + Produit.getNom_produit();
  
    }
    
}
