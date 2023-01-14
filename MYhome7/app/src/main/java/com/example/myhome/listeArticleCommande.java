package com.example.myhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myhome.m_DataObject.Article;

import java.util.ArrayList;

public class listeArticleCommande extends BaseAdapter {
    ArrayList<Article> articles;
    Context c;
    LayoutInflater inflater;

    public listeArticleCommande(ArrayList<Article> articles, Context c) {
        this.articles = articles;
        this.c = c;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    @Override
    public int getCount() {
        return articles.size();
    }
    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
           convertView=inflater.inflate(R.layout.articlecommande,parent,false);
        }
       TextView quantite=convertView.findViewById(R.id.quan);
        TextView Pname=convertView.findViewById(R.id.Pname);
        TextView sousT=convertView.findViewById(R.id.sousT);
        quantite.setText(articles.get(position).getQuantite()+"");
        Pname.setText(articles.get(position).getProduit().getNompro()+"");
       float a;
       a=(articles.get(position).getQuantite())*(articles.get(position).getProduit().getPrix());
       sousT.setText("  "+ a + " DH");
        return convertView;
    }

}
