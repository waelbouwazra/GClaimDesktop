/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Categorie;
import Entities.Image;
import Entities.Produit;
import Services.ProduitService;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author azizk
 */
public class main {
    public static void main(String[] args) {
       
        Categorie c = new Categorie(4);
        Produit p = new Produit("tv", "foulenaa",10f,10,c);
        ProduitService ps = new ProduitService();
                //ps.(24);
 Image i = new Image(p,"dd");
        ps.AddProduitPst(p,i);
        System.out.println(ps.ShowProduit());
        
   //   MaConx m=MaConx.getInstance();
    }
}
