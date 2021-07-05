package com.codewithashu.bloodbankandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class RequestBlood extends AppCompatActivity {

    EditText Bloodunits;
    Spinner BloodGroup;
    Button Request;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    /*DatabaseReference databaseReference;
    ValueEventListener listener;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_blood);

        Bloodunits = (EditText) findViewById(R.id.units);
//        databaseReference = FirebaseDatabase.getInstance().getReference("Request Blood");
        BloodGroup = (Spinner) findViewById(R.id.bldgrp);
        progressBar = findViewById(R.id.progressBar);
        fStore = FirebaseFirestore.getInstance();
        Request = (Button) findViewById(R.id.request);

        fetchData();

        /*list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        BloodGroup.setAdapter(adapter);
*/
        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String blood_Units = Bloodunits.getText().toString();
                String blood_Group = BloodGroup.getSelectedItem().toString();



                if (!validateInputs(blood_Units, blood_Group)) {

                    CollectionReference dbDonate = fStore.collection("Request Blood");

                    modelRequest requestmodel = new modelRequest(

                            blood_Units,
                            blood_Group
                    );


                    dbDonate.add(requestmodel)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(RequestBlood.this, "Data Added Successfully",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RequestBlood.this, e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                }

            }
        });
    }

    public void fetchData() {

    }

    public boolean validateInputs(String blood_Units, String blood_Group) {

        if (blood_Units.isEmpty()) {
            Bloodunits.setError("Blood Units is required");
            Bloodunits.requestFocus();
            return true;
        }
        if (blood_Group.equals("Choose a Blood Group")){
            Toast.makeText(RequestBlood.this,"Select Blood Group",Toast.LENGTH_SHORT).show();
            BloodGroup.requestFocus();
            return true;
        }
        return false;
    }


}