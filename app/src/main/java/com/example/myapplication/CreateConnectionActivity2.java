package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class CreateConnectionActivity2 extends AppCompatActivity {
    EditText mName,mDescription, mYoutube, mTwitch, mInstagram, mFacebook, mTwitter, mTiktok;
    Button mConnect;
    FirebaseFirestore fireStore;
    String userID;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_connection2);
        fireStore = FirebaseFirestore.getInstance();

        mName = findViewById(R.id.name);
        mDescription = findViewById(R.id.description);
        mYoutube = findViewById(R.id.youtube);
        mTwitch = findViewById(R.id.twitch);
        mInstagram = findViewById(R.id.instagram);
        mFacebook = findViewById(R.id.facebook);
        mTwitter = findViewById(R.id.twitter);
        mTiktok = findViewById(R.id.tiktok);
        mConnect = findViewById(R.id.connect);


        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = mName.getText().toString().trim();
                String description = mDescription.getText().toString().trim();
                String youtube = mYoutube.getText().toString().trim();
                String twitch = mTwitch.getText().toString().trim();
                String instagram = mInstagram.getText().toString().trim();
                String facebook = mFacebook.getText().toString().trim();
                String twitter = mTwitter.getText().toString().trim();
                String tiktok = mTiktok.getText().toString().trim();


                Map<String, Object> user = new HashMap<>();
                user.put("Name", name);
                user.put("description", description);
                user.put("youtube", youtube);
                user.put("twitch", twitch);
                user.put("instagram", instagram);
                user.put("facebook", facebook);
                user.put("twitter", twitter);
                user.put("tiktok", tiktok);

                fireStore.collection("Users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(CreateConnectionActivity2.this,"Connext Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateConnectionActivity2.this,"Connext Created", Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });





    }
}






