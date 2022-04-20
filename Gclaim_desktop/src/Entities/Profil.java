/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author yusse
 */
public class Profil {
    private int id;
    private String Description;
    private String Username;
    private Utilisateur user;
    private String game;
    private int numero;

    public Profil() {
    }

    public Profil(int id) {
        this.id = id;
    }

    public Profil(int id, Utilisateur user) {
        this.id = id;
        this.user = user;
    }

    public Profil(String Username, String game) {
        this.Username = Username;
        this.game = game;
    }

    public Profil(int id, String Username,String Description,   String game, int numero,Utilisateur user) {
        this.id = id;
        this.Description = Description;
        this.Username = Username;
        this.user = user;
        this.game = game;
        this.numero = numero;
    }

    public Profil(int id, String Description, String Username, String game, int numero) {
        this.id = id;
        this.Description = Description;
        this.Username = Username;
        //this.user = user;
        this.game = game;
        this.numero = numero;
    }

    public Profil(String Username,String Description,  String game, int numero) {
        this.Description = Description;
        this.Username = Username;
        this.game = game;
        this.numero = numero;
    }

    public Profil(String Description, String Username, Utilisateur user, String game, int numero) {
        this.Description = Description;
        this.Username = Username;
        this.user = user;
        this.game = game;
        this.numero = numero;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return Description;
    }

    public String getUsername() {
        return Username;
    }


    public String getGame() {
        return game;
    }

    public int getNumero() {
        return numero;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }


    public void setGame(String game) {
        this.game = game;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Profil{" + "id=" + id + ", Description=" + Description + ", Username=" + Username  + ", game=" + game + ", numero=" + numero + '}';
    }

    public Object getCoach() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}