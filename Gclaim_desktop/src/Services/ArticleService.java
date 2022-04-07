/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Article;
import Entities.cat;
import Tools.MaConnection;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azizk
 */
public class ArticleService {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public ArticleService() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddArticle(Article a){
         
              String req = "insert into article (titre,description,image,cat_id) values (?,?,?,?)";
              try { 
                            pst = cnx.prepareStatement(req);
              
              pst.setString(1, a.getTitre());
              pst.setString(2, a.getDescription());
              pst.setString(3, a.getImage());
              pst.setInt(4, a.getCat_id().getId());
             
             
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
          }
            
        

    }
    
        public void DeleteArticle(int p){
          try {
              String req = "DELETE from article  WHERE id =" +p+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);}
          }
        
        public void UpdateArticle(Article a,int pu)
        { String req ="UPDATE article set Titre=? , description=? , image=? , cat_id=? WHERE id =" +pu+ " ";
        try {
              pst = cnx.prepareStatement(req);
              pst.setString(1, a.getTitre());
              pst.setString(2, a.getDescription());
              pst.setString(3, a.getImage());
              pst.setInt(4, a.getCat_id().getId());

              pst.executeUpdate();
              System.out.println("article modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<Article> ShowArticle(){
        List<Article> article = new ArrayList<>();
        String sql="select * from article";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Article p = new Article();
                 p.setId(rs.getInt("id"));
                 p.setTitre(rs.getString("titre"));
                 p.setDescription(rs.getString("description"));
                 p.setImage(rs.getString("image"));
                 p.setCreate_at(rs.getDate("create_at"));
                 cat c =new cat (this.rs.getInt("cat_id"));
                 p.setCat_id(c);
                 p.setNbr_vu(rs.getInt("nbr_vu"));
                 article.add(p);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return article;
    }
        


   
}
