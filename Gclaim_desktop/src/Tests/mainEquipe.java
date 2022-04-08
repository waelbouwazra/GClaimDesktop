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

        Utilisateur p = new Utilisateur(139);
        Equipe E=new Equipe(43);
        ps.Rejoindreuneequipe(E, p);
        /*ps1.ajouterPersonne(p); */
        /*Equipe e = new Equipe("the best", "the best", "open", "moncef", 0, p);
        ps.AddEquipe(e);
        System.out.println(ps.afficheEquipe());

        Equipe p1 = new Equipe("bits and bytes", "the best", "open");
        ps.updateEquipe(p1, 43);
        System.out.println(ps.afficheEquipe());
        ps.DeleteEquipe(42);
*/
    }
}
