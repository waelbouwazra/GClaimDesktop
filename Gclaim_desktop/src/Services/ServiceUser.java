

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
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


/**
 *
 * @author souma
 */
public class ServiceUser {

    Connection ct;

    public ServiceUser() {
        ct = MaConnection.getInstance().getConnection();
    }
    public static Utilisateur currentUser = new Utilisateur();

//jdbc native
    public int ajouterPersonne(Utilisateur p) {
        int resultat = 0;
        if (p instanceof SimpleUtilisateur) {
            String query = "insert into utilisateur(password,email,type,fullname,specialite,username,verifpassword,is_verified,roles,role) values(?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement ste = ct.prepareStatement(query);
                ste.setString(1,  mdpconvert(p.getPassword()));
                ste.setString(2, p.getEmail());
                ste.setString(3, "simpleutilisateur");
                ste.setString(4, ((SimpleUtilisateur) p).getFullname());
                ste.setString(5, "NULL");
                ste.setString(6, p.getUsername());
                ste.setString(7, mdpconvert(p.getVerifpassword()));
                ste.setInt(8, 1);
                ste.setString(9, "[\"ROLE_USER\"]");
                ste.setInt(10, 0);
                ste.executeUpdate();
                System.out.println("SimpleUtilisateur ajoutée");
                resultat = 1;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (p instanceof Coach) {
            String query = "insert into utilisateur(password,email,type,fullname,specialite,username,verifpassword,is_verified,roles,role) values(?,?,?,?,?,?,?,?,?,?)";
            try {
                PreparedStatement ste = ct.prepareStatement(query);
                ste.setString(1, mdpconvert(p.getPassword()));
                ste.setString(2, p.getEmail());
                ste.setString(3, "coach");
                ste.setString(4, "NULL");
                ste.setString(5, ((Coach) p).getSpecialite());
                ste.setString(6, p.getUsername());
                ste.setString(7, mdpconvert(p.getVerifpassword()));
                ste.setInt(8, 1);
                ste.setString(9, "[\"ROLE_COACH\"]");
                ste.setInt(10, 0);
                ste.executeUpdate();
                System.out.println("coach ajoutée");
                resultat = 1;
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return resultat;
    }
 public String mdpconvert(String p) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(p.getBytes());
            BigInteger pwd = new BigInteger(1, messageDigest);
            String hashpwd = pwd.toString(16);
            return hashpwd;

        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
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

                if (rs.getString(4).equals("simpleutilisateur")) {
                    SimpleUtilisateur p = new SimpleUtilisateur();
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

                if (rs.getString(4).equals("coach")) {
                    Coach p = new Coach();
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
public List<Integer> getIDUSER() {
        List<Integer> personnes = new ArrayList<>();
        String query = "select * from utilisateur";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {
if (rs.getString(4).equals("simpleutilisateur")) {
                    personnes.add(rs.getInt(1));
                
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
public List<Integer> getIDCOACH() {
        List<Integer> personnes = new ArrayList<>();
        String query = "select * from utilisateur";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {
if (rs.getString(4).equals("coach")) {
                    personnes.add(rs.getInt(1));
                
            }}
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
    public void DeleteUser(int p) {

        String req = "DELETE from utilisateur  WHERE id =" + p + " ";
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

    public void UpdatePersonne(Utilisateur p, int pu) {
        if (p instanceof SimpleUtilisateur) {
            String query = "UPDATE utilisateur set password=?,email=?,type=?,fullname=?,specialite=?,username=?,verifpassword=?,is_verified=?,roles=?,role=? WHERE id =" + pu + "";
            try {
                PreparedStatement ste = ct.prepareStatement(query);
                ste.setString(1, mdpconvert(p.getPassword()));
                ste.setString(2, p.getEmail());
                ste.setString(3, "simpleutilisateur");
                ste.setString(4, ((SimpleUtilisateur) p).getFullname());
                ste.setString(5, "NULL");
                ste.setString(6, p.getUsername());
                ste.setString(7, mdpconvert(p.getVerifpassword()));
                ste.setInt(8, 1);
                ste.setString(9, "[\"ROLE_USER\"]");
                ste.setInt(10, 0);
                ste.executeUpdate();
                System.out.println("SimpleUtilisateur modifie");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (p instanceof Coach) {
            String query = "UPDATE utilisateur set password=?,email=?,type=?,fullname=?,specialite=?,username=?,verifpassword=?,is_verified=?,roles=?,role=? WHERE id =" + pu + "";
            try {
                PreparedStatement ste = ct.prepareStatement(query);
                ste.setString(1, mdpconvert(p.getPassword()));
                ste.setString(2, p.getEmail());
                ste.setString(3, "coach");
                ste.setString(4, "NULL");
                ste.setString(5, ((Coach) p).getSpecialite());
                ste.setString(6, p.getUsername());
                ste.setString(7, mdpconvert(p.getVerifpassword()));
                ste.setInt(8, 1);
                ste.setString(9, "[\"ROLE_COACH\"]");
                ste.setInt(10, 0);
                ste.executeUpdate();

                System.out.println("coach modifie");

            } catch (SQLException ex) {
                System.out.println("erreur");

                System.out.println(ex.getMessage());
            }
            updateCurrentUser(p);
        }

    }

    public void accepterdevenircoach(Utilisateur p) {
        String req = "DELETE from utilisateur  WHERE id =" + p.getId() + " ";
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
            ste1.setString(1,p.getPassword());
            ste1.setString(2, p.getEmail());
            ste1.setString(3, "coach");
            ste1.setString(4, "NULL");
            ste1.setString(5, "rien");
            ste1.setString(6, p.getUsername());
            ste1.setString(7, p.getVerifpassword());
            ste1.setInt(8, 1);
            ste1.setString(9, "[\"ROLE_COACH\"]");
            ste1.setInt(10, 0);
            ste1.executeUpdate();
            System.out.println("demande acceptée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void refuserdevenircoach(Utilisateur p) {

        String query = "UPDATE utilisateur set role=? WHERE id =" + p.getId() + "";
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

        String query = "UPDATE utilisateur set role=? WHERE id =" + p.getId() + "";
        try {
            PreparedStatement ste1 = ct.prepareStatement(query);
            ste1.setInt(1, 1);
            ste1.executeUpdate();
            System.out.println("demande de devenir coach est en cours de traitement ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void desactiveruncompte(int p) {

        String query = "UPDATE utilisateur set is_verified=? WHERE id =" + p + "";
        try {
            PreparedStatement ste1 = ct.prepareStatement(query);
            ste1.setInt(1, 0);
            ste1.executeUpdate();
            System.out.println("désactivation du compte est faite  ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void activeruncompte(int p) {

        String query = "UPDATE utilisateur set is_verified=? WHERE id =" + p + "";
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

                if (rs.getString(4).equals("simpleutilisateur")) {
                    SimpleUtilisateur p = new SimpleUtilisateur();
                    p.setId(rs.getInt(1));
                    p.setPassword(rs.getString(2));
                    p.setEmail(rs.getString(3));
                    p.setFullname(rs.getString(6));
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

    public List<Utilisateur> affichesimpleutilisateursdesactiver() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=0";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                if (rs.getString(4).equals("simpleutilisateur")) {
                    SimpleUtilisateur p = new SimpleUtilisateur();
                    p.setId(rs.getInt(1));
                    p.setPassword(rs.getString(2));
                    p.setEmail(rs.getString(3));
                    p.setFullname(rs.getString(6));
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

    public List<Utilisateur> affichecoachsactiver() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=1";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                if (rs.getString(4).equals("coach")) {
                    Coach p = new Coach();
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
 public List<Integer> getIdcoachsactiver() {
        List<Integer> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=1";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                if (rs.getString(4).equals("coach")) {
                   
                    personnes.add(rs.getInt(1));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
 public List<Integer> getIdusersactiver() {
        List<Integer> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=1";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                if (rs.getString(4).equals("simpleutilisateur")) {
                   
                    personnes.add(rs.getInt(1));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
 public List<Integer> getIdcoachsdesactiver() {
        List<Integer> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=0";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                if (rs.getString(4).equals("coach")) {
                   
                    personnes.add(rs.getInt(1));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
 public List<Integer> getIdusersdesactiver() {
        List<Integer> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=0";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                if (rs.getString(4).equals("simpleutilisateur")) {
                   
                    personnes.add(rs.getInt(1));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
 public List<Integer> getIdDESDEMANDES() {
        List<Integer> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.role=1";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                if (rs.getString(4).equals("simpleutilisateur")) {
                   
                    personnes.add(rs.getInt(1));

                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return personnes;
    }
public Utilisateur getuserbyID(int id) {
         String sql = "SELECT * FROM utilisateur where id=" + id + "";
         
          Utilisateur p =new Utilisateur();
         Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(sql);

            while (rs.first()) {
                    p.setId(rs.getInt(1));
                    p.setPassword(rs.getString(2));
                    p.setEmail(rs.getString(3));
                    p.setUsername(rs.getString(7));
                    p.setVerifpassword(rs.getString(8));
                    //System.out.println(p);
                    return p;
 
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(p);
        return p;
}
    public List<Utilisateur> affichecoachdesactiver() {
        List<Utilisateur> personnes = new ArrayList<>();
        String query = "select * from utilisateur u where u.is_verified=0";
        Statement ste;
        try {
            ste = ct.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                if (rs.getString(4).equals("coach")) {
                    Coach p = new Coach();
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

    public boolean getUtilisateurByCin(String cin) {

        boolean exist = false;
        try {
            String sql = "SELECT * FROM utilisateur where username=?";
            PreparedStatement ste = ct.prepareStatement(sql);
            ste.setString(1, cin);
            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                exist = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return exist;

    }

    public boolean getUtilisateurByEmail(String email) {
        boolean exist = false;

        try {
            String sql = "SELECT * FROM utilisateur where email=?";
            PreparedStatement ste = ct.prepareStatement(sql);
            ste.setString(1, email);

            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                exist = true;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exist;

    }

    public String Seconnecter(String email, String motdepasse) {
        String sql = "Select * from utilisateur where email =?";
        String result = "failed";
        try {
            PreparedStatement ste = ct.prepareStatement(sql);

            ste.setString(1, email);
            ResultSet rs = ste.executeQuery();
            if (rs.next()) {
               String pwd = rs.getString("password");
                if (mdpconvert(motdepasse).equals(pwd))  {
                    result = "success";

                    Utilisateur user = new Utilisateur();

                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setVerifpassword(rs.getString("verifpassword"));
                    user.setRoles(rs.getString("roles"));
                    user.setIsVerfied(rs.getBoolean("is_verified"));

                    updateCurrentUser(user);

                } else {
                    System.out.println("mdp incorrecte");
                }

            } else {

                System.out.println("email introuvable");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());       }
        return result;

    }

    public void updateCurrentUser(Utilisateur user) {
        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setVerifpassword(user.getVerifpassword());
        currentUser.setEmail(user.getEmail());
        currentUser.setIsVerfied(user.isIsVerfied());
        currentUser.setRole(user.isRole());
        currentUser.setRoles(user.getRoles());
        
       
    }


    public String getRoleByCin(String email) throws SQLException {

        String roles = null;
        try {
            String sql = "SELECT `roles` FROM `utilisateur` WHERE `email` ='" + email + "'";
            PreparedStatement ste = ct.prepareStatement(sql);
            ResultSet rs = ste.executeQuery();//resultat requete sql

            while (rs.next()) {
                roles = rs.getString("roles");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return roles;
    }

    public boolean logOut() {
        currentUser.setId(0);
        currentUser.setUsername("");
        currentUser.setEmail("");
        currentUser.setPassword("");
        /*CurrentCoach.setId(0);
        CurrentCoach.setUsername("");
        CurrentCoach.setEmail("");
        CurrentCoach.setPassword("");*/
        return true;
    }
     public String nbrprod( List<Utilisateur> u){
       
         int nbr;
         nbr = (int) u.stream().count();
         String s=Integer.toString(nbr);
         return  s;
      
        }
     public Set<Utilisateur> tri( List<Utilisateur> u){
       
        Set<Utilisateur> ensEmp2 = u.stream().collect(Collectors.toCollection(()->new TreeSet<Utilisateur>((e1,e2)->e1.getUsername().compareTo(e2.getUsername()))));
        return ensEmp2;
    }
}
