package com.codewithashu.bloodbankandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class DonateBlood extends AppCompatActivity {

    EditText Bloodid, Bloodunits, Phone_No;
    Spinner BloodGroup;
    Button Donate;
    ProgressBar progressBar;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_blood);

        Bloodid = (EditText) findViewById(R.id.bid);
        Bloodunits = (EditText) findViewById(R.id.units);
        Phone_No = (EditText)findViewById(R.id.phone);
        BloodGroup = (Spinner) findViewById(R.id.bldgrp);
        progressBar = findViewById(R.id.progressBar);
        fStore = FirebaseFirestore.getInstance();
        Donate = (Button) findViewById(R.id.donate);

        Donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String blood_ID = Bloodid.getText().toString().trim();
                String blood_Units = Bloodunits.getText().toString();
                String blood_Group = BloodGroup.getSelectedItem().toString();
                String phone_No = Phone_No.getText().toString();

                if (!validateInputs(blood_ID, blood_Units, blood_Group, phone_No)) {

                    CollectionReference dbDonate = fStore.collection("Donate Blood");

                    modelDonate donatemodel = new modelDonate(

                            blood_ID,
                            blood_Units,
                            blood_Group,
                            phone_No
                    );


                    dbDonate.add(donatemodel)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(DonateBlood.this, "Data Added Successfully",Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(DonateBlood.this, e.getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });
                }

            }
        });
    }

    public boolean validateInputs(String blood_ID, String blood_Units, String blood_Group, String phone_No) {
        if (blood_ID.isEmpty()) {
            Bloodid.setError("Blood Id is required");
            Bloodid.requestFocus();
            return true;
        }
        if (blood_Units.isEmpty()) {
            Bloodunits.setError("Blood Units is required");
            Bloodunits.requestFocus();
            return true;
        }if (phone_No.equals("")) {
            Phone_No.setError("Phone Number is required");
            Phone_No.requestFocus();
            return true;
        }
        if (blood_Group.equals("Choose a Blood Group")){
            Toast.makeText(DonateBlood.this,"Select Blood Group",Toast.LENGTH_SHORT).show();
            BloodGroup.requestFocus();
            return true;
        }
        return false;
    }


}

/*


//                CollectionReference collectionReference = fStore.collection("Donate");

//                DocumentReference documentReference = db.collection("Donate Blood").document();

                modelDonate donatemodel = new modelDonate(
                        bloodid,
                        bunits,
                        bloodgrp
                );

               */



/* collectionReference.add(donatemodel)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                progressBar.setVisibility(View.VISIBLE);
                                Toast.makeText(DonateBlood.this, "Data Added Successfully",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.VISIBLE);
                        Toast.makeText(DonateBlood.this, "Error",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));

                    }
                });*/

/*

//                Map<String, Object> user = new HashMap<>();
//                user.put("Blood Id", bloodid);
//                user.put("Blood Units", bunits);
//                user.put("Blood Group", bloodgrp);

// Add a new document with a generated ID
                fStore.collection("Donate Blood")
                        .add(donatemodel)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                Toast.makeText(DonateBlood.this, "Data Added Successfully",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

            }
        
    }


}*/
