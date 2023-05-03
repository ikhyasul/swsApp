package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tutorial);
        LinearLayout btnOver = (LinearLayout) findViewById(R.id.btnTutorialOver);
        LinearLayout btnSeting = (LinearLayout) findViewById(R.id.btnTutorialSeting);
        TextView tvTanggal = (TextView) findViewById(R.id.tvTutorialDate);
        TextView tvJam = (TextView) findViewById(R.id.tvTutorialTime);
        Calendar newDate = Calendar.getInstance();
        SimpleDateFormat Jam = new SimpleDateFormat("h:m:s a");
        String sJam = Jam.format(newDate.getTime()) + " WIB";
        tvJam.setText(sJam);
    }
}