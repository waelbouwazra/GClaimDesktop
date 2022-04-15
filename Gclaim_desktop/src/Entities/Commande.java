/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author moham
 */
public class Commande {
    private int id;
    private int total;
    private Date date_achat;
    private boolean livrer;
    private Utilisateur User;

    public Commande() {
    }

    public Commande(int id) {
        this.id = id;
    }

    public Commande(int id, int total, Date date_achat, boolean livrer, Utilisateur User) {
        this.id = id;
        this.total = total;
        this.date_achat = date_achat;
        this.livrer = livrer;
        this.User = User;
    }

    public int getId() {
        return id;
    }

    public int getTotal() {
        return total;
    }

    public Date getDate_achat() {
        return date_achat;
    }

    public boolean isLivrer() {
        return livrer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setDate_achat(Date date_achat) {
        this.date_achat = date_achat;
    }

    public void setLivrer(boolean livrer) {
        this.livrer = livrer;
    }

    public Utilisateur getUser() {
        return User;
    }

    public void setUser(Utilisateur User) {
        this.User = User;
    }

    @Override
    public String toString() {
        //return "Commande{" + "id=" + id + ", total=" + total + ", date_achat=" + date_achat + ", livrer=" + livrer + ", User=" + User + '}';
    return "Commande id :" + id + " \t| Total: "+total+ "\t| Username :" + User.getUsername()+ " \t| Status: "+livrer+ "\t| Date :" + date_achat ;
  
    }
    
}
