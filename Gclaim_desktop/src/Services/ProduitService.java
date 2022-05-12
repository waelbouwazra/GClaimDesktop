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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
    
       public void AddProduitPst(Produit p, String i) {
         
              String req = "insert into produit (nom_produit,description,prix_produit,date_ajout_produit,Qte_produit,categorie,nbr_vu) values (?,?,?,?,?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              pst.setString(1, p.getNom_produit());
              pst.setString(2, p.getDescription());
              pst.setDouble(3, p.getPrix_produit());
               pst.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
              pst.setInt(5, p.getQte_produit());
              pst.setInt(6, p.getCategorie().getId_categorie());
               pst.setInt(7, 0);
               
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
              pst.setString(2, i);
              
             
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
              System.out.println(ex.getMessage());}
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

public List<Produit> filtreprix( List<Produit> produit, double min, double max){
       
        return
         produit.stream().filter(pp->pp.getPrix_produit()>min && pp.getPrix_produit()<max).collect(Collectors.toList());
        
    }

public void filtrecateg( List<Produit> produit, int categ){
       
        
         produit.stream().filter(pp->pp.getCategorie().getId_categorie()==categ).forEach((t) -> {System.out.println(t);
        });
    }


public List<Produit> plusvu( List<Produit> produit){
       
         return
         produit.stream()
        .filter(e -> e.getNbr_vu() != 0).collect(Collectors.toList());
      
        }
    /* public Set<Produit> plusvu( List<Produit> produit){
       
        
        Set<Produit> p= (Set<Produit>) produit.stream()
        .filter(e -> e.getNbr_vu() != 0);
       return p;
        }*/

    public String nbrprod( List<Produit> produit){
       
         int nbr;
         nbr = (int) produit.stream().count();
           String s=Integer.toString(nbr);
         return  s;
         
      
        }
    
public String getImage(int id) {
       String image="";
        String query = "select * from images where produit='"+id+"'";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {
       image=rs.getString("url_image");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return image;
    }
    
    
        public List<Integer> getIdproduit() {
        List<Integer> produit = new ArrayList<>();
        String query = "select * from produit";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {
        produit.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return produit;
    }
        
         public Produit getProduitByID(int id){
        Produit produit = new Produit();
        String sql="select * from produit where id_produit ="+id+"";
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
               produit=p;
              
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return produit;
    } 
         
         public int getIdPro(String nom , String description, Double prix, int qte){
      String req = "SELECT * FROM produit WHERE nom_produit=? and description=? and prix_produit=? and Qte_produit=? ";
Produit a =new Produit();
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, nom);
            pst.setString(2, description);
            pst.setDouble(3, prix);
            pst.setInt(4, qte);
            

            ResultSet as = pst.executeQuery();
            if (as.next()) {
             return as.getInt(1);
         
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    
}
         
         public void updatevu(Produit p)
        { String req ="UPDATE produit set nbr_vu=? WHERE id_produit =" +p.getId_produit()+ " ";
        try {
              pst = cnx.prepareStatement(req);
              pst.setInt(1, p.getNbr_vu()+1);
             System.out.println(p.getNbr_vu());
              pst.executeUpdate();
             System.out.println("doneeee");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
            public void generateExcel() 
         {
           String sql = "select * from produit";
        Statement ste;
        try {
       
          ste=cnx.prepareStatement(sql);
               ResultSet rs = ste.executeQuery(sql);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("Produit details ");
            HSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id_produit");
            header.createCell(1).setCellValue("nom_produit");
            header.createCell(2).setCellValue("description");
            header.createCell(3).setCellValue("prix_produit");
            header.createCell(4).setCellValue("date_ajout_produit");
            header.createCell(5).setCellValue("Qte_produit");
            header.createCell(6).setCellValue("nbr_vu");
            header.createCell(7).setCellValue("categorie");
            int index = 1;
            while (rs.next()) {
                System.out.println(rs.getString("id_produit"));
                HSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(rs.getString("id_produit"));
                row.createCell(1).setCellValue(rs.getString("nom_produit"));
                row.createCell(2).setCellValue(rs.getString("description"));
                row.createCell(3).setCellValue(rs.getString("prix_produit"));
                row.createCell(4).setCellValue(rs.getString("date_ajout_produit"));
                row.createCell(5).setCellValue(rs.getString("Qte_produit"));
                row.createCell(6).setCellValue(rs.getString("nbr_vu"));
                row.createCell(7).setCellValue(rs.getString("categorie"));
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\souma\\Desktop\\produitsDetails.Xls");
            wb.write(fileOut);
            fileOut.close();
           ste.close();
           rs.close();

        } catch (SQLException e) {
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

       

    }
   
}
