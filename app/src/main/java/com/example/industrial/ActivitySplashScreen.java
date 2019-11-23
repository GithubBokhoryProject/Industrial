package com.example.industrial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ActivitySplashScreen extends AppCompatActivity {
    ///////////////splash screen
    private static int SPLACH_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(ActivitySplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLACH_TIME_OUT);
    }
}
