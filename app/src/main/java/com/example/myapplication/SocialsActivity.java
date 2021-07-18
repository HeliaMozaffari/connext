package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class SocialsActivity extends AppCompatActivity {

    private ImageView imageView;
    Users users = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socials);

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof Users){
            users = (Users) object;

        }

        imageView = findViewById(R.id.asimageView);

        if(users != null){
            Glide.with(getApplicationContext()).load(users.getPicture()).into(imageView);
            Toast.makeText(SocialsActivity.this, users.getFacebook(), Toast.LENGTH_SHORT).show();

        }



    }

}