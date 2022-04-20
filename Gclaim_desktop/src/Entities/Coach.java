/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author souma
 */
public class Coach extends Utilisateur {
    String specialite;

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Coach(String specialite) {
        this.specialite = specialite;
    }
    public Coach() {
        
    }

    public Coach(String username, String password, String verifpassword, String email,String specialite) {
        super(username, password, verifpassword, email);
        this.specialite = specialite;
    }

    public Coach(int id, String username, String password, String verifpassword, String email,String specialite) {
        super(id, username, password, verifpassword, email);
        this.specialite = specialite;
    }

    public Coach(int id, String username, String password, String verifpassword, String email, String roles, boolean role, boolean isVerfied,String specialite) {
        super(id, username, password, verifpassword, email, roles, role, isVerfied);
        this.specialite = specialite;
    }

    @Override
    public String toString() {
      
        return " username=" + username + "\n \n password=" + password + "\n \n verifpassword=" + verifpassword + "\n \n email=" + email +  "\n \n specialite=" + specialite + "\n \n";
    }
    

}

