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
import com.twilio.Twilio;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.TreeSet;
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
 * @author souma
 */
public class ServiceUser {

    Connection ct;

    public ServiceUser() {
        ct = MaConnection.getInstance().getConnection();
    }
    public static Utilisateur currentUser;

//jdbc native
    public int ajouterPersonne(Utilisateur p) {
        int resultat = 0;
        if (p instanceof SimpleUtilisateur) {
            String query = "insert into utilisateur(password,email,type,fullname,specialite,username,verifpassword,is_verified,roles,role) values(?,?,?,?,?,?,?,?,?,?)";
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

                }
            }
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

                }
            }
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
            String query = "UPDATE utilisateur set email=?,type=?,fullname=?,specialite=?,username=?,is_verified=?,roles=?,role=? WHERE id =" + pu + "";
            try {
                PreparedStatement ste = ct.prepareStatement(query);

                ste.setString(1, p.getEmail());
                ste.setString(2, "simpleutilisateur");
                ste.setString(3, ((SimpleUtilisateur) p).getFullname());
                ste.setString(4, "NULL");
                ste.setString(5, p.getUsername());

                ste.setInt(6, 1);
                ste.setString(7, "[\"ROLE_USER\"]");
                ste.setInt(8, 0);
                ste.executeUpdate();
                System.out.println("SimpleUtilisateur modifie");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (p instanceof Coach) {
            String query = "UPDATE utilisateur set email=?,type=?,fullname=?,specialite=?,username=?,is_verified=?,roles=?,role=? WHERE id =" + pu + "";
            try {
                PreparedStatement ste = ct.prepareStatement(query);

                ste.setString(1, p.getEmail());
                ste.setString(2, "coach");
                ste.setString(3, "NULL");
                ste.setString(4, ((Coach) p).getSpecialite());
                ste.setString(5, p.getUsername());

                ste.setInt(6, 1);
                ste.setString(7, "[\"ROLE_COACH\"]");
                ste.setInt(8, 0);
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
            ste1.setString(1, p.getPassword());
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

    public Utilisateur getuserbyID(int id) {
        String sql = "SELECT * FROM utilisateur where id=" + id + "";

        Utilisateur p = new Utilisateur();
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

    public Utilisateur getuserbyEmail(String email) {
        String sql = "SELECT * FROM utilisateur where email=" + email + "";

        Utilisateur p = new Utilisateur();
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

    public String getUtilisateurByEmail1(String email) {

        try {
            String sql = "SELECT * FROM utilisateur where email=?";
            PreparedStatement ste = ct.prepareStatement(sql);
            ste.setString(1, email);

            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {

                return rs.getString("username");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return "";

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
                if (mdpconvert(motdepasse).equals(pwd)) {
                    result = "success";
                    if (rs.getString(4).equals("simpleutilisateur")) {
                        Utilisateur user = new SimpleUtilisateur();

                        user.setId(rs.getInt("id"));
                        user.setEmail(rs.getString("email"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setVerifpassword(rs.getString("verifpassword"));
                        user.setRoles(rs.getString("roles"));
                        user.setIsVerfied(rs.getBoolean("is_verified"));
                        ((SimpleUtilisateur) user).setFullname(rs.getString("fullname"));
                        updateCurrentUser(user);
                    } else {
                        Utilisateur user = new Coach();

                        user.setId(rs.getInt("id"));
                        user.setEmail(rs.getString("email"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setVerifpassword(rs.getString("verifpassword"));
                        user.setRoles(rs.getString("roles"));
                        user.setIsVerfied(rs.getBoolean("is_verified"));
                        ((Coach) user).setSpecialite(rs.getString("specialite"));
                        updateCurrentUser(user);
                    }
                } else {
                    System.out.println("mdp incorrecte");
                }

            } else {

                System.out.println("email introuvable");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;

    }

    public void updateCurrentUser(Utilisateur user) {

        if (user.getRoles().equals("[\"ROLE_USER\"]") || user.getRoles().equals("[\"ROLE_ADMIN\"]")) {
            currentUser = new SimpleUtilisateur();
            currentUser.setId(user.getId());
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());
            currentUser.setVerifpassword(user.getVerifpassword());
            currentUser.setEmail(user.getEmail());
            currentUser.setIsVerfied(user.isIsVerfied());
            currentUser.setRole(user.isRole());
            currentUser.setRoles(user.getRoles());
            ((SimpleUtilisateur) currentUser).setFullname(((SimpleUtilisateur) user).getFullname());

        } else {
            currentUser = new Coach();
            currentUser.setId(user.getId());
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());
            currentUser.setVerifpassword(user.getVerifpassword());
            currentUser.setEmail(user.getEmail());
            currentUser.setIsVerfied(user.isIsVerfied());
            currentUser.setRole(user.isRole());
            currentUser.setRoles(user.getRoles());
            ((Coach) currentUser).setSpecialite(((Coach) user).getSpecialite());

        }

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

    public String nbrprod(List<Utilisateur> u) {

        int nbr;
        nbr = (int) u.stream().count();
        String s = Integer.toString(nbr);
        return s;

    }

    public Set<Utilisateur> tri(List<Utilisateur> u) {

        Set<Utilisateur> ensEmp2 = u.stream().collect(Collectors.toCollection(() -> new TreeSet<Utilisateur>((e1, e2) -> e1.getUsername().compareTo(e2.getUsername()))));
        return ensEmp2;
    }

    public List<Utilisateur> chercheUtilisateur(Object o) {
        String query = "";
        String ch = "";
        int i = 0;
        List<Utilisateur> user = new ArrayList<>();
        if (o.getClass() == ch.getClass()) {
            ch = (String) o;
            query = "SELECT * FROM `utilisateur` WHERE `username` LIKE '%" + ch + "%' OR `email` LIKE '%" + ch + "%' OR `fullname` LIKE '%" + ch + "%' OR `specialite` LIKE '%" + ch + "%'";
        }

        try {
            //System.out.println(query);
            PreparedStatement ste = ct.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {
                if (rs.getString(4).equals("simpleutilisateur")) {
                    SimpleUtilisateur p = new SimpleUtilisateur();
                    p.setId(rs.getInt(1));
                    p.setPassword(rs.getString(2));
                    p.setEmail(rs.getString(3));
                    p.setFullname(rs.getString(5));
                    p.setUsername(rs.getString(7));
                    p.setVerifpassword(rs.getString(8));
                    user.add(p);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    public List<Utilisateur> chercheCpoch(Object o) {
        String query = "";
        String ch = "";
        int i = 0;
        List<Utilisateur> user = new ArrayList<>();
        if (o.getClass() == ch.getClass()) {
            ch = (String) o;
            query = "SELECT * FROM `utilisateur` WHERE `username` LIKE '%" + ch + "%' OR `email` LIKE '%" + ch + "%' OR `fullname` LIKE '%" + ch + "%' OR `specialite` LIKE '%" + ch + "%'";
        }

        try {
            //System.out.println(query);
            PreparedStatement ste = ct.prepareStatement(query);
            ResultSet rs = ste.executeQuery();
            while (rs.next()) {

                if (rs.getString(4).equals("coach")) {
                    Coach p = new Coach();
                    p.setId(rs.getInt(1));
                    p.setPassword(rs.getString(2));
                    p.setEmail(rs.getString(3));
                    p.setSpecialite(rs.getString(6));
                    p.setUsername(rs.getString(7));
                    p.setVerifpassword(rs.getString(8));
                    user.add(p);
                }

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return user;
    }

    public void ChangePasswordWithEmail(String email, String newPassword) {//autoincrement
        String sql = "UPDATE utilisateur SET password = ? ,verifpassword=? WHERE email = ?";
        try {
            PreparedStatement ste = ct.prepareStatement(sql);
            String hash = this.mdpconvert(newPassword);
            System.out.println(hash);
            ste.setString(1, hash);
            ste.setString(2, hash);
            ste.setString(3, email);

            ste.executeUpdate();
            System.out.println("mot de passe modifié avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Set<Utilisateur> Rechercher(List<Utilisateur> destination, String username) {

        Set<Utilisateur> u = destination.stream().filter(cc -> cc.getUsername().equals(username)).collect(Collectors.toCollection(() -> new TreeSet<Utilisateur>((e1, e2) -> e1.getUsername().compareTo(e2.getUsername()))));

        return u;
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);

        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void generateExcel() {
        String sql = "select * from utilisateur";
        Statement ste;
        try {

            ste = ct.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
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

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Email");
            header.createCell(2).setCellValue("Type ");
            header.createCell(3).setCellValue("Fullname");
            header.createCell(4).setCellValue("Specialite");
            header.createCell(5).setCellValue("Username");

            header.getCell(0).setCellStyle(csCouleur);
            header.getCell(1).setCellStyle(csCouleur);
            header.getCell(2).setCellStyle(csCouleur);
            header.getCell(3).setCellStyle(csCouleur);
            header.getCell(4).setCellStyle(csCouleur);
            header.getCell(5).setCellStyle(csCouleur);

            
            int index = 1;
            while (rs.next()) {
                HSSFCellStyle cs1Couleur = wb.createCellStyle();
                cs1Couleur.setFillForegroundColor((short) 45);
                HSSFRow row = sheet.createRow(index);
                
                
                row.createCell(0).setCellValue(rs.getString("id"));
                row.createCell(1).setCellValue(rs.getString("email"));
                row.createCell(2).setCellValue(rs.getString("type"));
                row.createCell(3).setCellValue(rs.getString("fullname"));
                row.createCell(4).setCellValue(rs.getString("specialite"));
                row.createCell(5).setCellValue(rs.getString("username"));
            row.getCell(0).setCellStyle(cs1Couleur);
                row.getCell(1).setCellStyle(cs1Couleur);
                row.getCell(2).setCellStyle(cs1Couleur);
                row.getCell(3).setCellStyle(cs1Couleur);
                row.getCell(4).setCellStyle(cs1Couleur);
                row.getCell(5).setCellStyle(cs1Couleur);
                index++;
            }
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\souma\\Desktop\\utilisateurs.Xls");
            wb.write(fileOut);
            fileOut.close();
            ste.close();
            rs.close();

        } catch (SQLException e) {
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public void sensSMS()
    {
    
     String ACCOUNT_SID = System.getenv("AC1bfc52d30073068147b27bcfeae02c20");
    String AUTH_TOKEN = System.getenv("626f9f30ef57e99f0239c2c46bff15e8");

   
       Twilio.init("AC1bfc52d30073068147b27bcfeae02c20", "626f9f30ef57e99f0239c2c46bff15e8");
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                new com.twilio.type.PhoneNumber("+21623251728"),
                new com.twilio.type.PhoneNumber("++17579095719 "),
                "This is the ship that made the Kessel Run in fourteen parsecs?")
            .create();

        System.out.println(message.getSid());
    
    }
}
