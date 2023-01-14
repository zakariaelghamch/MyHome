package com.example.myhome.m_mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloaderpro  extends AsyncTask <Void,Void,String> {
    Context c;
    String urlAdress;
    public ListView ls;
    ProgressDialog pd;
    public Downloaderpro(Context c, String urlAdress, ListView ls) {
        this.c = c;
        this.urlAdress = urlAdress;
        this.ls = ls;
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
            Toast.makeText(c, "unsuccessfull,no data retrieved", Toast.LENGTH_SHORT).show();
        }else{
            ParaserPro parser= new ParaserPro(c,jsonData,ls);
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
