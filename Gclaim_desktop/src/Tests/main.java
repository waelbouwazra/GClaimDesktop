/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Categorie;
import Entities.Image;
import Entities.Produit;
import Entities.Profil;
import Entities.Rdv;
import Entities.Utilisateur;
import Services.ProduitService;
import Services.ProfilService;
import Services.RdvService;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jdk.nashorn.internal.objects.Global;

/**
 *
 * @author azizk
 */
public class main {
    public static void main(String[] args) {
       Categorie c = new Categorie(7);
       Produit p = new Produit("pc", "pc",10f,10,c);
        ProduitService ps = new ProduitService();
        
      // System.out.println(ps.ShowProduit());
       Image i=new Image("aaaaa");
      //  ps.AddProduitPst(p, i);
      // ps.DeleteProduit(p.getId_produit());
      
        //-------------------------------------------------------recherche
        // List<Produit> listp = new ArrayList<>();
        //listp = ps.Rechercher("microsoft");
        //listp.stream().forEach(pp->System.out.println(pp));
      
      
        //-------------------------------------------------------filtre par pix
        //List<Produit> listp = new ArrayList<>();
        //listp = ps.ShowProduit();
        // ps.filtreprix(listp, 4, 6);
      
        //-------------------------------------------------------filtre par categorie
        //List<Produit> listp = new ArrayList<>();
        //listp = ps.ShowProduit();
        // ps.filtrecateg(listp,4 );
     
        //-------------------------------------------------------les plus vues
        //List<Produit> listp = new ArrayList<>();
        //listp = ps.ShowProduit();
         //ps.plusvu(listp );
         
         
          //-------------------------------------------------------nombre de produit
         //List<Produit> listp = new ArrayList<>();
        //listp = ps.ShowProduit();
         //ps.nbrprod(listp );
      
   //   MaConx m=MaConx.getInstance();
    /* Utilisateur c = new Utilisateur(104);
        Profil p = new Profil("test1" ,"kchouk1",c,"lol1",55);
           ProfilService ps = new ProfilService();
        ps.AddProfil(p);
        System.out.println(ps.ShowProfil());*/
       
        /*Profil c = new Profil(3);
        
        Utilisateur b = new Utilisateur(114);
        LocalDate l=LocalDate.of(2022, Month.MARCH, 9);

        Rdv p = new Rdv(c,b);
           RdvService ps = new RdvService();
          
       // ps.AddRdv(p);
        System.out.println(ps.ShowRdv());
        
        
       */ 
    }
}
