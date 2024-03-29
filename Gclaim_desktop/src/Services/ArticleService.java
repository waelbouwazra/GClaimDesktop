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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


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

    public void AddArticle(Article a) {

        String req = "insert into article (titre,description,image,create_at,cat_id,nbr_vu) values (?,?,?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);

            pst.setString(1, a.getTitre());
            pst.setString(2, a.getDescription());
            pst.setString(3, a.getImage());
            pst.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            pst.setInt(5, a.getCat_id().getId());

            pst.setInt(6, 0);

            pst.executeUpdate();
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }

    }

    public void DeleteArticle(int p) {
        try {
            String req = "DELETE from article  WHERE id =" + p + " ";

            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("done");
        } catch (SQLException ex) {
            System.out.println("Probléme");
           System.out.println(ex.getMessage());
        }
    }

    public void UpdateArticle(Article a, int pu) {
        String req = "UPDATE article set Titre=? , description=? , image=? , cat_id=? WHERE id =" + pu + " ";
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

    public List<Article> ShowArticle() {
        List<Article> article = new ArrayList<>();
        String sql = "select * from article";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while (rs.next()) {
                Article p = new Article();
                p.setId(rs.getInt(1));
                p.setTitre(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setImage(rs.getString(4));
                p.setCreate_at(rs.getDate(5));
                
                cat c = new cat(rs.getInt(6));
               
                p.setCat_id(c);
                p.setNbr_vu(rs.getInt(7));
                  
                article.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return article;
    }
public int getIdArticle(String tit , String desc){
      String req = "SELECT * FROM `article` WHERE titre=? and description=?";
Article a =new Article();
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, tit);
            pst.setString(2, desc);

            ResultSet as = pst.executeQuery();
            if (as.next()) {
             return as.getInt(1);
         
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    
}
public Set<Article> tri( List<Article> u){
       
        Set<Article> ensEmp2 = u.stream().collect(Collectors.toCollection(()->new TreeSet<Article>((e1,e2)->e1.getTitre().compareTo(e2.getTitre()))));
        return ensEmp2;
    }
public void filtrecateg( List<Article> produit, int cat){
       
        
         produit.stream().filter(pp->pp.getCat_id().getId()==cat).forEach((t) -> {System.out.println(t);
        });
    }
public void trinbrvu( List<Article> produit){
       
         
         produit.stream()
        .filter(e -> e.getNbr_vu() != 0).forEach((t) -> {System.out.println(t);});
      
        }
public List<Article> plusvu( List<Article> produit){
       
         return
         produit.stream()
        .filter(e -> e.getNbr_vu() != 0).collect(Collectors.toList());
      
        }
   public Set<Article> tripardate( List<Article> u){
       
        Set<Article> ensEmp2 = u.stream().collect(Collectors.toCollection(()->new TreeSet<Article>((e1,e2)->e1.getCreate_at().compareTo(e2.getCreate_at()))));
        return ensEmp2;
    }
/** public List<Article> affichecoachdesactiver() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=0";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                if (rs.getString(4).equals("coach")) {
                    Coach p = new Coach();
                    p.setId(rs.getInt(1));
                    p.setPassword(rs.getString(2));
                    p.setEmail(rs.getString(3));
                    p.setSpecialite(rs.getString(6));
                    p.setUsername(rs.getString(7));
                    p.setVerifpassword(rs.getString(8));
                    personnes.add(p);

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }*/
public List<Article> ShowArticleFront() {
        List<Article> article = new ArrayList<>();
        CatService Catservice =new CatService();
        String sql = "select * from article";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
           
            while (rs.next()) {
                Article p = new Article();
                p.setId(rs.getInt(1));
                p.setTitre(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setImage(rs.getString(4));
                p.setCreate_at(rs.getDate(5));
              /*  cat e =new cat (rs.getInt("cat_id"));
                System.out.println(e);
                p.setCat_id(e);*/
           System.out.println(rs.getInt("cat_id"));
           p.setCat_id(Catservice.getIdCat(rs.getInt("cat_id")));
                System.out.println(p.getCat_id());
                p.setNbr_vu(rs.getInt(7));
                  
                article.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return article;
    }
public int Recherche(String tit ){
      String req = "SELECT * FROM `article` WHERE titre=? ";
Article a =new Article();
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, tit);
            ResultSet as = pst.executeQuery();
            if (as.next()) {
             return as.getInt(1);
         
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    
}
public long Recherche1() {

        List<Article> article = ShowArticle();
        return article.stream().filter(b -> b.getNbr_vu()> 0).filter(b -> b.getNbr_vu()<4 ).count();

      
        }
   
      public long Recherche2() {

        List<Article> article = ShowArticle();
        return article.stream().filter(b -> b.getNbr_vu()> 4).filter(b -> b.getNbr_vu()< 8 ).count();

}
       public long Recherche3() {

        List<Article> article = ShowArticle();
        return article.stream().filter(b -> b.getNbr_vu()<10 ).count();

}
       public long Recherche4() {

        List<Article> article = ShowArticle();
 return article.stream().filter(b -> b.getNbr_vu()> 10).filter(b -> b.getNbr_vu()< 20 ).count();
}
     public Set<Article> Rechercher(List<Article> destination, String tit) {

        Set<Article> u = destination.stream().filter(cc -> cc.getTitre().equals(tit)).collect(Collectors.toCollection(() -> new TreeSet<Article>((e1, e2) -> e1.getTitre().compareTo(e2.getTitre()))));

        return u;
    }
     
     
      public void increment(Article p) {

      String req1 = "UPDATE article set nbr_vu=? WHERE id =" +p.getId()+ "";
        try {

          
           pst = cnx.prepareStatement(req1);
            pst.setInt(1, p.getNbr_vu()+1);
           
            pst.executeUpdate();
            System.out.println("nombre de vu incremente");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     
     
     
     
}

