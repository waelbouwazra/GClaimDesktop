/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Services.ProduitService;
import java.util.HashMap;
import java.sql.*;
/**
 *
 * @author moham
 */
public  final class Panier {
      private static HashMap< Produit,Integer> panier = new HashMap< Produit,Integer>();
   
     private static Panier instance;
     public Panier(){
                     /* Produit p1= new Produit();
                    /* p1.setId_produit(1);
                      p1.setNom_produit("Manette");
                      p1.setPrix_produit(100);*/
                   /* Produit p2= new Produit();
                  /*  p2.setId_produit(2);
                      p2.setNom_produit("PC");
                      p2.setPrix_produit(500);*/
                   /*  ProduitService ps = new ProduitService();
                     p1=ps.getProduitByID(4);
                      p2=ps.getProduitByID(5);
                panier.putIfAbsent(p1,9);
               panier.putIfAbsent(p2, 12);*/
     }
        public static Panier getInstance() {
        if (instance == null) {
            instance = new Panier();
        }
        return instance;
    }

    public static HashMap<Produit, Integer> getPanier() {
        return panier;
    }

    public static void setPanier(HashMap<Produit, Integer> panier) {
        Panier.panier = panier;
    }
 
}


