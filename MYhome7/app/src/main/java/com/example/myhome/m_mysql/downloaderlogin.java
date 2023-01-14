package com.example.myhome.m_mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.myhome.m_DataObject.Client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import static android.content.Context.MODE_PRIVATE;

public class downloaderlogin  extends AsyncTask<Void,Void,String> {
    ProgressDialog pd;
    SharedPreferences pref;
  private String urlAdress;
  Client client;
    private Context c;
    public downloaderlogin(Context c,String urlAdress) {
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
        Toast.makeText(c, jsonData+"", Toast.LENGTH_SHORT).show();

        pd.dismiss();
        if(jsonData==null){
            Toast.makeText(c, " no données Affiche ,Verifier Votre Connexion ", Toast.LENGTH_SHORT).show();
        }else{
            if(jsonData.trim().equals("no")){
                Toast.makeText(c, "Client n' est pas enregistré ", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(c, "Login fait avec succès", Toast.LENGTH_SHORT).show();
                try{
                    JSONArray ja=new JSONArray(jsonData);
                    JSONObject jo=null;
                    for(int i=0;i<ja.length();i++) {
                        jo = ja.getJSONObject(i);
                        int idC = jo.getInt("idClient");
                        String nomC = jo.getString("nomClient");
                        String prenomC = jo.getString("prenomClient");
                        String emailC = jo.getString("gmailClient");
                        client=new Client();
                        client.setId(idC);
                        client.setNom(nomC);
                        client.setPrenom(prenomC);
                        client.setEmail(emailC);
                    }
                }catch (JSONException e) {
                    e.printStackTrace();}
             pref =c.getSharedPreferences("user_details",MODE_PRIVATE);
                SharedPreferences.Editor editor=pref.edit();
           editor.putString("nom",client.getNom());
           editor.putInt("id",client.getId());
           editor.putString("prenom",client.getPrenom());
           editor.putString("email",client.getEmail());
           editor.commit();

            }

        }
    }

    private String DownloadData() {
        HttpURLConnection con = connecter.connect(this.urlAdress);
        if (con == null) {
            return null;
        }
        try {
            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line = br.readLine()) != null) {
                jsonData.append(line + "\n");
            }
            br.close();
            is.close();
            return jsonData.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    } }
