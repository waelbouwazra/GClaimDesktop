/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Coach;
import Entities.SimpleUtilisateur;
import Entities.Utilisateur;
import Services.ServiceUser;

/**
 *
 * @author souma
 */
public class mainuser {

    public static void main(String[] args) {

        ServiceUser ps = new ServiceUser();
        /*SimpleUtilisateur p = new SimpleUtilisateur("test","10101010","10101010","mariem.bensassi@esprit.tn","mariem bensassi");
       Coach s = new Coach("test","10101010","10101010","nabil.bensassi@esprit.tn","jeux video");
        ps.ajouterPersonne(p); 
        ps.ajouterPersonne(s); 
        System.out.println("liste des  Simples users");
        System.out.println(ps.afficheSimpleUser());
         System.out.println("liste des Coach");
        System.out.println(ps.afficheSimpleCoach());
        ps.DeleteUser(138);
        Coach s1 = new Coach("nabil","12345678","12345678","nabil.bensassi@esprit.tn","jeux video");
        ps.UpdatePersonne(s1,139);
        System.out.println("liste des  Simples users");
        System.out.println(ps.afficheSimpleUser());
         System.out.println("liste des Coach");
        System.out.println(ps.afficheSimpleCoach());
        SimpleUtilisateur p = new SimpleUtilisateur("mariem","12121212","12121212","mariem.bensassi@esprit.tn","mariem bensassi");
        ps.UpdatePersonne(p,127);*/

        //devenir coachh
        SimpleUtilisateur p = new SimpleUtilisateur(119);
        ps.desactiveruncompte(p.getId());
        //ps.activeruncompte(p);
        System.out.println("les coachs desactiver sont"+ ps.affichecoachdesactiver());
        System.out.println("les coachs activer sont"+ ps.affichecoachsactiver());
          System.out.println("les simples utilisateurs desactiver sont"+ ps.affichesimpleutilisateursdesactiver());
        System.out.println("les simples utilisateurs activer sont"+ ps.affichesimpleutilisateursactiver());
        /*ps.demandedevenircoach(p);
        System.out.println("liste des demande de devenir coach "+ps.affichelesdemandededevenircoach());
       // ps.refuserdevenircoach(p);
       ps.accepterdevenircoach(p);
         System.out.println("liste des demande de devenir coach "+ps.affichelesdemandededevenircoach());
         */
    }
}
