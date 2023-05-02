package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        LinearLayout btnSetting = (LinearLayout) findViewById(R.id.btnOverSeting);
        LinearLayout btnTutorial = (LinearLayout) findViewById(R.id.btnOverTutorial);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),SetingpinActivity.class);
                startActivity(i);
            }
        });
    }
}