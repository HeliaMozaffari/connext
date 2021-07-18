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
import android.widget.ListView;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Cards cards_data[];
    private arrayAdapter arrayAdapter;
    private int i;
    Button mLogout;
    Button mCreate;
    Button matches;
    FirebaseFirestore fireStore;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    ListView listView;
    List<Cards> rowItems;
    private DatabaseReference usersDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersDb = FirebaseDatabase.getInstance().getReference().child("User");

        mLogout = findViewById(R.id.logout);
        mCreate = findViewById(R.id.createConnection);
        matches = findViewById(R.id.matches);


        rowItems= new ArrayList<Cards>();
        userDatabase();

        arrayAdapter = new arrayAdapter(this, R.layout.item, rowItems);


        SwipeFlingAdapterView flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                rowItems.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {

                Cards obj = (Cards) dataObject;
                String userId = obj.getUserId();
                usersDb.child(userId).child("connections").child("Nope").child(currentUser.getUid()).setValue(true);

                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {

                Cards obj = (Cards) dataObject;
                String userId = obj.getUserId();
                usersDb.child(userId).child("connections").child("match").child(currentUser.getUid()).setValue(true);


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
                    if(snapshot.exists() && !snapshot.child("connections").child("Nope").hasChild(currentUser.getUid()) && !snapshot.child("connections").child("match").hasChild(currentUser.getUid())){

                        Cards item = new Cards(snapshot.getKey(),snapshot.child("name").getValue().toString(),snapshot.child("picture").getValue().toString());
                        rowItems.add(item);
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



            matches.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MatchesActivity.class);
                    startActivity(intent);
                    return;

                }
            });



        }






}