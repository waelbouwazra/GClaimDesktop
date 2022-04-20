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
import java.util.stream.Collectors;

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
         
              String req = "insert into profil (description,username,game,numero,user_id) values (?,?,?,?,?)";
              try { 
              
              pst = cnx.prepareStatement(req);
              
             pst.setInt(5, c.getUser().getId()); // implementer cette methode quand la session marche
              pst.setString(1, c.getDescription());
               pst.setString(2, c.getUsername());
              pst.setString(3, c.getGame());
               pst.setInt(4, c.getNumero());
              
              
              
             
              pst.executeUpdate();
          } catch (SQLException ex) {
              Logger.getLogger(ProfilService.class.getName()).log(Level.SEVERE, null, ex);
          }

        

    }
    public Profil getuserbyID(int id) {
         String sql = "SELECT * FROM profil where id=" + id + "";
                ServiceUser serviceUser = new ServiceUser();

          Profil p =new Profil();
         Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);

            while (rs.first()) {
                    p.setId(rs.getInt(1));
                    p.setDescription(rs.getString("description"));
                    p.setUsername(rs.getString("username"));
                 p.setUser(serviceUser.getuserbyID(rs.getInt("user_id")));
                    p.setGame(rs.getString("game"));
                    p.setNumero(rs.getInt("numero"));
                    //System.out.println(p);
                    return p;
 /* id;
    private String Description;
    private String Username;
    private Utilisateur user;
    private String game;
    private int numero;*/
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(p);
        return p;
}
        public void DeleteProfil(int c){
          try {
              String req = "DELETE from profil  WHERE id ="+c+" ";
              
              ste = cnx.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(ProfilService.class.getName()).log(Level.SEVERE, null, ex);}
          }
        
        public void UpdateProfil(Profil c)
        {   String req = "UPDATE profil set description=?,game=?,numero=? WHERE id =" +c.getId()+ "";
        try {
              pst = cnx.prepareStatement(req);             
              pst.setString(1, c.getDescription());
           
               pst.setString(2, c.getGame());
                pst.setInt(3, c.getNumero());
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
         public void TriProfil(List<Profil> categorie){
        
        categorie.stream().sorted((o1, o2)->o1.getUsername().
                                                                compareTo(o2.getUsername())).
                                                                collect(Collectors.toList()).forEach(t-> System.out.println(t));
        
        
        
    }
        


}
