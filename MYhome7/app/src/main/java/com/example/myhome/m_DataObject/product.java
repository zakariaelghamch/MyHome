package com.example.myhome.m_DataObject;

public class product
{
 private    int idpro;
 private  String nompro;
 private  String H;
 private  String W;
 private String imageurl;
 private  String desc;
 private String fab;
 private  String gar;
 private  float prix;
 private float ancprix;
 private String couleur;
 private String  matiere;
 private int quantite_en_stock ;

    public int getQuantite_en_stock() {
        return quantite_en_stock;
    }
    public void setQuantite_en_stock(int quantite_en_stock) {
        this.quantite_en_stock = quantite_en_stock;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public product(int idpro, String nompro, String h, String w, String imageurl, String desc, String fab, String gar, float prix, float ancprix, String c, String m) {
        this.idpro = idpro;
        this.nompro = nompro;
        H = h;
        W = w;
        this.imageurl = imageurl;
        this.desc = desc;
        this.fab = fab;
        this.gar = gar;
        this.prix = prix;
        this.ancprix = ancprix;
        this.couleur=c;
        this.matiere=m; }

    public int getIdpro() {
        return idpro;
    }

    public void setIdpro(int idpro) {
        this.idpro = idpro;
    }

    public String getNompro() {
        return nompro;
    }

    public void setNompro(String nompro) {
        this.nompro = nompro;
    }

    public String getH() {
        return H;
    }

    public void setH(String h) {
        H = h;
    }

    public String getW() {
        return W;
    }

    public void setW(String w) {
        W = w;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getDesc() {
        return desc;
    }

    public product() {
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFab() {
        return fab;
    }

    public void setFab(String fab) {
        this.fab = fab;
    }

    public String getGar() {
        return gar;
    }

    public void setGar(String gar) {
        this.gar = gar;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getAncprix() {
        return ancprix;
    }

    public void setAncprix(float ancprix) {
        this.ancprix = ancprix;
    }
}
