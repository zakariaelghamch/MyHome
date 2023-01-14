package com.example.myhome.m_mysql;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ExpandableListView;

import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void,Void,String> {
        Context c;
        String urlAdress;
    public   ExpandableListView Ex;
 //       ProgressDialog pd;
    public Downloader(Context c, String urlAdress, ExpandableListView ex) {
        this.c = c;
        this.urlAdress = urlAdress;
        Ex = ex;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

            }

    @Override
    protected String doInBackground(Void... voids) {

        return DownloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        if(jsonData==null){
            Toast.makeText(c, "unsuccessfull,no data affiche ,verifier votre connexion", Toast.LENGTH_SHORT).show();
        }else{

 DataParaser parser= new DataParaser(c,jsonData,Ex);
 parser.execute();
        }
    }
    private String DownloadData(){
        HttpURLConnection con=connecter.connect(urlAdress);
        if(con==null){
            return null;
        }
        try {
            InputStream is= new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer jsonData=new StringBuffer();

            while ((line=br.readLine()) !=null){
                jsonData.append(line+"\n");
            }
            br.close();
            is.close();
            return  jsonData.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
