/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.Equipe;
import Entities.Jeu;
import Entities.Produit;
import Entities.SimpleUtilisateur;

import Entities.Tournoi;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.sql.*;
import java.util.stream.Collectors;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
/**
 *
 * @author ElyesM
 */
public class ServiceTournoi {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public ServiceTournoi() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddTournoiPst(Tournoi c) {
         
              String req = "insert into tournoi (jeu_id,nomtournoi,description,datec,dateev,heureev,image) values (?,?,?,?,?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              pst.setInt(1, c.getJeu().getId());
              pst.setString(2, c.getNomtournoi());
              pst.setString(3, c.getDescription());
              pst.setDate(4, Date.valueOf(LocalDate.now()));
              pst.setDate(5, Date.valueOf(c.getDatev()));
              pst.setTime(6, Time.valueOf(c.getHeurev()));
              pst.setString(7, c.getImage());
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(ServiceTournoi.class.getName()).log(Level.SEVERE, null, ex);
          }

        

    }
    
        public void DeleteTournoi(int c){
          try {
              String req = "DELETE from tournoi  WHERE id =" +c+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(ServiceTournoi.class.getName()).log(Level.SEVERE, null, ex);}
          }
        
        public void UpdateTournoi(Tournoi c,int cu)
        { String req ="UPDATE tournoi set jeu_id=?,nomtournoi=? , description=?,dateev=?,heureev=?,image=? WHERE id =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
            pst.setInt(1, c.getJeu().getId());
              pst.setString(2, c.getNomtournoi());
              pst.setString(3, c.getDescription());
              pst.setDate(4, Date.valueOf(c.getDatev()));
              pst.setTime(5, Time.valueOf(c.getHeurev()));
              pst.setString(6, c.getImage());
              pst.executeUpdate();
              System.out.println("Tournoi modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<Tournoi> ShowTournoi(){
             ServiceJeu sj=new ServiceJeu();
        List<Tournoi> tournoi = new ArrayList<>();
        String sql="select * from tournoi";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Tournoi c = new Tournoi();
                 c.setId(rs.getInt("id"));
                 c.setNomtournoi(rs.getString("nomtournoi"));
                 c.setDescription(rs.getString("description"));
                 c.setDatec(rs.getDate("datec"));
                 c.setDatev(rs.getString("dateev"));     
                 c.setHeurev(rs.getString("heureev"));   
                 c.setImage(rs.getString("image"));
                 c.setJeu(sj.ShowJeuById(new Jeu(rs.getInt("jeu_id"))));
                 tournoi.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return tournoi;
    }
        public void RejoindreTournoi(Equipe p,Tournoi t) {
           

        String req =  "insert into tournoi_equipe (tournoi_id,equipe_id) values (?,?)";
      
        try {

            pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.setInt(2, p.getId());
            pst.executeUpdate();
           
            System.out.println("rejoindre tournoi done");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
           public void quitterTournoi(Equipe p,Tournoi t) {
           

        String req =  "DELETE FROM `tournoi_equipe` WHERE `tournoi_equipe`.`tournoi_id` = ? AND `tournoi_equipe`.`equipe_id`=? ";
      
        try {

            pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.setInt(2, p.getId());
            pst.executeUpdate();
           
            System.out.println("quitter tournoi done");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 public Tournoi ShowTournoiById(int id){
        ServiceJeu sj=new ServiceJeu();
        Tournoi tournoi = new Tournoi();
        String sql="select * from tournoi where id="+id;
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Tournoi c = new Tournoi();
                 c.setId(rs.getInt("id"));
                 c.setNomtournoi(rs.getString("nomtournoi"));
                 c.setDescription(rs.getString("description"));
                 c.setDatec(rs.getDate("datec"));
                 c.setDatev(rs.getString("dateev"));     
                 c.setHeurev(rs.getString("heureev"));               
                 c.setJeu(sj.ShowJeuById(new Jeu(rs.getInt("jeu_id"))));
                 tournoi=c;
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return tournoi;
    }
  public List<Tournoi> ShowTournoiByIds(int id){
        ServiceJeu sj=new ServiceJeu();
        List<Tournoi> tournoi = new ArrayList<>();
        String sql="select * from tournoi where id="+id;
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Tournoi c = new Tournoi();
                 c.setId(rs.getInt("id"));
                 c.setNomtournoi(rs.getString("nomtournoi"));
                 c.setDescription(rs.getString("description"));
                 c.setDatec(rs.getDate("datec"));
                 c.setDatev(rs.getString("dateev"));     
                 c.setHeurev(rs.getString("heureev"));   
                 c.setImage(rs.getString("image"));
                 c.setJeu(sj.ShowJeuById(new Jeu(rs.getInt("jeu_id"))));
                 tournoi.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return tournoi;
    }
           

     public List<Integer> afficheTourEq(int id) {
        List<Integer> nom = new ArrayList<>();
        String query = " select * from tournoi u , tournoi_equipe e WHERE u.id = e.tournoi_id and e.equipe_id = "+id+"";
        Statement ste;
        try {
           
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

               
                nom.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nom;
    }
        
 public void Rechercher( List<Tournoi> tournoi, String nomTournoi){
       
        
         tournoi.stream().filter(cc->cc.getNomtournoi().equals(nomTournoi)).forEach((t) -> {System.out.println(t);
        });
    }

 public void TriTournoi(List<Tournoi> tournoi){
        
        tournoi.stream().sorted((o1, o2)->o1.getNomtournoi().
                                                                compareTo(o2.getNomtournoi())).
                                                                collect(Collectors.toList()).forEach(t-> System.out.println(t));
        
    }

 public void nbrtourn( List<Tournoi> tournoi){
       
         int nbr;
         nbr = (int) tournoi.stream().count();
         System.out.println(nbr);
      
        }
       
public void filtreidJ( List<Tournoi> tournoi, int idj){
       
        
         tournoi.stream().filter(pp->pp.getJeu().getId()==idj).forEach((t) -> {System.out.println(t);
        });
    }
 public List<Integer> getIdTournoi() {
        List<Integer> tournoi = new ArrayList<>();
        String query = "select * from tournoi";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {
        tournoi.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          return tournoi;
    }
   public List<Tournoi> cherchetournoi(Object o) {
            String query="";
            String ch="";
            int i=0;
            List<Tournoi> tournoi = new ArrayList<>();
            if(o.getClass()==ch.getClass()){
                ch=(String) o;
                query="SELECT * FROM `tournoi` WHERE `nomtournoi` LIKE '%" + ch + "%' OR `description` LIKE '%" + ch +  "%'";
            }
            if(o instanceof Integer){
                i=(Integer) o;
                query="SELECT * FROM `tournoi` WHERE `datec` = " + i + " OR " + " `dateev` LIKE '%" + i +" OR " + " `heureev` LIKE '%" + i + "%' ";
            }
            try {
                //System.out.println(query);
                ServiceJeu sj=new ServiceJeu();
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                   
                  
                 Tournoi c = new Tournoi();
                 c.setId(rs.getInt("id"));
                 c.setNomtournoi(rs.getString("nomtournoi"));
                 c.setDescription(rs.getString("description"));
                 c.setDatec(rs.getDate("datec"));
                 c.setDatev(rs.getString("dateev"));     
                 c.setHeurev(rs.getString("heureev"));               
                 c.setJeu(sj.ShowJeuById(new Jeu(rs.getInt("jeu_id"))));
                 tournoi.add(c);
                 
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return tournoi;   
        }
   public Set<Tournoi> tripardate( List<Tournoi> u){
       
        Set<Tournoi> ensEmp2 = u.stream().collect(Collectors.toCollection(()->new TreeSet<Tournoi>((e1,e2)->e1.getDatev().compareTo(e2.getDatev()))));
        return ensEmp2;
    }
    public void generateExcel(int id ) {
            ServiceEquipe es=new ServiceEquipe();
            List<Integer> listE =es.afficheEqTour(id);
            
        
        try
        {
           
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFCellStyle csCouleur = wb.createCellStyle();
            csCouleur.setFillForegroundColor((short) 45);
            HSSFFont font = wb.createFont();
            font.setBold(true);
            font.setColor((short) 13);

            csCouleur.setFont(font);

            csCouleur.setFillPattern(csCouleur.SOLID_FOREGROUND);

            HSSFSheet sheet = wb.createSheet("abonnement details ");
            HSSFCellStyle style = createStyleForTitle(wb);
            HSSFRow header = sheet.createRow(0);

            header.createCell(0).setCellValue("Username");
            header.createCell(1).setCellValue("Email");
  
          

            header.getCell(0).setCellStyle(csCouleur);
            header.getCell(1).setCellStyle(csCouleur);
        

            
            int index = 1;
            for(Integer Eq : listE ){
                for(Utilisateur Ut : es.afficheUtilisateursduneEqu(Eq))
                {
                HSSFCellStyle cs1Couleur = wb.createCellStyle();
                cs1Couleur.setFillForegroundColor((short) 45);
                HSSFRow row = sheet.createRow(index);
                
                
                
                row.createCell(1).setCellValue(Ut.getUsername());
                row.createCell(0).setCellValue(Ut.getEmail());
                row.getCell(0).setCellStyle(cs1Couleur);
                row.getCell(1).setCellStyle(cs1Couleur);
               
                index++;
            }
            }
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\souma\\Desktop\\participant.Xls");
            wb.write(fileOut);
            fileOut.close();
            
            }catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        }

    
      private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);

        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
   
}

