package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> al;
    private ArrayAdapter<String> arrayAdapter;
    private int i;
    Button mLogout;
    Button mCreate;
    FirebaseFirestore fireStore;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogout = findViewById(R.id.logout);
        mCreate = findViewById(R.id.createConnection);


        al = new ArrayList<>();
        userDatabase();

        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al);


        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {

            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                finish();
            }
        });

        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), CreateConnetionActivity.class));
                finish();
            }
        });

    }


        public void userDatabase(){
        final FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference dbR = FirebaseDatabase.getInstance().getReference().child("User");
            dbR.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull  DataSnapshot snapshot, @Nullable  String previousChildName) {
                    if(snapshot.exists()){
                        al.add(snapshot.getKey());
                        arrayAdapter.notifyDataSetChanged();
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
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }




}