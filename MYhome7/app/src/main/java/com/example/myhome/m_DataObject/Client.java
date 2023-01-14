package com.example.myhome.m_DataObject;

public class Client {
    private int  id;
    private String nom ;
    private  String prenom;
    private String  email;
    private  String adress;
    private  String ville;
    private  String  code_postale;
    private String  telephone;

    public Client() {
    }

    public Client(int id, String nom, String prenom, String email, String adress, String ville, String code_postale, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adress = adress;
        this.ville = ville;
        this.code_postale = code_postale;
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode_postale() {
        return code_postale;
    }

    public void setCode_postale(String code_postale) {
        this.code_postale = code_postale;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

