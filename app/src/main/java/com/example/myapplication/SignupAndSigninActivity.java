package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupAndSigninActivity extends AppCompatActivity {

    private Button mRegister, mLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_and_signin);

        mRegister = (Button) findViewById(R.id.register);
        mLogin = (Button) findViewById(R.id.login);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupAndSigninActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();
                return;

            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupAndSigninActivity.this, SigninActivity.class);
                startActivity(intent);
                finish();
                return;


            }
        });
    }
}