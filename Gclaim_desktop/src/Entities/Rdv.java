/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author azizk
 */
public class Rdv {
     private int id;
    private Profil coach;
    private Utilisateur user;
    private Date date;

    public Rdv() {
    }

    public Rdv(int id, Profil coach, Utilisateur user, Date date) {
        this.id = id;
        this.coach = coach;
        this.user = user;
        this.date = date;
    }

    public Rdv(Profil coach, Utilisateur user) {
        this.coach = coach;
        this.user = user;
    }

    public Rdv(Profil coach, Utilisateur user, Date date) {
        this.coach = coach;
        this.user = user;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Profil getCoach() {
        return coach;
    }

    public void setCoach(Profil coach) {
        this.coach = coach;
    }



    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Rdv{" + "id=" + id + ", coach=" + coach + ", user=" + user + ", date=" + date + '}';
    }
    
    
   
    
    
}
