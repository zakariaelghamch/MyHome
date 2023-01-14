package com.example.myhome.m_DataObject;

import java.util.ArrayList;

public class Sale {
    private int idSale;
    private String nomSale;
    private ArrayList<Categorie> list = new ArrayList<Categorie>();

    public Sale(String nomSale, ArrayList<Categorie> List,int id) {
        this.nomSale = nomSale;
        this.idSale=id;
        this.list=List;
    }

    public Sale(String nomSale,int id) {
        this.nomSale = nomSale;
    this.idSale=id;
    }

    public Sale() {
    }

    public int getIdSale() {
        return idSale;
    }
    public void setIdSale(int idSale) {
        this.idSale = idSale;
    }
    public String getNomSale() {
        return nomSale;
    }
    public void setNomSale(String nomSale) {
        this.nomSale = nomSale;
    }

    public ArrayList<Categorie> getList() {
        return list;
    }

    public void setList(ArrayList<Categorie> list) {
        this.list = list;
    }
    public void addCat(Categorie c){
        list.add(c);
    }
    public void addCat(int id,String nomcat){
        Categorie n=new Categorie(id,nomcat);
        list.add(n);
    }
}
