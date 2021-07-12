package com.example.myapplication;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class CreateConnetionActivity extends AppCompatActivity {

    private Button mConnect;
    private ImageView imageView;
    FirebaseStorage storage;

    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_connetion);
        mConnect = findViewById(R.id.upload);
        imageView = findViewById(R.id.imageView2);
        storage = FirebaseStorage.getInstance();


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
                


            }
        });

        mConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                uploadImage();

            }
        });

    }

    private void uploadImage() {

        if(imageUri != null){
            StorageReference reference = storage.getReference().child("images/" + UUID.randomUUID().toString());

            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull  Task<UploadTask.TaskSnapshot> task) {

                    if(task.isSuccessful()){
                        Toast.makeText(CreateConnetionActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(CreateConnetionActivity.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }



    }

    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if(result != null){
                        imageView.setImageURI(result);
                        imageUri = result;
                    }
                }
            });


}