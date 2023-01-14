package com.example.myhome;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.myhome.m_mysql.Downloader;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.example.myhome.m_DataObject.Sale;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
public  class MainActivity extends AppCompatActivity{
    SharedPreferences pref;
    ImageView imgcon;
  private ExpandableListView simpleExpandableListView;
  ArrayList<Sale> sales=new ArrayList<Sale>();
    // TODO Auto-generated method stub
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String urlAdress="https://bigvigo.000webhostapp.com/select.php";
        simpleExpandableListView =(ExpandableListView) findViewById(R.id.expandableListViewCategorie);
        Downloader d=new Downloader(MainActivity.this,urlAdress,simpleExpandableListView);
        d.execute();
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);
      imgcon=findViewById(R.id.connection);
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
           final PopupMenu popup=new PopupMenu(MainActivity.this,imgcon);
           popup.getMenuInflater().inflate(R.menu.menu_con,popup.getMenu());
         MenuItem i1=popup.getMenu().findItem(R.id.i1) ;
        MenuItem i2=popup.getMenu().findItem(R.id.i2);
            if(pref.contains("nom") && pref.contains("prenom") && pref.contains("email")) {
            i1.setTitle("  " +pref.getString("prenom",null).toUpperCase()+"  "+pref.getString("nom",null).toUpperCase());
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
                            break;
                        case R.id.i2:
                            Intent i2=new Intent(getApplicationContext(),loginActivity.class);
                            startActivity(i2);
                            break;
                    }
                    return true;
                }
            });
            popup.show();
        }


        }


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
        }

        return super.onOptionsItemSelected(item);
    }}