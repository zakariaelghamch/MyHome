package com.example.myhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhome.m_DataObject.Article;

import java.util.ArrayList;

public class panier extends AppCompatActivity {
    SharedPreferences pref;
            ArrayList<Article> articles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);

   ListView lv=findViewById(R.id.list);
Button bCommande=findViewById(R.id.faire_commande);
        if(!(pref.contains("nom") && pref.contains("prenom") && pref.contains("email"))){
            bCommande.setText("S I G N  I N ");
            bCommande.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),loginActivity.class);
                    startActivity(i);
                }
            });
        }else{
bCommande.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Intent i=new Intent(getApplicationContext(),details_facturation.class);
        startActivity(i);}

});

     }
        GlobaleArticles g=GlobaleArticles.getInstance();
        articles=g.getArticles();
        ListeViewPanierAdapter adapter=new ListeViewPanierAdapter(articles,this);
        lv.setAdapter(adapter);
        TextView Prix_totale=findViewById(R.id.Panier_prix_totale);
        long a=0;
        for(int i=0;i<articles.size();i++){
            a+=((articles.get(i).getQuantite())*(articles.get(i).getProduit().getPrix()));
        }
        Prix_totale.setText("Total :"+a+" DH");
        ImageView go_back=findViewById(R.id.back);
     go_back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        onBackPressed();
    }
});
    }



}
