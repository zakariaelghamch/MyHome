package com.example.myhome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhome.m_DataObject.Article;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Commande extends AppCompatActivity {
    GlobaleArticles g;
    SharedPreferences pref;
  final int random_int = (int)(Math.random() * (1000 - 1 + 1) +1);
   private static final String url = "jdbc:mysql://mysql5027.site4now.net:3306/db_a6114f_myhome";
   private static final String user = "a6114f_myhome";
    private static final String pass = "zakaria@1998";
    ArrayList<Article> articles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        articles = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        ListView list;
        list = findViewById(R.id.listeV);
         g = GlobaleArticles.getInstance();
        articles = g.getArticles();
        listeArticleCommande adapter = new listeArticleCommande(articles, getApplication());
        list.setAdapter(adapter);
        long a = 0;
        for (int i = 0; i < articles.size(); i++) {
            a += ((articles.get(i).getQuantite()) * (articles.get(i).getProduit().getPrix()));
        }
        TextView totale=findViewById(R.id.totale);
        totale.setText("T O T A T E : " + a + " DH");

        Button bcommande =(Button) findViewById(R.id.bCommande);
        bcommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertArticle in;
                InsertCommande co = new InsertCommande(articles, pref.getInt("id", -1));
                co.execute("");
                for (int i = 0; i < articles.size(); i++) {
                    in = new InsertArticle(articles.get(i));
                    in.execute("");
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(Commande.this);
            builder.setTitle("enregistre le commande ");
            builder.setMessage("voulez-vous enregistre une copie pour la commande");
            builder.setPositiveButton("Oui",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Calendar  c=Calendar.getInstance();
                            Date d=c.getTime();
                            String de=d.getYear()+""+d.getMonth()+""+d.getDay()+""+d.getHours()+""+d.getMinutes()+""+d.getSeconds();
                            String data="";
                            long a=0;
                            for(int i=0;i<articles.size();i++){
                                data=data +articles.get(i).getQuantite()+" X "+articles.get(i).getProduit().getNompro()+"\n";
                                a+= ((articles.get(i).getQuantite()) * (articles.get(i).getProduit().getPrix()));
                            }
                            data=data+"TOTALE :"+ a + " DH \n";
                            data=data+d.toString()+"";
                            String date=de.replaceAll(" ","")+".txt";
                            FileOutputStream fos;
                            Toast.makeText(Commande.this,"la commande enregistre à "+date , Toast.LENGTH_SHORT).show();


                            try {
                                File myfile=new File("/sdcard/"+date);
                                myfile.createNewFile();
                                FileOutputStream fOut=new FileOutputStream(myfile);
                                OutputStreamWriter myOutWriter=new OutputStreamWriter(fOut);
                            myOutWriter.append(data);
                            myOutWriter.close();
                           fOut.close();

                            } catch (FileNotFoundException e) {
                                Toast.makeText(Commande.this, e.toString()+"", Toast.LENGTH_SHORT).show();
                            }
                            catch (IOException e){
                                Toast.makeText(Commande.this, e.toString()+"", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Commande.this, " tu click sur No", Toast.LENGTH_SHORT).show();

                }
            });
            builder.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent=new Intent(Commande.this,MainActivity.class);
                        g.setArticles(null);
                        startActivity(intent);
                        finish();
                    }
                },10000);


            }
    });}
   private class InsertCommande extends AsyncTask<String, Void,String> {
        String s;
        int idClient;
       ArrayList<Article> articles;
        public InsertCommande(ArrayList<Article> articles ,int c) {
            this.articles = articles;
            this.idClient=c;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {
            try {
                long a=0;
                for(int i=0;i<articles.size();i++){
                    a+=((articles.get(i).getQuantite())*(articles.get(i).getProduit().getPrix()));
                }
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();
                String sql="INSERT INTO  commande (idCommande, dateC, prix, idClient) VALUES ("+ random_int+", CURRENT_TIMESTAMP,"+a+","+this.idClient +"); ";
                st.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
                s=e.toString();
            }
            return s ;
        }

        protected void onPostExecute( String result) {
        }
    }
    private class InsertArticle extends AsyncTask<String, Void,String> {
       Article article;
String s;
        public InsertArticle(Article article) {
            this.article = article;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... params) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);
                Statement st = con.createStatement();
                String sql="INSERT INTO enlignecommande (idProduit, idCommande, quantite) VALUES ("+article.getProduit().getIdpro()+", "+random_int+","+article.getQuantite()+"); ";
                st.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
                s=e.toString();
             }

            return s;
        }

        protected void onPostExecute( String result) {
        }
    }
}
