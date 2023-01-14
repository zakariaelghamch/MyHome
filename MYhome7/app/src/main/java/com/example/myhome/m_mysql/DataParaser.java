package com.example.myhome.m_mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myhome.CustomAdapter;
import com.example.myhome.Main2Activity;
import com.example.myhome.m_DataObject.Categorie;
import com.example.myhome.m_DataObject.Sale;
import com.example.myhome.productliste;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DataParaser extends AsyncTask<Void,Void,Integer> {
    Context c;
    String jsonData;
 public   ExpandableListView ex;
public    ArrayList<Sale> sales=new ArrayList<>();
    LinkedHashMap<String,Sale> list=new LinkedHashMap<String, Sale>();
ProgressDialog pd;
    public DataParaser(Context c, String jsonData, ExpandableListView ex) {
        this.c = c;
        this.jsonData = jsonData;
        this.ex = ex;
    }

        @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("parse");
        pd.setMessage("Parsing ...Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... voids) {

        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
      pd.dismiss();
       final  Intent i=new Intent(c,productliste.class);
      if(result==0){
          Toast.makeText(c, "unable To Parse", Toast.LENGTH_SHORT).show();
      }else{
          CustomAdapter listAdapter=new CustomAdapter(c,sales);
          ex.setAdapter(listAdapter);
         ex.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
              @Override
              public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                  Sale headerInfo = sales.get(groupPosition);
                 Categorie detailInfo = headerInfo.getList().get(childPosition);
          i.putExtra("id",detailInfo.getIdCat());
          c.startActivity(i);
                  return false;
              }
          });
      }
    }
   private int parseData() {
try{
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;
            sales.clear();
    for(int i=0;i<ja.length();i++) {
        jo = ja.getJSONObject(i);
        int idM = jo.getInt("idmeuble");
        String nomM = (jo.getString("nomMeuble")).toUpperCase() ;
         int idC=jo.getInt("id_categorie");
         String nomC=jo.getString("nomCategorie");
        addCategorie(idC,idM,nomM,nomC);

    }return 1;
    }catch (JSONException e) {
        e.printStackTrace();}
       return 0;

}
private int addCategorie(int idc,int idm,String nomM,String nomC){
    int groupPosition = 0;

    //check the hash map if the group already exists
    Sale headerInfo = list.get(nomM);
    //add the group if doesn't exists
    if (headerInfo == null) {
        headerInfo = new Sale();
        headerInfo.setNomSale(nomM);
        headerInfo.setIdSale(idm);
       list.put(nomM, headerInfo);
       sales.add(headerInfo);
    }
    // get the children for the group
    ArrayList<Categorie> productList = headerInfo.getList();
    // size of the children list
    int listSize = productList.size();
    // add to the counter
    listSize++;

    // create a new child and add that to the group
    Categorie detailInfo = new Categorie(idc,nomC);
    productList.add(detailInfo);
    headerInfo.setList(productList);

    // find the group position inside the list
    groupPosition = sales.indexOf(headerInfo);
    return groupPosition;

    }

   }
