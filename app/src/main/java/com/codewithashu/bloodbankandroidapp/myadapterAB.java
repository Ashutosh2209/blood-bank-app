package com.codewithashu.bloodbankandroidapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


public class myadapterAB extends FirestoreRecyclerAdapter<model, myadapterAB.myviewolder>
{


    Button adelete;

    public myadapterAB(@NonNull FirestoreRecyclerOptions<model> options) {
            super(options);
        }

    @Override
    protected void onBindViewHolder(@NonNull final myadapterAB.myviewolder holder, final int position, @NonNull final model model) {

        holder.blood_ID.setText(model.getBlood_ID());
        holder.blood_Units.setText(model.getBlood_Units());
        holder.blood_Group.setText(model.getBlood_Group());


        String docId = getSnapshots().getSnapshot(position).getId();

        Log.d("GETREFTEST", docId);


                    holder.blood_edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final DialogPlus dialogPlus=DialogPlus.newDialog(holder.blood_Units.getContext())
                                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                                        .setExpanded(true,1100)
                                        .create();



                            View myview = dialogPlus.getHolderView();

                            EditText blood_ID = myview.findViewById(R.id.ubid);
                            EditText blood_Units =myview.findViewById(R.id.uunits);
                            EditText blood_Group =myview.findViewById(R.id.ubldgrp);
                            Button aupdate = myview.findViewById(R.id.uupdate);


                            blood_ID.setText(model.getBlood_ID());
                            blood_Units.setText(model.getBlood_Units());
                            blood_Group.setText(model.getBlood_Group());

                            dialogPlus.show();

                                aupdate.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("blood_ID", blood_ID.getText().toString());
                                        map.put("blood_Units", blood_Units.getText().toString());
                                        map.put("blood_Group", blood_Group.getText().toString());

                                        final DocumentReference docRef = FirebaseFirestore.getInstance().collection("Donate Blood")
                                                .document(docId);

                                        docRef.update(map)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(view.getContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                                                        dialogPlus.dismiss();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                      /*  docRef.collection("Donate Blood").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    List<String> paths = new ArrayList<>();
                                                    List<ModelClass> documents = new ArrayList<>();
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        paths.add(document.getPath());
                                                        documents.add(document.toObject(ModelClass.class));
                                                    }

                                                    //Do what you need to do with the lists
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
*/

//                                        docRef.get();

                                        /*docRef.collection("Donate Blood").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                            @Override
                                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                                Toast.makeText(view.getContext(), "Success", Toast.LENGTH_SHORT);
                                                List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();
                                                for (DocumentSnapshot snapshot: snapshotList){
                                                    docRef.update(map);
                                                    Toast.makeText(view.getContext(), "Success", Toast.LENGTH_SHORT);
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT);
                                            }
                                        });*/



                                    }
                                });
                      }


                    });

                    holder.blood_delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            AlertDialog.Builder builder=new AlertDialog.Builder(holder.blood_ID.getContext());
                            builder.setTitle("Delete Record");
                            builder.setMessage("Are you sure you want to delete this record ?");

                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    final DocumentReference docRef = FirebaseFirestore.getInstance().collection("Donate Blood")
                                            .document(docId);

                                    docRef.delete();
                                }
                            });

                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            builder.show();
                        }
                    });

    }



    @NonNull
    @Override
    public myviewolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avaialbleblood_item, parent, false);
        return new myviewolder(view);
    }

    static class myviewolder extends RecyclerView.ViewHolder{

        TextView blood_ID, blood_Units, blood_Group;
        ImageView blood_edit, blood_delete;

        public myviewolder(@NonNull View itemView) {
            super(itemView);
            blood_ID =(TextView)itemView.findViewById(R.id.tvbid);
            blood_Units = (TextView)itemView.findViewById(R.id.tvunits);
            blood_Group = (TextView)itemView.findViewById(R.id.tvbloodgrp);

            blood_edit = (ImageView)itemView.findViewById(R.id.tvedit);
            blood_delete = (ImageView)itemView.findViewById(R.id.tvdelete);
//            Age = (TextView)itemView.findViewById(R.id.tvage);
//            Email_Address = (TextView)itemView.findViewById(R.id.tvemail);

        }
    }


}
