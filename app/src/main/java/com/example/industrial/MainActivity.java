package com.example.industrial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // btn go to activity user
    public void btn_user(View view) {
        Intent intent=new Intent(this,ActivityUser.class);
        startActivity(intent);
    }
    // btn go to industry
    public void btn_industry(View view){
        Intent intent=new Intent(this,ActivityIndustry.class);
        startActivity(intent);
    }
}
