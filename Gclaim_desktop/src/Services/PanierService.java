/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author moham
 */
public class PanierService {

    private Panier panier = new Panier().getInstance();

    public HashMap<Produit, Integer> addProductQuantity(Produit l) {

        panier.getPanier().replace(l, panier.getPanier().get(l), panier.getPanier().get(l) + 1);

        return panier.getPanier();
    }

    public HashMap<Produit, Integer> MinusProductQuantity(Produit l) {

        panier.getPanier().replace(l, panier.getPanier().get(l), panier.getPanier().get(l) - 1);
        return panier.getPanier();
    }

    public HashMap<Produit, Integer> RemoveProductFromCart(Produit l) {

        panier.getPanier().remove(l);
        return panier.getPanier();
    }
     public void CancelCart() {

        panier.getPanier().clear();
        System.out.println("Cart Canceled!");
    }

    public void ConfirmCart() {
        CommandeService commandeService = new CommandeService();
        LigneCommandeService ligneCommandeService = new LigneCommandeService();
        Commande c = new Commande();
        Utilisateur u = new Utilisateur(111);
        int somme = 0;
        int commandeID;
        for (Produit i : panier.getPanier().keySet()) {
            somme += i.getPrix_produit() * panier.getPanier().get(i);

        }
// System.out.println("somme = "+somme);
        c.setDate_achat(Date.valueOf(LocalDate.now()));
        c.setLivrer(false);
        c.setTotal(somme);
        c.setUser(u);
//System.out.println("blech id = "+c);
        commandeService.AddCommande(c);
        c.setId(commandeService.getLastInsertedId());
//System.out.println("commande bell id ="+c);
        for (Produit i : panier.getPanier().keySet()) {
            LigneCommande lc = new LigneCommande();
            lc.setCommande(c);
            lc.setProduit(i);
            lc.setQuantite(panier.getPanier().get(i));
//System.out.println("ligne commande ="+lc);
            ligneCommandeService.AddLigneCommande(lc);
        }
        System.out.println("Succes");
    }
    
}
