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
public class Article {
    private int id;
    private String titre;
    private String description;
    private String image;
    private Date create_at;
    private cat cat_id;
    private Commentaire commentaire;
    private int nbr_vu;

    public Article() {
    }

    public Article(int id) {
        this.id = id;
    }

    public Article(int id, String titre, String description, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
    }
    

    public Article(String titre, String description, String image, Date create_at, int nbr_vu) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.create_at = create_at;
        this.nbr_vu = nbr_vu;
    }

    
  

    public Article(String titre, String description, String image, cat cat_id) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.cat_id = cat_id;
    }

    public Article(String titre, String description, String image, Date create_at, cat cat_id, Commentaire commentaire) {
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.create_at = create_at;
        this.cat_id = cat_id;
        this.commentaire = commentaire;
    }

    public Article(int id, String titre, String description, String image, cat cat_id) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.image = image;
        this.cat_id = cat_id;
    }

   
    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public cat getCat_id() {
        return cat_id;
    }

    public void setCat_id(cat cat_id) {
        this.cat_id = cat_id;
    }

    public int getNbr_vu() {
        return nbr_vu;
    }

    public void setNbr_vu(int nbr_vu) {
        this.nbr_vu = nbr_vu;
    }

    @Override
    public String toString() {
        return "Article :" + " \t| titre=" + titre + " \t| , description=" + description + " \t| ,image=" + image + " \t| ,create_at=" + create_at + " \t|, cat_id=" + cat_id + " \t|,  nbr_vu=" + nbr_vu + '}';
    }

  

}
