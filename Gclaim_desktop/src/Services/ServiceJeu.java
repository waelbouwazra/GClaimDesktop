/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Jeu;
import Entities.Tournoi;
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
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author ElyesM
 */
public class ServiceJeu {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public ServiceJeu() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddJeuPst(Jeu c) {
         
              String req = "insert into jeu (nomjeu,description,createur,date_s,image) values (?,?,?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              pst.setString(1, c.getNomjeu());
              pst.setString(2, c.getDescription());
              pst.setString(3, c.getCreateur());
              pst.setDate(4, Date.valueOf(LocalDate.now()));
              pst.setString(5, c.getImage());

              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(ServiceJeu.class.getName()).log(Level.SEVERE, null, ex);
          }

        

    }
    
        public void DeleteJeu(int c){
          try {
              String req = "DELETE from jeu  WHERE id =" +c+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(ServiceJeu.class.getName()).log(Level.SEVERE, null, ex);}
          }
        
        public void UpdateJeu(Jeu c,int cu)
        { String req ="UPDATE jeu set nomjeu=? , description=?,createur=?,image=? WHERE id =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
              pst.setString(1, c.getNomjeu());
              pst.setString(2, c.getDescription());
              pst.setString(3, c.getCreateur());
              pst.setString(4, c.getImage());

              pst.executeUpdate();
              System.out.println("Jeu modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<Jeu> ShowJeu(){
        List<Jeu> jeu = new ArrayList<>();
        String sql="select * from jeu";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Jeu c = new Jeu();
                 c.setId(rs.getInt("id"));
                 c.setNomjeu(rs.getString("nomjeu"));
                 c.setDescription(rs.getString("description"));
                 c.setCreateur(rs.getString("createur"));
                 c.setDatec(rs.getDate("date_s"));
                 c.setImage(rs.getString("image"));
                 jeu.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return jeu;
    }
        
 public void Rechercher( List<Jeu> jeu, String nomJeu){
       
        
         jeu.stream().filter(cc->cc.getNomjeu().equals(nomJeu)).forEach((t) -> {System.out.println(t);
        });
    }

 public void TriTournoi(List<Jeu> jeu){
        
        jeu.stream().sorted((o1, o2)->o1.getNomjeu().
                                                                compareTo(o2.getNomjeu())).
                                                                collect(Collectors.toList()).forEach(t-> System.out.println(t));
        
    }
  public List<Integer> getIdJeu() {
        List<Integer> jeu = new ArrayList<>();
        String query = "select * from jeu";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {
        jeu.add(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          return jeu;
    }
  public List<String> getNomJeu(){
        List<String> jeu = new ArrayList<>();
        String sql="select * from jeu";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 String c;
                 
                 c=rs.getString("nomjeu");
              
                 jeu.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return jeu;
    }
 public Jeu ShowJeuById(Jeu id){
        Jeu jeu = new Jeu();
        String sql="select * from jeu where id="+id.getId();
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Jeu c = new Jeu();
                 c.setId(rs.getInt("id"));
                 c.setNomjeu(rs.getString("nomjeu"));
                 c.setDescription(rs.getString("description"));
                 c.setCreateur(rs.getString("createur"));
                 c.setDatec(rs.getDate("date_s"));
                 c.setImage(rs.getString("image"));
                 jeu=c;
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return jeu;
    }
 public Jeu ShowJeuByNom(String nom){
        Jeu jeu = new Jeu();
        String sql="select * from jeu WHERE nomjeu = '" + nom + "'";
        Statement ste;
       System.out.println(sql);
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Jeu c = new Jeu();
                 c.setId(rs.getInt("id"));
                 c.setNomjeu(rs.getString("nomjeu"));
                 c.setDescription(rs.getString("description"));
                 c.setCreateur(rs.getString("createur"));
                 c.setDatec(rs.getDate("date_s"));
                 c.setImage(rs.getString("image"));
                 jeu=c;
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return jeu;
    }
 public List<Jeu> cherchejeu(Object o) {
            String query="";
            String ch="";
            int i=0;
            List<Jeu> jeu = new ArrayList<>();
           
                ch=(String) o;
                query="SELECT * FROM `jeu` WHERE `nomjeu` LIKE '%" + ch + "%' OR `description` LIKE '%" + ch + "%' OR `createur` LIKE '%" + ch + "%'";
          
            try {
                //System.out.println(query);
                ServiceJeu sj=new ServiceJeu();
                PreparedStatement ste = cnx.prepareStatement(query);
                ResultSet rs= ste.executeQuery();
                while(rs.next()){
                   
                  
                  Jeu c = new Jeu();
                 c.setId(rs.getInt("id"));
                 c.setNomjeu(rs.getString("nomjeu"));
                 c.setDescription(rs.getString("description"));
                 c.setCreateur(rs.getString("createur"));
                 c.setDatec(rs.getDate("date_s"));
                 c.setImage(rs.getString("image"));
                 jeu.add(c);
                 
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }

            return jeu;   
        }
   public Set<Jeu> tripardate( List<Jeu> u){
       
        Set<Jeu> ensEmp2 = u.stream().collect(Collectors.toCollection(()->new TreeSet<Jeu>((e1,e2)->e1.getDatec().compareTo(e2.getDatec()))));
        return ensEmp2;
    }
}
