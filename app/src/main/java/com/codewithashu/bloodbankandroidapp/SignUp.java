package com.codewithashu.bloodbankandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    TextView loginText;
    EditText mSignupname, mSignupage, mSignupbloodgroup, mSignupphone, mSignuppass, mSignupemail;
    Button mSignup;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mSignupname = (EditText)findViewById(R.id.Signupname);
        mSignupage = (EditText)findViewById(R.id.Signupage);
        mSignupbloodgroup = (EditText)findViewById(R.id.Signupbloodgrp);
        mSignupphone = (EditText)findViewById(R.id.Signupphone);
        mSignuppass = (EditText)findViewById(R.id.Signuppassword);
        mSignupemail = (EditText)findViewById(R.id.Signupemail);
        mSignup = (Button)findViewById(R.id.loginbutton);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        loginText = (TextView)findViewById(R.id.textView3);

       /* if(fAuth.getCurrentUser()!=null);{
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mSignupemail.getText().toString().trim();
                String pass = mSignuppass.getText().toString();

                if(TextUtils.isEmpty(email)){
                    mSignupemail.setError("Email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    mSignuppass.setError("Password is required");
                    return;
                }
                if(pass.length() < 6){
                    mSignuppass.setError("Password must be >=6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp.this, "Sign Up Successfull",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }else{
                            Toast.makeText(SignUp.this,"Error!.."+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });
    }
}