package com.example.myhome;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myhome.m_DataObject.Article;
import java.util.ArrayList;
public class ListeViewPanierAdapter extends BaseAdapter{
 TextView quantite;
    Animation a;
    Context c;
    ArrayList<Article> articles;
    LayoutInflater inflater;

    public ListeViewPanierAdapter(ArrayList<Article> articles,Context c) {
        this.articles = articles;
        this.c=c;
        inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return    articles.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null){
            convertView=inflater.inflate(R.layout.article,parent,false);

        }
        ImageView img=(ImageView) convertView.findViewById(R.id.product_image);
        TextView name=(TextView) convertView.findViewById(R.id.NomProduit);
        TextView prix=(TextView) convertView.findViewById(R.id.price);
        TextView prixA=(TextView) convertView.findViewById(R.id.price_reduce);
        TextView fab=convertView.findViewById(R.id.fabrication_by);
        final TextView prixarticle=convertView.findViewById(R.id.prixtotale);
        prixA.setPaintFlags(prixA.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
         final Article article=articles.get(position);
        a= AnimationUtils.loadAnimation(c,R.anim.anim);
        PicassoClient.downloadimage(c,article.getProduit().getImageurl(),img);
        fab.setText("fabriqué par :"+article.getProduit().getFab());
        name.setText(article.getProduit().getNompro());
        prix.setText(article.getProduit().getPrix()+" DH");
        if( article.getProduit().getAncprix()!=0.0){
            prixA.setText(article.getProduit().getAncprix()+"DH");
            prix.startAnimation(a);
        }
        prixarticle.setText("SOUS-TOTAL : "+((articles.get(position).getQuantite())*(articles.get(position).getProduit().getPrix()))+"");
        quantite=convertView.findViewById(R.id.quantite);
        quantite.setText(article.getQuantite()+"");
Button Bplus=convertView.findViewById(R.id.Bplus);
Button Bmoins=convertView.findViewById(R.id.Bmoins);
Bplus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        articles.get(position).setQuantite(article.getQuantite()+1);
       quantite.setText(articles.get(position).getQuantite()+"");
        Intent i=new Intent(c,panier.class);
        c.startActivity(i);
    }
});
        Bmoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articles.get(position).setQuantite(article.getQuantite()-1);
                quantite.setText(articles.get(position).getQuantite()+"");
                Intent i=new Intent(c,panier.class);
                c.startActivity(i);
            }
        });
        Button delete=convertView.findViewById(R.id.supprimer);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                articles.remove(articles.get(position));
                  Intent i=new Intent(c,panier.class);
                  c.startActivity(i);
            }
        });
        return convertView;
    }
}
