/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Commentaire;
import Entities.Article;
import Services.CommentaireService;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author azizk
 */
public class mainCom {
    public static void main(String[] args) {
        Article c = new Article(36);
        Commentaire p = new Commentaire(c,"test", "foulenaa");
        CommentaireService ps = new CommentaireService();
       // ps.AddCommentairePst(p);
        System.out.println(ps.ShowCommentaireArt(c));
       // System.out.println(ps.ShowProduit());
        
   //   MaConx m=MaConx.getInstance();
    }
}
