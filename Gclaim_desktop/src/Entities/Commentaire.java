/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author User
 */
public class Commentaire {
    private int id;
    private Article article_id;
    private String commentaire;
    private Date creation;
    private String user;

    public Commentaire() {
    }

    public Commentaire(int id, Article article_id, String commentaire, Date creation, String user) {
        this.id = id;
        this.article_id = article_id;
        this.commentaire = commentaire;
        this.creation = creation;
        this.user = user;
    }

    public Commentaire(Article article_id, String commentaire, String user) {
        this.article_id = article_id;
        this.commentaire = commentaire;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Article getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Article article_id) {
        this.article_id = article_id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", article_id=" + article_id + ", commentaire=" + commentaire + ", creation=" + creation + ", user=" + user + '}';
    }
    
}
