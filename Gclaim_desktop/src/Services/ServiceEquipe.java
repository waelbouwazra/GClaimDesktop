/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Equipe;
import Tools.MaConnection;
import Entities.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Tools.*;

/**
 *
 * @author souma
 */
public class ServiceEquipe {

    private Statement ste;
    private PreparedStatement pst;
    private PreparedStatement pst1;
    private ResultSet rs;
    private Connection cnx;

    public ServiceEquipe() {
        cnx = MaConnection.getInstance().getConnection();
    }

    public void AddEquipe(Equipe p) {

        String req = "insert into equipe (nom_equipe,description,date_creation,etat,chef,nb) values (?,?,?,?,?,?)";
        String req1 = "insert into equipe_utilisateur (equipe_id,utilisateur_id) values (?,?)";
        try {

            pst = cnx.prepareStatement(req);
            pst1 = cnx.prepareStatement(req1);
            pst.setString(1, p.getNomEquipe());
            pst.setString(2, p.getDescription());
            pst.setDate(3, Date.valueOf(LocalDate.now()));
            pst.setString(4, p.getEtat());
            pst.setString(5, p.getChef());
            pst.setInt(6, p.getNb());

            pst.executeUpdate();
            String query1 = "select MAX(id) id  from equipe";
            Statement ste;
            int x = 0;
            try {
                ste = cnx.createStatement();
                ResultSet rs = ste.executeQuery(query1);
                while (rs.next()) {
                    x = rs.getInt("id");
                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            pst1.setInt(1, x);
            pst1.setInt(2, 104);
            pst1.executeUpdate();
            System.out.println("ajout done");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Equipe> afficheEquipe() {
        List<Equipe> equipes = new ArrayList<>();
        String query = "select * from equipe";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);

            while (rs.next()) {

                Equipe p = new Equipe();
                p.setId(rs.getInt(1));
                p.setNomEquipe(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setDateCreation(rs.getDate(4));
                p.setEtat(rs.getString(5));
                p.setChef(rs.getString(6));
                p.setNb(rs.getInt(7));
                equipes.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return equipes;
    }

    public void DeleteEquipe(int p) {

        String req = "DELETE from equipe  WHERE id =" + p + " ";
        Statement ste;
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("done");
        } catch (SQLException ex) {
            System.out.println("Probléme");

            System.out.println(ex.getMessage());

        }
    }

    public void updateEquipe(Equipe p,int id) {

        String req = "UPDATE equipe set nom_equipe=?,description=?,etat=? WHERE id =" +id+ "";
      
        try {

            pst = cnx.prepareStatement(req);
            pst.setString(1, p.getNomEquipe());
            pst.setString(2, p.getDescription());
            pst.setString(3, p.getEtat());
            

            pst.executeUpdate();
           
            System.out.println("update equipe done");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}