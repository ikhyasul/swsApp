package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment2 extends Fragment {
    String DeveloperCode = "1234";
    int passDevelop = 10;

    public Fragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        EditText editText = (EditText)view.findViewById(R.id.iPin);
        ImageView imageView = (ImageView)view.findViewById(R.id.btnPinEnter);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sCode = editText.getText().toString();
                if (sCode.equals(DeveloperCode)){
                     if(passDevelop != 0){passDevelop--;}
                     else{
                         Bundle extras = new Bundle();
                         extras.putInt("Send",1);
                         Intent c = new Intent(getActivity(),OneForAllActivity.class);
                         c.putExtras(extras);
                         startActivity(c);
                     }
                }
                if (sCode.equals("DeveloperCode")){
//                    Intent i= new Intent(getApplicationContext(),SetingActivity.class);
//                    startActivity(i);
                } else if (editText.getText().toString().length() > 6) {
                    NOTIF("Pin Terlalu Panjang");
                } else if (editText.getText().toString().length() < 6) {
                    NOTIF("Pin Terlalu Pendek");
                } else {
                    NOTIF("Pin Salah");
                }
            }
        });
        return view;
    }
    void NOTIF (String message){
        Context context = getActivity();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}