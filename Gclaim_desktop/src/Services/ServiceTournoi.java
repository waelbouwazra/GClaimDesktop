/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Tournoi;
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
public class ServiceTournoi {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public ServiceTournoi() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddTournoiPst(Tournoi c) {
         
              String req = "insert into tournoi (nomtournoi,description) values (?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
              pst.setString(1, c.getNomtournoi());
              pst.setString(2, c.getDescription());
              
              
             
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
        { String req ="UPDATE tournoi set nomtournoi=? , description=? WHERE id =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
              pst.setString(2, c.getNomtournoi());
              pst.setString(3, c.getDescription());
              
              pst.executeUpdate();
              System.out.println("Tournoi modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<Tournoi> ShowTournoi(){
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
                 
                 tournoi.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return tournoi;
    }
        


}
