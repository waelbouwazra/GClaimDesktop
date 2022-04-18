/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Jeu;
import Entities.Tournoi;
import Services.ServiceTournoi;
import Services.ServiceJeu;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author azizk
 */
public class mainJeu {
    public static void main(String[] args) {
        Jeu h= new Jeu(1, "nomJeu", "description", "de","waaaaaaaaaws");
       
       // Produit p = new Produit("test", "foulenaa",10f,10,c);
        ServiceJeu ps = new ServiceJeu();
        ps.AddJeuPst(h);
        ps.UpdateJeu(h, 4);
        System.out.println(ps.ShowJeu());
        
   //   MaConx m=MaConx.getInstance();
    }
}
