package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SetingpinActivity extends AppCompatActivity {
    String iPin = "1234";
    boolean bPassView = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setingpin);
        LinearLayout btnOver = (LinearLayout) findViewById(R.id.btnPinOver);
        LinearLayout btnTutorial = (LinearLayout) findViewById(R.id.btnPinTutorial);
        EditText editText = (EditText)findViewById(R.id.iPin);
        ImageView imageView = (ImageView) findViewById(R.id.btnPinEnter);
        ImageView View = (ImageView)findViewById(R.id.btnPinPas);
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                if (bPassView){editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);}
                else {editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);}
                bPassView = !bPassView;
            }
        });
        btnOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),OverviewActivity.class);
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
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().equals(iPin)){
                    Intent i= new Intent(getApplicationContext(),SetingActivity.class);
                    startActivity(i);
                } else {
                    NOTIF("Pin Salah");
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