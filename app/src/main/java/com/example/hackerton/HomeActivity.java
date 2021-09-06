package com.example.hackerton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void funcMoveMap(View v){
        Intent it=new Intent(HomeActivity.this,MainActivity.class);
        startActivity(it);
    }

    public void funcMoveTraffic(View v){
        Intent it=new Intent(HomeActivity.this,TrafficActivity.class);
        startActivity(it);
    }

}