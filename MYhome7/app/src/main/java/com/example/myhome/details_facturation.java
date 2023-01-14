package com.example.myhome;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhome.m_DataObject.Article;
import com.example.myhome.m_DataObject.Client;
import com.example.myhome.m_mysql.DownloderSign_up;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class details_facturation extends AppCompatActivity {
    private static final String url = "jdbc:mysql://mysql5027.site4now.net:3306/db_a6114f_myhome";
    private static final String user = "a6114f_myhome";
    private static final String pass = "zakaria@1998";
 final  String TAG="Validation d'infos";
    EditText input_nom,input_prenom,input_email,input_ville,input_adress,input_codepostale,input_tele;
    String nom, prenom,ville,adress,codepostale,tele,email;
    Button bValide;
    SharedPreferences pref;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_facturation);
      input_nom=findViewById(R.id.input_nom);
       input_prenom=findViewById(R.id.input_prenom);
        input_email=findViewById(R.id.input_email);
       input_adress=findViewById(R.id.input_adress);
        input_ville=findViewById(R.id.input_ville);
        input_codepostale=findViewById(R.id.input_codepostal);
        input_tele=findViewById(R.id.input_tele);

        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        if(pref.contains("nom") && pref.contains("prenom") && pref.contains("email")){
            input_nom.setText(pref.getString("nom",null));
            input_prenom.setText(pref.getString("prenom",null));
            input_email.setText(pref.getString("email",null));
        }
        bValide=findViewById(R.id.valid);
        bValide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
          valid();
            }
        });

    }
    public void valid() {
        Log.d(TAG, "validation");
        if (!validate()) {
            onFailed();
        }
        else {
          bValide.setEnabled(false);
            prenom = input_prenom.getText().toString();
            nom = input_nom.getText().toString();
            email = input_email.getText().toString();
            adress= input_adress.getText().toString();
            ville = input_ville.getText().toString();
            codepostale=input_codepostale.getText().toString();
            tele=input_tele.getText().toString();
           Client client=new Client(pref.getInt("id",-1),nom,prenom,email,adress,ville,codepostale,tele);
            InsertClient Insert= new InsertClient(client);
            Insert.execute("");
            Intent i=new Intent(getApplicationContext(),Commande.class);
             startActivity(i);
finish();
                  }
    }
    public void onFailed() {
        Toast.makeText(getBaseContext(), "Corrigé les erreurs", Toast.LENGTH_LONG).show();
        bValide.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;
        prenom = input_prenom.getText().toString();
        nom = input_nom.getText().toString();
        email = input_email.getText().toString();
        adress= input_adress.getText().toString();
        ville = input_ville.getText().toString();
        codepostale=input_codepostale.getText().toString();
        tele=input_tele.getText().toString();
        if (prenom.isEmpty() || prenom.length() < 3) { input_prenom.setError("le prenom plus 3 caractères");
            valid = false;
        } else {
            input_prenom.setError(null);
        }
        if (nom.isEmpty() || nom.length() < 3) { input_nom.setError("le nom plus 3 caractères ");
            valid = false;
        } else {
            input_nom.setError(null);
        }
        if (adress.isEmpty()) {
            input_adress.setError("le champ ville est obligatoire");
            valid = false;
        } else {
            input_adress.setError(null);
        }
        if (ville.isEmpty()) {
            input_ville.setError("le champ ville est obligatoire");
            valid = false;
        } else {
            input_ville.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            input_email.setError("enter un  valid email address");
            valid = false;
        } else {
            input_email.setError(null);
        }

        if (tele.isEmpty()) {
            input_tele.setError("le numéro de telephone est obligatoire");
            valid = false;
        } else {
            input_tele.setError(null);
        }
        if (codepostale.isEmpty()) {
            input_codepostale.setError("le code Postale est obligatoire");
            valid = false;
        } else {
            input_codepostale.setError(null);
        }
        return valid;
    }
    private class InsertClient extends AsyncTask<String, Void,String> {
Client client;
String s;
        public InsertClient(Client client) {
            this.client = client;
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
                String sql="UPDATE clients SET  nomClient = '"+client.getNom() +"', prenomClient = '" +client.getPrenom() + "', telephone= '"+client.getTelephone()+"', ville = '"+client.getVille()+"', address = '"+client.getAdress()+"', codepostale = '"+client.getCode_postale()+"' WHERE clients.idClient= "+client.getId()+"";
                st.executeUpdate(sql);
            } catch (Exception e) {
               e.printStackTrace();
              s=e.toString();
            }
            return s ;
        }
        protected void onPostExecute(String result) {
        }
    }}