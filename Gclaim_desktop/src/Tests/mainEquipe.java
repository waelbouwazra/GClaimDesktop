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

       
        

        Equipe p1 = new Equipe(43,"bits and bytes", "the best", "close");
        ps.updateEquipe(p1);
        System.out.println(ps.afficheEquipe());
      
    }
}
