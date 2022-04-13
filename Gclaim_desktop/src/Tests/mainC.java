/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Categorie;
import Services.CategorieService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author azizk
 */
public class mainC {
    public static void main(String[] args) {
        Categorie c = new Categorie("cccccccccc", "cat");
        CategorieService ps = new CategorieService();
        ps.AddCategoriePst(c);
        //System.out.println(ps.ShowCategorie());
        
        //recherche
        //List<Categorie> listc = new ArrayList<>();
       // listc = ps.ShowCategorie();
        //ps.Rechercher(listc,"validation");
        
        
        //tri
        List<Categorie> listc = new ArrayList<>();
        listc = ps.ShowCategorie();
        ps.TriCategorie(listc);
        
        
       // ps.UpdateCategorie(c, 7);
       // ps.DeleteCategorie(c.getId_categorie());
   //   MaConx m=MaConx.getInstance();
    }
}
