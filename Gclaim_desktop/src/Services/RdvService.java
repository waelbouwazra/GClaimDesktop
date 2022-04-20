/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Profil;
import Entities.Rdv;
import Tools.MaConnection;
import Entities.Utilisateur;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yusse
 */
public class RdvService {

    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public RdvService() {
        cnx = MaConnection.getInstance().getConnection();
    }

    public void AddRdv(Rdv c) {

        String req = "insert into rdv (coach_id,user_id,is_verified,coachname,username,date) values (?,?,?,?,?,?)";

        try {
           
            
            pst = cnx.prepareStatement(req);
          
            pst.setInt(1, c.getCoach().getId());
            pst.setInt(2, c.getUser().getId());
            
           // System.out.println(c.getDate().getYear());
          // Date date = Date.from(c.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            pst.setDate(6, Date.valueOf(c.getDate()));
            pst.setInt(3, 0);
            pst.setString(4, c.getCoach().getUsername());
            pst.setString(5, c.getUser().getUsername());
            pst.executeUpdate();
            System.out.println("ajout rdv done");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void DeleteProfil(int c) {
        try {
            String req = "DELETE from rdv  WHERE id =" + c + " ";

            ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("done");
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }
    }

    /* public void UpdateRdv(Rdv c,int cu)
        { String req ="UPDATE rdv set date=?  WHERE id =" +cu+ " ";
        try {
              pst = cnx.prepareStatement(req);             
              pst.setString(2, c.getDescription());
             
              
              pst.executeUpdate();
              System.out.println("Profil modifié");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        } 
        }
     */
    

    public List<Rdv> ShowRdv() {
        List<Rdv> rdv = new ArrayList<>();
        String sql = "select * from rdv";
        Statement ste;
       ServiceUser serviceUser = new ServiceUser();
       ProfilService ProfilService = new ProfilService();

        try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Rdv p = new Rdv();
                 p.setUser(serviceUser.getuserbyID(rs.getInt("user_id")));
                 p.setCoach(ProfilService.getuserbyID(rs.getInt("coach_id")));
                 p.setDate(rs.getDate("date").toLocalDate());
                 
                 rdv.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return rdv;
    }
    public List<Rdv> ShowRdvVerified() {
        List<Rdv> rdv = new ArrayList<>();
        String sql = "select * from rdv r where r.is_verified=1";
        Statement ste;
 ServiceUser serviceUser = new ServiceUser();
       ProfilService ProfilService = new ProfilService();

             try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Rdv p = new Rdv();
                 p.setUser(serviceUser.getuserbyID(rs.getInt("user_id")));
                 p.setCoach(ProfilService.getuserbyID(rs.getInt("coach_id")));
                 p.setDate(rs.getDate("date").toLocalDate());
                 
                 rdv.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        return rdv;
    }
    public List<Rdv> ShowRdvNotVerified() {
        List<Rdv> rdv = new ArrayList<>();
        String sql = "select * from rdv r where r.is_verified=0";
        Statement ste; ServiceUser serviceUser = new ServiceUser();
       ProfilService ProfilService = new ProfilService();

     try {
            ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);
             while(rs.next()){
                 Rdv p = new Rdv();
                 p.setUser(serviceUser.getuserbyID(rs.getInt("user_id")));
                 p.setCoach(ProfilService.getuserbyID(rs.getInt("coach_id")));
                 p.setDate(rs.getDate("date").toLocalDate());
                 
                 rdv.add(p);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


        return rdv;
    }

}
