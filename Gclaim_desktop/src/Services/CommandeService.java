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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
        
        public void UpdateCommande(Commande c)
        { String req ="UPDATE commande set livrer=? WHERE id =" +c.getId()+ " ";
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
        public IntSummaryStatistics getStatistics(){
            List<Commande> l = this.ShowCommande();
           return l.stream().mapToInt(e->e.getTotal()).summaryStatistics();
            
            //return l;
        }
            public List<Commande> getLeastCommande(){
            List<Commande> l = this.triCommande();
           return l.stream().limit(1).collect(Collectors.toList());
            //return l;
        }
            public List<Commande> getTopCommande(){
            List<Commande> l = this.ShowCommande();
             l=l.stream().sorted((o1, o2)->o2.getTotal()-o1.getTotal()).
             collect(Collectors.toList());
             l=l.stream().limit(1).collect(Collectors.toList());
            return l;
        }
        public List<Commande> triCommande(){
             List<Commande> l = this.ShowCommande();
              l=l.stream().sorted((o1, o2)->o2.getTotal()-o1.getTotal()).
             collect(Collectors.toList());
        
            return l;
        }
       public List<Commande> rechercherCommande(int Username){
             List<Commande> l = this.ShowCommande();
                 
         //l.stream().filter(cc->cc.getUser().getUsername().equals(Username));
        l.stream().filter(cc->cc.getUser().getId()==Username);
        
            return l;
        }
       public Commande getSingleCommande(int id){
       Commande commande = new Commande();
        String sql="select * from commande where id="+id;
        Statement ste;
       ServiceUser serviceUser = new ServiceUser();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 
                 
                 Commande c = new Commande();
                 c.setId(rs.getInt("id"));
                 c.setTotal(rs.getInt("total"));
                 c.setLivrer(rs.getBoolean("livrer"));
                
                 c.setUser(serviceUser.getuserbyID(rs.getInt("user")));
                 c.setDate_achat(rs.getDate("date_achat"));
               
                 commande=c;
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return commande;
    }
         public List<Commande> ShowCommande(){
        List<Commande> commandes = new ArrayList<>();
        String sql="select * from commande";
        Statement ste;
       ServiceUser serviceUser = new ServiceUser();
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 
                 
                 Commande c = new Commande();
                 c.setId(rs.getInt("id"));
                 c.setTotal(rs.getInt("total"));
                 c.setLivrer(rs.getBoolean("livrer"));
                
                 c.setUser(serviceUser.getuserbyID(rs.getInt("user")));
                 c.setDate_achat(rs.getDate("date_achat"));
               
                 commandes.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return commandes;
    }
        public int getLastInsertedId(){
            
             String query1 = "select MAX(id) id  from commande";
            Statement ste;
            int x = 0;
            try {
                ste = cnx.createStatement();
                ResultSet rs = ste.executeQuery(query1);
                while (rs.next()) {
                    x = rs.getInt("id");
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            return x;
        }
         private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);

        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void generateExcel() {
        String sql = "select * from commande";
        Statement ste;
        try {

            ste = cnx.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFCellStyle csCouleur = wb.createCellStyle();
            csCouleur.setFillForegroundColor((short) 45);
            HSSFFont font = wb.createFont();
            font.setBold(true);
            font.setColor((short) 13);

            csCouleur.setFont(font);

            csCouleur.setFillPattern(csCouleur.SOLID_FOREGROUND);

            HSSFSheet sheet = wb.createSheet("Commande details ");
            HSSFCellStyle style = createStyleForTitle(wb);
            HSSFRow header = sheet.createRow(0);

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("total");
            header.createCell(2).setCellValue("livrer ");
            header.createCell(3).setCellValue("date Achat");
            

            header.getCell(0).setCellStyle(csCouleur);
            header.getCell(1).setCellStyle(csCouleur);
            header.getCell(2).setCellStyle(csCouleur);
            header.getCell(3).setCellStyle(csCouleur);
         

            
            int index = 1;
            while (rs.next()) {
                HSSFCellStyle cs1Couleur = wb.createCellStyle();
                cs1Couleur.setFillForegroundColor((short) 45);
                HSSFRow row = sheet.createRow(index);
                
                
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("total"));
                row.createCell(2).setCellValue(rs.getString("livrer"));
                row.createCell(3).setCellValue(rs.getString("date_achat"));
              
            row.getCell(0).setCellStyle(cs1Couleur);
                row.getCell(1).setCellStyle(cs1Couleur);
                row.getCell(2).setCellStyle(cs1Couleur);
                row.getCell(3).setCellStyle(cs1Couleur);
               
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\souma\\Desktop\\commandes.Xls");
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
