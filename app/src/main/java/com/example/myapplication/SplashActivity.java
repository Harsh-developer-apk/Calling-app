package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent recycle = new Intent(this,Recycle.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(recycle);
                finish();
            }
        },2250);
    }
}