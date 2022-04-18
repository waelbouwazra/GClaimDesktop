/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.cat;
import Entities.Article;
import Entities.Commentaire;
import Tools.MaConnection;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author azizk
 */
public class CommentaireService {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public CommentaireService() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddCommentairePst(Commentaire p) {
         
              String req = "insert into commentaire (article_id,commentaire,user) values (?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              pst.setInt(1, p.getArticle_id().getId());
              pst.setString(2, p.getCommentaire());
              pst.setString(3, p.getUser());
      
              pst.executeUpdate();
          } catch (SQLException ex) {
             System.out.println(ex.getMessage());
          } 
             /* String req1 = "insert into article (commentaire,user) values (?,?)";
              try { 
              
              pst = cnx.prepareStatement(req1);
              pst.setInt(1, i.getCommentaire().getId());
              pst.setString(2, i.getCommentaire().getUser());
             
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
          }*/
        

    }
    
        public void DeleteCommentaire(int p){
          try {
              String req = "DELETE from commentaire  WHERE id =" +p+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
                System.out.println(ex.getMessage());}
          }
        
        public void UpdateCommentaire(Commentaire p,int pu)
        { String req ="UPDATE commentaire set article_id=? , commentaire=?  WHERE id =" +pu+ " ";
        try {
              pst = cnx.prepareStatement(req);
              pst.setInt(1, p.getArticle_id().getId());
              pst.setString(2, p.getCommentaire());

              pst.executeUpdate();
              System.out.println("Commentaire modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<Commentaire> ShowCommentaire(){
        List<Commentaire> commentaire = new ArrayList<>();
        String sql="select * from commentaire";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Commentaire p = new Commentaire();
                 Article c =new Article (rs.getInt("id"));
                 p.setArticle_id(c);
                 p.setCommentaire(rs.getString("commentaire"));
                 p.setUser(rs.getString("user"));
                
                 commentaire.add(p);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return commentaire;
    }
        


   
}
