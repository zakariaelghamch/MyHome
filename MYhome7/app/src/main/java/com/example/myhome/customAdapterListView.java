package com.example.myhome;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhome.m_DataObject.product;

import java.util.ArrayList;

public class customAdapterListView  extends BaseAdapter {
Context c;
ArrayList<product> produits;
LayoutInflater inflater;
    public customAdapterListView(Context c, ArrayList<product> produits) {
        this.c = c;
        this.produits = produits;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
    return    produits.size();
    }

    @Override
    public Object getItem(int position) {
       return produits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=inflater.inflate(R.layout.productlist,parent,false);

        }
        ImageView img=(ImageView) convertView.findViewById(R.id.productimage);
        TextView name=(TextView) convertView.findViewById(R.id.productname);
       TextView prixA=(TextView) convertView.findViewById(R.id.price);
        TextView prix=(TextView) convertView.findViewById(R.id.ancienprice);
        prixA.setPaintFlags(prixA.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        product produit=produits.get(position);
        String b=produit.getAncprix()+" DH";
        String a=produit.getPrix()+" DH";
        if(produit.getAncprix()!=0.0){
            prixA.setText(b);
            prix.setGravity(11);
        }
        name.setText(produit.getNompro());
        prix.setText(a);
        PicassoClient.downloadimage(c,produit.getImageurl(),img);
        return convertView ;
    }
}
