package com.example.myhome.m_mysql;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class connecter {
    public static HttpURLConnection connect(String urlAdress){
       try {
           URL url= new URL(urlAdress);
           HttpURLConnection con=(HttpURLConnection) url.openConnection();
           con.setRequestMethod("GET");
           con.setConnectTimeout(20000);
           con.setReadTimeout(20000);
           con.setDoInput(true);
           return con;
       } catch (MalformedURLException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       }
        return  null;
    }
}
