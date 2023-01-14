package com.example.myhome.m_mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static android.content.Context.MODE_PRIVATE;

public class DownloderSign_up  extends AsyncTask<Void,Void,String> {
    ProgressDialog pd;
    private String urlAdress;
    private Context c;
    public DownloderSign_up(Context c,String urlAdress) {
        this.urlAdress = urlAdress;
        this.c=c;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Retrieve");
        pd.setMessage("Retrieving...Please Wait ");
        pd.show();
    }
    @Override
    protected String doInBackground(Void... voids) {
        return DownloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);

        pd.dismiss();
        if(jsonData==null){
            Toast.makeText(c, " no données Affiche ,Verifier Votre Connexion ", Toast.LENGTH_SHORT).show();
        }else{
            if(jsonData.trim().equals("no")){
                Toast.makeText(c, "Client est  dejà enregistré   ", Toast.LENGTH_SHORT).show();
            }
            else if (jsonData.trim().equals("ok")){
                Toast.makeText(c, "Client enregistré avec succès", Toast.LENGTH_SHORT).show();
                        }

        }
    }

    private String DownloadData(){
        HttpURLConnection con= connecter.connect(this.urlAdress);
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