/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;
import Entities.Article;
import Entities.cat;
import Entities.Article;
import Services.ArticleService;

/**
 *
 * @author User
 */
public class mainArticle {
        public static void main(String[] args) {
        cat c =new cat(1);
        Article a = new Article("bhbhb", "blala","imama",c);
        ArticleService ps = new ArticleService();
       // ps.AddArticle(a);
        System.out.println(ps.ShowArticle());
      // ps.UpdateArticle(a,21);

        }
}
