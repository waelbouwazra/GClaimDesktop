/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.cat;
import Tools.MaConnection;
import java.sql.Connection;
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
public class CatService {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public CatService() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddCatPst(cat c) {
         
              String req = "insert into cat (nom,couleur) values (?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              pst.setString(1, c.getNom());
              pst.setString(2, c.getCouleur());
              
              
             
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
          }

        

    }
    
        public void DeleteCategorie(int c){
          try {
              String req = "DELETE from cat  WHERE id_article =" +c+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);}
          }
        
        public void UpdateCategorie(cat c,int cu)
        { String req ="UPDATE article set nom=? , couleur=? WHERE id =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
              pst.setString(2, c.getNom());
              pst.setString(3, c.getCouleur());
              
              pst.executeUpdate();
              System.out.println("categorie modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<cat> ShowCategorie(){
        List<cat> cat = new ArrayList<>();
        String sql="select * from cat";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 cat c = new cat();
                 c.setId(rs.getInt("id"));
                 c.setNom(rs.getString("nom"));
                 c.setCouleur(rs.getString("couleur"));
                 
                 cat.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return cat;
    }
        


}
