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
public class SimpleUtilisateur extends Utilisateur {
     protected String fullname;

    public SimpleUtilisateur() {
      
    }  public SimpleUtilisateur(int id) {
        super(id);
      
    }

    public SimpleUtilisateur(int id, String username, String password, String verifpassword, String email,String fullname) {
        super(id, username, password, verifpassword, email);
        this.fullname = fullname;
    }
   
  public SimpleUtilisateur(String username, String password, String verifpassword, String email,String fullname) {
        super(username, password, verifpassword, email);
        this.fullname = fullname;
    }
    public SimpleUtilisateur(int id, String username, String password, String verifpassword, String email, String roles, boolean role, boolean isVerfied,String fullname) {
        super(id,username, password, verifpassword, email, roles, role, isVerfied);
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public String toString() {
        return "SimpleUtilisateur{" +"id=" + id + ", username=" + username + ", password=" + password + ", verifpassword=" + verifpassword + ", email=" + email +  " ,fullname=" + fullname + '}';
    }


}
