package com.example.myhome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhome.m_DataObject.Client;
import com.example.myhome.m_mysql.DownloderSign_up;
import com.example.myhome.m_mysql.downloaderlogin;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
public class sign_up extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    String first_name ;
    String last_name;
    String email;
    EditText _prenomText;
    EditText _nomText;
    EditText  _emailText;
    EditText _passwordText;
    EditText _passwordVerifie;
    Button _signupButton;
    TextView _loginLink;
    String prenom;
    String nom;
    String password;
    String passwrdC;
    SharedPreferences pref;
    private LoginButton  loginButton;
    private CallbackManager callbackManager;
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        _prenomText=findViewById(R.id.input_prenom);
        _nomText=findViewById(R.id.input_nom);
        _emailText=findViewById(R.id.input_email);
        _passwordText= findViewById(R.id.input_password);
        _passwordVerifie=findViewById(R.id.confirmed_password);
        _signupButton=findViewById(R.id.btn_signup);
        _loginLink=findViewById(R.id.link_login);
        loginButton=findViewById(R.id.login_button);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();

            }
        });
        callbackManager=CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),loginActivity.class);
                startActivity(i);
                finish();
            }
        });
   }

   public void signup() {
        Log.d(TAG, "Signup");
        if (!validate()) {
            onSignupFailed();
        }
else {
            _signupButton.setEnabled(false);
            final ProgressDialog progressDialog = new ProgressDialog(sign_up.this,R.style.Theme_AppCompat_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();
            prenom = _prenomText.getText().toString();
            nom = _nomText.getText().toString();
            email = _emailText.getText().toString();
            password = _passwordText.getText().toString();
            passwrdC = _passwordVerifie.getText().toString();
            // TODO: Implement your own signup logic here.
               String urlAdress = "https://bigvigo.000webhostapp.com/insertclient.php?nom=" + nom + "&prenom=" + prenom + "&email=" + email.trim().toLowerCase() + "&password=" + password + "";
            DownloderSign_up downloader = new DownloderSign_up(sign_up.this, urlAdress);
            downloader.execute();
            progressDialog.dismiss();
            Intent i=new Intent(this,loginActivity.class);
           startActivity(i);
        }
    }
        public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _signupButton.setEnabled(true);
    }
    public boolean validate() {
        boolean valid = true;
       prenom=_prenomText.getText().toString();
       nom = _nomText.getText().toString();
         email = _emailText.getText().toString();
         password = _passwordText.getText().toString();
        String passwordC=_passwordVerifie.getText().toString();
        if (prenom.isEmpty() || prenom.length() < 3) { _prenomText.setError("le prenom plus 3 caractères");
            valid = false;
        } else {
            _prenomText.setError(null);
        }
        if (nom.isEmpty() || nom.length() < 3) { _nomText.setError("le nom plus 3 caractères ");
            valid = false;
        } else {
            _nomText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter un  valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("Entre 4 et 10 alphanumerique caractères");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        if (!password.equals(passwordC)) {
            _passwordVerifie.setError(" les deux mots ne sont pas identiques");
            valid = false;
        } else {
            _passwordVerifie.setError(null);
        }
        return valid;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
AccessTokenTracker tokenTracker=new AccessTokenTracker() {
    @Override
    protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
        if(currentAccessToken==null){
            Toast.makeText(sign_up.this, "User logged out", Toast.LENGTH_SHORT).show();
        }else{
            loadUser(currentAccessToken);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String emaila= last_name+first_name+ "@gmail.com";
                    String emai=emaila.toLowerCase().replaceAll(" ","");;
                    String password="facebook";
                    String urlAdress = "https://bigvigo.000webhostapp.com/insertclient.php?nom=" + last_name + "&prenom=" +first_name + "&email=" + emai + "&password=" + password +"";
                    DownloderSign_up downloader = new DownloderSign_up(sign_up.this, urlAdress);
                    downloader.execute();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String emaila= last_name+first_name+ "@gmail.com";
                            String emai=emaila.toLowerCase().replaceAll(" ","");;
                            String password="facebook";
                            String urlA="https://bigvigo.000webhostapp.com/selectclient.php?email="+ emai +"&password="+ password +"";
                            downloaderlogin  down=new downloaderlogin(sign_up.this,urlA);
                            down.execute();
                        }},2000);}},500);
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onLoginSuccess();
                        }
                    }, 3000);
        }
    }};
   private  void  loadUser(AccessToken newAccessToken){
       Bundle parameters=new Bundle();
       parameters.putString("fields","first_name,last_name,email");
       GraphRequest request=GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
           @Override
           public void onCompleted(JSONObject object, GraphResponse response) {

            try{
                 first_name =object.getString("first_name");
                 last_name=object.getString("last_name");
            } catch (JSONException e) {
                e.printStackTrace();
            }
          }
           });
       request.setParameters(parameters);
       request.executeAsync();
   }
    public void onLoginSuccess() {
        onBackPressed();
    }
}





