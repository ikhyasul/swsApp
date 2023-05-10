package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OneForAllActivity extends AppCompatActivity {

    private ViewPager viewPager;
    FrameLayout frameLayout;
    private Handler handlerTime = new Handler(), handlerFast = new Handler();
    private Runnable runnableTime, runnableFast;
    LinearLayout btnOver, btnSetting, btnTutorial;
    TextView tvTanggal, tvJam;
    int lastPage = 3, iSend = 0, lastSend = 5;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_one_for_all);

        sharedPref = getSharedPreferences("MySettings", Context.MODE_PRIVATE);
        editor = sharedPref.edit();

//        Intent c = getIntent();
//        Bundle extra = c.getExtras();
//        iSend = extra.getInt("Send");

        btnOver = (LinearLayout) findViewById(R.id.btnOfaOver);
        btnSetting = (LinearLayout) findViewById(R.id.btnOfaSeting);
        btnTutorial = (LinearLayout) findViewById(R.id.btnOfaTutorial);

        tvTanggal = (TextView) findViewById(R.id.tvOfaDate);
        tvJam = (TextView) findViewById(R.id.tvOfaTime);

        viewPager = findViewById(R.id.view_pager);
        SwipePagerAdapter pagerAdapter = new SwipePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        frameLayout = (FrameLayout)findViewById(R.id.container);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.container, new Fragment4()).commit();
//        FragmentView(1);

//        viewPager.setVisibility(View.GONE);
//        frameLayout.setVisibility(View.VISIBLE);

        if (iSend == 0){
            viewPager.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.GONE);
        } else {
            viewPager.setVisibility(View.GONE);
            frameLayout.setVisibility(View.VISIBLE);
            btnOver.setBackgroundResource(R.drawable.costume_button2);
            btnSetting.setBackgroundResource(R.drawable.costume_button1);
            btnTutorial.setBackgroundResource(R.drawable.costume_button2);
        }

        btnOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                editor.putInt("fase", 0);
                editor.apply();
//                viewPager.setVisibility(View.VISIBLE);
//                frameLayout.setVisibility(View.GONE);
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                editor.putInt("fase", 0);
                editor.apply();
//                viewPager.setVisibility(View.VISIBLE);
//                frameLayout.setVisibility(View.GONE);
            }
        });
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                editor.putInt("fase", 0);
                editor.apply();
//                viewPager.setVisibility(View.VISIBLE);
//                frameLayout.setVisibility(View.GONE);
            }
        });

        handlerTime.postDelayed(runnableTime = new Runnable() {
            public void run() {
                updateTime();
                handlerTime.postDelayed(this, 1000);
            }
        }, 1000);
        handlerFast.post(runnableFast = new Runnable() {
            public void run() {
                iSend = sharedPref.getInt("fase", 0);
                if (iSend != lastSend){
                    if (iSend == 0){
                        viewPager.setVisibility(View.VISIBLE);
                        frameLayout.setVisibility(View.GONE);
                    } else if (iSend == 1) {
                        viewPager.setVisibility(View.GONE);
                        frameLayout.setVisibility(View.VISIBLE);
//                        FragmentView(1);
                        btnOver.setBackgroundResource(R.drawable.costume_button2);
                        btnSetting.setBackgroundResource(R.drawable.costume_button1);
                        btnTutorial.setBackgroundResource(R.drawable.costume_button2);
                    } else if (iSend == 2){
                        viewPager.setVisibility(View.GONE);
                        frameLayout.setVisibility(View.VISIBLE);
//                        FragmentView(2);
                        btnOver.setBackgroundResource(R.drawable.costume_button2);
                        btnSetting.setBackgroundResource(R.drawable.costume_button1);
                        btnTutorial.setBackgroundResource(R.drawable.costume_button2);
                    }
                    lastSend = iSend;
                }
                int page = viewPager.getCurrentItem();
                if (page != lastPage && iSend == 0){
                    if (page == 0){
                        btnOver.setBackgroundResource(R.drawable.costume_button1);
                        btnSetting.setBackgroundResource(R.drawable.costume_button2);
                        btnTutorial.setBackgroundResource(R.drawable.costume_button2);
                        lastPage = page;
                    } else if (page == 1){
                        btnOver.setBackgroundResource(R.drawable.costume_button2);
                        btnSetting.setBackgroundResource(R.drawable.costume_button1);
                        btnTutorial.setBackgroundResource(R.drawable.costume_button2);
                        lastPage = page;
                    } else if (page == 2){
                        btnOver.setBackgroundResource(R.drawable.costume_button2);
                        btnSetting.setBackgroundResource(R.drawable.costume_button2);
                        btnTutorial.setBackgroundResource(R.drawable.costume_button1);
                        lastPage = page;
                    }
                }
                handlerFast.post(this);
            }
        });
    }

    private void updateTime() {
        // Mendapatkan waktu saat ini
        long currentTime = System.currentTimeMillis();

        // Mengatur format waktu
        SimpleDateFormat Jam = new SimpleDateFormat("hh:mm:ss", new Locale("id", "ID"));
        SimpleDateFormat Tanggal = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id", "ID"));
        String sJam = Jam.format(new Date(currentTime));
        String sTanggal = Tanggal.format(new Date(currentTime));

        // Menampilkan waktu pada TextView
        tvJam.setText(sJam + " WIB");
        tvTanggal.setText(sTanggal);
    }

    private void FragmentView(int iVall){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        if (iVall == 1){
//            fragmentTransaction.replace(R.id.container, new Fragment4());
//        } else if (iVall == 2) {
//            fragmentTransaction.replace(R.id.container, new Fragment5());
//        }
//        fragmentTransaction.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Menghentikan pembaruan waktu saat aktivitas dihancurkan
        handlerTime.removeCallbacks(runnableTime);
        handlerFast.removeCallbacks(runnableFast);
    }
}