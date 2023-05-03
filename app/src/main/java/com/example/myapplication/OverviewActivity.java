package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_overview);
        LinearLayout btnSetting = (LinearLayout) findViewById(R.id.btnOverSeting);
        LinearLayout btnTutorial = (LinearLayout) findViewById(R.id.btnOverTutorial);
        TextView tvTanggal = (TextView) findViewById(R.id.tvOverDate);
        TextView tvJam = (TextView) findViewById(R.id.tvOverTime);
        Calendar newDate = Calendar.getInstance();
        SimpleDateFormat Jam = new SimpleDateFormat("h:m:s");
        String sJam = Jam.format(newDate.getTime()) + " WIB";
        tvJam.setText(sJam);
        SimpleDateFormat Tanggal = new SimpleDateFormat("dd MMMM yyyy");
        String sTanggal = Tanggal.format(newDate.getTime());
        tvTanggal.setText(sTanggal);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),SetingpinActivity.class);
                startActivity(i);
            }
        });
    }
}