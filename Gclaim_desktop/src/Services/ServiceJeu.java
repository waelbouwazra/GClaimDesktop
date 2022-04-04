/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Jeu;
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
         
              String req = "insert into jeu (nomjeu,description,createur) values (?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              pst.setString(1, c.getNomjeu());
              pst.setString(2, c.getDescription());
              pst.setString(2, c.getCreateur());

              
              
             
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
        { String req ="UPDATE jeu set nomjeu=? , description=?,createur=? WHERE id =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
              pst.setString(2, c.getNomjeu());
              pst.setString(3, c.getDescription());
              pst.setString(2, c.getCreateur());

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
                 c.setNomjeu(rs.getString("nomtournoi"));
                 c.setDescription(rs.getString("description"));
                 c.setCreateur(rs.getString("createur"));
                 jeu.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return jeu;
    }
        


}
