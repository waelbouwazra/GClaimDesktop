/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Article;
import Entities.cat;
import Tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.scene.paint.Color;

/**
 *
 * @author azizk
 */
public class CatService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public CatService() {
        cnx = MaConnection.getInstance().getConnection();
    }

    public void AddCatPst(cat c) {

        String req = "insert into cat (nom,couleur) values (?,?)";
        try {

            pst = cnx.prepareStatement(req);

            pst.setString(1, c.getNom());
            pst.setString(2, c.getCouleur());

            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void DeleteCategorie(int c) {
        try {
            String req = "DELETE from cat  WHERE id =" + c + " ";

            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("done");
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }

    public void UpdateCategorie(cat c, int cu) {
        String req = "UPDATE cat set nom=? , couleur=? WHERE id =" + cu + " ";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getCouleur());

            pst.executeUpdate();
            System.out.println("categorie modifié");

        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());

        }
    }

    public List<cat> ShowCategorie() {
        List<cat> cat = new ArrayList<>();
        String sql = "select * from cat";
        Statement ste;

        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
            while (rs.next()) {
                cat c = new cat();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setCouleur(rs.getString("couleur"));

                cat.add(c);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return cat;
    }

    public cat getIdCat(int id) {
        String req = "SELECT * FROM `cat` WHERE id=?";
        cat ca = new cat();
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);

            ResultSet as = pst.executeQuery();
            if (as.next()) {
                ca.setId(id);
                ca.setNom(as.getString(2));
                ca.setCouleur(as.getString(3));
                return ca;

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ca;

    }

    public int getidCat(String nom) {
        String req = "SELECT * FROM cat WHERE nom=?";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, nom);

            ResultSet as = pst.executeQuery();
            if (as.next()) {
             return as.getInt(1);
         
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    
    }
    public int Recherche(String tit ){
      String req = "SELECT * FROM `cat` WHERE nom=? ";
cat a =new cat();
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1, tit);
            ResultSet as = pst.executeQuery();
            if (as.next()) {
             return as.getInt(1);
         
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    
}
public static String toHexString(Color color) {
  int r = ((int) Math.round(color.getRed()     * 255)) << 24;
  int g = ((int) Math.round(color.getGreen()   * 255)) << 16;
  int b = ((int) Math.round(color.getBlue()    * 255)) << 8;
  int a = ((int) Math.round(color.getOpacity() * 255));
  return String.format("#%08X", (r + g + b + a));
}
public Set<cat> tri( List<cat> u){
       
        Set<cat> ensEmp2 = u.stream().collect(Collectors.toCollection(()->new TreeSet<cat>((e1,e2)->e1.getNom().compareTo(e2.getNom()))));
        return ensEmp2;
    }
}
