/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Categorie;
import Tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.TreeSet;

/**
 *
 * @author azizk
 */
public class CategorieService {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public CategorieService() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddCategoriePst(Categorie c) {
         
              String req = "insert into categorie (nom_categorie,type_categorie) values (?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              pst.setString(1, c.getNom_categorie());
              pst.setString(2, c.getType_categorie());
              
              
             
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
          }

        

    }
    
        public void DeleteCategorie(int c){
          try {
              String req = "DELETE from categorie  WHERE id_categorie =" +c+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
                  System.out.println(ex.getMessage());
            }
          }
        
        public void UpdateCategorie(Categorie c,int cu)
        { String req ="UPDATE categorie set nom_categorie=? , type_categorie=? WHERE id_categorie =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
              pst.setString(1, c.getNom_categorie());
              pst.setString(2, c.getType_categorie());
              
              pst.executeUpdate();
              System.out.println(c);
              System.out.println("categorie modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<Categorie> ShowCategorie(){
        List<Categorie> categorie = new ArrayList<>();
        String sql="select * from categorie";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Categorie c = new Categorie();
                 c.setId_categorie(rs.getInt("id_categorie"));
                 c.setNom_categorie(rs.getString("nom_categorie"));
                 c.setType_categorie(rs.getString("type_categorie"));
                 
                 categorie.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return categorie;
    }
        
 public List<Categorie> Rechercher( List<Categorie> categorie, String nom_categorie){
       
        return
         categorie.stream().filter(cc->cc.getNom_categorie().equals(nom_categorie)).collect(Collectors.toList());
        
         
    }

 public Set<Categorie> TriCategorie(List<Categorie> categorie){
         Set<Categorie> trica = categorie.stream().collect(Collectors.toCollection(()->new TreeSet<Categorie>((e1,e2)->e1.getNom_categorie().compareTo(e2.getNom_categorie()))));
         
         
      // Set<Categorie> trica categorie.stream().collect(Collectors.toCollection()) -> new TreeSet<Categorie>((o1,o2)->o1.getNom_categorie().compareTo(o2.getNom_categorie()));
               
             //  sorted((o1, o2)->o1.getNom_categorie().
                                                            //    compareTo(o2.getNom_categorie())).
                                                            //    collect(Collectors.toList());
       return trica;

    }
 public List<Integer> getIdcategorie() {
        List<Integer> categorie = new ArrayList<>();
        String query = "select * from categorie";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {
        categorie.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return categorie;
    }
 
 public int getIdCateg(String nom , String type){
      String req = "SELECT * FROM categorie WHERE nom_categorie=? and type_categorie=? ";
Categorie a =new Categorie();
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, nom);
            pst.setString(2, type);

            ResultSet as = pst.executeQuery();
            if (as.next()) {
             return as.getInt(1);
         
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    
}
 
  public int getIdCategnom(String nom ){
      String req = "SELECT * FROM categorie WHERE nom_categorie=? ";
Categorie a =new Categorie();
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, nom);
            

            ResultSet as = pst.executeQuery();
            if (as.next()) {
             return as.getInt(1);
         
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    
}
 
}
