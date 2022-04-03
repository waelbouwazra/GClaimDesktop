/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Categorie;
import Services.CategorieService;

/**
 *
 * @author azizk
 */
public class mainC {
    public static void main(String[] args) {
        Categorie c = new Categorie("cat", "cat");
        CategorieService ps = new CategorieService();
        ps.AddCategoriePst(c);
        System.out.println(ps.ShowCategorie());
        
   //   MaConx m=MaConx.getInstance();
    }
}
