package com.codewithashu.bloodbankandroidapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddBlood extends AppCompatActivity {
    EditText Bloodid, Bloodunits;
    Spinner BloodGroup;
    Button AddBlood;
    ProgressBar progressBar;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood);

        Bloodid = (EditText) findViewById(R.id.bid);
        Bloodunits = (EditText) findViewById(R.id.units);

        BloodGroup = (Spinner) findViewById(R.id.bldgrp);
        progressBar = findViewById(R.id.progressBar);
        fStore = FirebaseFirestore.getInstance();
        AddBlood = (Button) findViewById(R.id.Addblood);

        AddBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String blood_ID = Bloodid.getText().toString().trim();
                String blood_Units = Bloodunits.getText().toString();
                String blood_Group = BloodGroup.getSelectedItem().toString();

                if (!validateInputs(blood_ID, blood_Units, blood_Group)) {

                    CollectionReference dbAddBlood= fStore.collection("Add Blood");

                    modelAdd addmodel = new modelAdd(

                            blood_ID,
                            blood_Units,
                            blood_Group
                    );


                    dbAddBlood.add(addmodel)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(AddBlood.this, "Data Added Successfully",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(AddBlood.this, e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                }

            }
        });
    }

    public boolean validateInputs(String blood_ID, String blood_Units, String blood_Group) {
        if (blood_ID.isEmpty()) {
            Bloodid.setError("Blood Id is required");
            Bloodid.requestFocus();
            return true;
        }
        if (blood_Units.isEmpty()) {
            Bloodunits.setError("Blood Units is required");
            Bloodunits.requestFocus();
            return true;
        }
        if (blood_Group.equals("Choose a Blood Group")){
            Toast.makeText(AddBlood.this,"Select Blood Group",Toast.LENGTH_SHORT).show();
            BloodGroup.requestFocus();
            return true;
        }
        return false;
    }


}