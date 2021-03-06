package com.codewithashu.bloodbankandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView signUp;
    EditText mLoginuser,mLoginpass;
    Button mLogin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginuser = (EditText)findViewById(R.id.loginemail);
        mLoginpass = (EditText)findViewById(R.id.loginPassword);
        mLogin = (Button)findViewById(R.id.loginbutton);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        signUp = (TextView)findViewById(R.id.textView3);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,SignUp.class));
            }
        });


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUserAccount();
                progressBar.setVisibility(View.VISIBLE);
//                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });
    }
        private void loginUserAccount(){

            String email = mLoginuser.getText().toString().trim();
            String pass = mLoginpass.getText().toString();

            if(TextUtils.isEmpty(email)){
                mLoginuser.setError("Username is required");
                return;
            }
            if(TextUtils.isEmpty(pass)){
                mLoginpass.setError("Password is required");
                return;
            }
            if(pass.length() < 6){
                mLoginpass.setError("Password must be >=6 characters");
                return;
            }

            fAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "Login Successfull",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                    }else{
                        Toast.makeText(Login.this, "Error!..Invalid Credentials",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

        }

}
