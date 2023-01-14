package com.example.myhome;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhome.m_DataObject.Article;
import com.example.myhome.m_DataObject.product;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

import java.util.ArrayList;


public class Main2Activity extends AppCompatActivity {
  product produit;
  Animation a;
  ImageView imgcon;
SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       Toolbar t=findViewById(R.id.tb);
        imgcon=findViewById(R.id.connection);
        setSupportActionBar(t);
        a= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim);
        Bundle extra = getIntent().getExtras();
        int id = extra.getInt("id");
       String name=extra.getString("nom");
            String desc=extra.getString("desc");
            String h=extra.getString("hateur");
            String l=extra.getString("largeur");
            String fab=extra.getString("fab");
            String g=extra.getString("garantie");
            String c=extra.getString("coleur");
               String m=extra.getString("matiere");
            float prix=extra.getFloat("prix");
            float aprix=extra.getFloat("aprix");
            String  img=extra.getString("img");
            int quantite=extra.getInt("quantite");
            produit=new product(id,name,h,l,img,desc,fab,g,prix,aprix,c,m);
            TextView txtname=findViewById(R.id.nom_produit);
            txtname.setText(name);
            TextView txtdesc=findViewById(R.id.desc);
            txtdesc.setText(desc);
            TextView M=findViewById(R.id.matiere);
            if(m!=""){
                TextView Mlabel=findViewById(R.id.matierelabel);
                Mlabel.setVisibility(View.VISIBLE);
                M.setText(m);
            }
           TextView q=findViewById(R.id.Stock);
            q.setText( quantite + "  EN STOCK ");
            TextView L=findViewById(R.id.largeur);
            L.setText(l);
            TextView H=findViewById(R.id.Heteur);
            H.setText(h);
            TextView f=findViewById(R.id.fabr);
            f.setText(fab);
            TextView G=findViewById(R.id.garantie);
            G.setText(g);
            TextView C=findViewById(R.id.coleur);
            C.setText(c);
            TextView  pr=findViewById(R.id.price);TextView pra=findViewById(R.id.priceA);
            pr.setText(prix+" DH");
            if(aprix!=0.0){
             pra.setPaintFlags(pr.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
             pr.startAnimation(a);
            pra.setText(aprix+" DH");}
            ImageView image=findViewById(R.id.imgproduct);
            PicassoClient.downloadimage(this,img,image);
            Button ajouter=findViewById(R.id.ajout);
            ajouter.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v){
                    Article a=new Article(produit,1);
                    GlobaleArticles g=GlobaleArticles.getInstance();
                    ArrayList<Article> articles=new ArrayList<>();
                    boolean isfound=false;
                    articles=g.getArticles();
                     for(int i=0;i<articles.size();i++){
                       if(articles.get(i).getProduit().getIdpro()==a.getProduit().getIdpro()){
                       articles.get(i).setQuantite(a.getQuantite()+1);
                       isfound=true;
                     }}
                     if(!isfound){
                         articles.add(a);
                     }
                     g.setArticles(articles);
                    Toast.makeText(Main2Activity.this, "le produit est bien Ajouté", Toast.LENGTH_SHORT).show();
                }
            });
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        if(pref.contains("nom") && pref.contains("prenom") && pref.contains("email")) {
            String a = pref.getString("prenom", null).trim().toLowerCase().substring(0, 1);
            if (a.equals("a")) imgcon.setImageResource(R.drawable.a);
            if (a.equals("b")) imgcon.setImageResource(R.drawable.b);
            if (a.equals("c")) imgcon.setImageResource(R.drawable.c);
            if (a.equals("d")) imgcon.setImageResource(R.drawable.d);
            if (a.equals("k")) imgcon.setImageResource(R.drawable.k);
            if (a.equals("e")) imgcon.setImageResource(R.drawable.e);
            if (a.equals("f")) imgcon.setImageResource(R.drawable.f);
            if (a.equals("g")) imgcon.setImageResource(R.drawable.g);
            if (a.equals("h")) imgcon.setImageResource(R.drawable.h);
            if (a.equals("i")) imgcon.setImageResource(R.drawable.i);
            if (a.equals("j")) imgcon.setImageResource(R.drawable.j);
            if (a.equals("l")) imgcon.setImageResource(R.drawable.l);
            if (a.equals("m")) imgcon.setImageResource(R.drawable.m);
            if (a.equals("n")) imgcon.setImageResource(R.drawable.n);
            if (a.equals("o")) imgcon.setImageResource(R.drawable.o);
            if (a.equals("p")) imgcon.setImageResource(R.drawable.p);
            if (a.equals("q")) imgcon.setImageResource(R.drawable.q);
            if (a.equals("r")) imgcon.setImageResource(R.drawable.r);
            if (a.equals("s")) imgcon.setImageResource(R.drawable.s);
            if (a.equals("t")) imgcon.setImageResource(R.drawable.t);
            if (a.equals("u")) imgcon.setImageResource(R.drawable.u);
            if (a.equals("v")) imgcon.setImageResource(R.drawable.v);
            if (a.equals("w")) imgcon.setImageResource(R.drawable.w);
            if (a.equals("x")) imgcon.setImageResource(R.drawable.x);
            if (a.equals("y")) imgcon.setImageResource(R.drawable.y);
            if (a.equals("z")) imgcon.setImageResource(R.drawable.z);}else{
            imgcon.setImageResource(R.drawable.connexion);
        }

    }
    public void click(View view)
    {

        final PopupMenu popup=new PopupMenu(Main2Activity.this,imgcon);
        popup.getMenuInflater().inflate(R.menu.menu_con,popup.getMenu());
        MenuItem i1=popup.getMenu().findItem(R.id.i1) ;
        MenuItem i2=popup.getMenu().findItem(R.id.i2);
        if(pref.contains("nom") && pref.contains("prenom") && pref.contains("email")) {
                i1.setTitle("  " +pref.getString("prenom",null).toUpperCase()+" "+pref.getString("nom",null).toUpperCase());
            i2.setTitle("DECONNEXION");
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.i2:
                                    LoginManager.getInstance().logOut();
                            SharedPreferences.Editor editor = pref.edit();
                            imgcon.setImageResource(R.drawable.connexion);
                            editor.clear();
                            editor.commit();
                            popup.dismiss();
                    }
                    return true;
                }
            });
            popup.show();
        }else{

            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.i1:
                            Intent i=new Intent(getApplicationContext(),sign_up.class);
                            startActivity(i);
                        case R.id.i2:
                            Intent i2=new Intent(getApplicationContext(),loginActivity.class);
                            startActivity(i2);
                    }
                    return true;
                }
            });
            popup.show();
    }}
    // TODO Auto-generated method stub
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_panier){
            Toast.makeText(this, "panier", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(),panier.class);
            startActivity(i);
            return  true;
        }

        return super.onOptionsItemSelected(item);
    }



}
