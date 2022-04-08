/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Coach;
import Entities.SimpleUtilisateur;
import Entities.Utilisateur;
import Tools.MaConnection;
import Entities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Tools.*;

/**
 *
 * @author souma
 */
public class ServiceUser {

    Connection ct;

    public ServiceUser() {
        ct =MaConnection.getInstance().getConnection();
    }
//jdbc native
    public void ajouterPersonne(Utilisateur p) {
        if (p instanceof SimpleUtilisateur) {
            String query = "insert into utilisateur(password,email,type,fullname,specialite,username,verifpassword,is_verified,roles,role) values(?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement ste = ct.prepareStatement(query);
                ste.setString(1, p.getPassword());
                ste.setString(2, p.getEmail());
                ste.setString(3, "simpleutilisateur");
                ste.setString(4, ((SimpleUtilisateur) p).getFullname());
                ste.setString(5, "NULL");
                ste.setString(6, p.getUsername());
                ste.setString(7, p.getVerifpassword());
                ste.setInt(8, 1);
                ste.setString(9, "['ROLE_USER']");
                ste.setInt(10, 0);
                ste.executeUpdate();
                System.out.println("SimpleUtilisateur ajoutée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (p instanceof Coach) {
            String query = "insert into utilisateur(password,email,type,fullname,specialite,username,verifpassword,is_verified,roles,role) values(?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement ste = ct.prepareStatement(query);
                ste.setString(1, p.getPassword());
                ste.setString(2, p.getEmail());
                ste.setString(3, "coach");
                ste.setString(4, "NULL");
                ste.setString(5, ((Coach) p).getSpecialite());
                ste.setString(6, p.getUsername());
                ste.setString(7, p.getVerifpassword());
                ste.setInt(8, 1);
                ste.setString(9, "['ROLE_COACH']");
                ste.setInt(10, 0);
                ste.executeUpdate();
                System.out.println("coach ajoutée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

    public List<Utilisateur> afficheSimpleUser() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);
        
            while (rs.next()) {
               
                if(rs.getString(4).equals("simpleutilisateur"))
                {SimpleUtilisateur p = new SimpleUtilisateur();
                p.setId(rs.getInt(1));
                p.setPassword(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setFullname(rs.getString(5));
                p.setUsername(rs.getString(7));
                p.setVerifpassword(rs.getString(8));
                personnes.add(p);
                }
    
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
public List<Utilisateur> afficheSimpleCoach() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);
        
            while (rs.next()) {
              
               if(rs.getString(4).equals("coach"))
                { Coach p = new Coach();
                p.setId(rs.getInt(1));
                p.setPassword(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setSpecialite(rs.getString(6));
                p.setUsername(rs.getString(7));
                p.setVerifpassword(rs.getString(8));
                personnes.add(p);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
public void DeleteUser(int p){
       
              String req = "DELETE from utilisateur  WHERE id =" +p+ " ";
              Statement ste;
                 try {
              ste = ct.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              
                     System.out.println(ex.getMessage());
          
          }
          }

   public void UpdatePersonne(Utilisateur p,int pu) {
         if (p instanceof SimpleUtilisateur) {
            String query = "UPDATE utilisateur set password=?,email=?,type=?,fullname=?,specialite=?,username=?,verifpassword=?,is_verified=?,roles=?,role=? WHERE id =" +pu+ "";
            try {
                PreparedStatement ste = ct.prepareStatement(query);
                ste.setString(1, p.getPassword());
                ste.setString(2, p.getEmail());
                ste.setString(3, "simpleutilisateur");
                ste.setString(4, ((SimpleUtilisateur) p).getFullname());
                ste.setString(5, "NULL");
                ste.setString(6, p.getUsername());
                ste.setString(7, p.getVerifpassword());
                ste.setInt(8, 1);
                ste.setString(9, "['ROLE_USER']");
                ste.setInt(10, 0);
                ste.executeUpdate();
                System.out.println("SimpleUtilisateur modifie");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (p instanceof Coach) {
          String query = "UPDATE utilisateur set password=?,email=?,type=?,fullname=?,specialite=?,username=?,verifpassword=?,is_verified=?,roles=?,role=? WHERE id =" +pu+ "";
            try {
                 PreparedStatement ste = ct.prepareStatement(query);
                ste.setString(1, p.getPassword());
                ste.setString(2, p.getEmail());
                ste.setString(3, "coach");
                ste.setString(4, "NULL");
                ste.setString(5, ((Coach) p).getSpecialite());
                ste.setString(6, p.getUsername());
                ste.setString(7, p.getVerifpassword());
                ste.setInt(8, 1);
                ste.setString(9, "['ROLE_COACH']");
                ste.setInt(10, 0);
                ste.executeUpdate();
              
                                System.out.println("coach modifie");

            } catch (SQLException ex) {
                                System.out.println("erreur");

                System.out.println(ex.getMessage());
            }
        }

    }
    public void accepterdevenircoach(Utilisateur p) {
        String req = "DELETE from utilisateur  WHERE id =" +p.getId()+ " ";
              Statement ste;
                 try {
              ste = ct.createStatement();
              ste.executeUpdate(req);
              System.out.println("done");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              
                     System.out.println(ex.getMessage());
          
          }
            String query = "insert into utilisateur(password,email,type,fullname,specialite,username,verifpassword,is_verified,roles,role) values(?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement ste1 = ct.prepareStatement(query);
                ste1.setString(1, p.getPassword());
                ste1.setString(2, p.getEmail());
                ste1.setString(3, "coach");
                ste1.setString(4, "NULL");
                ste1.setString(5,"rien");
                ste1.setString(6, p.getUsername());
                ste1.setString(7, p.getVerifpassword());
                ste1.setInt(8, 1);
                ste1.setString(9, "['ROLE_COACH']");
                ste1.setInt(10, 0);
                ste1.executeUpdate();
                System.out.println("demande acceptée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        

    }
public void refuserdevenircoach(Utilisateur p) {
        
            String query = "UPDATE utilisateur set role=? WHERE id =" +p.getId()+ "";
            try {
                PreparedStatement ste1 = ct.prepareStatement(query);
                ste1.setInt(1, 0);
                ste1.executeUpdate();
                System.out.println("demande refusée");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        

    }

   public void demandedevenircoach(Utilisateur p) {
        
            String query = "UPDATE utilisateur set role=? WHERE id =" +p.getId()+ "";
            try {
                PreparedStatement ste1 = ct.prepareStatement(query);
                ste1.setInt(1, 1);
                ste1.executeUpdate();
                System.out.println("demande de devenir coach est en cours de traitement ");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        

    }
   public void desactiveruncompte(Utilisateur p) {
        
            String query = "UPDATE utilisateur set is_verified=? WHERE id =" +p.getId()+ "";
            try {
                PreparedStatement ste1 = ct.prepareStatement(query);
                ste1.setInt(1, 0);
                ste1.executeUpdate();
                System.out.println("désactivation du compte est faite  ");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        

    }
   public void activeruncompte(Utilisateur p) {
        
            String query = "UPDATE utilisateur set is_verified=? WHERE id =" +p.getId()+ "";
            try {
                PreparedStatement ste1 = ct.prepareStatement(query);
                ste1.setInt(1, 1);
                ste1.executeUpdate();
                System.out.println("activation du compte est faite  ");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        

    }
   public List<Utilisateur> affichelesdemandededevenircoach() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.role=1";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);
        
            while (rs.next()) {
              
                SimpleUtilisateur p = new SimpleUtilisateur();
                p.setId(rs.getInt(1));
                p.setPassword(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setFullname(rs.getString(6));
                p.setUsername(rs.getString(7));
                p.setVerifpassword(rs.getString(8));
                personnes.add(p);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }

public List<Utilisateur> affichesimpleutilisateursactiver() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=1";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);
        
            while (rs.next()) {
              
                if(rs.getString(4).equals("simpleutilisateur"))
                {SimpleUtilisateur p = new SimpleUtilisateur();
                p.setId(rs.getInt(1));
                p.setPassword(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setFullname(rs.getString(6));
                p.setUsername(rs.getString(7));
                p.setVerifpassword(rs.getString(8));
                personnes.add(p);
                
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }

public List<Utilisateur> affichesimpleutilisateursdesactiver() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=0";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);
        
            while (rs.next()) {
              
                if(rs.getString(4).equals("simpleutilisateur"))
                {SimpleUtilisateur p = new SimpleUtilisateur();
                p.setId(rs.getInt(1));
                p.setPassword(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setFullname(rs.getString(6));
                p.setUsername(rs.getString(7));
                p.setVerifpassword(rs.getString(8));
                personnes.add(p);
                
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
public List<Utilisateur> affichecoachsactiver() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=1";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);
        
            while (rs.next()) {
              
              
                if(rs.getString(4).equals("coach"))
                {Coach p = new Coach();
                p.setId(rs.getInt(1));
                p.setPassword(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setSpecialite(rs.getString(6));
                p.setUsername(rs.getString(7));
                p.setVerifpassword(rs.getString(8));
                personnes.add(p);
                
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
public List<Utilisateur> affichecoachdesactiver() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=0";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);
        
            while (rs.next()) {
              
                if(rs.getString(4).equals("coach"))
                {Coach p = new Coach();
                p.setId(rs.getInt(1));
                p.setPassword(rs.getString(2));
                p.setEmail(rs.getString(3));
                p.setSpecialite(rs.getString(6));
                p.setUsername(rs.getString(7));
                p.setVerifpassword(rs.getString(8));
                personnes.add(p);
                
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
}
