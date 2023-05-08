package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = new Bundle();
        extras.putInt("Send",0);
        Intent c = new Intent(getApplicationContext(),OneForAllActivity.class);
        c.putExtras(extras);
        startActivity(c);
//        Intent i= new Intent(getApplicationContext(),OneForAllActivity.class);
//        startActivity(i);
    }
}