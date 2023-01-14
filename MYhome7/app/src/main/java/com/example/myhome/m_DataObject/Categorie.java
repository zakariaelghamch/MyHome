package com.example.myhome.m_DataObject;

public class Categorie {
private  int  idCat;
private String nomCat;

    public Categorie(String nomCat) {
        this.nomCat = nomCat;
    }

    public Categorie(int id, String nomCat) {
        this.idCat=id;
        this.nomCat = nomCat;
    }

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }
}
