/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Jeu;
import Entities.Tournoi;
import Services.ServiceTournoi;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author azizk
 */
public class mainTournoi {
    public static void main(String[] args) {
        Tournoi c = new Tournoi(1,"tournoi","waaaaaaw");
       // Produit p = new Produit("test", "foulenaa",10f,10,c);
        ServiceTournoi ps = new ServiceTournoi();
        ps.AddTournoiPst(c);
        System.out.println(ps.ShowTournoi());
        
   //   MaConx m=MaConx.getInstance();
    }
}
