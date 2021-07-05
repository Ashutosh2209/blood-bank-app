package com.codewithashu.bloodbankandroidapp;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AvailableBlood extends AppCompatActivity {

    RecyclerView recyclerView;
//    ProgressDialog progressDialog;
    FirebaseFirestore fStore;
    myadapterAB myadapter;
//    private FirestoreRecyclerAdapter myadapter;
//    private FirestoreRecyclerAdapter<model, myviewholder> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_blood);

        fStore = FirebaseFirestore.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.newrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



//
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Fetching Data...");
//        progressDialog.show();

        Query query = fStore.collection("Donate Blood").orderBy("blood_ID", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<model> options = new FirestoreRecyclerOptions.Builder<model>()
                .setQuery(query, model.class)
                .build();

//        FirestoreRecyclerAdapter myadapter = new FirebaseRecyclerAdapter<model, myviewholder>(options) {

//            @NonNull
//            @Override
//            public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.avaialbleblood_item, parent, false);
//                return new myviewholder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(@NonNull AvailableBlood.myviewholder holder, int position, @NonNull model model) {
//
//                holder.Full_Name.setText(model.getName());
//                holder.Blood_Group.setText(model.getAge());
//                holder.Blood_Group.setText(model.getBloodGroup());
//                holder.Phone_Number.setText(model.getPhone());
//
//            }
//        };
//
//
        myadapter = new myadapterAB(options);
        recyclerView.setAdapter(myadapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        myadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myadapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_availableblood, menu);

        MenuItem menuItem = menu.findItem(R.id.asearch);

        SearchView searchView = (SearchView)menuItem.getActionView();

        searchView.setQueryHint(Html.fromHtml("<font color = #ffffff>" +
                getResources().getString(R.string.search_color) + "</font>"));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                proccesssearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                proccesssearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void proccesssearch(String s){

//        Query searchquery = fStore.collection("Donate Blood").orderBy("blood_ID").startAt(s).endAt(s+"\uf8ff", model.class);

        FirestoreRecyclerOptions<model> options = new FirestoreRecyclerOptions.Builder<model>()
                .setQuery(fStore.collection("Donate Blood").orderBy("blood_Group", Query.Direction.ASCENDING).startAt(s).endAt("\uf8ff"), model.class)
                .build();

        myadapter = new myadapterAB(options);
        myadapter.startListening();
        recyclerView.setAdapter(myadapter);

    }
}

//        myadapter = new myadapterAB(opt);


//        recyclerView.setAdapter(myadapter);



//    @Override
//    protected void onStart() {
//        super.onStart();
//        myadapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        myadapter.stopListening();
//    }

//    private class myviewholder extends RecyclerView.ViewHolder {
//
//        TextView Full_Name, Phone_Number, Blood_Group, Age;
//
//        public myviewholder(@NonNull View itemView) {
//            super(itemView);
//
//            Full_Name =(TextView)itemView.findViewById(R.id.tvfName);
//            Phone_Number = (TextView)itemView.findViewById(R.id.tvphone);
//            Blood_Group = (TextView)itemView.findViewById(R.id.tvbloodgrp);
//            Age = (TextView)itemView.findViewById(R.id.tvage);
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        myadapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        myadapter.stopListening();
//    }
//}


     /*   FirebaseRecyclerOptions<modelAB> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("students"), model.class)
                        .build();

        //fStore = FirebaseFirestore.getInstance();
        //userArrayList = new ArrayList<User>();
        availableBloodMyAdapter = new AvailableBloodMyAdapter(options);

        recyclerView.setAdapter(availableBloodMyAdapter);

      *///  UserDetails();


    /*}

    @Override
    protected void onStart() {
        super.onStart();
        availableBloodMyAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        availableBloodMyAdapter.stopListening();
    }*/

/*    private void UserDetails() {

        fStore.collection("Users").orderBy("Full Name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                        if(error != null){
                            Log.e("Firestore Error", error.getMessage());
                            return;
                        }

                        for (DocumentChange dc : value.getDocumentChanges()){

                            if(dc.getType() == DocumentChange.Type.ADDED){

                                userArrayList.add(dc.getDocument().toObject(User.class));

                            }

                            availableBloodMyAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }

                    }
                });

    }*/
