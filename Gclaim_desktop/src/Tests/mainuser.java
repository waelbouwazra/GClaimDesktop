/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Services.ServiceUser;

/**
 *
 * @author souma
 */
public class mainuser {
    public static void main(String[] args){
            
   
        ServiceUser ps = new ServiceUser();
       /*SimpleUtilisateur p = new SimpleUtilisateur("mariem","10101010","10101010","mariem.bensassi@esprit.tn","mariem bensassi");
       Coach s = new Coach("nabil","10101010","10101010","nabil.bensassi@esprit.tn","jeux video");
        ps.ajouterPersonne(p); 
      ps.ajouterPersonne(s); */
        System.out.println("liste des  Simples users");
        System.out.println(ps.afficheSimpleUser());
         System.out.println("liste des Coach");
        System.out.println(ps.afficheSimpleCoach());
        /*ps.DeleteUser(116);
        Coach s1 = new Coach("nabil","12345678","12345678","nabil.bensassi@esprit.tn","jeux video");
        ps.UpdatePersonne(s1,128);*/
        /*SimpleUtilisateur p = new SimpleUtilisateur("mariem","12121212","12121212","mariem.bensassi@esprit.tn","mariem bensassi");
        ps.UpdatePersonne(p,127);*/
    }
}
