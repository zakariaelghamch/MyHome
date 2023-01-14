package com.example.myhome.m_mysql;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myhome.Main2Activity;
import com.example.myhome.customAdapterListView;
import com.example.myhome.m_DataObject.product;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParaserPro extends AsyncTask<Void,Void,Integer> {
    public ArrayList<product> produits= new ArrayList<>();
    Context c;
    String jsonData;
    ListView ls;
ProgressDialog pd;
    public ParaserPro(Context c, String jsonData, ListView ls) {
        this.c = c;
        this.jsonData = jsonData;
        this.ls = ls;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing  .... please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        pd.dismiss();
        if(result==0){
            Toast.makeText(c, "Unable To parse", Toast.LENGTH_SHORT).show();
        }else{
          customAdapterListView adapter=new  customAdapterListView(c,produits);
           ls.setAdapter(adapter);
             final Intent i =new Intent(c,Main2Activity.class);
            ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    product produit=produits.get(position);
                    i.putExtra("id",produit.getIdpro());
                    i.putExtra("nom",produit.getNompro());
                    i.putExtra("desc",produit.getDesc());
                    i.putExtra("coleur",produit.getCouleur());
                    i.putExtra("hateur",produit.getH());
                    i.putExtra("largeur",produit.getW());
                    i.putExtra("img",produit.getImageurl());
                    i.putExtra("prix",produit.getPrix());
                    i.putExtra("aprix",produit.getAncprix());
                    i.putExtra("fab",produit.getFab());
                    i.putExtra("garantie",produit.getGar());
                    i.putExtra("matiere",produit.getMatiere());
                    i.putExtra("quantite",produit.getQuantite_en_stock());
                    c.startActivity(i);
                                   }
            });
        }
    }

    private int parseData(){

        try{
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo =null;
            produits.clear();
            for(int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                int id=jo.getInt("idProduit");
                String urlimage=jo.getString("imageUrl");
                String nom =jo.getString("nomProduit");
                float prix=(float) jo.getInt("prix");
                float aprix=(float) jo.getInt("IFNULL(prixancien,0)");
                String matier=jo.getString("IFNULL(matiere,'')");
                String h=jo.getString("hateur");
                String l=jo.getString("largeur");
                String f=jo.getString("fabrication");
                String g=jo.getString("garantie");
                String desc=jo.getString("description");
                String coleur=jo.getString("couleur");
                int quantite=jo.getInt("quantite_en_stock");

               product pro=new product(id,nom,h,l,urlimage,desc,f,g,prix,aprix,coleur, matier);
             pro.setQuantite_en_stock(quantite);
              produits.add(pro);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
