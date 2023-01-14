package com.example.myhome;

import com.example.myhome.m_DataObject.Article;

import java.util.ArrayList;

public class GlobaleArticles {
    private static GlobaleArticles instance;
   private ArrayList<Article> articles=new ArrayList<>();

    public GlobaleArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public GlobaleArticles() {
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
    public static synchronized GlobaleArticles getInstance(){
        if(instance==null){
            instance=new GlobaleArticles();
        }
        return instance;
    }
}
