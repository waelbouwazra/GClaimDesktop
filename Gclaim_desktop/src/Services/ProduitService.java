/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Entities.Image;
import Entities.Produit;
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
public class ProduitService {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public ProduitService() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddProduitPst(Produit p, Image i) {
         
              String req = "insert into produit (nom_produit,description,prix_produit,Qte_produit,categorie,nbr_vu) values (?,?,?,?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              pst.setString(1, p.getNom_produit());
              pst.setString(2, p.getDescription());
              pst.setDouble(3, p.getPrix_produit());
           
              pst.setInt(4, p.getQte_produit());
              pst.setInt(5, p.getCategorie().getId_categorie());
               pst.setInt(6, 0);
              pst.executeUpdate();
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          }
              String query1 = "select MAX(id_produit) id  from produit";
            Statement ste;
            int x = 0;
            try {
                ste = cnx.createStatement();
                ResultSet rs = ste.executeQuery(query1);
                while (rs.next()) {
                    x = rs.getInt("id");
                }
            }
                catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            String req1 = "insert into images (produit,url_image) values (?,?)";
              try { 
              
              pst = cnx.prepareStatement(req1);
              
              pst.setInt(1, x);
              pst.setString(2, i.getUrl_image());
              
             
              pst.executeUpdate();
          } catch (SQLException ex) {
              System.out.println(ex.getMessage());
          } 
        

    }
    
        public void DeleteProduit(int p){
          try {
              String req = "DELETE from produit  WHERE id_produit =" +p+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);}
          }
        
        public void UpdateProduit(Produit p,int pu)
        { String req ="UPDATE produit set nom_produit=? , description=? , prix_produit=?,Qte_produit=?, categorie=? WHERE id_produit =" +pu+ " ";
        try {
              pst = cnx.prepareStatement(req);
              pst.setString(1, p.getNom_produit());
              pst.setString(2, p.getDescription());
              pst.setDouble(3, p.getPrix_produit());
              pst.setInt(4, p.getQte_produit());
              pst.setInt(5, p.getCategorie().getId_categorie());
              pst.executeUpdate();
              System.out.println("produit modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<Produit> ShowProduit(){
        List<Produit> produit = new ArrayList<>();
        String sql="select * from produit";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Produit p = new Produit();
                 p.setId_produit(rs.getInt("id_produit"));
                 p.setNom_produit(rs.getString("nom_produit"));
                 p.setDescription(rs.getString("description"));
                 p.setPrix_produit(rs.getDouble("prix_produit"));
                 p.setDateAjout_produit(rs.getDate("date_ajout_produit"));
                 p.setQte_produit(rs.getInt("Qte_produit"));
                 p.setNbr_vu(rs.getInt("nbr_vu"));
                 p.setCategorie(new Categorie (rs.getInt("categorie")));
                 produit.add(p);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return produit;
    }
        
public List<Produit> Rechercher(String nom_produit)
    {

       List<Produit> produit = new ArrayList<>();
        String sql="select * from produit where nom_produit like ?";
        PreparedStatement ste;
       
        try {
            
            ste = cnx.prepareStatement(sql);
           ste.setString(1, nom_produit);
            ResultSet rs = ste.executeQuery();
             while(rs.next()){
                 Produit p = new Produit();
                 p.setId_produit(rs.getInt("id_produit"));
                 p.setNom_produit(rs.getString("nom_produit"));
                 p.setDescription(rs.getString("description"));
                 p.setPrix_produit(rs.getDouble("prix_produit"));
                 p.setDateAjout_produit(rs.getDate("date_ajout_produit"));
                 p.setQte_produit(rs.getInt("Qte_produit"));
                 p.setNbr_vu(rs.getInt("nbr_vu"));
                 p.setCategorie(new Categorie (rs.getInt("categorie")));
                 produit.add(p);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return produit;
    }  

public void filtreprix( List<Produit> produit, double min, double max){
       
        
         produit.stream().filter(pp->pp.getPrix_produit()>min && pp.getPrix_produit()<max).forEach((t) -> {System.out.println(t);
        });
    }
}
