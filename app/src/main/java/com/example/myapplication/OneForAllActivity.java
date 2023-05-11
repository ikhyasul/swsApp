package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class OneForAllActivity extends AppCompatActivity {

    private ViewPager viewPager;
    LinearLayout Seting, Pin;
    private Handler handlerTime = new Handler(), handlerFast = new Handler();
    private Runnable runnableTime, runnableFast;
    LinearLayout btnOver, btnSetting, btnTutorial;
    TextView tvTanggal, tvJam;
    int lastPage = 3, iSend = 0, lastSend = 5;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    EditText Nama;
    EditText PIN1, PIN2;
    boolean[] bArrSeting = new boolean[9];

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

        Seting = (LinearLayout) findViewById(R.id.lOfaSeting);
        Pin = (LinearLayout) findViewById(R.id.lOfaPin);
        viewPager = findViewById(R.id.view_pager);
        SwipePagerAdapter pagerAdapter = new SwipePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        Seting();
        gantiPin();

        FragmentView(1);

//        viewPager.setVisibility(View.GONE);
//        frameLayout.setVisibility(View.VISIBLE);

        if (iSend == 0){
            FragmentView(0);
        } else {
            FragmentView(1);
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
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
                editor.putInt("fase", 0);
                editor.apply();
            }
        });
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
                editor.putInt("fase", 0);
                editor.apply();
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
                        FragmentView(0);
                    } else if (iSend == 1) {
                        FragmentView(1);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Menghentikan pembaruan waktu saat aktivitas dihancurkan
        handlerTime.removeCallbacks(runnableTime);
        handlerFast.removeCallbacks(runnableFast);
    }

    private void FragmentView(int iVall){
        if (iVall == 0){
            viewPager.setVisibility(View.VISIBLE);
            SwipePagerAdapter pagerAdapter = new SwipePagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);
            Seting.setVisibility(View.GONE);
            Pin.setVisibility(View.GONE);
        } else if (iVall == 1) {
            viewPager.setVisibility(View.GONE);
            Seting.setVisibility(View.VISIBLE);
            Pin.setVisibility(View.GONE);
        } else if (iVall == 2) {
            viewPager.setVisibility(View.GONE);
            Seting.setVisibility(View.GONE);
            Pin.setVisibility(View.VISIBLE);
        }
    }

    private void Seting(){
        TextView btnPin = (TextView) findViewById(R.id.btnOfaSetingPin);
        TextView Simpan = (TextView) findViewById(R.id.btnOfaSetingSimpan);
        Nama = (EditText) findViewById(R.id.edtOfaSetingName);
        SwitchCompat Gas1 = (SwitchCompat )findViewById(R.id.swOfaSetingGas1);
        SwitchCompat  Gas2 = (SwitchCompat )findViewById(R.id.swOfaSetingGas2);
        SwitchCompat  Gas3 = (SwitchCompat )findViewById(R.id.swOfaSetingGas3);
        SwitchCompat  Gas4 = (SwitchCompat )findViewById(R.id.swOfaSetingGas4);
        SwitchCompat  Gas5 = (SwitchCompat )findViewById(R.id.swOfaSetingGas5);
        SwitchCompat  Gas6 = (SwitchCompat )findViewById(R.id.swOfaSetingGas6);
        SwitchCompat  Gas7 = (SwitchCompat )findViewById(R.id.swOfaSetingGas7);
        SwitchCompat  Door1 = (SwitchCompat )findViewById(R.id.swOfaSetingDoor1);
        SwitchCompat  Door2 = (SwitchCompat )findViewById(R.id.swOfaSetingDoor2);
        Slider slPitch = (Slider)findViewById(R.id.sldOfaSetingPitch);
        Slider slRoll = (Slider)findViewById(R.id.sldOfaSetingRoll);

        String sName = sharedPref.getString("nama", "Belum Dinamai");
        Nama.setText(sName);
        bArrSeting[0] = sharedPref.getBoolean("gas1", false);
        Gas1.setChecked(bArrSeting[0]);
        bArrSeting[1] = sharedPref.getBoolean("gas2", false);
        Gas2.setChecked(bArrSeting[1]);
        bArrSeting[2] = sharedPref.getBoolean("gas4", false);
        Gas3.setChecked(bArrSeting[2]);
        bArrSeting[3] = sharedPref.getBoolean("gas5", false);
        Gas4.setChecked(bArrSeting[3]);
        bArrSeting[4] = sharedPref.getBoolean("gas5", false);
        Gas5.setChecked(bArrSeting[4]);
        bArrSeting[5] = sharedPref.getBoolean("gas6", false);
        Gas6.setChecked(bArrSeting[5]);
        bArrSeting[6] = sharedPref.getBoolean("gas7", false);
        Gas7.setChecked(bArrSeting[6]);
        bArrSeting[7] = sharedPref.getBoolean("door1", false);
        Door1.setChecked(bArrSeting[7]);
        bArrSeting[8] = sharedPref.getBoolean("door2", false);
        Door2.setChecked(bArrSeting[8]);

        slPitch.setValue(sharedPref.getFloat("slpitch", 5));
        slRoll.setValue(sharedPref.getFloat("slroll", 5));

        Gas1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bArrSeting[0] = b;
            }
        });
        Gas2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bArrSeting[1] = b;
            }
        });
        Gas3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bArrSeting[2] = b;
            }
        });
        Gas4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bArrSeting[3] = b;
            }
        });
        Gas5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bArrSeting[4] = b;
            }
        });
        Gas6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bArrSeting[5] = b;
            }
        });
        Gas7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bArrSeting[6] = b;
            }
        });
        Door1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bArrSeting[7] = b;
            }
        });
        Door2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bArrSeting[8] = b;
            }
        });
        btnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentView(2);
            }
        });
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNama = Nama.getText().toString();
                editor.putBoolean("gas1", bArrSeting[0]);
                editor.putBoolean("gas2", bArrSeting[1]);
                editor.putBoolean("gas3", bArrSeting[2]);
                editor.putBoolean("gas4", bArrSeting[3]);
                editor.putBoolean("gas5", bArrSeting[4]);
                editor.putBoolean("gas6", bArrSeting[5]);
                editor.putBoolean("gas7", bArrSeting[6]);
                editor.putBoolean("door1", bArrSeting[7]);
                editor.putBoolean("door2", bArrSeting[8]);
                editor.putFloat("slpitch", slPitch.getValue());
                editor.putFloat("slroll", slRoll.getValue());
                editor.putString("nama", sNama);
                editor.apply();
                NOTIF("Sudah disimpan sebagai " + sNama);
            }
        });
    }

    private void gantiPin(){
        PIN1 = (EditText) findViewById(R.id.edtOfaPin1);
        PIN2 = (EditText) findViewById(R.id.edtOfaPin2);
        ImageView button = (ImageView) findViewById(R.id.btnOfaPinEnter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sPin1 = PIN1.getText().toString();
                String sPin2 = PIN2.getText().toString();
                if (sPin1.length() < 6){
                    NOTIF("Pin terlalu pendek");
                } else if (sPin1.length() > 6){
                    NOTIF("Pin terlalu panjang");
                } else if (!sPin1.equals(sPin2)) {
                    NOTIF("Pin tidak sama");
                } else {
                    NOTIF("Pin berhasil dirubah");
                    PIN1.setText("");
                    PIN2.setText("");
                    editor.putString("pin", sPin1);
                    editor.putInt("fase", 1);
                    editor.apply();
                    FragmentView(1);
                }
            }
        });
    }

    void NOTIF (String message){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}