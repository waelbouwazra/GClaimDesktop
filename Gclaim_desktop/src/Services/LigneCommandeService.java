/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Commande;
import Entities.LigneCommande;
import Entities.Produit;
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

/**
 *
 * @author moham
 */
public class LigneCommandeService {
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public LigneCommandeService() {
         cnx = MaConnection.getInstance().getConnection();
    }
      public void AddLigneCommande(LigneCommande lc) {
         
              String req = "insert into ligne_commande (quantite,produit,commande) values (?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              
              pst.setInt(1, lc.getQuantite());
              pst.setInt(2, lc.getProduit().getId_produit());
             
              pst.setInt(3, lc.getCommande().getId());
            
              pst.executeUpdate();
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          }
    }
        public List<LigneCommande> ShowLigneCommande(){
        List<LigneCommande> LigneCommandes = new ArrayList<>();
        String sql="select * from ligne_commande";
        Statement ste;
       
        try {
                 ProduitService ps = new ProduitService();
                 CommandeService cs = new CommandeService();
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){

                  Commande c = new Commande();
                  Produit prod = new Produit();
                 LigneCommande lc = new LigneCommande();
                 lc.setId(rs.getInt("id"));
                    //prod.setId_produit(rs.getInt("produit"));
                    prod= ps.getProduitByID(rs.getInt("produit"));
               lc.setProduit(prod);
             
                 c=cs.getSingleCommande(rs.getInt("commande"));
                  lc.setCommande(c);
                 lc.setQuantite(rs.getInt("quantite"));
             
                
                 
                 LigneCommandes.add(lc);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return LigneCommandes;
    }
        public List<LigneCommande> getLigneCommandeByCommandeID(int id){
        List<LigneCommande> LigneCommandes = new ArrayList<>();
        String sql="select * from ligne_commande where commande="+id;
        Statement ste;
       
        try {
                 ProduitService ps = new ProduitService();
                 CommandeService cs = new CommandeService();
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){

                  Commande c = new Commande();
                  Produit prod = new Produit();
                 LigneCommande lc = new LigneCommande();
                 lc.setId(rs.getInt("id"));
                    //prod.setId_produit(rs.getInt("produit"));
                    prod= ps.getProduitByID(rs.getInt("produit"));
               lc.setProduit(prod);
             
                 c=cs.getSingleCommande(rs.getInt("commande"));
                  lc.setCommande(c);
                 lc.setQuantite(rs.getInt("quantite"));
             
                
                 
                 LigneCommandes.add(lc);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return LigneCommandes;
    }
        
        
    
}
