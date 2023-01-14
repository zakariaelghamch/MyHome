package com.example.myhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhome.m_mysql.Downloaderpro;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

public class productliste extends AppCompatActivity {
    SharedPreferences pref;

ImageView imgcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productliste);
        imgcon=findViewById(R.id.connection);
       Bundle extra=getIntent().getExtras();
           int id=extra.getInt("id");
       String urladress="https://bigvigo.000webhostapp.com/select_liste_products.php?id="+id+"";
        Toolbar t=(Toolbar) findViewById(R.id.tb);
        setSupportActionBar(t);
        ListView lv=(ListView) findViewById(R.id.lv);
        new Downloaderpro(productliste.this, urladress, lv).execute();
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
            if (a.equals("z")) imgcon.setImageResource(R.drawable.z);}
        else{ imgcon.setImageResource(R.drawable.connexion);}

    }
    //get reference of the ExpandableListView
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void click(View view)
    {

        final PopupMenu popup=new PopupMenu(getApplicationContext(),imgcon);
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
            popup.show();}
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.action_panier){
            Toast.makeText(this, "panier", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(getApplicationContext(),panier.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
