package com.codewithashu.bloodbankandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    public CardView cardDonate, cardRequest, cardAvailable, cardAddBlood, cardDelete, cardLogOut;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cardDonate = (CardView)findViewById(R.id.donate);
        cardRequest = (CardView)findViewById(R.id.request);
        cardAvailable = (CardView)findViewById(R.id.available);
//        cardAddBlood = (CardView)findViewById(R.id.addblood);
        cardDelete = (CardView)findViewById(R.id.delete);
        cardLogOut = (CardView)findViewById(R.id.logout);
        fAuth = FirebaseAuth.getInstance();


/*

        cardDonate.setOnClickListener(this);
        cardRequest.setOnClickListener(this);
        cardAvailable.setOnClickListener(this);
        cardAddBlood.setOnClickListener(this);
        cardDelete.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){

            case R.id.donate:
                i = new Intent(this,Donateblood.class);
                startActivity(i);
                break;

            case R.id.request:
                i = new Intent(this,Requestblood.class);
                startActivity(i);
                break;

            case R.id.available:
                i = new Intent(this,AvailableBlood.class);
                startActivity(i);
                break;

            case R.id.addblood:
                i = new Intent(this,Addblood.class);
                startActivity(i);
                break;

            case R.id.delete:
                i = new Intent(this,Deleteblood.class);
                startActivity(i);
                break;
        }
*/

        cardDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent i = new Intent(getApplicationContext(), DonateBlood.class);
             startActivity(i);
            }
        });

        cardRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RequestBlood.class);
                startActivity(i);
            }
        });

//        cardAddBlood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(), AddBlood.class);
//                startActivity(i);
//            }
//        });

        cardAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent i = new Intent(getApplicationContext(), AvailableBlood.class);
             startActivity(i);
            }
        });

        cardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DeleteBlood.class);
                startActivity(i);
            }
        });

        cardLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Dashboard.this, Login.class));
                Toast.makeText(getApplicationContext(),"Logged Out Successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
