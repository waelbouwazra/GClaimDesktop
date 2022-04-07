/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Categorie;
import Entities.Produit;
import Entities.Profil;
import Entities.Rdv;
import Entities.Utilisateur;
import Services.ProduitService;
import Services.RdvService;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author azizk
 */
public class main {
    public static void main(String[] args) throws ParseException {
      // Categorie c = new Categorie(7);
       // Produit p = new Produit("test", "foulenaa",10f,10,c);
        //ProduitService ps = new ProduitService();
        //ps.DeleteProduit(24);
       // System.out.println(ps.ShowProduit());
        
   //   MaConx m=MaConx.getInstance();
    /* Utilisateur c = new Utilisateur(104);
        Profil p = new Profil("test1" ,"kchouk1",c,"lol1",55);
           ProfilService ps = new ProfilService();
        ps.AddProfil(p);
        System.out.println(ps.ShowProfil());*/
       
        Profil c = new Profil(3);
        
        Utilisateur b = new Utilisateur(114);
      
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         Date myDate = formatter.parse("2022-12-31");
         System.out.println(myDate);
        Rdv p = new Rdv(c,b,myDate);
           RdvService ps = new RdvService();
          
       ps.AddRdv(p);
       // System.out.println(ps.ShowRdv());
    }
}
