package com.example.myhome;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.gms.common.ConnectionResult;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Arrays;
public class loginActivity extends AppCompatActivity  {
    private static final String TAG = "LoginActivity";
    String first_name ;
    String last_name;
  EditText _emailText;
     EditText _passwordText;
    Button _loginButton;
     TextView _signupLink;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
    _emailText=findViewById(R.id.input_email);
        _passwordText=findViewById(R.id.input_password);
        _loginButton=findViewById(R.id.btn_login);
        _signupLink=findViewById(R.id.link_signup);
        loginButton=findViewById(R.id.login_button);
        callbackManager= CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });


        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),sign_up.class);
                startActivity(intent);
                finish();
            }
        });
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
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);
        final ProgressDialog progressDialog = new ProgressDialog(getApplicationContext(),R.style.Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authentification ...");

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        password=password.trim();
        email=email.toLowerCase().trim();
        String urlAdress="https://bigvigo.000webhostapp.com/selectclient.php?email="+ email +"&password="+ password +"";
        downloaderlogin  downloader=new downloaderlogin(this,urlAdress);
        downloader.execute();
        // TODO: Implement your own authentication logic here.
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                    }
                }, 2000);
    }
    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
       onBackPressed();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

     public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 20) {
            _passwordText.setError("between 4 and 20 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);


    }
    AccessTokenTracker tokenTracker=new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(currentAccessToken==null){
                Toast.makeText(loginActivity.this, "User logged out", Toast.LENGTH_SHORT).show();
            }else{
                loadUser(currentAccessToken);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String emaila= last_name+first_name+ "@gmail.com";
                        String emai=emaila.toLowerCase().replaceAll(" ","");;
                        String password="facebook";
                        String urlAdress = "https://bigvigo.000webhostapp.com/insertclient.php?nom=" + last_name + "&prenom=" +first_name + "&email=" + emai + "&password=" + password +"";
                        DownloderSign_up downloader = new DownloderSign_up(loginActivity.this, urlAdress);
                        downloader.execute();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                String emaila= last_name+first_name+ "@gmail.com";
                                String emai=emaila.toLowerCase().replaceAll(" ","");;
                                String password="facebook";
                                String urlA="https://bigvigo.000webhostapp.com/selectclient.php?email="+ emai +"&password="+ password +"";
                                downloaderlogin  down=new downloaderlogin(loginActivity.this,urlA);
                                down.execute();
                            }},500);}},1000);
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
}
