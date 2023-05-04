package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class OverviewActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private Runnable runnable;
    TextView tvTanggal, tvJam;
    TextView tvNotif1, tvNotif2, tvNotif3;
    LinearLayout lNotif1, lNotif2, lNotif3;
    ImageView imgNotif1, imgNotif2, imgNotif3;
    String[] sNotif = {"","",""};
    int[] iNotif = {0,0,0};
    int thisNotif = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_overview);
        // ----findViewById----
        LinearLayout btnSetting = (LinearLayout) findViewById(R.id.btnOverSeting);
        LinearLayout btnTutorial = (LinearLayout) findViewById(R.id.btnOverTutorial);
        tvTanggal = (TextView) findViewById(R.id.tvOverDate);
        tvJam = (TextView) findViewById(R.id.tvOverTime);
        lNotif1 = (LinearLayout)findViewById(R.id.nOver1);
        lNotif2 = (LinearLayout)findViewById(R.id.nOver2);
        lNotif3 = (LinearLayout)findViewById(R.id.nOver3);
        imgNotif1 = (ImageView)findViewById(R.id.imgOverNotif1);
        imgNotif2 = (ImageView)findViewById(R.id.imgOverNotif2);
        imgNotif3 = (ImageView)findViewById(R.id.imgOverNotif3);
        lNotif1.setVisibility(View.GONE);
        lNotif2.setVisibility(View.GONE);
        lNotif3.setVisibility(View.GONE);
        tvNotif1 = (TextView)findViewById(R.id.tvOverNotif1);
        tvNotif2 = (TextView)findViewById(R.id.tvOverNotif2);
        tvNotif3 = (TextView)findViewById(R.id.tvOverNotif3);
        Notif("Terdapat kebocoran gas", 1);
        Notif("Sonar tidak terdeteksi", 2);
        Notif("Pintu Tertutup", 3);
        Notif("", 2);
        Notif("Sonar terdeteksi", 2);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),SetingpinActivity.class);
                startActivity(i);
            }
        });
        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),TutorialActivity.class);
                startActivity(i);
            }
        });
        handler.postDelayed(runnable = new Runnable() {
            public void run() {
                updateTime();
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
    void Notif(String sVall, int iType){
        iType--;
        if (sVall.equals("") && iNotif[iType] != 0) {
            iNotif[iType] = 0;
            for (int i = 0; i < iNotif.length; i++){
                if (iNotif[i] > iNotif[iType]){iNotif[i] = iNotif[i] - 1;}
            }
            thisNotif--;
        } else if (iNotif[iType] == 0){
            thisNotif++;
            sNotif[iType] = sVall;
            iNotif[iType] = thisNotif;
        } else {
            sNotif[iType] = sVall;
        }
        if (thisNotif == 0) {
            lNotif1.setVisibility(View.GONE);
            lNotif2.setVisibility(View.GONE);
            lNotif3.setVisibility(View.GONE);
        } else {
            lNotif1.setVisibility(View.VISIBLE);
            lNotif2.setVisibility(View.VISIBLE);
            lNotif3.setVisibility(View.VISIBLE);
            if (thisNotif == 1){
                lNotif1.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif2.setBackground(null);
                lNotif3.setBackground(null);
                imgNotif1.setVisibility(View.VISIBLE);
                imgNotif2.setVisibility(View.GONE);
                imgNotif3.setVisibility(View.GONE);
                tvNotif2.setText("");
                tvNotif3.setText("");
                for (int i = 0; i < iNotif.length; i++){
                    if (iNotif[i] == 1){
                        tvNotif1.setText(sNotif[i]);
                    }
                }
            } else if (thisNotif == 2) {
                lNotif1.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif2.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif3.setBackground(null);
                imgNotif1.setVisibility(View.VISIBLE);
                imgNotif2.setVisibility(View.VISIBLE);
                imgNotif3.setVisibility(View.GONE);
                tvNotif3.setText("");
                for (int i = 0; i < iNotif.length; i++){
                    if (iNotif[i] == 1){tvNotif1.setText(sNotif[i]);}
                    else if (iNotif[i] == 2){tvNotif2.setText(sNotif[i]);}
                }
            } else if (thisNotif == 3) {
                lNotif1.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif2.setBackgroundResource(R.drawable.costume_notif_background);
                lNotif3.setBackgroundResource(R.drawable.costume_notif_background);
                imgNotif1.setVisibility(View.VISIBLE);
                imgNotif2.setVisibility(View.VISIBLE);
                imgNotif3.setVisibility(View.VISIBLE);
                for (int i = 0; i < iNotif.length; i++){
                    if (iNotif[i] == 1){tvNotif1.setText(sNotif[i]);}
                    else if (iNotif[i] == 2){tvNotif2.setText(sNotif[i]);}
                    else if (iNotif[i] == 3){tvNotif3.setText(sNotif[i]);}
                }
            }
        }
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
        handler.removeCallbacks(runnable);
    }
}