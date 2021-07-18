package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

public class MatchesActivity extends AppCompatActivity {



    RecyclerView recyclerView;
    private ArrayList<Users> usersList;
    private DatabaseReference myRef;
    private RecyclerAdapter recyclerAdapter;
    private Context mContext;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        recyclerView = findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        myRef = FirebaseDatabase.getInstance().getReference();

        usersList = new ArrayList<>();

        ClearAll();

        GetDataFromFirebase();
    }

    private void GetDataFromFirebase() {
        Query query = myRef.child("User");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {


                if(snapshot.exists() && snapshot.child("connections").child("match").hasChild(currentUser.getUid())){
                    Users users = new Users();
                    users.setPicture(snapshot.child("picture").getValue().toString());
                    users.setName(snapshot.child("name").getValue().toString());
                    users.setDescription(snapshot.child("description").getValue().toString());
                    if(snapshot.child("facebook") != null){
                        users.setFacebook(snapshot.child("facebook").getValue().toString());
                    }
                    if(snapshot.child("instagram") != null){
                        users.setInstagram(snapshot.child("instagram").getValue().toString());
                    }
                    if(snapshot.child("youtube") != null){
                        users.setYoutube(snapshot.child("youtube").getValue().toString());
                    }
                    if(snapshot.child("twitter") != null){
                        users.setTwitter(snapshot.child("twitter").getValue().toString());
                    }
                    if(snapshot.child("twitch") != null){
                        users.setTwitch(snapshot.child("twitch").getValue().toString());
                    }
                    if(snapshot.child("tiktok") != null){
                        users.setTiktok(snapshot.child("tiktok").getValue().toString());
                    }
                    usersList.add(users);
                    recyclerAdapter = new RecyclerAdapter(getApplicationContext(),usersList);
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerAdapter.notifyDataSetChanged();
                }


            }


            @Override
            public void onChildChanged(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });




    }

    private void ClearAll(){
        if(usersList != null){
            usersList.clear();

            if(recyclerAdapter != null){
                recyclerAdapter.notifyDataSetChanged();

            }

        }
        usersList = new ArrayList<>();
    }
}


