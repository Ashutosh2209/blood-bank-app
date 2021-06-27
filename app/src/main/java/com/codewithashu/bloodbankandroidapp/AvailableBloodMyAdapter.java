package com.codewithashu.bloodbankandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.protobuf.StringValue;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AvailableBloodMyAdapter extends RecyclerView.Adapter<AvailableBloodMyAdapter.MyViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public AvailableBloodMyAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public AvailableBloodMyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.avaialbleblood_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AvailableBloodMyAdapter.MyViewHolder holder, int position) {

        User user = userArrayList.get(position);
        holder.name.setText(user.mSignupname);
        holder.phone.setText(String.valueOf(user.mSignupphone));
        holder.bloodGroup.setText(user.mSignupbloodgroup);
        holder.age.setText(String.valueOf(user.mSignupage));

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, phone, age, bloodGroup;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvfirstName);
            phone = itemView.findViewById(R.id.tvphone);
            bloodGroup = itemView.findViewById(R.id.tvbloodgrp);
            age = itemView.findViewById(R.id.tvage);

        }
    }
}
