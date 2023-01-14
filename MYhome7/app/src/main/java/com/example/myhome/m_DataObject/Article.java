package com.example.myhome.m_DataObject;

public class Article {
    private product produit;
    private  int quantite;

    public Article(product produit,int quantite) {
        this.produit = produit;
        this.quantite=quantite;
    }

    public product getProduit() {
        return produit;
    }

    public void setProduit(product produit) {
        this.produit = produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    }
