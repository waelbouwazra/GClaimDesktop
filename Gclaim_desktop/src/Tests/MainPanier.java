/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Commande;
import Entities.LigneCommande;
import Entities.Produit;
import Entities.Utilisateur;
import Services.CommandeService;
import Services.LigneCommandeService;

/**
 *
 * @author moham
 */
public class MainPanier {
    public static void main(String[] args) {
       // Categorie c = new Categorie(7);
       // Produit p = new Produit("test", "foulenaa",10f,10,c);
        CommandeService cs = new CommandeService();
        System.out.println(cs.ShowCommande());
       
        Commande c = new Commande();
        Utilisateur u = new Utilisateur(139);
        c.setLivrer(true);
        c.setTotal(260);
        c.setUser(u);
        Produit p = new Produit();
        p.setId_produit(5);
        c.setId(1);
        LigneCommande lc= new LigneCommande();
        lc.setCommande(c);
        lc.setProduit(p);
        lc.setQuantite(10);
        LigneCommandeService lcs= new LigneCommandeService();
       
        lcs.AddLigneCommande(lc);
        System.out.println(lcs.ShowLigneCommande());
   
        /*
        cs.AddCommande(c);
     */
        
        
      //  cs.DeleteCommande(16);
        
        /* Update Commande
        
        */
      cs.UpdateCommande(c, 17);
        
   //   MaConx m=MaConx.getInstance();
    }    
}


