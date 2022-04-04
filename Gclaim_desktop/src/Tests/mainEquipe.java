/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.*;
import java.sql.Date;
import Services.*;

/**
 *
 * @author souma
 */
public class mainEquipe {
      public static void main(String[] args) {
     ServiceEquipe ps = new ServiceEquipe();
     ServiceUser ps1 = new ServiceUser();
     
    SimpleUtilisateur p = new SimpleUtilisateur("mariem","10101010","10101010","mariem.bensassi@esprit.tn","mariem bensassi");
   /* ps1.ajouterPersonne(p); */
     /*Equipe e= new Equipe("the best","the best","open","soumaya",0,p);
     ps.AddEquipe(e);*/
          System.out.println(ps.afficheEquipe());
         // ps.DeleteEquipe(42);
         Equipe p1=new Equipe ("bits and bytes","the best","open");
         ps.updateEquipe(p1, 43);
            System.out.println(ps.afficheEquipe());
          
             }
}
