package com.codewithashu.bloodbankandroidapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class myadapterAB extends FirestoreRecyclerAdapter<model, myadapterAB.myviewolder>
{


        public myadapterAB(@NonNull FirestoreRecyclerOptions<model> options) {
            super(options);
        }


    @Override
    protected void onBindViewHolder(@NonNull myadapterAB.myviewolder holder, int position, @NonNull  model model) {

        holder.blood_ID.setText(model.getBlood_ID());
//        holder.Blood_Group.setText(model.getAge());
        holder.blood_Group.setText(model.getBlood_Group());
        holder.blood_Units.setText(model.getBlood_Units());
//        holder.Email_Address.setText(model.getEmail_Address());
    }

    @NonNull
    @Override
    public myviewolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avaialbleblood_item, parent, false);
        return new myviewolder(view);
    }

    static class myviewolder extends RecyclerView.ViewHolder{

        TextView blood_ID, blood_Units, blood_Group;


        public myviewolder(@NonNull View itemView) {
            super(itemView);
            blood_ID =(TextView)itemView.findViewById(R.id.tvbid);
            blood_Units = (TextView)itemView.findViewById(R.id.tvphone);
            blood_Group = (TextView)itemView.findViewById(R.id.tvbloodgrp);
//            Age = (TextView)itemView.findViewById(R.id.tvage);
//            Email_Address = (TextView)itemView.findViewById(R.id.tvemail);

        }
    }


}
