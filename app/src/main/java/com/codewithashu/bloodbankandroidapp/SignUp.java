package com.codewithashu.bloodbankandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    TextView loginText;
    EditText mSignupname, /*mSignupage, mSignupbloodgroup,*/ mSignupphone, mSignupemail, mSignuppass;
    Button mSignup;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mSignupname = (EditText)findViewById(R.id.Signupname);
//        mSignupage = (EditText)findViewById(R.id.Signupage);
//        mSignupbloodgroup = (EditText)findViewById(R.id.Signupbloodgrp);
        mSignupphone = (EditText)findViewById(R.id.Signupphone);
        mSignupemail = (EditText)findViewById(R.id.Signupemail);
        mSignuppass = (EditText)findViewById(R.id.Signuppassword);
        mSignup = (Button)findViewById(R.id.loginbutton);
        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
        loginText = (TextView)findViewById(R.id.textView3);
        fStore = FirebaseFirestore.getInstance();

       /* if(fAuth.getCurrentUser()!=null);{
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/

        mSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mSignupemail.getText().toString().trim();
                String pass = mSignuppass.getText().toString();
                String name = mSignupname.getText().toString();
                String phno = mSignupphone.getText().toString();
//                String age = mSignupage.getText().toString();
//                String bg = mSignupbloodgroup.getText().toString();

                if(TextUtils.isEmpty(email)){
                    mSignupemail.setError("Email is required");
                    return;
                }if(TextUtils.isEmpty(name)){
                    mSignupname.setError("Name is required");
                    return;
                }if(TextUtils.isEmpty(phno)){
                    mSignupphone.setError("Phone No is required");
                    return;
                }
//                if(TextUtils.isEmpty(age)){
//                    mSignupage.setError("Age is required");
//                    return;
//                }if(TextUtils.isEmpty(bg)){
//                    mSignupbloodgroup.setError("Blood Group is required");
//                    return;
//                }
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
                            userID = fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("Users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("Full_Name", name);
//                            user.put("Blood_Group", bg);
//                            user.put("Age", age);
                            user.put("Phone_Number", phno);
                            user.put("Email_Address", email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d("Tag","User Profile Created" +userID);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), Login.class));
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