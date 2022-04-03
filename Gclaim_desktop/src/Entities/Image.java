/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author azizk
 */
public class Image {
    private int id;
    private Produit produit;
    private String url_image;

    public Image() {
    }

    public Image(int id, Produit produit, String url_image) {
        this.id = id;
        this.produit = produit;
        this.url_image = url_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    @Override
    public String toString() {
        return "Image{" + "id=" + id + ", produit=" + produit + ", url_image=" + url_image + '}';
    }
    
}
