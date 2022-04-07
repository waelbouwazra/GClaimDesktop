/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.Commande;
import Entities.Produit;
import Entities.Utilisateur;
import Tools.MaConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moham
 */
public class CommandeService {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public CommandeService() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddCommande(Commande c) {
         
              String req = "insert into Commande (total,date_achat,livrer,User) values (?,?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              
              pst.setInt(1, c.getTotal());
              pst.setDate(2, Date.valueOf(LocalDate.now()));
             
              pst.setBoolean(3, c.isLivrer());
              pst.setInt(4, c.getUser().getId());
             
              pst.executeUpdate();
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          }

        

    }
    
        public void DeleteCommande(int c){
          try {
              String req = "DELETE from commande  WHERE id =" +c+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("Commande Deleted");
          } catch (SQLException ex) {
              System.out.println("Commande delete failed");}
          }
        
        public void UpdateCommande(Commande c,int cID)
        { String req ="UPDATE commande set livrer=? WHERE id =" +cID+ " ";
        try {
              pst = cnx.prepareStatement(req);
              pst.setBoolean(1, !(c.isLivrer()));


              pst.executeUpdate();
              System.out.println("Commande modifié");
            
        } catch (SQLException ex) {
            System.out.println("Update Commande failed!");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<Commande> ShowCommande(){
        List<Commande> commandes = new ArrayList<>();
        String sql="select * from commande";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 
                 
                 Commande c = new Commande();
                 c.setId(rs.getInt("id"));
                 c.setTotal(rs.getInt("total"));
                 c.setLivrer(rs.getBoolean("livrer"));
                 Utilisateur u=new Utilisateur(rs.getInt("User"));
                 c.setUser(u);
                 c.setDate_achat(rs.getDate("date_achat"));
               
                 commandes.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return commandes;
    }
          public Commande getSingleCommande(int id){
        Commande c = new Commande();
        String sql="select * from commande  WHERE id =" +id;
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 
                
                 c.setId(rs.getInt("id"));
                 c.setTotal(rs.getInt("total"));
                 c.setLivrer(rs.getBoolean("livrer"));
                 Utilisateur u=new Utilisateur(rs.getInt("User"));
                 c.setUser(u);
                 c.setDate_achat(rs.getDate("date_achat"));
               
               
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return c;
    }
        
}
