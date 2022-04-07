/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Profil;
import Tools.MaConnection;
import Entities.Utilisateur;
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
 * @author azizk
 */
public class ProfilService {
     private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public ProfilService() {
         cnx = MaConnection.getInstance().getConnection();
    }
    
       public void AddProfil(Profil c) {
         
              String req = "insert into profil (user_id,description,username,game,numero) values (?,?,?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
             pst.setInt(1, c.getUser().getId());
              pst.setString(2, c.getDescription());
               pst.setString(3, c.getUsername());
              pst.setString(4, c.getGame());
               pst.setInt(5, c.getNumero());
              
              
              
             
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(ProfilService.class.getName()).log(Level.SEVERE, null, ex);
          }

        

    }
    
        public void DeleteProfil(int c){
          try {
              String req = "DELETE from profil  WHERE id =" +c+ " ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(ProfilService.class.getName()).log(Level.SEVERE, null, ex);}
          }
        
        public void UpdateProfil(Profil c,int cu)
        { String req ="UPDATE profil set description=? , game=? WHERE id =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
              pst.setString(2, c.getDescription());
              pst.setString(3, c.getUsername());
              
              pst.executeUpdate();
              System.out.println("Profil modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
        
         public List<Profil> ShowProfil(){
        List<Profil> profil = new ArrayList<>();
        String sql="select * from profil";
        Statement ste;
       
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Profil c = new Profil();
                 c.setId(rs.getInt("id"));
                 c.setUsername(rs.getString("username"));
                 c.setDescription(rs.getString("description"));
                 c.setGame(rs.getString("game"));
                c.setNumero(rs.getInt("numero"));
                Utilisateur e =new Utilisateur (rs.getInt("user_id"));
                c.setUser(e);
                 profil.add(c);
                 
        }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return profil;
    }
        


}
